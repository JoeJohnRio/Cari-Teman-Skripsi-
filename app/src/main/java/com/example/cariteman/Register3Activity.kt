package com.example.cariteman

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.cariteman.databinding.ActivityRegister3Binding

class Register3Activity: AppCompatActivity(){

    private lateinit var viewBind: ActivityRegister3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_3)
        viewBind = DataBindingUtil.setContentView(this, R.layout.activity_register_3)

        viewBind.cvLomba.setOnClickListener {
            viewBind.cvLomba.setCardBackgroundColor(resources.getColor(R.color.white_low))
            viewBind.tvLomba.setTextColor(resources.getColor(R.color.navy_blue_high))

            viewBind.cvPkl.setCardBackgroundColor(resources.getColor(R.color.navy_blue_high))
            viewBind.tvPkl.setTextColor(resources.getColor(R.color.white_low))
        }

        viewBind.cvPkl.setOnClickListener {
            viewBind.cvLomba.setCardBackgroundColor(resources.getColor(R.color.navy_blue_high))
            viewBind.tvLomba.setTextColor(resources.getColor(R.color.white_low))

            viewBind.cvPkl.setCardBackgroundColor(resources.getColor(R.color.white_low))
            viewBind.tvPkl.setTextColor(resources.getColor(R.color.navy_blue_high))
        }

        viewBind.bRegister.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

}