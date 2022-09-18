package com.karasu.yome.model

data class Volume (
    var id: Int,
    var number: Int,
    var name: String,
    var created: String,
    var lastModified: String,
    var pages: Int,
    var pagesRead: Int,
    var chapters: Array<Chapter>,

    /**
    * This is only available on the object when fetched for SeriesDetail
    */
    var timeEstimate: HourEstimateRange?,
    var minHoursToRead: Int,
    var maxHoursToRead: Int,
    var avgHoursToRead: Int,
)