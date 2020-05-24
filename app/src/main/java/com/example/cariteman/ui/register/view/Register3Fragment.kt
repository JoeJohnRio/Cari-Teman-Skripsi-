package com.example.cariteman.ui.register.view

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.cariteman.R
import com.example.cariteman.data.model.Fakultas
import com.example.cariteman.data.model.Keminatan
import com.example.cariteman.data.model.ProgramStudi
import com.example.cariteman.databinding.ActivityRegister3Binding
import com.example.cariteman.ui.base.view.BaseFragment
import com.example.cariteman.ui.register.presenter.RegisterMVPPresenter
import com.example.cariteman.util.Utils
import com.example.cariteman.util.Utils.mStorageRef
import com.example.cariteman.util.Utils.uploadFileFromFragment
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import javax.inject.Inject

class Register3Fragment : BaseFragment(), RegisterMVPView {

    companion object {

        internal val TAG = "Register1"

        fun newInstance(): Register3Fragment {
            return Register3Fragment()
        }
    }

    @Inject
    lateinit var presenter: RegisterMVPPresenter<RegisterMVPView>

    private var togglePreferensiPklorLomba: Boolean = true
    private lateinit var viewBind: ActivityRegister3Binding
    private lateinit var contextActivity: Activity

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBind = ActivityRegister3Binding.inflate(inflater, container, false)
        presenter.onAttach(this)
        return viewBind.root
    }


    override fun onAttach(activity: Activity) {
        contextActivity = activity as RegisterActivity
        super.onAttach(activity)
    }

    override fun setUp() {
        viewBind.bRegister.setOnClickListener {
            (contextActivity as RegisterActivity).mahasiswa.let {
                it.foto_profil =
                    uploadFileFromFragment(
                        (activity as RegisterActivity).fotoProfileUri!!,
                        contextActivity,
                        this
                    )
                it.foto_ktm =
                    uploadFileFromFragment(
                        (activity as RegisterActivity).fotoKtmUri!!,
                        contextActivity,
                        this
                    )
                it.preferensi = togglePreferensiPklorLomba
                presenter.sendMahasiswaData((contextActivity as RegisterActivity).mahasiswa)
                Toast.makeText(context, "Akun anda sedang di review", Toast.LENGTH_LONG).show()
            }
            (contextActivity as RegisterActivity).finish()
        }

        viewBind.cvLomba.setOnClickListener {
            togglePreferensiPklorLomba = true
            viewBind.cvLomba.backgroundTintList =
                resources.getColorStateList(R.color.navy_blue_high)
            viewBind.tvLomba.setTextColor(resources.getColorStateList(R.color.white_low))

            viewBind.cvPkl.backgroundTintList = resources.getColorStateList(R.color.white_low)
            viewBind.tvPkl.setTextColor(resources.getColorStateList(R.color.navy_blue_high))
        }

        viewBind.cvPkl.setOnClickListener {
            togglePreferensiPklorLomba = false
            viewBind.cvLomba.backgroundTintList = resources.getColorStateList(R.color.white_low)
            viewBind.tvLomba.setTextColor(resources.getColorStateList(R.color.navy_blue_high))

            viewBind.cvPkl.backgroundTintList = resources.getColorStateList(R.color.navy_blue_high)
            viewBind.tvPkl.setTextColor(resources.getColorStateList(R.color.white_low))
        }
    }

    override fun openRegisterFragment() {
        //notImplemented
    }

    override fun showFakultas(responses: ArrayList<Fakultas>) {
        //notImplemented
    }

    override fun showProgramStudi(responses: ArrayList<ProgramStudi>) {
        //notImplemented
    }

    override fun showKeminatan(responses: ArrayList<Keminatan>) {
        //notImplemented
    }

}