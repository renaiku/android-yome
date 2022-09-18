package com.karasu.yome.enum

/**
 * How the image should scale to the screen size
 */
enum class ScalingOption {
    /**
     * Fit the image into the height of screen
     */
    FitToHeight,

    /**
     * Fit the image into the width of screen
     */
    FitToWidth,

    /**
     * Apply no logic and render the image as is
     */
    Original,

    /**
     * Ask the reader to attempt to choose the best ScalingOption for the user
     */
    Automatic
}