package com.example.cariteman.ui.pengalaman.pengalamanhome.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cariteman.R
import com.example.cariteman.data.model.AnggotaKelompok
import com.example.cariteman.data.model.PengalamanLombaOrganisasiResponse
import com.example.cariteman.data.model.RelationBidangKerja
import com.example.cariteman.data.model.RelationKelompok
import com.example.cariteman.databinding.FragmentDaftarKelompokBinding
import com.example.cariteman.databinding.FragmentDetailKelompokBinding
import com.example.cariteman.databinding.FragmentPengalamanHomeBinding
import com.example.cariteman.ui.MessageActivity
import com.example.cariteman.ui.base.view.BaseFragment
import com.example.cariteman.ui.pengalaman.pengalamanhome.presenter.KelompokDetailPresenter
import com.example.cariteman.ui.pengalaman.pengalamanhome.presenter.KelompokHomePresenter
import com.example.cariteman.ui.pengalaman.pengalamanhome.presenter.PengalamanHomePresenter
import com.example.cariteman.ui.pengalaman.tambahpengalaman.view.TambahPengalamanLombaFragment
import com.example.cariteman.ui.pengalaman.tambahpengalaman.view.TambahPengalamanOrganisasiFragment
import com.example.cariteman.util.Utils
import com.example.cariteman.util.extension.addFragmentWithBackStack
import javax.inject.Inject

class KelompokDetailFragment : BaseFragment(),
    KelompokDetailMVPView {
    @Inject
    lateinit var presenter: KelompokDetailPresenter<KelompokDetailMVPView>

    lateinit var viewBind: FragmentDetailKelompokBinding

    lateinit var adapterWithList: DetailKelompokAnggotaListAdapter
    companion object {
        internal val TAG = "Kelompok"

        fun newInstance(): KelompokDetailFragment {
            return KelompokDetailFragment()
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)

        val bundle: Bundle? = arguments
        val idKelompok: Int? = bundle?.getInt("id")
        val namaKelompok: String? = bundle?.getString("namaKelompok")

        viewBind = FragmentDetailKelompokBinding.inflate(inflater, container, false)
        presenter.onAttach(this)
        context.let {presenter.setKey(Utils.loadData(it!!))}

        presenter.getAnggotaKelompok(idKelompok ?: 0)
        viewBind.tvKelompokTitle.setText(namaKelompok.toString())

        viewBind.ivMessageButton.setOnClickListener {
            startActivity(Intent(context, MessageActivity::class.java))
        }

        viewBind.fabRemoveAnggota.setOnClickListener {

        }

        viewBind.bTambahAnggota.setOnClickListener {
            val bundle = Bundle()
            bundle.putBoolean("isMakingKelompok", false)
            bundle.putInt("idKelompok", idKelompok!!)
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

    override fun showAnggotaKelompokWithoutRemove(response: MutableList<AnggotaKelompok>) {
        adapterWithList = DetailKelompokAnggotaListAdapter(this, false)

        viewBind.tvJumlahAnggota.setText(response.size.toString() + " anggota kelompok")
        adapterWithList.submitList(response)
        viewBind.rvAnggotaKelompok.apply {
            adapter = adapterWithList
            if (layoutManager == null){
                layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            }
            adapter?.notifyDataSetChanged()
        }
    }


    override fun setUp() {

    }

}
