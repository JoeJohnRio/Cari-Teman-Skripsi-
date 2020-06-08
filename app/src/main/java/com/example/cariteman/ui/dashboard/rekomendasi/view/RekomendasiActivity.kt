package com.example.cariteman.ui.dashboard.barudilihat.view

import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cariteman.R
import com.example.cariteman.data.model.RekomendasiResponse
import com.example.cariteman.databinding.ActivityListOrangBinding
import com.example.cariteman.ui.base.view.BaseActivity
import com.example.cariteman.ui.dashboard.barudilihat.presenter.RekomendasiPresenter
import com.example.cariteman.util.Utils
import dagger.android.AndroidInjector
import dagger.android.HasActivityInjector
import kotlinx.android.synthetic.main.item_people_and_place.view.*
import javax.inject.Inject

class RekomendasiActivity : BaseActivity(), RekomendasiMVPView, HasActivityInjector {

    @Inject
    lateinit var presenter: RekomendasiPresenter<RekomendasiMVPView>
    lateinit var mutablelistOfRekomendasi: MutableList<RekomendasiResponse?>
    private lateinit var adapterWithList: ListRekomendasiListAdapter
    private lateinit var viewBind: ActivityListOrangBinding

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_orang)
        presenter.onAttach(this)
        presenter.setKey(Utils.loadData(applicationContext))
        adapterWithList = ListRekomendasiListAdapter(applicationContext, presenter)

        viewBind = DataBindingUtil.setContentView(this, R.layout.activity_list_orang)

        viewBind.tvToolbarTitle.setText("Rekomendasi")
        presenter.getHistoryProfilPkl(RekomendasiResponse(typeOfRecommendation = 1))
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
                Utils.toggleThreeButton(
                    viewBind.bFilterPkl,
                    viewBind.bFilterLomba,
                    viewBind.bFilterTempatPkl,
                    R.color.navy_blue,
                    R.color.white,
                    resources
                )
                mutablelistOfRekomendasi = mutableListOf()
                presenter.getHistoryProfilPkl(RekomendasiResponse(typeOfRecommendation = 1))
        }

        viewBind.bFilterLomba.setOnClickListener {
                Utils.toggleThreeButton(
                    viewBind.bFilterLomba,
                    viewBind.bFilterPkl,
                    viewBind.bFilterTempatPkl,
                    R.color.navy_blue,
                    R.color.white,
                    resources
                )
                mutablelistOfRekomendasi = mutableListOf()
                presenter.getHistoryProfilPkl(RekomendasiResponse(typeOfRecommendation = 2))
        }

        viewBind.bFilterTempatPkl.setOnClickListener {
                Utils.toggleThreeButton(
                    viewBind.bFilterTempatPkl,
                    viewBind.bFilterLomba,
                    viewBind.bFilterPkl,
                    R.color.navy_blue,
                    R.color.white,
                    resources
                )
                presenter.getHistoryProfilPkl(RekomendasiResponse(typeOfRecommendation = 3))
                mutablelistOfRekomendasi = mutableListOf()
        }

        mutablelistOfRekomendasi = mutableListOf()
    }

    override fun populateRekomendasiProfile(responses: MutableList<RekomendasiResponse>) {
        if (responses.isNullOrEmpty()){
            viewBind.rvItemPeople.visibility = View.GONE
            viewBind.incItemKosongBaruDilihat.visibility = View.VISIBLE
        }else{
            viewBind.rvItemPeople.visibility = View.VISIBLE
            viewBind.incItemKosongBaruDilihat.visibility = View.GONE

            mutablelistOfRekomendasi = responses.toMutableList()

            adapterWithList.submitList(mutablelistOfRekomendasi)
            viewBind.rvItemPeople.apply {
                if (adapter == null) {
                    adapter = adapterWithList
                }
                if (layoutManager == null) {
                    layoutManager = LinearLayoutManager(context) as RecyclerView.LayoutManager?
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