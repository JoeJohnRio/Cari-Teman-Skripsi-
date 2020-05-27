package com.example.cariteman.ui.dashboard.barudilihat.view

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cariteman.R
import com.example.cariteman.data.model.MessageKelompok
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.extensions.LayoutContainer


class MessageIsNotUserViewHolder(override val containerView: View) :
    RecyclerView.ViewHolder(containerView),
    LayoutContainer {

    private var url: String = "https://tanzolymp.com/images/default-non-user-no-photo-1-300x300.jpg"

    private var civFotoProfilPesan: CircleImageView? = null
    private var tvNamaPengirimPesan: TextView? = null
    private var tvIsiPesan: TextView? = null

    init {
        civFotoProfilPesan = itemView.findViewById(R.id.civ_foto_profil_pesan)
        tvNamaPengirimPesan = itemView.findViewById(R.id.tv_nama_pengirim_pesan)
        tvIsiPesan = itemView.findViewById(R.id.tv_isi_pesan)
    }

    fun bind(
        response: MessageKelompok
    ) {
        Glide.with(this.itemView.context)
            .load(response.mahasiswa?.foto_profil ?: url)
            .into(civFotoProfilPesan)

        tvNamaPengirimPesan?.setText(response.mahasiswa?.name)
        tvIsiPesan?.setText(response.isiPesan)
    }
}