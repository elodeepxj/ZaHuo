package com.joker.mysdk.net;

import com.joker.mysdk.entity.jisu.AllExchangeCurrencyEntity;
import com.joker.mysdk.entity.jisu.ExchangeConvertEntity;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by tv on 2018/6/29.
 */

public interface Api {
    boolean isDebug = true;
    String DEV_URL ="http://m.maoyan.com/movie/";
    String PRODUCE_URL = "";
    String BASE_URL = isDebug?DEV_URL:PRODUCE_URL;
    String JISU_BASE_URL = "http://api.jisuapi.com/";

    //数据返回，成功码
    String SUCCESS_CODE = "0";
    String FAIL_MESSAGE="返回数据为空";


    /**获取所有货币*/
    @GET("exchange/currency")
    Flowable<AllExchangeCurrencyEntity> QueryExchangeCurrency(@Query("appkey") String appkey);

    /**汇率转换*/
    @POST("exchange/convert")
    Flowable<ExchangeConvertEntity> exchangeConvert(@Query("from") String from,@Query("to") String to,@Query("amount") String amount);
}
