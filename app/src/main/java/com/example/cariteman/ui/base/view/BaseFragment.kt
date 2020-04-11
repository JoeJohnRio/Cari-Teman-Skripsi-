package com.example.cariteman.ui.base.view

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.cariteman.ui.base.MVPView
import com.example.cariteman.util.CommonUtil
import com.example.cariteman.util.Utils.SHARED_PREFS
import dagger.android.support.AndroidSupportInjection

abstract class BaseFragment : Fragment(), MVPView {

    private var parentActivity: BaseActivity? = null
    private var progressDialog: ProgressDialog? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity) {
            val activity = context as BaseActivity?
            this.parentActivity = activity
            activity?.onFragmentAttached()
        }
    }

    fun getKey(): String {
            val sharedPreferences = context?.getSharedPreferences(SHARED_PREFS,
                AppCompatActivity.MODE_PRIVATE
            )
        return sharedPreferences.toString()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        performDependencyInjection()
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
    }

    override fun hideProgress() {
        if (progressDialog != null && progressDialog?.isShowing!!) {
            progressDialog?.cancel()
        }
    }

    override fun showMessageToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    override fun showProgress() {
        hideProgress()
        progressDialog = CommonUtil.showLoadingDialog(this.context)
    }

    fun getBaseActivity() = parentActivity

    private fun performDependencyInjection() = AndroidSupportInjection.inject(this)

    interface CallBack {
        fun onFragmentAttached()
        fun onFragmentDetached(tag: String)
    }

    abstract fun setUp()
}