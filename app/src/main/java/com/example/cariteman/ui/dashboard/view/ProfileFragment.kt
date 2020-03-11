package com.example.cariteman.ui.dashboard.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import com.example.cariteman.R
import com.example.cariteman.databinding.FragmentProfileBinding
import com.example.cariteman.ui.DaftarKelompokActivity
import com.example.cariteman.ui.PengaturanActivity
import com.example.cariteman.ui.UserProfilActivity
import com.example.cariteman.ui.base.view.BaseFragment
import com.example.cariteman.ui.pengalaman.PengalamanActivity
import com.example.cariteman.util.Utils
import com.example.cariteman.util.extension.addFragment

class ProfileFragment : BaseFragment(), AdapterView.OnItemSelectedListener, DashboardMVPView{

    private lateinit var viewBind: FragmentProfileBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        viewBind = FragmentProfileBinding.inflate(inflater, container, false)

        Toast.makeText(context, "Profile", Toast.LENGTH_LONG).show()
        activity?.title = getString(R.string.title_home)
        Utils.loadData(context!!)
        return viewBind.root
    }

    override fun setUp() {
        viewBind.bPengaturan.setOnClickListener {
            val intent = Intent(context, PengaturanActivity::class.java)
            startActivity(intent)
        }

        viewBind.bProfilSaya.setOnClickListener {
            val intent = Intent(context, UserProfilActivity::class.java)
            startActivity(intent)
        }

        viewBind.bDaftarKelompok.setOnClickListener{
            val intent = Intent(context, DaftarKelompokActivity::class.java)
            startActivity(intent)
        }

        viewBind.bPengalaman.setOnClickListener {
            val intent = Intent(context, PengalamanActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        //notImplemented
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        //notImplemented
    }

    companion object {
        val TAG: String = ProfileFragment::class.java.simpleName
        fun newInstance() = ProfileFragment()
    }
}