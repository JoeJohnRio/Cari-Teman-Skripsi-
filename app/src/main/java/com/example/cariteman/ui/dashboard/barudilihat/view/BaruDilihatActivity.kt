package com.example.cariteman.ui.dashboard.barudilihat.view

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.BounceInterpolator
import android.view.animation.ScaleAnimation
import android.widget.CompoundButton
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

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_orang)
        presenter.onAttach(this)
        presenter.setKey(Utils.loadData(applicationContext))
        adapterWithList = BaruDilihatListAdapter()

        viewBind = DataBindingUtil.setContentView(this, R.layout.activity_list_orang)

        presenter.getHistoryProfilPkl(true, pageNumber)

        viewBind.tvToolbarTitle.text = "Baru Dilihat"

        viewBind.ivBack.setOnClickListener {
            this.finish()
        }
        viewBind.bFilterLomba.setOnClickListener{
            dataPkl = listOf()
        }
        viewBind.bFilterPkl.setOnClickListener{
            pageNumber = 0
            dataPkl = listOf()
            presenter.getHistoryProfilPkl(true, pageNumber)
        }
        viewBind.bFilterTempatPkl.setOnClickListener {
            dataPkl = listOf()
        }

        viewBind.rvItemPeople.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (!recyclerView.canScrollVertically(1)) {
                    if (lastPage<=pageNumber){
                        pageNumber++
                        presenter.getHistoryProfilPkl(false, pageNumber)
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
        dataPkl = dataPkl + Mapper.historyLombaResponseMapper(responses)
        adapterWithList = BaruDilihatListAdapter()

        adapterWithList.submitList(dataPkl)
        viewBind.rvItemPeople.apply {
            adapter = adapterWithList
            if (layoutManager == null){
                layoutManager = LinearLayoutManager(context)
            }
            adapter?.notifyDataSetChanged()

            val scaleAnimation = ScaleAnimation(
                0.7f,
                1.0f,
                0.7f,
                1.0f,
                Animation.RELATIVE_TO_SELF,
                0.7f,
                Animation.RELATIVE_TO_SELF,
                0.7f
            )
            scaleAnimation?.setDuration(500)
            val bounceInterpolator = BounceInterpolator()
            scaleAnimation?.setInterpolator(bounceInterpolator)


            viewBind.rvItemPeople.tb_favorite.setOnCheckedChangeListener(object :
                View.OnClickListener, CompoundButton.OnCheckedChangeListener {
                override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {
                    p0?.startAnimation(scaleAnimation);
                    Log.d(
                        "fav",
                        "am i here"
                    ) //To change body of created functions use File | Settings | File Templates.
                }

                override fun onClick(p0: View?) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }
            })

            viewBind.rvItemPeople.scrollToPosition(dataPkl.size-responses.size)
        }
    }

    override fun onFragmentAttached() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onFragmentDetached(tag: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}