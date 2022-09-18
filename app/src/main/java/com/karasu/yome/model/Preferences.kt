package com.karasu.yome.model

import com.karasu.yome.enum.*
import kotlinx.serialization.Serializable

@Serializable
data class Preferences (
    // Manga Reader
    val readingDirection: ReadingDirection,
    val scalingOption: ScalingOption,
    val pageSplitOption: PageSplitOption,
    val readerMode: ReaderMode,
    val autoCloseMenu: Boolean,
    val layoutMode: LayoutMode,
    val backgroundColor: String,
    val showScreenHints: Boolean,

    // Book Reader
    val bookReaderMargin: Int,
    val bookReaderLineSpacing: Int,
    val bookReaderFontSize: Int,
    val bookReaderFontFamily: String,
    val bookReaderTapToPaginate: Boolean,
    val bookReaderReadingDirection: ReadingDirection,
    val bookReaderThemeName: String,
    val bookReaderLayoutMode: BookPageLayoutMode,
    val bookReaderImmersiveMode: Boolean,

    // Global
    val theme: SiteTheme,
    val globalPageLayoutMode: PageLayoutMode,
    val blurUnreadSummaries: Boolean,
    val promptForDownloadSize: Boolean
)


//val readingDirections = [
//    {text: 'Left to Right', value: ReadingDirection.LeftToRight},
//    {text: 'Right to Left', value: ReadingDirection.RightToLeft}
//];
//
//val scalingOptions = [
//    {text: 'Automatic', value: ScalingOption.Automatic},
//    {text: 'Fit to Height', value: ScalingOption.FitToHeight},
//    {text: 'Fit to Width', value: ScalingOption.FitToWidth},
//    {text: 'Original', value: ScalingOption.Original}
//];
//
//val pageSplitOptions = [
//    {text: 'Fit to Screen', value: PageSplitOption.FitSplit},
//    {text: 'Right to Left', value: PageSplitOption.SplitRightToLeft},
//    {text: 'Left to Right', value: PageSplitOption.SplitLeftToRight},
//    {text: 'No Split', value: PageSplitOption.NoSplit}
//];
//
//val readingModes = [
//    {text: 'Left to Right', value: ReaderMode.LeftRight},
//    {text: 'Up to Down', value: ReaderMode.UpDown},
//    {text: 'Webtoon', value: ReaderMode.Webtoon}
//];
//
//val layoutModes = [
//    {text: 'Single', value: LayoutMode.Single},
//    {text: 'Double', value: LayoutMode.Double},
//    {text: 'Double (Manga)', value: LayoutMode.DoubleReversed}
//];
//
//val bookLayoutModes = [
//    {text: 'Default', value: BookPageLayoutMode.Default},
//    {text: '1 Column', value: BookPageLayoutMode.Column1},
//    {text: '2 Column', value: BookPageLayoutMode.Column2}
//];
//
//val pageLayoutModes = [
//    {text: 'Cards', value: PageLayoutMode.Cards},
//    {text: 'List', value: PageLayoutMode.List}
//];