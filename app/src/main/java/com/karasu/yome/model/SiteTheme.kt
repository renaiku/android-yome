package com.karasu.yome.model

import com.karasu.yome.enum.ThemeProvider
import kotlinx.serialization.Serializable

/**
 * Theme for the whole instance
 */
@Serializable
data class SiteTheme (
    val id: Int,
    val name: String,
    val filePath: String,
    val isDefault: Boolean,
    val provider: ThemeProvider,

    /**
    * The actual class the root is defined against. It is generated at the backend.
    */
    val selector: String
)