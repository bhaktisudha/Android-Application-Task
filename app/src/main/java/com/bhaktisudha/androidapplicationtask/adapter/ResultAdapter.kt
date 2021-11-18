package com.bhaktisudha.androidapplicationtask.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bhaktisudha.androidapplicationtask.R
import com.bhaktisudha.androidapplicationtask.resultModel.ResultModelItem
import java.util.ArrayList

class ResultAdapter(val context: Context) : RecyclerView.Adapter<ResultAdapter.ResultViewHolder>() {
    var items = ArrayList<ResultModelItem>()
    inner class ResultViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val itemV = itemView.findViewById<TextView>(R.id.item_tv)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.result_item_tv,parent,false)
        val viewHolder = ResultViewHolder(view)
        viewHolder.itemV.setOnClickListener{
           // listener.itemClicked(items[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
        val currentItem = items[position]
        holder.itemV.text = currentItem.firstName
    }

    override fun getItemCount(): Int {
       return items.size
    }

    fun updateList(resultList:List<ResultModelItem>){
        items.clear()
        items.addAll(resultList)
        items.size
        notifyDataSetChanged()
    }
}


