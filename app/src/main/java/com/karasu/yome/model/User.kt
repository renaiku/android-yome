package com.karasu.yome.model

import kotlinx.serialization.Serializable


@Serializable
data class User (
    val username: String,
    val apiKey: String,
    val token: String,

    val email: String?,
    val refreshToken: String?,
    val preferences: Preferences?
) {
    override fun toString(): String = username
}