package com.example.wenkun.githubapp;

import java.util.Observable;

/**
 * Created by Wenkun on 2017/12/21.
 */

public interface GithubService {
    @GET("/user/{user}");
    Observable<Github> getUser(@Path("user") String user);
}
