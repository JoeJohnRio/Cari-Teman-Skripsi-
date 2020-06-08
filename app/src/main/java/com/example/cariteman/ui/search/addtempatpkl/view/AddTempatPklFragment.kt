package com.example.cariteman.ui.search.filtersearch.view

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.OpenableColumns
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.bumptech.glide.Glide
import com.example.cariteman.R
import com.example.cariteman.data.model.LokasiPklResponse
import com.example.cariteman.data.model.TempatPklResponse
import com.example.cariteman.databinding.FragmentAddTempatPklBinding
import com.example.cariteman.ui.base.view.BaseFragment
import com.example.cariteman.ui.pengalaman.pengalamanhome.presenter.AddTempatPklPresenter
import com.example.cariteman.ui.pengalaman.pengalamanhome.view.BidangKerjaFragment
import com.example.cariteman.util.AppConstants
import com.example.cariteman.util.Utils
import com.example.cariteman.util.extension.addFragmentWithBackStack
import javax.inject.Inject

class AddTempatPklFragment : BaseFragment(),
    AddTempatPklMVPView {
    @Inject
    lateinit var presenter: AddTempatPklPresenter<AddTempatPklMVPView>

    lateinit var viewBind: FragmentAddTempatPklBinding

    var bidangKerja = ""
    private var positionKota: Int = 0
    var bidangKerjaId = 0
    val PICK_FOTO_TEMPAT_PKL = 1
    val PICK_FOTO_PENGALAMAN_LOMBA_SAVE = 2
    var namaPerusahaan = ""
    var gambarUrl = ""
    var phoneNumber = 0
    var fotoLombaUri: Uri? = null
    companion object {

        internal val TAG = "AddTempatPkl"
        fun newInstance(): AddTempatPklFragment {
            return AddTempatPklFragment()
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBind = FragmentAddTempatPklBinding.inflate(inflater, container, false)
        presenter.onAttach(this)
        context.let { presenter.setKey(Utils.loadData(it!!)) }

        presenter.getLokasiPkl()
        viewBind.tvBidangKerja.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("namaBidangKerja", viewBind.tvBidangKerja.text.toString())

            val bidangKerja = BidangKerjaFragment.newInstance()
            bidangKerja.arguments = bundle
            bidangKerja.setTargetFragment(this, AppConstants.FRAGMENT_CODE)
            getBaseActivity()?.supportFragmentManager?.addFragmentWithBackStack(
                R.id.cl_search,
                bidangKerja,
                BidangKerjaFragment.TAG
            )
        }

        viewBind.etAlamatTempatPkl.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                //notImplemented
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //notImplemented
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })

        viewBind.etNamaPerusahaan.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                //notImplemented
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //notImplemented
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                namaPerusahaan = s.toString()
            }
        })
        viewBind.etNomorTelepon.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                //notImplemented
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //notImplemented
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                phoneNumber = 0
            }
        })
        viewBind.mbSaveButton.setOnClickListener {
            presenter.addTempatPkl(TempatPklResponse(namaPerusahaan = namaPerusahaan, gambar = gambarUrl, phoneNumber = phoneNumber, idLokasiPkl = positionKota, idBidangKerja = bidangKerjaId))
        }

        viewBind.ivAddFotoTempatPkl.setOnClickListener {
            openFileFotoPengalamanLomba()
        }


        return viewBind.root
    }

    override fun handleAfterAddLokasiPkl() {
        activity?.onBackPressed()
    }

    fun openFileFotoPengalamanLomba() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent, PICK_FOTO_TEMPAT_PKL)
    }

    override fun handleLokasiPkl(responses: MutableList<LokasiPklResponse>) {
        var counter = 0
        val fakultasArray = arrayOfNulls<String>(responses.size)
        for (response in responses) {
            fakultasArray[counter] = response.namaKota
            counter++
        }
        val spinnerItemFakultas = fakultasArray
        val spinnerAdapterFakultas =
            ArrayAdapter(context!!, android.R.layout.simple_spinner_item, spinnerItemFakultas)
        spinnerAdapterFakultas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        viewBind.spinnerKota.adapter = spinnerAdapterFakultas
        viewBind.spinnerKota.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {
                    //notImplemented
                }

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    positionKota = position
                }
            }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == AppConstants.FRAGMENT_CODE) {
                bidangKerja = data?.getStringExtra("bidangKerjaNama") ?: ""
                bidangKerjaId = data!!.getIntExtra("bidangKerjaId", 0)
            }
        }

        if (requestCode == PICK_FOTO_TEMPAT_PKL && resultCode == Activity.RESULT_OK
            && data != null && data.getData() != null
        ) {
            data.data.let { returnUri ->
                context?.contentResolver?.query(returnUri!!, null, null, null, null)
            }?.use { cursor ->
                val sizeIndex = cursor.getColumnIndex(OpenableColumns.SIZE)
                cursor.moveToFirst()
                if (cursor.getLong(sizeIndex).toDouble().div(1024) < 1000.0) {
                    fotoLombaUri = data.getData()!!
                    viewBind.ivFotoTempatPkl.visibility = View.VISIBLE
                    Glide.with(this.context)
                        .load(fotoLombaUri)
                        .into(viewBind.ivFotoTempatPkl)
                } else {
                    showMessageToast("File melebihi 1MB")
                }
            }
        }
    }

    override fun setUp() {

    }

}
