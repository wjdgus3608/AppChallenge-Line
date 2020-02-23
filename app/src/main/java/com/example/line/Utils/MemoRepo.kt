package com.example.line.Utils

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.line.DataClass.Memo

class MemoRepo (application: Application) {

    private val memoDao: MemoDao by lazy {
        val db = MemoDatabase.getInstance(application)!!
        db.memoDao()
    }
    private val memos: LiveData<List<Memo>> by lazy {
        memoDao.getAllMemo()
    }

    fun getAllMemo(): LiveData<List<Memo>> {
        return memos
    }

    fun insert(memo: Memo){
        Thread(Runnable { memoDao.insert(memo) }).start()
    }
    fun delete(id: Long){
        Thread(Runnable { memoDao.deleteById(id) }).start()
    }
}

