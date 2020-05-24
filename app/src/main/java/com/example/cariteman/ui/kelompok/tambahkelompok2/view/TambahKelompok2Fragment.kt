package com.example.cariteman.ui.pengalaman.pengalamanhome.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.OpenableColumns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import com.bumptech.glide.Glide
import com.example.cariteman.R
import com.example.cariteman.data.model.*
import com.example.cariteman.databinding.FragmentTambahKelompok2Binding
import com.example.cariteman.ui.base.view.BaseFragment
import com.example.cariteman.ui.pengalaman.pengalamanhome.presenter.TambahKelompok2Presenter
import com.example.cariteman.ui.pengalaman.view.KelompokActivity
import com.example.cariteman.util.Mapper
import com.example.cariteman.util.Utils
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import javax.inject.Inject

class TambahKelompok2Fragment : BaseFragment(),
    TambahKelompok2MVPView {
    @Inject
    lateinit var presenter: TambahKelompok2Presenter<TambahKelompok2MVPView>

    lateinit var viewBind: FragmentTambahKelompok2Binding
    var url =
        "https://i.pinimg.com/originals/9e/a0/6c/9ea06cc55d5169eeb4fcce595ecc7443.jpg"
    var pklOrLomba = 0
    var PICK_FOTO_GROUP = 1
    var fotoGroupUri: Uri? = null
    var listOfTeman: ArrayList<DaftarTemanHanyaNama>? = null

    companion object {

        internal val TAG = "Kelompok"
        fun newInstance(): TambahKelompok2Fragment {
            return TambahKelompok2Fragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)

        viewBind = FragmentTambahKelompok2Binding.inflate(inflater, container, false)
        presenter.onAttach(this)
        context.let { presenter.setKey(Utils.loadData(it!!)) }

        val bundle: Bundle? = arguments
        listOfTeman = bundle?.getParcelableArrayList("listOfFriend")

        viewBind.rgPilihanTipeKelompok.setOnCheckedChangeListener(object :
            RadioGroup.OnCheckedChangeListener {
            override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
                when (checkedId) {
                    R.id.radioPkl -> {
                        pklOrLomba = 0
                    }
                    R.id.radioLomba -> {
                        pklOrLomba = 1
                    }
                }
            }

        })

        viewBind.ivGroupProfilPhoto.setOnClickListener {
            openFileFotoPengalamanLomba()
            showMessageToast("Pilih File Dibawah 1MB")
        }

        viewBind.fabAddKelompok.setOnClickListener {
            if (fotoGroupUri == null){
                presenter.makeKelompok(
                    kelompok = Kelompok(
                        namaKelompok = viewBind.etKelompokName.text.toString(),
                        jumlahAnggota = listOfTeman?.size,
                        tipeKelompok = pklOrLomba,
                        fotoKelompok = url,
                        calonAnggotas = Mapper.temanToRelationKelompok(listOfTeman!!)
                    )
                )
            }else{
                uploadFotoProfile(fotoGroupUri!!, context!!, this)
            }
        }

        return viewBind.root
    }


    fun uploadFotoProfile(foto: Uri, context: Context, view: BaseFragment) {
        showProgress()
        val fileReference: StorageReference =
            Utils.mStorageRef.child(
                "${System.currentTimeMillis()}.${Utils.getFileExtension(
                    foto,
                    context
                )}"
            )
        val putFile = fileReference.putFile(foto)
        var urlTask: Task<Uri> =
            putFile.continueWithTask(object : Continuation<UploadTask.TaskSnapshot, Task<Uri>> {
                override fun then(p0: Task<UploadTask.TaskSnapshot>): Task<Uri> {
                    if (!p0.isSuccessful) {
                        view.showMessageToast("error 1")
                    }
                    return fileReference.downloadUrl
                }
            }).addOnCompleteListener {
                if (it.isSuccessful) {
                    val uri = it.result
                    val string = uri.toString()
                    var fotoUrl = string

                    hideProgress()
                    presenter.makeKelompok(
                        kelompok = Kelompok(
                            namaKelompok = viewBind.etKelompokName.text.toString(),
                            jumlahAnggota = listOfTeman?.size,
                            tipeKelompok = pklOrLomba,
                            fotoKelompok = fotoUrl,
                            calonAnggotas = Mapper.temanToRelationKelompok(listOfTeman!!)
                        )
                    )
                } else {
                    view.showMessageToast("error")
                }
                hideProgress()
            }
    }

    fun openFileFotoPengalamanLomba() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent, PICK_FOTO_GROUP)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_FOTO_GROUP && resultCode == Activity.RESULT_OK
            && data != null && data.getData() != null
        ) {
            data.data.let { returnUri ->
                context?.contentResolver?.query(returnUri!!, null, null, null, null)
            }?.use { cursor ->
                val sizeIndex = cursor.getColumnIndex(OpenableColumns.SIZE)
                cursor.moveToFirst()
                if (cursor.getLong(sizeIndex).toDouble().div(1024) < 1000.0) {
                    fotoGroupUri = data.getData()!!
                    Glide.with(this.context)
                        .load(fotoGroupUri)
                        .into(viewBind.ivGroupProfilPhoto)
                } else {
                    showMessageToast("File melebihi 1MB")
                }
            }
        }
    }

    override fun returnToHome() {
        var intent = Intent(context, KelompokActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }


    override fun setUp() {

    }

}