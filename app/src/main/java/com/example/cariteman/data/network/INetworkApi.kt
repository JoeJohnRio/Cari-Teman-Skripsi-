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
}