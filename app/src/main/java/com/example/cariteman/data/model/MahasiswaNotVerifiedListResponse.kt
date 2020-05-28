package com.example.cariteman.data.model

import com.google.gson.annotations.SerializedName

data class MahasiswaNotVerifiedListResponse(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("nim") var nim: String? = null,
    @SerializedName("email") var email: String? = null,
    @SerializedName("tahun_mulai") var tahunMulai: Int? = null,
    @SerializedName("preferensi") var preferensi: Int? = null,
    @SerializedName("fakultas") var fakultas: String? = null,
    @SerializedName("program_studi") var programStudi: String? = null,
    @SerializedName("keminatan") var keminatan: String? = null,
    @SerializedName("jenis_kelamin") var jenisKelamin: Int? = null,
    @SerializedName("foto_profil") var fotoProfil: String? = null,
    @SerializedName("foto_ktm") var fotoKtm: String? = null,
    @SerializedName("status") var status: Int? = null
    )

