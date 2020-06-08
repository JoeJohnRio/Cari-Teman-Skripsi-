package com.example.cariteman.data.model

import com.google.gson.annotations.SerializedName

data class Token(
    @SerializedName("token") var token: String? = null,
    @SerializedName("error") var error: String? = null
)