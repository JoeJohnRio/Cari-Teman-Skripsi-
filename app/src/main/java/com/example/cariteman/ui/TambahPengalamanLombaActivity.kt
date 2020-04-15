package com.example.cariteman.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.cariteman.R
import com.example.cariteman.databinding.ActivityTambahPengalamanLombaBinding
import com.example.cariteman.ui.pengalaman.view.PengalamanActivity

class TambahPengalamanLombaActivity: AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_pengalaman_lomba)

        val viewBind: ActivityTambahPengalamanLombaBinding = DataBindingUtil.setContentView(this,
            R.layout.activity_tambah_pengalaman_lomba
        )

        viewBind.mbSaveButton.setOnClickListener{
            val intent= Intent(this, PengalamanActivity::class.java)
            startActivity(intent)
        }

    }
}