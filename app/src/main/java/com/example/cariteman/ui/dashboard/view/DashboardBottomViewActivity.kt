package com.example.cariteman.ui.dashboard.view

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.cariteman.R
import com.example.cariteman.data.model.MahasiswaHistoryDashboardResponse
import com.example.cariteman.data.model.RelationTempatPklFavorite
import com.example.cariteman.databinding.NavigationBottomParentBinding
import com.example.cariteman.ui.base.view.BaseActivity
import com.example.cariteman.ui.dashboard.presenter.DashboardPresenter
import com.example.cariteman.util.*
import com.example.cariteman.util.extension.attach
import com.example.cariteman.util.extension.detach
import com.example.cariteman.util.extension.removeFragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class DashboardBottomViewActivity : BaseActivity(), DashboardMVPView, HasSupportFragmentInjector {

    @Inject
    internal lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var presenter: DashboardPresenter<DashboardMVPView>

    var isFirst: Boolean = true
    private lateinit var binding: NavigationBottomParentBinding
    private var navPosition: BottomNavigationPosition = BottomNavigationPosition.HOME

    companion object {
        lateinit var activityEnd: Activity
        const val KEY_POSITION = "keyPosition"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        restoreSavedInstanceState(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.navigation_bottom_parent)

        presenter.onAttach(this)
        activityEnd = this
        binding.bottomNavigation.apply {
            // This is required in Support Library 27 or lower:
            // bottomNavigation.disableShiftMode()

            active(navPosition.position) // Extension function
            setOnNavigationItemSelectedListener { item ->
                navPosition = findNavigationPositionById(item.itemId)
                switchFragment(navPosition)
            }
        }

        initFragment(savedInstanceState)
    }

    override fun onBackPressed() {
        if (isFirst){
            showMessageToast("Press back again to leave")
            isFirst = false
        }else{
            super.onBackPressed()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        // Store the current navigation position.
        outState.putInt(KEY_POSITION, navPosition.id)
        super.onSaveInstanceState(outState)
    }

    override fun supportFragmentInjector() = fragmentDispatchingAndroidInjector

    override fun onFragmentAttached() {
        //notImplemented
    }


    override fun onFragmentDetached(tag: String) {
        supportFragmentManager?.removeFragment(tag = tag)
    }

    private fun restoreSavedInstanceState(savedInstanceState: Bundle?) {
        // Restore the current navigation position.
        savedInstanceState?.getInt(KEY_POSITION, BottomNavigationPosition.HOME.id)?.also {
            navPosition = findNavigationPositionById(it)
        }
    }

    private fun initFragment(savedInstanceState: Bundle?) {
        savedInstanceState ?: switchFragment(BottomNavigationPosition.HOME)
    }

    /**
     * Immediately execute transactions with FragmentManager#executePendingTransactions.
     */
    private fun switchFragment(navPosition: BottomNavigationPosition): Boolean {
        return findFragment(navPosition).let {
            if (it.isAdded) return false
            supportFragmentManager.detach() // Extension function
            supportFragmentManager.attach(it, navPosition.getTag()) // Extension function
            supportFragmentManager.executePendingTransactions()
        }
    }

    private fun findFragment(position: BottomNavigationPosition): Fragment {
        return supportFragmentManager.findFragmentByTag(position.getTag())
            ?: position.createFragment()
    }

    override fun setLastPageLimiter(lastPage: Int) {
        //notImplemented
    }

    override fun populateFavoriteProfil(responses: MutableList<RelationTempatPklFavorite>) {
        //notImplemented
    }

    override fun populateLombaDanPklDashboard(responses: List<MahasiswaHistoryDashboardResponse>) {
        //notImplemented
    }
}