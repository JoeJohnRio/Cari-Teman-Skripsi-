package com.example.cariteman.ui.dashboard.barudilihat.view

import androidx.recyclerview.widget.DiffUtil
import com.example.cariteman.data.model.MahasiswaHistoryDashboardResponse
import com.example.cariteman.data.model.PengalamanLombaOrganisasi
import com.example.cariteman.data.model.PengalamanLombaOrganisasiResponse
import com.example.cariteman.data.model.ProfilePengalamanRekomendasi

class ProfileDiffCallBack : DiffUtil.ItemCallback<PengalamanLombaOrganisasiResponse>() {
    override fun areItemsTheSame(oldItem: PengalamanLombaOrganisasiResponse, newItem: PengalamanLombaOrganisasiResponse): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PengalamanLombaOrganisasiResponse, newItem: PengalamanLombaOrganisasiResponse): Boolean {
        return oldItem.id == newItem.id
    }
}