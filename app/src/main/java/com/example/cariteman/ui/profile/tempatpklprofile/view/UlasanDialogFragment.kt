package com.example.cariteman.ui.profile.tempatpklprofile.view

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.cariteman.R
import android.graphics.drawable.ColorDrawable
import android.graphics.Color


class UlasanDialogFragment : DialogFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_add_ulasan_tempat_pkl, container, false)
    }
    override fun onViewCreated(view:View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val editText = view.findViewById<EditText>(R.id.inMobile)
        if (arguments != null && !TextUtils.isEmpty(arguments?.getString("mobile")))
            editText.setText(arguments?.getString("mobile"))
        val btnDone = view.findViewById<Button>(R.id.btnDone)
        btnDone.setOnClickListener(object: View.OnClickListener {
            override fun onClick(view:View) {
                if (editText.text.toString() != ""){
                    val dialogListener = activity as DialogListener
                    dialogListener.onFinishEditDialog(editText.text.toString())
                    dismiss()
                }
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Hey", "onCreate")
        var  setFullScreen = false
        if (arguments != null) {
            setFullScreen = requireNotNull(arguments?.getBoolean("fullScreen"))
        }
        if (setFullScreen)
            setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Black_NoTitleBar_Fullscreen)
    }

    override fun onStart() {
        super.onStart()

        val dialog = dialog
        if (dialog != null) {
            dialog.window!!.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
    }

    interface DialogListener {
        fun onFinishEditDialog(isiUlasan: String)
    }
}
