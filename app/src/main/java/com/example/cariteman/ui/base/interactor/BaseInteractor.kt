package com.example.cariteman.ui.base.interactor


//open class BaseInteractor() : MVPInteractor {
//
//    protected lateinit var preferenceHelper: PreferenceHelper
//    protected lateinit var apiHelper: ApiHelper
//
//    constructor(preferenceHelper: PreferenceHelper, apiHelper: ApiHelper) : this() {
//        this.preferenceHelper = preferenceHelper
//        this.apiHelper = apiHelper
//    }
//
//    override fun isUserLoggedIn() = this.preferenceHelper.getCurrentUserLoggedInMode() != AppConstants.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT.type
//
//    override fun performUserLogout() = preferenceHelper.let {
//        it.setCurrentUserId(null)
//        it.setAccessToken(null)
//        it.setCurrentUserEmail(null)
//        it.setCurrentUserLoggedInMode(AppConstants.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT)
//    }
//
//}