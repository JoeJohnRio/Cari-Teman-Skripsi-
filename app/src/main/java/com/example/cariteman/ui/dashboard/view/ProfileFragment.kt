package com.example.cariteman.ui.dashboard.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import com.example.cariteman.R
import com.example.cariteman.data.model.MahasiswaHistoryDashboardResponse
import com.example.cariteman.data.model.RekomendasiResponse
import com.example.cariteman.data.model.RelationTempatPklFavorite
import com.example.cariteman.databinding.FragmentProfileBinding
import com.example.cariteman.ui.base.view.BaseFragment
import com.example.cariteman.ui.dashboard.presenter.DashboardPresenter
import com.example.cariteman.ui.pengalaman.view.KelompokActivity
import com.example.cariteman.ui.pengalaman.view.PengalamanActivity
import com.example.cariteman.ui.pengaturan.view.PengaturanActivity
import com.example.cariteman.ui.profile.view.ProfileActivity
import com.example.cariteman.util.Utils
import javax.inject.Inject

class ProfileFragment : BaseFragment(), AdapterView.OnItemSelectedListener, DashboardMVPView{
    private lateinit var viewBind: FragmentProfileBinding

    @Inject
    lateinit var presenter: DashboardPresenter<DashboardMVPView>

    companion object {

        val TAG: String = ProfileFragment::class.java.simpleName
        fun newInstance() = ProfileFragment()
    }
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
            activity?.finish()
            startActivity(intent)
        }

        viewBind.bProfilSaya.setOnClickListener {
            val intent = Intent(context, ProfileActivity::class.java)
            intent.putExtra("isSearchingItself", true)
            startActivity(intent)
        }

        viewBind.bDaftarKelompok.setOnClickListener{
            val intent = Intent(context, KelompokActivity::class.java)
            startActivity(intent)
        }

        viewBind.bPengalaman.setOnClickListener {
            val intent = Intent(context, PengalamanActivity::class.java)
            startActivity(intent)
        }
    }

    override fun setLastPageLimiter(lastPage: Int) {
        //notImplemented
    }

    override fun populateFavoriteProfil(responses: MutableList<RelationTempatPklFavorite>) {
        //notimplemented
    }

    override fun populateRekomendasi(responses: MutableList<RekomendasiResponse>) {
        //notImplemented
    }

    override fun populateLombaDanPklDashboard(responses: List<MahasiswaHistoryDashboardResponse>) {
        //notimplemented
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        //notImplemented
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        //notImplemented
    }
}