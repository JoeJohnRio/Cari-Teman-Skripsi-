package com.example.cariteman.data.network

object Endpoints {
    const val login = "login/"
    const val register = "register/"
    const val mahasiswa = "mahasiswa/"

    //fakultas
    const val fakultas = "fakultas/"
    const val programStudi = "fakultas/programstudi/{isVerified}"
    const val keminatan = "fakultas/programstudi/keminatan/{isVerified}"

    //historyDashboard
    const val historyDashboardLomba = "history/lihatprofil/dashboard/lomba"
    const val historyDashboardPkl = "history/lihatprofil/dashboard/pkl"
    const val historyDashboardTempatPkl = "history/lihattempatpkl/dashboard"
    const val historyProfilPkl = "history/lihatprofil/pkl"
    const val historyProfilLomba = "history/lihatprofil/lomba"
    const val historyProfilTempatPkl = "history/lihattempatpkl"

    //dashboardFavorite
    const val favoriteDashboardPkl = "relationteman/favorite/pkl"
    const val favoriteDashboardLomba= "relationteman/favorite/lomba"
    const val favoriteDashboardTempatPkl = "relationtempatpkl/favorite"

    //relasiTeman
    const val toggleFavoriteFriend = "relationteman/favorite/toggle/{id}"

    //relasiTempatPkl
    const val toggleFavoriteTempatPkl = "relationtempatpkl/favorite/toogle/{id}"

    //rekomendasi
    const val totalPengalaman = "rekomendasi/count"
//    const val pengalamanLomba = "pengalaman/lomba"
//    const val pengalamanOrganisasi = "pengalaman/organisasi"
    const val profilInfoOthers = "rekomendasi/profil/{id}"
    const val pengalamanLombaOrganisasiWithRekomendasi = "pengalaman/bothwithrekomendasi/{id}"
    const val pengalamanLombaOrganisasi= "pengalaman/both"
    const val modifyPengalamanLomba= "pengalaman/lomba/modify"
    const val modifyPengalamanOrganisasi= "pengalaman/organisasi/modify"

    //ulasanTempatPkl
    const val showUlasanTempat = "ulasantempatpkl/show"
    const val saveUlasanTempat = "ulasantempatpkl/save"

    //bidangKerjaNama
    const val bidangKerjaSearch = "bidangkerja/search/{namaBidangKerja}"
    const val makeBidangKerja = "bidangkerja/make/{namaBidangKerja}"

    //Kelompok
    const val showKelompok = "kelompok/show"
    const val makeKelompok = "kelompok/make"
    const val showFriendWithNamaOnly = "relationteman/showfriendnama"
    const val addFriendToKelompok = "kelompok/addfriend"
    const val showAnggotaKelompok = "kelompok/anggotakelompok/{id_kelompok}"
    const val showFriendNotAdded = "kelompok/showfriendnotadded/{id_kelompok}"
}