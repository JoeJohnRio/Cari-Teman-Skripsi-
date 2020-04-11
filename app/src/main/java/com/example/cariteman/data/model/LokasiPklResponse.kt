package com.example.cariteman.data.model

import com.google.gson.annotations.SerializedName

data class LokasiPklResponse(
    @SerializedName("isVerified") var userId: Int? = null,
    @SerializedName("nama_kota") var namaKota: String? = null,
    @SerializedName("created_at") var createdAt: String? = null,
    @SerializedName("updated_at") var updatedAt: String? = null
)