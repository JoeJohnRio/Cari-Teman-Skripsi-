package com.example.cariteman.data.model

import com.google.gson.annotations.SerializedName

data class MessageKelompok(
    @SerializedName("id") var id: String? = null,
    @SerializedName("isi_pesan") var isiPesan: String? = null,
    @SerializedName("isPengirim") var isPengirim: Int? = null,
    @SerializedName("mahasiswa") var mahasiswa: MahasiswaResponse? = null
    )

