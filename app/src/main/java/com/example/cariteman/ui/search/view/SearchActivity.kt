package com.example.cariteman.ui.pengalaman.view

import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.cariteman.R
import com.example.cariteman.data.model.*
import com.example.cariteman.databinding.ActivitySearchBinding
import com.example.cariteman.ui.base.view.BaseActivity
import com.example.cariteman.ui.dashboard.presenter.SearchPresenter
import com.example.cariteman.ui.search.filtersearch.view.AddTempatPklFragment
import com.example.cariteman.util.Utils
import com.example.cariteman.util.extension.removeFragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject
import com.example.cariteman.ui.search.filtersearch.view.FilterSearchFragment
import com.example.cariteman.ui.search.filtersearch.view.FrontProfileFragment
import com.example.cariteman.ui.search.filtersearch.view.SearchHistoryFragment
import com.example.cariteman.util.extension.addFragmentWithBackStack
import com.example.cariteman.util.extension.addFragmentWithoutBackStack
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class SearchActivity : BaseActivity(), SearchMVPView, HasSupportFragmentInjector {

    @Inject
    lateinit var presenter: SearchPresenter<SearchMVPView>

    @Inject
    internal lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    lateinit var viewBind: ActivitySearchBinding
    lateinit var searchEditText: TextInputEditText
    lateinit var filterSearchButton: MaterialButton
    var filterDetails = MahasiswaSearchFilter(keyword = "", preferensi = 0)
    var filterTempatPkl = TempatPklSearchFilter(keyword = "")
    var skillHobi = SkillHobi()
    var bidangKerja = BidangKerja()
    var isFirstTimeSearching = true
    override fun supportFragmentInjector(): AndroidInjector<Fragment> =
        fragmentDispatchingAndroidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBind = DataBindingUtil.setContentView(this, R.layout.activity_search)
        presenter.onAttach(this)
        presenter.setKey(Utils.loadData(applicationContext))

        supportFragmentManager?.addFragmentWithoutBackStack(
            R.id.cl_search,
            SearchHistoryFragment.newInstance(),
            SearchHistoryFragment.TAG
        )

        viewBind.bAddTempatPkl.setOnClickListener {
            supportFragmentManager.addFragmentWithBackStack(
                R.id.cl_search,
                AddTempatPklFragment.newInstance(),
                AddTempatPklFragment.TAG
            )
        }

        filterSearchButton = viewBind.bFilterSearch

        viewBind.ivBack.setOnClickListener {
            onBackPressed()
        }

        searchEditText = viewBind.tietSearchInput



        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                //notImplemented
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //notImplemented
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                Handler().postDelayed({
                    if (isFirstTimeSearching) {
                        isFirstTimeSearching = false
                        val bundle = Bundle()
                        filterDetails.keyword = s.toString()
                        bundle.putString("keyword", filterDetails.keyword)
                        bundle.putInt("searchType", filterDetails.preferensi ?: 0)
                        supportFragmentManager?.addFragmentWithBackStack(
                            R.id.cl_search,
                            FrontProfileFragment.newInstance(),
                            FrontProfileFragment.TAG
                        )
                    } else {

                    }
                }, 500)
            }
        })

        viewBind.bFilterSearch.setOnClickListener()
        {
            supportFragmentManager?.addFragmentWithBackStack(
                R.id.cl_search,
                FilterSearchFragment.newInstance(),
                FilterSearchFragment.TAG
            )
        }

    }

    override fun onBackPressed() {
        val count = supportFragmentManager.backStackEntryCount
        if (count == 0) {
            super.onBackPressed()
            //additional code
        } else {
            supportFragmentManager.popBackStack()
        }
    }

    override fun onFragmentAttached() {
        //notImplemented
    }

    override fun onFragmentDetached(tag: String) {
        supportFragmentManager?.removeFragment(tag = tag)
    }
}