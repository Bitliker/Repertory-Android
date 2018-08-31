package com.bitliker.simple.common

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bitliker.simple.R
import com.bitliker.simple.common.model.CommonModel

class CommonSelectAdapter(ct: Context, models: List<CommonModel>?) : RecyclerView.Adapter<CommonSelectAdapter.ViewHolder>() {
    var ct: Context? = null
    var models: List<CommonModel>? = null

    init {
        this.ct = ct
        this.models = models
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(ct).inflate(R.layout.item_common_list, parent, false))
    }

    override fun getItemCount(): Int {
        return models?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name?.text = models!![position].name
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView? = null

        init {
            name = itemView.findViewById(R.id.name)
        }
    }
}