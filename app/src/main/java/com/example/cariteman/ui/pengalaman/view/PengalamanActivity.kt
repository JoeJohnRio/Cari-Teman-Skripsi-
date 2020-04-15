package com.example.cariteman.ui.pengalaman.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.cariteman.R
import com.example.cariteman.databinding.ActivityPengalamanBinding

class PengalamanActivity: AppCompatActivity(){

    override fun onStart() {
        super.onStart()
        setContentView(R.layout.activity_pengalaman)


        val viewBind: ActivityPengalamanBinding = DataBindingUtil.setContentView(this,
            R.layout.activity_pengalaman
        )

        viewBind.llPengalamanLomba.setOnClickListener{
            val intent= Intent(this, TambahPengalamanLombaActivity::class.java)
            startActivity(intent)
        }

        viewBind.llPengalamanOrganisasi.setOnClickListener{
            val intent= Intent(this, TambahPengalamanOrganisasiActivity::class.java)
            startActivity(intent)
        }
    }

}