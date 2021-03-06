package com.example.cariteman.ui.dashboard.barudilihat.view

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cariteman.R
import com.example.cariteman.data.model.PengalamanLombaOrganisasiResponse
import com.example.cariteman.ui.dashboard.presenter.ProfilePresenter
import com.example.cariteman.ui.pengalaman.pengalamanhome.view.PengalamanHomeFragment
import com.example.cariteman.ui.profile.view.ProfileMVPView
import com.example.cariteman.util.Utils.formatterDate
import com.example.cariteman.util.Utils.parserDate
import kotlinx.android.extensions.LayoutContainer
import java.text.SimpleDateFormat

class ProfilePengalamanLombaViewHolder(override val containerView: View) :
    RecyclerView.ViewHolder(containerView),
    LayoutContainer {

    private var url: String = "https://tanzolymp.com/images/default-non-user-no-photo-1-300x300.jpg"

    private var ivGambarPengalaman: ImageView? = null
    private var tvJudulPengalaman: TextView? = null
    private var tvTanggalPengalaman: TextView? = null
    private var tvDeskripsiPengalaman: TextView? = null
    private var ivModifyPengalaman: ImageView? = null

    init {
        ivGambarPengalaman = itemView.findViewById(R.id.iv_gambar_pengalaman)
        tvJudulPengalaman = itemView.findViewById(R.id.tv_judul_pengalaman)
        tvTanggalPengalaman = itemView.findViewById(R.id.tv_tanggal_pengalaman)
        tvDeskripsiPengalaman = itemView.findViewById(R.id.tv_deskripsi_pengalaman)
        ivModifyPengalaman = itemView.findViewById(R.id.iv_modify_pengalaman)
    }

    fun bindProfilPengalaman(response: PengalamanLombaOrganisasiResponse, presenter: ProfilePresenter<ProfileMVPView>) {
        try {
            Glide.with(this.itemView.context)
                .load(response.gambar?: url)
                .into(ivGambarPengalaman)
        } catch (e: Throwable) {
            Glide.with(this.itemView.context).load(url)
                .into(ivGambarPengalaman)
        }

        tvJudulPengalaman?.text = response.namaKompetisi ?: "Pengalaman"

        val parser = SimpleDateFormat("yyyy-MM-dd")
        val formatter = SimpleDateFormat("dd MMMM, yyyy")
        tvDeskripsiPengalaman?.text = response.deskripsi
        tvTanggalPengalaman?.text =
            "${formatter.format(parser.parse("${response.tanggal}"))}"
    }

    fun bindPengalaman(response: PengalamanLombaOrganisasiResponse, view: PengalamanHomeFragment) {
        try {
            Glide.with(this.itemView.context)
                .load(response.gambar?: url)
                .into(ivGambarPengalaman)
        } catch (e: Throwable) {
            Glide.with(this.itemView.context).load(url)
                .into(ivGambarPengalaman)
        }

        tvJudulPengalaman?.text = "${response.namaKompetisi} sebagai ${response.relationBidangKerja?.bidangKerja?.namaBidangKerja}" ?: "Pengalaman"

        tvDeskripsiPengalaman?.text = response.deskripsi
        tvTanggalPengalaman?.text =
            "${formatterDate.format(parserDate.parse("${response.tanggal}"))}"

        ivModifyPengalaman?.setOnClickListener {
            view.getModifyPengalamanLombaFragment(response)
        }
    }

}