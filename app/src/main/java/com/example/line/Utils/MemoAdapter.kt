package com.example.line.Utils

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.line.DataClass.Memo
import com.example.line.R
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;
import kotlinx.android.synthetic.main.memo_item.view.*


class MemoAdapter :RecyclerView.Adapter<MemoAdapter.MemoViewHolder>() {
     var mList=MutableLiveData<ArrayList<Memo>>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemoViewHolder = MemoViewHolder(parent)

    override fun getItemCount(): Int = mList.value!!.size

    override fun onBindViewHolder(holder: MemoViewHolder, position: Int) {
        mList.value!![position].let { item-> with(holder){
            title.text=item.title
            des.text=item.des
            Glide.with(holder.itemView).load("https://live.staticflickr.com/4561/38054606355_26429c884f_b.jpg").into(image)
        }}
    }

    inner class MemoViewHolder(parent:ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.memo_item, parent, false)){
        var title:TextView = itemView.sum_title
        var des:TextView = itemView.sum_des
        var image:ImageView = itemView.sum_img
    }
}