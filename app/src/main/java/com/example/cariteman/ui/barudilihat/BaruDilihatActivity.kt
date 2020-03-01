package com.example.cariteman.ui.barudilihat

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.cariteman.R
import com.example.cariteman.databinding.FragmentListOrangBinding

class BaruDilihatActivity: AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_list_orang)
        var viewBind: FragmentListOrangBinding = DataBindingUtil.setContentView(this,
            R.layout.fragment_list_orang
        )

        viewBind.tvToolbarTitle.text = "Baru Dilihat"

        viewBind.ivBack.setOnClickListener {
            this.finish()
        }
    }

}