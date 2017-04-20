package com.csi0n.blog.business.pojo.response;

import com.csi0n.blog.business.pojo.model.Tag;
import com.csi0n.blog.business.pojo.response.ext.BaseResponse;
import com.google.gson.annotations.SerializedName;

/**
 * Created by csi0n on 10/24/16.
 */

public class GetTagIndexResponse extends BaseResponse {
    @SerializedName("result")
    public Tag[] tags;
}
