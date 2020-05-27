package com.example.cariteman.ui.profile.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.cariteman.R
import com.example.cariteman.data.model.*
import com.example.cariteman.databinding.ActivityUserProfileBinding
import com.example.cariteman.ui.base.view.BaseActivity
import com.example.cariteman.ui.dashboard.barudilihat.view.ProfilPengalamanListAdapter
import com.example.cariteman.ui.dashboard.barudilihat.view.RekomendasiListAdapter
import com.example.cariteman.ui.dashboard.presenter.ProfilePresenter
import com.example.cariteman.ui.message.view.MessageActivity
import com.example.cariteman.util.Utils
import javax.inject.Inject



class ProfileActivity : BaseActivity(), ProfileMVPView {
    @Inject
    lateinit var presenter: ProfilePresenter<ProfileMVPView>
    lateinit var adapterPengalaman: ProfilPengalamanListAdapter
    lateinit var adapterRekomendasi: RekomendasiListAdapter
    lateinit var mutableListOfKelompok: MutableList<RelationKelompok>
    lateinit private var viewBind: ActivityUserProfileBinding
    var isSearchingItself = false
    var url =
        "https://upload.wikimedia.org/wikipedia/commons/thumb/7/7e/Circle-icons-profile.svg/1200px-Circle-icons-profile.svg.png"
    var name = ""
    var mahasiswaId = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBind = DataBindingUtil.setContentView(this, R.layout.activity_user_profile)
        presenter.onAttach(this)
        presenter.setKey(Utils.loadData(applicationContext))
        mahasiswaId = intent.getIntExtra("MAHASISWA_ID", -1)
        val isSearchingItself = intent.getBooleanExtra("isSearchingItself", false)
        this.isSearchingItself = isSearchingItself
        if (isSearchingItself){
            presenter.getProfilInfoOthersItself()
            presenter.getPengalamanAndRekomendasiItself()
        }else{
            if (mahasiswaId == -1) {
                showMessageToast("ID Error, please try again later")
                finish()
            } else {
                presenter.getProfilInfoOthers(mahasiswaId)
                presenter.getPengalamanAndRekomendasi(mahasiswaId)
            }
        }
        adapterRekomendasi = RekomendasiListAdapter(presenter)
        adapterPengalaman = ProfilPengalamanListAdapter(presenter)
    }

    override fun setPengalamanAndRekomendasi(
        rekomendasi: MutableList<Rekomendasi>?,
        rekomendasipengalamanLombaOrganisasiResponse: MutableList<PengalamanLombaOrganisasiResponse>
    ) {
        adapterPengalaman.submitList(rekomendasipengalamanLombaOrganisasiResponse)
        viewBind.rvItemPengalamanProfile.apply {
            if (adapter == null) {
                adapter = adapterPengalaman
            }
            if (layoutManager == null) {
                layoutManager = LinearLayoutManager(context)
            }
            adapter?.notifyDataSetChanged()
        }

        adapterRekomendasi.submitList(rekomendasi)

        viewBind.rvItemRekomendasiProfile.apply {
            if (adapter == null) {
                adapter = adapterRekomendasi
            }
            if (layoutManager == null) {
                layoutManager = LinearLayoutManager(context)
            }
            adapter?.notifyDataSetChanged()
        }

        viewBind.rvItemRekomendasiProfile.isNestedScrollingEnabled = false
        viewBind.rvItemPengalamanProfile.isNestedScrollingEnabled = false
    }

    override fun setInfoProfil(profilInfoOthers: ProfilInfoOthers) {
        if (!isSearchingItself){
            setFriendStatusView(profilInfoOthers)
        }
        name = profilInfoOthers.name ?: ""
        viewBind.tvNamaProfil.text = "${profilInfoOthers.name}, ${profilInfoOthers.tahunMulai}"
        viewBind.tvJumlahRekomendasi.text = "${profilInfoOthers.jumlahRekomendasi}"
        viewBind.tvJumlahTim.text = "${profilInfoOthers.jumlahKelompok}"
        viewBind.tvJumlahTeman.text = "${profilInfoOthers.jumlahTeman}"
        viewBind.tvFakultasProdiKeminatan.text = "${profilInfoOthers.fakultas}"
        Glide.with(applicationContext)
            .load(profilInfoOthers.fotoProfil ?: url)
            .into(viewBind.civProfil)
        var fakultasInfo = ""
        profilInfoOthers.fakultas.let { fakultasInfo = fakultasInfo + it }
        profilInfoOthers.programStudi.let { fakultasInfo = fakultasInfo + ", " + it }
        profilInfoOthers.keminatan.let { fakultasInfo = fakultasInfo + ", " + it }
        viewBind.tvFakultasProdiKeminatan.text = fakultasInfo
    }

    override fun setFriendStatusView(profilInfoOthers: ProfilInfoOthers) {
        val status = profilInfoOthers.status
        if (status == 0) {
            viewBind.llStatusZero.visibility = View.VISIBLE
            viewBind.bAddFriend.setOnClickListener {
                if (mahasiswaId == -1){
                    showMessageToast("Error")
                }else{
                    presenter.addFriend(RelationTeman(idMahasiswaTwo = mahasiswaId))
                }
            }
        } else if (status == 1) {
            viewBind.llStatusOne.visibility = View.VISIBLE
            viewBind.bKirimPesan.setOnClickListener {
                var intent = Intent(baseContext, MessageActivity::class.java)
                intent.putExtra("isKelompok", 0)
                intent.putExtra("idMahasiswaPenerima", mahasiswaId)
                intent.putExtra("namaToolbar", name)
                startActivity(intent)
            }
            viewBind.bTambahKelompok.setOnClickListener {
                presenter.showKelompok()
            }
        } else if (status == 2) {
            viewBind.llStatusTwo.visibility = View.VISIBLE
            viewBind.bAcceptFriend.setOnClickListener {
                if (mahasiswaId == -1){
                    showMessageToast("Error")
                }else{
                    presenter.confirmFriend(RelationTeman(idMahasiswaTwo = mahasiswaId, status = 1))
                }
            }
            viewBind.bRejectFriend.setOnClickListener {
                if (mahasiswaId == -1){
                    showMessageToast("Error")
                }else{
                    presenter.confirmFriend(RelationTeman(idMahasiswaTwo = mahasiswaId, status = 0))
                }
            }
        } else if (status == 3){
            viewBind.llStatusThree.visibility = View.VISIBLE
        } else if(status == -1){

        }
    }

    override fun restartActivity() {
        val intent = intent
        this.intent.putExtra("MAHASISWA_ID", mahasiswaId)
        finish()
        startActivity(intent)
    }

    override fun handleShowKelompok(response: MutableList<RelationKelompok>) {
        mutableListOfKelompok = response
        val myDialogFragment = AddKelompokAnggotaDialogFragment()
        myDialogFragment.setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme)
        myDialogFragment.show(supportFragmentManager, "myfragment")
    }

    override fun onFragmentAttached() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onFragmentDetached(tag: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

//    override fun activityInjector(): AndroidInjector<Activity> {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }

}