package com.example.cariteman.data.model

import com.google.gson.annotations.SerializedName

data class MessageOnly(
    @SerializedName("message") var message: String? = null
    )

