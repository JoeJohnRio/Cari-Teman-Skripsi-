package com.example.cariteman.ui.profile.view

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

class RekomendasiDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        if (arguments != null)
        {
            if (arguments?.getBoolean("notAlertDialog")!!)
            {
                return super.onCreateDialog(savedInstanceState)
            }
        }
        val builder = AlertDialog.Builder(activity, R.style.CustomAlertDialog)
        builder.setTitle("Mengganti gambar")
        builder.setMessage("Apa anda yakin ingin mengganti foto profil anda?")
        builder.setPositiveButton("Ya", object: DialogInterface.OnClickListener {
            override fun onClick(dialog:DialogInterface, which:Int) {
                val dialogListener = activity as DialogListener
                dialogListener.returnAnswer(true)
                dismiss()
            }
        })
        builder.setNegativeButton("Tidak", object: DialogInterface.OnClickListener {
            override fun onClick(dialog:DialogInterface, which:Int) {
                val dialogListener = activity as DialogListener
                dialogListener.returnAnswer(false)
                dismiss()
            }
        })
        return builder.create()
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_fragment_rekomendasi, container, false)
    }
    override fun onViewCreated(view:View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val editText = view.findViewById<EditText>(R.id.inMobile)
        val ratingBar = view.findViewById<RatingBar>(R.id.rb_rating_rekomendasi_isi)
        if (arguments != null && !TextUtils.isEmpty(arguments?.getString("mobile")))
            editText.setText(arguments?.getString("mobile"))
        val btnDone = view.findViewById<Button>(R.id.btnDone)
        btnDone.setOnClickListener(object: View.OnClickListener {
            override fun onClick(view:View) {
                if (editText.text.toString() != ""){
                    val dialogListener = activity as DialogListener
                    dialogListener.onFinishEditDialog(editText.text.toString(), ratingBar.rating.toInt())
                    dismiss()
                }
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var  setFullScreen = false
        if (arguments != null) {
            setFullScreen = requireNotNull(arguments?.getBoolean("fullScreen"))
        }
        if (setFullScreen)
            setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Black_NoTitleBar_Fullscreen)
    }

    interface DialogListener {
        fun onFinishEditDialog(deskripsiRekomendasi: String, jumlahRating: Int)

        fun returnAnswer(isYes: Boolean)
    }
}
