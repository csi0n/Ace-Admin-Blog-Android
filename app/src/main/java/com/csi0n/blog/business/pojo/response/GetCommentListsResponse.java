package com.csi0n.blog.business.pojo.response;

import com.csi0n.blog.business.pojo.model.Comment;
import com.csi0n.blog.business.pojo.response.ext.BaseResponse;
import com.google.gson.annotations.SerializedName;

/**
 * Created by csi0n on 10/28/16.
 */

public class GetCommentListsResponse extends BaseResponse {
    @SerializedName("result")
    public Comment[] comments;
}
