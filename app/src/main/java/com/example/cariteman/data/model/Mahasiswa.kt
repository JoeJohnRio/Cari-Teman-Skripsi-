package com.example.cariteman.data.model

import com.google.gson.annotations.SerializedName
import java.io.File
import java.util.*

data class Mahasiswa(
    var userId: Int? = null,
    var id: Int? = null,
    var name: String? = null,
    var password: String? = null,
    var password_confirmation: String? = null,
    var email: String? = null,
    var foto_ktm: File? = null,
    var foto_profil: String? = null,
    var nim: String? = null,
    var jenis_kelamin: Boolean? = null,
    var tahun_mulai: Int? = null,
    var preferensi: Boolean? = null,
    var id_fakultas: Int? = null,
    var id_program_studi: Int? = null,
    var id_keminatan: Int? = null,
    var created_at: Date? = null,
    var updated_at: Date? = null
    )