package com.karasu.yome.network.dto

data class FileDto(
    val created: String,
    val filePath: String,
    val format: Int,
    val id: Int,
    val pages: Int
)