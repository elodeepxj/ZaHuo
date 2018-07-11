package com.joker.zahuo.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.joker.zahuo.R

/**
 * Created by tv on 2018/7/6.
 */
class ToolsAdapter : RecyclerView.Adapter<ToolsAdapter.ToolsViewHolder>, View.OnClickListener {



    private var context:Context? = null
    private var mList:List<String>? = null
    private var onItemClickListener:OnItemClickListener? = null

    constructor(context: Context,mList:List<String>){
        this.context = context
        this.mList = mList
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener){
        this.onItemClickListener = onItemClickListener
    }

    override fun onClick(v: View?) {
        onItemClickListener?.onClick(v,v?.getTag() as Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ToolsViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_tools_adapter,null)
        view.setOnClickListener(this)
        return ToolsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mList?.size as Int
    }

    override fun onBindViewHolder(holder: ToolsViewHolder?, position: Int) {
        if(mList?.size as Int > position){
            holder?.name?.text = mList?.get(position)
            when (position){
                0->holder?.icon?.setImageResource(R.mipmap.icon_dictionary)
                1->holder?.icon?.setImageResource(R.mipmap.icon_exchange)
            }
            holder?.itemView?.setTag(position)


        }
    }



    class ToolsViewHolder:RecyclerView.ViewHolder {
        var name:TextView
        var icon:ImageView

        constructor(view :View):super(view){
            name = view.findViewById(R.id.name)
            icon = view.findViewById(R.id.icon)
        }

    }

    interface OnItemClickListener{
        fun onClick(v: View?, i: Int)
    }
}
