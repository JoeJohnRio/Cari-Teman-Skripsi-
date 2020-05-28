package com.example.cariteman.ui.verifikasiakun.view

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.cariteman.R
import com.example.cariteman.databinding.ActivityPengalamanBinding
import com.example.cariteman.databinding.ActivityVerifikasiAkunBinding
import com.example.cariteman.ui.base.view.BaseActivity
import com.example.cariteman.ui.verifikasiakun.presenter.VerifikasiAkunPresenter
import com.example.cariteman.ui.verifikasiakun.verifikasiakunlist.view.VerifikasiAkunListFragment
import com.example.cariteman.util.Utils
import com.example.cariteman.util.extension.removeFragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject
import com.example.cariteman.util.extension.addFragmentWithoutBackStack

class VerifikasiAkunActivity: BaseActivity(), VerifikasiAkunMVPView, HasSupportFragmentInjector {

    @Inject
    lateinit var presenter: VerifikasiAkunPresenter<VerifikasiAkunMVPView>

    @Inject
    internal lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    lateinit var viewBind: ActivityVerifikasiAkunBinding

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentDispatchingAndroidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBind = DataBindingUtil.setContentView(this, R.layout.activity_verifikasi_akun)
        presenter.onAttach(this)
        presenter.setKey(Utils.loadData(applicationContext))

        supportFragmentManager.addFragmentWithoutBackStack(
            R.id.cl_verifikasi_akun,
            VerifikasiAkunListFragment.newInstance(),
            VerifikasiAkunListFragment.TAG
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

    override fun onFragmentAttached() {
        //notImplemented
    }

    override fun onFragmentDetached(tag: String) {
        supportFragmentManager?.removeFragment(tag = tag)
    }
}