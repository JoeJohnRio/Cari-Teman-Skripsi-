package com.example.cariteman.data.model

import com.google.gson.annotations.SerializedName

data class ProfilInfoOthers(
    @SerializedName("name") var name: String? = null,
    @SerializedName("tahun_mulai") var tahunMulai: Int? = null,
    @SerializedName("foto_profil") var fotoProfil: String? = null,
    @SerializedName("status") var status: Int? = null,
    @SerializedName("is_favorite") var isFavorite: Int? = null,
    @SerializedName("jumlah_teman") var jumlahTeman: Int? = null,
    @SerializedName("jumlah_rekomendasi") var jumlahRekomendasi: Int? = null,
    @SerializedName("jumlah_kelompok") var jumlahKelompok: Int? = null,
    @SerializedName("fakultas") var fakultas: String? = null,
    @SerializedName("program_studi") var programStudi: String? = null,
    @SerializedName("keminatan") var keminatan: String? = null)