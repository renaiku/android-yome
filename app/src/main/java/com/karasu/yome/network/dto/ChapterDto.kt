package com.karasu.yome.network.dto

data class ChapterDto(
    val ageRating: Int,
    val avgHoursToRead: Int,
    val coverImageLocked: Boolean,
    val created: String,
    val files: List<FileDto>,
    val id: Int,
    val isSpecial: Boolean,
    val maxHoursToRead: Int,
    val minHoursToRead: Int,
    val number: String,
    val pages: Int,
    val pagesRead: Int,
    val range: String,
    val releaseDate: String,
    val summary: String,
    val title: String,
    val titleName: String,
    val volumeId: Int,
    val volumeTitle: String,
    val wordCount: Int
)