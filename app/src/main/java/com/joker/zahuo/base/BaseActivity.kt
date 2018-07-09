package com.joker.zahuo.base

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import butterknife.ButterKnife
import com.joker.zahuo.R

open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        ButterKnife.bind(this)
    }
}
