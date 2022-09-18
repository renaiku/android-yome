package com.karasu.yome.enum

/**
 * The pagination method used by the reader
 */
enum class ReaderMode {
    /**
     * Manga default left/right to page
     */
    LeftRight,
    /**
     * Manga up and down to page
     */
    UpDown,
    /**
     * Webtoon reading (scroll) with optional areas to tap
     */
    Webtoon
}