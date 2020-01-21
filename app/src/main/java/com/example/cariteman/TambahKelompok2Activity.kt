package com.example.cariteman

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.cariteman.databinding.ActivityTambahKelompok2Binding

class TambahKelompok2Activity: AppCompatActivity(){

    override fun onStart() {
        super.onStart()
        setContentView(R.layout.activity_tambah_kelompok_2)

        val viewBind: ActivityTambahKelompok2Binding = DataBindingUtil.setContentView(this, R.layout.activity_tambah_kelompok_2)


        viewBind.fabAddKelompok.setOnClickListener{
            val intent= Intent(this, DetailKelompokActivity::class.java)
            startActivity(intent)
        }

    }

}