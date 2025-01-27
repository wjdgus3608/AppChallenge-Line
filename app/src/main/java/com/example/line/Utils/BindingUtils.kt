package com.example.line.Utils

import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.example.line.DataClass.Memo
import com.example.line.ui.main.MainViewModel

@BindingAdapter("bind_items","bind_vm")
fun bind_items(view : RecyclerView, mList : MutableLiveData<ArrayList<Memo>>,viewModel: MainViewModel){
    val adapter = view.adapter as? MemoAdapter
        ?: MemoAdapter(viewModel).apply { view.adapter = this }
    adapter.mList = mList
    adapter.notifyDataSetChanged()
}

@BindingAdapter("bind_pre_items","bind_vm")
fun bind_pre_items(view : RecyclerView, mList : MutableLiveData<ArrayList<Any>>,viewModel: MainViewModel){
    val adapter = view.adapter as? PreviewAdapter
        ?: PreviewAdapter(viewModel).apply { view.adapter = this }
    adapter.mList = mList
    adapter.notifyDataSetChanged()
}

@BindingAdapter("bind_image_items")
fun bind_image_items(view : ViewPager, mList : List<Any>){
    val adapter = view.adapter as? ImagePagerAdapter
        ?: ImagePagerAdapter().apply { view.adapter = this }
    adapter.inputList = ArrayList(mList)
    adapter.notifyDataSetChanged()
}
