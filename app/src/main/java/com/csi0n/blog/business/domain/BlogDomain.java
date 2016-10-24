package com.csi0n.blog.business.domain;

import com.csi0n.blog.business.pojo.response.GetArticleSearchResponse;
import com.csi0n.blog.business.pojo.response.GetHomeIndexResponse;
import com.csi0n.blog.business.pojo.response.GetTagIndexResponse;
import com.csi0n.blog.business.pojo.response.GetTagShowResponse;

import rx.Observable;

/**
 * Created by csi0n on 10/10/16.
 */

public interface BlogDomain {
    Observable<GetHomeIndexResponse> GetHomeIndex();

    Observable<GetTagIndexResponse> GetTagIndex();

    Observable<GetTagShowResponse> GetTagShow(int id);

    Observable<GetArticleSearchResponse> GetArticleSearch(String key);
}
