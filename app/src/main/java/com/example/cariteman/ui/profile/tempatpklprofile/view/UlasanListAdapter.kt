package com.example.cariteman.ui.profile.tempatpklprofile.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cariteman.R
import com.example.cariteman.data.model.UlasanTempatPklProfile
import kotlinx.android.extensions.LayoutContainer

class UlasanListAdapter :
    ListAdapter<UlasanTempatPklProfile, RecyclerView.ViewHolder>(UlasanDiffCallBack()) {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is UlasanViewHolder) {
            val response = getItem(position) as UlasanTempatPklProfile

            holder.bind(response)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UlasanViewHolder {
        return UlasanViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_ulasan_profile,
                parent,
                false
            )
        )
    }
}

class UlasanViewHolder(override val containerView: View) :
    RecyclerView.ViewHolder(containerView), LayoutContainer {

    private var tvNamaPengulas: TextView? = null
    private var ivGambarPengulas: ImageView? = null
    private var tvIsiUlasan: TextView? = null
    private var url: String = "https://tanzolymp.com/images/default-non-user-no-photo-1-300x300.jpg"

    init {
        tvNamaPengulas = itemView.findViewById(R.id.tv_nama_pengulas)
        ivGambarPengulas = itemView.findViewById(R.id.iv_gambar_pengulas)
        tvIsiUlasan = itemView.findViewById(R.id.tv_isi_ulasan)
    }

    fun bind(response: UlasanTempatPklProfile?) {
        try {
            Glide.with(this.itemView.context)
                .load(response?.gambarPengirim ?: url)
                .into(ivGambarPengulas)
        } catch (e: Throwable) {
            Glide.with(this.itemView.context).load(url)
                .into(ivGambarPengulas)
        }
        tvNamaPengulas?.text = response?.namaPengirim

        tvIsiUlasan?.text = response?.isiUlasan
    }

}