package com.example.cariteman.data.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class MahasiswaResponse(
    @SerializedName("id") var userId: Int? = null,
    @SerializedName("is_verified") var id: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("email") var email: String? = null,
    @SerializedName("email") var password: String? = null,
    @SerializedName("foto_ktm") var foto_ktm: String? = null,
    @SerializedName("foto_profil") var foto_profil: String? = null,
    @SerializedName("nim") var nim: String? = null,
    @SerializedName("jenis_kelamin") var jenis_kelamin: Boolean? = null,
    @SerializedName("tahun_mulai") var tahun_mulai: Int? = null,
    @SerializedName("preferensi") var preferensi: Boolean? = null,
    @SerializedName("id_fakultas") var id_fakultas: Int? = null,
    @SerializedName("id_program_studi") var id_program_studi: Int? = null,
    @SerializedName("id_keminatan") var id_keminatan: Int? = null,
    @SerializedName("created_at") var created_at: Date? = null,
    @SerializedName("updated_at") var updated_at: Date? = null
    )