package com.example.cariteman.ui.verifikasiakun.verifikasiakunlist.view

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cariteman.R
import com.example.cariteman.data.model.MahasiswaNotVerifiedListResponse
import com.example.cariteman.ui.verifikasiakun.verifikasiakunlist.view.VerifikasiAkunListFragment
import kotlinx.android.extensions.LayoutContainer


class VerifikasiAkunListViewHolder(override val containerView: View) :
    RecyclerView.ViewHolder(containerView),
    LayoutContainer {

    private var url: String = "https://tanzolymp.com/images/default-non-user-no-photo-1-300x300.jpg"

    private var ivProfilPicWithFavorite: ImageView? = null
    private var tvNameAndYearInfo: TextView? = null
    private var tvNimInfo: TextView? = null
    private var tvFakultasProdiKeminatanInfo: TextView? = null

    init {
        ivProfilPicWithFavorite = itemView.findViewById(R.id.iv_profil_pic_with_favorite)
        tvNameAndYearInfo = itemView.findViewById(R.id.tv_name_and_year_info)
        tvNimInfo = itemView.findViewById(R.id.tv_nim_info)
        tvFakultasProdiKeminatanInfo = itemView.findViewById(R.id.tv_fakultas_prodi_keminatan_info)
    }

    fun bind(
        response: MahasiswaNotVerifiedListResponse,
        view: VerifikasiAkunListFragment
    ) {
        Glide.with(this.itemView.context)
            .load(response.fotoKtm ?: url)
            .into(ivProfilPicWithFavorite)

        tvNameAndYearInfo?.setText("${response.name} | ${response.tahunMulai}")

        tvNimInfo?.setText(response.nim)

        tvFakultasProdiKeminatanInfo?.setText("${response.fakultas}|${response.programStudi}|${response.keminatan}")

        itemView.setOnClickListener {
            view.goToMahasiswaDetail(id = response.id ?: 0)
        }
    }
}