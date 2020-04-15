package com.example.cariteman.ui.dashboard.barudilihat.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cariteman.R
import com.example.cariteman.data.model.Rekomendasi
import com.example.cariteman.ui.dashboard.presenter.ProfilePresenter
import com.example.cariteman.ui.profile.view.ProfileMVPView
import kotlinx.android.extensions.LayoutContainer

class RekomendasiListAdapter(presenter: ProfilePresenter<ProfileMVPView>) :
    ListAdapter<Rekomendasi, RecyclerView.ViewHolder>(RekomendasiDiffCallBack()) {
    var presenterAdapter = presenter

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is RekomendasiViewHolder) {
            val response = getItem(position) as Rekomendasi

            holder.bind(response, presenterAdapter)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RekomendasiViewHolder {
        return RekomendasiViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_rekomendasi_profile,
                parent,
                false
            )
        )
    }
}

class RekomendasiViewHolder(override val containerView: View) :
    RecyclerView.ViewHolder(containerView), LayoutContainer {
    private var tvNamaPerekomendasi: TextView? = null
    private var ivGambarPerekomendasi: ImageView? = null
    private var rbRatingRekomendasi: RatingBar? = null
    private var tvIsiRekomendasi: TextView? = null
    private var url: String = "https://tanzolymp.com/images/default-non-user-no-photo-1-300x300.jpg"

    init {
        tvNamaPerekomendasi = itemView.findViewById(R.id.tv_nama_perekomendasi)
        ivGambarPerekomendasi = itemView.findViewById(R.id.iv_gambar_perekomendasi)
        rbRatingRekomendasi = itemView.findViewById(R.id.rb_rating_rekomendasi)
        tvIsiRekomendasi = itemView.findViewById(R.id.tv_isi_rekomendasi)
    }

    fun bind(response: Rekomendasi?, presenter: ProfilePresenter<ProfileMVPView>) {

        try {
            Glide.with(this.itemView.context)
                .load(response?.dataMahasiswa?.foto_profil ?: url)
                .into(ivGambarPerekomendasi)
        } catch (e: Throwable) {
            Glide.with(this.itemView.context).load(url)
                .into(ivGambarPerekomendasi)
        }

        tvNamaPerekomendasi?.text = response?.dataMahasiswa?.name

        rbRatingRekomendasi?.rating = response?.jumlahRating?.toFloat()!!

        tvIsiRekomendasi?.text = response.deskripsi
    }

}