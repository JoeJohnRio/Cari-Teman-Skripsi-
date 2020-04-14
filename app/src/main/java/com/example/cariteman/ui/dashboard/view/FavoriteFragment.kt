package com.example.cariteman.ui.dashboard.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cariteman.R
import com.example.cariteman.data.model.MahasiswaHistoryDashboardResponse
import com.example.cariteman.data.model.RelationTempatPklFavorite
import com.example.cariteman.databinding.FragmentFavoriteBinding
import com.example.cariteman.ui.base.view.BaseFragment
import com.example.cariteman.ui.dashboard.barudilihat.view.FavoriteListAdapter
import com.example.cariteman.ui.dashboard.presenter.DashboardPresenter
import com.example.cariteman.util.Mapper
import com.example.cariteman.util.Utils
import kotlinx.android.synthetic.main.item_people_and_place.view.*
import javax.inject.Inject

class FavoriteFragment : BaseFragment(), AdapterView.OnItemSelectedListener, DashboardMVPView {


    @Inject
    lateinit var presenter: DashboardPresenter<DashboardMVPView>
    lateinit var dataPkl: List<RelationTempatPklFavorite?>
    private lateinit var viewBind: FragmentFavoriteBinding
    var pageNumber: Int = 1
    var lastPage: Int = 2
    var type: Int = 0

    private lateinit var adapterWithList: FavoriteListAdapter

    companion object {
        val TAG: String = FavoriteFragment::class.java.simpleName
        fun newInstance() = FavoriteFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBind = FragmentFavoriteBinding.inflate(inflater, container, false)
        activity?.title = getString(R.string.title_home)
        presenter.setKey(Utils.loadData(context!!))
        presenter.onAttach(this)
        adapterWithList = FavoriteListAdapter(presenter)
        presenter.getFavoriteFriendPklResponse(true, 1)
        dataPkl = listOf()

        return viewBind.root
    }

    override fun setUp() {
        viewBind.ivBack.visibility = View.GONE
        viewBind.tvToolbarTitle.text = getString(R.string.favorite)

        Utils.toggleThreeButton(
            viewBind.bFilterPkl,
            viewBind.bFilterLomba,
            viewBind.bFilterTempatPkl,
            R.color.navy_blue,
            R.color.white,
            resources
        )

        viewBind.bFilterPkl.setOnClickListener {
            if (type != 0) {
                type = 0
                Utils.toggleThreeButton(
                    viewBind.bFilterPkl,
                    viewBind.bFilterLomba,
                    viewBind.bFilterTempatPkl,
                    R.color.navy_blue,
                    R.color.white,
                    resources
                )
                pageNumber = 1
                dataPkl = listOf()
                presenter.getFavoriteFriendPklResponse(true, pageNumber)
            }
        }

        viewBind.bFilterLomba.setOnClickListener {
            if (type != 1) {
                type = 1
                Utils.toggleThreeButton(
                    viewBind.bFilterLomba,
                    viewBind.bFilterPkl,
                    viewBind.bFilterTempatPkl,
                    R.color.navy_blue,
                    R.color.white,
                    resources
                )
                pageNumber = 1
                dataPkl = listOf()
                presenter.getFavoriteFriendLombaResponse(true, pageNumber)
            }
        }

        viewBind.bFilterTempatPkl.setOnClickListener {
            if (type != 2) {
                type = 2
                Utils.toggleThreeButton(
                    viewBind.bFilterTempatPkl,
                    viewBind.bFilterPkl,
                    viewBind.bFilterLomba,
                    R.color.navy_blue,
                    R.color.white,
                    resources
                )
                pageNumber = 1
                dataPkl = listOf()
                presenter.getFavoriteTempatPklResponse(true, pageNumber)
            }
        }

        viewBind.rvItemPeople.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (!recyclerView.canScrollVertically(1)) {
                    if (lastPage > pageNumber) {
                        Log.d("onScrolled", "Sedang scroll")
                        if (type == 0) {
                            presenter.getFavoriteFriendPklResponse(false, pageNumber+1)
                            pageNumber++
                        } else if (type == 1) {
                            presenter.getFavoriteFriendLombaResponse(false, pageNumber+1)
                            pageNumber++
                        } else if (type == 2) {
                            presenter.getFavoriteTempatPklResponse(false, pageNumber+1)
                            pageNumber++
                        }
                    }
                }
            }
        })

    }

    override fun setLastPageLimiter(lastPage: Int) {
        this.lastPage = lastPage
    }

    override fun populateFavoriteProfil(responses: MutableList<RelationTempatPklFavorite>) {
        dataPkl = dataPkl + Mapper.favoriteDashboardResponseMapper(responses)

        adapterWithList.submitList(dataPkl)
        viewBind.rvItemPeople.apply {
            if (adapter == null) {
                adapter = adapterWithList
            }
            if (layoutManager == null) {
                layoutManager = LinearLayoutManager(context)
            }
            adapter?.notifyDataSetChanged()

        }
    }

        override fun onNothingSelected(parent: AdapterView<*>?) {
            //notImplemented
        }

        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            //notImplemented
        }

        override fun populateLombaDanPklDashboard(responses: List<MahasiswaHistoryDashboardResponse>) {
            //notImplemented
        }
    }
