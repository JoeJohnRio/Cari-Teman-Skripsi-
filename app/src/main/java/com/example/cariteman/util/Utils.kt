package com.example.cariteman.util

import android.content.Context
import android.content.res.Resources
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import java.text.SimpleDateFormat

object Utils {
    lateinit var fakultas: Array<String>

    val SHARED_PREFS = "sharedPrefs"
    val TEXT = "text"


    val parserDate = SimpleDateFormat("yyyy-MM-dd")
    val formatterDate = SimpleDateFormat("dd MMMM, yyyy")

    fun toggleThreeButton(turnUp: MaterialButton, turnDown: MaterialButton, turnDown1: MaterialButton, colorOn: Int, colorOff: Int, resources: Resources){
        turnUp.setBackgroundColor(resources.getColor(colorOn))
        turnUp.setTextColor(resources.getColor(colorOff))
        turnDown.setBackgroundColor(resources.getColor(colorOff))
        turnDown.setTextColor(resources.getColor(colorOn))
        turnDown1.setBackgroundColor(resources.getColor(colorOff))
        turnDown1.setTextColor(resources.getColor(colorOn))
    }

    fun toggleVisibility(viewWantToVisible: View, viewWantToGone: View){
        viewWantToGone.visibility = View.GONE
        viewWantToVisible.visibility = View.VISIBLE
    }

    fun toggleBoolean(intBoolean: Int): Int{
        if (intBoolean==1){
            return 0
        }else{
            return 1
        }
    }

    fun intToBoolean(intNumber: Int?): Boolean{
        if (intNumber==1){
            return true
        }else{
            return false
        }
    }

    fun booleanToInt(booleanVar: Boolean): Int{
        if (booleanVar){
            return 1
        }else{
            return 0
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