package com.example.cariteman.data.model

import com.google.gson.annotations.SerializedName

data class UlasanTempatPklProfile(
    @SerializedName("id_tempat_pkl") var idTempatPkl: Int? = null,
    @SerializedName("nama_pengirim") var namaPengirim: String? = null,
    @SerializedName("gambar_pengirim") var gambarPengirim: String? = null,
    @SerializedName("isi_ulasan") var isiUlasan: String? = null
)