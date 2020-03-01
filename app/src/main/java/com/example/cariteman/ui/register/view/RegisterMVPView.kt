package com.example.cariteman.ui.register.view

import com.example.cariteman.ui.base.MVPView


interface RegisterMVPView : MVPView {

    fun inflateUserDetails(userDetails: Pair<String?, String?>)
    fun openLoginActivity()
    fun openFeedActivity()
    fun openAboutFragment()
    fun openRateUsDialog(): Unit?
    fun lockDrawer(): Unit?
    fun unlockDrawer(): Unit?
}