package com.karasu.yome.enum

enum class PageSplitOption {
    /**
     * Renders the left side of the image then the right side
     */
    SplitLeftToRight,
    /**
     * Renders the right side of the image then the left side
     */
    SplitRightToLeft,
    /**
     * Don't split and show the image in original size
     */
    NoSplit,
    /**
     * Don't split and scale the image to fit screen space
     */
    FitSplit
}