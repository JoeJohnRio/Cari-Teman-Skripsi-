package com.example.cariteman.ui.register.view


import android.net.Uri
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.cariteman.R
import com.example.cariteman.data.model.Fakultas
import com.example.cariteman.data.model.Keminatan
import com.example.cariteman.data.model.Mahasiswa
import com.example.cariteman.data.model.ProgramStudi
import com.example.cariteman.databinding.ActivityRegisterBinding
import com.example.cariteman.ui.base.view.BaseActivity
import com.example.cariteman.ui.register.presenter.RegisterMVPPresenter
import com.example.cariteman.util.extension.addFragmentWithBackStack
import com.example.cariteman.util.extension.addFragmentWithoutBackStack
import com.example.cariteman.util.extension.removeFragment
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class RegisterActivity : BaseActivity(), RegisterMVPView, HasSupportFragmentInjector {


    @Inject
    internal lateinit var presenter: RegisterMVPPresenter<RegisterMVPView>
    @Inject
    internal lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>
    lateinit var viewBind: ActivityRegisterBinding
    var mahasiswa = Mahasiswa()
    var fotoKtmUri: Uri? = null
    var fotoProfileUri: Uri? = null

    override fun supportFragmentInjector() = fragmentDispatchingAndroidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.onAttach(this)

        viewBind = DataBindingUtil.setContentView(this, R.layout.activity_register)

        supportFragmentManager.addFragmentWithoutBackStack(
            R.id.cl_register,
            Register1Fragment.newInstance(),
            Register1Fragment.TAG
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
        supportFragmentManager?.removeFragment(tag = tag)
    }

    override fun onFragmentAttached() {
        //notImplemented
    }

    override fun openRegisterFragment() {
        //notImplemented
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