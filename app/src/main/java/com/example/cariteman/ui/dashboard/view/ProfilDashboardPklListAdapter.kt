package com.example.cariteman.ui.dashboard.view

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
import com.example.cariteman.ui.profile.tempatpklprofile.view.ProfileTempatPklActivity
import com.example.cariteman.ui.profile.view.ProfileActivity
import kotlinx.android.extensions.LayoutContainer

class ProfilDashboardPklListAdapter :
    ListAdapter<MahasiswaHistoryDashboardResponse, RecyclerView.ViewHolder>(
        MahasiswaDashboardDiffCallback()
    ) {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is DashboardHistoryViewHolder) {
            val response = getItem(position) as MahasiswaHistoryDashboardResponse
            holder.bind(response)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardHistoryViewHolder {
        return DashboardHistoryViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_profile_dashboard,
                parent,
                false
            )
        )
    }
}

class DashboardHistoryViewHolder(override val containerView: View) :
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

    fun bind(response: MahasiswaHistoryDashboardResponse?) {
        if (response != null) {
            if (response.mahasiswaTwoPkl != null) {

                itemView.setOnClickListener {
                    val intent = Intent(
                        itemView.context,
                        ProfileActivity::class.java
                    ).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    intent.putExtra("MAHASISWA_ID", response?.idMahasiswaTwo)
                    itemView.context.startActivity(intent)
                }

                Glide.with(this.itemView.context).load(response.mahasiswaTwoPkl?.foto_profil)
                    .into(profilePic)

                try {
                    Glide.with(this.itemView.context)
                        .load(response.mahasiswaTwoPkl?.pengalamanLomba?.get(0)?.gambar ?: url)
                        .into(civImageOne)
                } catch (e: Throwable) {
                    Glide.with(this.itemView.context).load(url)
                        .into(civImageOne)
                }
                try {
                    Glide.with(this.itemView.context)
                        .load(response.mahasiswaTwoPkl?.pengalamanLomba?.get(1)?.gambar ?: url)
                        .into(civImageTwo)
                } catch (e: Throwable) {
                    Glide.with(this.itemView.context).load(url)
                        .into(civImageTwo)
                }
                try {
                    Glide.with(this.itemView.context)
                        .load(response.mahasiswaTwoPkl?.pengalamanLomba?.get(2)?.gambar ?: url)
                        .into(civImageThree)
                } catch (e: Throwable) {
                    Glide.with(this.itemView.context).load(url)
                        .into(civImageThree)
                }

                if (response.mahasiswaTwoPkl?.countRekomendasi?.size == null) {
                    tvRecommendationTotal?.text = "0 of Recommendation"
                } else {
                    tvRecommendationTotal?.text =
                        "${response.mahasiswaTwoPkl?.countRekomendasi?.size.toString()} of Recommendation"
                            ?: "0 of Recommendation"
                }
                tvName?.text = "${response.mahasiswaTwoPkl?.name}"

                try {
                    tvRiwayatLomba?.text =
                        response.mahasiswaTwoPkl?.pengalamanLomba?.get(0)?.namaKompetisi
                            ?: "Belum mengikuti kompetisi"

                } catch (e: Throwable) {
                    tvRiwayatLomba?.text = "Belum mengikuti kompetisi"
                }

                try {
                    tvJabatan?.text =
                        response.mahasiswaTwoPkl?.pengalamanOrganisasi?.get(0)?.namaOrganisasi
                            ?: "Belum mengikuti organisasi"
                } catch (e: Throwable) {
                    tvJabatan?.text = "Belum mengikuti organisasi"
                }
            } else if (response.mahasiswaTwoLomba != null) {

                itemView.setOnClickListener {
                    val intent = Intent(
                        itemView.context,
                        ProfileActivity::class.java
                    ).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    intent.putExtra("MAHASISWA_ID", response?.idMahasiswaTwo)
                    itemView.context.startActivity(intent)
                }

                Glide.with(this.itemView.context).load(response.mahasiswaTwoLomba?.foto_profil)
                    .into(profilePic)

                try {
                    Glide.with(this.itemView.context)
                        .load(response.mahasiswaTwoLomba?.pengalamanLomba?.get(0)?.gambar ?: url)
                        .into(civImageOne)
                } catch (e: Throwable) {
                    Glide.with(this.itemView.context).load(url)
                        .into(civImageOne)
                }
                try {
                    Glide.with(this.itemView.context)
                        .load(response.mahasiswaTwoLomba?.pengalamanLomba?.get(1)?.gambar ?: url)
                        .into(civImageTwo)
                } catch (e: Throwable) {
                    Glide.with(this.itemView.context).load(url)
                        .into(civImageTwo)
                }
                try {
                    Glide.with(this.itemView.context)
                        .load(response.mahasiswaTwoLomba?.pengalamanLomba?.get(2)?.gambar ?: url)
                        .into(civImageThree)
                } catch (e: Throwable) {
                    Glide.with(this.itemView.context).load(url)
                        .into(civImageThree)
                }

                if (response.mahasiswaTwoLomba?.countRekomendasi?.size == null) {
                    tvRecommendationTotal?.text = "0 of Recommendation"
                } else {
                    tvRecommendationTotal?.text =" ${response.mahasiswaTwoLomba?.countRekomendasi?.size.toString()} of Recommendation" ?: "0 of Recommendation"
                }
                tvName?.text = "Lomba - " + response.mahasiswaTwoLomba?.name ?: "no name"

                try {
                    tvRiwayatLomba?.text =
                        response.mahasiswaTwoLomba?.pengalamanLomba?.get(0)?.namaKompetisi
                            ?: "Belum mengikuti kompetisi"
                } catch (e: Throwable) {
                    tvRiwayatLomba?.text = "Belum mengikuti kompetisi"
                }

                try {
                    tvJabatan?.text =
                        response.mahasiswaTwoLomba?.pengalamanOrganisasi?.get(0)?.namaOrganisasi
                            ?: "Belum mengikuti organisasi"
                } catch (e: Throwable) {
                    tvJabatan?.text = "Belum mengikuti organisasi"
                }
            } else if (response.tempatPkl != null) {
                itemView.setOnClickListener {
                    val intent = Intent(
                        itemView.context,
                        ProfileTempatPklActivity::class.java
                    ).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    intent.putExtra("tempatPklId", response.tempatPkl?.id)
                    itemView.context.startActivity(intent)
                }

                Glide.with(this.itemView.context).load(response.tempatPkl?.gambar).into(profilePic)

                Glide.with(this.itemView.context).load(urlBlank)
                    .into(civImageOne)
                Glide.with(this.itemView.context).load(urlBlank)
                    .into(civImageTwo)
                Glide.with(this.itemView.context).load(urlBlank)
                    .into(civImageThree)
                if (response.tempatPkl?.countUlasanTempatPkl?.size == null) {
                    tvRecommendationTotal?.text = "0 Ulasan"
                } else {
                    tvRecommendationTotal?.text = "${response.tempatPkl?.countUlasanTempatPkl?.size.toString()} of Recommendation" ?: "0 of Recommendation"
                }
                tvName?.text = "${response.tempatPkl?.namaPerusahaan}"

                try {
                    tvRiwayatLomba?.text = response.tempatPkl?.lokasiPkl?.namaKota ?: "Kota Error"
                } catch (e: Throwable) {
                    tvRiwayatLomba?.text = "Kota Error"
                }

                try {
                    tvJabatan?.text =
                        response.tempatPkl?.relationBidangKerja?.get(0)?.bidangKerja?.namaBidangKerja
                            ?: "Bidang Kerja Error"
                } catch (e: Throwable) {
                    tvJabatan?.text = "Bidang Kerja Error"
                }
            }
        }

    }
}

class MahasiswaDashboardDiffCallback : DiffUtil.ItemCallback<MahasiswaHistoryDashboardResponse>() {
    override fun areItemsTheSame(
        oldItem: MahasiswaHistoryDashboardResponse,
        newItem: MahasiswaHistoryDashboardResponse
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: MahasiswaHistoryDashboardResponse,
        newItem: MahasiswaHistoryDashboardResponse
    ): Boolean {
        return oldItem.id == newItem.id
    }
}