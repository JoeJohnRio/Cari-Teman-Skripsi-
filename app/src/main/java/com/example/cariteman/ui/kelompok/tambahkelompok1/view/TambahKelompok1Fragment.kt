package com.example.cariteman.ui.pengalaman.pengalamanhome.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cariteman.R
import com.example.cariteman.data.model.*
import com.example.cariteman.databinding.FragmentTambahKelompok1Binding
import com.example.cariteman.ui.base.view.BaseFragment
import com.example.cariteman.ui.pengalaman.pengalamanhome.presenter.TambahKelompok1Presenter
import com.example.cariteman.util.Mapper
import com.example.cariteman.util.Utils
import com.example.cariteman.util.extension.addFragmentWithBackStack
import javax.inject.Inject

class TambahKelompok1Fragment : BaseFragment(),
    TambahKelompok1MVPView {
    @Inject
    lateinit var presenter: TambahKelompok1Presenter<TambahKelompok1MVPView>

    lateinit var viewBind: FragmentTambahKelompok1Binding
    lateinit var adapterPengalaman: DaftarTemanHanyaNamaListAdapter
    lateinit var listOfFriend: ArrayList<DaftarTemanHanyaNama>

    companion object {

        internal val TAG = "Kelompok"
        fun newInstance(): TambahKelompok1Fragment {
            return TambahKelompok1Fragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)

        viewBind = FragmentTambahKelompok1Binding.inflate(inflater, container, false)
        presenter.onAttach(this)
        context.let { presenter.setKey(Utils.loadData(it!!)) }
        adapterPengalaman = DaftarTemanHanyaNamaListAdapter(this)

        val bundle: Bundle? = arguments
        val isMakingKelompok = bundle?.getBoolean("isMakingKelompok")
        val idKelompok = bundle?.getInt("idKelompok")

        if (isMakingKelompok == null) {
            getBaseActivity()?.finish()
        } else if (isMakingKelompok) {
            presenter.showFriendWithNameOnly()

            viewBind.fabAddKelompok.setOnClickListener {
                if (listOfFriend.isNullOrEmpty()) {
                    showMessageToast("Pilih teman terlebih dahulu")
                } else {
                    val bundle = Bundle()
                    bundle.putParcelableArrayList("listOfFriend", listOfFriend)
                    val tambahKelompok2Fragment = TambahKelompok2Fragment.newInstance()
                    tambahKelompok2Fragment.arguments = bundle
                    getBaseActivity()?.supportFragmentManager?.addFragmentWithBackStack(
                        R.id.cl_kelompok_home,
                        tambahKelompok2Fragment,
                        TambahKelompok2Fragment.TAG
                    )
                }
            }
        } else if (!isMakingKelompok) {
            presenter.showFriendNotAddedYet(idKelompok!!)

            viewBind.fabAddKelompok.setOnClickListener {
                if (listOfFriend.isNullOrEmpty()) {
                    showMessageToast("Pilih teman terlebih dahulu")
                } else {
                    presenter.addFriendToKelompok(
                        Kelompok(
                            idKelompok = idKelompok,
                            calonAnggotas = Mapper.temanToRelationKelompok(listOfFriend)
                        )
                    )
                }
            }
        }

        return viewBind.root
    }

    override fun handleShowFriend(response: MutableList<DaftarTemanHanyaNama>) {
        adapterPengalaman.submitList(response)
        viewBind.rvTambahTeman.apply {
            if (adapter == null) {
                adapter = adapterPengalaman
            }
            if (layoutManager == null) {
                layoutManager = LinearLayoutManager(context)
            }
            adapter?.notifyDataSetChanged()
        }
        listOfFriend = arrayListOf()
    }

    override fun saveListOfTeman(response: DaftarTemanHanyaNama) {
        listOfFriend.add(response)
    }

    override fun deleteListOfTeman(response: DaftarTemanHanyaNama) {
        listOfFriend.remove(response)
    }

    override fun goToKelompokDetail() {
        getBaseActivity()?.onBackPressed()
    }

    override fun setUp() {

    }

}