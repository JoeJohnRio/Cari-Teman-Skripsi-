package com.example.cariteman.ui.dashboard.view

import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cariteman.R
import com.example.cariteman.data.model.MahasiswaHistoryDashboardResponse
import com.example.cariteman.data.model.RelationTempatPklFavorite
import com.example.cariteman.databinding.FragmentHomeBinding
import com.example.cariteman.ui.*
import com.example.cariteman.ui.dashboard.barudilihat.view.BaruDilihatActivity
import com.example.cariteman.ui.base.view.BaseFragment
import com.example.cariteman.ui.dashboard.presenter.DashboardPresenter
import com.example.cariteman.ui.profile.view.ProfileActivity
import com.example.cariteman.util.Mapper
import com.example.cariteman.util.Utils
import com.example.cariteman.util.Utils.toggleThreeButton
import com.google.android.material.button.MaterialButton
import javax.inject.Inject


class HomeFragment : BaseFragment(), AdapterView.OnItemSelectedListener, DashboardMVPView{
    @Inject
    lateinit var presenter: DashboardPresenter<DashboardMVPView>
    private lateinit var viewBind: FragmentHomeBinding
    private lateinit var adapterWithList: ProfilDashboardPklListAdapter

    companion object {
        val TAG: String = HomeFragment::class.java.simpleName
        fun newInstance() = HomeFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewBind = FragmentHomeBinding.inflate(inflater, container, false)
        activity?.title = getString(R.string.title_home)
        toggleThreeButton(
            viewBind.bFilterPklBaruDilihat, viewBind.bSearchLomba, viewBind.bSearchTempatPkl,
            R.color.navy_blue,
            R.color.white, resources
        )

        presenter.setKey(Utils.loadData(context!!))
        presenter.onAttach(this)
        return viewBind.root
    }

    override fun setUp() {
        presenter.getHistoryDashboardPklResponse()
        viewBind.ivNotifikasiIc.setOnClickListener {
            val intent = Intent(context, NotifikasiActivity::class.java)
            startActivity(intent)
        }

        viewBind.bSearchCariTeman.setOnClickListener{
            val intent = Intent(context, SearchActivity::class.java)
            startActivity(intent)
        }
        viewBind.tvLihatSemuaBaruDilihat.setOnClickListener{
            val intent = Intent(context, BaruDilihatActivity::class.java)
            startActivity(intent)
        }

        viewBind.tvLihatSemuaRecommendation.setOnClickListener{
            val intent = Intent(context, RecommendationActivity::class.java)
            startActivity(intent)
        }

        viewBind.bSearchPkl.setOnClickListener{
            toggleThreeButton(
                viewBind.bSearchPkl, viewBind.bSearchLomba, viewBind.bSearchTempatPkl,
                R.color.orange_high,
                R.color.white, resources
            )
        }
        viewBind.bSearchLomba.setOnClickListener{
            toggleThreeButton(
                viewBind.bSearchLomba, viewBind.bSearchPkl, viewBind.bSearchTempatPkl,
                R.color.orange_high,
                R.color.white, resources
            )
        }
        viewBind.bSearchTempatPkl.setOnClickListener{
            toggleThreeButton(
                viewBind.bSearchTempatPkl, viewBind.bSearchLomba, viewBind.bSearchPkl,
                R.color.orange_high,
                R.color.white, resources
            )
        }

        viewBind.bFilterLombaBaruDilihat.setOnClickListener{
            toggleThreeButton(
                viewBind.bFilterLombaBaruDilihat, viewBind.bFilterPklBaruDilihat, viewBind.bFilterTempatPklBaruDilihat,
                R.color.navy_blue,
                R.color.white, resources
            )
            presenter.getHistoryDashboardLombaResponse()
        }
        viewBind.bFilterPklBaruDilihat.setOnClickListener{
            toggleThreeButton(
                viewBind.bFilterPklBaruDilihat, viewBind.bFilterLombaBaruDilihat, viewBind.bFilterTempatPklBaruDilihat,
                R.color.navy_blue,
                R.color.white, resources
            )
            presenter.getHistoryDashboardPklResponse()
        }
        viewBind.bFilterTempatPklBaruDilihat.setOnClickListener{
            toggleThreeButton(
                viewBind.bFilterTempatPklBaruDilihat, viewBind.bFilterPklBaruDilihat, viewBind.bFilterLombaBaruDilihat,
                R.color.navy_blue,
                R.color.white, resources
            )
            presenter.getHistoryDashboardTempatPklResponse()
        }

        viewBind.bFilterLombaRecommendation.setOnClickListener{
            toggleThreeButton(
                viewBind.bFilterLombaRecommendation, viewBind.bFilterPklRecommendation, viewBind.bFilterTempatPklRecommendation,
                R.color.navy_blue,
                R.color.white, resources
            )
        }
        viewBind.bFilterPklRecommendation.setOnClickListener{
            toggleThreeButton(
                viewBind.bFilterPklRecommendation, viewBind.bFilterLombaRecommendation, viewBind.bFilterTempatPklRecommendation,
                R.color.navy_blue,
                R.color.white, resources
            )
        }
        viewBind.bFilterTempatPklRecommendation.setOnClickListener{
            toggleThreeButton(
                viewBind.bFilterTempatPklRecommendation, viewBind.bFilterPklRecommendation, viewBind.bFilterLombaRecommendation,
                R.color.navy_blue,
                R.color.white, resources
            )
        }
    }

    override fun populateLombaDanPklDashboard(responses: List<MahasiswaHistoryDashboardResponse>) {
        val data = Mapper.dashboardHistoryLombaResponseMapper(responses)
        adapterWithList = ProfilDashboardPklListAdapter()

        adapterWithList.submitList(data)
        viewBind.rvDashboardBaruLihat.apply {
            adapter = adapterWithList
            if (layoutManager == null){
                layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            }
            adapter?.notifyDataSetChanged()
        }
            viewBind.rvDashboardBaruLihat.smoothScrollToPosition(4)

            viewBind.rvDashboardBaruLihat.smoothScrollToPosition(0)
    }

    override fun setLastPageLimiter(lastPage: Int) {
        //notImplemented
    }

    override fun populateFavoriteProfil(responses: MutableList<RelationTempatPklFavorite>) {
        //notimplemented
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        //notImplemented
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        //notImplemented
    }
}