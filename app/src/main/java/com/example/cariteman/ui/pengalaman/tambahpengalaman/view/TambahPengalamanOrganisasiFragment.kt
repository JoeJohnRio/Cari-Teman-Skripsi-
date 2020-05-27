package com.example.cariteman.ui.pengalaman.tambahpengalaman.view

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.OpenableColumns
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import com.bumptech.glide.Glide
import com.example.cariteman.R
import com.example.cariteman.data.model.PengalamanLombaOrganisasiResponse
import com.example.cariteman.databinding.FragmentTambahPengalamanOrganisasiBinding
import com.example.cariteman.ui.base.view.BaseFragment
import com.example.cariteman.ui.pengalaman.pengalamanhome.view.BidangKerjaFragment
import com.example.cariteman.ui.pengalaman.tambahpengalaman.presenter.TambahPengalamanPresenter
import com.example.cariteman.util.AppConstants
import com.example.cariteman.util.Utils
import com.example.cariteman.util.extension.addFragmentWithBackStack
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import java.util.*
import javax.inject.Inject

class TambahPengalamanOrganisasiFragment : BaseFragment(),
    TambahPengalamanMVPView {
    @Inject
    lateinit var presenter: TambahPengalamanPresenter<TambahPengalamanMVPView>
    lateinit var viewBind: FragmentTambahPengalamanOrganisasiBinding
    lateinit var dpdStart: DatePickerDialog
    lateinit var dpdEnd: DatePickerDialog
    var calendar = Calendar.getInstance()
    val day = calendar.get(Calendar.DAY_OF_MONTH)
    val month = calendar.get(Calendar.MONTH)
    val year = calendar.get(Calendar.YEAR)

    val PICK_FOTO_PENGALAMAN_ORGANISASI_MODIFY = 1
    val PICK_FOTO_PENGALAMAN_ORGANISASI_SAVE = 2
    var fotoOrganisasiUri: Uri? = null
    var idPengalaman = 0
    var tipePengalamanOrganisasi = "tambah"
    var namaOrganisasi = ""
    var bidangKerja = ""
    var bidangKerjaId = 0
    var deskripsi = "Masukkan Deskripsi"
    var tanggalMulai = "Masukkan Tanggal"
    var tanggalSelesai = "Masukkan Tanggal"
    var fotoPengalaman = ""

    companion object {

        internal val TAG = "Pengalaman"
        fun newInstance(): TambahPengalamanOrganisasiFragment {
            return TambahPengalamanOrganisasiFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)

        viewBind = FragmentTambahPengalamanOrganisasiBinding.inflate(inflater, container, false)
        presenter.onAttach(this)
        context.let { presenter.setKey(Utils.loadData(it!!)) }

        val bundle: Bundle? = arguments
        idPengalaman = bundle?.getInt("idPengalaman", 0) ?: 0
        tipePengalamanOrganisasi = bundle?.getString("tipePengalamanOrganisasi") ?: "tambah"
        namaOrganisasi = bundle?.getString("namaOrganisasi") ?: ""
        bidangKerja =
            bundle?.getString("namaBidangKerja") ?: ""
        deskripsi = bundle?.getString("deskripsi") ?: ""
        fotoPengalaman = bundle?.getString("fotoPengalaman") ?: ""
        tanggalMulai = bundle?.getString("tanggalMulai") ?: "$year-$month-$day"
        tanggalSelesai = bundle?.getString("tanggalSelesai") ?: "$year-$month-$day"

        if (tipePengalamanOrganisasi == "modify") {
            viewBind.etOrganisationName.setText(namaOrganisasi)
            viewBind.tvBidangKerja.text = bidangKerja
            viewBind.etDescription.setText(deskripsi)
            viewBind.tvDateStart.setText(
                "${Utils.formatterDate.format(Utils.parserDate.parse("${tanggalMulai}"))}"
            )
            viewBind.tvDateEnd.setText(
                "${Utils.formatterDate.format(Utils.parserDate.parse("${tanggalSelesai}"))}"
            )

            viewBind.mbSaveButton.setOnClickListener {
                if (namaOrganisasi.isEmpty() || deskripsi.isEmpty() || fotoPengalaman.isEmpty() || tanggalMulai.isEmpty() || tanggalSelesai.isEmpty() || bidangKerja.isEmpty()) {
                    showMessageToast("Selesaikan form terlebih dahulu")
                } else {
                    if (fotoOrganisasiUri != null) {
                        uploadFotoProfile(
                            fotoOrganisasiUri!!,
                            context!!,
                            this,
                            PICK_FOTO_PENGALAMAN_ORGANISASI_MODIFY
                        )
                    } else {
                        presenter.modifyPengalamanOrganisasi(
                            PengalamanLombaOrganisasiResponse(
                                idPengalamanOrganisasi = idPengalaman,
                                namaOrganisasi = namaOrganisasi,
                                deskripsi = deskripsi,
                                gambar = fotoPengalaman,
                                tanggalMulai = tanggalMulai,
                                tanggalSelesai = tanggalSelesai,
                                idBidangKerja = bidangKerjaId
                            )
                        )
                    }
                }
            }

            viewBind.mbDeleteButton.visibility = View.VISIBLE

            if (!fotoPengalaman.isEmpty()) {
                viewBind.ivFotoPengalamanOrganisasi.visibility = View.VISIBLE

                Glide.with(this.context)
                    .load(fotoPengalaman)
                    .into(viewBind.ivFotoPengalamanOrganisasi)
            }

            viewBind.mbDeleteButton.setOnClickListener {
                if (idPengalaman != 0) {
                    presenter.deletePengalamanOrganisasi(idPengalaman ?: 0)
                } else {
                    backstack()
                    showMessageToast("ID Error")
                }
            }
        } else if (tipePengalamanOrganisasi == "tambah") {
            viewBind.mbSaveButton.setOnClickListener {
                if (namaOrganisasi.isEmpty() || deskripsi.isEmpty() || fotoPengalaman.isEmpty() || tanggalMulai.isEmpty() || tanggalSelesai.isEmpty() || bidangKerja.isEmpty()) {
                    showMessageToast("Selesaikan form terlebih dahulu")
                } else {
                    if (fotoOrganisasiUri != null) {
                        uploadFotoProfile(
                            fotoOrganisasiUri!!,
                            context!!,
                            this,
                            PICK_FOTO_PENGALAMAN_ORGANISASI_SAVE
                        )
                    } else {
                        presenter.tambahPengalamanOrganisasi(
                            PengalamanLombaOrganisasiResponse(
                                idPengalamanOrganisasi = idPengalaman,
                                namaOrganisasi = namaOrganisasi,
                                deskripsi = deskripsi,
                                gambar = fotoPengalaman,
                                tanggalMulai = tanggalMulai,
                                tanggalSelesai = tanggalSelesai,
                                idBidangKerja = bidangKerjaId
                            )
                        )
                    }
                }
            }
            viewBind.mbDeleteButton.visibility = View.GONE
        }


        viewBind.etOrganisationName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                //notImplemented
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //notImplemented
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                namaOrganisasi = viewBind.etOrganisationName.text.toString()
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

        viewBind.tvDateStart.setOnClickListener {
            dpdStart = DatePickerDialog(context!!, object : DatePickerDialog.OnDateSetListener {
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                    tanggalMulai = "$year-$month-$dayOfMonth"
                    viewBind.tvDateStart.setText(
                        "${Utils.formatterDate.format(Utils.parserDate.parse("${tanggalMulai}"))}"
                    )
                }
            }, year, month, day)
            dpdStart.show()
        }

        viewBind.tvDateEnd.setOnClickListener {
            calendar = Calendar.getInstance()
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            val month = calendar.get(Calendar.MONTH)
            val year = calendar.get(Calendar.YEAR)

            dpdEnd = DatePickerDialog(context!!, object : DatePickerDialog.OnDateSetListener {
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                    tanggalSelesai = "$year-$month-$dayOfMonth"
                    viewBind.tvDateEnd.setText(
                        "${Utils.formatterDate.format(Utils.parserDate.parse("${tanggalSelesai}"))}"
                    )
                }
            }, year, month, day)
            dpdEnd.show()
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

        viewBind.ivAddFotoPengalamanOrganisasi.setOnClickListener {
            openFileFotoPengalamanOrganisasi()
        }

        return viewBind.root
    }

    fun openFileFotoPengalamanOrganisasi() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent, PICK_FOTO_PENGALAMAN_ORGANISASI_MODIFY)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == AppConstants.FRAGMENT_CODE) {
                bidangKerja = data?.getStringExtra("bidangKerjaNama") ?: ""
                bidangKerjaId = data!!.getIntExtra("bidangKerjaId", 0)
            }
        }

        if ((requestCode == PICK_FOTO_PENGALAMAN_ORGANISASI_MODIFY || requestCode == PICK_FOTO_PENGALAMAN_ORGANISASI_SAVE) && resultCode == Activity.RESULT_OK
            && data != null && data.getData() != null
        ) {
            data.data.let { returnUri ->
                context?.contentResolver?.query(returnUri!!, null, null, null, null)
            }?.use { cursor ->
                val sizeIndex = cursor.getColumnIndex(OpenableColumns.SIZE)
                cursor.moveToFirst()
                if (cursor.getLong(sizeIndex).toDouble().div(1024) < 1000.0) {
                    fotoOrganisasiUri = data.getData()!!
                    viewBind.ivFotoPengalamanOrganisasi.visibility = View.VISIBLE
                    Glide.with(this.context)
                        .load(fotoOrganisasiUri)
                        .into(viewBind.ivFotoPengalamanOrganisasi)
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
                    if (pickFoto == PICK_FOTO_PENGALAMAN_ORGANISASI_MODIFY) {
                        presenter.modifyPengalamanLomba(
                            PengalamanLombaOrganisasiResponse(
                                idPengalamanOrganisasi = idPengalaman,
                                namaOrganisasi = namaOrganisasi,
                                deskripsi = deskripsi,
                                gambar = fotoPengalaman,
                                tanggalMulai = tanggalMulai,
                                tanggalSelesai = tanggalSelesai,
                                idBidangKerja = bidangKerjaId
                            )
                        )
                    } else if (pickFoto == PICK_FOTO_PENGALAMAN_ORGANISASI_SAVE) {
                        presenter.tambahPengalamanOrganisasi(
                            PengalamanLombaOrganisasiResponse(
                                idPengalamanOrganisasi = idPengalaman,
                                namaOrganisasi = namaOrganisasi,
                                deskripsi = deskripsi,
                                gambar = fotoPengalaman,
                                tanggalMulai = tanggalMulai,
                                tanggalSelesai = tanggalSelesai,
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

    override fun backstack() {
        activity?.onBackPressed()
    }

    override fun onStart() {
        super.onStart()

        viewBind.tvBidangKerja.setText(bidangKerja)
        viewBind.tvBidangKerja.setTextColor(resources.getColor(R.color.black_effective))
        viewBind.tvDateStart.setTextColor(resources.getColor(R.color.black_effective))
        viewBind.tvDateEnd.setTextColor(resources.getColor(R.color.black_effective))
    }

    override fun getBackToPengalamanHome() {
        activity?.onBackPressed()
    }

    override fun setUp() {
    }

}