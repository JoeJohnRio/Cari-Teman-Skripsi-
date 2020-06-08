package com.example.cariteman.data.model

import com.google.gson.annotations.SerializedName

data class FrontProfileResponse(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("id_mahasiswa") var idMahasiswa: Int? = null,
    @SerializedName("id_tempat_pkl") var idTempatPkl: Int? = null,
    @SerializedName("id_mahasiswa_one") var idMahasiswaOne: Int? = null,
    @SerializedName("id_mahasiswa_two") var idMahasiswaTwo: Int? = null,
    @SerializedName("created_at") var createdAt: String? = null,
    @SerializedName("updated_at") var updatedAt: String? = null,
    @SerializedName("tempat_pkl") var tempatPkl: RekomendasiResponse? = null,
    @SerializedName("mahasiswa_two_lomba") var mahasiswaTwoLomba: MahasiswaResponse? = null,
    @SerializedName("mahasiswa_two_pkl") var mahasiswaTwoPkl: MahasiswaResponse? = null
)











