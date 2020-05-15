package com.example.cariteman.data.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class KeminatanResponse(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("id_program_studi") var idProgramStudi: Int? = null,
    @SerializedName("created_at") var createdAt: Date? = null,
    @SerializedName("updated_at") var updatedAt: Date? = null)