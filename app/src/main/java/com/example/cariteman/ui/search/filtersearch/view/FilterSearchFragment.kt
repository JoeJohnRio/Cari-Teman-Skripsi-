package com.example.cariteman.ui.search.filtersearch.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.cariteman.data.model.*
import com.example.cariteman.databinding.FragmentFilterBinding
import com.example.cariteman.ui.base.view.BaseFragment
import com.example.cariteman.ui.pengalaman.pengalamanhome.presenter.FilterSearchPresenter
import com.example.cariteman.ui.pengalaman.pengalamanhome.view.SkillHobiFragment
import com.example.cariteman.ui.pengalaman.view.SearchActivity
import com.example.cariteman.R
import com.example.cariteman.ui.pengalaman.pengalamanhome.view.BidangKerjaFragment
import com.example.cariteman.util.Utils
import com.example.cariteman.util.extension.addFragmentWithBackStack
import javax.inject.Inject

class FilterSearchFragment : BaseFragment(),
    FilterSearchMVPView, AdapterView.OnItemSelectedListener {
    @Inject
    lateinit var presenter: FilterSearchPresenter<FilterSearchMVPView>

    lateinit var viewBind: FragmentFilterBinding

    private lateinit var spinnerTahunMulai: List<String>

    var searchFilter = SearchFilter()
    var type = 0
    private var positionFakultas: Int = 0
    private var positionProgramStudi: Int = 0
    private var positionKeminatan: Int = 0
    private var positionTahun: Int = 0
    private var positionKota: Int = 0


    companion object {
        internal val TAG = "SearchHistory"
        fun newInstance(): FilterSearchFragment {
            return FilterSearchFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBind = FragmentFilterBinding.inflate(inflater, container, false)
        presenter.onAttach(this)
        context.let { presenter.setKey(Utils.loadData(it!!)) }

        presenter.getFakultasResponse()
        presenter.getLokasiPkl()

        val activityMain = activity as SearchActivity
        spinnerTahunMulai =
            listOf("Tahun Mulai", "2013", "2014", "2015", "2016", "2017", "2018", "2019")
        viewBind.spinnerTahunMulai.setOnItemSelectedListener(this)
        val spinnerItemTahunMulai = spinnerTahunMulai
        val spinnerAdapterTahunMulai =
            ArrayAdapter(context!!, android.R.layout.simple_spinner_item, spinnerItemTahunMulai)
        spinnerAdapterTahunMulai.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        if (!activityMain.skillHobi.namaSkillhobi.isNullOrEmpty()) {
            viewBind.tvSkillHobi.setText(activityMain.skillHobi.namaSkillhobi)
        }
        if (!activityMain.bidangKerja.namaBidangKerja.isNullOrEmpty()) {
            viewBind.tvSkillHobi.setText(activityMain.bidangKerja.namaBidangKerja)
        }

        viewBind.tvSkillHobi.setOnClickListener {
                getBaseActivity()?.supportFragmentManager?.addFragmentWithBackStack(
                    com.example.cariteman.R.id.cl_search,
                    SkillHobiFragment.newInstance(),
                    SkillHobiFragment.TAG
                )
        }

        viewBind.tvBidangKerja.setOnClickListener {
            val bundle = Bundle()
            bundle.putBoolean("isFilter", true)

            val bidangKerja = BidangKerjaFragment.newInstance()
            bidangKerja.arguments = bundle
            getBaseActivity()?.supportFragmentManager?.addFragmentWithBackStack(
                com.example.cariteman.R.id.cl_search,
                bidangKerja,
                BidangKerjaFragment.TAG
            )
        }

        viewBind.bFilter.setOnClickListener {
            if (activityMain.filterDetails.preferensi == 3) {
                activityMain.filterTempatPkl.let {
                    it.idLokasiPkl = positionKota
                    it.idBidangKerja = (activity as SearchActivity).bidangKerja.id
                }
            } else {
                activityMain.filterDetails.let {
                    it.preferensi = (activity as SearchActivity).filterDetails.preferensi
                    it.idFakultas = positionFakultas
                    it.idProgramStudi = positionProgramStudi
                    it.idKeminatan = positionKeminatan
                    it.idSkillHobi = activityMain.skillHobi.id
                    it.jenisKelamin =
                        Utils.booleanToInt(
                            viewBind.rgJenisKelamin.checkedRadioButtonId.equals(
                                viewBind.rPerempuan
                            )
                        )
                    if (it.tahunMulai != 0) {
                        it.tahunMulai = positionTahun + 2012
                    }
                }
            }

            activityMain.onBackPressed()
        }

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

        return viewBind.root
    }

    override fun handleLokasiPkl(responses: MutableList<LokasiPklResponse>) {
        var counter = 0
        val kotaArray = arrayOfNulls<String>(responses.size)
        for (response in responses) {
            kotaArray[counter] = response.namaKota
            counter++
        }
        val spinnerItemFakultas = kotaArray
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

    override fun setUp() {
        if ((activity as SearchActivity).filterDetails.preferensi == 0) {
            (activity as SearchActivity).filterSearchButton.text = "FILTER - PKL"
            Utils.toggleThreeButton(
                viewBind.bTypeFilterPkl,
                viewBind.bTypeFilterLomba,
                viewBind.bTypeFilterTempatPkl,
                R.color.navy_blue,
                R.color.white,
                resources
            )
            viewBind.llFilterPklAndLomba.visibility = View.VISIBLE
            viewBind.llFilterTempatPkl.visibility = View.GONE
        } else if ((activity as SearchActivity).filterDetails.preferensi == 1) {
            (activity as SearchActivity).filterSearchButton.text = "FILTER - LOMBA"
            Utils.toggleThreeButton(
                viewBind.bTypeFilterLomba,
                viewBind.bTypeFilterPkl,
                viewBind.bTypeFilterTempatPkl,
                R.color.navy_blue,
                R.color.white,
                resources
            )
            viewBind.llFilterPklAndLomba.visibility = View.VISIBLE
            viewBind.llFilterTempatPkl.visibility = View.GONE
        } else if ((activity as SearchActivity).filterDetails.preferensi == 2) {
            (activity as SearchActivity).filterSearchButton.text = "FILTER - TEMPAT PKL"
            Utils.toggleThreeButton(
                viewBind.bTypeFilterTempatPkl,
                viewBind.bTypeFilterPkl,
                viewBind.bTypeFilterLomba,
                R.color.navy_blue,
                R.color.white,
                resources
            )
            viewBind.llFilterPklAndLomba.visibility = View.GONE
            viewBind.llFilterTempatPkl.visibility = View.VISIBLE
        }

        viewBind.bTypeFilterPkl.setOnClickListener {
            if ((activity as SearchActivity).filterDetails.preferensi != 0) {
                if ((activity as SearchActivity).filterDetails.preferensi == 2) {
                    viewBind.llFilterPklAndLomba.visibility = View.VISIBLE
                    viewBind.llFilterTempatPkl.visibility = View.GONE
                }
                (activity as SearchActivity).filterDetails.preferensi = 0
                (activity as SearchActivity).filterSearchButton.text = "FILTER - PKL"
                Utils.toggleThreeButton(
                    viewBind.bTypeFilterPkl,
                    viewBind.bTypeFilterLomba,
                    viewBind.bTypeFilterTempatPkl,
                    R.color.navy_blue,
                    R.color.white,
                    resources
                )
                (activity as SearchActivity).bidangKerja = BidangKerja()
            }
        }

        viewBind.bTypeFilterLomba.setOnClickListener {
            if ((activity as SearchActivity).filterDetails.preferensi != 1) {
                if ((activity as SearchActivity).filterDetails.preferensi == 2) {
                    viewBind.llFilterPklAndLomba.visibility = View.VISIBLE
                    viewBind.llFilterTempatPkl.visibility = View.GONE
                }
                (activity as SearchActivity).filterDetails.preferensi = 1
                (activity as SearchActivity).filterSearchButton.text = "FILTER - LOMBA"
                Utils.toggleThreeButton(
                    viewBind.bTypeFilterLomba,
                    viewBind.bTypeFilterPkl,
                    viewBind.bTypeFilterTempatPkl,
                    R.color.navy_blue,
                    R.color.white,
                    resources
                )
                (activity as SearchActivity).bidangKerja = BidangKerja()
            }
        }

        viewBind.bTypeFilterTempatPkl.setOnClickListener {
            if ((activity as SearchActivity).filterDetails.preferensi != 2) {
                viewBind.llFilterPklAndLomba.visibility = View.GONE
                viewBind.llFilterTempatPkl.visibility = View.VISIBLE
                (activity as SearchActivity).filterDetails.preferensi = 2
                (activity as SearchActivity).filterSearchButton.text = "FILTER - TEMPAT PKL"
                Utils.toggleThreeButton(
                    viewBind.bTypeFilterTempatPkl,
                    viewBind.bTypeFilterPkl,
                    viewBind.bTypeFilterLomba,
                    R.color.navy_blue,
                    R.color.white,
                    resources
                )
                (activity as SearchActivity).skillHobi = SkillHobi()
            }
        }
    }

    override fun backstack() {
        activity?.onBackPressed()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        //notImplemented
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        //notImplemented
    }

}
