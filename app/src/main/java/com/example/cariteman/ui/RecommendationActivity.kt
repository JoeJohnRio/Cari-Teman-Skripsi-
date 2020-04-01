package com.example.cariteman.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.cariteman.R
import com.example.cariteman.databinding.ActivityListOrangBinding

class RecommendationActivity: AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_orang)
        var viewBind: ActivityListOrangBinding = DataBindingUtil.setContentView(this,
            R.layout.activity_list_orang
        )

        viewBind.tvToolbarTitle.text = "Rekomendasi"

        viewBind.ivBack.setOnClickListener {
            this.finish()
        }
    }
}