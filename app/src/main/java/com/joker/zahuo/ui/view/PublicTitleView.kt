package com.joker.zahuo.ui.view

import android.content.Context
import android.text.TextUtils
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
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
    var view: View? = null
    var title: TextView? = null
    var left_icon: ImageView? = null
    var left_text: TextView? = null
    var right_icon: ImageView? = null
    var right_text: TextView? = null
    var title_layout:LinearLayout? = null
    var line:View? = null

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
        view = LayoutInflater.from(context).inflate(R.layout.layout_public_title, this, true)
        //标题
        view?.title?.visibility = titleVisibility
        if (!TextUtils.isEmpty(titleText)) {
            view?.title?.text = titleText
        }
        //左按钮
        left_icon = view?.left_icon
        left_icon?.visibility = leftIconVisibility
        if (leftIcon > 0) {
            left_icon?.setImageResource(leftIcon)
        }
        //左文字
        left_text = view?.left_txt
        left_text?.visibility = leftTextVisibility
        if (!TextUtils.isEmpty(leftText)) {
            left_txt?.text = leftText
        } else {
            left_txt?.text = resources.getString(R.string.back)
        }
        //右按钮
        right_icon = view?.right_icon
        right_icon?.visibility = rightIconVisibility
        if (rightIcon > 0) {
            right_icon?.setImageResource(rightIcon)
        }
        //右文字
        right_text = view?.right_txt
        right_text?.visibility = rightTextVisibility
        if (!TextUtils.isEmpty(rightText)) {
            right_text?.text = rightText
        }
        //线颜色
        line = view?.title_line

        if (lineColor > 0) {
            line?.setBackgroundColor(resources.getColor(lineColor))
        }
        //背景色
        title_layout = view?.layout_title
       title_layout?.setBackgroundColor(resources.getColor(backgroudColor))
    }

    fun setLineColor(int: Int){

    }

    public fun leftTextOnClick(onClickListener: OnClickListener) {
        left_text?.setOnClickListener(onClickListener)
    }

    public fun leftIconOnClick(onClickListener: OnClickListener) {
        left_icon?.setOnClickListener(onClickListener)
    }

    public fun rightTextOnClick(onClickListener: OnClickListener) {
        right_text?.setOnClickListener(onClickListener)
    }

    public fun rightIconOnClick(onClickListener: OnClickListener) {
        right_icon?.setOnClickListener(onClickListener)
    }


}
