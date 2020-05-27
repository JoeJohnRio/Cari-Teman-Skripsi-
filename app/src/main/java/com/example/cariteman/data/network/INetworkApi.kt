package com.example.cariteman.data.network

import com.example.cariteman.data.model.*
import io.reactivex.Observable
import retrofit2.http.*

interface INetworkApi {

    @POST(Endpoints.login)
    fun loginApi(@Body login: Login): Observable<Token>

    @POST(Endpoints.loginAdmin)
    fun loginAdminApi(@Body login: Login): Observable<MessageOnly>

    @POST(Endpoints.register)
    fun registerApi(@Body mahasiswa: MahasiswaResponse): Observable<MahasiswaResponse>

    @POST(Endpoints.checkUserExist)
    fun checkIfUserExist(@Body mahasiswa: MahasiswaResponse): Observable<MessageOnly>

    @GET(Endpoints.fakultas)
    @Headers("No-Authentication: true")
    fun getFakultas(): Observable<List<FakultasResponse>>

    @GET(Endpoints.programStudi)
    @Headers("No-Authentication: true")
    fun getProgramStudi(@Path("isVerified") id: Int): Observable<List<ProgramStudiResponse>>

    @GET(Endpoints.keminatan)
    fun getKeminatan(@Path("isVerified") id: Int?): Observable<List<KeminatanResponse>>

    //History Dashboard
    @GET(Endpoints.historyDashboardLomba)
    @Headers("No-Authentication: true")
    fun getHistoryDashboardLomba(@Header("app-key") string: String?): Observable<List<MahasiswaHistoryDashboardResponse>>

    @GET(Endpoints.historyProfilPkl)
    @Headers("No-Authentication: true")
    fun getHistoryProfilPkl(@Header("app-key") string: String?, @Query("page") page: Int): Observable<Pagination<MahasiswaHistoryDashboardResponse>>

    @GET(Endpoints.historyProfilLomba)
    @Headers("No-Authentication: true")
    fun getHistoryProfilLomba(@Header("app-key") string: String?, @Query("page") page: Int): Observable<Pagination<MahasiswaHistoryDashboardResponse>>

    @GET(Endpoints.historyProfilTempatPkl)
    @Headers("No-Authentication: true")
    fun getHistoryProfilTempatPkl(@Header("app-key") string: String?, @Query("page") page: Int): Observable<Pagination<MahasiswaHistoryDashboardResponse>>

    //Dashboard Home
    @GET(Endpoints.historyDashboardPkl)
    @Headers("No-Authentication: true")
    fun getHistoryDashboardPkl(@Header("app-key") string: String?): Observable<List<MahasiswaHistoryDashboardResponse>>

    @GET(Endpoints.historyDashboardTempatPkl)
    @Headers("No-Authentication: true")
    fun getHistoryDashboardTempatPkl(@Header("app-key") string: String?): Observable<List<MahasiswaHistoryDashboardResponse>>

    @PUT(Endpoints.toggleFavoriteFriend)
    @Headers("No-Authentication: true")
    fun toggleFavoriteFriend(@Header("app-key") string: String?, @Path("id") id: Int?): Observable<RelationTeman>

    //Dashboard Favorite
    @GET(Endpoints.favoriteDashboardPkl)
    @Headers("No-Authentication: true")
    fun getDashboardFavoriteFriendPkl(@Header("app-key") string: String?, @Query("page") page: Int): Observable<Pagination<RelationTempatPklFavorite>>

    //Dashboard Favorite
    @GET(Endpoints.favoriteDashboardLomba)
    @Headers("No-Authentication: true")
    fun getDashboardFavoriteFriendLomba(@Header("app-key") string: String?, @Query("page") page: Int): Observable<Pagination<RelationTempatPklFavorite>>

    //Dashboard Favorite
    @GET(Endpoints.favoriteDashboardTempatPkl)
    @Headers("No-Authentication: true")
    fun getDashboardFavoriteTempatPkl(@Header("app-key") string: String?, @Query("page") page: Int): Observable<Pagination<RelationTempatPklFavorite>>

