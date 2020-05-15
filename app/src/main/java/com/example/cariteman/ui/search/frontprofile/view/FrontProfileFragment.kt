package com.example.cariteman.ui.search.filtersearch.view

import android.R
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cariteman.data.model.*
import com.example.cariteman.databinding.FragmentFilterBinding
import com.example.cariteman.databinding.FragmentFrontProfileBinding
import com.example.cariteman.databinding.FragmentHistorySearchBinding
import com.example.cariteman.ui.base.view.BaseFragment
import com.example.cariteman.ui.pengalaman.pengalamanhome.presenter.FilterSearchPresenter
import com.example.cariteman.ui.pengalaman.pengalamanhome.presenter.FrontProfilePresenter
import com.example.cariteman.ui.pengalaman.view.SearchActivity
import com.example.cariteman.ui.search.frontprofile.view.FrontProfileListAdapter
import com.example.cariteman.util.Mapper
import com.example.cariteman.util.Utils
import javax.inject.Inject

class FrontProfileFragment : BaseFragment(),
    FrontProfileMVPView {

    @Inject
    lateinit var presenter: FrontProfilePresenter<FrontProfileMVPView>

    lateinit var frontProfileListAdapter: FrontProfileListAdapter
    lateinit var viewBind: FragmentFrontProfileBinding
    lateinit var activity: SearchActivity

    companion object {
        internal val TAG = "SearchHistory"
        fun newInstance(): FrontProfileFragment {
            return FrontProfileFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBind = FragmentFrontProfileBinding.inflate(inflater, container, false)
        presenter.onAttach(this)
        context.let { presenter.setKey(Utils.loadData(it!!)) }

        activity = getBaseActivity() as SearchActivity

        frontProfileListAdapter =
            FrontProfileListAdapter(presenter)

        val bundle: Bundle? = arguments
        val keyword = bundle?.getString("keyword") ?: activity.filterDetails.keyword
        val searchType = bundle?.getInt("searchType",   0) ?: activity.filterDetails.preferensi

        if (searchType == 0 || searchType == 1) {
            activity.filterDetails.let {
                it.keyword = keyword
                it.preferensi = searchType
            }
            presenter.searchMahasiswa(
                activity.filterDetails

            )
        } else if (searchType == 2) {
            presenter.searchTempatPkl(TempatPklSearchFilter(keyword = keyword))
        }

        activity.searchEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                //notImplemented
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //notImplemented
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Handler().postDelayed({
                    activity.filterDetails.let {
                        it.keyword = s.toString()
                    }
                    showMessageToast(s.toString())
                    presenter.searchMahasiswa(activity.filterDetails)
                }, 1000)
            }
        })
//        (getBaseActivity() as (SearchActivity)).filterDetails

        return viewBind.root
    }

    override fun handleSearchMahasiswa(mahasiswaResponse: SearchFilter) {
        frontProfileListAdapter.submitList(
            Mapper.searchFilterToFrontProfileResponse(mahasiswaResponse)
        )

        viewBind.rvSearchResult.apply {
            if (adapter == null) {
                adapter = frontProfileListAdapter
            }
            if (layoutManager == null) {
                layoutManager = LinearLayoutManager(context)
            }
            adapter?.notifyDataSetChanged()
        }
    }

    override fun handleSearchTempatPkl(tempatPklResponse: SearchFilter) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setUp() {

    }

}
