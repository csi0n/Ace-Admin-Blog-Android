package com.csi0n.blog.business.pojo.response;

import com.csi0n.blog.business.pojo.model.Article;
import com.csi0n.blog.business.pojo.model.Cate;
import com.csi0n.blog.business.pojo.response.ext.BaseResponse;
import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

/**
 * Created by csi0n on 10/11/16.
 */

public class GetHomeIndexResponse extends BaseResponse {
    @SerializedName("result")
    public Cate[] cates;

    @Override
    public String toString() {
        return "GetHomeIndexResponse{" +
                "cates=" + Arrays.toString(cates) +
                '}';
    }
}
