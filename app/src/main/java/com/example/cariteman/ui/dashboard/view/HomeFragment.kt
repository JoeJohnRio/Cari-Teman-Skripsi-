package com.example.cariteman.ui.dashboard.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.cariteman.R
import com.example.cariteman.data.model.MahasiswaHistoryDashboardResponse
import com.example.cariteman.data.model.RelationTempatPklFavorite
import com.example.cariteman.databinding.FragmentHomeBinding
import com.example.cariteman.ui.*
import com.example.cariteman.ui.dashboard.barudilihat.view.BaruDilihatActivity
import com.example.cariteman.ui.base.view.BaseFragment
import com.example.cariteman.ui.dashboard.presenter.DashboardPresenter
import com.example.cariteman.ui.pengalaman.view.SearchActivity
import com.example.cariteman.util.Mapper
import com.example.cariteman.util.Utils
import com.example.cariteman.util.Utils.toggleThreeButton
import kotlinx.android.synthetic.main.item_profile_dashboard.view.*
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

        viewBind.incItemKosong.iv_profil_pic
        Glide.with(context).load(url)
            .into(viewBind.incItemKosong.iv_profil_pic)
        Glide.with(context)
            .load(url)
            .into(viewBind.incItemKosong.civ_image_one)
        Glide.with(context)
            .load(url)
            .into(viewBind.incItemKosong.civ_image_two)
        Glide.with(context)
            .load(url)
            .into(viewBind.incItemKosong.civ_image_three)
        viewBind.incItemKosong.tv_recommendation_total?.text = "0 of Recommendation"

        viewBind.incItemKosong.tv_jabatan?.text = "Belum mengikuti organisasi"

        viewBind.incItemKosong.tv_riwayat_lomba?.text = "Belum mengikuti kompetisi"
        viewBind.incItemKosong.tv_name?.text = "Tidak ada data"
    }

    var url = "https://cdn.clipart.email/23d5eca3a775fad9d1e33d343ae57328_silhouette-man-face-at-getdrawingscom-free-for-personal-use-_1000-563.jpeg"

    override fun populateLombaDanPklDashboard(responses: List<MahasiswaHistoryDashboardResponse>) {
        if(responses.isNullOrEmpty()){
            viewBind.incItemKosong.visibility = View.VISIBLE
            viewBind.rvDashboardBaruLihat.visibility = View.GONE
        }else{
            viewBind.incItemKosong.visibility = View.GONE
            viewBind.rvDashboardBaruLihat.visibility = View.VISIBLE
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
        }
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