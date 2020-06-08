package com.example.cariteman.util

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.net.Uri
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.webkit.MimeTypeMap
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cariteman.ui.base.view.BaseActivity
import com.example.cariteman.ui.base.view.BaseFragment
import com.example.cariteman.util.Utils.mStorageRef
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.android.material.button.MaterialButton
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import java.text.SimpleDateFormat
import java.util.regex.Pattern

object Utils {
    lateinit var fakultas: Array<String>

    val SHARED_PREFS = "sharedPrefs"
    val TEXT = "text"


    val parserDate = SimpleDateFormat("yyyy-MM-dd")
    val formatterDate = SimpleDateFormat("dd MMMM, yyyy")
    var mStorageRef = FirebaseStorage.getInstance().getReference("uploads")

    fun hideKeyboard(context: Context, view: View) {
        val inputMethodManager = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun toggleThreeButton(
        turnUp: MaterialButton,
        turnDown: MaterialButton,
        turnDown1: MaterialButton,
        colorOn: Int,
        colorOff: Int,
        resources: Resources
    ) {
        turnUp.setBackgroundColor(resources.getColor(colorOn))
        turnUp.setTextColor(resources.getColor(colorOff))
        turnDown.setBackgroundColor(resources.getColor(colorOff))
        turnDown.setTextColor(resources.getColor(colorOn))
        turnDown1.setBackgroundColor(resources.getColor(colorOff))
        turnDown1.setTextColor(resources.getColor(colorOn))
    }

    fun isEmailValid(email: String): Boolean {
        val expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
        val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
        val matcher = pattern.matcher(email)
        return matcher.matches()
    }

    fun isNimValid(email: String): Boolean {
        if (email.length == 15){
            return true
        }else{
            return false
        }
    }

    fun uploadFileFromActivity(foto: Uri, context: Context, view: BaseActivity): String {
        val fileReference: StorageReference =
            mStorageRef.child(
                "${System.currentTimeMillis()}.${Utils.getFileExtension(
                    foto,
                    context
                )}"
            )

        val putFile = fileReference.putFile(foto)
        var url = ""
        var urlTask: Task<Uri> =
            putFile.continueWithTask(object : Continuation<UploadTask.TaskSnapshot, Task<Uri>> {
                override fun then(p0: Task<UploadTask.TaskSnapshot>): Task<Uri> {
                    if (!p0.isSuccessful) {
                        view.showMessageToast("error 1")
                    }
                    return fileReference.downloadUrl
                }
            }).addOnCompleteListener {
                if (it.isSuccessful) {
                    val uri = it.result
                    val string = uri.toString()
                    url = string
                } else {
                    view.showMessageToast("error")
                }
            }
        return url
    }

    fun toggleVisibility(viewWantToVisible: View, viewWantToGone: View) {
        viewWantToGone.visibility = View.GONE
        viewWantToVisible.visibility = View.VISIBLE
    }

    fun getFileExtension(uri: Uri, context: Context): String? {
        val cR = context.contentResolver
        val mime = MimeTypeMap.getSingleton()
        return mime.getExtensionFromMimeType(cR.getType(uri))
    }

    fun toggleBoolean(intBoolean: Int): Int {
        if (intBoolean == 1) {
            return 0
        } else {
            return 1
        }
    }

    fun intToBoolean(intNumber: Int?): Boolean {
        if (intNumber == 1) {
            return true
        } else {
            return false
        }
    }

    fun booleanToInt(booleanVar: Boolean): Int {
        if (booleanVar) {
            return 1
        } else {
            return 0
        }
    }

    fun loadData(context: Context): String? {
        val sharedPreferences = context.getSharedPreferences(
            SHARED_PREFS,
            AppCompatActivity.MODE_PRIVATE
        )
        return sharedPreferences.getString(TEXT, "")
    }


}