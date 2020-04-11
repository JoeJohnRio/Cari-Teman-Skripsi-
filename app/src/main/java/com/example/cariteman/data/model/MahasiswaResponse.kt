package com.example.cariteman.data.model

import com.google.gson.annotations.SerializedName

data class MahasiswaResponse(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("is_verified") var isVerified: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("email") var email: String? = null,
//    @SerializedName("password") var password: String? = null,
    @SerializedName("foto_ktm") var foto_ktm: String? = null,
    @SerializedName("foto_profil") var foto_profil: String? = null,
    @SerializedName("nim") var nim: String? = null,
    @SerializedName("jenis_kelamin") var jenis_kelamin: Int? = null,
    @SerializedName("tahun_mulai") var tahun_mulai: Int? = null,
    @SerializedName("preferensi") var preferensi: Int? = null,
    @SerializedName("id_fakultas") var id_fakultas: Int? = null,
    @SerializedName("id_program_studi") var id_program_studi: Int? = null,
    @SerializedName("id_keminatan") var id_keminatan: Int? = null,
    @SerializedName("created_at") var created_at: String? = null,
    @SerializedName("updated_at") var updated_at: String? = null,
//    @SerializedName("relation_teman") var relationTeman: List<RelationTeman>? = null,
    @SerializedName("count_rekomendasi") var countRekomendasi: MutableList<CountRekomendasi>? = null,
    @SerializedName("pengalaman_lomba") var pengalamanLomba: MutableList<PengalamanLomba>? = null,
    @SerializedName("pengalaman_organisasi") var pengalamanOrganisasi: MutableList<PengalamanOrganisasi>? = null,
    @SerializedName("relation_teman") var relationTeman: RelationTeman? = null
    )

