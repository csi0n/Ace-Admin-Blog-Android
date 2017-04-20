package com.csi0n.blog.business.pojo.model;

import com.csi0n.blog.business.pojo.model.ext.BaseModel;
import com.google.gson.annotations.SerializedName;

/**
 * Created by csi0n on 10/28/16.
 */

public class Comment extends BaseModel {

    /**
     * post_id : 6343762919284540000
     * thread_id : 6339007472828679000
     * thread_key : 6
     * author_id : 10887316
     * author_key :
     * author_name : Mr_chen886
     * author_email :
     * author_url : http://t.qq.com/q841506740
     * ip : 61.172.57.230
     * created_at : 2016-10-21T12:00:03+08:00
     * message : 123
     * status : approved
     * type :
     * parent_id :
     * agent : Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.59 Safari/537.36
     */

    @SerializedName("post_id")
    public String postId;
    @SerializedName("thread_id")
    public String threadId;
    @SerializedName("thread_key")
    public String threadKey;
    @SerializedName("author_id")
    public String authorId;
    @SerializedName("author_key")
    public String authorKey;
    @SerializedName("author_name")
    public String authorName;
    @SerializedName("author_email")
    public String authorEmail;
    @SerializedName("author_url")
    public String authorUrl;
    @SerializedName("ip")
    public String ip;
    @SerializedName("created_at")
    public String createdAt;
    @SerializedName("message")
    public String message;
    @SerializedName("status")
    public String status;
    @SerializedName("type")
    public String type;
    @SerializedName("parent_id")
    public String parentId;
    @SerializedName("agent")
    public String agent;
}
