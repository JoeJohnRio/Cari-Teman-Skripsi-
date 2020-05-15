package com.example.cariteman.ui.bidangkerja.view

import androidx.recyclerview.widget.DiffUtil
import com.example.cariteman.data.model.BidangKerja
import com.example.cariteman.data.model.SkillHobi

class SkillHobiDiffCallback : DiffUtil.ItemCallback<SkillHobi>() {
    override fun areItemsTheSame(oldItem: SkillHobi, newItem: SkillHobi): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: SkillHobi, newItem: SkillHobi): Boolean {
        return oldItem.id == newItem.id
    }
}