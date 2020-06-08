package com.example.cariteman.ui.profile.view

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.OpenableColumns
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
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import javax.inject.Inject


class ProfileActivity : BaseActivity(), ProfileMVPView, RekomendasiDialogFragment.DialogListener {
    @Inject
    lateinit var presenter: ProfilePresenter<ProfileMVPView>

    lateinit var adapterPengalaman: ProfilPengalamanListAdapter

    lateinit var adapterRekomendasi: RekomendasiListAdapter

    lateinit var mutableListOfKelompok: MutableList<RelationKelompok>
    lateinit var myDialogFragment: AddKelompokAnggotaDialogFragment
    lateinit private var viewBind: ActivityUserProfileBinding
    var isSearchingItself = false
    val PICK_FOTO_BARU_PROFIL = 1
    var url =
        "https://upload.wikimedia.org/wikipedia/commons/thumb/7/7e/Circle-icons-profile.svg/1200px-Circle-icons-profile.svg.png"
    var name = ""
    var mahasiswaId = -1
    var fotoProfileUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBind = DataBindingUtil.setContentView(this, R.layout.activity_user_profile)
        presenter.onAttach(this)
        presenter.setKey(Utils.loadData(applicationContext))
        mahasiswaId = intent.getIntExtra("MAHASISWA_ID", -1)
        this.isSearchingItself = intent.getBooleanExtra("isSearchingItself", false)
        if (isSearchingItself) {
            presenter.getProfilInfoOthersItself()
            presenter.getPengalamanAndRekomendasiItself()
        } else {
            if (mahasiswaId == -1) {
                showMessageToast("ID Error, please try again later")
                finish()
            } else {
                presenter.addHistoryLihatProfil(mahasiswaId)
                presenter.getProfilInfoOthers(mahasiswaId)
                presenter.getPengalamanAndRekomendasi(mahasiswaId)
            }
        }
        adapterRekomendasi = RekomendasiListAdapter(presenter)
        adapterPengalaman = ProfilPengalamanListAdapter(presenter)
    }
    override fun onFinishEditDialog(deskripsiRekomendasi: String, jumlahRating: Int) {
        presenter.saveRekomendasi(Rekomendasi(jumlahRating = jumlahRating, deskripsi = deskripsiRekomendasi, idPenerima = mahasiswaId))
    }

    override fun setPengalamanAndRekomendasi(
        rekomendasi: MutableList<Rekomendasi>?,
        rekomendasipengalamanLombaOrganisasiResponse: MutableList<PengalamanLombaOrganisasiResponse>
    ) {
        if (rekomendasipengalamanLombaOrganisasiResponse.isNullOrEmpty()){
            viewBind.llNoPengalaman.visibility = View.VISIBLE
            viewBind.rvItemPengalamanProfile.visibility = View.GONE
        }else{
            viewBind.llNoPengalaman.visibility = View.GONE
            viewBind.rvItemPengalamanProfile.visibility = View.VISIBLE
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
            viewBind.rvItemPengalamanProfile.isNestedScrollingEnabled = false
        }

        if (rekomendasi.isNullOrEmpty()){
            viewBind.llNoRekomendasi.visibility = View.VISIBLE
            viewBind.rvItemRekomendasiProfile.visibility = View.GONE
        }else{
            viewBind.llNoRekomendasi.visibility = View.GONE
            viewBind.rvItemRekomendasiProfile.visibility = View.VISIBLE
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
        }


    }

    override fun setInfoProfil(profilInfoOthers: ProfilInfoOthers) {
        if (!isSearchingItself) {
            setFriendStatusView(profilInfoOthers)
        }else{
            viewBind.ivIcModify.visibility = View.VISIBLE
            viewBind.civProfil.setOnClickListener {
                val dialogFragment = RekomendasiDialogFragment()
                val ft = supportFragmentManager.beginTransaction()
                val prev = supportFragmentManager.findFragmentByTag("dialog")
                if (prev != null)
                {
                    ft.remove(prev)
                }
                ft.addToBackStack(null)
                dialogFragment.show(ft, "dialog")
            }
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
                if (mahasiswaId == -1) {
                    showMessageToast("Error")
                } else {
                    presenter.addFriend(RelationTeman(idMahasiswaTwo = mahasiswaId))
                }
            }
        } else if (status == 1) {
            viewBind.bTambahRekomendasi.visibility = View.VISIBLE
            viewBind.llStatusOne.visibility = View.VISIBLE
            viewBind.bKirimPesan.setOnClickListener {
                var intent = Intent(baseContext, MessageActivity::class.java)
                intent.putExtra("isKelompok", 0)
                intent.putExtra("idMahasiswaPenerima", mahasiswaId)
                intent.putExtra("namaToolbar", name)
                startActivity(intent)
            }
            viewBind.bTambahKelompok.setOnClickListener {
                presenter.showKelompok(mahasiswaId)
            }
            viewBind.bTambahRekomendasi.setOnClickListener {
                val dialogFragment = RekomendasiDialogFragment()
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
        } else if (status == 2) {
            viewBind.llStatusTwo.visibility = View.VISIBLE
            viewBind.bAcceptFriend.setOnClickListener {
                if (mahasiswaId == -1) {
                    showMessageToast("Error")
                } else {
                    presenter.confirmFriend(RelationTeman(idMahasiswaTwo = mahasiswaId, status = 1))
                }
            }
            viewBind.bRejectFriend.setOnClickListener {
                if (mahasiswaId == -1) {
                    showMessageToast("Error")
                } else {
                    presenter.confirmFriend(RelationTeman(idMahasiswaTwo = mahasiswaId, status = 0))
                }
            }
        } else if (status == 3) {
            viewBind.llStatusThree.visibility = View.VISIBLE
        } else if (status == -1) {
            viewBind.ivIcModify.visibility = View.VISIBLE
            viewBind.civProfil.setOnClickListener {
                val dialogFragment = RekomendasiDialogFragment()
                val ft = supportFragmentManager.beginTransaction()
                val prev = supportFragmentManager.findFragmentByTag("dialog")
                if (prev != null)
                {
                    ft.remove(prev)
                }
                ft.addToBackStack(null)
                dialogFragment.show(ft, "dialog")
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_FOTO_BARU_PROFIL && resultCode == Activity.RESULT_OK
            && data != null && data.getData() != null
        ) {
            data.data.let { returnUri ->
                applicationContext?.contentResolver?.query(returnUri!!, null, null, null, null)
            }?.use { cursor ->
                val sizeIndex = cursor.getColumnIndex(OpenableColumns.SIZE)
                cursor.moveToFirst()
                if (cursor.getLong(sizeIndex).toDouble().div(1024) < 1000.0) {
                    fotoProfileUri = data.getData()!!
                    Glide.with(applicationContext)
                        .load(fotoProfileUri)
                        .into(viewBind.civProfil)
                    uploadFotoProfile(fotoProfileUri!!)
                } else {
                    showMessageToast("File tidak boleh melebihi 1MB")
                }
            }
        }
    }

    override fun addFriendToKelompok(idKelompok: Int) {
        presenter.addFriendToKelompok(Kelompok(idKelompok = idKelompok, calonAnggotas = mutableListOf(
            RelationKelompok(idMahasiswa = mahasiswaId)))
        )
    }

    override fun handleAfterInviteToKelompok() {
        myDialogFragment.dismiss()
    }

    override fun restartActivity() {
        val intent = intent
        this.intent.putExtra("MAHASISWA_ID", mahasiswaId)
        finish()
        startActivity(intent)
    }

    fun openFilePhoto() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent, PICK_FOTO_BARU_PROFIL)
    }

    override fun handleShowKelompok(response: MutableList<RelationKelompok>) {
        mutableListOfKelompok = response
        myDialogFragment = AddKelompokAnggotaDialogFragment()
        myDialogFragment.setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme)
        myDialogFragment.show(supportFragmentManager, "myfragment")
    }

    override fun returnAnswer(isYes: Boolean) {
        if (isYes){
            openFilePhoto()
        }else{
            //notImplemented
        }
    }

    fun uploadFotoProfile(foto: Uri) {
        showProgress()
        val fileReference: StorageReference =
            Utils.mStorageRef.child(
                "${System.currentTimeMillis()}.${Utils.getFileExtension(
                    foto,
                    applicationContext
                )}"
            )

        val putFile = fileReference.putFile(foto)
        var url = ""
        var urlTask: Task<Uri> =
            putFile.continueWithTask(object : Continuation<UploadTask.TaskSnapshot, Task<Uri>> {
                override fun then(p0: Task<UploadTask.TaskSnapshot>): Task<Uri> {
                    if (!p0.isSuccessful) {
                        showMessageToast("error 1")
                    }
                    return fileReference.downloadUrl
                }
            }).addOnCompleteListener {
                if (it.isSuccessful) {
                    val uri = it.result
                    val string = uri.toString()
                    hideProgress()
                    presenter.changeProfilePicture(mahasiswa = MahasiswaResponse(foto_profil = string))
                } else {
                    showMessageToast("error")
                }
                hideProgress()
            }
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