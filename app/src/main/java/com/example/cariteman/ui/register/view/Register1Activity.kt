package com.example.cariteman.ui.register.view

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.cariteman.R
import com.example.cariteman.data.model.*
import com.example.cariteman.databinding.ActivityRegister1Binding
import com.example.cariteman.ui.base.view.BaseActivity
import com.example.cariteman.ui.register.presenter.RegisterMVPPresenter
import com.example.cariteman.util.extension.addFragment
import com.example.cariteman.util.extension.removeFragment
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject


class Register1Activity: BaseActivity(), RegisterMVPView, HasSupportFragmentInjector{

    @Inject
    internal lateinit var presenter: RegisterMVPPresenter<RegisterMVPView>
    @Inject
    internal lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>
    lateinit var viewBind: ActivityRegister1Binding
    lateinit var mahasiswa: Mahasiswa

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

    override fun onFragmentDetached(tag: String) {
        supportFragmentManager?.removeFragment(tag = tag)
    }

    override fun openRegisterFragment() {
        mahasiswa = Mahasiswa()
        mahasiswa.let {
            it.name = viewBind.etNameLogin.text.toString()
            it.nim= viewBind.etNimLogin.text.toString()
            it.email= viewBind.etEmailLogin.text.toString()
            it.password = viewBind.etPasswordLogin.text.toString()
            it.password_confirmation = viewBind.etPasswordConfirmationLogin.text.toString()
        }
        supportFragmentManager.addFragment(R.id.cl_register_2, Register2Fragment.newInstance(), Register2Fragment.TAG)
    }

    override fun onBackPressed() {
        val count = supportFragmentManager.fragments.size
        if (count == 0) {
            super.onBackPressed()
        } else {
            supportFragmentManager.removeFragment("Register1")
        }
    }

    override fun supportFragmentInjector() = fragmentDispatchingAndroidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_1)
        presenter.onAttach(this)

        viewBind = DataBindingUtil.setContentView(this, R.layout.activity_register_1)

        viewBind.etNameLogin.setText(R.string.nama_login)
        viewBind.etNimLogin.setText(R.string.nim_login)
        viewBind.etEmailLogin.setText(R.string.email_login)
        viewBind.etPasswordLogin.setText(R.string.password_login)
        viewBind.etPasswordConfirmationLogin.setText(R.string.password_confirm_login)
        viewBind.bNext.setOnClickListener {
            presenter.onNextRegisterClick()
        }
    }

    override fun showFakultas(responses: ArrayList<Fakultas>) {
        //notImplemented
    }

    override fun onFragmentAttached() {
        //not implemented
    }

    override fun showProgramStudi(responses: ArrayList<ProgramStudi>) {
        //notImplemented
    }

    override fun showKeminatan(responses: ArrayList<Keminatan>) {
        //notImplemented
    }

}