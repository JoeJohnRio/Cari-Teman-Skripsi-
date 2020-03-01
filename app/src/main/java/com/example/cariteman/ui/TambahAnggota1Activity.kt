package com.example.cariteman.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.cariteman.R
import com.example.cariteman.databinding.ActivityTambahAnggota1Binding

class TambahAnggota1Activity: AppCompatActivity(){

    override fun onStart() {
        super.onStart()
        setContentView(R.layout.activity_tambah_anggota_1)

        val viewBind: ActivityTambahAnggota1Binding= DataBindingUtil.setContentView(this,
            R.layout.activity_tambah_anggota_1
        )

        viewBind.fabAddKelompok.setOnClickListener{
            val intent= Intent(this, DetailKelompokActivity::class.java)
            startActivity(intent)
        }

    }

}