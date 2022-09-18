package com.karasu.yome.network.dto

data class VolumeDto(
    val avgHoursToRead: Int,
    val chapters: List<ChapterDto>,
    val created: String,
    val id: Int,
    val lastModified: String,
    val maxHoursToRead: Int,
    val minHoursToRead: Int,
    val name: String,
    val number: Int,
    val pages: Int,
    val pagesRead: Int,
    val seriesId: Int
) {
    override fun toString(): String = "Name: $name - ID: $id"
}