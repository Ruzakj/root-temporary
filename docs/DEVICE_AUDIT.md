# Device compatibility audit — Vivo iQOO Z9x

## Reported device
- Model: Vivo iQOO Z9x (I2219)
- Android: 16
- Firmware/build: PD2353EF_EX_A_16.2.17.0.W20
- Kernel: 5.10.246-android12-9-00032-gc8d19ae79d9f-ab15925918
- Bootloader: locked
- DFRoot reported version: 4.7
- Android security patch reported: 2026-09-01

## Observed DFRoot result
The supplied log reports that 1,408 bytes were patched to crash_dump64, followed by crash_dump64 verification failed and DEVICE NOT VULNERABLE. This proves the attempted patch did not pass DFRoot verification. It does not establish the exact cause or prove another app build will work.

## Safe audit conclusions
1. Kernel KMI family android12-5.10 is not a guarantee of exploitability.
2. Building a new APK cannot make a mitigated kernel vulnerable.
3. Do not remove vulnerability checks, bypass mitigations, or claim root success without independent verification.
4. Gather exact app version, build fingerprint, security patch, kernel release, upstream source revision, and full log before making compatibility claims.
5. The companion diagnostics app is read-only and does not execute DirtyFrag or change system state.

## Build meaning
A green GitHub Actions workflow means only that the diagnostics APK compiled successfully; it is not a successful root test.
