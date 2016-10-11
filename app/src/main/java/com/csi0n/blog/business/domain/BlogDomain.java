package com.csi0n.blog.business.domain;

import com.csi0n.blog.business.pojo.response.GetHomeIndexResponse;

import rx.Observable;
import rx.Observer;

/**
 * Created by csi0n on 10/10/16.
 */

public interface BlogDomain {
    Observable<GetHomeIndexResponse> GetHomeIndex();
}
