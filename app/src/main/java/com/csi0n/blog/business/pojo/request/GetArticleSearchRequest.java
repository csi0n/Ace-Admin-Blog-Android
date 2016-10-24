package com.csi0n.blog.business.pojo.request;

import com.csi0n.blog.business.pojo.request.ext.BaseRequest;

/**
 * Created by csi0n on 10/24/16.
 */

public class GetArticleSearchRequest extends BaseRequest {
    public String key;

    public GetArticleSearchRequest(String key) {
        this.key = key;
    }
}
