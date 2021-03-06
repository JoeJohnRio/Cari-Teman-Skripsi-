package com.example.cariteman.data.network

object Endpoints {
    const val login = "login/"
    const val loginAdmin = "admin/login"
    const val register = "register/"
    const val mahasiswa = "mahasiswa/"
    const val checkUserExist = "mahasiswa/checkexist"

    //fakultas
    const val fakultas = "fakultas/"
    const val programStudi = "fakultas/programstudi/{id}"
    const val keminatan = "fakultas/programstudi/keminatan/{id}"
    const val lokasiPkl = "lokasipkl"

    //historyDashboard
    const val historyDashboardLomba = "history/lihatprofil/dashboard/lomba"
    const val historyDashboardPkl = "history/lihatprofil/dashboard/pkl"
    const val historyDashboardTempatPkl = "history/lihattempatpkl/dashboard"
    const val historyProfilPkl = "history/lihatprofil/pkl"
    const val historyProfilLomba = "history/lihatprofil/lomba"
    const val historyProfilTempatPkl = "history/lihattempatpkl"
    const val addHistoryLihatProfil = "history/lihatprofil/save/{id}"

    //dashboardFavorite
    const val favoriteDashboardPkl = "relationteman/favorite/pkl"
    const val favoriteDashboardLomba= "relationteman/favorite/lomba"
    const val favoriteDashboardTempatPkl = "relationtempatpkl/favorite"

    //relasiTeman
    const val toggleFavoriteFriend = "relationteman/favorite/toggle/{id}"
    const val addFriend = "relationteman/addfriend"
    const val confirmFriend= "relationteman/confirm"

    //relasiTempatPkl
    const val toggleFavoriteTempatPkl = "relationtempatpkl/favorite/toogle/{id}"

    //rekomendasi
    const val totalPengalaman = "rekomendasi/count"
//    const val pengalamanLomba = "pengalaman/lomba"
//    const val pengalamanOrganisasi = "pengalaman/organisasi"
    const val profilInfoOthers = "rekomendasi/profil/{id}"
    const val profilInfoOthersItself = "rekomendasi/profil/itself"
    const val pengalamanLombaOrganisasiWithRekomendasi = "pengalaman/bothwithrekomendasi/{id}"
    const val pengalamanLombaOrganisasiWithRekomendasiItself = "pengalaman/bothwithrekomendasi/itself"
    const val pengalamanLombaOrganisasi= "pengalaman/both"
    const val modifyPengalamanLomba= "pengalaman/lomba/modify"
    const val modifyPengalamanOrganisasi= "pengalaman/organisasi/modify"
    const val deletePengalamanLomba= "pengalaman/lomba/delete/{id}"
    const val deletePengalamanOrganisasi= "pengalaman/organisasi/delete/{id}"
    const val saveRekomendasi = "rekomendasi/save"
    const val changeProfilPicture = "mahasiswa/changeprofilpicture"
    const val rekomendasiDashboard = "rekomendasi/home"

    //ulasanTempatPkl
    const val showUlasanTempat = "ulasantempatpkl/show/{id}"
    const val saveUlasanTempat = "ulasantempatpkl/save"

    //tempatPkl
    const val showTempatPklProfile = "tempatpkl/profile/{id}"
    const val addTempatPkl = "tempatpkl/add"

    //bidangKerjaNama
    const val bidangKerjaSearch = "bidangkerja/search/{namaBidangKerja}"
    const val makeBidangKerja = "bidangkerja/make/{namaBidangKerja}"

    //skillHobi
    const val skillHobiSearch = "skillhobi/search/{namaSkillhobi}"
    const val skillHobiKerja = "skillhobi/make/{namaSkillhobi}"

    //search
    const val searchMahasiswa = "search/mahasiswa"
    const val searchTempatPkl = "search/tempatpkl"
    const val addSearchHistory = "search/add"
    const val showSearchHistory = "search/show"

    //Kelompok
    const val showKelompok = "kelompok/show"
    const val showKelompokNotInvitedYet = "kelompok/show/notinvitedyet/{id_mahasiswa}"
    const val makeKelompok = "kelompok/make"
    const val showFriendWithNamaOnly = "relationteman/showfriendnama"
    const val addFriendToKelompok = "kelompok/addfriend"
    const val showAnggotaKelompok = "kelompok/anggotakelompok/{id_kelompok}"
    const val confirmAnggotaKelompok = "kelompok/confirm"
    const val showFriendNotAdded = "kelompok/showfriendnotadded/{id_kelompok}"
    const val showPending = "kelompok/showpending/{id_kelompok}"
    const val removeAnggota = "kelompok/removeanggota"

    //message
    const val sendMessageKelompok = "pesankelompok/send"
    const val showMessageKelompok = "pesankelompok/show"
    const val sendMessageUser = "pesanuser/send"
    const val showMessageUser = "pesanuser/show"

    //Notifikasi
    const val showNotifikasi = "notifikasi/show"

    //Admin
    const val showMahasiswaNotVerified = "admin/mahasiswa"
    const val showMahasiswaNotVerifiedDetail = "admin/mahasiswa/detail"
    const val showMahasiswaNotVerifiedConfirm = "admin/mahasiswa/confirm"
}