package com.example.cariteman.ui.pengalaman.view

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.cariteman.R
import com.example.cariteman.data.model.PengalamanLombaOrganisasiResponse
import com.example.cariteman.databinding.ActivityPengalamanBinding
import com.example.cariteman.ui.base.view.BaseActivity
import com.example.cariteman.ui.pengalaman.pengalamanhome.view.PengalamanListAdapter
import com.example.cariteman.ui.dashboard.presenter.PengalamanPresenter
import com.example.cariteman.util.Utils
import com.example.cariteman.util.extension.removeFragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject
import com.example.cariteman.ui.pengalaman.pengalamanhome.view.PengalamanHomeFragment
import com.example.cariteman.util.extension.addFragmentWithBackStack
import com.example.cariteman.util.extension.addFragmentWithoutBackStack

class PengalamanActivity: BaseActivity(), PengalamanMVPView, HasSupportFragmentInjector {

    @Inject
    lateinit var presenter: PengalamanPresenter<PengalamanMVPView>

    @Inject
    internal lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    lateinit var viewBind: ActivityPengalamanBinding

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentDispatchingAndroidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBind = DataBindingUtil.setContentView(this, R.layout.activity_pengalaman)
        presenter.onAttach(this)
        presenter.setKey(Utils.loadData(applicationContext))

        viewBind.ivBack.setOnClickListener {
            onBackPressed()
        }

        supportFragmentManager.addFragmentWithoutBackStack(
            R.id.cl_tambah_pengalaman_lomba,
            PengalamanHomeFragment.newInstance(),
            PengalamanHomeFragment.TAG
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