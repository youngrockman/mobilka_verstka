package com.example.shoesapptest.data.remote.network.auth

import kotlinx.serialization.Serializable

@Serializable
data class RegistrationRequest(
    val userName: String,
    val email: String,
    val password: String
)