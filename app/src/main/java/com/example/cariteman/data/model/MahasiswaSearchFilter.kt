package com.example.cariteman.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class MahasiswaSearchFilter(
    @SerializedName("keyword") var keyword: String? = null,
    @SerializedName("preferensi") var preferensi: Int? = null,
    @SerializedName("jenis_kelamin") var jenisKelamin: Int? = null,
    @SerializedName("id_fakultas") var idFakultas: Int? = null,
    @SerializedName("id_program_studi") var idProgramStudi: Int? = null,
    @SerializedName("id_keminatan") var idKeminatan: Int? = null,
    @SerializedName("tahun_mulai") var tahunMulai: Int? = null,
    @SerializedName("id_skillhobi") var idSkillHobi: Int? = null
    ): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt()) {
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun describeContents(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object CREATOR : Parcelable.Creator<DaftarTemanHanyaNama> {
        override fun createFromParcel(parcel: Parcel): DaftarTemanHanyaNama {
            return DaftarTemanHanyaNama(parcel)
        }

        override fun newArray(size: Int): Array<DaftarTemanHanyaNama?> {
            return arrayOfNulls(size)
        }
    }
}

