package com.example.cariteman.ui.message.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cariteman.R
import com.example.cariteman.data.model.MessageKelompok
import com.example.cariteman.data.model.NotifikasiResponse
import com.example.cariteman.data.model.PengalamanLombaOrganisasiResponse
import com.example.cariteman.ui.dashboard.barudilihat.view.*
import com.example.cariteman.ui.notifikasi.view.NotifikasiActivity

class NotifikasiListAdapter(var activity: NotifikasiActivity) :
    ListAdapter<NotifikasiResponse, RecyclerView.ViewHolder>(NotifikasiDiffCallback()) {

    override fun getItemViewType(position: Int): Int {
        if (getItem(position).notifikasiType == 1) {
            return NOTIFIKASITYPE.MEMINTA_PERTEMANAN
        } else if (getItem(position).notifikasiType == 2) {
            return NOTIFIKASITYPE.MENERIMA_PERTEMANAN
        } else if (getItem(position).notifikasiType == 3) {
            return NOTIFIKASITYPE.MENOLAK_PERTEMANAN
        } else if (getItem(position).notifikasiType == 4) {
            return NOTIFIKASITYPE.MEMINTA_BERGABUNG_PADA_TIM
        } else if (getItem(position).notifikasiType == 5) {
            return NOTIFIKASITYPE.MENERIMA_PERMINTAAN_BERGABUNG_TIM
        } else if (getItem(position).notifikasiType == 6) {
            return NOTIFIKASITYPE.MENOLAK_PERMINTAAN_BERGABUNG_TIM
        } else if (getItem(position).notifikasiType == 7) {
            return NOTIFIKASITYPE.MEREKOMENDASIKAN_PROFIL
        } else if (getItem(position).notifikasiType == 8) {
            return NOTIFIKASITYPE.MENGIRIM_PESAN
        } else {
            return 8
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        NOTIFIKASITYPE.MEMINTA_PERTEMANAN -> NotifikasiMemintaPertemananViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_notifikasi,
                parent,
                false
            )
        ) as RecyclerView.ViewHolder

        NOTIFIKASITYPE.MENERIMA_PERTEMANAN -> NotifikasiMenerimaPertemananViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_notifikasi,
                parent,
                false
            )
        ) as RecyclerView.ViewHolder

        NOTIFIKASITYPE.MENOLAK_PERTEMANAN -> NotifikasiMenolakPertemananViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_notifikasi,
                parent,
                false
            )
        ) as RecyclerView.ViewHolder

        NOTIFIKASITYPE.MEMINTA_BERGABUNG_PADA_TIM -> NotifikasiMemintaBergabungTimViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_notifikasi,
                parent,
                false
            )
        ) as RecyclerView.ViewHolder

        NOTIFIKASITYPE.MENERIMA_PERMINTAAN_BERGABUNG_TIM -> NotifikasiMenerimaBergabungTimViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_notifikasi,
                parent,
                false
            )
        ) as RecyclerView.ViewHolder

        NOTIFIKASITYPE.MENOLAK_PERMINTAAN_BERGABUNG_TIM -> NotifikasiMenolakBergabungTimViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_notifikasi,
                parent,
                false
            )
        ) as RecyclerView.ViewHolder

        else -> throw NullPointerException("View holder for type $viewType not found")
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is NotifikasiMemintaPertemananViewHolder) {
            val response = getItem(position) as NotifikasiResponse

            holder.bind(response)
        }else if (holder is NotifikasiMenerimaPertemananViewHolder) {
            val response = getItem(position) as NotifikasiResponse

            holder.bind(response)
        }else if (holder is NotifikasiMenolakPertemananViewHolder) {
            val response = getItem(position) as NotifikasiResponse

            holder.bind(response)
        }else if (holder is NotifikasiMemintaBergabungTimViewHolder) {
            val response = getItem(position) as NotifikasiResponse

            holder.bind(response, activity)
        }else if (holder is NotifikasiMenerimaBergabungTimViewHolder) {
            val response = getItem(position) as NotifikasiResponse

            holder.bind(response)
        }else if (holder is NotifikasiMenolakBergabungTimViewHolder) {
            val response = getItem(position) as NotifikasiResponse

            holder.bind(response)
        }
    }
}

object NOTIFIKASITYPE {
    const val MEMINTA_PERTEMANAN = 1
    const val MENERIMA_PERTEMANAN = 2
    const val MENOLAK_PERTEMANAN = 3
    const val MEMINTA_BERGABUNG_PADA_TIM = 4
    const val MENERIMA_PERMINTAAN_BERGABUNG_TIM = 5
    const val MENOLAK_PERMINTAAN_BERGABUNG_TIM = 6
    const val MEREKOMENDASIKAN_PROFIL = 7
    const val MENGIRIM_PESAN = 8
}