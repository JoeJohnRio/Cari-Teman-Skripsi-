package com.example.cariteman.ui.dashboard.barudilihat.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cariteman.R
import com.example.cariteman.data.model.MessageKelompok
import com.example.cariteman.data.model.NotifikasiResponse
import com.example.cariteman.ui.notifikasi.view.NotifikasiActivity
import com.example.cariteman.ui.pengalaman.pengalamanhome.view.KelompokDetailFragment
import com.example.cariteman.ui.profile.view.ProfileActivity
import com.example.cariteman.util.extension.addFragmentWithBackStack
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.extensions.LayoutContainer


class NotifikasiMemintaBergabungTimViewHolder(override val containerView: View) :
    RecyclerView.ViewHolder(containerView),
    LayoutContainer {

    private var url: String = "https://tanzolymp.com/images/default-non-user-no-photo-1-300x300.jpg"

    private var tvDescNotifikasi: TextView? = null
    private var tvNamaTemanNotifikasi: TextView? = null
    private var civNotifikasi: CircleImageView? = null

    init {
        civNotifikasi = itemView.findViewById(R.id.civ_foto_notifikasi)
        tvDescNotifikasi = itemView.findViewById(R.id.tv_desc_notifikasi)
        tvNamaTemanNotifikasi = itemView.findViewById(R.id.tv_nama_teman_notifikasi)
    }

    fun bind(
        response: NotifikasiResponse, activity: NotifikasiActivity
    ) {
        Glide.with(this.itemView.context)
            .load(response.mahasiswa?.foto_profil ?: url)
            .into(civNotifikasi)

        tvDescNotifikasi?.setText("${response.mahasiswa?.name} meminta bergabung pada tim")

        tvNamaTemanNotifikasi?.setText(response.mahasiswa?.name)

        itemView.setOnClickListener {
            activity.hideRecyclerView()

            val bundle = Bundle()
            bundle.putInt("id", response.idKelompok ?: 0)
            bundle.putString("namaKelompok", "test")
            bundle.putInt("isAlreadyKelompok", 0)
            val kelompokDetailFragment = KelompokDetailFragment.newInstance()
            kelompokDetailFragment.arguments = bundle
            activity.supportFragmentManager.addFragmentWithBackStack(
                R.id.f_kelompok_detail_fragment,
                kelompokDetailFragment,
                KelompokDetailFragment.TAG
            )
        }
    }
}