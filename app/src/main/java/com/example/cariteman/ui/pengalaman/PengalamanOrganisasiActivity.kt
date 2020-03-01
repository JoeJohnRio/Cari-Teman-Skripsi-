package com.example.cariteman.ui.pengalaman

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.cariteman.R
import com.example.cariteman.databinding.ActivityTambahPengalamanOrganisasiBinding

class PengalamanOrganisasiActivity: AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_pengalaman_organisasi)

        val viewBind: ActivityTambahPengalamanOrganisasiBinding = DataBindingUtil.setContentView(this,
            R.layout.activity_tambah_pengalaman_organisasi
        )
        viewBind.mbSaveButton.setOnClickListener {
            val intent = Intent(this, PengalamanActivity::class.java)
            startActivity(intent)
        }
    }

}