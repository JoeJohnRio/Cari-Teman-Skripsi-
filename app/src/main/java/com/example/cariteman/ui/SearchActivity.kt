package com.example.cariteman.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.cariteman.R
import com.example.cariteman.databinding.ActivitySearchBinding
import com.example.cariteman.ui.filter.FilterDialog

class SearchActivity: AppCompatActivity(){

    private lateinit var viewBind: ActivitySearchBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        viewBind = DataBindingUtil.setContentView(this,
            R.layout.activity_search
        )

        viewBind.ivBack.setOnClickListener {
            this.finish()
        }

        viewBind.bFilterSearch.setOnClickListener {
            val intent = Intent(this, FilterDialog::class.java)
            startActivity(intent)
        }

    }

}