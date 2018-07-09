package com.joker.mysdk.net;

import android.text.TextUtils;

import com.joker.mysdk.base.BaseApplication;
import com.joker.mysdk.utils.LogUtil;
import com.joker.mysdk.utils.Version;

import java.io.IOException;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

/**
 * Created by PengXJ on 2018/6/8.
 */

public class HeadInterceptor implements Interceptor {
    private boolean hasPublicParameter = false;

    public HeadInterceptor(boolean hasParameter) {
        this.hasPublicParameter = hasParameter;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request newRequest;
        if (hasPublicParameter) {
            //有公共参数
            Request oldRequest = getRequest(chain);

            newRequest = getNewBuilder(oldRequest, oldRequest.method())
//                    .method(oldRequest.method(), oldRequest.body())
//                    .url(getBuilder(oldRequest).build())
                    .build();
        } else {
            //没有公共参数或者把公共参数放在头部
            newRequest = chain.request()
                    .newBuilder()
//                    .addHeader("","")//添加头部
                    .build();


        }
        return chain.proceed(newRequest);
    }


    private HttpUrl.Builder getBuilder(Request request) {
        HttpUrl.Builder authorizedUrlBuilder = request.url()
                .newBuilder()
                .setEncodedQueryParameter("actid", "")//添加公共参数
                .setEncodedQueryParameter("version", "")//添加公共参数
                .setEncodedQueryParameter("ts", "")//添加公共参数
                .setEncodedQueryParameter("vkey","");//添加公共参数
        return authorizedUrlBuilder;
    }

    private Request getRequest(Chain chain) {
        Request request = chain.request();
        return request;
    }

    private Request.Builder getNewBuilder(Request oldRequest, String method) {
        Request.Builder newRequestBuild = null;
        if ("POST".equals(method)) {
            RequestBody oldBody = oldRequest.body();
            String postBodyString = "";
            if (oldBody instanceof FormBody) {
                FormBody.Builder formBodyBuilder = new FormBody.Builder();
                formBodyBuilder.add("actid", "");
                formBodyBuilder.add("version",  Version.getVersionName(BaseApplication.getApplication()));
                formBodyBuilder.add("ts", "");
                formBodyBuilder.add("vkey", "");
                newRequestBuild = oldRequest.newBuilder();
                RequestBody formBody = formBodyBuilder.build();
                postBodyString = bodyToString(oldRequest.body());
                postBodyString += ((postBodyString.length() > 0) ? "&" : "") + bodyToString(formBody);
                if(!TextUtils.isEmpty(postBodyString)){
                    LogUtil.e(postBodyString);
                }
                newRequestBuild.post(RequestBody.create(MediaType.parse("application/x-www-form-urlencoded;charset=UTF-8"), postBodyString));
            } else if (oldBody instanceof MultipartBody) {
                MultipartBody oldBodyMultipart = (MultipartBody) oldBody;
                List<MultipartBody.Part> oldPartList = oldBodyMultipart.parts();
                MultipartBody.Builder builder = new MultipartBody.Builder();
                builder.setType(MultipartBody.FORM);
                RequestBody requestBody1 = RequestBody.create(MediaType.parse("text/plain"), "");
                RequestBody requestBody2 = RequestBody.create(MediaType.parse("text/plain"), "");
                RequestBody requestBody3 = RequestBody.create(MediaType.parse("text/plain"), "");
                for (MultipartBody.Part part : oldPartList) {
                    builder.addPart(part);
                    postBodyString += (bodyToString(part.body()) + "\n");
                }
                postBodyString += (bodyToString(requestBody1) + "\n");
                postBodyString += (bodyToString(requestBody2) + "\n");
                postBodyString += (bodyToString(requestBody3) + "\n");
//              builder.addPart(oldBody);  //不能用这个方法，因为不知道oldBody的类型，可能是PartMap过来的，也可能是多个Part过来的，所以需要重新逐个加载进去
                builder.addPart(requestBody1);
                builder.addPart(requestBody2);
                builder.addPart(requestBody3);
                newRequestBuild = oldRequest.newBuilder();
                newRequestBuild.post(builder.build());
                LogUtil.e("MultipartBody," + oldRequest.url());
            } else {
                newRequestBuild = oldRequest.newBuilder();
            }
        } else {
            // 添加新的参数
            HttpUrl.Builder commonParamsUrlBuilder = oldRequest.url()
                    .newBuilder()
                    .scheme(oldRequest.url().scheme())
                    .host(oldRequest.url().host())
                    .addQueryParameter("deviceOs", "")
                    .addQueryParameter("appVersion", "")
                    .addQueryParameter("appName", "");
            newRequestBuild = oldRequest.newBuilder()
                    .method(oldRequest.method(), oldRequest.body())
                    .url(commonParamsUrlBuilder.build());
        }

        return newRequestBuild;
    }

    private static String bodyToString(final RequestBody request) {
        try {
            final RequestBody copy = request;
            final Buffer buffer = new Buffer();
            if (copy != null)
                copy.writeTo(buffer);
            else
                return "";
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "did not work";
        }
    }

}
