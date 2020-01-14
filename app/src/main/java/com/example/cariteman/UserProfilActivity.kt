package com.example.cariteman

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.cariteman.databinding.ActivityUserProfileBinding

class UserProfilActivity: AppCompatActivity(){

    override fun onStart() {
        super.onStart()
        setContentView(R.layout.activity_user_profile)



        var viewBind: ActivityUserProfileBinding = DataBindingUtil.setContentView(this, R.layout.activity_user_profile)
        viewBind.bKirimPesan.setOnClickListener {
            val intent = Intent(this, MessageActivity::class.java)
            startActivity(intent)
        }


    }

}