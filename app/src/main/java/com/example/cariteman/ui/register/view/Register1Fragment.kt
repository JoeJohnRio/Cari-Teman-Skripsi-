package com.example.cariteman.ui.register.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.cariteman.R
import com.example.cariteman.data.model.*
import com.example.cariteman.databinding.ActivityRegister1Binding
import com.example.cariteman.ui.base.view.BaseFragment
import com.example.cariteman.ui.register.presenter.RegisterMVPPresenter
import com.example.cariteman.util.Utils
import com.example.cariteman.util.extension.addFragmentWithBackStack
import dagger.android.DispatchingAndroidInjector
import javax.inject.Inject


class Register1Fragment : BaseFragment(), RegisterMVPView{
    @Inject
    internal lateinit var presenter: RegisterMVPPresenter<RegisterMVPView>

    @Inject
    internal lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>
    lateinit var viewBind: ActivityRegister1Binding
    companion object {

        internal val TAG = "Register1"
        fun newInstance(): Register1Fragment{
            return Register1Fragment()
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBind = ActivityRegister1Binding.inflate(inflater, container, false)
        presenter.onAttach(this)

        viewBind.etNameLogin.setText(R.string.nama_login)
        viewBind.etNimLogin.setText(R.string.nim_login)
        viewBind.etEmailLogin.setText(R.string.email_login)
        viewBind.etPasswordLogin.setText(R.string.password_login)
        viewBind.etPasswordConfirmationLogin.setText(R.string.password_confirm_login)
        viewBind.bNext.setOnClickListener {
            if(Utils.isEmailValid(viewBind.etEmailLogin.text.toString()) &&
                !viewBind.etNameLogin.text.toString().isEmpty() &&
                !viewBind.etPasswordLogin.text.toString().isEmpty() &&
                viewBind.etPasswordLogin.text.toString() == viewBind.etPasswordConfirmationLogin.text.toString()  &&
                Utils.isNimValid(viewBind.etNimLogin.text.toString()) ){
                presenter.onNextRegisterClick(viewBind.etNimLogin.text.toString(), viewBind.etEmailLogin.text.toString())
            }else{
                showMessageToast("Isi informasi diatas dengan benar")
            }
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

    override fun openRegisterFragment() {
        (activity as RegisterActivity).mahasiswa = MahasiswaResponse()
        (activity as RegisterActivity).mahasiswa.let {
            it.name = viewBind.etNameLogin.text.toString()
            it.nim = viewBind.etNimLogin.text.toString()
            it.email = viewBind.etEmailLogin.text.toString()
            it.password = viewBind.etPasswordLogin.text.toString()
            it.passwordConfirmation = viewBind.etPasswordConfirmationLogin.text.toString()
        }
        activity?.supportFragmentManager?.addFragmentWithBackStack(
            R.id.cl_register,
            Register2Fragment.newInstance(),
            Register2Fragment.TAG
        )
    }

    override fun finishActivity() {
        (context as RegisterActivity).finish()
    }

    override fun showFakultas(responses: ArrayList<Fakultas>) {
        //notImplemented
    }

    override fun showProgramStudi(responses: ArrayList<ProgramStudi>) {
        //notImplemented
    }

    override fun showKeminatan(responses: ArrayList<Keminatan>) {
        //notImplemented
    }

}