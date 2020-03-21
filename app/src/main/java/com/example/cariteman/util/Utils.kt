package com.example.cariteman.util

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

object Utils {
    lateinit var fakultas: Array<String>

    val SHARED_PREFS = "sharedPrefs"
    val TEXT = "text"

    fun intToBoolean(integer : Int): Boolean {
        if (integer == 0){
            return false
        }else if (integer == 1){
            return true
        }else{
            return false
        }
    }

    fun loadData(context: Context) : String?{
        val sharedPreferences = context.getSharedPreferences(SHARED_PREFS,
            AppCompatActivity.MODE_PRIVATE
        )
        return sharedPreferences.getString(TEXT, "")
//        Toast.makeText(context, sharedPreferences.getString(TEXT, ""), Toast.LENGTH_LONG ).show()
    }



}