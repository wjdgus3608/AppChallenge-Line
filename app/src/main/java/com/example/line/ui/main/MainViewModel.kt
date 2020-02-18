package com.example.line.ui.main

import android.content.Intent
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.line.DataClass.Memo

class MainViewModel : ViewModel() {
    var mList=MutableLiveData<ArrayList<Memo>>()
    var fragmentMode=MutableLiveData<Int>()
    var memoTitle=MutableLiveData<String>()
    var memoDes=MutableLiveData<String>()
    var toastMsg= MutableLiveData<String>()
    var imageInputMode=MutableLiveData<Int>()
    init {
        fragmentMode.value=1
        imageInputMode.value=0
        mList.value=ArrayList()
        addItem(Memo("sample1","des sample1",ArrayList()))
        addItem(Memo("sample1","des sample1",ArrayList()))
    }
    fun addItem(item:Memo) = mList.value!!.add(item)
    fun addBtnClick(){
        fragmentMode.value=2
    }
    fun galleryImgAdd(){
        imageInputMode.value=2
    }
    fun cameraImgAdd(){
        imageInputMode.value=1
    }
    fun urlImgAdd(){
        imageInputMode.value=3
    }
    fun submitBtnClick(){
        if(memoTitle.value.isNullOrBlank() || memoDes.value.isNullOrBlank()){
            toastMsg.postValue("입력되지 않은 칸이 있습니다!")
        }
        else {
            addItem(Memo(memoTitle.value!!, memoDes.value!!, ArrayList()))
            fragmentMode.value = 1
        }
    }
    fun backBtnClick(){
        fragmentMode.value=1
    }
}
