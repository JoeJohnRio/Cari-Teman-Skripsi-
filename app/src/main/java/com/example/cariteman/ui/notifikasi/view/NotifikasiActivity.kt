package com.example.cariteman.ui.notifikasi.view

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cariteman.R
import com.example.cariteman.data.model.MessageKelompok
import com.example.cariteman.data.model.NotifikasiResponse
import com.example.cariteman.databinding.ActivityNotifikasiBinding
import com.example.cariteman.ui.base.view.BaseActivity
import com.example.cariteman.ui.message.view.NotifikasiListAdapter
import com.example.cariteman.ui.notifikasi.presenter.NotifikasiPresenter
import com.example.cariteman.util.Utils
import com.example.cariteman.util.extension.removeFragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject
import kotlin.concurrent.fixedRateTimer


class NotifikasiActivity : BaseActivity(), NotifikasiMVPView, HasSupportFragmentInjector {
    @Inject
    lateinit var presenter: NotifikasiPresenter<NotifikasiMVPView>

    @Inject
    internal lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentDispatchingAndroidInjector

    lateinit var viewBind: ActivityNotifikasiBinding
    lateinit var adapterMessage : NotifikasiListAdapter

    var listOfNotifikasi: MutableList<NotifikasiResponse> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBind = DataBindingUtil.setContentView(this, R.layout.activity_notifikasi)
        presenter.onAttach(this)
        presenter.setKey(Utils.loadData(applicationContext))
        adapterMessage = NotifikasiListAdapter(this)
        presenter.showNotifikasi()

        fixedRateTimer("timer", false, 0L, 3 * 1000) {
            this@NotifikasiActivity.runOnUiThread {
                presenter.showNotifikasi()
            }
        }

        viewBind.ivBack.setOnClickListener {
            finish()
        }
    }

    override fun showNotifikasi(responses: MutableList<NotifikasiResponse>) {
        if (listOfNotifikasi != responses) {
            listOfNotifikasi = responses
            adapterMessage.submitList(responses)

            viewBind.rvNotifikasi.apply {
                if (adapter == null) {
                    adapter = adapterMessage
                }
                if (layoutManager == null) {
                    layoutManager = LinearLayoutManager(context)
                }
                adapter?.notifyDataSetChanged()
            }
        }
    }

    override fun onBackPressed() {
        val count = supportFragmentManager.backStackEntryCount
        if (count == 0) {
            super.onBackPressed()
            //additional code
        } else {
            supportFragmentManager.popBackStack()
            viewBind.rvNotifikasi.visibility = View.VISIBLE
        }
    }

    fun hideRecyclerView(){
        viewBind.rvNotifikasi.visibility = View.GONE
    }

    override fun onFragmentAttached() {
        //notImplemented
    }

    override fun onFragmentDetached(tag: String) {
        supportFragmentManager?.removeFragment(tag = tag)
    }
}