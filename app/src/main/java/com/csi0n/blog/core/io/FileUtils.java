package com.csi0n.blog.core.io;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;

import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.Response;

/**
 * Created by csi0n on 10/10/16.
 */

public class FileUtils {
    private static final String TAG="FileUtils";
    private static String saveFolder;
    public static void init(String saveFloder){
        saveFolder=saveFloder;
    }
    public static boolean checkSDCard() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }
    public static String getSDCardPath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }
    public static void installApk(Context context, File file) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setType("application/vnd.android.package-archive");
        intent.setData(Uri.fromFile(file));
        intent.setDataAndType(Uri.fromFile(file),
                "application/vnd.android.package-archive");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
    public static File getSaveFolder(String folderName) {
        File file = new File(getSDCardPath() + File.separator + folderName
                + File.separator);
        file.mkdirs();
        return file;
    }

    public static File getSaveFile(String folderPath, String fileNmae) {
        File file = new File(getSavePath(folderPath) + File.separator
                + fileNmae);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    public static boolean isFileExists(String folderPath, String fileNmae) {
        File file = new File(getSavePath(folderPath) + File.separator
                + fileNmae);
        return file.exists();
    }

    public static String getSavePath(String folderName) {
        return getSaveFolder(folderName).getAbsolutePath();
    }

    public static boolean saveResponse(Response response, File saveFile) throws IOException {
        InputStream is = null;
        byte[] buf = new byte[2048];
        int len = 0;
        FileOutputStream fos = null;
        is = response.body().byteStream();
        fos = new FileOutputStream(saveFile);
        while ((len = is.read(buf)) != -1) {
            fos.write(buf, 0, len);
        }
        closeIO(fos);
        return true;
    }

    public static boolean bitmapToFile(Bitmap bitmap, String filePath) {
        boolean isSuccess = false;
        if (bitmap == null)
            return isSuccess;
        File file = new File(filePath.substring(0,
                filePath.lastIndexOf(File.separator)));
        if (!file.exists())
            file.mkdirs();
        OutputStream out = null;
        try {
            out = new BufferedOutputStream(new FileOutputStream(filePath),
                    8 * 1024);
            isSuccess = bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeIO(out);
        }
        return isSuccess;
    }

    public static void closeIO(Closeable... closeables) {
        if (null == closeables || closeables.length <= 0) {
            return;
        }
        for (Closeable cb : closeables) {
            try {
                if (null == cb) {
                    continue;
                }
                cb.close();
            } catch (IOException e) {
                throw new RuntimeException(
                        FileUtils.class.getClass().getName(), e);
            }
        }
    }

    private static String SDPATH = getSavePath(saveFolder) + "/formats/";

    public static void saveBitmap(Bitmap bm, String picName) {
        try {
            if (!isFileExist("")) {
                File tempf = createSDDir("");
            }
            File f = new File(SDPATH, picName + ".JPEG");
            if (f.exists()) {
                f.delete();
            }
            FileOutputStream out = new FileOutputStream(f);
            bm.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static File createSDDir(String dirName) throws IOException {
        File dir = new File(SDPATH + dirName);
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {

            System.out.println("createSDDir:" + dir.getAbsolutePath());
            System.out.println("createSDDir:" + dir.mkdir());
        }
        return dir;
    }

    public static boolean isFileExist(String fileName) {
        File file = new File(SDPATH + fileName);
        file.isFile();
        return file.exists();
    }

    public static void delFile(String fileName) {
        File file = new File(SDPATH + fileName);
        if (file.isFile()) {
            file.delete();
        }
        file.exists();
    }

    public static void deleteDir() {
        File dir = new File(SDPATH);
        if (dir == null || !dir.exists() || !dir.isDirectory())
            return;

        for (File file : dir.listFiles()) {
            if (file.isFile())
                file.delete(); // 删除所有文件
            else if (file.isDirectory())
                deleteDir(); // 递规的方式删除文件夹
        }
        dir.delete();// 删除目录本身
    }

    public static boolean fileIsExists(String path) {
        try {
            File f = new File(path);
            if (!f.exists()) {
                return false;
            }
        } catch (Exception e) {

            return false;
        }
        return true;
    }

    public static boolean copyFile(File oldPath, File newPath) {
        try {
            int bytesum = 0;
            int byteread = 0;
            if (oldPath.exists()) { //文件存在时
                InputStream inStream = new FileInputStream(oldPath); //读入原文件
                FileOutputStream fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1444];
                while ((byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread; //字节数 文件大小
                    System.out.println(bytesum);
                    fs.write(buffer, 0, byteread);
                }
                closeIO(inStream);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }

    }
}
