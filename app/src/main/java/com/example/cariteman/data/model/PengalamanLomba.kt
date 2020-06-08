package com.example.cariteman.data.model

import com.google.gson.annotations.SerializedName

data class PengalamanLomba(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("nama_kompetisi") var namaKompetisi: String? = null,
    @SerializedName("deskripsi") var deskripsi: String? = null,
    @SerializedName("gambar") var gambar: String? = null,
    @SerializedName("id_mahasiswa") var idMahasiswa: Int? = null,
    @SerializedName("tanggal") var tanggal: String? = null,
    @SerializedName("created_at") var createdAt: String? = null,
    @SerializedName("updated_at") var updatedAt: String? = null,
    @SerializedName("bidang_kerja_id") var bidangKerjaId: Int? = null,
    @SerializedName("bidang_kerja_nama") var bidangKerjaNama: String? = null,
    @SerializedName("relation_bidang_kerja") var relationBidangKerja: RelationBidangKerja? = null)

data class PengalamanLombaOrganisasiResponse(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("nama_kompetisi") var namaKompetisi: String? = null,
    @SerializedName("nama_organisasi") var namaOrganisasi: String? = null,
    @SerializedName("deskripsi") var deskripsi: String? = null,
    @SerializedName("gambar") var gambar: String? = null,
    @SerializedName("id_mahasiswa") var idMahasiswa: Int? = null,
    @SerializedName("id_pengalaman_lomba") var idPengalamanLomba: Int? = null,
    @SerializedName("id_pengalaman_organisasi") var idPengalamanOrganisasi: Int? = null,
    @SerializedName("id_bidang_kerja") var idBidangKerja: Int? = null,
    @SerializedName("tanggal") var tanggal: String? = null,
    @SerializedName("tanggal_mulai") var tanggalMulai: String? = null,
    @SerializedName("tanggal_selesai") var tanggalSelesai: String? = null,
    @SerializedName("created_at") var createdAt: String? = null,
    @SerializedName("updated_at") var updatedAt: String? = null,
    @SerializedName("relation_bidang_kerja") var relationBidangKerja: RelationBidangKerja? = null)