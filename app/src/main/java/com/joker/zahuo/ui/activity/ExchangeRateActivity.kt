package com.joker.zahuo.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.joker.mysdk.entity.jisu.AllExchangeCurrencyEntity
import com.joker.mysdk.entity.jisu.ExchangeConvertEntity
import com.joker.mysdk.net.Api
import com.joker.mysdk.net.NetWorkUtil
import com.joker.mysdk.net.RetrofitHelper
import com.joker.mysdk.net.SubscribeWrapper
import com.joker.mysdk.utils.GsonTools
import com.joker.mysdk.utils.LogUtil
import com.joker.mysdk.utils.SpUtils
import com.joker.zahuo.R
import com.joker.zahuo.constant.ConstantKey
import com.joker.zahuo.constant.SPKey

class ExchangeRateActivity : AppCompatActivity() {
    private var original_country_icon: ImageView? = null
    private var original_country_name: TextView? = null
    private var target_country_icon: ImageView? = null
    private var target_country_name: TextView? = null
    private var allCurrencyList = ArrayList<AllExchangeCurrencyEntity.ExchangeCurencyBean>()
    private var hasUSD = false
    private var hasCNY = false
//    private var flagsMap: MutableMap<String,Int>? = null
    private var flagsMap= mutableMapOf<String,Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exchange_rate)
        initFlags()
        getAllCurrencyCode()
//        exchangeRateConvert()

    }

    private fun initFlags() {
        var stringArray = resources.getStringArray(R.array.currency_country_name)
        var intArray = resources.getIntArray(R.array.currency_country_flag)
//        for(i in stringArray.indices){
        for(i in 0..3){
            flagsMap?.set(stringArray[i],intArray[i])
        }

    }

    private fun setUI() {
        LogUtil.d(hasCNY)
        LogUtil.d(hasUSD)
        //设置原始货币
        if (hasCNY) {//有rmb
            original_country_name?.text = ConstantKey.RMB
            original_country_icon?.setImageResource(R.drawable.flag_china)
        } else {//没有rmb
            if (hasUSD) {//有美元
                original_country_name?.text = ConstantKey.USD
                original_country_icon?.setImageResource(R.drawable.flag_usa)
            } else {
                //既没有rmb，也没有美元，取集合第一个
                if (allCurrencyList.isNotEmpty()) {
                    var name = allCurrencyList[0].name
                    original_country_name?.text = name
                    if(!TextUtils.isEmpty(name) && flagsMap?.contains(name)!!){
                        flagsMap?.get(allCurrencyList[0].name)?.let { original_country_icon?.setImageResource(it) }
                    }
                }
            }
        }

        //设置目标货币
        if (hasCNY && hasUSD) {//有rmb,有美元
            target_country_name?.text = ConstantKey.USD
            original_country_icon?.setImageResource(R.drawable.flag_usa)
        } else if ((!hasCNY && hasUSD) || (hasCNY && !hasUSD)) {//没有rmb，有美元,美元作为原始货币;或者有rmb没有美元;都取第一个其他货币作为目标货币
            if (allCurrencyList.isNotEmpty()) {
                original_country_name?.text = allCurrencyList[0].name
            }
        } else {//没有rmb，没有美元，取第二个作为目标货币
            if (allCurrencyList.size > 1) {
                original_country_name?.text = allCurrencyList[1].name
            }
        }


    }

    /**获取所有汇率编码*/
    private fun getAllCurrencyCode() {
        RetrofitHelper.getInstance().getApiService(Api.JISU_BASE_URL, Api::class.java)
                .QueryExchangeCurrency(ConstantKey.JISU_KEY)
                .compose(NetWorkUtil.rxSchedulerHelper())
                .subscribe(SubscribeWrapper(object : SubscribeWrapper.RequestListener<AllExchangeCurrencyEntity> {
                    override fun onSuccess(t: AllExchangeCurrencyEntity?) {
                        if (t?.status.equals(Api.SUCCESS_CODE)) {
//                            allCurrencyList = arrayOf(t.result)
                            var jsonString = GsonTools.createGsonString(t)
                            if (!TextUtils.isEmpty(jsonString)) {
                                SpUtils.saveString(this@ExchangeRateActivity, SPKey.ALL_CURRENCY_LIST, "")
                            }
                            t?.result?.forEach {
                                //                                LogUtil.d(it.name,it.currency)
//                                Log.e("---","<item>"+it.name+"</item>")
                                when (it.currency) {
                                    ConstantKey.USD_CODE -> hasUSD = true
                                    ConstantKey.CNY_CODE -> hasCNY = true
                                }
                                allCurrencyList?.add(it)

                            }
                            setUI()
                        } else {
                            Toast.makeText(this@ExchangeRateActivity, getString(R.string.network_connection_error), Toast.LENGTH_LONG)
                        }
                    }

                    override fun onFail(message: String?) {


                    }

                }))

    }


    /**汇率转换*/
    private fun exchangeRateConvert() {
        RetrofitHelper.getInstance().getApiService(Api.JISU_BASE_URL, Api::class.java)
                .exchangeConvert("", "", "")
                .compose(NetWorkUtil.rxSchedulerHelper())
                .subscribe(SubscribeWrapper(object : SubscribeWrapper.RequestListener<ExchangeConvertEntity> {
                    override fun onSuccess(t: ExchangeConvertEntity?) {
                        if (t?.status.equals(Api.SUCCESS_CODE)) {
                            TODO()
                        } else {
                            Toast.makeText(this@ExchangeRateActivity, getString(R.string.network_connection_error), Toast.LENGTH_LONG)
                        }

                    }

                    override fun onFail(message: String?) {

                    }
                }))


    }


}
