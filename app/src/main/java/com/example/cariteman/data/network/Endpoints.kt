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

    //relasiTeman
    const val toggleFavoriteFriend = "relationteman/favorite/toggle/{isVerified}"

    //rekomendasi
    const val totalPengalaman = "rekomendasi/count"
    const val pengalamanLomba = "pengalaman/lomba"
    const val pengalamanOrganisasi = "pengalaman/organisasi"

    //ulasanTempatPkl
    const val showUlasanTempat = "ulasantempatpkl/show"
    const val saveUlasanTempat = "ulasantempatpkl/save"
}