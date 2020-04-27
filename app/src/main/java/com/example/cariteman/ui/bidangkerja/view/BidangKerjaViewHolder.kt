package com.example.cariteman.ui.bidangkerja.view

import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cariteman.R
import com.example.cariteman.data.model.BidangKerja
import com.example.cariteman.ui.pengalaman.pengalamanhome.view.BidangKerjaFragment
import kotlinx.android.extensions.LayoutContainer

class BidangKerjaViewHolder(override val containerView: View) :
    RecyclerView.ViewHolder(containerView),
    LayoutContainer {

    private var url: String = "https://tanzolymp.com/images/default-non-user-no-photo-1-300x300.jpg"

    private var tvNamaTeman: TextView? = null
    private var cbCheckTeman: CheckBox? = null

    init {
        tvNamaTeman= itemView.findViewById(R.id.tv_nama_teman)
        cbCheckTeman = itemView.findViewById(R.id.cb_check_teman)
    }

    fun bindBidangKerja(
        response: BidangKerja, view: BidangKerjaFragment
    ) {
        tvNamaTeman?.text = response.namaBidangKerja

        if (view.isFirst) {
            cbCheckTeman?.isChecked = false
        }

        cbCheckTeman?.setOnClickListener {
            if (cbCheckTeman?.isChecked!!){
                if (view.isFirst){
                    cbCheckTeman?.isChecked = true
                    view.bidangKerjaNama = response.namaBidangKerja!!
                    view.bidangKerjaId = response.id!!
                    view.isFirst=false
                }else{
                    if (view.bidangKerjaNama==""){
                        cbCheckTeman?.isChecked = true
                        view.bidangKerjaNama = response.namaBidangKerja!!
                        view.bidangKerjaId = response.id!!
                    }
                    if (view.bidangKerjaNama != response.namaBidangKerja){
                        view.showMessageToast("Anda hanya dapat memilih satu bidang kerja")
                        cbCheckTeman?.isChecked = false
                    }
                }
            }else{
                view.bidangKerjaNama = ""
                view.bidangKerjaId = 0
                cbCheckTeman?.isChecked = false
            }
        }
    }
}
