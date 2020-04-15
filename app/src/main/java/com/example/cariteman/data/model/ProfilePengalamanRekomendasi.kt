package com.example.cariteman.data.model

import com.google.gson.annotations.SerializedName

data class ProfilePengalamanRekomendasi(
    @SerializedName("rekomendasi") var rekomendasi: MutableList<Rekomendasi>? = null,
    @SerializedName("pengalaman") var pengalaman: PengalamanLombaOrganisasi? = null
    )

data class PengalamanLombaOrganisasi(
    @SerializedName("pengalaman_lomba") var pengalamanLomba: MutableList<PengalamanLomba>? = null,
    @SerializedName("pengalaman_organisasi") var pengalamanOrganisasi: MutableList<PengalamanOrganisasi>? = null
)


