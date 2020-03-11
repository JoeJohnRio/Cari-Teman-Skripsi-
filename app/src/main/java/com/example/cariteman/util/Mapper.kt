package com.example.cariteman.util

import com.example.cariteman.data.model.*

object Mapper {

    fun fakultasResponseMapper(responses: List<FakultasResponse>): ArrayList<Fakultas>{
        val responseFakultas: ArrayList<Fakultas> = arrayListOf(Fakultas("Fakultas"))
        for(response in responses){
            responseFakultas.add(Fakultas(response.name))
        }
        return responseFakultas
    }

    fun programStudiResponseMapper(responses: List<ProgramStudiResponse>): ArrayList<ProgramStudi>{
        val responseProgramStudi: ArrayList<ProgramStudi> = arrayListOf(ProgramStudi(name = "Program Studi"))
        for(response in responses){
            responseProgramStudi.add(ProgramStudi(response.name))
        }
        return responseProgramStudi
    }

    fun mahasiswaToMahasiswaResponseMapper(mahasiswa: Mahasiswa): MahasiswaResponse{
        val mahasiswaResponse = MahasiswaResponse()
        mahasiswaResponse.name = mahasiswa.name
        mahasiswaResponse.email = mahasiswa.email
        mahasiswaResponse.nim = mahasiswa.nim
        mahasiswaResponse.password = mahasiswa.password
//        mahasiswaResponse.foto_ktm = mahasiswa.foto_ktm
        mahasiswaResponse.foto_profil = mahasiswa.foto_profil
        mahasiswaResponse.id_fakultas = mahasiswa.id_fakultas
        mahasiswaResponse.id_program_studi = mahasiswa.id_program_studi
        mahasiswaResponse.id_keminatan = mahasiswa.id_keminatan
        mahasiswaResponse.tahun_mulai = mahasiswa.tahun_mulai
        mahasiswaResponse.preferensi = mahasiswa.preferensi

        return mahasiswaResponse
    }

    fun keminatanResponseMapper(responses: List<KeminatanResponse>): ArrayList<Keminatan>{
        val responseKeminatan: ArrayList<Keminatan> = arrayListOf(Keminatan(name = "Keminatan"))
        for(response in responses){
            responseKeminatan.add(Keminatan(response.name))
        }
        return responseKeminatan
    }

}