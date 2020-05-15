package com.example.cariteman.util

import com.example.cariteman.data.model.*

object Mapper {

    fun temanToRelationKelompok(responses: ArrayList<DaftarTemanHanyaNama>): MutableList<RelationKelompok> {
        var mutableListOfRelationKelompok = mutableListOf<RelationKelompok>()

        for (response in responses) {
            mutableListOfRelationKelompok.add(RelationKelompok(idMahasiswa = response.id))
        }

        return mutableListOfRelationKelompok
    }

    fun dashboardHistoryLombaResponseMapper(responses: List<MahasiswaHistoryDashboardResponse>): List<MahasiswaHistoryDashboardResponse?> {
        val responseDashboard: Array<MahasiswaHistoryDashboardResponse?> = arrayOfNulls(5)
        var index = 0
        for (item in responses) {
            if (item.mahasiswaTwoLomba != null) {
                responseDashboard[index] = item
                index++
            } else if (item.mahasiswaTwoPkl != null) {
                responseDashboard[index] = item
                index++
            } else if (item.tempatPkl != null) {
                responseDashboard[index] = item
                index++
            }
            if (index == 5) break
        }
        return responseDashboard.toList()
    }

    fun historyResponseMapper(responses: List<MahasiswaHistoryDashboardResponse>): List<MahasiswaHistoryDashboardResponse?> {
        var index = 0
        val responsesMapped: MutableList<MahasiswaHistoryDashboardResponse> = mutableListOf()
        for (item in responses) {
            if (item.mahasiswaTwoLomba != null) {
                responsesMapped.add(index, item)
                index++
            } else if (item.mahasiswaTwoPkl != null) {
                responsesMapped.add(index, item)
                index++
            } else if (item.tempatPkl != null) {
                responsesMapped.add(index, item)
                index++
            }
        }

        return responsesMapped
    }

    fun searchFilterToFrontProfileResponse(responses: SearchFilter): MutableList<FrontProfileResponse> {
        var responsesMapped: MutableList<FrontProfileResponse> = mutableListOf()
        if (!responses.mahasiswa.isNullOrEmpty()) {
            for (item in responses.mahasiswa ?: mutableListOf()) {
                if (item.preferensi == 0) {
                    responsesMapped.add(FrontProfileResponse(mahasiswaTwoPkl = item))
                } else if (item.preferensi != 1) {
                    responsesMapped.add(FrontProfileResponse(mahasiswaTwoLomba = item))
                }
            }
        } else if (!responses.tempatPkl.isNullOrEmpty()){
            for (item in responses.tempatPkl ?: mutableListOf()){
                responsesMapped.add(FrontProfileResponse(tempatPkl = item))
            }
        }

            return responsesMapped
    }

    fun searchHistoryDownTo8(responses: MutableList<SearchHistory>): MutableList<SearchHistory>{
        var searchHistoryOnly8 = mutableListOf<SearchHistory>()
        var limit = 8
        var x = 0
        if (responses.size <=8){
            return responses
        }else {
            while (x < limit) {
                if (responses[x].name == responses[x + 1].name) {
                    limit++
                } else {
                    searchHistoryOnly8.add(responses[x])
                }
                x++
            }
            return searchHistoryOnly8
        }
        }

    fun pengalamanLombaMapper(responses: PengalamanLombaOrganisasi?): MutableList<PengalamanLombaOrganisasiResponse> {
        var index = 0
        val responsesMapped: MutableList<PengalamanLombaOrganisasiResponse> = mutableListOf()

        for (response in responses?.pengalamanLomba!!) {
            responsesMapped.add(index,
                response.let {
                    PengalamanLombaOrganisasiResponse(
                        id = it.id,
                        namaKompetisi = it.namaKompetisi,
                        deskripsi = it.deskripsi,
                        gambar = it.gambar,
                        idMahasiswa = it.idMahasiswa,
                        tanggal = it.tanggal,
                        createdAt = it.createdAt,
                        updatedAt = it.updatedAt,
                        relationBidangKerja = it.relationBidangKerja
                    )
                }
            )
            index++
        }
        for (response in responses?.pengalamanOrganisasi!!) {
            responsesMapped.add(index,
                response.let {
                    PengalamanLombaOrganisasiResponse(
                        id = it.id,
                        namaOrganisasi = it.namaOrganisasi,
                        deskripsi = it.deskripsi,
                        gambar = it.gambar,
                        tanggalMulai = it.tanggalMulai,
                        tanggalSelesai = it.tanggalSelesai,
                        idMahasiswa = it.idMahasiswa,
                        createdAt = it.createdAt,
                        updatedAt = it.updatedAt,
                        relationBidangKerja = it.relationBidangKerja
                    )
                }
            )
            index++
        }

        return responsesMapped
    }

    fun favoriteDashboardResponseMapper(responses: List<RelationTempatPklFavorite>): List<RelationTempatPklFavorite?> {
        var index = 0
        val responsesMapped: MutableList<RelationTempatPklFavorite> = mutableListOf()
        for (item in responses) {
            if (item.mahasiswaTwoLomba != null) {
                responsesMapped.add(index, item)
                index++
            } else if (item.mahasiswaTwoPkl != null) {
                responsesMapped.add(index, item)
                index++
            } else if (item.tempatPkl != null) {
                responsesMapped.add(index, item)
                index++
            }
        }

        return responsesMapped
    }


    fun fakultasResponseMapper(responses: List<FakultasResponse>): ArrayList<Fakultas> {
        val responseFakultas: ArrayList<Fakultas> = arrayListOf(Fakultas("Fakultas"))
        for (response in responses) {
            responseFakultas.add(Fakultas(response.name))
        }
        return responseFakultas
    }

    fun programStudiResponseMapper(responses: List<ProgramStudiResponse>): ArrayList<ProgramStudi> {
        val responseProgramStudi: ArrayList<ProgramStudi> =
            arrayListOf(ProgramStudi(name = "Program Studi"))
        for (response in responses) {
            responseProgramStudi.add(ProgramStudi(response.name))
        }
        return responseProgramStudi
    }

    fun keminatanResponseMapper(responses: List<KeminatanResponse>): ArrayList<Keminatan> {
        val responseKeminatan: ArrayList<Keminatan> = arrayListOf(Keminatan(name = "Keminatan"))
        for (response in responses) {
            responseKeminatan.add(Keminatan(response.name))
        }
        return responseKeminatan
    }

}