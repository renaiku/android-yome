package com.karasu.yome.model

import com.karasu.yome.enum.AgeRating

data class Chapter (
    var id: Int,
    var range: String,
    var number: String,
    var files: Array<MangaFile>,

    /**
     * This is used in the UI, it is not updated or sent to Backend
     */
    var coverImage: String,
    var coverImageLocked: Boolean,
    var pages: Int,
    var volumeId: Int,
    var pagesRead: Int, // Attached for the given user when requesting from AP,
    var isSpecial: Boolean,
    var title: String,
    var created: String,

    /**
     * Actual name of the Chapter if populated in underlying metadata
     */
    var titleName: String,

    /**
     * Summary for the chapter
     */
    var summary: String?,
    var minHoursToRead: Int,
    var maxHoursToRead: Int,
    var avgHoursToRead: Int,

    var ageRating: AgeRating,
    var releaseDate: String,
    var wordCount: Int,

    /**
     * 'Volume Int'. Only available for SeriesDetail
     */
    var volumeTitle: String?,
)