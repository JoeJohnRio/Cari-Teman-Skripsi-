package com.example.cariteman.ui.login.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.cariteman.R
import com.example.cariteman.data.model.Login
import com.example.cariteman.databinding.ActivityLoginBinding
import com.example.cariteman.ui.base.view.BaseActivity
import com.example.cariteman.ui.dashboard.view.DashboardBottomViewActivity
import com.example.cariteman.ui.login.presenter.LoginMVPPresenter
import com.example.cariteman.ui.loginadmin.view.LoginAdminActivity
import com.example.cariteman.ui.register.view.RegisterActivity
import com.example.cariteman.util.CommonUtil.emailPattern
import com.example.cariteman.util.Utils
import com.example.cariteman.util.Utils.SHARED_PREFS
import com.example.cariteman.util.Utils.TEXT
import javax.inject.Inject

class LoginActivity : BaseActivity(), LoginMVPView {

    @Inject
    lateinit var presenter: LoginMVPPresenter<LoginMVPView>

    lateinit var viewBind: ActivityLoginBinding
    var hiddenClick = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        presenter.onAttach(this)

        viewBind = DataBindingUtil.setContentView(this, R.layout.activity_login)
        viewBind.etEmail.setText(R.string.email_login)
        viewBind.etPassword.setText(R.string.password_login)
        viewBind.etPasswordConfirmation.setText(R.string.password_confirm_login)

        viewBind.ivHiddenClick.setOnClickListener {
            hiddenClick++
            if (hiddenClick == 5){
                val intent = Intent(this, LoginAdminActivity::class.java)
                Toast.makeText(getApplicationContext(), "Anda telah masuk ke login Admin", Toast.LENGTH_SHORT).show()
                startActivity(intent)
            }
        }

        viewBind.bSignIn.setOnClickListener {
            if (viewBind.etEmail.getText().toString().isEmpty() && viewBind.etPassword.text.toString().isEmpty() && viewBind.etPasswordConfirmation.text.toString().isEmpty()) {
                Toast.makeText(getApplicationContext(), "Penuhi form terlebih dahulu", Toast.LENGTH_SHORT)
                    .show()
            } else {
                if (Utils.isEmailValid(viewBind.etEmail.getText().toString().trim())) {
                    presenter.onLoginBtnClicked(
                        Login(
                            email = viewBind.etEmail.text.toString(),
                            password = viewBind.etPassword.text.toString(),
                            passwordConfirmation = viewBind.etPasswordConfirmation.text.toString()
                        )
                    )
                } else {
                    Toast.makeText(
                        getApplicationContext(),
                        "Email tidak benar",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        viewBind.tvSignUpNotHaveAccount.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    override fun goToDashboard() {
        val intent = Intent(this, DashboardBottomViewActivity::class.java)
        Toast.makeText(getApplicationContext(), "Anda telah masuk", Toast.LENGTH_SHORT).show()
        finish()
        startActivity(intent)
    }

    override fun saveData(apiKey: String) {
        val sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.putString(TEXT, apiKey)
        editor.apply()
    }

    override fun onBackPressed() {
        finish()
    }

    override fun onFragmentDetached(tag: String) {
        //noImplement
    }

    override fun onFragmentAttached() {
        //noImplement
    }
}
