package com.example.cariteman.ui.dashboard.barudilihat.view

import android.content.Context
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
import com.example.cariteman.data.model.MahasiswaHistoryDashboardResponse
import com.example.cariteman.data.model.RelationTempatPklFavorite
import com.example.cariteman.ui.dashboard.barudilihat.presenter.BaruDilihatPresenter
import com.example.cariteman.ui.dashboard.presenter.DashboardPresenter
import com.example.cariteman.ui.dashboard.view.DashboardMVPView
import com.example.cariteman.ui.profile.view.ProfileActivity
import com.example.cariteman.util.Utils
import kotlinx.android.extensions.LayoutContainer
import java.lang.Exception

class BaruDilihatPklViewHolder(override val containerView: View) :
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

    fun bindHistory(context: Context, response: MahasiswaHistoryDashboardResponse, presenter: BaruDilihatPresenter<BaruDilihatMVPView>) {

        itemView.setOnClickListener {
            val intent = Intent(context, ProfileActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.putExtra("MAHASISWA_ID", response.idMahasiswaTwo)
            context.startActivity(intent)
        }

        try {
            Glide.with(this.itemView.context)
                .load(response.mahasiswaTwoPkl?.foto_profil ?: url)
                .into(ivProfilPicWithFavorite)
        } catch (e: Throwable) {
            Glide.with(this.itemView.context).load(url)
                .into(ivProfilPicWithFavorite)
        }

        tvItemType?.text = "PKL"
        tbFavorite?.isChecked = Utils.intToBoolean(response.mahasiswaTwoPkl?.relationTeman?.isFavorite)

        tvItemName?.text = response.mahasiswaTwoPkl?.name
        try {
            if (response.mahasiswaTwoPkl?.pengalamanOrganisasi?.get(0)?.relationBidangKerja?.bidangKerja?.namaBidangKerja != null && response.mahasiswaTwoPkl?.pengalamanOrganisasi?.get(
                    0
                )?.namaOrganisasi != null
            ){
                tvJabatanOrganisasi?.text =
                    "" + response.mahasiswaTwoPkl?.pengalamanOrganisasi?.get(0)?.relationBidangKerja?.bidangKerja?.namaBidangKerja + " at " + response.mahasiswaTwoPkl?.pengalamanOrganisasi?.get(
                        0
                    )?.namaOrganisasi
            }
            else{
                tvJabatanOrganisasi?.text = "Belum memiliki pengalaman berorganisasi"
            }
        } catch (e: Exception) {
            tvJabatanOrganisasi?.text = "Belum memiliki pengalaman berorganisasi"
        }
        try {
            tvPrestasiLomba?.text =
                "" + response.mahasiswaTwoPkl?.pengalamanLomba?.get(0)?.namaKompetisi + " at " + response.mahasiswaTwoPkl?.pengalamanOrganisasi?.get(
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

        tbFavorite?.setOnClickListener{
            tbFavorite?.startAnimation(scaleAnimation)

            response.mahasiswaTwoPkl?.id?.let {
                presenter.toggleFavoriteFriend(it, tbFavorite?.isChecked!!)
                response.mahasiswaTwoPkl?.relationTeman?.isFavorite = Utils.toggleBoolean(response.mahasiswaTwoPkl?.relationTeman?.isFavorite!!)
            }
        }

    }

    fun bindFavorite(response: RelationTempatPklFavorite, presenter: DashboardPresenter<DashboardMVPView>) {

        try {
            Glide.with(this.itemView.context)
                .load(response.mahasiswaTwoPkl?.foto_profil ?: url)
                .into(ivProfilPicWithFavorite)
        } catch (e: Throwable) {
            Glide.with(this.itemView.context).load(url)
                .into(ivProfilPicWithFavorite)
        }

        tvItemType?.text = "PKL"
        tbFavorite?.isChecked = Utils.intToBoolean(response.isFavorite)

        tvItemName?.text = response.mahasiswaTwoPkl?.name
        try {
            if (response.mahasiswaTwoPkl?.pengalamanOrganisasi?.get(0)?.relationBidangKerja?.bidangKerja?.namaBidangKerja != null && response.mahasiswaTwoPkl?.pengalamanOrganisasi?.get(
                    0
                )?.namaOrganisasi != null
            ){
                tvJabatanOrganisasi?.text =
                    "" + response.mahasiswaTwoPkl?.pengalamanOrganisasi?.get(0)?.relationBidangKerja?.bidangKerja?.namaBidangKerja + " at " + response.mahasiswaTwoPkl?.pengalamanOrganisasi?.get(
                        0
                    )?.namaOrganisasi
            }
            else{
                tvJabatanOrganisasi?.text = "Belum memiliki pengalaman berorganisasi"
            }
        } catch (e: Exception) {
            tvJabatanOrganisasi?.text = "Belum memiliki pengalaman berorganisasi"
        }
        try {
            tvPrestasiLomba?.text =
                "" + response.mahasiswaTwoPkl?.pengalamanLomba?.get(0)?.namaKompetisi + " at " + response.mahasiswaTwoPkl?.pengalamanOrganisasi?.get(
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

        tbFavorite?.setOnClickListener{
            tbFavorite?.startAnimation(scaleAnimation)

            response.mahasiswaTwoPkl?.id?.let {
                presenter.toggleFavoriteFriend(it, tbFavorite?.isChecked!!)
                response.isFavorite = Utils.toggleBoolean(response.isFavorite!!)}
        }

    }
}