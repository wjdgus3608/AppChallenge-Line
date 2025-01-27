package com.example.line.Utils

import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.example.line.R
import kotlinx.android.synthetic.main.viewpager_item.view.*

class ImagePagerAdapter : PagerAdapter(){
    var inputList=ArrayList<Any>()
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(container.context)
        val view = inflater.inflate(R.layout.viewpager_item, container, false)

        view.clipToOutline=true
        inputList[position].let {
            if(it is String){
                Glide.with(view).load(it).centerCrop().into(view.image_view)
            }
            else if(it is Uri){
                view.image_view.setImageURI(it)
            }
            else{
                Log.e("log",it.toString())
            }
        }
        container.addView(view)
        return view
    }

    override fun getItemPosition(`object`: Any): Int {
        return POSITION_NONE
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) = container.removeView(`object` as View?)
    override fun isViewFromObject(view: View, `object`: Any): Boolean = (view==`object`)
    override fun getCount(): Int = inputList.size
}