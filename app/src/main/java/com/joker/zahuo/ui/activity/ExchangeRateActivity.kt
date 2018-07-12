package com.joker.zahuo.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
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
import com.joker.mysdk.utils.SpUtils
import com.joker.zahuo.R
import com.joker.zahuo.constant.ConstantKey
import com.joker.zahuo.constant.SPKey
import kotlinx.android.synthetic.main.activity_exchange_rate.*

class ExchangeRateActivity : AppCompatActivity() {

    private var allCurrencyList = ArrayList<AllExchangeCurrencyEntity.ExchangeCurencyBean>()
    private var hasUSD = false
    private var hasCNY = false
    private var flagsMap = mutableMapOf<String, Int>()
    private var originalCurrency: AllExchangeCurrencyEntity.ExchangeCurencyBean? = null
    private var targetCurrency: AllExchangeCurrencyEntity.ExchangeCurencyBean? = null
    private var amout = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exchange_rate)
        initView()
        initFlags()
        getAllCurrencyCode()
        initAction()
    }

    private fun initAction() {
        et_original_money.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                when (actionId) {
                    EditorInfo.IME_ACTION_SEARCH -> exchangeRateConvert()
                }

                return false
            }
        })

    }



    private fun initView() {

    }

    private fun initFlags() {
        var stringArray = resources.getStringArray(R.array.currency_country_name)
        var intArray = resources.getIntArray(R.array.currency_country_flag)
//        for(i in stringArray.indices){
        for (i in 0..3) {
            flagsMap?.set(stringArray[i], intArray[i])
        }

    }

    private fun setUIAndData() {
        //设置原始货币
        if (hasCNY) {//有rmb
            tv_original_currency?.text = ConstantKey.RMB + " " + ConstantKey.CNY_CODE
            iv_original_currency?.setImageResource(R.drawable.flag_china)
            originalCurrency = AllExchangeCurrencyEntity.ExchangeCurencyBean(ConstantKey.RMB, ConstantKey.CNY_CODE)

        } else {//没有rmb
            if (hasUSD) {//有美元
                tv_original_currency?.text = ConstantKey.USD + " " + ConstantKey.USD_CODE
                iv_original_currency?.setImageResource(R.drawable.flag_usa)
                originalCurrency = AllExchangeCurrencyEntity.ExchangeCurencyBean(ConstantKey.USD, ConstantKey.USD_CODE)
            } else {
                //既没有rmb，也没有美元，取集合第一个
                if (allCurrencyList.isNotEmpty()) {
                    var name = allCurrencyList[0].name
                    tv_original_currency?.text = name
                    if (!TextUtils.isEmpty(name) && flagsMap?.contains(name)!!) {
                        originalCurrency = AllExchangeCurrencyEntity.ExchangeCurencyBean(name, allCurrencyList[0].currency)
                        flagsMap?.get(allCurrencyList[0].name)?.let { iv_original_currency?.setImageResource(it) }
                    }
                }
            }
        }

        //设置目标货币
        if (hasCNY && hasUSD) {//有rmb,有美元
            tv_target_currency?.text = ConstantKey.USD + " " + ConstantKey.USD_CODE
            iv_target_currency?.setImageResource(R.drawable.flag_usa)
            targetCurrency = AllExchangeCurrencyEntity.ExchangeCurencyBean(ConstantKey.USD, ConstantKey.USD_CODE)
        } else if ((!hasCNY && hasUSD) || (hasCNY && !hasUSD)) {//没有rmb，有美元,美元作为原始货币;或者有rmb没有美元;都取第一个其他货币作为目标货币
            if (allCurrencyList.isNotEmpty()) {
                for (it in allCurrencyList) {
                    if (ConstantKey.USD.equals(it.name)) {
                        tv_target_currency?.text = it.name
                        flagsMap?.get(it.name)?.let { iv_target_currency?.setImageResource(it) }
                        targetCurrency = AllExchangeCurrencyEntity.ExchangeCurencyBean(it.name, it.currency)
                        break
                    }
                }

            }
        } else {//没有rmb，没有美元，取第二个作为目标货币
            if (allCurrencyList.size > 1) {
                tv_target_currency?.text = allCurrencyList[1].name
                targetCurrency = AllExchangeCurrencyEntity.ExchangeCurencyBean(allCurrencyList[1].name, allCurrencyList[1].currency)
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
                            var jsonString = GsonTools.createGsonString(t)
                            if (!TextUtils.isEmpty(jsonString)) {
                                SpUtils.saveString(this@ExchangeRateActivity, SPKey.ALL_CURRENCY_LIST, "")
                            }
                            t?.result?.let { allCurrencyList = t.result }
                            t?.result?.let {
                                //                                Log.e("---","<item>"+it.name+"</item>")
                                for (it in t.result) {
                                    when (it.currency) {
                                        ConstantKey.USD_CODE -> hasUSD = true
                                        ConstantKey.CNY_CODE -> hasCNY = true
                                    }
                                    if (hasUSD && hasCNY) break
                                }
//                                allCurrencyList?.add(it)
                            }
                            setUIAndData()
                            exchangeRateConvert()
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
        if (null == originalCurrency) {
            return
        }
        if (null == targetCurrency) {
            return
        }
        RetrofitHelper.getInstance().getApiService(Api.JISU_BASE_URL, Api::class.java)
                .exchangeConvert(ConstantKey.JISU_KEY,originalCurrency?.currency, targetCurrency?.currency, amout?.toString())
                .compose(NetWorkUtil.rxSchedulerHelper())
                .subscribe(SubscribeWrapper(object : SubscribeWrapper.RequestListener<ExchangeConvertEntity> {
                    override fun onSuccess(t: ExchangeConvertEntity?) {
                        if (t?.status.equals(Api.SUCCESS_CODE)) {
                            tv_target_money.text = t?.result?.camount
                        } else {
                            Toast.makeText(this@ExchangeRateActivity, getString(R.string.network_connection_error), Toast.LENGTH_LONG)
                        }

                    }

                    override fun onFail(message: String?) {

                    }
                }))
    }


}
