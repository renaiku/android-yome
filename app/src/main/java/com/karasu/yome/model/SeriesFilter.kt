package com.karasu.yome.model

import com.karasu.yome.enum.MangaFormat

data class FilterItem<T> (
    var title: String,
    var value: T,
    var selected: Boolean
)

data class SeriesFilter (
    var formats: Array<MangaFormat>,
    var libraries: Array<Int>,
    var readStatus: ReadStatus,
    var genres: Array<Int>,
    var writers: Array<Int>,
    var artists: Array<Int>,
    var penciller: Array<Int>,
    var inker: Array<Int>,
    var colorist: Array<Int>,
    var letterer: Array<Int>,
    var coverArtist: Array<Int>,
    var editor: Array<Int>,
    var publisher: Array<Int>,
    var character: Array<Int>,
    var translators: Array<Int>,
    var collectionTags: Array<Int>,
    var rating: Int,
    var ageRating: Array<Int>,
    var sortOptions: SortOptions?,
    var tags: Array<Int>,
    var languages: Array<String>,
    var publicationStatus: Array<Int>,
    var seriesNameQuery: String
)

data class SortOptions (
    var sortField: SortField,
    var isAscending: Boolean
)

enum class SortField {
    SortName,
    Created,
    LastModified,
    LastChapterAdded,
    TimeToRead
}

data class ReadStatus (
    var notRead: Boolean,
    var inProgress: Boolean,
    var read: Boolean
)

//export const mangaFormatFilters = [
//(
//    var title: 'Images',
//    var value: MangaFormat.IMAGE,
//    var selected: false
//),
//(
//    var title: 'EPUB',
//    var value: MangaFormat.EPUB,
//    var selected: false
//),
//(
//    var title: 'PDF',
//    var value: MangaFormat.PDF,
//    var selected: false
//),
//(
//    var title: 'ARCHIVE',
//    var value: MangaFormat.ARCHIVE,
//    var selected: false
//)
//],

data class FilterEvent (
    var filter: SeriesFilter,
    var isFirst: Boolean
)