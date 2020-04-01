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
    fun getProgramStudi(@Path("id") id: Int): Observable<List<ProgramStudiResponse>>

    @GET(Endpoints.keminatan)
    fun getKeminatan(@Path("id") id: Int?): Observable<List<KeminatanResponse>>

    //History Dashboard
    @GET(Endpoints.historyDashboardLomba)
    @Headers("No-Authentication: true")
    fun getHistoryDashboardLomba(@Header("app-key") string: String?): Observable<List<MahasiswaHistoryDashboardResponse>>

    @GET(Endpoints.historyProfilPkl)
    @Headers("No-Authentication: true")
    fun getHistoryProfilPkl(@Header("app-key") string: String?,@Query("page") page: Int): Observable<Pagination<MahasiswaHistoryDashboardResponse>>

    @GET(Endpoints.historyProfilLomba)
    @Headers("No-Authentication: true")
    fun getHistoryProfilLomba(@Header("app-key") string: String?): Observable<List<MahasiswaHistoryDashboardResponse>>

    @GET(Endpoints.historyProfilTempatPkl)
    @Headers("No-Authentication: true")
    fun getHistoryProfilTempatPkl(@Header("app-key") string: String?): Observable<List<MahasiswaHistoryDashboardResponse>>

    @GET(Endpoints.historyDashboardPkl)
    @Headers("No-Authentication: true")
    fun getHistoryDashboardPkl(@Header("app-key") string: String?): Observable<List<MahasiswaHistoryDashboardResponse>>

    @GET(Endpoints.historyDashboardTempatPkl)
    @Headers("No-Authentication: true")
    fun getHistoryDashboardTempatPkl(@Header("app-key") string: String?): Observable<List<MahasiswaHistoryDashboardResponse>>

    @GET(Endpoints.showUlasanTempat)
    @Headers("No-Authentication: true")
    fun getUlasanTempatPkl(@Header("app-key") string: String?): Observable<List<UlasanTempatPkl>>

    @GET(Endpoints.pengalamanLomba)
    @Headers("No-Authentication: true")
    fun getPengalamanLomba(@Header("app-key") string: String?): Observable<List<MahasiswaHistoryDashboardResponse>>

    @GET(Endpoints.pengalamanOrganisasi)
    @Headers("No-Authentication: true")
    fun getPengalamanOrganisasi(@Header("app-key") string: String?): Observable<TotalPengalamanResponse>


}