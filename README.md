# Root Temporary — DFRoot compatibility audit

This repository starts a device-focused, read-only diagnostics companion for auditing DFRoot compatibility on the Vivo iQOO Z9x (I2219).

## Reported device
- Android 16
- Firmware: PD2353EF_EX_A_16.2.17.0.W20
- Kernel: 5.10.246-android12-9-00032-gc8d19ae79d9f-ab15925918
- Bootloader: locked
- DFRoot 4.7 result: DEVICE NOT VULNERABLE after crash_dump64 verification failed

## Scope
The Android app collects public build properties and the kernel release for comparison with trusted, device-specific compatibility evidence. It does not run an exploit, patch kernel files, alter SELinux, or grant root. See docs/DEVICE_AUDIT.md.

## Build
GitHub Actions workflow: .github/workflows/build-diagnostics.yml

Locally, with JDK 17, Android SDK platform 36, and Gradle 8.13 installed:

    gradle --no-daemon :app:assembleDebug

APK output: app/build/outputs/apk/debug/app-debug.apk

## Compatibility status
UNVERIFIED. The supplied DFRoot 4.7 log says DEVICE NOT VULNERABLE. This repository does not bypass that check or claim a root method is available for this firmware.
