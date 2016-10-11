package com.csi0n.blog.business.pojo.response.ext;

import com.csi0n.blog.business.pojo.request.ext.BaseRequest;
import com.google.gson.annotations.SerializedName;

/**
 * Created by csi0n on 10/11/16.
 */

public class BaseResponse implements IResponse {
    @SerializedName("msg")
    public String msg;
    @SerializedName("status")
    public int status;
}
