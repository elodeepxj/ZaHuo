package com.joker.zahuo.ui.activity

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.joker.mysdk.utils.LogUtil
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
        mList.addAll(resources.getStringArray(R.array.tools_name))
        LogUtil.d(mList.size)
        rv.layoutManager = GridLayoutManager(this,4)
        rv.adapter = ToolsAdapter(this@ToolsHomeActivity,mList)

    }

}