    //Favorite
    @PUT(Endpoints.toggleFavoriteTempatPkl)
    @Headers("No-Authentication: true")
    fun toggleFavoriteTempatPkl(@Header("app-key") string: String?, @Path("id") id: Int?): Observable<RelationTempatPkl>

    @GET(Endpoints.showUlasanTempat)
    @Headers("No-Authentication: true")
    fun getUlasanTempatPkl(@Header("app-key") string: String?, @Path("id") id: Int?): Observable<MutableList<UlasanTempatPklProfile>>

    @GET(Endpoints.showTempatPklProfile)
    @Headers("No-Authentication: true")
    fun getTempatPklProfile(@Header("app-key") string: String?, @Path("id") id: Int?): Observable<TempatPklProfile>

    //Rekomendasi
    @GET(Endpoints.profilInfoOthers)
    @Headers("No-Authentication: true")
    fun getProfilInfoOthers(@Header("app-key") string: String?, @Path("id") id: Int?): Observable<ProfilInfoOthers>

    @POST(Endpoints.profilInfoOthersItself)
    @Headers("No-Authentication: true")
    fun getProfilInfoOthersItself(@Header("app-key") string: String?): Observable<ProfilInfoOthers>

    @GET(Endpoints.pengalamanLombaOrganisasiWithRekomendasi)
    @Headers("No-Authentication: true")
    fun getPengalamanAndRekomendasi(@Header("app-key") string: String?, @Path("id") id: Int?): Observable<ProfilePengalamanRekomendasi>

    @POST(Endpoints.pengalamanLombaOrganisasiWithRekomendasiItself)
    @Headers("No-Authentication: true")
    fun getPengalamanAndRekomendasiItself(@Header("app-key") string: String?): Observable<ProfilePengalamanRekomendasi>

    @GET(Endpoints.pengalamanLombaOrganisasi)
    @Headers("No-Authentication: true")
    fun getPengalamanBoth(@Header("app-key") string: String?): Observable<PengalamanLombaOrganisasi>

    @PUT(Endpoints.modifyPengalamanLomba)
    @Headers("No-Authentication: true")
    fun modifyPengalamanLomba(
        @Header("app-key") string: String?, @Body pengalamanLombaOrganisasi: PengalamanLombaOrganisasiResponse
    ): Observable<PengalamanLombaOrganisasiResponse>

    @PUT(Endpoints.modifyPengalamanOrganisasi)
    @Headers("No-Authentication: true")
    fun modifyPengalamanOrganisasi(
        @Header("app-key") string: String?, @Body pengalamanLombaOrganisasi: PengalamanLombaOrganisasiResponse
    ): Observable<PengalamanLombaOrganisasiResponse>

    @POST(Endpoints.deletePengalamanLomba)
    @Headers("No-Authentication: true")
    fun deletePengalamanLomba(
        @Header("app-key") string: String?, @Path("id") id: Int?
    ): Observable<PengalamanLombaOrganisasiResponse>

    @POST(Endpoints.deletePengalamanOrganisasi)
    @Headers("No-Authentication: true")
    fun deletePengalamanOrganisasi(
        @Header("app-key") string: String?, @Path("id") id: Int?
    ): Observable<PengalamanLombaOrganisasiResponse>

    //BidangKerja
    @GET(Endpoints.bidangKerjaSearch)
    @Headers("No-Authentication: true")
    fun getBidangKerjaSearch(@Header("app-key") string: String?, @Path("namaBidangKerja") namaBidangKerja: String?): Observable<MutableList<BidangKerja>>

    @POST(Endpoints.makeBidangKerja)
    @Headers("No-Authentication: true")
    fun makeBidangKerja(@Header("app-key") string: String?, @Path("namaBidangKerja") namaBidangKerja: String?): Observable<BidangKerja>

    //SkillHobi
    @GET(Endpoints.skillHobiSearch)
    @Headers("No-Authentication: true")
    fun getSkillhobiSearch(@Header("app-key") string: String?, @Path("namaSkillhobi") namaBidangKerja: String?): Observable<MutableList<SkillHobi>>

    @POST(Endpoints.skillHobiKerja)
    @Headers("No-Authentication: true")
    fun makeSkillHobi(@Header("app-key") string: String?, @Path("namaSkillhobi") namaBidangKerja: String?): Observable<SkillHobi>

