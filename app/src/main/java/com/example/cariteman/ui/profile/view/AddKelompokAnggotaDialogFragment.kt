package com.example.cariteman.ui.profile.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cariteman.R
import com.example.cariteman.databinding.DialogfragmentAddToKelompokBinding
import com.example.cariteman.ui.pengalaman.pengalamanhome.view.DaftarKelompokListAdapter

class AddKelompokAnggotaDialogFragment: DialogFragment(){

    lateinit var viewBind: DialogfragmentAddToKelompokBinding
    lateinit var adapterPengalaman: DaftarKelompokListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        viewBind = DialogfragmentAddToKelompokBinding.inflate(inflater, container, false)

        adapterPengalaman = DaftarKelompokListAdapter(type = 1, kelompokHomeProfileActivity = (activity as ProfileActivity))
        adapterPengalaman.submitList((activity as ProfileActivity).mutableListOfKelompok)
        viewBind.rvListKelompokToAdd.apply {
            if (adapter == null) {
                adapter = adapterPengalaman
            }
            if (layoutManager == null) {
                layoutManager = LinearLayoutManager(context)
            }
            adapter?.notifyDataSetChanged()
        }

        return viewBind.root
    }
}