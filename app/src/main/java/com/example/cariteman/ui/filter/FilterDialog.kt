package com.example.cariteman.ui.filter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.cariteman.R
import com.example.cariteman.databinding.DialogFilterBinding
import com.google.android.material.button.MaterialButton

class FilterDialog : AppCompatActivity(){


    private lateinit var viewBind: DialogFilterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBind = DataBindingUtil.setContentView(this,
            R.layout.dialog_filter
        )

        viewBind.ivBack.setOnClickListener{
            this.finish()
        }

        viewBind.bTypeFilterPkl.setOnClickListener{
            toggleTurnOf(viewBind.bTypeFilterPkl, viewBind.bTypeFilterLomba, viewBind.bTypeFilterTempatPkl,
                R.color.navy_blue,
                R.color.white_low
            )
        }
        viewBind.bTypeFilterLomba.setOnClickListener{
            toggleTurnOf(viewBind.bTypeFilterLomba, viewBind.bTypeFilterPkl, viewBind.bTypeFilterTempatPkl,
                R.color.navy_blue,
                R.color.white_low
            )
        }
        viewBind.bTypeFilterTempatPkl.setOnClickListener{
            toggleTurnOf(viewBind.bTypeFilterTempatPkl, viewBind.bTypeFilterPkl, viewBind.bTypeFilterLomba,
                R.color.navy_blue,
                R.color.white_low
            )
        }

    }
    fun toggleTurnOf(turnUp: MaterialButton, turnDown: MaterialButton, turnDown1: MaterialButton, colorOn: Int, colorOff: Int){
        turnUp.setBackgroundColor(resources.getColor(colorOn))
        turnUp.setTextColor(resources.getColor(colorOff))
        turnDown.setBackgroundColor(resources.getColor(colorOff))
        turnDown.setTextColor(resources.getColor(colorOn))
        turnDown1.setBackgroundColor(resources.getColor(colorOff))
        turnDown1.setTextColor(resources.getColor(colorOn))
    }
}