package com.csi0n.blog.business.api.retrofit;

import com.csi0n.blog.business.pojo.request.GetHomeIndexRequest;
import com.csi0n.blog.business.pojo.response.GetArticleSearchResponse;
import com.csi0n.blog.business.pojo.response.GetCommentListsResponse;
import com.csi0n.blog.business.pojo.response.GetHomeIndexResponse;
import com.csi0n.blog.business.pojo.response.GetTagIndexResponse;
import com.csi0n.blog.business.pojo.response.GetTagShowResponse;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by csi0n on 10/10/16.
 */

public interface IBlogHttpApi {
    @GET("home")
    Call<GetHomeIndexResponse> GetHomeIndexResponse();

    @GET("tag")
    Call<GetTagIndexResponse> GetTagIndexResponse();

    @GET("tag/{id}")
    Call<GetTagShowResponse> GetTagShowResponse(@Path("id") int id);

    @GET("article/search/{key}")
    Call<GetArticleSearchResponse> GetArticleSearchResponse(@Path("key") String key);

    @GET("comments/lists/{id}")
    Call<GetCommentListsResponse> GetCommentsListsResponse(@Path("id") int id);
}
