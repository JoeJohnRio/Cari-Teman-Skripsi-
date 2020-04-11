package com.example.cariteman.data.model

import com.google.gson.annotations.SerializedName

data class BidangKerja(
    @SerializedName("isVerified") var id: Int? = null,
    @SerializedName("nama_bidang_kerja") var namaBidangKerja: String? = null,
    @SerializedName("created_at") var createdAt: String? = null,
    @SerializedName("updated_at") var updatedAt: String? = null
)