package com.example.cariteman.ui.dashboard.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import com.example.cariteman.R
import com.example.cariteman.data.model.MahasiswaHistoryDashboardResponse
import com.example.cariteman.data.model.RelationTempatPklFavorite
import com.example.cariteman.databinding.FragmentProfileBinding
import com.example.cariteman.ui.DaftarKelompokActivity
import com.example.cariteman.ui.PengaturanActivity
import com.example.cariteman.ui.UserProfilActivity
import com.example.cariteman.ui.base.view.BaseFragment
import com.example.cariteman.ui.dashboard.presenter.DashboardPresenter
import com.example.cariteman.ui.pengalaman.PengalamanActivity
import com.example.cariteman.util.Utils
import com.example.cariteman.util.extension.addFragment
import javax.inject.Inject

class ProfileFragment : BaseFragment(), AdapterView.OnItemSelectedListener, DashboardMVPView{
    override fun setLastPageLimiter(lastPage: Int) {
        //notImplemented
    }

    override fun populateFavoriteProfil(responses: MutableList<RelationTempatPklFavorite>) {
        //notimplemented
    }

    override fun populateLombaDanPklDashboard(responses: List<MahasiswaHistoryDashboardResponse>) {
        //notimplemented
    }

    private lateinit var viewBind: FragmentProfileBinding

    @Inject
    lateinit var presenter: DashboardPresenter<DashboardMVPView>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        viewBind = FragmentProfileBinding.inflate(inflater, container, false)

//        Toast.makeText(context, "Profile", Toast.LENGTH_LONG).show()
        activity?.title = getString(R.string.title_home)
        presenter.setKey(Utils.loadData(context!!))
        presenter.onAttach(this)
        return viewBind.root
    }

    override fun setUp() {
        viewBind.bPengaturan.setOnClickListener {
            val intent = Intent(context, PengaturanActivity::class.java)
            startActivity(intent)
        }

        viewBind.bProfilSaya.setOnClickListener {
            val intent = Intent(context, UserProfilActivity::class.java)
            startActivity(intent)
        }

        viewBind.bDaftarKelompok.setOnClickListener{
            val intent = Intent(context, DaftarKelompokActivity::class.java)
            startActivity(intent)
        }

        viewBind.bPengalaman.setOnClickListener {
            val intent = Intent(context, PengalamanActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        //notImplemented
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        //notImplemented
    }

    companion object {
        val TAG: String = ProfileFragment::class.java.simpleName
        fun newInstance() = ProfileFragment()
    }
}