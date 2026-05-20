# Wii-u-emulator-android-custom

This repository now contains a **starter Android emulator shell** for a custom Wii U emulator app.

## What is implemented
- Android Gradle project scaffold.
- Emulator settings model with defaults for graphics/performance/input.
- Import/migration adapter skeletons for:
  - Cemu Android-style setting keys.
  - Eden-style setting keys.
- Game library scanning service for common Wii U image/extensions.
- Emulation engine control-state skeleton (boot/pause/resume/stop).

## What is not implemented yet
This is **not a fully working emulator core** yet. A real Wii U emulator needs:
- Accurate CPU/GPU emulation or recompiler/JIT.
- Shader pipeline and cache management.
- Timing/audio synchronization.
- Input, UI, save states, and per-game compatibility DB.
- Significant performance optimization and testing.

## Next recommended milestones
1. Add Android UI (Jetpack Compose) for game list and settings editor.
2. Implement config file readers for real Cemu/Eden config files (JSON/INI) with validation.
3. Add JNI bridge and a native core interface.
4. Add game metadata parser and title database.
5. Add automated tests for config mapping and game scanning.
