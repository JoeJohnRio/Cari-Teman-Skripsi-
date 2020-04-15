package com.example.cariteman.ui.dashboard.barudilihat.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cariteman.R
import com.example.cariteman.data.model.MahasiswaHistoryDashboardResponse
import com.example.cariteman.data.model.PengalamanLombaOrganisasiResponse
import com.example.cariteman.ui.dashboard.presenter.ProfilePresenter
import com.example.cariteman.ui.profile.view.ProfileMVPView

class PengalamanListAdapter(presenter: ProfilePresenter<ProfileMVPView>) :
    ListAdapter<PengalamanLombaOrganisasiResponse, RecyclerView.ViewHolder>(ProfileDiffCallBack()) {
    var presenterAdapter = presenter

    override fun getItemViewType(position: Int): Int {
        if (getItem(position).namaOrganisasi != null) {
            return 0
        } else if (getItem(position).namaKompetisi!= null) {
            return 1
        } else {
            return 2
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        ProfilePengalaman.Organisasi -> ProfilePengalamanOrganisasiViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_pengalaman_profile ,
                parent,
                false
            )
        ) as RecyclerView.ViewHolder

        ProfilePengalaman.LOMBA -> ProfilePengalamanLombaViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_pengalaman_profile ,
                parent,
                false
            )
        ) as RecyclerView.ViewHolder

        else -> throw NullPointerException("View holder for type $viewType not found")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ProfilePengalamanOrganisasiViewHolder) {
            val response = getItem(position) as PengalamanLombaOrganisasiResponse

            holder.bindHistory(response, presenterAdapter)
        }else if (holder is ProfilePengalamanLombaViewHolder) {
            val response = getItem(position) as PengalamanLombaOrganisasiResponse

            holder.bindHistory(response, presenterAdapter)
        }
    }
}

object ProfilePengalaman {
    const val Organisasi = 0
    const val LOMBA = 1
    const val TEMPATPKL = 2
    const val KOSONG = 3
}