package com.joker.mysdk.net;

import android.text.TextUtils;

import com.joker.mysdk.entity.BaseEntity;
import com.joker.mysdk.utils.LogUtil;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/**
 * Created by PengXJ on 2018/6/29.
 */

public class SubscribeWrapper<T extends BaseEntity> implements Subscriber<T> {
    private RequestListener<T> requestListener;

    public SubscribeWrapper(RequestListener<T> requestListener) {
        this.requestListener = requestListener;
    }


    @Override
    public void onSubscribe(Subscription s) {
        s.request(Long.MAX_VALUE);//表示请求的数量
    }

    @Override
    public void onNext(T t) {
        String code = t.getCode();
        String message = t.getMessage();
        if(null != t){
            if(!TextUtils.isEmpty(code)){
                if(Api.SUCCESS_CODE.equals(code)){
                    requestListener.onSuccess(t);
                }else {
                    requestListener.onFail(message);
                }
            }else{
                requestListener.onFail(Api.FAIL_MESSAGE);
            }
        }else {
            requestListener.onFail(Api.FAIL_MESSAGE);
        }
    }

    @Override
    public void onError(Throwable t) {
        LogUtil.e(t.getMessage()+" "+t.getLocalizedMessage());
        requestListener.onFail(t.getMessage());
    }

    @Override
    public void onComplete() {

    }

    public interface RequestListener<T>{
        void onSuccess(T t);
        void onFail(String message);
    }
}
