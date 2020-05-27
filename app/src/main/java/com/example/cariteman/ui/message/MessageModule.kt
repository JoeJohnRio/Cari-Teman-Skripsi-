package com.example.cariteman.ui.dashboard

import com.example.cariteman.ui.message.presenter.MessageMVPPresenter
import com.example.cariteman.ui.message.presenter.MessagePresenter
import com.example.cariteman.ui.message.view.MessageMVPView
import dagger.Module
import dagger.Provides

@Module
class MessageModule {
    @Provides
    internal fun provideMessagePresenter(mainPresenter: MessagePresenter<MessageMVPView>)
            : MessageMVPPresenter<MessageMVPView> = mainPresenter
}