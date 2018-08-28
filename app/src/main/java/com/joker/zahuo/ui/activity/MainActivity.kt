package com.joker.zahuo.ui.activity

import android.content.Intent
import android.support.design.widget.NavigationView
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import com.joker.zahuo.R
import com.joker.zahuo.base.BaseActivity
import com.joker.zahuo.ui.adapter.HomeAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override fun initView() {

    }
    override fun initAction() {
        initNavigationMenuAction()
    }

    override fun initData() {
        var mList:ArrayList<String> = ArrayList<String>()

        var homeAdapter = HomeAdapter(this@MainActivity,mList)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = homeAdapter
    }

    override fun getLayout(): Int {
        return R.layout.activity_main
    }

    private fun initNavigationMenuAction() {
        navigation.setNavigationItemSelectedListener(object : NavigationView.OnNavigationItemSelectedListener{
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                when(item.itemId){
                    //小工具首页
                    R.id.small_tools->startActivity(Intent(this@MainActivity,ToolsHomeActivity::class.java))
                }
                return false
            }
        })

    }



}
