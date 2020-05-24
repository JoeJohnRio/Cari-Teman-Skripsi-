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
import android.app.Activity.RESULT_OK
import com.example.cariteman.R
import com.example.cariteman.data.model.*
import com.example.cariteman.util.extension.addFragmentWithBackStack
import com.example.cariteman.ui.register.presenter.RegisterMVPPresenter
import javax.inject.Inject
import android.widget.Toast
import android.content.Intent
import android.net.Uri
import android.provider.OpenableColumns
import com.bumptech.glide.Glide
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import android.webkit.MimeTypeMap
import com.example.cariteman.util.Utils
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.storage.UploadTask
import java.io.File


class Register2Fragment : BaseFragment(), AdapterView.OnItemSelectedListener, RegisterMVPView {

    @Inject
    lateinit var presenter: RegisterMVPPresenter<RegisterMVPView>

    private var positionFakultas: Int = 0
    private var positionProgramStudi: Int = 0
    private var positionKeminatan: Int = 0
    private var positionTahun: Int = 0
    private val PICK_FOTO_KTM = 1
    private val PICK_FOTO_PROFILE = 2
    private var fotoProfile = ""
    private var fotoKtm = ""
    private lateinit var myContext: Activity
    private lateinit var viewBind: ActivityRegister2Binding
    private lateinit var contextActivity: Activity
    private lateinit var spinnerTahunMulai: List<String>
    private lateinit var mStorageRef: StorageReference
    private lateinit var mDatabaseRef: DatabaseReference

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
            if (positionFakultas * positionProgramStudi * positionTahun == 0 || (activity as RegisterActivity).fotoKtmUri == null || (activity as RegisterActivity).fotoProfileUri == null) {
                Toast.makeText(context, "Isi informasi terlebih dahulu", Toast.LENGTH_LONG).show()
            } else {
                (contextActivity as RegisterActivity).mahasiswa.let {
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
                    R.id.cl_register,
                    Register3Fragment.newInstance(),
                    Register3Fragment.TAG
                )
            }
        }

        viewBind.ivAddFotoKtm.setOnClickListener {
            openFileFotoKtm()
        }

        viewBind.ivAddFotoProfile.setOnClickListener {
            openFileFotoProfile()
        }


        presenter.onAttach(this)
        presenter.getFakultasResponse()
        return viewBind.root
    }

    fun openFileFotoKtm() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent, PICK_FOTO_KTM)
    }

    fun openFileFotoProfile() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent, PICK_FOTO_PROFILE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_FOTO_KTM && resultCode == RESULT_OK
            && data != null && data.getData() != null
        ) {
            data.data.let { returnUri ->
                context?.contentResolver?.query(returnUri!!, null, null, null, null)
            }?.use { cursor ->
                val sizeIndex = cursor.getColumnIndex(OpenableColumns.SIZE)
                cursor.moveToFirst()
                if (cursor.getLong(sizeIndex).toDouble().div(1024) < 1000.0) {
                    (activity as RegisterActivity).fotoKtmUri = data.getData()!!
                    Glide.with(this.context)
                        .load((activity as RegisterActivity).fotoKtmUri)
                        .into(viewBind.ivAddFotoKtm)
                } else {
                    showMessageToast("File melebihi 1MB")
                }
            }
        } else if (requestCode == PICK_FOTO_PROFILE && resultCode == RESULT_OK
            && data != null && data.getData() != null
        ) {
            data.data.let { returnUri ->
                context?.contentResolver?.query(returnUri!!, null, null, null, null)
            }?.use { cursor ->
                val sizeIndex = cursor.getColumnIndex(OpenableColumns.SIZE)
                cursor.moveToFirst()
                if (cursor.getLong(sizeIndex).toDouble().div(1024) < 1000.0) {
                    (activity as RegisterActivity).fotoProfileUri = data.getData()!!
                    Glide.with(this.context)
                        .load((activity as RegisterActivity).fotoProfileUri)
                        .into(viewBind.ivAddFotoProfile)
                    showMessageToast(
                        Utils.uploadFileFromFragment(
                            (activity as RegisterActivity).fotoProfileUri!!,
                            contextActivity,
                            this
                        )
                    )
                } else {
                    showMessageToast("File melebihi 1MB")
                }
            }
        }
    }

    override fun onAttach(activity: Activity) {
        myContext = activity as FragmentActivity
        contextActivity = activity as RegisterActivity
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
