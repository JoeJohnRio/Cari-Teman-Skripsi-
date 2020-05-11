package com.example.cariteman.ui.pengalaman.pengalamanhome.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cariteman.R
import com.example.cariteman.data.model.PengalamanLombaOrganisasiResponse
import com.example.cariteman.databinding.FragmentPengalamanHomeBinding
import com.example.cariteman.ui.base.view.BaseFragment
import com.example.cariteman.ui.pengalaman.pengalamanhome.presenter.PengalamanHomePresenter
import com.example.cariteman.ui.pengalaman.tambahpengalaman.view.TambahPengalamanChooseFragment
import com.example.cariteman.ui.pengalaman.tambahpengalaman.view.TambahPengalamanLombaFragment
import com.example.cariteman.ui.pengalaman.tambahpengalaman.view.TambahPengalamanOrganisasiFragment
import com.example.cariteman.util.Utils
import com.example.cariteman.util.extension.addFragmentWithBackStack
import javax.inject.Inject

class PengalamanHomeFragment : BaseFragment(),
    PengalamanHomeMVPView {

    @Inject
    lateinit var presenter: PengalamanHomePresenter<PengalamanHomeMVPView>
    lateinit var viewBind: FragmentPengalamanHomeBinding
    lateinit var adapterPengalaman: PengalamanListAdapter
    lateinit var modifyPengalaman: PengalamanLombaOrganisasiResponse

    companion object {
        internal val TAG = "Pengalaman"
        fun newInstance(): PengalamanHomeFragment {
            return PengalamanHomeFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBind = FragmentPengalamanHomeBinding.inflate(inflater, container, false)
        presenter.onAttach(this)
        context.let {presenter.setKey(Utils.loadData(it!!))}
        adapterPengalaman =
            PengalamanListAdapter(this)
        presenter.getPengalamanLombaDanOrganisasi()
        modifyPengalaman = PengalamanLombaOrganisasiResponse()

        viewBind.fabAddPengalaman.setOnClickListener {
            getBaseActivity()?.supportFragmentManager?.addFragmentWithBackStack(
                R.id.cl_tambah_pengalaman_lomba,
                TambahPengalamanChooseFragment.newInstance(),
                TambahPengalamanChooseFragment.TAG
            )
        }

        return viewBind.root
    }

    override fun getModifyPengalamanLombaFragment(pengalaman: PengalamanLombaOrganisasiResponse) {
        modifyPengalaman = pengalaman
        val bundle = Bundle()
        bundle.putInt("idPengalaman", pengalaman.id!!)
        bundle.putString("namaKompetisi", pengalaman.namaKompetisi)
        bundle.putString("tipePengalamanLomba", "modify")
        bundle.putString("namaBidangKerja", pengalaman.relationBidangKerja?.bidangKerja?.namaBidangKerja ?: "")
        bundle.putString("deskripsi", pengalaman.deskripsi)
        bundle.putString("tanggal", pengalaman.tanggal)
        val pengalamanLombaFragment = TambahPengalamanLombaFragment.newInstance()
        pengalamanLombaFragment.arguments = bundle
        getBaseActivity()?.supportFragmentManager?.addFragmentWithBackStack(
            R.id.cl_tambah_pengalaman_lomba,
            pengalamanLombaFragment,
            TambahPengalamanLombaFragment.TAG
        )
    }

    override fun getModifyPengalamanOrganisasiFragment(pengalaman: PengalamanLombaOrganisasiResponse) {
        modifyPengalaman = pengalaman
        val bundle = Bundle()
        bundle.putInt("idPengalaman", pengalaman.id!!)
        bundle.putString("namaOrganisasi", pengalaman.namaOrganisasi)
        bundle.putString("tipePengalamanOrganisasi", "modify")
        bundle.putString("namaBidangKerja", pengalaman.relationBidangKerja?.bidangKerja?.namaBidangKerja ?: "")
        bundle.putString("deskripsi", pengalaman.deskripsi)
        bundle.putString("tanggalMulai", pengalaman.tanggalMulai)
        bundle.putString("tanggalSelesai", pengalaman.tanggalSelesai)
        val pengalamanOrganisasiFragment = TambahPengalamanOrganisasiFragment.newInstance()
        pengalamanOrganisasiFragment.arguments = bundle

        getBaseActivity()?.supportFragmentManager?.addFragmentWithBackStack(
            R.id.cl_tambah_pengalaman_lomba,
            pengalamanOrganisasiFragment,
            TambahPengalamanOrganisasiFragment.TAG
        )
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