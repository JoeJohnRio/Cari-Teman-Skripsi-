package com.example.cariteman.data.model

import com.google.gson.annotations.SerializedName

data class Kelompok(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("nama_kelompok") var namaKelompok: String? = null,
    @SerializedName("jumlah_anggota") var jumlahAnggota: Int? = null,
    @SerializedName("tipe_kelompok") var tipeKelompok: Int? = null,
    @SerializedName("foto_kelompok") var fotoKelompok: String? = null,
    @SerializedName("created_at") var createdAt: String? = null,
    @SerializedName("updated_at") var updatedAt: String? = null,
    @SerializedName("calon_anggotas") var calonAnggotas: MutableList<RelationKelompok>? = null,
    @SerializedName("id_kelompok") var idKelompok: Int? = null
)