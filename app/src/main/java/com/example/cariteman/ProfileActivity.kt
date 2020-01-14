package com.example.cariteman

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.cariteman.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        var viewBind: ActivityProfileBinding = DataBindingUtil.setContentView(this, R.layout.activity_profile)

        viewBind.bPengaturan.setOnClickListener {
            val intent = Intent(this, PengaturanActivity::class.java)
            startActivity(intent)
        }

        viewBind.bProfilSaya.setOnClickListener {
            val intent = Intent(this, UserProfilActivity::class.java)
            startActivity(intent)
        }

        viewBind.bDaftarKelompok.setOnClickListener{
            val intent = Intent(this, DaftarKelompokActivity::class.java)
            startActivity(intent)
        }

        viewBind.bPengalaman.setOnClickListener {
            val intent = Intent(this, PengalamanActivity::class.java)
            startActivity(intent)
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
                    true
                }

                else ->{
                    true
                }
            }
        }

    }
}