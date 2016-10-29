package com.csi0n.blog.business.api.retrofit;

import android.util.Log;

import com.csi0n.blog.business.api.BlogApi;
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
import com.csi0n.blog.core.io.CLog;
import com.csi0n.blog.core.io.FileUtils;
import com.csi0n.blog.core.net.NetWorkException;
import com.csi0n.blog.core.string.Constants;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by csi0n on 10/10/16.
 */

public class BlogApiRetrofitImpl implements BlogApi {
    IBlogHttpApi httpApi;

    public BlogApiRetrofitImpl() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        int cacheSize = 100 * 1024 * 1024;//1000Mib
        Cache cache = new Cache(FileUtils.getSaveFolder(Constants.cacheFolder), cacheSize);
        builder.cache(cache);
        builder.readTimeout(15, TimeUnit.MINUTES);
        builder.connectTimeout(15, TimeUnit.MINUTES);
        builder.writeTimeout(15, TimeUnit.MINUTES);
        builder.interceptors().add(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Response response = chain.proceed(request);
                CLog.i("Interceptor:request = %s, response = %s", request, response);
                return response;
            }
        });
        httpApi = new Retrofit.Builder()
                .client(builder.build())
                .baseUrl(Constants.BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(IBlogHttpApi.class);
    }

    @Override
    public GetHomeIndexResponse GetHomeIndexResponse(GetHomeIndexRequest getHomeIndexRequest) throws NetWorkException {
        CLog.i("BlogApiRetrofitImpl.GetHomeIndexResponse request = " + getHomeIndexRequest);
        return new RetrofitAdapter<GetHomeIndexResponse>() {
            @Override
            GetHomeIndexResponse call() throws Exception {
                return httpApi.GetHomeIndexResponse().execute().body();
            }
        }.get();
    }

    @Override
    public GetTagIndexResponse GetTagIndexResponse(GetTagIndexRequest getTagIndexRequest) throws NetWorkException {
        CLog.i("BlogApiRetrofitImpl.GetTagIndexResponse request = " + getTagIndexRequest);
        return new RetrofitAdapter<GetTagIndexResponse>() {
            @Override
            GetTagIndexResponse call() throws Exception {
                return httpApi.GetTagIndexResponse().execute().body();
            }
        }.get();
    }

    @Override
    public GetTagShowResponse GetTagShowResponse(final GetTagShowRequest getTagShowRequest) throws NetWorkException {
        CLog.i("BlogApiRetrofitImpl.GetTagShowResponse request = " + getTagShowRequest);
        return new RetrofitAdapter<GetTagShowResponse>() {
            @Override
            GetTagShowResponse call() throws Exception {
                return httpApi.GetTagShowResponse(getTagShowRequest.id).execute().body();
            }
        }.get();
    }

    @Override
    public GetArticleSearchResponse GetArticleSearchResponse(final GetArticleSearchRequest getArticleSearchRequest) throws NetWorkException {
        CLog.i("BlogApiRetrofitImpl.GetArticleSearchResponse request = " + getArticleSearchRequest);
        return new RetrofitAdapter<GetArticleSearchResponse>() {
            @Override
            GetArticleSearchResponse call() throws Exception {
                return httpApi.GetArticleSearchResponse(getArticleSearchRequest.key).execute().body();
            }
        }.get();
    }

    @Override
    public GetCommentListsResponse GetCommentListsResponse(final GetCommentListsRequest getCommentListsRequest) throws NetWorkException {
        CLog.i("BlogApiRetrofitImpl.GetCommentListsRequest request = " + getCommentListsRequest);
        return new RetrofitAdapter<GetCommentListsResponse>() {
            @Override
            GetCommentListsResponse call() throws Exception {
                return httpApi.GetCommentsListsResponse(getCommentListsRequest.article_id).execute().body();
            }
        }.get();
    }
}
