package com.example.cariteman.data.model

import com.google.gson.annotations.SerializedName

data class TempatPklResponse(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("nama_perusahaan") var namaPerusahaan: String? = null,
    @SerializedName("gambar") var gambar: String? = null,
    @SerializedName("id_lokasi_pkl") var idLokasiPkl: Int? = null,
    @SerializedName("id_relation_bidang_kerja") var idRelationBidangKerja: Int? = null,
    @SerializedName("relation_tempat_pkl") var relationTempatPkl: RelationTempatPkl? = null,
    @SerializedName("lokasi_pkl") var lokasiPkl: LokasiPklResponse? = null,
    @SerializedName("relation_bidang_kerja") var relationBidangKerja: MutableList<RelationBidangKerja>? = null,
    @SerializedName("count_ulasan_tempat_pkl") var countUlasanTempatPkl: List<UlasanTempatPkl>? = null,
    @SerializedName("created_at") var createdAt: String? = null,
    @SerializedName("updated_at") var updatedAt: String? = null
)