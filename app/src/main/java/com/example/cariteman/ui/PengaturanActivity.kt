package com.example.cariteman.ui

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import com.example.cariteman.R
import com.example.cariteman.ui.dashboard.view.DashboardBottomViewActivity

class PengaturanActivity: AppCompatActivity(){

    override fun onStart() {
        super.onStart()
        setContentView(R.layout.activity_pengaturan)

        DashboardBottomViewActivity.activityEnd.finish()

        finish()
    }

}