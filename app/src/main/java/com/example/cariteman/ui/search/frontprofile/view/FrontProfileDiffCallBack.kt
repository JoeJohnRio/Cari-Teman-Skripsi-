package com.example.cariteman.ui.search.frontprofile.view

import androidx.recyclerview.widget.DiffUtil
import com.example.cariteman.data.model.FrontProfileResponse

class FrontProfileDiffCallBack : DiffUtil.ItemCallback<FrontProfileResponse>() {
    override fun areItemsTheSame(
        oldItem: FrontProfileResponse,
        newItem: FrontProfileResponse
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: FrontProfileResponse,
        newItem: FrontProfileResponse
    ): Boolean {
        return oldItem.id == newItem.id
    }
}