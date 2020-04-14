package com.example.cariteman.util

import com.example.cariteman.data.model.*

object Mapper {

    fun dashboardHistoryLombaResponseMapper(responses: List<MahasiswaHistoryDashboardResponse>): List<MahasiswaHistoryDashboardResponse?> {
        val responseDashboard: Array<MahasiswaHistoryDashboardResponse?> = arrayOfNulls(5)
        var index = 0
        for (item in responses){
            if (item.mahasiswaTwoLomba != null){
                responseDashboard[index] = item
                index++
            }else if (item.mahasiswaTwoPkl != null){
                responseDashboard[index] = item
                index++
            }else if (item.tempatPkl != null){
                responseDashboard[index] = item
                index++
            }
            if (index==5) break
        }
        return responseDashboard.toList()
    }

    fun historyResponseMapper(responses: List<MahasiswaHistoryDashboardResponse>): List<MahasiswaHistoryDashboardResponse?> {
        var index = 0
        val responsesMapped: MutableList<MahasiswaHistoryDashboardResponse> = mutableListOf()
        for (item in responses){
            if (item.mahasiswaTwoLomba != null){
                responsesMapped.add(index, item)
                index++
            }else if (item.mahasiswaTwoPkl != null){
                responsesMapped.add(index, item)
                index++
            }else if (item.tempatPkl != null){
                responsesMapped.add(index, item)
                index++
            }
        }

        return responsesMapped
    }

    fun favoriteDashboardResponseMapper(responses: List<RelationTempatPklFavorite>): List<RelationTempatPklFavorite?> {
        var index = 0
        val responsesMapped: MutableList<RelationTempatPklFavorite> = mutableListOf()
        for (item in responses){
            if (item.mahasiswaTwoLomba != null){
                responsesMapped.add(index, item)
                index++
            }else if (item.mahasiswaTwoPkl != null){
                responsesMapped.add(index, item)
                index++
            }else if (item.tempatPkl != null){
                responsesMapped.add(index, item)
                index++
            }
        }

        return responsesMapped
    }


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

    fun keminatanResponseMapper(responses: List<KeminatanResponse>): ArrayList<Keminatan>{
        val responseKeminatan: ArrayList<Keminatan> = arrayListOf(Keminatan(name = "Keminatan"))
        for(response in responses){
            responseKeminatan.add(Keminatan(response.name))
        }
        return responseKeminatan
    }

}