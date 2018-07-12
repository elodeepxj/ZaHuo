package com.joker.zahuo.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.view.MenuItem
import com.joker.zahuo.R
import com.joker.zahuo.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override fun initView() {

    }
    override fun initAction() {
        initNavigationMenuAction()
    }

    override fun initData() {

    }

    override fun getLayout(): Int {
        return R.layout.activity_main
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
