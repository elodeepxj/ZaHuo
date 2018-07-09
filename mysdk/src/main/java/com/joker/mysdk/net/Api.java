package com.joker.mysdk.net;

import com.joker.mysdk.entity.BaseEntity;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by tv on 2018/6/29.
 */

public interface Api {
    boolean isDebug = true;
    String DEV_URL ="http://m.maoyan.com/movie/";
    String PRODUCE_URL = "";
    String BASE_URL = isDebug?DEV_URL:PRODUCE_URL;

    //数据返回，成功码
    String SUCCESS_CODE = "0";
    String FAIL_MESSAGE="返回数据为空";


    //http://m.maoyan.com/movie/list.json?type=hot&offset=0&limit=1000
    @GET("list.json")
    Flowable<BaseEntity> getMovieList(@Query("type") String type, @Query("offset") String offset, @Query("limit") String limit);
}
