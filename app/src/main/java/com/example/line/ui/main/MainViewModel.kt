package com.example.line.ui.main

import android.content.Intent
import android.util.Log
import android.webkit.URLUtil
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.example.line.DataClass.Memo

class MainViewModel : ViewModel() {
    var mList=MutableLiveData<ArrayList<Memo>>()
    var fragmentMode=MutableLiveData<Int>()
    var memoTitle=MutableLiveData<String>()
    var memoDes=MutableLiveData<String>()
    var toastMsg= MutableLiveData<String>()
    var inputBtnMode=MutableLiveData<Int>()
    var imageUrl=MutableLiveData<String>()
    var imageList=MutableLiveData<ArrayList<Any>>()
    var selectedMemo=MutableLiveData<Memo?>()
    lateinit var images:ArrayList<Any>
    init {
        fragmentMode.value=1
        inputBtnMode.value=-1
        selectedMemo.value=null
        initImgList()
        clearDigData()
        mList.value=ArrayList()
        imageList.value= ArrayList()
        addItem(Memo("sample1","des sample1",ArrayList()))
        addItem(Memo("sample1","des sample1",ArrayList()))
    }
    fun addItem(item:Memo) = mList.value!!.add(item)
    fun addBtnClick(){
        fragmentMode.postValue(2)
    }
    fun handleBtnMode(mode:Int){
        inputBtnMode.postValue(mode)
    }
    fun submitBtnClick(){
        if(memoTitle.value.isNullOrBlank() || memoDes.value.isNullOrBlank()){
            toastMsg.postValue("입력되지 않은 칸이 있습니다!")
        }
        else {
            val tmpList=ArrayList<Any>()
            tmpList.addAll(images)
            addItem(Memo(memoTitle.value!!, memoDes.value!!, tmpList))
            initImgList()
            fragmentMode.postValue(1)
        }
        handleBtnMode(-1)
    }
    fun imageAdd(image:Any){
        images.add(image)
        imageList.postValue(images)
    }
    fun urlSubmitBtnClick(){
        if(!imageUrl.value.isNullOrBlank() && URLUtil.isValidUrl(imageUrl.value))
            imageAdd(imageUrl.value as Any)
        else toastMsg.postValue("올바르지 않은 URL 입니다.")
        handleBtnMode(-1)
    }
    fun urlCancelBtnClick() {
        handleBtnMode(-1)
    }
    fun backBtnClick(){
        initImgList()
        handleBtnMode(-1)
        fragmentMode.postValue(1)
    }
    fun clearDigData(){
        imageUrl.value=""
    }
    fun initImgList(){
        images= ArrayList()
        imageList.postValue(images)
    }
    fun detailBackBtnClick(){
        selectedMemo.postValue(null)
        fragmentMode.postValue(1)
    }
    fun detailDeleteBtnClick(){
        val tmpList=mList.value
        tmpList!!.remove(selectedMemo.value)
        mList.postValue(tmpList)
        selectedMemo.postValue(null)
        fragmentMode.postValue(1)
    }
}
