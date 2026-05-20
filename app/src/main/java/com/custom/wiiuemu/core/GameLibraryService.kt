package com.custom.wiiuemu.core

import java.io.File

class GameLibraryService {
    private val supportedExtensions = setOf("wud", "wux", "rpx", "iso")

    fun scanGames(directory: File): List<File> {
        if (!directory.exists() || !directory.isDirectory) return emptyList()
        return directory.walkTopDown()
            .filter { it.isFile }
            .filter { file ->
                val ext = file.extension.lowercase()
                supportedExtensions.contains(ext)
            }
            .toList()
    }
}
