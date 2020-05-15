package com.example.cariteman.data.model

import com.google.gson.annotations.SerializedName

data class SearchFilter(
    @SerializedName("mahasiswa") var mahasiswa: MutableList<MahasiswaResponse>? = null,
    @SerializedName("tempat_pkl") var tempatPkl: MutableList<TempatPklResponse>? = null
    )

