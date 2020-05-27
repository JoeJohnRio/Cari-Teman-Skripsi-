package com.example.cariteman.ui.dashboard.barudilihat.view

import android.content.Intent
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cariteman.R
import com.example.cariteman.data.model.NotifikasiResponse
import com.example.cariteman.ui.profile.view.ProfileActivity
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.extensions.LayoutContainer


class NotifikasiMenolakPertemananViewHolder(override val containerView: View) :
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
        response: NotifikasiResponse
    ) {
        Glide.with(this.itemView.context)
            .load(response.mahasiswa?.foto_profil ?: url)
            .into(civNotifikasi)

        tvDescNotifikasi?.setText("${response.mahasiswa?.name} menolak permintaan berteman anda")

        tvNamaTemanNotifikasi?.setText(response.mahasiswa?.name)

        itemView.setOnClickListener {
            val intent = Intent(itemView.context, ProfileActivity::class.java)
            intent.putExtra(
                "MAHASISWA_ID",
                response.mahasiswa?.id
            )
            itemView.context.startActivity(intent)
        }
    }
}