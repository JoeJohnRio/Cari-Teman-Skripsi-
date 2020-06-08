package com.example.cariteman.ui.pengalaman.tambahpengalaman.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cariteman.R
import com.example.cariteman.databinding.FragmentTambahPengalamanLombaBinding
import com.example.cariteman.ui.base.view.BaseFragment
import com.example.cariteman.ui.pengalaman.pengalamanhome.view.BidangKerjaFragment
import com.example.cariteman.ui.pengalaman.tambahpengalaman.presenter.TambahPengalamanPresenter
import com.example.cariteman.util.extension.addFragmentWithBackStack
import javax.inject.Inject
import android.app.Activity.RESULT_OK
import android.app.DatePickerDialog
import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import android.text.Editable
import android.text.TextWatcher
import android.widget.DatePicker
import com.bumptech.glide.Glide
import com.example.cariteman.data.model.PengalamanLombaOrganisasiResponse
import com.example.cariteman.util.AppConstants
import com.example.cariteman.util.Utils
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import java.util.*


class TambahPengalamanLombaFragment : BaseFragment(),
    TambahPengalamanMVPView {

    @Inject
    lateinit var presenter: TambahPengalamanPresenter<TambahPengalamanMVPView>
    lateinit var viewBind: FragmentTambahPengalamanLombaBinding
    lateinit var calendar: Calendar
    lateinit var dpd: DatePickerDialog
    val PICK_FOTO_PENGALAMAN_LOMBA_MODIFY = 1
    val PICK_FOTO_PENGALAMAN_LOMBA_SAVE = 2
    var fotoLombaUri: Uri? = null
    var idPengalaman = 0
    var tipePengalamanLombaFragment = "tambah"
    var namaKompetisi = ""
    var bidangKerja = ""
    var bidangKerjaId = 0
    var deskripsi = "Masukkan Deskripsi"
    var tanggal = "Masukkan Tanggal"
    var fotoPengalaman = ""

    companion object {
        internal val TAG = "Pengalaman"
        fun newInstance(): TambahPengalamanLombaFragment {
            return TambahPengalamanLombaFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)

        viewBind = FragmentTambahPengalamanLombaBinding.inflate(inflater, container, false)
        presenter.onAttach(this)
        context.let { presenter.setKey(Utils.loadData(it!!)) }

        val bundle: Bundle? = arguments
        idPengalaman = bundle?.getInt("idPengalaman", 0) ?: 0
        tipePengalamanLombaFragment = bundle?.getString("tipePengalamanLomba") ?: "tambah"
        namaKompetisi = bundle?.getString("namaKompetisi") ?: ""
        if (bidangKerja.isEmpty()){
            bidangKerja =
                bundle?.getString("namaBidangKerja") ?: ""
        }else{
            viewBind.tvBidangKerja.setText(bidangKerja)
        }
        deskripsi = bundle?.getString("deskripsi") ?: "Masukkan Deskripsi"
        tanggal = bundle?.getString("tanggal") ?: "Masukkan Tanggal"
        fotoPengalaman = bundle?.getString("fotoPengalaman") ?: ""

        if (tipePengalamanLombaFragment == "modify") {
            viewBind.etCompetitionName.setText(namaKompetisi)
            viewBind.tvBidangKerja.setText(bidangKerja)
            viewBind.etDescription.setText(deskripsi)
            viewBind.tvDate.setText(
                "${Utils.formatterDate.format(Utils.parserDate.parse("${tanggal}"))}"
            )

            viewBind.mbSaveButton.setOnClickListener {
                if (namaKompetisi.isEmpty() || bidangKerja.isEmpty() || deskripsi.isEmpty() || tanggal.isEmpty()) {
                    showMessageToast("Selesaikan form terlebih dahulu")
                } else {
                    if (fotoLombaUri != null) {
                        uploadFotoProfile(
                            fotoLombaUri!!,
                            context!!,
                            this,
                            PICK_FOTO_PENGALAMAN_LOMBA_MODIFY
                        )
                    } else {
                        presenter.modifyPengalamanLomba(
                            PengalamanLombaOrganisasiResponse(
                                idPengalamanLomba = idPengalaman,
                                namaKompetisi = namaKompetisi,
                                deskripsi = deskripsi,
                                gambar = fotoPengalaman,
                                tanggal = tanggal,
                                idBidangKerja = bidangKerjaId
                            )
                        )
                    }
                }
            }

            viewBind.mbDeleteButton.visibility = View.VISIBLE

            if (!fotoPengalaman.isEmpty()) {
                viewBind.ivFotoPengalamanLomba.visibility = View.VISIBLE

                Glide.with(this.context)
                    .load(fotoPengalaman)
                    .into(viewBind.ivFotoPengalamanLomba)
            }

            viewBind.mbDeleteButton.setOnClickListener {
                if (idPengalaman != 0) {
                    presenter.deletePengalamanLomba(idPengalaman ?: 0)
                } else {
                    backstack()
                    showMessageToast("ID Error")
                }
            }
        } else if (tipePengalamanLombaFragment == "tambah") {
            viewBind.mbDeleteButton.visibility = View.GONE

            viewBind.mbSaveButton.setOnClickListener {
                if (namaKompetisi.isEmpty() || bidangKerja.isEmpty() || deskripsi.isEmpty() || tanggal.isEmpty() || fotoLombaUri == null) {
                    showMessageToast("Selesaikan form terlebih dahulu")
                } else {
                    if (fotoLombaUri != null) {
                        uploadFotoProfile(
                            fotoLombaUri!!,
                            context!!,
                            this,
                            PICK_FOTO_PENGALAMAN_LOMBA_SAVE
                        )
                    } else {
                        presenter.tambahPengalamanLomba(
                            PengalamanLombaOrganisasiResponse(
                                idPengalamanLomba = idPengalaman,
                                namaKompetisi = namaKompetisi,
                                deskripsi = deskripsi,
                                gambar = fotoPengalaman,
                                tanggal = tanggal,
                                idBidangKerja = bidangKerjaId
                            )
                        )
                    }
                }
            }
        }

        viewBind.etCompetitionName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                //notImplemented
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //notImplemented
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                namaKompetisi = viewBind.etCompetitionName.text.toString()
            }
        })

        viewBind.etDescription.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                //notImplemented
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //notImplemented
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                deskripsi = viewBind.etDescription.text.toString()
            }
        })

        viewBind.tvDate.setOnClickListener {
            calendar = Calendar.getInstance()
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            val month = calendar.get(Calendar.MONTH)
            val year = calendar.get(Calendar.YEAR)

            dpd = DatePickerDialog(context!!, object : DatePickerDialog.OnDateSetListener {
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                    tanggal = "$year-$month-$dayOfMonth"
                    viewBind.tvDate.setText(
                        "${Utils.formatterDate.format(Utils.parserDate.parse("${tanggal}"))}"
                    )
                }
            }, year, month, day)
            dpd.show()
        }

        viewBind.tvBidangKerja.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("namaBidangKerja", viewBind.tvBidangKerja.text.toString())

            val bidangKerja = BidangKerjaFragment.newInstance()
            bidangKerja.arguments = bundle
            bidangKerja.setTargetFragment(this, AppConstants.FRAGMENT_CODE)
            getBaseActivity()?.supportFragmentManager?.addFragmentWithBackStack(
                R.id.cl_tambah_pengalaman_lomba,
                bidangKerja,
                BidangKerjaFragment.TAG
            )
        }

        viewBind.ivAddFotoPengalamanLomba.setOnClickListener {
            openFileFotoPengalamanLomba()
        }

        return viewBind.root
    }


    fun openFileFotoPengalamanLomba() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent, PICK_FOTO_PENGALAMAN_LOMBA_MODIFY)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK) {
            if (requestCode == AppConstants.FRAGMENT_CODE) {
                bidangKerja = data?.getStringExtra("bidangKerjaNama") ?: ""
                bidangKerjaId = data!!.getIntExtra("bidangKerjaId", 0)
            }
        }

        if ((requestCode == PICK_FOTO_PENGALAMAN_LOMBA_MODIFY || requestCode == PICK_FOTO_PENGALAMAN_LOMBA_SAVE) && resultCode == RESULT_OK
            && data != null && data.getData() != null
        ) {
            data.data.let { returnUri ->
                context?.contentResolver?.query(returnUri!!, null, null, null, null)
            }?.use { cursor ->
                val sizeIndex = cursor.getColumnIndex(OpenableColumns.SIZE)
                cursor.moveToFirst()
                if (cursor.getLong(sizeIndex).toDouble().div(1024) < 1000.0) {
                    fotoLombaUri = data.getData()!!
                    viewBind.ivFotoPengalamanLomba.visibility = View.VISIBLE
                    Glide.with(this.context)
                        .load(fotoLombaUri)
                        .into(viewBind.ivFotoPengalamanLomba)
                } else {
                    showMessageToast("File melebihi 1MB")
                }
            }
        }
    }


    fun uploadFotoProfile(foto: Uri, context: Context, view: BaseFragment, pickFoto: Int) {
        showProgress()
        val fileReference: StorageReference =
            Utils.mStorageRef.child(
                "${System.currentTimeMillis()}.${Utils.getFileExtension(
                    foto,
                    context
                )}"
            )

        val putFile = fileReference.putFile(foto)
        var url = ""
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
                    fotoPengalaman = string

                    hideProgress()
                    if (pickFoto == PICK_FOTO_PENGALAMAN_LOMBA_MODIFY) {
                        presenter.modifyPengalamanLomba(
                            PengalamanLombaOrganisasiResponse(
                                idPengalamanLomba = idPengalaman,
                                namaKompetisi = namaKompetisi,
                                deskripsi = deskripsi,
                                gambar = fotoPengalaman,
                                tanggal = tanggal,
                                idBidangKerja = bidangKerjaId
                            )
                        )
                    } else if (pickFoto == PICK_FOTO_PENGALAMAN_LOMBA_SAVE) {
                        presenter.tambahPengalamanLomba(
                            PengalamanLombaOrganisasiResponse(
                                idPengalamanLomba = idPengalaman,
                                namaKompetisi = namaKompetisi,
                                deskripsi = deskripsi,
                                gambar = fotoPengalaman,
                                tanggal = tanggal,
                                idBidangKerja = bidangKerjaId
                            )
                        )
                    }
                } else {
                    view.showMessageToast("error")
                }
                hideProgress()
            }
    }

    override fun onStart() {
        super.onStart()

        viewBind.tvBidangKerja.setTextColor(resources.getColor(R.color.black_effective))
        viewBind.tvDate.setTextColor(resources.getColor(R.color.black_effective))
    }

    override fun backstack() {
        activity?.onBackPressed()
    }

    override fun getBackToPengalamanHome() {
        activity?.onBackPressed()
    }

    override fun setUp() {
    }

}