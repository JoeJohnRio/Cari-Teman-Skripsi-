package com.example.cariteman.ui.dashboard.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import com.example.cariteman.R
import com.example.cariteman.data.model.MahasiswaHistoryDashboardResponse
import com.example.cariteman.databinding.FragmentFavoriteBinding
import com.example.cariteman.ui.base.view.BaseFragment
import com.example.cariteman.ui.dashboard.presenter.DashboardPresenter
import com.example.cariteman.util.Utils
import javax.inject.Inject

class FavoriteFragment : BaseFragment(), AdapterView.OnItemSelectedListener, DashboardMVPView {
    override fun populateLombaDanPklDashboard(responses: List<MahasiswaHistoryDashboardResponse>) {
        //notImplemented
    }

    private lateinit var viewBind: FragmentFavoriteBinding

    @Inject
    lateinit var presenter: DashboardPresenter<DashboardMVPView>

    companion object {
        val TAG: String = FavoriteFragment::class.java.simpleName
        fun newInstance() = FavoriteFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        viewBind = FragmentFavoriteBinding.inflate(inflater, container, false)
//        Toast.makeText(context, "Favorite", Toast.LENGTH_LONG).show()
        activity?.title = getString(R.string.title_home)
        presenter.setKey(Utils.loadData(context!!))
        presenter.onAttach(this)
        return viewBind.root
    }

    override fun setUp() {
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        //notImplemented
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        //notImplemented
    }
}
