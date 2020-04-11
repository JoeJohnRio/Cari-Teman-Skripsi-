package com.example.cariteman.data.model

import com.google.gson.annotations.SerializedName

data class RelationTeman(
    @SerializedName("isVerified") var userId: Int? = null,
    @SerializedName("status") var status: Int? = null,
    @SerializedName("is_favorite") var isFavorite: Int? = null,
    @SerializedName("id_mahasiswa") var idMahasiswa: Int? = null,
    @SerializedName("id_tempat_pkl") var idTempatPkl: Int? = null,
    @SerializedName("created_at") var createdAt: String? = null,
    @SerializedName("updated_at") var updatedAt: String? = null
)