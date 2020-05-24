package com.example.cariteman.ui.pengalaman.tambahpengalaman.view

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import com.example.cariteman.R
import com.example.cariteman.data.model.PengalamanLombaOrganisasiResponse
import com.example.cariteman.databinding.FragmentTambahPengalamanOrganisasiBinding
import com.example.cariteman.ui.base.view.BaseFragment
import com.example.cariteman.ui.pengalaman.pengalamanhome.view.BidangKerjaFragment
import com.example.cariteman.ui.pengalaman.tambahpengalaman.presenter.TambahPengalamanPresenter
import com.example.cariteman.util.AppConstants
import com.example.cariteman.util.Utils
import com.example.cariteman.util.extension.addFragmentWithBackStack
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
    var bidangKerja = ""
    var bidangKerjaId = 0
    var urlGambar =
    "https://ae01.alicdn.com/kf/HTB1N_RqXiDxK1Rjy1zcq6yGeXXay/Pesona-Serangga-Kalung-Liontin-Ambar-Scorpion-Liontin-Kalung-dengan-Tali-Adjustable-Scorpion-Lebah-Semut-Tawon.jpg"
    val day = calendar.get(Calendar.DAY_OF_MONTH)
    val month = calendar.get(Calendar.MONTH)
    val year = calendar.get(Calendar.YEAR)

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
        context.let {presenter.setKey(Utils.loadData(it!!))}

        val bundle: Bundle? = arguments
        var idPengalaman = bundle?.getInt("idPengalaman", 0)
        var tipePengalamanOrganisasi = bundle?.getString("tipePengalamanOrganisasi") ?: "tambah"
        var namaOrganisasi = bundle?.getString("namaOrganisasi") ?: ""
        var namaBidangKerja =
            bundle?.getString("namaBidangKerja") ?: ""
        var deskripsi = bundle?.getString("deskripsi") ?: ""
        var tanggalMulai = bundle?.getString("tanggalMulai") ?: "$year-$month-$day"
        var tanggalSelesai = bundle?.getString("tanggalSelesai") ?: "$year-$month-$day"

        if (tipePengalamanOrganisasi == "modify") {
            viewBind.etOrganisationName.setText(namaOrganisasi)
            viewBind.tvBidangKerja.text = namaBidangKerja
            viewBind.etDescription.setText(deskripsi)
            viewBind.tvDateStart.setText(
                "${Utils.formatterDate.format(Utils.parserDate.parse("${tanggalMulai}"))}"
            )
            viewBind.tvDateEnd.setText(
                "${Utils.formatterDate.format(Utils.parserDate.parse("${tanggalSelesai}"))}"
            )

            viewBind.mbSaveButton.setOnClickListener {
                presenter.modifyPengalamanOrganisasi(
                    PengalamanLombaOrganisasiResponse(
                        idPengalamanOrganisasi = idPengalaman,
                        namaOrganisasi = namaOrganisasi,
                        deskripsi = deskripsi,
                        gambar = urlGambar,
                        tanggalMulai = tanggalMulai,
                        tanggalSelesai = tanggalSelesai,
                        idBidangKerja = bidangKerjaId
                    )
                )
            }

            viewBind.mbDeleteButton.visibility = View.VISIBLE

            viewBind.mbDeleteButton.setOnClickListener {
                if (idPengalaman != 0){
                    presenter.deletePengalamanOrganisasi(idPengalaman ?: 0)
                }else{
                    backstack()
                    showMessageToast("ID Error")
                }
            }
        }else if(tipePengalamanOrganisasi == "tambah"){
            viewBind.mbSaveButton.setOnClickListener {
                presenter.tambahPengalamanOrganisasi(
                    PengalamanLombaOrganisasiResponse(
                        idPengalamanOrganisasi = idPengalaman,
                        namaOrganisasi = namaOrganisasi,
                        deskripsi = deskripsi,
                        gambar = urlGambar,
                        tanggalMulai = tanggalMulai,
                        tanggalSelesai = tanggalSelesai,
                        idBidangKerja = bidangKerjaId
                    )
                )
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

        return viewBind.root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == AppConstants.FRAGMENT_CODE) {
                bidangKerja = data?.getStringExtra("bidangKerjaNama") ?: ""
                bidangKerjaId = data!!.getIntExtra("bidangKerjaId", 0)
            }
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