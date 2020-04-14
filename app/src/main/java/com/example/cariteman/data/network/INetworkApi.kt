package com.example.cariteman.data.network

import com.example.cariteman.data.model.*
import io.reactivex.Observable
import retrofit2.http.*

interface INetworkApi {

    @POST(Endpoints.login)
    fun loginApi(@Body login: Login): Observable<Token>

    @POST(Endpoints.register)
    fun registerApi(@Body mahasiswa: Mahasiswa): Observable<Mahasiswa>

    @GET(Endpoints.mahasiswa)
    fun getMahasiswa(@Header("app-key") string: String): Observable<List<MahasiswaResponse>>

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
    fun getHistoryProfilPkl(@Header("app-key") string: String?,@Query("page") page: Int): Observable<Pagination<MahasiswaHistoryDashboardResponse>>

    @GET(Endpoints.historyProfilLomba)
    @Headers("No-Authentication: true")
    fun getHistoryProfilLomba(@Header("app-key") string: String?,@Query("page") page: Int): Observable<Pagination<MahasiswaHistoryDashboardResponse>>

    @GET(Endpoints.historyProfilTempatPkl)
    @Headers("No-Authentication: true")
    fun getHistoryProfilTempatPkl(@Header("app-key") string: String?,@Query("page") page: Int): Observable<Pagination<MahasiswaHistoryDashboardResponse>>

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
    fun getDashboardFavoriteFriendPkl(@Header("app-key") string: String?,@Query("page") page: Int): Observable<Pagination<RelationTempatPklFavorite>>

    //Dashboard Favorite
    @GET(Endpoints.favoriteDashboardLomba)
    @Headers("No-Authentication: true")
    fun getDashboardFavoriteFriendLomba(@Header("app-key") string: String?,@Query("page") page: Int): Observable<Pagination<RelationTempatPklFavorite>>

    //Dashboard Favorite
    @GET(Endpoints.favoriteDashboardTempatPkl)
    @Headers("No-Authentication: true")
    fun getDashboardFavoriteTempatPkl(@Header("app-key") string: String?,@Query("page") page: Int): Observable<Pagination<RelationTempatPklFavorite>>

    //Favorite
    @PUT(Endpoints.toggleFavoriteTempatPkl)
    @Headers("No-Authentication: true")
    fun toggleFavoriteTempatPkl(@Header("app-key") string: String?, @Path("id") id: Int?): Observable<RelationTempatPkl>

    @GET(Endpoints.showUlasanTempat)
    @Headers("No-Authentication: true")
    fun getUlasanTempatPkl(@Header("app-key") string: String?): Observable<List<UlasanTempatPkl>>

    @GET(Endpoints.pengalamanLomba)
    @Headers("No-Authentication: true")
    fun getPengalamanLomba(@Header("app-key") string: String?): Observable<List<MahasiswaHistoryDashboardResponse>>

    @GET(Endpoints.pengalamanOrganisasi)
    @Headers("No-Authentication: true")
    fun getPengalamanOrganisasi(@Header("app-key") string: String?): Observable<TotalPengalamanResponse>

    //Rekomendasi
    @GET(Endpoints.profilInfoOthers)
    @Headers("No-Authentication: true")
    fun getProfilInfoOthers(@Header("app-key") string: String?,  @Path("id") id: Int?): Observable<ProfilInfoOthers>

}