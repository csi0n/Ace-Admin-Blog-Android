package com.csi0n.blog.business.pojo.response;

import com.csi0n.blog.business.pojo.model.Article;
import com.csi0n.blog.business.pojo.response.ext.BaseResponse;
import com.google.gson.annotations.SerializedName;

/**
 * Created by csi0n on 10/24/16.
 */

public class GetTagShowResponse extends BaseResponse {
    @SerializedName("result")
    public Article[] articles;
}
