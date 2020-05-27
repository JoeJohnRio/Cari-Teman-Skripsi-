package com.example.cariteman.ui.dashboard.barudilihat.view

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cariteman.R
import com.example.cariteman.data.model.MessageKelompok
import kotlinx.android.extensions.LayoutContainer


class MessageIsUserViewHolder(override val containerView: View) :
    RecyclerView.ViewHolder(containerView),
    LayoutContainer {

    private var tvIsiPesan: TextView? = null

    init {
        tvIsiPesan = itemView.findViewById(R.id.tv_isi_pesan_is_user)
    }

    fun bind(
        response: MessageKelompok
    ) {
        tvIsiPesan?.setText(response.isiPesan)
    }
}