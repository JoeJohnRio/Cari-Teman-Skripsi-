package com.example.cariteman.ui.pengalaman.pengalamanhome.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cariteman.R
import com.example.cariteman.data.model.PengalamanLombaOrganisasiResponse
import com.example.cariteman.databinding.FragmentHistorySearchBinding
import com.example.cariteman.databinding.FragmentPengalamanHomeBinding
import com.example.cariteman.ui.base.view.BaseFragment
import com.example.cariteman.ui.pengalaman.pengalamanhome.presenter.PengalamanHomePresenter
import com.example.cariteman.ui.pengalaman.pengalamanhome.presenter.SearchHistoryPresenter
import com.example.cariteman.ui.pengalaman.tambahpengalaman.view.TambahPengalamanChooseFragment
import com.example.cariteman.ui.pengalaman.tambahpengalaman.view.TambahPengalamanLombaFragment
import com.example.cariteman.ui.pengalaman.tambahpengalaman.view.TambahPengalamanOrganisasiFragment
import com.example.cariteman.util.Utils
import com.example.cariteman.util.extension.addFragmentWithBackStack
import javax.inject.Inject

class SearchHistoryFragment : BaseFragment(),
    SearchHistoryMVPView {

    @Inject
    lateinit var presenter: SearchHistoryPresenter<SearchHistoryMVPView>
    lateinit var viewBind: FragmentHistorySearchBinding
    lateinit var adapterPengalaman: PengalamanListAdapter
    lateinit var modifyPengalaman: PengalamanLombaOrganisasiResponse

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
        context.let {presenter.setKey(Utils.loadData(it!!))}
        adapterPengalaman =
            PengalamanListAdapter(this)
        modifyPengalaman = PengalamanLombaOrganisasiResponse()

        return viewBind.root
    }

    override fun setPengalamanAdapter(pengalaman: MutableList<PengalamanLombaOrganisasiResponse>) {
        if (pengalaman.isNullOrEmpty()){
            viewBind.cvItemAdaPengalamanHome.visibility = View.GONE
            viewBind.cvItemKosongPengalamanHome.visibility = View.VISIBLE
        }else{
            viewBind.cvItemAdaPengalamanHome.visibility = View.VISIBLE
            viewBind.cvItemKosongPengalamanHome.visibility = View.GONE

            adapterPengalaman.submitList(pengalaman)
            viewBind.rvItemPengalamanWithModify.apply {
                if (adapter == null) {
                    adapter = adapterPengalaman
                }
                if (layoutManager == null) {
                    layoutManager = LinearLayoutManager(context)
                }
                adapter?.notifyDataSetChanged()
            }
        }
    }

    override fun setUp() {

    }

}