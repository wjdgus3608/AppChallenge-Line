package com.example.line.Utils

import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.line.DataClass.Memo

@BindingAdapter("bind_items")
fun bind_items(view : RecyclerView, mList : MutableLiveData<ArrayList<Memo>>){
    val adapter = view.adapter as? MemoAdapter
        ?: MemoAdapter().apply { view.adapter = this }
    adapter.mList = mList
    adapter.notifyDataSetChanged()
}