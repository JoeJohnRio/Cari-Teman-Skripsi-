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
import com.example.cariteman.ui.register.view.Register1Activity
import com.example.cariteman.ui.login.presenter.LoginMVPPresenter
import com.example.cariteman.util.CommonUtil.emailPattern
import com.example.cariteman.util.Utils.SHARED_PREFS
import com.example.cariteman.util.Utils.TEXT
import javax.inject.Inject

class LoginActivity : BaseActivity(), LoginMVPView{

    @Inject
    lateinit var presenter: LoginMVPPresenter<LoginMVPView>

    lateinit var viewBind: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        presenter.onAttach(this)

        viewBind = DataBindingUtil.setContentView(this, R.layout.activity_login)
        viewBind.etEmail.setText(R.string.email_login)
        viewBind.etPassword.setText(R.string.password_login)
        viewBind.etPasswordConfirmation.setText(R.string.password_confirm_login)

        viewBind.bSignIn.setOnClickListener{
            if(viewBind.etEmail.getText().toString().isEmpty()) {
                Toast.makeText(getApplicationContext(),"enter email address",Toast.LENGTH_SHORT).show();
            }else {
                if (viewBind.etEmail.getText().toString().trim().matches(emailPattern.toRegex())) {

                } else {
                    Toast.makeText(getApplicationContext(),"Invalid email address", Toast.LENGTH_SHORT).show();
                }
            }

            presenter.onLoginBtnClicked(Login(email = viewBind.etEmail.text.toString(), password = viewBind.etPassword.text.toString(), passwordConfirmation = viewBind.etPasswordConfirmation.text.toString() ))
        }

        viewBind.tvSignUpNotHaveAccount.setOnClickListener {
            val intent = Intent(this, Register1Activity::class.java)
            startActivity(intent)
        }
    }
    override fun goToDashboard() {
        val intent = Intent(this, DashboardBottomViewActivity::class.java)
        Toast.makeText(getApplicationContext(),"Anda telah masuk",Toast.LENGTH_SHORT).show();
        startActivity(intent)
    }

    override fun saveData(apiKey: String) {
        val sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.putString(TEXT, apiKey)
        editor.apply()
    }

    override fun onFragmentDetached(tag: String) {
        //noImplement
    }

    override fun onFragmentAttached() {
        //noImplement
    }
}
