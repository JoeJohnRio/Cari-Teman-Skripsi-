package com.example.cariteman.data.model

import com.google.gson.annotations.SerializedName

data class SkillHobi(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("nama_skillhobi") var namaSkillhobi: String? = null,
    @SerializedName("created_at") var createdAt: String? = null,
    @SerializedName("updated_at") var updatedAt: String? = null
)