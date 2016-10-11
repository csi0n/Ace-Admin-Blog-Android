package com.csi0n.blog.business.api.retrofit;

import com.csi0n.blog.business.pojo.request.GetHomeIndexRequest;
import com.csi0n.blog.business.pojo.response.GetHomeIndexResponse;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;

/**
 * Created by csi0n on 10/10/16.
 */

public interface IBlogHttpApi {
    @FormUrlEncoded
    @GET("home")
    Call<GetHomeIndexResponse> GetHomeIndexResponse();
}
