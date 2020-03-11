package com.example.cariteman.ui.register.interactor

import com.example.cariteman.data.network.ApiHelper
import com.example.cariteman.data.preferences.PreferenceHelper
import javax.inject.Inject


//class RegisterInteractor @Inject internal constructor(preferenceHelper: PreferenceHelper, apiHelper: ApiHelper) : BaseInteractor(preferenceHelper = preferenceHelper, apiHelper = apiHelper), RegisterMVPInteractor {
//
////    override fun getQuestionCardData() = questionRepoHelper.loadQuestions()
////        .flatMapIterable { question -> question }
////        .flatMapSingle { question -> getQuestionCards(question) }
////        .toList()
////
////    override fun getUserDetails() = Pair(preferenceHelper.getCurrentUserName(),
////        preferenceHelper.getCurrentUserEmail())
////
////    override fun makeLogoutApiCall() = apiHelper.performLogoutApiCall()
//
////    private fun getQuestionCards(question: Question) = optionsRepoHelper.loadOptions(question.id)
////        .map { options -> createQuestionCard(options, question) }
////
////    private fun createQuestionCard(options: List<Options>, question: Question) = QuestionCardData(options, question)
//
//}
