package com.example.cariteman.data.model

import com.google.gson.annotations.SerializedName

data class RelationTeman(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("status") var status: Int? = null,
    @SerializedName("is_favorite") var isFavorite: Int? = null,
    @SerializedName("id_mahasiswa_one") var idMahasiswaOne: Int? = null,
    @SerializedName("id_mahasiswa_two") var idMahasiswaTwo: Int? = null,
    @SerializedName("created_at") var createdAt: String? = null,
    @SerializedName("updated_at") var updatedAt: String? = null
)