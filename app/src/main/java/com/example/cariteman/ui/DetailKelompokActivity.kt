package com.example.cariteman.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.cariteman.R
import com.example.cariteman.databinding.ActivityDetailKelompokBinding

class DetailKelompokActivity: AppCompatActivity(){

    override fun onStart() {
        super.onStart()
        setContentView(R.layout.activity_detail_kelompok)

        var viewBind: ActivityDetailKelompokBinding = DataBindingUtil.setContentView(this,
            R.layout.activity_detail_kelompok
        )

        viewBind.bTambahAnggota.setOnClickListener {
            val intent = Intent(this, TambahAnggota1Activity::class.java)
            startActivity(intent)
        }

    }

}