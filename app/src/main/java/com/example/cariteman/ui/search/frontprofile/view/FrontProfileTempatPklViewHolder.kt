package com.example.cariteman.ui.search.frontprofile.view

import android.content.Intent
import android.view.View
import android.view.animation.Animation
import android.view.animation.BounceInterpolator
import android.view.animation.ScaleAnimation
import android.widget.ImageView
import android.widget.TextView
import android.widget.ToggleButton
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cariteman.R
import com.example.cariteman.data.model.FrontProfileResponse
import com.example.cariteman.ui.pengalaman.pengalamanhome.presenter.FrontProfilePresenter
import com.example.cariteman.ui.profile.tempatpklprofile.view.ProfileTempatPklActivity
import com.example.cariteman.ui.search.filtersearch.view.FrontProfileMVPView
import com.example.cariteman.util.Utils
import kotlinx.android.extensions.LayoutContainer

class FrontProfileTempatPklViewHolder(override val containerView: View) :
    RecyclerView.ViewHolder(containerView),
    LayoutContainer {

    private var url: String = "https://tanzolymp.com/images/default-non-user-no-photo-1-300x300.jpg"

    private var ivProfilPic: ImageView? = null
    private var tvItemType: TextView? = null
    private var tvItemName: TextView? = null
    private var tvBidangKerja: TextView? = null
    private var tvBanyakUlasan: TextView? = null
    private var tbFavorite: ToggleButton? = null

    init {
        ivProfilPic = itemView.findViewById(R.id.iv_profil_pic_with_favorite)
        tvItemType = itemView.findViewById(R.id.tv_item_type)
        tvItemName = itemView.findViewById(R.id.tv_item_name)
        tvBidangKerja = itemView.findViewById(R.id.tv_jabatan_organisasi)
        tvBanyakUlasan = itemView.findViewById(R.id.tv_prestasi_lomba)
        tbFavorite = itemView.findViewById(R.id.tb_favorite)
    }


    fun bind(
        response: FrontProfileResponse,
        presenter: FrontProfilePresenter<FrontProfileMVPView>
    ) {
        itemView.setOnClickListener {
            val intent = Intent(itemView.context, ProfileTempatPklActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.putExtra("tempatPklId", response.tempatPkl?.id)
            itemView.context.startActivity(intent)
        }

        try {
            Glide.with(this.itemView.context)
                .load(response.tempatPkl?.gambar ?: url)
                .into(ivProfilPic)
        } catch (e: Throwable) {
            Glide.with(this.itemView.context).load(url)
                .into(ivProfilPic)
        }

        tvItemType?.text = "Tempat PKL"
        tvItemName?.text = response.tempatPkl?.namaPerusahaan
        tbFavorite?.isChecked =
            Utils.intToBoolean(response.tempatPkl?.relationTempatPkl?.isFavorite)

        response.tempatPkl?.relationBidangKerja?.size

        var bidangKerja = ""
        for (data in response.tempatPkl?.relationBidangKerja!!) {
            bidangKerja = bidangKerja + "${data.bidangKerja?.namaBidangKerja}"
            if (data != response.tempatPkl?.relationBidangKerja?.get(response.tempatPkl?.relationBidangKerja?.size!! - 1)) {
                bidangKerja = bidangKerja + ", "
            }
        }

        if (bidangKerja.isNotEmpty()) {
            tvBidangKerja?.text = bidangKerja
        } else {
            tvBidangKerja?.text = "Tidak memiliki bidang kerja"
        }

        tvBanyakUlasan?.text =
            "${response.tempatPkl?.countUlasanTempatPkl?.size} orang pernah Organisasi di sini"

        val scaleAnimation = ScaleAnimation(
            0.7f,
            1.0f,
            0.7f,
            1.0f,
            Animation.RELATIVE_TO_SELF,
            0.7f,
            Animation.RELATIVE_TO_SELF,
            0.7f
        )
        scaleAnimation?.setDuration(500)
        val bounceInterpolator = BounceInterpolator()
        scaleAnimation?.setInterpolator(bounceInterpolator)

        tbFavorite?.setOnClickListener {
            tbFavorite?.startAnimation(scaleAnimation)

            response.tempatPkl?.id?.let {
//                presenter.toggleFavoriteTempatPkl(it, tbFavorite?.isChecked!!)
                response.tempatPkl?.relationTempatPkl?.isFavorite =
                    Utils.toggleBoolean(response.tempatPkl?.relationTempatPkl?.isFavorite!!)
            }
        }
    }
}