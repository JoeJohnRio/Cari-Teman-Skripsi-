package com.example.cariteman.ui.dashboard.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import com.example.cariteman.R
import com.example.cariteman.databinding.FragmentHomeBinding
import com.example.cariteman.ui.*
import com.example.cariteman.ui.barudilihat.BaruDilihatActivity
import com.example.cariteman.ui.base.view.BaseFragment
import com.example.cariteman.util.Utils
import com.example.cariteman.util.extension.addFragment
import com.google.android.material.button.MaterialButton


class HomeFragment : BaseFragment(), AdapterView.OnItemSelectedListener, DashboardMVPView{

    private lateinit var viewBind: FragmentHomeBinding

    companion object {
        val TAG: String = HomeFragment::class.java.simpleName
        fun newInstance() = HomeFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        viewBind = FragmentHomeBinding.inflate(inflater, container, false)

        Toast.makeText(context, "Home", Toast.LENGTH_LONG).show()
        activity?.title = getString(R.string.title_home)
        Utils.loadData(context!!)
        return viewBind.root
    }

    override fun setUp() {
        viewBind.ivNotifikasiIc.setOnClickListener {
            val intent = Intent(context, NotifikasiActivity::class.java)
            startActivity(intent)
        }

        viewBind.bSearchCariTeman.setOnClickListener{
            val intent = Intent(context, SearchActivity::class.java)
            startActivity(intent)
        }
        viewBind.tvLihatSemuaBaruDilihat.setOnClickListener{
            var intent = Intent(context, BaruDilihatActivity::class.java)
            startActivity(intent)
        }

        viewBind.tvLihatSemuaRecommendation.setOnClickListener{
            var intent = Intent(context, RecommendationActivity::class.java)
            startActivity(intent)
        }

        viewBind.bSearchPkl.setOnClickListener{
            toggleTurnOf(
                viewBind.bSearchPkl, viewBind.bSearchLomba, viewBind.bSearchTempatPkl,
                R.color.orange_high,
                R.color.white
            )
        }
        viewBind.bSearchLomba.setOnClickListener{
            toggleTurnOf(
                viewBind.bSearchLomba, viewBind.bSearchPkl, viewBind.bSearchTempatPkl,
                R.color.orange_high,
                R.color.white
            )
        }
        viewBind.bSearchTempatPkl.setOnClickListener{
            toggleTurnOf(
                viewBind.bSearchTempatPkl, viewBind.bSearchLomba, viewBind.bSearchPkl,
                R.color.orange_high,
                R.color.white
            )
        }

        viewBind.bFilterLombaBaruDilihat.setOnClickListener{
            toggleTurnOf(
                viewBind.bFilterLombaBaruDilihat, viewBind.bFilterPklBaruDilihat, viewBind.bFilterTempatPklBaruDilihat,
                R.color.navy_blue,
                R.color.white
            )
        }
        viewBind.bFilterPklBaruDilihat.setOnClickListener{
            toggleTurnOf(
                viewBind.bFilterPklBaruDilihat, viewBind.bFilterLombaBaruDilihat, viewBind.bFilterTempatPklBaruDilihat,
                R.color.navy_blue,
                R.color.white
            )
        }
        viewBind.bFilterTempatPklBaruDilihat.setOnClickListener{
            toggleTurnOf(
                viewBind.bFilterTempatPklBaruDilihat, viewBind.bFilterPklBaruDilihat, viewBind.bFilterLombaBaruDilihat,
                R.color.navy_blue,
                R.color.white
            )
        }

        viewBind.bFilterLombaRecommendation.setOnClickListener{
            toggleTurnOf(
                viewBind.bFilterLombaRecommendation, viewBind.bFilterPklRecommendation, viewBind.bFilterTempatPklRecommendation,
                R.color.navy_blue,
                R.color.white
            )
        }
        viewBind.bFilterPklRecommendation.setOnClickListener{
            toggleTurnOf(
                viewBind.bFilterPklRecommendation, viewBind.bFilterLombaRecommendation, viewBind.bFilterTempatPklRecommendation,
                R.color.navy_blue,
                R.color.white
            )
        }
        viewBind.bFilterTempatPklRecommendation.setOnClickListener{
            toggleTurnOf(
                viewBind.bFilterTempatPklRecommendation, viewBind.bFilterPklRecommendation, viewBind.bFilterLombaRecommendation,
                R.color.navy_blue,
                R.color.white
            )
        }
    }

    fun toggleTurnOf(turnUp: MaterialButton, turnDown: MaterialButton, turnDown1: MaterialButton, colorOn: Int, colorOff: Int){
        turnUp.setBackgroundColor(resources.getColor(colorOn))
        turnUp.setTextColor(resources.getColor(colorOff))
        turnDown.setBackgroundColor(resources.getColor(colorOff))
        turnDown.setTextColor(resources.getColor(colorOn))
        turnDown1.setBackgroundColor(resources.getColor(colorOff))
        turnDown1.setTextColor(resources.getColor(colorOn))
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        //notImplemented
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        //notImplemented
    }
}