    //Kelompok
    @GET(Endpoints.showKelompok)
    @Headers("No-Authentication: true")
    fun showKelompok(@Header("app-key") string: String?): Observable<MutableList<RelationKelompok>>

    @GET(Endpoints.showFriendWithNamaOnly)
    @Headers("No-Authentication: true")
    fun showFriendWithNamaOnly(@Header("app-key") string: String?): Observable<MutableList<DaftarTemanHanyaNama>>

    @GET(Endpoints.showFriendNotAdded)
    @Headers("No-Authentication: true")
    fun showFriendNotAdded(@Header("app-key") string: String?,  @Path("id_kelompok") idKelompok: Int?): Observable<MutableList<DaftarTemanHanyaNama>>

    @POST(Endpoints.addFriendToKelompok)
    @Headers("No-Authentication: true")
    fun addFriendToKelompok(@Header("app-key") string: String?, @Body kelompok: Kelompok): Observable<RelationKelompok>

    @POST(Endpoints.makeKelompok)
    @Headers("No-Authentication: true")
    fun makeKelompok(@Header("app-key") string: String?, @Body kelompok: Kelompok): Observable<Kelompok>

    @GET(Endpoints.showAnggotaKelompok)
    @Headers("No-Authentication: true")
    fun showAnggotaKelompok(@Header("app-key") string: String?, @Path("id_kelompok") idKelompok: Int?): Observable<MutableList<AnggotaKelompok>>

    @POST(Endpoints.confirmAnggotaKelompok)
    @Headers("No-Authentication: true")
    fun confirmAnggotaKelompok(@Header("app-key") string: String?, @Body relationKelompok: RelationKelompok): Observable<MessageOnly>

    @POST(Endpoints.searchMahasiswa)
    @Headers("No-Authentication: true")
    fun searchMahasiswa(@Header("app-key") string: String?, @Body mahasiswaSearch: MahasiswaSearchFilter?): Observable<SearchFilter>

    @POST(Endpoints.searchTempatPkl)
    @Headers("No-Authentication: true")
    fun searchTempatPkl(@Header("app-key") string: String?, @Body tempatPklSearch: TempatPklSearchFilter?): Observable<SearchFilter>

    @POST(Endpoints.addSearchHistory)
    @Headers("No-Authentication: true")
    fun addSearchHistory(@Header("app-key") string: String?, @Body searchHistory: SearchHistory?): Observable<SearchHistory>

    @GET(Endpoints.showSearchHistory)
    @Headers("No-Authentication: true")
    fun showSearchHistory(@Header("app-key") string: String?): Observable<MutableList<SearchHistory>>

    //Message
    @POST(Endpoints.showMessageKelompok)
    @Headers("No-Authentication: true")
    fun showMessageKelompok(@Header("app-key") string: String?, @Body messageSend: MessageSend): Observable<MutableList<MessageKelompok>>

    @POST(Endpoints.sendMessageKelompok)
    @Headers("No-Authentication: true")
    fun sendMessageKelompok(@Header("app-key") string: String?, @Body messageSend: MessageSend): Observable<MessageOnly>

    @POST(Endpoints.showMessageUser)
    @Headers("No-Authentication: true")
    fun showMessageUser(@Header("app-key") string: String?, @Body messageSend: MessageSend): Observable<MutableList<MessageKelompok>>

    @POST(Endpoints.sendMessageUser)
    @Headers("No-Authentication: true")
    fun sendMessageUser(@Header("app-key") string: String?, @Body messageSend: MessageSend): Observable<MessageOnly>

    //Notifikasi
    @GET(Endpoints.showNotifikasi)
    @Headers("No-Authentication: true")
    fun showNotifikasi(@Header("app-key") string: String?): Observable<MutableList<NotifikasiResponse>>

    //Relation
    @POST(Endpoints.addFriend)
    @Headers("No-Authentication: true")
    fun addFriend(@Header("app-key") string: String?, @Body relation: RelationTeman): Observable<MessageOnly>

    @POST(Endpoints.confirmFriend)
    @Headers("No-Authentication: true")
    fun confirmFriend(@Header("app-key") string: String?, @Body relation: RelationTeman): Observable<MessageOnly>
}