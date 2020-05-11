package com.example.cariteman.ui.dashboard.barudilihat.view

import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cariteman.R
import com.example.cariteman.data.model.DaftarTemanHanyaNama
import com.example.cariteman.ui.pengalaman.pengalamanhome.view.TambahKelompok1Fragment
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.extensions.LayoutContainer


class DaftarTemanViewHolder(override val containerView: View) :
    RecyclerView.ViewHolder(containerView),
    LayoutContainer {

    private var url: String = "https://tanzolymp.com/images/default-non-user-no-photo-1-300x300.jpg"

    private var civFotoProfil: CircleImageView? = null
    private var tvNamaTeman: TextView? = null
    private var cbCheckTeman: CheckBox? = null

    init {
        civFotoProfil = itemView.findViewById(R.id.civ_foto_profil)
        tvNamaTeman = itemView.findViewById(R.id.tv_nama_teman)
        cbCheckTeman = itemView.findViewById(R.id.cb_check_teman)
    }

    fun bind(
        response: DaftarTemanHanyaNama,
        view: TambahKelompok1Fragment
    ) {
        Glide.with(this.itemView.context)
            .load(response.fotoProfil ?: url)
            .into(civFotoProfil)

        tvNamaTeman?.setText(response.name ?: "")

        itemView.setOnClickListener {
            cbCheckTeman?.isChecked = !cbCheckTeman?.isChecked!!
            if (cbCheckTeman?.isChecked!!) {
                view.saveListOfTeman(response)
            } else {
                view.deleteListOfTeman(response)
            }
        }

    }
}