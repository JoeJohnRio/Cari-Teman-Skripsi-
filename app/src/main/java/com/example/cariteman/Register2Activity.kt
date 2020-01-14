package com.example.cariteman

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.cariteman.databinding.ActivityRegister2Binding

class Register2Activity :AppCompatActivity(), AdapterView.OnItemSelectedListener{

    private lateinit var viewBind: ActivityRegister2Binding
    private lateinit var spinnerProgramStudiContent: List<String>
    private lateinit var spinnerFakultasContent: List<String>
    private lateinit var spinnerKeminatanContent: List<String>
    private lateinit var spinnerTahunMulai: List<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_2)
        viewBind = DataBindingUtil.setContentView(this, R.layout.activity_register_2)

        viewBind.bRegister.setOnClickListener {
            val intent = Intent(this, Register3Activity::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        spinnerFakultasContent = listOf("Fakultas", "Filkom", "FTP")
        spinnerProgramStudiContent = listOf("Program Studi", "Teknik Informatika", "Teknik Komputer", "Teknologi Informasi", "Pendidikan Teknologi Informasi"
        , "Sistem Informasi")
        spinnerKeminatanContent = listOf("Keminatan", "Rekayasa Perangkat Lunak", "Jaringan", "Komputasi Cerdas", "Multimedia, Game, & Mobile")
        spinnerTahunMulai = listOf("Tahun Mulai", "2013", "2014", "2015", "2016", "2017", "2018", "2019")

        viewBind.spinnerFakultas.setOnItemSelectedListener(this)
        val spinnerItemFakultas = spinnerFakultasContent
        val spinnerAdapterFakultas = ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerItemFakultas)
        spinnerAdapterFakultas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        viewBind.spinnerFakultas.adapter = spinnerAdapterFakultas

        viewBind.spinnerKeminatan.setOnItemSelectedListener(this)
        val spinnerItemKeminatan = spinnerKeminatanContent
        val spinnerAdapterKeminatan = ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerItemKeminatan)
        spinnerAdapterKeminatan.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        viewBind.spinnerKeminatan.adapter = spinnerAdapterKeminatan

        viewBind.spinnerProgramStudi.setOnItemSelectedListener(this)
        val spinnerItemProgramStudi = spinnerProgramStudiContent
        val spinnerAdapterProgramStudi = ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerItemProgramStudi)
        spinnerAdapterProgramStudi.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        viewBind.spinnerProgramStudi.adapter = spinnerAdapterProgramStudi

        viewBind.spinnerTahunMulai.setOnItemSelectedListener(this)
        val spinnerItemTahunMulai = spinnerTahunMulai
        val spinnerAdapterTahunMulai = ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerItemTahunMulai)
        spinnerAdapterTahunMulai.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        viewBind.spinnerTahunMulai.adapter = spinnerAdapterTahunMulai
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
    }
}