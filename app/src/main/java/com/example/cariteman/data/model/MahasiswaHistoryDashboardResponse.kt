package com.example.cariteman.data.model

import com.google.gson.annotations.SerializedName

data class MahasiswaHistoryDashboardResponse(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("id_mahasiswa") var idMahasiswa: Int? = null,
    @SerializedName("id_tempat_pkl") var idTempatPkl: Int? = null,
    @SerializedName("tempat_pkl") var tempatPkl: TempatPklResponse? = null,
    @SerializedName("id_mahasiswa_one") var idMahasiswaOne: Int? = null,
    @SerializedName("id_mahasiswa_two") var idMahasiswaTwo: Int? = null,
    @SerializedName("created_at") var createdAt: String? = null,
    @SerializedName("updated_at") var updatedAt: String? = null,
    @SerializedName("mahasiswa_two_lomba") var mahasiswaTwoLomba: MahasiswaResponseNotSerialized? = null,
    @SerializedName("mahasiswa_two_pkl") var mahasiswaTwoPkl: MahasiswaResponseNotSerialized? = null
)

class MahasiswaResponseNotSerialized(
    @SerializedName("id") var userId: Int? = null,
    @SerializedName("is_verified") var id: Int? = null,
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
    @SerializedName("count_pengalaman") var countPengalaman: List<CountPengalaman>? = null,
    @SerializedName("pengalaman_lomba") var pengalamanLomba: List<PengalamanLomba>? = null,
    @SerializedName("pengalaman_organisasi") var pengalamanOrganisasi: List<PengalamanOrganisasi>? = null
)

data class CountPengalaman(
    @SerializedName("id") var userId: Int? = null,
    @SerializedName("jumlah_rating") var jumlahRating: Int? = null,
    @SerializedName("deskripsi") var deskripsi: String? = null,
    @SerializedName("is_hidden") var isHidden: Int? = null,
    @SerializedName("id_pengirim") var idPengirim: Int? = null,
    @SerializedName("id_penerima") var idPenerima: String? = null,
    @SerializedName("created_at") var createdAt: Int? = null,
    @SerializedName("updated_at") var updatedAt: String? = null
)

data class PengalamanLomba(
    @SerializedName("id") var userId: Int? = null,
    @SerializedName("nama_kompetisi") var namaKompetisi: String? = null,
    @SerializedName("deskripsi") var deskripsi: String? = null,
    @SerializedName("gambar") var gambar: String? = null,
    @SerializedName("id_mahasiswa") var idMahasiswa: Int? = null,
    @SerializedName("tanggal") var tanggal: String? = null,
    @SerializedName("created_at") var createdAt: Int? = null,
    @SerializedName("updated_at") var updatedAt: String? = null
)

data class PengalamanOrganisasi(
    @SerializedName("id") var userId: Int? = null,
    @SerializedName("nama_organisasi") var namaOrganisasi: String? = null,
    @SerializedName("deskripsi") var deskripsi: String? = null,
    @SerializedName("gambar") var gambar: String? = null,
    @SerializedName("tanggal_mulai") var tanggalMulai: String? = null,
    @SerializedName("tanggal_selesai") var tanggalSelesai: String? = null,
    @SerializedName("id_mahasiswa") var idMahasiswa: Int? = null,
    @SerializedName("id_bidang_kerja") var idBidangKerja: Int? = null,
    @SerializedName("created_at") var createdAt: String? = null,
    @SerializedName("updated_at") var updatedAt: String? = null
)

data class TempatPklResponse(
    @SerializedName("id") var userId: Int? = null,
    @SerializedName("nama_perusahaan") var namaPerusahaan: String? = null,
    @SerializedName("id_lokasi_pkl") var idLokasiPkl: Int? = null,
    @SerializedName("id_bidang_kerja") var idBidangKerja: Int? = null,
    @SerializedName("gambar") var gambar: String? = null,
    @SerializedName("bidang_kerja") var bidangKerja: BidangKerjaResponse? = null,
    @SerializedName("lokasi_pkl") var lokasiPkl: LokasiPklResponse? = null,
    @SerializedName("count_ulasan_tempat_pkl") var countUlasanTempatPkl: List<UlasanTempatPkl>? = null,
    @SerializedName("created_at") var createdAt: String? = null,
    @SerializedName("updated_at") var updatedAt: String? = null
)

data class BidangKerjaResponse(
    @SerializedName("id") var userId: Int? = null,
    @SerializedName("nama_bidang_kerja") var namaBidangKerja: String? = null,
    @SerializedName("created_at") var createdAt: String? = null,
    @SerializedName("updated_at") var updatedAt: String? = null
)

data class LokasiPklResponse(
    @SerializedName("id") var userId: Int? = null,
    @SerializedName("nama_kota") var namaKota: String? = null,
    @SerializedName("created_at") var createdAt: String? = null,
    @SerializedName("updated_at") var updatedAt: String? = null
)