package com.example.cariteman.data.model

import com.google.gson.annotations.SerializedName

data class RelationKelompok(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("id_kelompok") var idKelompok: Int? = null,
    @SerializedName("id_mahasiswa") var idMahasiswa: Int? = null,
    @SerializedName("status") var status: Int? = null,
    @SerializedName("created_at") var createdAt: String? = null,
    @SerializedName("updated_at") var updatedAt: String? = null,
    @SerializedName("kelompok") var kelompok: Kelompok? = null
)