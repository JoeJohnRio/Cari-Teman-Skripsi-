package com.example.cariteman.data.model

import com.google.gson.annotations.SerializedName

data class AnggotaKelompok(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("foto_profil") var fotoProfil: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("nama_fakultas") var namaFakultas: String? = null
)