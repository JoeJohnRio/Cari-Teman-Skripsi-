package com.example.cariteman

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.cariteman.databinding.ActivityLoginBinding

class MainActivity : AppCompatActivity() {
    lateinit var activityLoginBinding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        activityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        activityLoginBinding.bSignIn.setOnClickListener{
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
        }

        activityLoginBinding.tvSignUpNotHaveAccount.setOnClickListener {
            val intent = Intent(this, Register1Activity::class.java)
            startActivity(intent)
        }
    }
}
