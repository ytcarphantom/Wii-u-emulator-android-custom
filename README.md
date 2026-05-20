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

diff --git a/README.md b/README.md
index 7623f7ac7039139f95f4ebab95c5a2d35d2f6c7b..cb2c0c03998025456dae238bec4780dde2681b8f 100644
--- a/README.md
+++ b/README.md
@@ -4,25 +4,44 @@ You were right: the previous commit had only backend skeleton classes and no rea
 
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
+
+
+## Publish APKs on GitHub
+This repository now includes a GitHub Actions workflow at `.github/workflows/android-apk-release.yml` that:
+- Builds `app-release.apk` on every tag push matching `v*` (for example: `v0.2.0`).
+- Lets you run the build manually from the **Actions** tab (`workflow_dispatch`).
+- Uploads the APK as an Actions artifact.
+- Automatically creates a GitHub Release for tags and attaches the APK.
+
+### How to publish
+1. Commit and push your changes.
+2. Create and push a version tag:
+   ```bash
+   git tag v0.2.0
+   git push origin v0.2.0
+   ```
+3. Open the new GitHub Release and download `app-release.apk`.
+
+> Note: this workflow currently publishes an unsigned release APK unless your Gradle build is configured with signing credentials.
