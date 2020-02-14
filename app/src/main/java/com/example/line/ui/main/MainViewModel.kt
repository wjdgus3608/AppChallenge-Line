package com.example.line.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.line.DataClass.Memo

class MainViewModel : ViewModel() {
    val text=MutableLiveData<String>()
    var mList=MutableLiveData<ArrayList<Memo>>()
    init {
        text.value="abcd"
        mList.value=ArrayList()
        addItem(Memo("sample1","des sample1",ArrayList()))
        addItem(Memo("sample1","des sample1",ArrayList()))
    }
    fun addItem(item:Memo) = mList.value!!.add(item)
    // TODO: Implement the ViewModel
}
