package com.example.cariteman.ui.verifikasiakun.verifikasiakunlist.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.cariteman.data.model.MahasiswaNotVerifiedListResponse
import com.example.cariteman.databinding.FragmentDetailMahasiswaVerifiedBinding
import com.example.cariteman.ui.base.view.BaseFragment
import com.example.cariteman.ui.verifikasiakun.verifikasiakunlist.presenter.VerifikasiAkunDetailPresenter
import com.example.cariteman.util.Utils
import javax.inject.Inject

class VerifikasiAkunDetailFragment : BaseFragment(),
    VerifikasiAkunDetailMVPView {
    @Inject
    lateinit var presenter: VerifikasiAkunDetailPresenter<VerifikasiAkunDetailMVPView>

    lateinit var viewBind: FragmentDetailMahasiswaVerifiedBinding

    companion object {

        internal val TAG = "Verifikasi Akun"

        private var url: String = "https://tanzolymp.com/images/default-non-user-no-photo-1-300x300.jpg"
        fun newInstance(): VerifikasiAkunDetailFragment {
            return VerifikasiAkunDetailFragment()
        }

    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)

        viewBind = FragmentDetailMahasiswaVerifiedBinding.inflate(inflater, container, false)
        presenter.onAttach(this)
        context.let { presenter.setKey(Utils.loadData(it!!)) }

        val bundle: Bundle? = arguments

        val idMahasiswa = bundle?.getInt("idMahasiswa", 0) ?: 0
        if (idMahasiswa != 0){
            presenter.getMahasiswaDetail(MahasiswaNotVerifiedListResponse(id = idMahasiswa))
        }else{
            showMessageToast("error")
        }

        viewBind.mbAcceptVerification.setOnClickListener {
            presenter.confirmMahasiswa(MahasiswaNotVerifiedListResponse(id = idMahasiswa, status = 1))
        }
        viewBind.mbDeclineVerification.setOnClickListener {
            presenter.confirmMahasiswa(MahasiswaNotVerifiedListResponse(id = idMahasiswa, status = 0))
        }

        return viewBind.root
    }
    override fun showMahasiswaDetail(response: MahasiswaNotVerifiedListResponse) {
        viewBind.tvNamaVerifyingDetail.text = response.name
        viewBind.tvFakultasVerifyingDetail.text = response.fakultas
        viewBind.tvProgramStudiVerifyingDetail.text = response.programStudi
        viewBind.tvKeminatanVerifyingDetail.text = response.keminatan
        viewBind.tvTahunMulaiVerifyingDetail.text = response.tahunMulai.toString()
        viewBind.tvEmailVerifyingDetail.text = response.email
        if (response.jenisKelamin == 0){
            viewBind.tvJenisKelaminVerifyingDetail.text = "Laki laki"
        }else{
            viewBind.tvJenisKelaminVerifyingDetail.text = "Perempuan"
        }
        if (response.preferensi == 0){
            viewBind.tvPreferensiVerifyingDetail.text = "PKL"
        }else{
            viewBind.tvPreferensiVerifyingDetail.text = "Lomba"
        }
        viewBind.tvNimVerifyingDetail.text = response.nim

        Glide.with(context)
            .load(response.fotoKtm ?: url)
            .into(viewBind.ivFotoKtmVerifyingDetail)


        Glide.with(context)
            .load(response.fotoProfil ?: url)
            .into(viewBind.ivFotoProfileVerifyingDetail)
    }

    override fun mahasiswaConfirmed() {
        activity?.onBackPressed()
    }

    override fun setUp() {

    }

}