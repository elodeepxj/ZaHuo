package com.joker.zahuo.ui.view

import android.content.Context
import android.text.TextUtils
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.joker.mysdk.utils.LogUtil
import com.joker.zahuo.R
import kotlinx.android.synthetic.main.layout_public_title.view.*

/**
 * Created by tv on 2018/7/9.
 */
class PublicTitleView : RelativeLayout {
    //0 visible ,4 invisible ,8 gone
    var leftText: String? = null
    var rightText: String? = null
    var leftIcon: Int = R.mipmap.icon_back;
    var rightIcon: Int = 0
    var titleText: String? = null
    var backgroudColor: Int = R.color.white
    var lineColor: Int = R.color.white
    var leftTextVisibility: Int = 0
    var rightTextVisibility: Int = 0
    var leftIconVisibility: Int = 0
    var rightIconVisibility: Int = 0
    var titleVisibility: Int = 0
    var lineVisibility: Int = 0


    constructor(context: Context) : super(context) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initAttrs(attrs)
        initView()
    }

    private fun initAttrs(attrs: AttributeSet) {
        var ta = context.obtainStyledAttributes(attrs, R.styleable.PublicTitleView)
        leftText = ta.getString(R.styleable.PublicTitleView_left_text)
        rightText = ta.getString(R.styleable.PublicTitleView_right_text)
        leftIcon = ta.getResourceId(R.styleable.PublicTitleView_left_icon, R.mipmap.icon_back)
        rightIcon = ta.getResourceId(R.styleable.PublicTitleView_right_icon, 0)
        titleText = ta.getString(R.styleable.PublicTitleView_title_text)
        backgroudColor = ta.getResourceId(R.styleable.PublicTitleView_backgroudColor, R.color.white)
        lineColor = ta.getResourceId(R.styleable.PublicTitleView_lineColor, R.color.white)
        leftTextVisibility = ta.getResourceId(R.styleable.PublicTitleView_leftTextVisibility, 0)
        rightTextVisibility = ta.getResourceId(R.styleable.PublicTitleView_rightTextVisibility, 2)
        leftIconVisibility = ta.getResourceId(R.styleable.PublicTitleView_leftIconVisibility, 0)
        rightIconVisibility = ta.getResourceId(R.styleable.PublicTitleView_rightIconVisibility, 2)
        titleVisibility = ta.getResourceId(R.styleable.PublicTitleView_titleVisibility, 0)
        lineVisibility = ta.getResourceId(R.styleable.PublicTitleView_lineVisibility, 0)
        ta.recycle();

    }

    private fun initView() {
        var view = LayoutInflater.from(context).inflate(R.layout.layout_public_title, this, true)
        LogUtil.d("----------" + titleText)
        view?.title?.visibility = titleVisibility
        if (!TextUtils.isEmpty(titleText)) {
            view.title.text = titleText
        }

        view.left_icon.visibility = leftIconVisibility
        if (leftIcon > 0) {
            view.left_icon.setImageResource(leftIcon)
        }

        view.left_txt.visibility = leftTextVisibility
        if (!TextUtils.isEmpty(leftText)) {
            view.left_txt.text = leftText
        } else {
            view.left_txt.text = resources.getString(R.string.back)
        }

        view.right_icon.visibility = rightIconVisibility
        if (rightIcon > 0) {
            view.right_icon.setImageResource(rightIcon)
        }

        view.right_txt.visibility = rightTextVisibility
        if (!TextUtils.isEmpty(rightText)) {
            view.right_txt.text = rightText
        }

        if (lineColor > 0) {
            view.title_line.setBackgroundColor(resources.getColor(lineColor))
        }

        view.layout_title.setBackgroundColor(resources.getColor(backgroudColor))

        if (rightIcon > 0) {
            right_icon.setImageResource(rightIcon)
        }

        if (leftIcon > 0) {
            left_icon.setImageResource(leftIcon)
        }


    }


}
