package com.karasu.yome.enum

/**
 * How to layout pages for reading
 */
enum class LayoutMode {
    Unknown,

    /**
     * Renders a single page on the renderer. Cover images will follow splitting logic.
     */
    Single,

    /**
     * Renders 2 pages side by side on the renderer. Cover images will not split and take up both panes.
     */
    Double,

    /**
     * Renders 2 pages side by side on the renderer. Cover images will not split and take up both panes. This version reverses the order and is used for Manga only
     */
    DoubleReversed
}