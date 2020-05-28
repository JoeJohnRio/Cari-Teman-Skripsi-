package com.example.cariteman.ui.verifikasiakun.verifikasiakunlist.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cariteman.R
import com.example.cariteman.data.model.MahasiswaNotVerifiedListResponse
import com.example.cariteman.databinding.FragmentMahasiswaNotVerifiedListBinding
import com.example.cariteman.ui.base.view.BaseFragment
import com.example.cariteman.ui.verifikasiakun.verifikasiakunlist.presenter.VerifikasiAkunListPresenter
import com.example.cariteman.util.Utils
import com.example.cariteman.util.extension.addFragmentWithBackStack
import javax.inject.Inject

class VerifikasiAkunListFragment : BaseFragment(),
    VerifikasiAkunListMVPView {
    @Inject
    lateinit var presenter: VerifikasiAkunListPresenter<VerifikasiAkunListMVPView>

    lateinit var viewBind: FragmentMahasiswaNotVerifiedListBinding

    lateinit var adapterVerifikasiAkun: VerifikasiAkunListAdapter
    companion object {

        internal val TAG = "Verifikasi Akun"

        fun newInstance(): VerifikasiAkunListFragment {
            return VerifikasiAkunListFragment()
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)

        viewBind = FragmentMahasiswaNotVerifiedListBinding.inflate(inflater, container, false)
        presenter.onAttach(this)
        context.let { presenter.setKey(Utils.loadData(it!!)) }
        adapterVerifikasiAkun = VerifikasiAkunListAdapter(this)

        presenter.showMahasiswa()

        return viewBind.root
    }

    override fun handleMahasiswaNotVerified(responses: MutableList<MahasiswaNotVerifiedListResponse>) {
        adapterVerifikasiAkun.submitList(responses)
        viewBind.rvMahasiswaNotVerifiedList.apply {
            if (adapter == null) {
                adapter = adapterVerifikasiAkun
            }
            if (layoutManager == null) {
                layoutManager = LinearLayoutManager(context)
            }
            adapter?.notifyDataSetChanged()
        }
    }

    override fun goToMahasiswaDetail(id: Int) {
        val bundle = Bundle()
        bundle.putInt("idMahasiswa", id)
        val verifikasiAkunDetailFragment = VerifikasiAkunDetailFragment.newInstance()
        verifikasiAkunDetailFragment.arguments = bundle

        getBaseActivity()?.supportFragmentManager?.addFragmentWithBackStack(
            R.id.cl_verifikasi_akun,
            verifikasiAkunDetailFragment,
            VerifikasiAkunDetailFragment.TAG
        )
    }

    override fun onResume() {
        super.onResume()

        presenter.showMahasiswa()
    }

    override fun setUp() {

    }

}