package com.example.line.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.line.DataClass.Memo

class MainViewModel : ViewModel() {
    var mList=MutableLiveData<ArrayList<Memo>>()
    var fragmentMode=MutableLiveData<Int>()
    var memoTitle=MutableLiveData<String>()
    var memoDes=MutableLiveData<String>()

    init {
        fragmentMode.value=1
        mList.value=ArrayList()
        addItem(Memo("sample1","des sample1",ArrayList()))
        addItem(Memo("sample1","des sample1",ArrayList()))
    }
    fun addItem(item:Memo) = mList.value!!.add(item)
    fun addBtnClick(){
        fragmentMode.value=2
    }
    fun submitBtnClick(){
        if(memoTitle.value.isNullOrBlank() || memoDes.value.isNullOrBlank()){

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
