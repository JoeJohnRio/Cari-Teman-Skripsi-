package com.example.cariteman.ui.message.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cariteman.R
import com.example.cariteman.data.model.MessageKelompok
import com.example.cariteman.data.model.PengalamanLombaOrganisasiResponse
import com.example.cariteman.ui.dashboard.barudilihat.view.*

class MessageKelompokListAdapter() :
    ListAdapter<MessageKelompok, RecyclerView.ViewHolder>(MessageKelompokDiffCallback()) {

    override fun getItemViewType(position: Int): Int {
        if (getItem(position).isPengirim == 1) {
            return MessageVar.isPengirim
        } else if (getItem(position).isPengirim == 0) {
            return MessageVar.notPengirim
        } else {
            return 2
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        MessageVar.isPengirim -> MessageIsUserViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_pesan_is_user,
                parent,
                false
            )
        ) as RecyclerView.ViewHolder

        MessageVar.notPengirim-> MessageIsNotUserViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_pesan_is_not_user,
                parent,
                false
            )
        ) as RecyclerView.ViewHolder

        else -> throw NullPointerException("View holder for type $viewType not found")
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is MessageIsUserViewHolder) {
            val response = getItem(position) as MessageKelompok

            holder.bind(response)
        }else if (holder is MessageIsNotUserViewHolder) {
            val response = getItem(position) as MessageKelompok

            holder.bind(response)
        }
    }
}

object MessageVar {
    const val isPengirim = 1
    const val notPengirim = 0
}