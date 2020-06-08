package com.example.cariteman.data.model

import com.google.gson.annotations.SerializedName

data class TempatPklSearchFilter(
    @SerializedName("keyword") var keyword: String? = null,
    @SerializedName("id_lokasi_pkl") var idLokasiPkl: Int? = null,
    @SerializedName("id_bidang_kerja") var idBidangKerja: Int? = null
    )

