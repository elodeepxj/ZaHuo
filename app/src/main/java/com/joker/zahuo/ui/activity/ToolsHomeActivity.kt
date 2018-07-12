package com.joker.zahuo.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.joker.mysdk.utils.LogUtil
import com.joker.zahuo.R
import com.joker.zahuo.base.BaseActivity
import com.joker.zahuo.ui.adapter.ToolsAdapter
import kotlinx.android.synthetic.main.activity_tools_home.*
import java.util.*

class ToolsHomeActivity : BaseActivity() ,ToolsAdapter.OnItemClickListener{

    override fun initData() {
        initAdapter()
    }

    override fun initView() {
    }

    override fun initAction() {
        title_view.leftIconOnClick(onClickListener = View.OnClickListener {
            finish()
        })
        title_view.leftTextOnClick(onClickListener = View.OnClickListener {
            finish()
        })
    }

    override fun getLayout(): Int {
        return R.layout.activity_tools_home
    }

    override fun onClick(view: View?, i: Int) {
        when(i){
            0->goDictionary()
            1->goExchangeRate()
        }
    }

    /**跳转字典*/
    private fun goDictionary() {


    }

    /**跳转汇率*/
    private fun goExchangeRate() {
        startActivity(Intent(this@ToolsHomeActivity,ExchangeRateActivity::class.java))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    private fun initAdapter() {
        val mList = ArrayList<String>()
        mList.addAll(resources.getStringArray(R.array.tools_name))
        LogUtil.d(mList.size)
        rv.layoutManager = GridLayoutManager(this,4)
        val toolsAdapter =ToolsAdapter(this@ToolsHomeActivity,mList)
        rv.adapter = toolsAdapter
        toolsAdapter.setOnItemClickListener(this)
    }



}
