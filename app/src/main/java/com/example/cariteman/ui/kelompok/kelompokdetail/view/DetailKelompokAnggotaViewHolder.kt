package com.example.cariteman.ui.dashboard.barudilihat.view

import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cariteman.R
import com.example.cariteman.data.model.AnggotaKelompok
import com.example.cariteman.ui.pengalaman.pengalamanhome.view.KelompokDetailFragment
import com.example.cariteman.ui.profile.view.ProfileActivity
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.extensions.LayoutContainer


class DetailKelompokAnggotaViewHolder(override val containerView: View) :
    RecyclerView.ViewHolder(containerView),
    LayoutContainer {

    private var url: String = "https://tanzolymp.com/images/default-non-user-no-photo-1-300x300.jpg"

    private var civFotoProfilAnggota: CircleImageView? = null
    private var tvNamaAnggota: TextView? = null
    private var tvNamaFakultas: TextView? = null

    init {
        civFotoProfilAnggota = itemView.findViewById(R.id.civ_foto_profil_anggota)
        tvNamaAnggota = itemView.findViewById(R.id.tv_nama_anggota)
        tvNamaFakultas = itemView.findViewById(R.id.tv_nama_fakultas)
    }

    fun bind(
        response: AnggotaKelompok,
        view: KelompokDetailFragment
    ) {
        Glide.with(this.itemView.context)
            .load(response.fotoProfil ?: url)
            .into(civFotoProfilAnggota)
        var tipeKelompok = ""
        tvNamaAnggota?.setText(response.name ?: "")
        tvNamaFakultas?.setText(response.namaFakultas)

        itemView.setOnClickListener {
            val intent = Intent(itemView.context, ProfileActivity::class.java)
            intent.putExtra(
                "MAHASISWA_ID",
                response.id
            )
            itemView.context.startActivity(intent)
        }
    }
}