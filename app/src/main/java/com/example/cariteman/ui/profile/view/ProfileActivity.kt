package com.example.cariteman.ui.profile.view

import android.app.Activity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.cariteman.R
import com.example.cariteman.data.model.ProfilInfoOthers
import com.example.cariteman.databinding.ActivityUserProfileBinding
import com.example.cariteman.ui.base.view.BaseActivity
import com.example.cariteman.ui.dashboard.presenter.ProfilePresenter
import com.example.cariteman.util.Utils
import dagger.android.AndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class ProfileActivity: BaseActivity(), ProfileMVPView{
    @Inject
    lateinit var presenter: ProfilePresenter<ProfileMVPView>
    var url = "https://upload.wikimedia.org/wikipedia/commons/thumb/7/7e/Circle-icons-profile.svg/1200px-Circle-icons-profile.svg.png"

    lateinit private var viewBind: ActivityUserProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBind = DataBindingUtil.setContentView(this, R.layout.activity_user_profile)
        presenter.onAttach(this)
        presenter.setKey(Utils.loadData(applicationContext))
        val mahasiswaId = intent.getIntExtra("MAHASISWA_ID", -1)
        if (mahasiswaId==-1){
            showMessageToast("ID Error, please try again later")
        }else{
            showMessageToast("${mahasiswaId}")
            presenter.getProfilInfoOthers(mahasiswaId)
        }
    }

    override fun setInfoProfil(profilInfoOthers: ProfilInfoOthers) {
        viewBind.tvNamaProfil.text = "${profilInfoOthers.name}, ${profilInfoOthers.tahunMulai}"
        viewBind.tvJumlahRekomendasi.text = "${profilInfoOthers.jumlahRekomendasi}"
        viewBind.tvJumlahTim.text = "${profilInfoOthers.jumlahKelompok}"
        viewBind.tvJumlahTeman.text = "${profilInfoOthers.jumlahTeman}"
        viewBind.tvFakultasProdiKeminatan.text = "${profilInfoOthers.fakultas}"
        Glide.with(applicationContext)
            .load(profilInfoOthers.fotoProfil ?: url)
            .into(viewBind.civProfil)
        var fakultasInfo= ""
        profilInfoOthers.fakultas.let { fakultasInfo = fakultasInfo + it }
        profilInfoOthers.programStudi.let { fakultasInfo = fakultasInfo + ", " + it }
        profilInfoOthers.keminatan.let { fakultasInfo = fakultasInfo + ", " + it }
        viewBind.tvFakultasProdiKeminatan.text = fakultasInfo
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