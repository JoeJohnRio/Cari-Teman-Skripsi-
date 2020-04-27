package com.example.cariteman.ui.pengalaman.tambahpengalaman.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.setContentView
import com.example.cariteman.R
import com.example.cariteman.databinding.FragmentTambahPengalamanLombaBinding
import com.example.cariteman.ui.base.view.BaseFragment
import com.example.cariteman.ui.pengalaman.pengalamanhome.view.BidangKerjaFragment
import com.example.cariteman.ui.pengalaman.tambahpengalaman.presenter.TambahPengalamanPresenter
import com.example.cariteman.ui.pengalaman.view.PengalamanActivity
import com.example.cariteman.util.extension.addFragmentWithBackStack
import javax.inject.Inject
import android.app.Activity.RESULT_OK
import android.app.DatePickerDialog
import android.text.Editable
import android.text.TextWatcher
import android.widget.CalendarView
import android.widget.DatePicker
import com.example.cariteman.data.model.PengalamanLombaOrganisasiResponse
import com.example.cariteman.util.AppConstants
import com.example.cariteman.util.Utils
import java.util.*


class TambahPengalamanLombaFragment : BaseFragment(),
    TambahPengalamanMVPView {
    @Inject
    lateinit var presenter: TambahPengalamanPresenter<TambahPengalamanMVPView>
    lateinit var viewBind: FragmentTambahPengalamanLombaBinding
    var bidangKerja = ""
    var bidangKerjaId = 0
    lateinit var calendar: Calendar
    lateinit var dpd: DatePickerDialog
    var urlGambar =
        "https://ae01.alicdn.com/kf/HTB1N_RqXiDxK1Rjy1zcq6yGeXXay/Pesona-Serangga-Kalung-Liontin-Ambar-Scorpion-Liontin-Kalung-dengan-Tali-Adjustable-Scorpion-Lebah-Semut-Tawon.jpg"

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
        context.let {presenter.setKey(Utils.loadData(it!!))}

        val bundle: Bundle? = arguments
        var idPengalaman = bundle?.getInt("idPengalaman", 0)
        var tipePengalamanLombaFragment = bundle?.getString("tipePengalamanLomba") ?: "tambah"
        var namaKompetisi = bundle?.getString("namaKompetisi") ?: "e.g. &quot;Cyber Jawara&quot;"
        var namaBidangKerja =
            bundle?.getString("namaBidangKerja") ?: "e.g. &quot;Mobile developer&quot;"
        var deskripsi = bundle?.getString("deskripsi") ?: "Masukkan Deskripsi"
        var tanggal = bundle?.getString("tanggal") ?: "Masukkan Tanggal"

        if (tipePengalamanLombaFragment == "modify") {
            viewBind.etCompetitionName.setText(namaKompetisi)
            viewBind.tvBidangKerja.setText(namaBidangKerja)
            viewBind.etDescription.setText(deskripsi)
            viewBind.tvDate.setText(
                "${Utils.formatterDate.format(Utils.parserDate.parse("${tanggal}"))}"
            )
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

        viewBind.mbSaveButton.setOnClickListener {
            presenter.modifyPengalamanLomba(
                PengalamanLombaOrganisasiResponse(
                    idPengalamanLomba = idPengalaman,
                    namaKompetisi = namaKompetisi,
                    deskripsi = deskripsi,
                    gambar = urlGambar,
                    tanggal = tanggal,
                    idBidangKerja = bidangKerjaId
                )
            )
        }
        viewBind.mbDeleteButton.setOnClickListener {

        }

        return viewBind.root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK) {
            if (requestCode == AppConstants.FRAGMENT_CODE) {
                bidangKerja = data?.getStringExtra("bidangKerjaNama") ?: ""
                bidangKerjaId = data!!.getIntExtra("bidangKerjaId", 0)
            }
        }
    }

    override fun onStart() {
        super.onStart()

        viewBind.tvBidangKerja.setText(bidangKerja)
        viewBind.tvBidangKerja.setTextColor(resources.getColor(R.color.black_effective))
        viewBind.tvDate.setTextColor(resources.getColor(R.color.black_effective))
    }

    override fun getBackToPengalamanHome() {
        activity?.onBackPressed()
    }

    override fun setUp() {
    }

}