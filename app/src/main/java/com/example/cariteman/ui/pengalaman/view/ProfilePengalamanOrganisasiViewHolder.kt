package com.example.cariteman.ui.dashboard.barudilihat.view

import android.view.View
import android.view.animation.Animation
import android.view.animation.BounceInterpolator
import android.view.animation.ScaleAnimation
import android.widget.ImageView
import android.widget.TextView
import android.widget.ToggleButton
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cariteman.R
import com.example.cariteman.data.model.PengalamanLombaOrganisasiResponse
import com.example.cariteman.data.model.RelationTempatPklFavorite
import com.example.cariteman.ui.dashboard.presenter.DashboardPresenter
import com.example.cariteman.ui.dashboard.presenter.ProfilePresenter
import com.example.cariteman.ui.dashboard.view.DashboardMVPView
import com.example.cariteman.ui.profile.view.ProfileMVPView
import com.example.cariteman.util.Utils
import kotlinx.android.extensions.LayoutContainer
import java.lang.Exception
import java.time.LocalDate
import java.util.*
import java.text.SimpleDateFormat


class ProfilePengalamanOrganisasiViewHolder(override val containerView: View) :
    RecyclerView.ViewHolder(containerView),
    LayoutContainer {

    private var url: String = "https://tanzolymp.com/images/default-non-user-no-photo-1-300x300.jpg"

    private var ivGambarPengalaman: ImageView? = null
    private var tvJudulPengalaman: TextView? = null
    private var tvTanggalPengalaman: TextView? = null
    private var tvDeskripsiPengalaman: TextView? = null

    init {
        ivGambarPengalaman = itemView.findViewById(R.id.iv_gambar_pengalaman)
        tvJudulPengalaman = itemView.findViewById(R.id.tv_judul_pengalaman)
        tvTanggalPengalaman = itemView.findViewById(R.id.tv_tanggal_pengalaman)
        tvDeskripsiPengalaman = itemView.findViewById(R.id.tv_deskripsi_pengalaman)
    }

    fun bindHistory(
        response: PengalamanLombaOrganisasiResponse,
        presenter: ProfilePresenter<ProfileMVPView>
    ) {
        try {
            Glide.with(this.itemView.context)
                .load(response.gambar ?: url)
                .into(ivGambarPengalaman)
        } catch (e: Throwable) {
            Glide.with(this.itemView.context).load(url)
                .into(ivGambarPengalaman)
        }

        tvJudulPengalaman?.text =
            "${response.namaOrganisasi}"
        if (response.relationBidangKerja?.bidangKerja?.namaBidangKerja != null){
            tvJudulPengalaman?.text =
                "${response.namaOrganisasi} sebagai ${response.relationBidangKerja?.bidangKerja?.namaBidangKerja }"
        }

        val parser = SimpleDateFormat("yyyy-MM-dd")
        val formatter = SimpleDateFormat("dd MMMM, yyyy")

        tvTanggalPengalaman?.text =
            "${formatter.format(parser.parse("${response.tanggalMulai}"))} sampai ${formatter.format(
                parser.parse("${response.tanggalSelesai}")
            )}"

        tvDeskripsiPengalaman?.text = response.deskripsi
    }
}