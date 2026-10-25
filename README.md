# Root Temporary — DFRoot compatibility audit

Device: Vivo iQOO Z9x I2219 · Android 16 · Firmware PD2353EF_EX_A_16.2.17.0.W20 · Kernel 5.10.246-android12-9-00032-gc8d19ae79d9f-ab15925918 · bootloader locked.

This branch builds a read-only diagnostics APK for collecting device properties. It is NOT a root exploit and does not grant root.

The supplied DFRoot 4.7 log reports `crash_dump64 verification failed` and `DEVICE NOT VULNERABLE`. A successful app build does not establish exploit compatibility. No mitigation bypass is included.

Build workflow: `.github/workflows/build-diagnostics.yml`
