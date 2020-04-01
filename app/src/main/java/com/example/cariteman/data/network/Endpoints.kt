package com.example.cariteman.data.network

object Endpoints {
    const val login = "login/"
    const val register = "register/"
    const val mahasiswa = "mahasiswa/"

    //fakultas
    const val fakultas = "fakultas/"
    const val programStudi = "fakultas/programstudi/{id}"
    const val keminatan = "fakultas/programstudi/keminatan/{id}"

    //historyDashboard
    const val historyDashboardLomba = "history/lihatprofil/dashboard/lomba"
    const val historyDashboardPkl = "history/lihatprofil/dashboard/pkl"
    const val historyDashboardTempatPkl = "history/lihattempatpkl/dashboard"
    const val historyProfilPkl = "history/lihatprofil/pkl"
    const val historyProfilLomba = "history/lihatprofil/lomba"
    const val historyProfilTempatPkl = "history/lihattempatpkl"

    //rekomendasi
    const val totalPengalaman = "rekomendasi/count"
    const val pengalamanLomba = "pengalaman/lomba"
    const val pengalamanOrganisasi = "pengalaman/organisasi"

    //ulasanTempatPkl
    const val showUlasanTempat = "ulasantempatpkl/show"
    const val saveUlasanTempat = "ulasantempatpkl/save"
}