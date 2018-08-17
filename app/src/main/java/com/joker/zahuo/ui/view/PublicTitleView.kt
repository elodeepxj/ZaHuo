package com.joker.zahuo.ui.view

import android.content.Context
import android.text.TextUtils
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import com.joker.mysdk.utils.LogUtil
import com.joker.zahuo.R
import kotlinx.android.synthetic.main.layout_public_title.view.*

/**
 * Created by tv on 2018/7/9.
 */
class PublicTitleView : RelativeLayout {

    //0 visible ,1 invisible ,2 gone
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
    var tv_title: TextView? = null
    var iv_left: ImageView? = null
    var tv_left: TextView? = null
    var iv_right: ImageView? = null
    var tv_right: TextView? = null
    var title_layout: LinearLayout? = null
    var line: View? = null
    var rightIconType :Int = 0
    var et_search: EditText? = null

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
        rightIconType = ta.getResourceId(R.styleable.PublicTitleView_rightIconType, 99)
        ta.recycle();

    }

    private fun initView() {
        view = LayoutInflater.from(context).inflate(R.layout.layout_public_title, this, true)
        //标题
        tv_title = view?.tv_title
        tv_title?.visibility = titleVisibility
        if (!TextUtils.isEmpty(titleText)) {
            tv_title?.text = titleText
        }
        //左按钮
        iv_left = view?.left_icon
        iv_left?.visibility = leftIconVisibility
        if (leftIcon > 0) {
            iv_left?.setImageResource(leftIcon)
        }
        //左文字
        tv_left = view?.left_txt
        tv_left?.visibility = leftTextVisibility
        if (!TextUtils.isEmpty(leftText)) {
            left_txt?.text = leftText
        } else {
            left_txt?.text = resources.getString(R.string.back)
        }
        //右按钮
        iv_right = view?.right_icon
        iv_right?.visibility = rightIconVisibility
        if (rightIcon > 0) {
            iv_right?.setImageResource(rightIcon)
        }

//        if (rightIconType != 99) {
//            iv_right?.visibility = View.VISIBLE
//        }
        LogUtil.e("--"+rightIconType)
        when (rightIconType) {
        //0完成
            0 -> iv_right?.setImageResource(R.mipmap.right_icon_done)
        //1编辑
            1 -> iv_right?.setImageResource(R.mipmap.right_icon_edit)
        //2查询
            2 -> iv_right?.setImageResource(R.mipmap.right_icon_search)
        }

        //右文字
        tv_right = view?.right_txt
        tv_right?.visibility = rightTextVisibility
        if (!TextUtils.isEmpty(rightText)) {
            tv_right?.text = rightText
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


    fun leftTextOnClick(onClickListener: OnClickListener) {
        tv_left?.setOnClickListener(onClickListener)
    }

    fun leftIconOnClick(onClickListener: OnClickListener) {
        iv_left?.setOnClickListener(onClickListener)
    }

    fun rightTextOnClick(onClickListener: OnClickListener) {
        tv_right?.setOnClickListener(onClickListener)
    }

    fun rightIconOnClick(onClickListener: OnClickListener) {
        when (rightIconType) {
            2 -> showSearchBar()
        }
        iv_right?.setOnClickListener(onClickListener)
    }

    /**显示搜索框*/
    fun showSearchBar() {
        et_search?.visibility = View.VISIBLE
        tv_title?.visibility = View.GONE
    }


}
