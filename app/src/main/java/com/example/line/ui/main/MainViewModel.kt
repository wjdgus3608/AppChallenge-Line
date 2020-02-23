package com.example.line.ui.main

import android.webkit.URLUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.line.DataClass.Memo
import com.example.line.Utils.MemoRepo

class MainViewModel(repo:MemoRepo) : ViewModel() {
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
    var memoRepo:MemoRepo
    init {
        fragmentMode.value=1
        inputBtnMode.value=-1
        selectedMemo.value=null
        initImgList()
        clearDigData()
        memoRepo=repo
    }
    fun addItem(item:Memo) = memoRepo.insert(item)
    fun deleteItem(item:Memo) = memoRepo.delete(item.id)

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
            if(selectedMemo.value!=null) {
                deleteItem(selectedMemo.value!!)
                selectedMemo.postValue(null)
            }
            val tmpList=ArrayList<Any>()
            tmpList.addAll(images)
            addItem(Memo(0,memoTitle.value!!, memoDes.value!!, tmpList as List<Any>))
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

        fragmentMode.postValue(1)
        selectedMemo.postValue(null)
    }
    fun detailDeleteBtnClick(){
        val tmpList=mList.value
        tmpList!!.remove(selectedMemo.value)
        deleteItem(selectedMemo.value!!)
        mList.postValue(tmpList)
        fragmentMode.postValue(1)
        selectedMemo.postValue(null)

    }
    fun detailEditBtnClick(){
        loadSelected()
        fragmentMode.postValue(2)
    }
    fun loadSelected(){
        memoTitle.postValue(selectedMemo.value!!.title)
        memoDes.postValue(selectedMemo.value!!.des)
        images.addAll(selectedMemo.value!!.photoList)
        imageList.postValue(images)
    }
}
