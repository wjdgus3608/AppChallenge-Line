package com.example.line.DataClass

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MemoTable")
data class Memo(
    @PrimaryKey(autoGenerate = true)
    var id:Long,
    var title:String,
    var des:String,
    var photoList:List<Any>
)