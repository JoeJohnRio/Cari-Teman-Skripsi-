package com.example.cariteman.ui.dashboard.rekomendasi

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cariteman.R
import com.example.cariteman.data.model.MahasiswaHistoryDashboardResponse
import com.example.cariteman.data.model.RekomendasiResponse
import com.example.cariteman.ui.profile.tempatpklprofile.view.ProfileTempatPklActivity
import com.example.cariteman.ui.profile.view.ProfileActivity
import kotlinx.android.extensions.LayoutContainer

class ProfilDashboardRekomendasiListAdapter:
    ListAdapter<RekomendasiResponse, RecyclerView.ViewHolder>(
        RekomendasiDiffCallback()
    ) {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is DashboardRekomendasiViewHolder) {
            val response = getItem(position) as RekomendasiResponse
            holder.bind(response)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DashboardRekomendasiViewHolder {
        return DashboardRekomendasiViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_profile_dashboard,
                parent,
                false
            )
        )
    }
}

class DashboardRekomendasiViewHolder(override val containerView: View) :
    RecyclerView.ViewHolder(containerView), LayoutContainer {
    private var profilePic: ImageView? = null
    private var civImageOne: ImageView? = null
    private var civImageTwo: ImageView? = null
    private var civImageThree: ImageView? = null
    private var tvRecommendationTotal: TextView? = null
    private var tvName: TextView? = null
    private var tvRiwayatLomba: TextView? = null
    private var tvJabatan: TextView? = null
    private var url: String = "https://tanzolymp.com/images/default-non-user-no-photo-1-300x300.jpg"
    private var urlBlank: String =
        "https://upload.wikimedia.org/wikipedia/commons/4/48/BLANK_ICON.png"

    init {
        profilePic = itemView.findViewById(R.id.iv_profil_pic)
        civImageOne = itemView.findViewById(R.id.civ_image_one)
        civImageTwo = itemView.findViewById(R.id.civ_image_two)
        civImageThree = itemView.findViewById(R.id.civ_image_three)
        tvRecommendationTotal = itemView.findViewById(R.id.tv_recommendation_total)
        tvName = itemView.findViewById(R.id.tv_name)
        tvRiwayatLomba = itemView.findViewById(R.id.tv_riwayat_lomba)
        tvJabatan = itemView.findViewById(R.id.tv_jabatan)
    }

    fun bind(response: RekomendasiResponse?) {
        if (response != null) {
            if (response.typeOfRecommendation == 1 || response.typeOfRecommendation == 2) {

                tvName?.text = "${response.name}"

                itemView.setOnClickListener {
                    val intent = Intent(
                        itemView.context,
                        ProfileActivity::class.java
                    ).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    intent.putExtra("MAHASISWA_ID", response.id)
                    itemView.context.startActivity(intent)
                }

                Glide.with(this.itemView.context).load(response.gambar)
                    .into(profilePic)

                try {
                    Glide.with(this.itemView.context)
                        .load(response.pengalamanLomba?.get(0)?.gambar ?: url)
                        .into(civImageOne)
                } catch (e: Throwable) {
                    Glide.with(this.itemView.context).load(url)
                        .into(civImageOne)
                }
                try {
                    Glide.with(this.itemView.context)
                        .load(response.pengalamanLomba?.get(1)?.gambar ?: url)
                        .into(civImageTwo)
                } catch (e: Throwable) {
                    Glide.with(this.itemView.context).load(url)
                        .into(civImageTwo)
                }
                try {
                    Glide.with(this.itemView.context)
                        .load(response.pengalamanLomba?.get(2)?.gambar ?: url)
                        .into(civImageThree)
                } catch (e: Throwable) {
                    Glide.with(this.itemView.context).load(url)
                        .into(civImageThree)
                }

                tvRecommendationTotal?.text = "${response.jumlahRekomendasi.toString()} of Recommendation"

                try {
                    tvRiwayatLomba?.text =
                        response.pengalamanLomba?.get(0)?.namaKompetisi
                            ?: "(Belum Memiliki Riwayat Lomba)"

                } catch (e: Throwable) {
                    tvRiwayatLomba?.text = "(Belum Memiliki Riwayat Lomba)"
                }

                try {
                    tvJabatan?.text =
                        response.pengalamanOrganisasi?.get(0)?.namaOrganisasi
                            ?: "(Belum mengikuti organisasi)"
                } catch (e: Throwable) {
                    tvJabatan?.text = "(Belum mengikuti organisasi)"
                }
            } else if (response.typeOfRecommendation == 3) {
                tvName?.text = "${response.namaPerusahaan}"

                itemView.setOnClickListener {
                    val intent = Intent(
                        itemView.context,
                        ProfileTempatPklActivity::class.java
                    ).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    intent.putExtra("tempatPklId", response.id)
                    itemView.context.startActivity(intent)
                }

                Glide.with(this.itemView.context).load(response.gambar)
                    .into(profilePic)


                Glide.with(this.itemView.context).load(urlBlank)
                    .into(civImageOne)
                Glide.with(this.itemView.context).load(urlBlank)
                    .into(civImageTwo)
                Glide.with(this.itemView.context).load(urlBlank)
                    .into(civImageThree)

                tvRecommendationTotal?.text = response.jumlahRekomendasi.toString()

                tvRiwayatLomba?.text = response.namaKota

                tvJabatan?.text = response.namaBidangKerja
            }
        }

    }
}

class RekomendasiDiffCallback : DiffUtil.ItemCallback<RekomendasiResponse>() {
    override fun areItemsTheSame(
        oldItem: RekomendasiResponse,
        newItem: RekomendasiResponse
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: RekomendasiResponse,
        newItem: RekomendasiResponse
    ): Boolean {
        return oldItem.id == newItem.id
    }
}