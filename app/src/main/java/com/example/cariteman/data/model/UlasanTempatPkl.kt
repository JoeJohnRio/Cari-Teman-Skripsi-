package com.example.cariteman.data.model

import com.google.gson.annotations.SerializedName

data class UlasanTempatPkl(
    @SerializedName("id") var id: Int,
    @SerializedName("ulasan") var ulasan: String,
    @SerializedName("id_tempat_pkl") var idTempatPkl: Int,
    @SerializedName("id_pengirim") var idPengirim: Int,
    @SerializedName("created_at") var createdAt: String,
    @SerializedName("updated_at") var updatedAt: String
)