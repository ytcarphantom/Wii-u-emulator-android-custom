package com.custom.wiiuemu.config

data class EmulatorSettings(
    val graphicsBackend: String = "Vulkan",
    val internalResolutionScale: Float = 1.0f,
    val asyncShaderCompile: Boolean = true,
    val fpsLimit: Int = 60,
    val audioLatencyMs: Int = 60,
    val controllerProfile: String = "WiiU GamePad"
)
