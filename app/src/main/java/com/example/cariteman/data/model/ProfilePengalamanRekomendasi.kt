package com.example.cariteman.data.model

import com.google.gson.annotations.SerializedName

data class ProfilePengalamanRekomendasi(
    @SerializedName("rekomendasi") var rekomendasi: Rekomendasi? = null,
    @SerializedName("pengalaman") var pengalaman: PengalamanLombaOrganisasi? = null
    )

data class PengalamanLombaOrganisasi(
    @SerializedName("pengalaman_lomba") var pengalamanLomba: MutableList<PengalamanLombaOrganisasiResponse?> = null,
    @SerializedName("pengalaman_organisasi") var pengalamanOrganisasi: MutableList<PengalamanLombaOrganisasiResponse?> = null
)


