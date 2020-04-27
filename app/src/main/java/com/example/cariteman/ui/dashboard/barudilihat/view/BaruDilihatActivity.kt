package com.example.cariteman.ui.dashboard.barudilihat.view

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cariteman.R
import com.example.cariteman.data.model.MahasiswaHistoryDashboardResponse
import com.example.cariteman.databinding.ActivityListOrangBinding
import com.example.cariteman.ui.base.view.BaseActivity
import com.example.cariteman.ui.dashboard.barudilihat.presenter.BaruDilihatPresenter
import com.example.cariteman.util.Mapper
import com.example.cariteman.util.Utils
import dagger.android.AndroidInjector
import dagger.android.HasActivityInjector
import kotlinx.android.synthetic.main.item_people_and_place.view.*
import javax.inject.Inject

class BaruDilihatActivity : BaseActivity(), BaruDilihatMVPView, HasActivityInjector {

    @Inject
    lateinit var presenter: BaruDilihatPresenter<BaruDilihatMVPView>
    lateinit var dataPkl: List<MahasiswaHistoryDashboardResponse?>
    private lateinit var adapterWithList: BaruDilihatListAdapter
    private lateinit var viewBind: ActivityListOrangBinding
    var pageNumber: Int = 0
    var lastPage: Int = 2
    var type: Int = 0

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_orang)
        presenter.onAttach(this)
        presenter.setKey(Utils.loadData(applicationContext))
        adapterWithList = BaruDilihatListAdapter(applicationContext, presenter)

        viewBind = DataBindingUtil.setContentView(this, R.layout.activity_list_orang)

        presenter.getHistoryProfilPkl(true, pageNumber)
        Utils.toggleThreeButton(
            viewBind.bFilterPkl,
            viewBind.bFilterLomba,
            viewBind.bFilterTempatPkl,
            R.color.navy_blue,
            R.color.white,
            resources
        )

        viewBind.ivBack.setOnClickListener {
            this.finish()
        }

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
                pageNumber = 0
                dataPkl = listOf()
                presenter.getHistoryProfilPkl(true, pageNumber)
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
                pageNumber = 0
                dataPkl = listOf()
                presenter.getHistoryProfilLomba(true, pageNumber)
            }
        }

        viewBind.bFilterTempatPkl.setOnClickListener {
            if (type != 2) {
                type = 2
                Utils.toggleThreeButton(
                    viewBind.bFilterTempatPkl,
                    viewBind.bFilterLomba,
                    viewBind.bFilterPkl,
                    R.color.navy_blue,
                    R.color.white,
                    resources
                )
                pageNumber = 0
                presenter.getHistoryProfilTempatPkl(true, pageNumber)
                dataPkl = listOf()
            }
        }

        viewBind.rvItemPeople.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (!recyclerView.canScrollVertically(1)) {
                    if (lastPage >= pageNumber) {
                        Log.d("onScrolled", "Sedang scroll")
                        if (type == 0) {
                            presenter.getHistoryProfilPkl(false, pageNumber+1)
                            pageNumber++
                        } else if (type == 1) {
                            presenter.getHistoryProfilLomba(false, pageNumber+1)
                            pageNumber++
                        } else if (type == 2) {
                            presenter.getHistoryProfilTempatPkl(false, pageNumber+1)
                            pageNumber++
                        }
                    }
                }
            }
        })

        dataPkl = listOf()
    }

    override fun setLastPageLimiter(lastPage: Int) {
        this.lastPage = lastPage
    }

    override fun populateBaruDilihatProfil(responses: List<MahasiswaHistoryDashboardResponse>) {
        if (responses.isNullOrEmpty()){
            viewBind.rvItemPeople.visibility = View.GONE
            viewBind.incItemKosongBaruDilihat.visibility = View.VISIBLE
        }else{
            viewBind.rvItemPeople.visibility = View.VISIBLE
            viewBind.incItemKosongBaruDilihat.visibility = View.GONE

            dataPkl = dataPkl + Mapper.historyResponseMapper(responses)

            adapterWithList.submitList(dataPkl)
            viewBind.rvItemPeople.apply {
                if (adapter == null) {
                    adapter = adapterWithList
                }
                if (layoutManager == null) {
                    layoutManager = LinearLayoutManager(context)
                }
                adapter?.notifyDataSetChanged()

                viewBind.rvItemPeople.tb_favorite.setOnClickListener{
                    super.showMessageToast("FAVORITE")
                }
            }
        }
    }

    override fun onFragmentAttached() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onFragmentDetached(tag: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}