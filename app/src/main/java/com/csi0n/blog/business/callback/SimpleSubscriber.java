package com.csi0n.blog.business.callback;

import com.csi0n.blog.business.pojo.response.ext.BaseResponse;
import com.csi0n.blog.core.io.CLog;
import com.csi0n.blog.core.string.Constants;

import rx.Subscriber;

/**
 * Created by chqss on 2016/5/3 0003.
 */
public class SimpleSubscriber<T extends BaseResponse> extends Subscriber<T> {
    private boolean isSuccess = false; // 是否成功
    private T response; // 得到的数据结果

    @Override
    public void onStart() {
        super.onStart();
        CLog.d(this + "....onStart");
    }

    @Override
    public final void onNext(T response) {
        CLog.d(this + "....onNext");
        if (response == null) {
            CLog.e("response is null.");
            return;
        }
        if (response.status == Constants.CODE_SUCCESS) {
            isSuccess = true;
            onHandleSuccess(response);
        } else {
            onHandleFail(response.msg, null);
        }
    }

    @Override
    public final void onError(Throwable throwable) {
        CLog.d(this + "....onError");
        onHandleFail(null, throwable);
        onHandleFinish();
    }

    @Override
    public final void onCompleted() {
        CLog.d(this + "....onCompleted");
        onHandleFinish();
    }

    /**
     * 处理成功
     */
    public void onHandleSuccess(T response) {
        CLog.d("response = " + response);
    }

    /**
     * 内容为空
     */
    public void onHandleEmptyData() {
    }

    /**
     * 处理失败
     */
    public void onHandleFail(String message, Throwable throwable) {
        CLog.d(this + "....onHandleFail");
        CLog.e("message = %s ,throwable = %s", message, throwable);
    }

    /**
     * 处理结束
     */
    public void onHandleFinish() {
        CLog.d(this + "....onHandleFinish");
    }

    public T getResponse() {
        return response;
    }

    public boolean isSuccess() {
        return isSuccess;
    }
}
