package com.custom.wiiuemu.core

import com.custom.wiiuemu.config.EmulatorSettings
import java.io.File

/**
 * Skeleton for a future Wii U emulation core binding.
 *
 * This class does not emulate yet; it defines the control surface required
 * for plugging in a native backend later.
 */
class EmulationEngine {
    var state: EngineState = EngineState.Idle
        private set

    fun bootGame(gameFile: File, settings: EmulatorSettings): Boolean {
        if (!gameFile.exists()) return false
        // TODO: validate game metadata, mount virtual file system, initialize JIT/CPU/GPU/HLE.
        state = EngineState.Running
        return true
    }

    fun pause() {
        if (state == EngineState.Running) state = EngineState.Paused
    }

    fun resume() {
        if (state == EngineState.Paused) state = EngineState.Running
    }

    fun stop() {
        state = EngineState.Idle
    }
}

enum class EngineState {
    Idle,
    Running,
    Paused
}
