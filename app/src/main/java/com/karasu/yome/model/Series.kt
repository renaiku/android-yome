package com.karasu.yome.model

import com.karasu.yome.enum.MangaFormat

data class Series(

    var id: Int,
    var name: String,


    /**
     * This is not shown to user
     */
    var originalName: String?,
    var localizedName: String?,
    var sortName: String?,
    var coverImageLocked: Boolean?,
    var sortNameLocked: Boolean?,
    var localizedNameLocked: Boolean?,
    var nameLocked: Boolean?,
    var volumes: Array<Volume>?,
    var libraryId: Int?,

    /**
     * Total pages in series
     */
    var pages: Int?,

    /**
     * Total pages the logged in user has read
     */
    var pagesRead: Int?,

    /**
     * User's rating (0-5)
     */
    var userRating: Int?,

    /**
     * The user's review
     */
    var userReview: String?,

    /**
     * DateTime the entity was created
     */
    var created: String?,

    /**
     * Format of the Series
     */
    var format: MangaFormat?,

    /**
     * DateTime that represents last time the logged in user read this series
     */
    var latestReadDate: String?,

    /**
     * DateTime representing last time a chapter was added to the Series
     */
    var lastChapterAdded: String?,

    /**
     * DateTime representing last time the series folder was scanned
     */
    var lastFolderScanned: String?,

    /**
     * _root_ide_package_.kotlin.Int of words in the series
     */
    var wordCount: Int?,
    var minHoursToRead: Int?,
    var maxHoursToRead: Int?,
    var avgHoursToRead: Int?,

    /**
     * Highest level folder containing this series
     */
    var folderPath: String?
) {
    constructor(id: Int, name: String) : this(
        id = id,
        name = name,
        originalName = "",
        localizedName = "",
        sortName = "",
        coverImageLocked = false,
        sortNameLocked = false,
        localizedNameLocked = false,
        nameLocked = false,
        volumes = arrayOf(),
        libraryId = -1,
        pages = -1,
        pagesRead = -1,
        userRating = -1,
        userReview = "",
        created = "",
        format = MangaFormat.UNKNOWN,
        latestReadDate = "",
        lastChapterAdded = "",
        lastFolderScanned = "",
        wordCount = -1,
        minHoursToRead = -1,
        maxHoursToRead = -1,
        avgHoursToRead = -1,
        folderPath = ""
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Series

        if (id != other.id) return false
        if (name != other.name) return false
        if (originalName != other.originalName) return false
        if (localizedName != other.localizedName) return false
        if (sortName != other.sortName) return false
        if (coverImageLocked != other.coverImageLocked) return false
        if (sortNameLocked != other.sortNameLocked) return false
        if (localizedNameLocked != other.localizedNameLocked) return false
        if (nameLocked != other.nameLocked) return false
        if (!volumes.contentEquals(other.volumes)) return false
        if (pages != other.pages) return false
        if (pagesRead != other.pagesRead) return false
        if (userRating != other.userRating) return false
        if (userReview != other.userReview) return false
        if (libraryId != other.libraryId) return false
        if (created != other.created) return false
        if (format != other.format) return false
        if (latestReadDate != other.latestReadDate) return false
        if (lastChapterAdded != other.lastChapterAdded) return false
        if (lastFolderScanned != other.lastFolderScanned) return false
        if (wordCount != other.wordCount) return false
        if (minHoursToRead != other.minHoursToRead) return false
        if (maxHoursToRead != other.maxHoursToRead) return false
        if (avgHoursToRead != other.avgHoursToRead) return false
        if (folderPath != other.folderPath) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + name.hashCode()
        result = 31 * result + originalName.hashCode()
        result = 31 * result + localizedName.hashCode()
        result = 31 * result + sortName.hashCode()
        result = 31 * result + coverImageLocked.hashCode()
        result = 31 * result + sortNameLocked.hashCode()
        result = 31 * result + localizedNameLocked.hashCode()
        result = 31 * result + nameLocked.hashCode()
        result = 31 * result + volumes.contentHashCode()
        result = 31 * result + pages!!
        result = 31 * result + pagesRead!!
        result = 31 * result + userRating!!
        result = 31 * result + userReview.hashCode()
        result = 31 * result + libraryId!!
        result = 31 * result + created.hashCode()
        result = 31 * result + format.hashCode()
        result = 31 * result + latestReadDate.hashCode()
        result = 31 * result + lastChapterAdded.hashCode()
        result = 31 * result + lastFolderScanned.hashCode()
        result = 31 * result + wordCount!!
        result = 31 * result + minHoursToRead!!
        result = 31 * result + maxHoursToRead!!
        result = 31 * result + avgHoursToRead!!
        result = 31 * result + folderPath.hashCode()
        return result
    }
}
