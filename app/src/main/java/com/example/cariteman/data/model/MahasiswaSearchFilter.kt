package com.example.cariteman.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class MahasiswaSearchFilter(
    @SerializedName("keyword") var keyword: String? = null,
    @SerializedName("preferensi") var preferensi: Int? = null,
    @SerializedName("jenis_kelamin") var jenisKelamin: Int? = null,
    @SerializedName("id_fakultas") var idFakultas: Int? = null,
    @SerializedName("id_program_studi") var idProgramStudi: Int? = null,
    @SerializedName("id_keminatan") var idKeminatan: Int? = null,
    @SerializedName("tahun_mulai") var tahunMulai: Int? = null,
    @SerializedName("id_skillhobi") var idSkillHobi: Int? = null
    )


