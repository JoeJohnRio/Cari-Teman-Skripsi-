package com.example.cariteman.data.model

import com.google.gson.annotations.SerializedName

data class Rekomendasi(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("jumlah_rating") var jumlahRating: Int? = null,
    @SerializedName("deskripsi") var deskripsi: String? = null,
    @SerializedName("id_pengirim") var idPengirim: Int? = null,
    @SerializedName("id_penerima") var idPenerima: Int? = null,
    @SerializedName("created_at") var createdAt: String? = null,
    @SerializedName("updated_at") var updatedAt: String? = null,
    @SerializedName("data_mahasiswa") var dataMahasiswa: MahasiswaResponse? = null
)