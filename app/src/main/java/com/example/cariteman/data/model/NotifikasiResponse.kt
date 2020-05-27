package com.example.cariteman.data.model

import com.google.gson.annotations.SerializedName

data class NotifikasiResponse(
    @SerializedName("id") var id: String? = null,
    @SerializedName("notifikasi_type") var notifikasiType: Int? = null,
    @SerializedName("is_read") var isRead: Int? = null,
    @SerializedName("id_mahasiswa_pengirim") var idMahasiswaPengirim: Int? = null,
    @SerializedName("id_mahasiswa_penerima") var idMahasiswaPenerima: Int? = null,
    @SerializedName("id_kelompok") var idKelompok: Int? = null,
    @SerializedName("mahasiswa") var mahasiswa: MahasiswaResponse? = null,
    @SerializedName("created_at") var createdAt: String? = null,
    @SerializedName("updated_at") var updatedAt: String? = null
)

