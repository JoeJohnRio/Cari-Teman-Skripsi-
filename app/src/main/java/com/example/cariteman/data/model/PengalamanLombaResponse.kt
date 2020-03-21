package com.example.cariteman.data.model

import com.google.gson.annotations.SerializedName

data class PengalamanLombaResponse(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("nama_kompetisi") var namaKompetisi: String? = null,
    @SerializedName("deskripsi") var deskripsi: String? = null,
    @SerializedName("id_mahasiswa") var idMahasiswa: Int? = null,
    @SerializedName("tanggal") var tanggal: String? = null,
    @SerializedName("created_at") var createdAt: String? = null,
    @SerializedName("updated_at") var updatedAt: String? = null,
    @SerializedName("relation_bidang_kerja") var relationBidangKerja: List<PengalamanLombaResponseNested1>? = null)

data class PengalamanLombaResponseNested1(
    @SerializedName("bidang_kerja") var bidangKerja: PengalamanLombaResponseNested2? = null)


data class PengalamanLombaResponseNested2(
    @SerializedName("nama_bidang_kerja") var createdAt: String? = null)