package com.example.cariteman.ui.bidangkerja.view

import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cariteman.R
import com.example.cariteman.data.model.BidangKerja
import com.example.cariteman.data.model.SkillHobi
import com.example.cariteman.ui.pengalaman.pengalamanhome.view.BidangKerjaFragment
import com.example.cariteman.ui.pengalaman.pengalamanhome.view.SkillHobiFragment
import kotlinx.android.extensions.LayoutContainer

class SkillHobiViewHolder(override val containerView: View) :
    RecyclerView.ViewHolder(containerView),
    LayoutContainer {

    private var url: String = "https://tanzolymp.com/images/default-non-user-no-photo-1-300x300.jpg"

    private var tvNamaSkillHobi: TextView? = null
    private var cbCheckSkill: CheckBox? = null

    init {
        tvNamaSkillHobi= itemView.findViewById(R.id.tv_nama_skill_hobi)
        cbCheckSkill = itemView.findViewById(R.id.cb_check_skill)
    }

    fun bind(
        response: SkillHobi, view: SkillHobiFragment
    ) {
        tvNamaSkillHobi?.text = response.namaSkillhobi

        if (view.isFirst) {
            cbCheckSkill?.isChecked = false
        }

        cbCheckSkill?.setOnClickListener {
            if (cbCheckSkill?.isChecked!!){
                if (view.isFirst){
                    cbCheckSkill?.isChecked = true
                    view.skillHobiNama = response.namaSkillhobi!!
                    view.skillHobiId = response.id!!
                    view.isFirst=false
                }else{
                    if (view.skillHobiNama==""){
                        cbCheckSkill?.isChecked = true
                        view.skillHobiNama = response.namaSkillhobi!!
                        view.skillHobiId = response.id!!
                    }
                    if (view.skillHobiNama != response.namaSkillhobi){
                        view.showMessageToast("Anda hanya dapat memilih satu skill atau hobi")
                        cbCheckSkill?.isChecked = false
                    }
                }
            }else{
                view.skillHobiNama = ""
                view.skillHobiId = 0
                cbCheckSkill?.isChecked = false
            }
        }
    }
}
