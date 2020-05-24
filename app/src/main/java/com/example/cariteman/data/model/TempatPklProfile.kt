package com.example.cariteman.data.model

import com.google.gson.annotations.SerializedName

data class TempatPklProfile(
    @SerializedName("nama_perusahaan") var namaPerusahaan: String,
    @SerializedName("gambar") var gambar: String,
    @SerializedName("phone_number") var phoneNumber: Int,
    @SerializedName("lokasi_pkl") var lokasiPkl: String,
    @SerializedName("jumlah_ulasan") var jumlahUlasan: Int
)