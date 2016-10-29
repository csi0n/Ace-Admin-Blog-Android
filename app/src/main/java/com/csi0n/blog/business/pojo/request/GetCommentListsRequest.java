package com.csi0n.blog.business.pojo.request;

import com.csi0n.blog.business.pojo.request.ext.BaseRequest;

/**
 * Created by csi0n on 10/28/16.
 */

public class GetCommentListsRequest extends BaseRequest {
    public int article_id;

    public GetCommentListsRequest(int article_id) {
        this.article_id = article_id;
    }
}
