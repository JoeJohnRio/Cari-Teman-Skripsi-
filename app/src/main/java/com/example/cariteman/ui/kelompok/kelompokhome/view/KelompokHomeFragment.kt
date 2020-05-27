package com.example.cariteman.ui.pengalaman.pengalamanhome.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cariteman.R
import com.example.cariteman.data.model.RelationKelompok
import com.example.cariteman.databinding.FragmentDaftarKelompokBinding
import com.example.cariteman.ui.base.view.BaseFragment
import com.example.cariteman.ui.pengalaman.pengalamanhome.presenter.KelompokHomePresenter
import com.example.cariteman.util.Utils
import com.example.cariteman.util.extension.addFragmentWithBackStack
import javax.inject.Inject

class KelompokHomeFragment : BaseFragment(),
    KelompokHomeMVPView {
    @Inject
    lateinit var presenter: KelompokHomePresenter<KelompokHomeMVPView>

    lateinit var viewBind: FragmentDaftarKelompokBinding
    lateinit var adapterPengalaman: DaftarKelompokListAdapter
    companion object {

        internal val TAG = "Daftar Kelompok"
        fun newInstance(): KelompokHomeFragment {
            return KelompokHomeFragment()
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)

        viewBind = FragmentDaftarKelompokBinding.inflate(inflater, container, false)
        presenter.onAttach(this)
        context.let {presenter.setKey(Utils.loadData(it!!))}
        adapterPengalaman =DaftarKelompokListAdapter(type = 0, kelompokHomeFragment = this)

        presenter.showKelompok()

        viewBind.fabAddKelompok.setOnClickListener{
            val bundle = Bundle()
            bundle.putBoolean("isMakingKelompok", true)
            val tambahKelompok1Fragment = TambahKelompok1Fragment.newInstance()
            tambahKelompok1Fragment.arguments = bundle

            getBaseActivity()?.supportFragmentManager?.addFragmentWithBackStack(
                R.id.cl_kelompok_home,
                tambahKelompok1Fragment,
                TambahKelompok1Fragment.TAG
            )
        }

        return viewBind.root
    }

    override fun handleShowKelompok(response: MutableList<RelationKelompok>) {
        adapterPengalaman.submitList(response)
        viewBind.rvDaftarKelompok.apply {
            if (adapter == null) {
                adapter = adapterPengalaman
            }
            if (layoutManager == null) {
                layoutManager = LinearLayoutManager(context)
            }
            adapter?.notifyDataSetChanged()
        }
    }

    override fun goToKelompokDetail(id: Int, jumlahAnggota: Int, namaKelompok: String) {
        val bundle = Bundle()
        bundle.putInt("id", id)
        bundle.putString("namaKelompok", namaKelompok)
        bundle.putInt("isAlreadyKelompok", 1)
        val kelompokDetailFragment = KelompokDetailFragment.newInstance()
        kelompokDetailFragment.arguments = bundle


        getBaseActivity()?.supportFragmentManager?.addFragmentWithBackStack(
            R.id.cl_kelompok_home,
            kelompokDetailFragment,
            KelompokDetailFragment.TAG
        )
    }

    override fun setUp() {

    }

}