package com.example.cariteman.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class DaftarTemanHanyaNama(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("foto_profil") var fotoProfil: String? = null,
    @SerializedName("name") var name: String? = null
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString()) {
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