package com.example.cariteman.ui.pengalaman.tambahpengalaman.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cariteman.R
import com.example.cariteman.databinding.FragmentPengalamanTambahBinding
import com.example.cariteman.ui.base.view.BaseFragment
import com.example.cariteman.ui.pengalaman.tambahpengalaman.presenter.TambahPengalamanPresenter
import com.example.cariteman.util.Utils
import com.example.cariteman.util.extension.addFragmentWithBackStack
import javax.inject.Inject


class TambahPengalamanChooseFragment : BaseFragment(),
    TambahPengalamanMVPView {
    @Inject
    lateinit var presenter: TambahPengalamanPresenter<TambahPengalamanMVPView>
    lateinit var viewBind: FragmentPengalamanTambahBinding

    companion object {
        internal val TAG = "Pengalaman"
        fun newInstance(): TambahPengalamanChooseFragment {
            return TambahPengalamanChooseFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)

        viewBind = FragmentPengalamanTambahBinding.inflate(inflater, container, false)
        presenter.onAttach(this)
        context.let {presenter.setKey(Utils.loadData(it!!))}

        viewBind.llPengalamanLomba.setOnClickListener{
            getBaseActivity()?.supportFragmentManager?.addFragmentWithBackStack(
                R.id.cl_tambah_pengalaman_lomba,
                TambahPengalamanLombaFragment.newInstance(),
                TambahPengalamanLombaFragment.TAG
            )
        }

        viewBind.llPengalamanOrganisasi.setOnClickListener{
            getBaseActivity()?.supportFragmentManager?.addFragmentWithBackStack(
                R.id.cl_tambah_pengalaman_lomba,
                TambahPengalamanOrganisasiFragment.newInstance(),
                TambahPengalamanOrganisasiFragment.TAG
            )
        }

        return viewBind.root
    }

    override fun getBackToPengalamanHome() {
        activity?.onBackPressed()
    }

    override fun setUp() {
    }

}