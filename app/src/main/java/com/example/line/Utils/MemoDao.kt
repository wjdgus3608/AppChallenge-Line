package com.example.line.Utils

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.line.DataClass.Memo

@Dao
interface MemoDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(memo: Memo)

    @Query("DELETE FROM MemoTable WHERE id = :id")
    fun deleteById(id: Long)

    @Query("SELECT * FROM MemoTable ORDER BY id")
    fun getAllMemo(): LiveData<List<Memo>>

}
