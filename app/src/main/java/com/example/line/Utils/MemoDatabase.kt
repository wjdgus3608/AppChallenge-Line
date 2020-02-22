package com.example.line.Utils

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.line.DataClass.Memo

@Database(entities = [Memo::class], version = 1)
@TypeConverters(Converters::class)
abstract class MemoDatabase: RoomDatabase() {
    abstract fun memoDao(): MemoDao

    companion object {
        private var INSTANCE: MemoDatabase? = null

        fun getInstance(context: Context): MemoDatabase? {
            return INSTANCE ?: synchronized(MemoDatabase::class) {
                INSTANCE ?: Room.databaseBuilder(context.applicationContext,
                    MemoDatabase::class.java, "memo.db").build().also { INSTANCE = it }
            }
        }

        fun destoryInstance() {
            INSTANCE = null
        }
    }
}
