package com.example.cariteman.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.cariteman.R
import com.example.cariteman.databinding.ActivityTambahKelompok1Binding

class TambahKelompok1Activity: AppCompatActivity(){

    override fun onStart() {
        super.onStart()
        setContentView(R.layout.activity_tambah_kelompok_1)

        val viewBind: ActivityTambahKelompok1Binding = DataBindingUtil.setContentView(this,
            R.layout.activity_tambah_kelompok_1
        )

        viewBind.fabAddKelompok.setOnClickListener{
            val intent= Intent(this, TambahKelompok2Activity::class.java)
            startActivity(intent)
        }

    }

}