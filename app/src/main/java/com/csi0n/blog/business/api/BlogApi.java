package com.csi0n.blog.business.api;

import com.csi0n.blog.business.pojo.request.GetArticleSearchRequest;
import com.csi0n.blog.business.pojo.request.GetCommentListsRequest;
import com.csi0n.blog.business.pojo.request.GetHomeIndexRequest;
import com.csi0n.blog.business.pojo.request.GetTagIndexRequest;
import com.csi0n.blog.business.pojo.request.GetTagShowRequest;
import com.csi0n.blog.business.pojo.response.GetArticleSearchResponse;
import com.csi0n.blog.business.pojo.response.GetCommentListsResponse;
import com.csi0n.blog.business.pojo.response.GetHomeIndexResponse;
import com.csi0n.blog.business.pojo.response.GetTagIndexResponse;
import com.csi0n.blog.business.pojo.response.GetTagShowResponse;
import com.csi0n.blog.core.net.NetWorkException;

/**
 * Created by csi0n on 10/10/16.
 */

public interface BlogApi {
    GetHomeIndexResponse GetHomeIndexResponse(GetHomeIndexRequest getHomeIndexRequest) throws NetWorkException;

    GetTagIndexResponse GetTagIndexResponse(GetTagIndexRequest getTagIndexRequest) throws NetWorkException;

    GetTagShowResponse GetTagShowResponse(GetTagShowRequest getTagShowRequest) throws NetWorkException;

    GetArticleSearchResponse GetArticleSearchResponse(GetArticleSearchRequest getArticleSearchRequest) throws NetWorkException;

    GetCommentListsResponse GetCommentListsResponse(GetCommentListsRequest getCommentListsRequest) throws NetWorkException;
}
