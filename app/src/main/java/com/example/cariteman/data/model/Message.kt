package com.example.cariteman.data.model

import com.google.gson.annotations.SerializedName

data class Message(
    @SerializedName("message") var message: String? = null
    )

