package com.joker.mysdk.net;

import android.text.TextUtils;
import android.util.Log;

import com.joker.mysdk.utils.LogUtil;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by PengXJ on 2018/6/8.
 */

public class RetrofitHelper {
    private static RetrofitHelper retrofitHelper;
    private final OkHttpClient mClient;

    public static RetrofitHelper  getInstance(){
        if(null == retrofitHelper){
            retrofitHelper = new RetrofitHelper();
        }
        return retrofitHelper;
    }

    private RetrofitHelper() {
        OkHttpClient client = new OkHttpClient();
        mClient = provideClient(client.newBuilder());
    }

    private Retrofit createRetrofit(OkHttpClient client, String url) {
        return new Retrofit.Builder()
                .baseUrl(url)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }



    private OkHttpClient provideClient(OkHttpClient.Builder builder) {

        //https
        X509TrustManager xtm = new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) {
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                X509Certificate[] x509Certificates = new X509Certificate[0];
                return x509Certificates;
            }
        };

        SSLContext sslContext = null;
        try {
            sslContext = SSLContext.getInstance("SSL");

            sslContext.init(null, new TrustManager[]{xtm}, new SecureRandom());

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession sslSession) {
                return true;
            }
        };
        builder.addInterceptor(new HeadInterceptor(true));
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {

            @Override
            public void log(String message) {
                if(!TextUtils.isEmpty(message)){
                    LogUtil.d(message);
                }
            }

        });
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        builder.addInterceptor(interceptor);
//        设置统一的请求头部参数


        //设置超时
        builder.connectTimeout(10, TimeUnit.SECONDS);
        builder.readTimeout(20, TimeUnit.SECONDS);
        builder.writeTimeout(20, TimeUnit.SECONDS);
        //错误重连
        builder.retryOnConnectionFailure(true);
        //设置https
        builder.sslSocketFactory(sslContext.getSocketFactory());
        builder.hostnameVerifier(DO_NOT_VERIFY);

        return builder.build();
    }

    public <T>T getApiService(Class<T> clazz){
        return createRetrofit(mClient,Api.BASE_URL).create(clazz);
    }
}
