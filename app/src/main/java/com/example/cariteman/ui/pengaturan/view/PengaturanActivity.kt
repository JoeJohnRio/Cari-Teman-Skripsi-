package com.example.cariteman.ui.pengaturan.view


import android.net.Uri
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.cariteman.R
import com.example.cariteman.data.model.*
import com.example.cariteman.databinding.ActivityPengalamanBinding
import com.example.cariteman.ui.base.view.BaseActivity
import com.example.cariteman.ui.pengaturan.presenter.PengaturanMVPPresenter
import com.example.cariteman.ui.register.presenter.RegisterMVPPresenter
import com.example.cariteman.ui.register.view.PengaturanMVPView
import com.example.cariteman.ui.register.view.Register1Fragment
import com.example.cariteman.ui.register.view.RegisterMVPView
import com.example.cariteman.util.extension.addFragmentWithoutBackStack
import com.example.cariteman.util.extension.removeFragment
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class PengaturanActivity : BaseActivity(), PengaturanMVPView, HasSupportFragmentInjector {

    @Inject
    internal lateinit var presenter: PengaturanMVPPresenter<PengaturanMVPView>


    @Inject
    internal lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>
    lateinit var viewBind: ActivityPengalamanBinding
    override fun supportFragmentInjector() = fragmentDispatchingAndroidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.onAttach(this)

        viewBind = DataBindingUtil.setContentView(this, R.layout.activity_pengalaman)

        supportFragmentManager.addFragmentWithoutBackStack(
            R.id.cl_pengalaman_home,
            PengaturanFragment.newInstance(),
            PengaturanFragment.TAG
        )
    }

    override fun onBackPressed() {
        val count = supportFragmentManager.backStackEntryCount
        if (count == 0) {
            super.onBackPressed()
            //additional code
        } else {
            supportFragmentManager.popBackStack()
        }
    }

    override fun onFragmentDetached(tag: String) {
        supportFragmentManager.removeFragment(tag = tag)
    }

    override fun onFragmentAttached() {
        //notImplemented
    }
}
