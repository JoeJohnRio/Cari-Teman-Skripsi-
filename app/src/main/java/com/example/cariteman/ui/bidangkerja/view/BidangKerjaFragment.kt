package com.example.cariteman.ui.pengalaman.pengalamanhome.view

import android.app.Activity.RESULT_OK
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cariteman.data.model.BidangKerja
import com.example.cariteman.data.model.PengalamanLombaOrganisasiResponse
import com.example.cariteman.databinding.FragmentPilihBidangKerjaBinding
import com.example.cariteman.ui.base.view.BaseFragment
import com.example.cariteman.ui.pengalaman.pengalamanhome.presenter.BidangKerjaPresenter
import com.example.cariteman.util.Utils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext
import android.content.Intent
import com.example.cariteman.data.model.SkillHobi
import com.example.cariteman.ui.pengalaman.view.SearchActivity


class BidangKerjaFragment : BaseFragment(),
    BidangKerjaMVPView, CoroutineScope {
    @Inject
    lateinit var presenter: BidangKerjaPresenter<BidangKerjaMVPView>

    lateinit var viewBind: FragmentPilihBidangKerjaBinding
    lateinit var adapterBidangKerja: BidangKerjaListAdapter
    lateinit var modifyPengalaman: PengalamanLombaOrganisasiResponse
    var bidangKerjaId = 0
    var bidangKerjaNama: String = ""
    var isFirst: Boolean = true
    var isFilter: Boolean = false
    var mutableListOfBidangKerja: MutableList<BidangKerja> = mutableListOf()

    companion object {

        internal val TAG = "BidangKerja"
        fun newInstance(): BidangKerjaFragment {
            return BidangKerjaFragment()
        }
    }

    override val coroutineContext: CoroutineContext = Dispatchers.Main

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBind = FragmentPilihBidangKerjaBinding.inflate(inflater, container, false)
        presenter.onAttach(this)
        context.let { presenter.setKey(Utils.loadData(it!!)) }
        val bundle: Bundle?= arguments
        bidangKerjaNama = bundle?.getString("namaBidangKerja") ?: ""
        isFilter = bundle?.getBoolean("isFilter") ?: false
        adapterBidangKerja = BidangKerjaListAdapter(this)
        modifyPengalaman = PengalamanLombaOrganisasiResponse()

        if (!bidangKerjaNama.isEmpty()){
            viewBind.etBidangKerjaName.setText(bidangKerjaNama)
            presenter.getBidangKerjaSearch(bidangKerjaNama)
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
                    presenter.getBidangKerjaSearch(s.toString())
//                }, 500)
            }
        })


        viewBind.mbNextBidangKerja.setOnClickListener {
            if (viewBind.etBidangKerjaName.text.toString() == "" && bidangKerjaId == 0){
                showMessageToast("Pilih bidang kerja dahulu")
            }else if(viewBind.etBidangKerjaName.text.toString() != "" && bidangKerjaId == 0){
                var isExist = false
                for (bidangKerja in mutableListOfBidangKerja){
                    if (viewBind.etBidangKerjaName.text.toString().equals(bidangKerja.namaBidangKerja, true)){
                        isExist = true
                        showMessageToast("Bidang kerja sudah ada")
                    }
                }
                if (!isExist){
                    presenter.makeNewBidangKerja(viewBind.etBidangKerjaName.text.toString())
                }
            }else{
                popBackStackWithBidangKerja(BidangKerja(id = bidangKerjaId, namaBidangKerja = bidangKerjaNama))
            }
        }

        return viewBind.root
    }

    override fun popBackStackWithBidangKerja(bidangKerja: BidangKerja) {
        if (isFilter == true){
            (activity as SearchActivity).bidangKerja = bidangKerja
            (activity as SearchActivity).skillHobi = SkillHobi()

            activity?.onBackPressed()
        }else{
            val intent = Intent(context, BidangKerjaFragment::class.java)
            intent.putExtra("bidangKerjaNama", bidangKerja.namaBidangKerja)
            intent.putExtra("bidangKerjaId", bidangKerja.id)
            targetFragment!!.onActivityResult(targetRequestCode, RESULT_OK, intent)
            activity?.supportFragmentManager?.popBackStack()
        }
    }

    override fun showBidangKerja(listOfBidangKerja: MutableList<BidangKerja>) {
        bidangKerjaNama = ""
        isFirst = true
        mutableListOfBidangKerja = listOfBidangKerja
        adapterBidangKerja.submitList(listOfBidangKerja)
        viewBind.rvItemBidangKerja.apply {
            if (adapter == null) {
                adapter = adapterBidangKerja
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