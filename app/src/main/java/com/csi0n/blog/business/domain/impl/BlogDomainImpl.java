package com.csi0n.blog.business.domain.impl;

import com.csi0n.blog.business.api.BlogApi;
import com.csi0n.blog.business.api.retrofit.IBlogHttpApi;
import com.csi0n.blog.business.domain.BlogDomain;
import com.csi0n.blog.business.pojo.request.GetHomeIndexRequest;
import com.csi0n.blog.business.pojo.response.GetHomeIndexResponse;
import com.csi0n.blog.business.pojo.response.GetTagIndexResponse;
import com.csi0n.blog.core.net.NetWorkException;
import com.google.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by csi0n on 10/10/16.
 */

public class BlogDomainImpl implements BlogDomain {
    @Inject
    BlogApi blogApi;

    @Override
    public Observable<GetHomeIndexResponse> GetHomeIndex() {
        return Observable.just(new GetHomeIndexRequest()).flatMap(new Func1<GetHomeIndexRequest, Observable<GetHomeIndexResponse>>() {
            @Override
            public Observable<GetHomeIndexResponse> call(GetHomeIndexRequest getHomeIndexRequest) {
                try {
                    GetHomeIndexResponse response = blogApi.GetHomeIndexResponse(getHomeIndexRequest);
                    return Observable.just(response);
                } catch (NetWorkException e) {
                    return Observable.error(e);
                }
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<GetTagIndexResponse> GetTagIndex() {
        return Observable.just(new GetTagIndexResponse()).flatMap(new Func1<GetTagIndexResponse, Observable<GetTagIndexResponse>>() {
            @Override
            public Observable<GetTagIndexResponse> call(GetTagIndexResponse getTagIndexResponse) {
                try {
                    GetTagIndexResponse response=blogApi.GetTagIndexResponse(getTagIndexResponse);
                    return  Observable.just(response);
                }catch (NetWorkException e){
                    return  Observable.error(e);
                }
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
