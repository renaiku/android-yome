package com.karasu.yome.model

import com.karasu.yome.enum.MangaFormat

data class MangaFile (
    val id: Int,
    val filePath: String,
    val pages: Int,
    val format: MangaFormat,
    val created: String,
)
