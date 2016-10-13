package com.csi0n.blog.business.pojo.model;

import com.csi0n.blog.business.pojo.model.ext.BaseModel;
import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

/**
 * Created by csi0n on 10/11/16.
 */

public class Cate extends BaseModel {
    /**
     * id : 2
     * name : 分类1
     * pid : 0
     * sort : 12
     * created_at : 2016-10-09 16:46:23
     * updated_at : 2016-10-09 16:46:23
     */

    @SerializedName("id")
    public int id;
    @SerializedName("name")
    public String name;
    @SerializedName("pid")
    public int pid;
    @SerializedName("sort")
    public int sort;
    @SerializedName("created_at")
    public String createdAt;
    @SerializedName("updated_at")
    public String updatedAt;
    @SerializedName("articles")
    public Article[] articles;

    @Override
    public String toString() {
        return "Cate{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pid=" + pid +
                ", sort=" + sort +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", articles=" + Arrays.toString(articles) +
                '}';
    }
}
