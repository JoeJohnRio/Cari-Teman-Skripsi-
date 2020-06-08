package com.example.cariteman.ui.dashboard.barudilihat.view

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cariteman.R
import com.example.cariteman.data.model.RelationKelompok
import com.example.cariteman.ui.pengalaman.pengalamanhome.view.KelompokHomeFragment
import com.example.cariteman.ui.profile.view.ProfileActivity
import kotlinx.android.extensions.LayoutContainer


class DaftarKelompokProfileActivityViewHolder(override val containerView: View) :
    RecyclerView.ViewHolder(containerView),
    LayoutContainer {

    private var url: String = "https://tanzolymp.com/images/default-non-user-no-photo-1-300x300.jpg"

    private var ivGambarKelompok: ImageView? = null
    private var tvNamaKelompok: TextView? = null
    private var tvAnggotaKelompok: TextView? = null

    init {
        ivGambarKelompok = itemView.findViewById(R.id.iv_kelompok_ic2)
        tvNamaKelompok = itemView.findViewById(R.id.tv_nama_kelompok)
        tvAnggotaKelompok = itemView.findViewById(R.id.tv_anggota_kelompok)
    }

    fun bind(
        response: RelationKelompok,
        view: ProfileActivity
    ) {
        Glide.with(this.itemView.context)
            .load(response.kelompok?.fotoKelompok?: url)
            .into(ivGambarKelompok)
        var tipeKelompok = ""
        if (response.kelompok?.tipeKelompok == 0){
            tipeKelompok = "PKL"
        }else if(response.kelompok?.tipeKelompok == 1){
            tipeKelompok = "Lomba"
        }
        tvNamaKelompok?.setText(tipeKelompok + " - " + response.kelompok?.namaKelompok ?: "")

        itemView.setOnClickListener {
            view.showMessageToast("clicked")
            view.addFriendToKelompok(response.idKelompok!!)
        }
    }
}