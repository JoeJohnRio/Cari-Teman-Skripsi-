package com.example.cariteman.ui.dashboard.barudilihat.view

import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.BounceInterpolator
import android.view.animation.ScaleAnimation
import android.widget.CompoundButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.ToggleButton
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cariteman.R
import com.example.cariteman.data.model.MahasiswaHistoryDashboardResponse
import kotlinx.android.extensions.LayoutContainer
import java.lang.Exception

class BaruDilihatLombaViewHolder(override val containerView: View) :
    RecyclerView.ViewHolder(containerView),
    LayoutContainer {

    private var url: String = "https://tanzolymp.com/images/default-non-user-no-photo-1-300x300.jpg"

    private var ivProfilPicWithFavorite: ImageView? = null
    private var tvItemType: TextView? = null
    private var tvItemName: TextView? = null
    private var tvJabatanOrganisasi: TextView? = null
    private var tvPrestasiLomba: TextView? = null
    private var tbFavorite: ToggleButton? = null

    init {
        ivProfilPicWithFavorite = itemView.findViewById(R.id.iv_profil_pic_with_favorite)
        tvItemType = itemView.findViewById(R.id.tv_item_type)
        tvItemName = itemView.findViewById(R.id.tv_item_name)
        tvJabatanOrganisasi = itemView.findViewById(R.id.tv_jabatan_organisasi)
        tvPrestasiLomba = itemView.findViewById(R.id.tv_prestasi_lomba)
        tbFavorite = itemView.findViewById(R.id.tb_favorite)
    }


    fun bind(response: MahasiswaHistoryDashboardResponse) {

        try {
            Glide.with(this.itemView.context)
                .load(response.mahasiswaTwoLomba?.foto_profil ?: url)
                .into(ivProfilPicWithFavorite)
        } catch (e: Throwable) {
            Glide.with(this.itemView.context).load(url)
                .into(ivProfilPicWithFavorite)
        }



        tvItemType?.text = "Lomba"
        tvItemName?.text = response.mahasiswaTwoLomba?.name
        try {
            if (response.mahasiswaTwoLomba?.pengalamanOrganisasi?.get(0)?.bidangKerja?.namaBidangKerja != null && response.mahasiswaTwoLomba?.pengalamanOrganisasi?.get(
                    0
                )?.namaOrganisasi != null
            ) {
                tvJabatanOrganisasi?.text =
                    "" + response.mahasiswaTwoLomba?.pengalamanOrganisasi?.get(0)?.bidangKerja?.namaBidangKerja + " at " + response.mahasiswaTwoLomba?.pengalamanOrganisasi?.get(
                        0
                    )?.namaOrganisasi
            } else {
                tvJabatanOrganisasi?.text = "Belum memiliki pengalaman berorganisasi"
            }
        } catch (e: Exception) {
            tvJabatanOrganisasi?.text = "Belum memiliki pengalaman berorganisasi"
        }
        try {
            tvPrestasiLomba?.text =
                "" + response.mahasiswaTwoLomba?.pengalamanLomba?.get(0)?.namaKompetisi + " at " + response.mahasiswaTwoLomba?.pengalamanOrganisasi?.get(
                    0
                )?.namaOrganisasi
        } catch (e: Exception) {
            tvPrestasiLomba?.text = "Belum memiliki pengalaman lomba"
        }

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

        tbFavorite?.setOnCheckedChangeListener(object :
            View.OnClickListener, CompoundButton.OnCheckedChangeListener {
            override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {
                p0?.startAnimation(scaleAnimation);
                Log.d(
                    "fav",
                    "am i here"
                ) //To change body of created functions use File | Settings | File Templates.
            }

            override fun onClick(p0: View?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
    }
}