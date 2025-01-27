package com.example.line.Utils

import androidx.room.TypeConverter

class Converters {

    @TypeConverter
    fun listToString(value: List<Any>?): String {
        var str=""
        value!!.map { str=str+it+","}
        return str
    }

    @TypeConverter
    fun stringToList(value: String): List<Any>? {
        val list= value.split(",").toList()
        return list.subList(0,list.size-1)
    }
}