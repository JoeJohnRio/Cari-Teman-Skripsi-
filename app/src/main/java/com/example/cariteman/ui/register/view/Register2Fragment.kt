package com.example.cariteman.ui.register.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.cariteman.databinding.ActivityRegister2Binding
import com.example.cariteman.ui.base.view.BaseFragment
import androidx.fragment.app.FragmentActivity
import android.app.Activity
import com.example.cariteman.R
import com.example.cariteman.data.model.*
import com.example.cariteman.util.extension.addFragmentWithBackStack
import com.example.cariteman.ui.register.presenter.RegisterMVPPresenter
import javax.inject.Inject
import android.widget.Toast


class Register2Fragment : BaseFragment(), AdapterView.OnItemSelectedListener, RegisterMVPView {

    @Inject
    lateinit var presenter: RegisterMVPPresenter<RegisterMVPView>

    private var positionFakultas: Int = 0
    private var positionProgramStudi: Int = 0
    private var positionKeminatan: Int = 0
    private var positionTahun: Int = 0
    private lateinit var myContext: Activity
    private lateinit var viewBind: ActivityRegister2Binding
    private lateinit var contextActivity: Activity
    private lateinit var spinnerTahunMulai: List<String>

    companion object {
        internal val TAG = "Register1"
        fun newInstance(): Register2Fragment {
            return Register2Fragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBind = ActivityRegister2Binding.inflate(inflater, container, false)
        viewBind.bRegister.setOnClickListener {
            if (positionFakultas * positionProgramStudi * positionTahun == 0) {
                Toast.makeText(context, "Isi informasi terlebih dahulu", Toast.LENGTH_LONG).show()
            } else {
                (contextActivity as Register1Activity).mahasiswa.let {

                    it.foto_profil = "https://cdn0-production-images-kly.akamaized.net/2Sr4_IgZ4EVApX_JbCljXD_ympY=/640x360/smart/filters:quality(75):strip_icc():format(jpeg)/kly-media-production/medias/2714564/original/034635400_1548654389-Dx95B_oUUAANtWV.jpg"
                    it.foto_ktm = "https://cdn0-production-images-kly.akamaized.net/2Sr4_IgZ4EVApX_JbCljXD_ympY=/640x360/smart/filters:quality(75):strip_icc():format(jpeg)/kly-media-production/medias/2714564/original/034635400_1548654389-Dx95B_oUUAANtWV.jpg"
                    it.id_fakultas = positionFakultas
                    it.id_program_studi = positionProgramStudi
                    it.id_keminatan = positionKeminatan
                    if (it.id_keminatan == 0) it.id_keminatan = null
                    it.tahun_mulai = positionTahun + 2012
                    it.jenis_kelamin =
                        viewBind.rgJenisKelamin.checkedRadioButtonId.equals(viewBind.rPerempuan)
                    Toast.makeText(context, "" + it.jenis_kelamin, Toast.LENGTH_LONG).show()
                }

                activity?.supportFragmentManager?.addFragmentWithBackStack(
                    R.id.cl_register_next,
                    Register3Fragment.newInstance(),
                    Register3Fragment.TAG
                )
            }
        }

        presenter.onAttach(this)
        presenter.getFakultasResponse()
        return viewBind.root
    }

    override fun onAttach(activity: Activity) {
        myContext = activity as FragmentActivity
        contextActivity = activity as Register1Activity
        super.onAttach(activity)
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
    }

    override fun showFakultas(responses: ArrayList<Fakultas>) {
        var counter = 0
        val fakultasArray = arrayOfNulls<String>(responses.size)
        for (response in responses) {
            fakultasArray[counter] = response.name
            counter++
        }
        val spinnerItemFakultas = fakultasArray
        val spinnerAdapterFakultas =
            ArrayAdapter(context!!, android.R.layout.simple_spinner_item, spinnerItemFakultas)
        spinnerAdapterFakultas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        viewBind.spinnerFakultas.adapter = spinnerAdapterFakultas
        viewBind.spinnerFakultas.onItemSelectedListener =
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
                    positionFakultas = position
                    positionKeminatan = 0
                    positionProgramStudi = 0
                    presenter.getProgramStudiResponse(position)
                }
            }
    }

    override fun showProgramStudi(responses: ArrayList<ProgramStudi>) {
        var counter = 0
        val programStudiArray = arrayOfNulls<String>(responses.size)
        for (response in responses) {
            programStudiArray[counter] = response.name
            counter++
        }
        val spinnerItemProgramStudi = programStudiArray
        val spinnerAdapterProgramStudi =
            ArrayAdapter(context!!, android.R.layout.simple_spinner_item, spinnerItemProgramStudi)
        spinnerAdapterProgramStudi.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        viewBind.spinnerProgramStudi.adapter = spinnerAdapterProgramStudi

        viewBind.spinnerProgramStudi.onItemSelectedListener =
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
                    positionProgramStudi = position
                    positionKeminatan = 0
                    presenter.getKeminatanResponse(position)
                }
            }
    }

    override fun showKeminatan(responses: ArrayList<Keminatan>) {
        var counter = 0
        val programStudiArray = arrayOfNulls<String>(responses.size)
        for (response in responses) {
            programStudiArray[counter] = response.name
            counter++
        }
        val spinnerItemProgramStudi = programStudiArray
        val spinnerAdapterProgramStudi =
            ArrayAdapter(context!!, android.R.layout.simple_spinner_item, spinnerItemProgramStudi)
        spinnerAdapterProgramStudi.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        viewBind.spinnerKeminatan.adapter = spinnerAdapterProgramStudi

        viewBind.spinnerKeminatan.onItemSelectedListener =
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
                    positionKeminatan = position
                }
            }
    }

    override fun openRegisterFragment() {
        //notImplemented
    }

    override fun setUp() {
        spinnerTahunMulai =
            listOf("Tahun Mulai", "2013", "2014", "2015", "2016", "2017", "2018", "2019")
        viewBind.spinnerTahunMulai.setOnItemSelectedListener(this)
        val spinnerItemTahunMulai = spinnerTahunMulai
        val spinnerAdapterTahunMulai =
            ArrayAdapter(context!!, android.R.layout.simple_spinner_item, spinnerItemTahunMulai)
        spinnerAdapterTahunMulai.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        viewBind.spinnerTahunMulai.adapter = spinnerAdapterTahunMulai

        viewBind.spinnerTahunMulai.onItemSelectedListener =
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
                    positionTahun = position
                }
            }
    }
}
