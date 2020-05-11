package com.example.cariteman.ui.pengalaman.pengalamanhome.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import com.example.cariteman.R
import com.example.cariteman.data.model.*
import com.example.cariteman.databinding.FragmentTambahKelompok2Binding
import com.example.cariteman.ui.base.view.BaseFragment
import com.example.cariteman.ui.pengalaman.pengalamanhome.presenter.TambahKelompok2Presenter
import com.example.cariteman.ui.pengalaman.view.KelompokActivity
import com.example.cariteman.util.Mapper
import com.example.cariteman.util.Utils
import javax.inject.Inject

class TambahKelompok2Fragment : BaseFragment(),
    TambahKelompok2MVPView {
    @Inject
    lateinit var presenter: TambahKelompok2Presenter<TambahKelompok2MVPView>

    lateinit var viewBind: FragmentTambahKelompok2Binding
    var url = "https://ae01.alicdn.com/kf/HTB1N_RqXiDxK1Rjy1zcq6yGeXXay/Pesona-Serangga-Kalung-Liontin-Ambar-Scorpion-Liontin-Kalung-dengan-Tali-Adjustable-Scorpion-Lebah-Semut-Tawon.jpg"
    var pklOrLomba = 0

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
        val listOfTeman: ArrayList<DaftarTemanHanyaNama>? =
            bundle?.getParcelableArrayList("listOfFriend")

        viewBind.rgPilihanTipeKelompok.setOnCheckedChangeListener(object: RadioGroup.OnCheckedChangeListener{
            override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
                when(checkedId){
                    R.id.radioPkl -> {
                        pklOrLomba = 0
                    }
                    R.id.radioLomba ->{
                        pklOrLomba = 1
                    }
                }
            }

        })

        viewBind.fabAddKelompok.setOnClickListener {
            presenter.makeKelompok(
                kelompok = Kelompok(
                    namaKelompok = viewBind.etKelompokName.text.toString(),
                    jumlahAnggota = listOfTeman?.size,
                    tipeKelompok = pklOrLomba,
                    fotoKelompok = url,
                    calonAnggotas = Mapper.temanToRelationKelompok(listOfTeman!!)
                )
                )
        }

        return viewBind.root
    }

    override fun returnToHome() {
        var intent = Intent(context, KelompokActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }



    override fun setUp() {

    }

}