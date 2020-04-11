package com.example.cariteman.data.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class FakultasResponse(
    @SerializedName("isVerified") var id: Int,
    @SerializedName("name") var name: String,
    @SerializedName("created_at") var createdAt: Date,
    @SerializedName("updated_at") var updatedAt: Date)