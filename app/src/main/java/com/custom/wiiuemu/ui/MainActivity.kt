package com.custom.wiiuemu.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.documentfile.provider.DocumentFile
import com.custom.wiiuemu.R
import com.custom.wiiuemu.config.SettingsImportService
import com.custom.wiiuemu.core.EmulationEngine

class MainActivity : AppCompatActivity() {
    private lateinit var statusText: TextView
    private lateinit var settingsText: TextView
    private lateinit var gameListText: TextView

    private val importService = SettingsImportService()
    private val engine = EmulationEngine()

    private val pickFolderLauncher = registerForActivityResult(ActivityResultContracts.OpenDocumentTree()) { uri ->
        if (uri != null) {
            contentResolver.takePersistableUriPermission(
                uri,
                Intent.FLAG_GRANT_READ_URI_PERMISSION
            )
            scanGamesInTree(uri)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        statusText = findViewById(R.id.statusText)
        settingsText = findViewById(R.id.settingsText)
        gameListText = findViewById(R.id.gameListText)

        findViewById<Button>(R.id.pickFolderButton).setOnClickListener {
            pickFolderLauncher.launch(null)
        }

        findViewById<Button>(R.id.importCemuButton).setOnClickListener {
            val sample = mapOf(
                "graphics_api" to "Vulkan",
                "resolution_scale" to "1.5",
                "async_shaders" to "true",
                "fps_limit" to "60",
                "audio_latency_ms" to "55",
                "controller_profile" to "WiiU Pro Controller"
            )
            val imported = importService.importFromCemuAndroid(sample)
            settingsText.text = imported.toString()
        }

        findViewById<Button>(R.id.importEdenButton).setOnClickListener {
            val sample = mapOf(
                "renderer" to "Vulkan",
                "resolution" to "2.0",
                "shader_async" to "true",
                "framerate_cap" to "45",
                "audio_buffer_ms" to "65",
                "input_profile" to "WiiU GamePad"
            )
            val imported = importService.importFromEden(sample)
            settingsText.text = imported.toString()
        }

        updateState()
    }

    private fun updateState() {
        statusText.text = "Engine state: ${engine.state}"
    }

    private fun scanGamesInTree(treeUri: Uri) {
        val root = DocumentFile.fromTreeUri(this, treeUri)
        if (root == null || !root.isDirectory) {
            gameListText.text = "Could not read selected folder."
            return
        }

        val supported = setOf("wud", "wux", "rpx", "iso")
        val matches = mutableListOf<String>()
        walkDocuments(root) { file ->
            if (file.isFile) {
                val ext = file.name?.substringAfterLast('.', "")?.lowercase().orEmpty()
                if (ext in supported) {
                    matches += file.name ?: "(unknown)"
                }
            }
        }

        gameListText.text = if (matches.isEmpty()) {
            "No Wii U game files found in selected folder."
        } else {
            "Found ${matches.size} file(s):\n- ${matches.joinToString("\n- ")}"
        }
    }

    private fun walkDocuments(current: DocumentFile, onFile: (DocumentFile) -> Unit) {
        onFile(current)
        if (current.isDirectory) {
            current.listFiles().forEach { child ->
                walkDocuments(child, onFile)
            }
        }
    }
}

