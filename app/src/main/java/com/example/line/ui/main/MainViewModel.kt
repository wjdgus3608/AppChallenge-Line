package com.example.line.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.line.DataClass.Memo

class MainViewModel : ViewModel() {
    var mList=MutableLiveData<ArrayList<Memo>>()
    init {
        mList.value=ArrayList()
        addItem(Memo("sample1","des sample1",ArrayList()))
        addItem(Memo("sample1","des sample1",ArrayList()))
    }
    fun addItem(item:Memo) = mList.value!!.add(item)
    // TODO: Implement the ViewModel
}
