package com.example.line.Utils

import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.line.DataClass.Memo
import com.example.line.R
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.util.Util
import com.example.line.databinding.PreviewItemBinding
import com.example.line.ui.main.MainViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.memo_item.view.*
import kotlinx.android.synthetic.main.preview_item.view.*


class PreviewAdapter(parentViewModel: MainViewModel) :RecyclerView.Adapter<PreviewAdapter.PreViewHolder>() {
     var mList=MutableLiveData<ArrayList<Any>>()
    val viewModel by lazy { parentViewModel }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PreViewHolder {
        var inflater=LayoutInflater.from(parent.context)
        var binding=
            DataBindingUtil.inflate<PreviewItemBinding>(inflater,R.layout.preview_item,parent,false)
        binding.setVariable(BR.vm,viewModel)
        return PreViewHolder(binding)
    }

    override fun getItemCount(): Int = mList.value!!.size

    override fun onBindViewHolder(holder: PreViewHolder, position: Int) {
        mList.value!![position].let { item-> with(holder){
            if(item is String) {
                Glide.with(holder.itemView)
                    .load(item)
                    .centerCrop().listener(
                    object : RequestListener<Drawable> {
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>?,
                            isFirstResource: Boolean
                        ): Boolean {
                            mList.value!!.removeAt(position)
                            notifyItemRemoved(position)
                            viewModel.toastMsg.postValue("이미지를 불러올수 없습니다.")
                            return false
                        }

                        override fun onResourceReady(
                            resource: Drawable?,
                            model: Any?,
                            target: Target<Drawable>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            return false
                        }
                    }
                ).into(imageView)
            }
            else if(item is Uri){
                holder.imageView.setImageURI(item)
            }
            else{}
            Log.e("log","${mList.value!!.size}")
        }}
        holder.deleteBtn.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                var images=mList.value
                images!!.removeAt(position)
                mList.postValue(images)
                notifyItemRemoved(position)
            }
        })
    }

    inner class PreViewHolder(binding: PreviewItemBinding) : RecyclerView.ViewHolder(binding.root){
        var imageView:ImageView = itemView.pre_image_view
        var deleteBtn:FloatingActionButton = itemView.image_delete_btn
    }
}