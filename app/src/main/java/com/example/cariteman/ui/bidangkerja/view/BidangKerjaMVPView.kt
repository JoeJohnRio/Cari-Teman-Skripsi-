package com.example.cariteman.ui.pengalaman.pengalamanhome.view

import com.example.cariteman.data.model.*
import com.example.cariteman.ui.base.MVPView

interface BidangKerjaMVPView : MVPView{
    fun showBidangKerja(listOfBidangKerja: MutableList<BidangKerja>)

    fun popBackStackWithBidangKerja(bidangKerja: BidangKerja)
}