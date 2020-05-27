package com.example.cariteman.data.model

import com.google.gson.annotations.SerializedName

data class Login(
    @SerializedName("email") var email: String? = null,
    @SerializedName("password") var password: String? = null,
    @SerializedName("passwordConfirmation") var passwordConfirmation: String? = null
)