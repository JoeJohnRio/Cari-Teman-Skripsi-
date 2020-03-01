package com.example.cariteman.ui.register.view

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.cariteman.databinding.ActivityRegister1Binding
import com.example.cariteman.ui.base.view.BaseActivity
import com.example.cariteman.ui.register.interactor.RegisterMVPInteractor
import com.example.cariteman.ui.register.presenter.RegisterMVPPresenter
import com.example.cariteman.util.extension.addFragment
import com.example.cariteman.util.extension.removeFragment
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject
import com.example.cariteman.R



class Register1Activity: BaseActivity(), RegisterMVPView, HasSupportFragmentInjector {

    override fun lockDrawer(): Unit? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    @Inject
    internal lateinit var presenter: RegisterMVPPresenter<RegisterMVPView, RegisterMVPInteractor>
    @Inject
    internal lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

    override fun onFragmentAttached() {
    }

    override fun onFragmentDetached(tag: String) {
        supportFragmentManager?.removeFragment(tag = tag)
    }

    override fun openAboutFragment() {
        supportFragmentManager.addFragment(R.id.cl_register_2, Register2Fragment.newInstance(), Register2Fragment.TAG)
    }

    override fun onBackPressed() {
            supportFragmentManager.popBackStack()
    }

    override fun supportFragmentInjector() = fragmentDispatchingAndroidInjector

    override fun inflateUserDetails(userDetails: Pair<String?, String?>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun openLoginActivity() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun openFeedActivity() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun openRateUsDialog(): Unit? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

//    override fun lockDrawer() = drawerLayout?.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)

    override fun unlockDrawer(): Unit? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    lateinit var viewBind: ActivityRegister1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_1)
        presenter.onAttach(this)

        viewBind = DataBindingUtil.setContentView(this,
            R.layout.activity_register_1
        )
        viewBind.bNext.setOnClickListener {
            presenter.onNextRegisterClick()
        }

    }


}