package com.example.cariteman.ui.dashboard.barudilihat.view

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.BounceInterpolator
import android.view.animation.ScaleAnimation
import android.widget.CompoundButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.ToggleButton
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cariteman.R
import com.example.cariteman.data.model.MahasiswaHistoryDashboardResponse
import com.example.cariteman.data.model.PengalamanLombaOrganisasiResponse
import com.example.cariteman.data.model.RelationTempatPklFavorite
import com.example.cariteman.ui.dashboard.barudilihat.presenter.BaruDilihatPresenter
import com.example.cariteman.ui.dashboard.presenter.DashboardPresenter
import com.example.cariteman.ui.dashboard.presenter.ProfilePresenter
import com.example.cariteman.ui.dashboard.view.DashboardMVPView
import com.example.cariteman.ui.profile.view.ProfileActivity
import com.example.cariteman.ui.profile.view.ProfileMVPView
import com.example.cariteman.util.Utils
import kotlinx.android.extensions.LayoutContainer
import java.lang.Exception

class ProfilePengalamanLombaViewHolder(override val containerView: View) :
    RecyclerView.ViewHolder(containerView),
    LayoutContainer {

    private var url: String = "https://tanzolymp.com/images/default-non-user-no-photo-1-300x300.jpg"

    private var ivGambarPengalaman: ImageView? = null
    private var tvJudulPengalaman: TextView? = null
    private var tvTanggalPengalaman: TextView? = null

    init {
        ivGambarPengalaman = itemView.findViewById(R.id.iv_gambar_pengalaman)
        tvJudulPengalaman = itemView.findViewById(R.id.tv_judul_pengalaman)
        tvTanggalPengalaman = itemView.findViewById(R.id.tv_tanggal_pengalaman)
    }

    fun bindHistory(response: PengalamanLombaOrganisasiResponse, presenter: ProfilePresenter<ProfileMVPView>) {
        try {
            Glide.with(this.itemView.context)
                .load(response.gambar?: url)
                .into(ivGambarPengalaman)
        } catch (e: Throwable) {
            Glide.with(this.itemView.context).load(url)
                .into(ivGambarPengalaman)
        }

        tvJudulPengalaman?.text = response.namaKompetisi ?: "Pengalaman"

        tvTanggalPengalaman?.text = "${response.tanggal}"
    }

}