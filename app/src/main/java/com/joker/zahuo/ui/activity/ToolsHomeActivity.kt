package com.joker.zahuo.ui.activity

import android.os.Bundle
import com.joker.zahuo.R
import com.joker.zahuo.base.BaseActivity
import com.joker.zahuo.ui.adapter.ToolsAdapter
import kotlinx.android.synthetic.main.activity_tools_home.*
import java.util.*

class ToolsHomeActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tools_home)
        val mList = ArrayList<String>()
        initData(mList)
        rv.adapter = ToolsAdapter(this,mList)

    }

    private fun initData(mList: ArrayList<String>) {
        var stringArray = resources.getStringArray(R.array.tools_name)
        mList.addAll(stringArray)
    }
}
