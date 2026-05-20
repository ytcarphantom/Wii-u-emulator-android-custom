package com.custom.wiiuemu.config

/**
 * Placeholder import service for migration from other Android emulators.
 *
 * IMPORTANT: actual compatibility depends on each emulator's config format and licensing terms.
 */
class SettingsImportService {
    fun importFromCemuAndroid(rawConfig: Map<String, String>): EmulatorSettings {
        return EmulatorSettings(
            graphicsBackend = rawConfig["graphics_api"] ?: "Vulkan",
            internalResolutionScale = rawConfig["resolution_scale"]?.toFloatOrNull() ?: 1.0f,
            asyncShaderCompile = rawConfig["async_shaders"]?.toBooleanStrictOrNull() ?: true,
            fpsLimit = rawConfig["fps_limit"]?.toIntOrNull() ?: 60,
            audioLatencyMs = rawConfig["audio_latency_ms"]?.toIntOrNull() ?: 60,
            controllerProfile = rawConfig["controller_profile"] ?: "WiiU GamePad"
        )
    }

    fun importFromEden(rawConfig: Map<String, String>): EmulatorSettings {
        return EmulatorSettings(
            graphicsBackend = rawConfig["renderer"] ?: "Vulkan",
            internalResolutionScale = rawConfig["resolution"]?.toFloatOrNull() ?: 1.0f,
            asyncShaderCompile = rawConfig["shader_async"]?.toBooleanStrictOrNull() ?: true,
            fpsLimit = rawConfig["framerate_cap"]?.toIntOrNull() ?: 60,
            audioLatencyMs = rawConfig["audio_buffer_ms"]?.toIntOrNull() ?: 60,
            controllerProfile = rawConfig["input_profile"] ?: "WiiU GamePad"
        )
    }
}

