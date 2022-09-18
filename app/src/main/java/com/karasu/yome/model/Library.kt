package com.karasu.yome.model

import com.karasu.yome.enum.LibraryType

data class Library(
    var id: Int,
    var name: String,
    var lastScanned: String,
    var type: LibraryType,
    var folders: Array<String>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Library

        if (id != other.id) return false
        if (name != other.name) return false
        if (lastScanned != other.lastScanned) return false
        if (type != other.type) return false
        if (!folders.contentEquals(other.folders)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + name.hashCode()
        result = 31 * result + lastScanned.hashCode()
        result = 31 * result + type.hashCode()
        result = 31 * result + folders.contentHashCode()
        return result
    }
}