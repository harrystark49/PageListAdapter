package com.example.pagelistadapter

import Results
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.recyclerview_row.view.*
import org.w3c.dom.CharacterData

class RecyclerViewAdapter:PagingDataAdapter<Results,RecyclerViewAdapter.myViewHolder>(DiffUtilCallBack()){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewAdapter.myViewHolder {
        return myViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_row,parent,false))
    }


    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    class myViewHolder(view:View):RecyclerView.ViewHolder(view){
        fun bind(item: Results?) {
            itemView.tvName.text=item?.name
            itemView.tvDes.text=item?.species
            Glide.with(itemView.img)
                .load(item?.image)
                .circleCrop()
                .into(itemView.img)

        }
    }

    class DiffUtilCallBack:DiffUtil.ItemCallback<Results>(){
        override fun areItemsTheSame(oldItem: Results, newItem: Results): Boolean {
            return oldItem.name==newItem.name
        }

        override fun areContentsTheSame(oldItem: Results, newItem: Results): Boolean {
           return (oldItem.name==newItem.name && oldItem.species==newItem.species)
        }

    }
}