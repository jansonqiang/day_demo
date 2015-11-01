package com.example.qiang.d_scrollview.myhttp;

import retrofit.http.GET;

/**
 * Created by qiang on 2015/10/27.
 */
public interface GithubServer {

    @GET("/ad/adList")
    String listRepos();
}
