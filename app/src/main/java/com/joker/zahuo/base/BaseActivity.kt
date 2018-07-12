package com.joker.zahuo.base

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import butterknife.ButterKnife
import com.joker.zahuo.R

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
        initView()
        initData()
        initAction()
    }

    abstract fun initAction()

    abstract fun initData()

    abstract fun initView()

    abstract fun  getLayout():Int
}
