package com.example.cariteman.data.model

import com.google.gson.annotations.SerializedName

data class MessageSend(
    @SerializedName("id") var id: String? = null,
    @SerializedName("isi_pesan") var isiPesan: String? = null,
    @SerializedName("id_mahasiswa_pengirim") var idMahasiswaPengirim: Int? = null,
    @SerializedName("id_mahasiswa_penerima") var idMahasiswaPenerima: Int? = null,
    @SerializedName("id_kelompok") var idKelompok: Int? = null,
    @SerializedName("created_at") var createdAt: String? = null,
    @SerializedName("updated_at") var updatedAt: String? = null
)

