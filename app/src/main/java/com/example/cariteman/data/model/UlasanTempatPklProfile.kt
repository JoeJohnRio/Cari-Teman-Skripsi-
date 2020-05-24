package com.example.cariteman.data.model

import com.google.gson.annotations.SerializedName

data class UlasanTempatPklProfile(
    @SerializedName("nama_pengirim") var namaPengirim: String,
    @SerializedName("gambar_pengirim") var gambarPengirim: String,
    @SerializedName("isi_ulasan") var isiUlasan: String
)