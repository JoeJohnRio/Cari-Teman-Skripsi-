package com.example.cariteman.ui.search.filtersearch.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cariteman.R
import com.example.cariteman.data.model.SearchHistory
import com.example.cariteman.databinding.FragmentHistorySearchBinding
import com.example.cariteman.ui.base.view.BaseFragment
import com.example.cariteman.ui.pengalaman.pengalamanhome.presenter.SearchHistoryPresenter
import com.example.cariteman.ui.pengalaman.pengalamanhome.view.SearchHistoryListAdapter
import com.example.cariteman.ui.pengalaman.view.SearchActivity
import com.example.cariteman.util.Mapper
import com.example.cariteman.util.Utils
import com.example.cariteman.util.extension.addFragmentWithBackStack
import javax.inject.Inject

class SearchHistoryFragment : BaseFragment(),
    SearchHistoryMVPView {

    @Inject
    lateinit var presenter: SearchHistoryPresenter<SearchHistoryMVPView>

    lateinit var searchHistoryListAdapter: SearchHistoryListAdapter
    lateinit var viewBind: FragmentHistorySearchBinding

    companion object {
        internal val TAG = "SearchHistory"
        fun newInstance(): SearchHistoryFragment {
            return SearchHistoryFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBind = FragmentHistorySearchBinding.inflate(inflater, container, false)
        presenter.onAttach(this)
        context.let { presenter.setKey(Utils.loadData(it!!)) }

        searchHistoryListAdapter = SearchHistoryListAdapter(presenter, this)

        presenter.showSearchHistory()
//        (getBaseActivity() as (SearchActivity)).filterDetails

        return viewBind.root
    }

    override fun showSearchHistory(searchHistory: MutableList<SearchHistory>) {

        searchHistoryListAdapter.submitList(Mapper.searchHistoryDownTo8(searchHistory))
        viewBind.rvDaftarHistorySearch.apply {
            if (adapter == null) {
                adapter = searchHistoryListAdapter
            }
            if (layoutManager == null) {
                layoutManager = LinearLayoutManager(context)
            }
            adapter?.notifyDataSetChanged()
        }
    }

    override fun searchWithFilter(searchHistory: SearchHistory) {

        (activity as SearchActivity).filterDetails.let {
            it.keyword = searchHistory.name
            it.preferensi = searchHistory.searchType
        }

        getBaseActivity()?.supportFragmentManager?.addFragmentWithBackStack(
            R.id.cl_search,
            FrontProfileFragment.newInstance(),
            FrontProfileFragment.TAG
        )
    }

    override fun searchWithSearchHistory(searchHistory: SearchHistory) {

        (activity as SearchActivity).filterDetails.let {
            it.keyword = searchHistory.name
            it.preferensi = searchHistory.searchType
        }

        getBaseActivity()?.supportFragmentManager?.addFragmentWithBackStack(
            R.id.cl_search,
            FrontProfileFragment.newInstance(),
            FrontProfileFragment.TAG
        )
    }

    override fun setUp() {

    }

}
