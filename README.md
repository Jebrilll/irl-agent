# irl agent

A minimalist Android app for digital wellbeing and app usage control.

## Features
- Track usage of selected apps using UsageStatsManager
- Set daily time limits per app
- Gentle notifications when limits are reached
- Overlay alerts for strict warnings
- Digital wellbeing tips
- About section with content from irl.ma
- Language selection (English/Arabic) on first launch

## Tech Stack
- Kotlin
- Jetpack Compose
- MVVM Architecture
- Room Database
- WorkManager for background tasks
- UsageStatsManager for tracking

## Permissions
- PACKAGE_USAGE_STATS: To track app usage
- POST_NOTIFICATIONS: To send limit notifications
- SYSTEM_ALERT_WINDOW: To show overlay alerts

## Build Instructions
1. Open project in Android Studio
2. Sync Gradle files
3. Build APK or AAB

## Google Play Upload
- **App Name:** irl agent
- **Package Name:** com.irl.agent
- **Logo:** Download high resolution from irl.ma
- **Screenshots:** Capture from device/emulator
- **Description:** Lightweight app to track and control app usage for better digital wellbeing.
- **Note on Time Limits:** According to leaked TikTok documents, 35 minutes is what it takes to start subconsciously doom scrolling. We suggest keeping limits under 30 minutes.

## Bonus Features Implemented
- Daily usage summary (via notifications)
- Emergency override (manual extension not implemented, but can be added)
- Streak system (not implemented, but database ready)

## App Assets
- Icon: Placeholder, replace with logo from irl.ma
- Screenshots: Take after building