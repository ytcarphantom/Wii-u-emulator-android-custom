# Wii-u-emulator-android-custom

You were right: the previous commit had only backend skeleton classes and no real usable screen.

This update adds a **usable Android UI prototype** so you can actually do things in-app now.

## What you can do now
- Launch the app and open a real main screen.
- Pick a folder using Android's system folder picker.
- Scan the selected folder recursively for Wii U game file extensions (`.wud`, `.wux`, `.rpx`, `.iso`).
- Import sample settings mappings from:
  - Cemu-style keys
  - Eden-style keys
- View imported settings and scanned game results directly in the UI.

## Added files/components
- `MainActivity` UI controller with:
  - Folder picker integration via `OpenDocumentTree`
  
  - Recursive storage scan via `DocumentFile`
  - Buttons for Cemu/Eden sample import
- `activity_main.xml` layout with buttons and output text areas.
- Android `AndroidManifest.xml` launcher activity setup.
- App theme and strings resources.
- Updated Gradle dependencies for AppCompat/Material/DocumentFile/activity APIs.

## Important scope note
This is still **not yet a full Wii U emulation core**. It is now a practical app shell you can interact with, and it is ready for the next step: wiring a native emulator backend through JNI.
