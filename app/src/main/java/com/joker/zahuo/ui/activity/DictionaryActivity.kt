package com.joker.zahuo.ui.activity

import android.view.View
import com.joker.zahuo.R
import com.joker.zahuo.base.BaseActivity
import kotlinx.android.synthetic.main.activity_dictionary.*

class DictionaryActivity : BaseActivity() {
    override fun initAction() {
        title_view.rightIconOnClick(onClickListener = View.OnClickListener {
            title_view.showSearchBar()
        })

    }

    override fun initData() {


    }

    override fun initView() {


    }

    override fun getLayout(): Int {
        return R.layout.activity_dictionary
    }

}
