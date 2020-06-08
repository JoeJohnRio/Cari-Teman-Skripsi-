package com.example.cariteman.ui.pengaturan.view

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.cariteman.databinding.ActivityPengaturanBinding
import com.example.cariteman.ui.base.view.BaseFragment
import com.example.cariteman.ui.login.view.LoginActivity
import com.example.cariteman.ui.pengaturan.presenter.PengaturanMVPPresenter
import com.example.cariteman.ui.register.view.PengaturanMVPView
import com.example.cariteman.util.Utils.SHARED_PREFS
import com.example.cariteman.util.Utils.TEXT
import dagger.android.DispatchingAndroidInjector
import javax.inject.Inject


class PengaturanFragment : BaseFragment(), PengaturanMVPView {

    @Inject
    internal lateinit var presenter: PengaturanMVPPresenter<PengaturanMVPView>

    @Inject
    internal lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>
    lateinit var viewBind: ActivityPengaturanBinding

    companion object {

        internal val TAG = "Register1"
        fun newInstance(): PengaturanFragment {
            return PengaturanFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBind = ActivityPengaturanBinding.inflate(inflater, container, false)
        presenter.onAttach(this)

        viewBind.tvLogout.setOnClickListener {
            val sharedPreferences = context?.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE)
            val editor = sharedPreferences?.edit()

            editor?.putString(TEXT, "")
            editor?.apply()

            activity?.finish()
            startActivity(Intent(context, LoginActivity::class.java))
        }

        return viewBind.root
    }

    override fun setUp() {
        //notImplemented
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }
}