package com.example.cariteman.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.cariteman.R
import com.example.cariteman.databinding.ActivityDaftarKelompokBinding

class DaftarKelompokActivity: AppCompatActivity(){

    override fun onStart() {
        super.onStart()
        setContentView(R.layout.activity_daftar_kelompok)

        val viewBind: ActivityDaftarKelompokBinding = DataBindingUtil.setContentView(this,
            R.layout.activity_daftar_kelompok
        )

        viewBind.llDaftarKelompok.setOnClickListener{
            val intent= Intent(this, DetailKelompokActivity::class.java)
            startActivity(intent)
        }

        viewBind.fabAddKelompok.setOnClickListener{
            val intent = Intent(this, TambahKelompok1Activity::class.java)
            startActivity(intent)
        }
    }

}