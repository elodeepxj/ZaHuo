package com.joker.zahuo.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.joker.zahuo.R
import com.joker.zahuo.ui.activity.MainActivity

/**
 * Created by PengXJ on 2018/8/28.
 */
class HomeAdapter(context: Context, mList: ArrayList<String>) : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {
    private var context:Context? = context
    private var mList:List<String>? = mList


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): HomeViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_home_adapter, null)
        return HomeViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mList?.size as Int
    }

    override fun onBindViewHolder(holder: HomeViewHolder?, position: Int) {
        holder?.tv?.text = mList?.get(position)
    }


    class HomeViewHolder : RecyclerView.ViewHolder {
        var tv: TextView;

        constructor(view: View) : super(view) {
            tv = view.findViewById(R.id.tv_item_home)
        }
    }


}