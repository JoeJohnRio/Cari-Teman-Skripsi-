package com.example.cariteman.data.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class ProgramStudiResponse(
    @SerializedName("isVerified") var id: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("id_fakultas") var idFakultas: Int? = null,
    @SerializedName("created_at") var createdAt: Date? = null,
    @SerializedName("updated_at") var updatedAt: Date? = null)