package com.example.cariteman.ui.pengalaman.pengalamanhome.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cariteman.R
import com.example.cariteman.data.model.AnggotaKelompok
import com.example.cariteman.data.model.RelationKelompok
import com.example.cariteman.databinding.FragmentDetailKelompokBinding
import com.example.cariteman.ui.base.view.BaseFragment
import com.example.cariteman.ui.message.view.MessageActivity
import com.example.cariteman.ui.pengalaman.pengalamanhome.presenter.KelompokDetailPresenter
import com.example.cariteman.util.Utils
import com.example.cariteman.util.extension.addFragmentWithBackStack
import javax.inject.Inject

class KelompokDetailFragment : BaseFragment(),
    KelompokDetailMVPView {
    @Inject
    lateinit var presenter: KelompokDetailPresenter<KelompokDetailMVPView>

    lateinit var viewBind: FragmentDetailKelompokBinding

    lateinit var listAnggotaKelompok: MutableList<AnggotaKelompok>
    lateinit var adapterWithList: DetailKelompokAnggotaListAdapter
    lateinit var adapterWithoutRemoveList: DetailKelompokAnggotaListAdapter
    lateinit var adapterAnggotaPending: DetailKelompokAnggotaListAdapter
    var idKelompok = 0
    var isOnDelete = false
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
        idKelompok = bundle?.getInt("id") ?: 0
        val namaKelompok: String? = bundle?.getString("namaKelompok")
        val isAlreadyKelompok: Int? = bundle?.getInt("isAlreadyKelompok" ,-1)

        viewBind = FragmentDetailKelompokBinding.inflate(inflater, container, false)
        presenter.onAttach(this)
        context.let {presenter.setKey(Utils.loadData(it!!))}

        if (isAlreadyKelompok == 1){
            presenter.getAnggotaKelompok(idKelompok)
            presenter.getPendingAnggotaKelompok(idKelompok)
            viewBind.tvKelompokTitle.setText(namaKelompok.toString())

            viewBind.ivMessageButton.setOnClickListener {
                var intent = Intent(context, MessageActivity::class.java)
                intent.putExtra("isKelompok", 1)
                intent.putExtra("idKelompok", idKelompok)
                intent.putExtra("namaToolbar", namaKelompok)
                startActivity(intent)
            }

            viewBind.fabRemoveAnggota.setOnClickListener {
                if (isOnDelete){
                    isOnDelete = false
                    if (!listAnggotaKelompok.isNullOrEmpty()){
                        showAnggotaKelompokWithRemove(listAnggotaKelompok)
                    }
                }else{
                    isOnDelete = true
                    if (!listAnggotaKelompok.isNullOrEmpty()){
                        showAnggotaKelompokWithoutRemove(listAnggotaKelompok)
                    }
                }
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
        }else if(isAlreadyKelompok == 0){
            presenter.getAnggotaKelompok(idKelompok ?: 0)
            viewBind.ivMessageButton.visibility = View.GONE
            viewBind.fabRemoveAnggota.visibility = View.GONE
            viewBind.bTambahAnggota.visibility = View.GONE
            viewBind.bAcceptKelompok.visibility = View.VISIBLE
            viewBind.bDeclineKelompok.visibility = View.VISIBLE

            viewBind.bAcceptKelompok.setOnClickListener {
                presenter.confirmAnggotaKelompok(relationKelompok = RelationKelompok(idKelompok = idKelompok, status = 1))
            }

            viewBind.bDeclineKelompok.setOnClickListener {
                presenter.confirmAnggotaKelompok(relationKelompok = RelationKelompok(idKelompok = idKelompok, status = 0))
            }
        }


        return viewBind.root
    }

    override fun removeAnggotaKelompok(idMahasiswa: Int) {
        presenter.removeAnggota(RelationKelompok(idKelompok = idKelompok, idMahasiswa = idMahasiswa))
    }

    override fun showAnggotaKelompokWithoutRemove(response: MutableList<AnggotaKelompok>) {
        adapterWithList = DetailKelompokAnggotaListAdapter(this, false)

        listAnggotaKelompok = response
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

    override fun showAnggotaKelompokWithRemove(response: MutableList<AnggotaKelompok>) {
        adapterWithoutRemoveList = DetailKelompokAnggotaListAdapter(this, true)

        listAnggotaKelompok = response
        viewBind.tvJumlahAnggota.setText(response.size.toString() + " anggota kelompok")
        adapterWithoutRemoveList.submitList(response)
        viewBind.rvAnggotaKelompok.apply {
            adapter = adapterWithoutRemoveList
            if (layoutManager == null){
                layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            }
            adapter?.notifyDataSetChanged()
        }
    }

    override fun showPendingAnggota(response: MutableList<AnggotaKelompok>) {
        if (response.isNullOrEmpty()){
            viewBind.rvAnggotaPending.visibility = View.GONE
            viewBind.llNoAnggotaPending.visibility = View.VISIBLE
        }else{
            viewBind.rvAnggotaPending.visibility = View.VISIBLE
            viewBind.llNoAnggotaPending.visibility = View.GONE

            adapterAnggotaPending = DetailKelompokAnggotaListAdapter(this, false)

            adapterAnggotaPending.submitList(response)
            viewBind.rvAnggotaPending.apply {
                adapter = adapterAnggotaPending
                if (layoutManager == null){
                    layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                }
                adapter?.notifyDataSetChanged()
            }
        }
    }

    override fun updateAfterConfirmKelompok(status: Int) {
        activity?.onBackPressed()
    }

    override fun setUp() {

    }

}
