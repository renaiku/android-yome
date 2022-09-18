package com.karasu.yome.network.dto

data class SeriesDto(
    val avgHoursToRead: Int,
    val coverImageLocked: Boolean,
    val created: String,
    val format: Int,
    val id: Int,
    val lastChapterAdded: String,
    val latestReadDate: String,
    val libraryId: Int,
    val libraryName: String,
    val localizedName: String,
    val localizedNameLocked: Boolean,
    val maxHoursToRead: Int,
    val minHoursToRead: Int,
    val name: String,
    val nameLocked: Boolean,
    val originalName: String,
    val pages: Int,
    val pagesRead: Int,
    val sortName: String,
    val sortNameLocked: Boolean,
    val summary: Any,
    val userRating: Int,
    val userReview: Any,
    val wordCount: Int
)