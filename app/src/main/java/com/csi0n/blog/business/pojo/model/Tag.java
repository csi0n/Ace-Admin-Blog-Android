package com.csi0n.blog.business.pojo.model;

import com.csi0n.blog.business.pojo.model.ext.BaseModel;
import com.google.gson.annotations.SerializedName;

/**
 * Created by csi0n on 10/11/16.
 */

public class Tag extends BaseModel{

    /**
     * id : 1
     * name : PHP
     * created_at : 2016-10-09 16:39:15
     * updated_at : 2016-10-09 16:39:15
     */

    @SerializedName("id")
    public int id;
    @SerializedName("name")
    public String name;
    @SerializedName("created_at")
    public String createdAt;
    @SerializedName("updated_at")
    public String updatedAt;
}
