package com.example.cariteman.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.cariteman.R
import com.example.cariteman.databinding.ActivityFavoritBinding
import com.example.cariteman.ui.dashboard.DashboardActivity

private lateinit var viewBind: ActivityFavoritBinding

class FavoritActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorit)

        viewBind = DataBindingUtil.setContentView(this,
            R.layout.activity_favorit
        )

        viewBind.tvToolbarTitle.text = "Favorit"

        viewBind.ivBack.setOnClickListener {
            this.finish()
        }

        viewBind.bnvDashboard.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_item_beranda -> {
                    val intent = Intent(this, DashboardActivity::class.java)
                    startActivity(intent)
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

                else -> {
                    true
                }
            }
        }
    }
}
