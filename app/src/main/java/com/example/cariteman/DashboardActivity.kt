package com.example.cariteman

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.cariteman.databinding.ActivityDashboardBinding
import com.google.android.material.button.MaterialButton

private lateinit var viewBind: ActivityDashboardBinding

class DashboardActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        viewBind = DataBindingUtil.setContentView(this, R.layout.activity_dashboard)


        utilButton()


    }

    fun utilButton(){

        viewBind.ivNotifikasiIc.setOnClickListener {
            val intent = Intent(this, NotifikasiActivity::class.java)
            startActivity(intent)
        }

        viewBind.bnvDashboard.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_item_beranda -> {
                    true
                }
                R.id.nav_item_favorite -> {
                    val intent = Intent(this, FavoritActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.nav_item_profil -> {
                    val intent = Intent(this, ProfileActivity::class.java)
                    startActivity(intent)
                    true
                }

                else ->{
                    true
                }
            }
        }
        viewBind.bSearchCariTeman.setOnClickListener{
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }
        viewBind.tvLihatSemuaBaruDilihat.setOnClickListener{
            var intent = Intent(this, BaruDilihatActivity::class.java)
            startActivity(intent)
        }

        viewBind.tvLihatSemuaRecommendation.setOnClickListener{
            var intent = Intent(this, RecommendationActivity::class.java)
            startActivity(intent)
        }

        viewBind.bSearchPkl.setOnClickListener{
            toggleTurnOf(viewBind.bSearchPkl, viewBind.bSearchLomba, viewBind.bSearchTempatPkl, R.color.orange_high, R.color.white)
        }
        viewBind.bSearchLomba.setOnClickListener{
            toggleTurnOf(viewBind.bSearchLomba, viewBind.bSearchPkl, viewBind.bSearchTempatPkl, R.color.orange_high, R.color.white)
        }
        viewBind.bSearchTempatPkl.setOnClickListener{
            toggleTurnOf(viewBind.bSearchTempatPkl, viewBind.bSearchLomba, viewBind.bSearchPkl, R.color.orange_high, R.color.white)
        }

        viewBind.bFilterLombaBaruDilihat.setOnClickListener{
            toggleTurnOf(viewBind.bFilterLombaBaruDilihat, viewBind.bFilterPklBaruDilihat, viewBind.bFilterTempatPklBaruDilihat, R.color.navy_blue, R.color.white)
        }
        viewBind.bFilterPklBaruDilihat.setOnClickListener{
            toggleTurnOf(viewBind.bFilterPklBaruDilihat, viewBind.bFilterLombaBaruDilihat, viewBind.bFilterTempatPklBaruDilihat, R.color.navy_blue, R.color.white)
        }
        viewBind.bFilterTempatPklBaruDilihat.setOnClickListener{
            toggleTurnOf(viewBind.bFilterTempatPklBaruDilihat, viewBind.bFilterPklBaruDilihat, viewBind.bFilterLombaBaruDilihat, R.color.navy_blue, R.color.white)
        }

        viewBind.bFilterLombaRecommendation.setOnClickListener{
            toggleTurnOf(viewBind.bFilterLombaRecommendation, viewBind.bFilterPklRecommendation, viewBind.bFilterTempatPklRecommendation, R.color.navy_blue, R.color.white)
        }
        viewBind.bFilterPklRecommendation.setOnClickListener{
            toggleTurnOf(viewBind.bFilterPklRecommendation, viewBind.bFilterLombaRecommendation, viewBind.bFilterTempatPklRecommendation, R.color.navy_blue, R.color.white)
        }
        viewBind.bFilterTempatPklRecommendation.setOnClickListener{
            toggleTurnOf(viewBind.bFilterTempatPklRecommendation, viewBind.bFilterPklRecommendation, viewBind.bFilterLombaRecommendation, R.color.navy_blue, R.color.white)
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