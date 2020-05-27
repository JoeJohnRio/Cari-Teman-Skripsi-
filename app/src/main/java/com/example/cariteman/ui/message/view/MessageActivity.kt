package com.example.cariteman.ui.message.view

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cariteman.R
import com.example.cariteman.data.model.MessageKelompok
import com.example.cariteman.data.model.MessageSend
import com.example.cariteman.databinding.ActivityMessageBinding
import com.example.cariteman.ui.base.view.BaseActivity
import com.example.cariteman.ui.message.presenter.MessagePresenter
import com.example.cariteman.util.Utils
import dagger.android.DispatchingAndroidInjector
import javax.inject.Inject
import kotlin.concurrent.fixedRateTimer


class MessageActivity: BaseActivity(), MessageMVPView {
    @Inject
    lateinit var presenter: MessagePresenter<MessageMVPView>

    @Inject
    internal lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    lateinit var viewBind: ActivityMessageBinding

    var isiPesan = ""
    var listOfMessage: MutableList<MessageKelompok> = mutableListOf()
    var adapterMessage = MessageKelompokListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBind = DataBindingUtil.setContentView(this, R.layout.activity_message)
        presenter.onAttach(this)
        presenter.setKey(Utils.loadData(applicationContext))

        val isKelompok = intent.getIntExtra("isKelompok", 0)
        val idKelompok = intent.getIntExtra("idKelompok", 0)
        val idMahasiswaPenerima = intent.getIntExtra("idMahasiswaPenerima", 0)
        val namaToolbar = intent.getStringExtra("namaToolbar") ?: ""

        viewBind.tvToolbarTitle.setText(namaToolbar)

        if (isKelompok == 1){
            presenter.showKelompok(idKelompok)

            fixedRateTimer("timer", false, 0L, 5 * 1000) {
                this@MessageActivity.runOnUiThread {
                    presenter.showKelompok(idKelompok)
                }
            }

            viewBind.ivSendButton.setOnClickListener {
                if (isiPesan.isEmpty()){
                    showMessageToast("Isi pesan terlebih dahulu")
                }else{
                    presenter.sendMessageKelompok(MessageSend(isiPesan = isiPesan, idKelompok = idKelompok))
                    viewBind.etMessage.setText("")
                }
            }
        }else if(isKelompok == 0){
            presenter.showUser(idMahasiswaPenerima)

            fixedRateTimer("timer", false, 0L, 5 * 1000) {
                this@MessageActivity.runOnUiThread {
                    presenter.showUser(idMahasiswaPenerima)
                }
            }

            viewBind.ivSendButton.setOnClickListener {
                if (isiPesan.isEmpty()){
                    showMessageToast("Isi pesan terlebih dahulu")
                }else{
                    presenter.sendMessageUser(MessageSend(isiPesan = isiPesan, idMahasiswaPenerima = idMahasiswaPenerima))
//                    View.hideKeyboard(applicationContext, View(applicationContext))
                    viewBind.etMessage.setText("")
                }
            }
        }

        viewBind.etMessage.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                //notImplemented
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //notImplemented
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                isiPesan = s.toString()
            }
        })

        viewBind.ivBack.setOnClickListener {
            finish()
        }
    }

    override fun setMessage(responses: MutableList<MessageKelompok>) {
        if (listOfMessage != responses){
            listOfMessage = responses
            adapterMessage.submitList(responses)

            viewBind.rvMessage.apply {
                if (adapter == null) {
                    adapter = adapterMessage
                }
                if (layoutManager == null) {
                    layoutManager = LinearLayoutManager(context)
                }
                adapter?.notifyDataSetChanged()
            }

            viewBind.rvMessage.scrollToPosition(responses.size - 1)
        }
    }

    override fun onFragmentAttached() {
        //notImplemented
    }

    override fun onFragmentDetached(tag: String) {
        //notImplemented
    }
}