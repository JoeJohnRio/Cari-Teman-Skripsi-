package com.example.cariteman

import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.cariteman.databinding.ActivityTambahAnggota1Binding

class TambahAnggota1: AppCompatActivity(){

    override fun onStart() {
        super.onStart()
        setContentView(R.layout.activity_tambah_anggota_1)

        val viewBind: ActivityTambahAnggota1Binding = DataBindingUtil.setContentView(this, R.layout.activity_tambah_anggota_1)

    }

}