package com.joker.zahuo.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.joker.mysdk.entity.jisu.ExchangeConvertEntity
import com.joker.mysdk.net.Api
import com.joker.mysdk.net.NetWorkUtil
import com.joker.mysdk.net.RetrofitHelper
import com.joker.mysdk.net.SubscribeWrapper
import com.joker.zahuo.R

class ExchangeRateActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exchange_rate)
        exchangeRateConvert()

    }

    private fun exchangeRateConvert() {
        RetrofitHelper.getInstance().getApiService(Api.JISU_BASE_URL,Api::class.java)
                .exchangeConvert("","","")
                .compose(NetWorkUtil.rxSchedulerHelper())
                .subscribe(SubscribeWrapper(object :SubscribeWrapper.RequestListener<ExchangeConvertEntity> {
                    override fun onSuccess(t: ExchangeConvertEntity?) {
                        if (t?.status.equals("0")){
                            TODO()
                        }else{
                            Toast.makeText(this@ExchangeRateActivity,getString(R.string.network_connection_error),Toast.LENGTH_LONG)
                        }

                    }

                    override fun onFail(message: String?) {

                    }
                }))



    }


}
