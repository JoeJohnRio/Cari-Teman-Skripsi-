package com.example.cariteman.data.model

import com.google.gson.annotations.SerializedName

data class RelationBidangKerja(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("id_mahasiswa") var idMahasiswa: Int? = null,
    @SerializedName("id_bidang_kerja") var idBidangKerja: Int? = null,
    @SerializedName("id_tempat_pkl") var idTempatPkl: Int? = null,
    @SerializedName("id_pengalaman_lomba") var idPengalamanLomba: Int? = null,
    @SerializedName("id_pengalaman_organisasi") var idPengalamanOrganisasi: Int? = null,
    @SerializedName("created_at") var createdAt: String? = null,
    @SerializedName("updated_at") var updatedAt: String? = null,
    @SerializedName("bidang_kerja") var bidangKerja: BidangKerja? = null)