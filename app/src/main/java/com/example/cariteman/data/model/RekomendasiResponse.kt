package com.example.cariteman.data.model

import com.google.gson.annotations.SerializedName

data class RekomendasiResponse(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("type_of_recommendation") var typeOfRecommendation: Int? = null,
    @SerializedName("is_favorite") var isFavorite: Int? = null,
    @SerializedName("gambar") var gambar: String? = null,
    @SerializedName("foto_profil") var fotoProfile: String? = null,
    @SerializedName("nama_perusahaan") var namaPerusahaan: String? = null,
    @SerializedName("jumlah_rekomendasi") var jumlahRekomendasi: Int? = null,
    @SerializedName("jumlah_pengalaman") var jumlahPengalaman: Int? = null,
    @SerializedName("id_bidang_kerja") var idBidangKerja: Int? = null,
    @SerializedName("nama_bidang_kerja") var namaBidangKerja: String? = null,
    @SerializedName("nama_kota") var namaKota: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("pengalaman_lomba") var pengalamanLomba: MutableList<PengalamanLomba>? = null,
    @SerializedName("pengalaman_organisasi") var pengalamanOrganisasi: MutableList<PengalamanOrganisasi>? = null,
    @SerializedName("jumlah_tim") var jumlahTim: Int? = null,
    @SerializedName("recommendation_scale") var recommendationScale: Int? = null
)











