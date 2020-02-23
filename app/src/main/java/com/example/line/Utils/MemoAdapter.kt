package com.example.line.Utils

import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.line.DataClass.Memo
import com.example.line.R
import com.example.line.databinding.MemoItemBinding
import com.example.line.ui.main.MainViewModel
import kotlinx.android.synthetic.main.memo_item.view.*


class MemoAdapter(parentViewModel: MainViewModel) :RecyclerView.Adapter<MemoAdapter.MemoViewHolder>() {
    var mList=MutableLiveData<ArrayList<Memo>>()
    val viewModel by lazy { parentViewModel }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemoViewHolder {
            var inflater=LayoutInflater.from(parent.context)
            var binding=
                DataBindingUtil.inflate<MemoItemBinding>(inflater,R.layout.memo_item,parent,false)
            binding.setVariable(BR.vm,viewModel)
            return MemoViewHolder(binding)
        }


    override fun getItemCount(): Int {
        if (mList.value==null) return 0
        else return mList.value!!.size
    }

    override fun onBindViewHolder(holder: MemoViewHolder, position: Int) {

        mList.value!![position].let { item-> with(holder){
            title.text=item.title
            des.text=item.des
            image.clipToOutline=true
            if(item.photoList.size!=0){
                if(item.photoList[0] is String)
                Glide.with(holder.itemView).load(item.photoList[0]).centerCrop().into(image)
                else if(item.photoList[0] is Uri)
                    image.setImageURI(item.photoList[0] as Uri)
                else{
                    Log.e("log","${item.photoList[0]}")
                }
            }
            holder.itemView.setOnClickListener{
                viewModel.selectedMemo.postValue(item)
                viewModel.fragmentMode.postValue(3)
            }
        }}


    }

    inner class MemoViewHolder(binding:MemoItemBinding) : RecyclerView.ViewHolder(binding.root){
        var title:TextView = itemView.sum_title
        var des:TextView = itemView.sum_des
        var image:ImageView = itemView.sum_img
    }
}