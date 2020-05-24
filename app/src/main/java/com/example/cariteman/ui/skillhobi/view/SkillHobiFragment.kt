package com.example.cariteman.ui.pengalaman.pengalamanhome.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cariteman.data.model.PengalamanLombaOrganisasiResponse
import com.example.cariteman.ui.base.view.BaseFragment
import com.example.cariteman.util.Utils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext
import com.example.cariteman.data.model.SkillHobi
import com.example.cariteman.databinding.FragmentPilihSkillHobiBinding
import com.example.cariteman.ui.pengalaman.pengalamanhome.presenter.SkillHobiPresenter
import com.example.cariteman.ui.pengalaman.view.SearchActivity


class SkillHobiFragment : BaseFragment(),
    SkillHobiMVPView, CoroutineScope {
    @Inject
    lateinit var presenter: SkillHobiPresenter<SkillHobiMVPView>

    lateinit var viewBind: FragmentPilihSkillHobiBinding
    lateinit var adapterSkillHobi: SkillHobiListAdapter
    lateinit var modifyPengalaman: PengalamanLombaOrganisasiResponse
    var skillHobiId = 0
    var skillHobiNama: String = ""
    var isFirst: Boolean = true
    var mutableListOfSkillHobi: MutableList<SkillHobi> = mutableListOf()

    companion object {

        internal val TAG = "SkillHobi"
        fun newInstance(): SkillHobiFragment {
            return SkillHobiFragment()
        }
    }

    override val coroutineContext: CoroutineContext = Dispatchers.Main

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBind = FragmentPilihSkillHobiBinding.inflate(inflater, container, false)
        presenter.onAttach(this)
        context.let { presenter.setKey(Utils.loadData(it!!)) }
        val bundle: Bundle?= arguments
        val namaBidangKerja = bundle?.getString("namaBidangKerja")
        adapterSkillHobi = SkillHobiListAdapter(this)
        modifyPengalaman = PengalamanLombaOrganisasiResponse()

        if (!namaBidangKerja.isNullOrEmpty()){
            presenter.getSkillHobiSearch(namaBidangKerja)
        }

        viewBind.etBidangKerjaName.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                //notImplemented
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //notImplemented
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                Handler().postDelayed({
                if(s.toString() != ""){
                    presenter.getSkillHobiSearch(s.toString())
                }
//                }, 500)
            }
        })


        viewBind.mbNextBidangKerja.setOnClickListener {
//            if (viewBind.etBidangKerjaName.text.toString() == "" && bidangKerjaId == 0){
//                showMessageToast("Pilih bidang kerja dahulu")
//            }else if(viewBind.etBidangKerjaName.text.toString() != "" && bidangKerjaId == 0){
//                var isExist = false
//                for (bidangKerja in mutableListOfBidangKerja){
//                    if (viewBind.etBidangKerjaName.text.toString().equals(bidangKerja.namaBidangKerja, true)){
//                        isExist = true
//                        showMessageToast("Bidang kerja sudah ada")
//                    }
//                }
//                if (!isExist){
//                    presenter.makeNewSkillHobi(viewBind.etBidangKerjaName.text.toString())
//                }
//            }else{
                popBackStackWithBidangKerja(SkillHobi(id = skillHobiId, namaSkillhobi = skillHobiNama))
//            }
        }

        return viewBind.root
    }

    override fun popBackStackWithBidangKerja(skillHobi: SkillHobi) {
        (activity as SearchActivity).skillHobi = skillHobi

        activity?.onBackPressed()
    }

    override fun showBidangKerja(listOfBidangKerja: MutableList<SkillHobi>) {
        skillHobiNama = ""
        isFirst = true
        mutableListOfSkillHobi = listOfBidangKerja
        adapterSkillHobi.submitList(listOfBidangKerja)
        viewBind.rvItemBidangKerja.apply {
            if (adapter == null) {
                adapter = adapterSkillHobi
            }
            if (layoutManager == null) {
                layoutManager = LinearLayoutManager(context)
            }
            adapter?.notifyDataSetChanged()
        }
    }

    override fun setUp() {

    }

}