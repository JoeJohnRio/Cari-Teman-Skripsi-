package com.example.cariteman.ui.profile.tempatpklprofile.view

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.cariteman.R
import com.example.cariteman.data.model.*
import com.example.cariteman.databinding.ActivityTempatPklProfileBinding
import com.example.cariteman.ui.base.view.BaseActivity
import com.example.cariteman.ui.profile.tempatpklprofile.presenter.ProfileTempatPklPresenter
import com.example.cariteman.ui.profile.view.RekomendasiDialogFragment
import com.example.cariteman.util.Utils
import javax.inject.Inject

class ProfileTempatPklActivity : BaseActivity(),
    ProfileTempatPklMVPView, UlasanDialogFragment.DialogListener{
    @Inject
    lateinit var presenter: ProfileTempatPklPresenter<ProfileTempatPklMVPView>

    lateinit var adapterUlasan: UlasanListAdapter

    var tempatPklId: Int = 0
    lateinit private var viewBind: ActivityTempatPklProfileBinding
    var url =
        "https://upload.wikimedia.org/wikipedia/commons/thumb/7/7e/Circle-icons-profile.svg/1200px-Circle-icons-profile.svg.png"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBind = DataBindingUtil.setContentView(this, R.layout.activity_tempat_pkl_profile)
        presenter.onAttach(this)
        presenter.setKey(Utils.loadData(applicationContext))

        viewBind.bAddUlasan.setOnClickListener {
            val dialogFragment = UlasanDialogFragment()
            val bundle = Bundle()
            bundle.putBoolean("notAlertDialog", true)
            dialogFragment.arguments = bundle
            val ft = supportFragmentManager.beginTransaction()
            val prev = supportFragmentManager.findFragmentByTag("dialog")
            if (prev != null) {
                ft.remove(prev)
            }
            ft.addToBackStack(null)
            dialogFragment.show(ft, "dialog")
        }

        tempatPklId = intent.getIntExtra("tempatPklId", -1)
        presenter.getProfilTempatPkl(tempatPklId)
        adapterUlasan = UlasanListAdapter()
    }

    override fun onFinishEditDialog(isiUlasan: String) {
        if (tempatPklId == 0){
            showMessageToast("Tolong restart aplikasi untuk melanjutkan")
        }else{
            presenter.saveUlasanTempatPkl(UlasanTempatPklProfile(idTempatPkl = tempatPklId, isiUlasan = isiUlasan))
        }
    }

    override fun setUlasan(response: MutableList<UlasanTempatPklProfile>?) {
        adapterUlasan.submitList(response)

        viewBind.rvUlasan.apply {
            if (adapter == null) {
                adapter = adapterUlasan
            }
            if (layoutManager == null) {
                layoutManager = LinearLayoutManager(context)
            }
            adapter?.notifyDataSetChanged()
        }
    }

    override fun setInfoProfil(response: TempatPklProfile) {
        viewBind.tvNamaTempatPkl.text = response.namaPerusahaan
        viewBind.tvAlamatTempatPkl.text = "${response.lokasiPkl} - ${response.phoneNumber}"
        viewBind.tvJumlahRekomendasi.text = response.jumlahUlasan.toString()
        Glide.with(applicationContext)
            .load(response.gambar ?: url)
            .into(viewBind.civProfil)
    }

    override fun onFragmentAttached() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onFragmentDetached(tag: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}