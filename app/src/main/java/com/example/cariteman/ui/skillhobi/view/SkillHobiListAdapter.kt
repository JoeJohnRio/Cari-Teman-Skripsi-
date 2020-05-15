package com.example.cariteman.ui.pengalaman.pengalamanhome.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cariteman.R
import com.example.cariteman.data.model.SkillHobi
import com.example.cariteman.ui.bidangkerja.view.SkillHobiDiffCallback
import com.example.cariteman.ui.bidangkerja.view.SkillHobiViewHolder

class SkillHobiListAdapter(view: SkillHobiFragment) :
    ListAdapter<SkillHobi, RecyclerView.ViewHolder>(SkillHobiDiffCallback()) {
    var skillHobiFragment = view

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SkillHobiViewHolder {
        return SkillHobiViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_skill_hobi,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val response = getItem(position) as SkillHobi
        holder as SkillHobiViewHolder
        holder.bind(response, skillHobiFragment)
    }
}
