package com.example.cariteman.ui.firstpage.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.databinding.DataBindingUtil
import com.example.cariteman.R
import com.example.cariteman.databinding.ActivityFirstPageBinding
import com.example.cariteman.databinding.ActivityLoginBinding
import com.example.cariteman.ui.base.view.BaseActivity
import com.example.cariteman.ui.dashboard.view.DashboardBottomViewActivity
import com.example.cariteman.ui.firstpage.presenter.FirstPageMVPPresenter
import com.example.cariteman.ui.login.view.LoginActivity
import com.example.cariteman.util.Utils
import javax.inject.Inject

class FirstPageActivity : BaseActivity(), FirstPageMVPView {

    @Inject
    lateinit var presenter: FirstPageMVPPresenter<FirstPageMVPView>

    lateinit var viewBind: ActivityFirstPageBinding
    var hiddenClick = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_page)
        presenter.onAttach(this)
        presenter.setKey(Utils.loadData(applicationContext))

        viewBind = DataBindingUtil.setContentView(this, R.layout.activity_first_page)

        Handler().postDelayed({
            if (Utils.loadData(applicationContext)!! == "") {
                finish()
                startActivity(Intent(this, LoginActivity::class.java))
            } else {
                finish()
                startActivity(Intent(this, DashboardBottomViewActivity::class.java))
            }
        }, 1000)
}

override fun onFragmentDetached(tag: String) {
    //noImplement
}

override fun onFragmentAttached() {
    //noImplement
}
}
