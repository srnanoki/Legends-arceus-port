ARCEUS

<p align="center">
  <strong>A dedicated Android emulation project focused on Pokémon Legends: Arceus.</strong>
</p><p align="center">
  <img src="https://img.shields.io/badge/Platform-Android-3DDC84?logo=android&logoColor=white" />
  <img src="https://img.shields.io/badge/Language-Kotlin%20%7C%20C%2B%2B-blue" />
  <img src="https://img.shields.io/badge/Build-Gradle-02303A?logo=gradle" />
  <img src="https://img.shields.io/badge/Status-In%20Development-orange" />
</p>---

About

ARCEUS is an experimental Android project derived from the Eden emulator codebase, with the goal of creating a focused experience for Pokémon Legends: Arceus.

Instead of being designed as a general-purpose game library, ARCEUS aims to provide a streamlined experience centered around one game, with a simplified interface and Android-focused improvements.

The project is currently under development. Some features and parts of the original emulator are still being adapted.

Project goals

- Create a dedicated Android experience for Pokémon Legends: Arceus.
- Simplify the game selection and launch process.
- Preserve the original emulation core and its important functionality.
- Keep useful graphics and performance options, including Lossless Scaling support.
- Improve the interface and make navigation easier on mobile devices.
- Maintain an open and transparent development process.

Technology

Component| Technology
Android application| Kotlin
Native emulation core| C++
Android interface| Existing Android UI framework
Build system| Gradle
Native build| CMake
Automation| GitHub Actions

Current development status

ARCEUS is still experimental.

Feature| Status
Android application build| Working
ARM64 APK generation| Working
Original emulation core| Preserved from upstream
Lossless Scaling support| Present in the inherited codebase
Dedicated Pokémon Legends: Arceus interface| In development
Simplified direct game launch| Planned
File access and game folder handling| Being improved
Performance and compatibility testing| Ongoing

The status above describes the application and development work, not a guarantee that the game will run on every Android device.

Installation

Development builds may be made available through the repository's GitHub Actions artifacts.

1. Open the repository's Actions tab.
2. Select the Android build workflow.
3. Open a successful workflow run.
4. Download the generated APK artifact.
5. Extract the APK and install it on a compatible Android device.

Only use game files and system files that you are legally entitled to use. ARCEUS does not distribute Pokémon Legends: Arceus, game keys, firmware, or copyrighted game content.

Hardware and compatibility

Compatibility and performance depend on the Android device, GPU drivers, available memory, emulator settings, and game version.

A successful APK build does not necessarily mean that gameplay is fully functional. Compatibility testing is still ongoing.

Credits and acknowledgements

ARCEUS is based on the Eden Emulator project and inherits code and components from its upstream codebase.

Thank you to the developers and contributors of Eden and the projects it builds upon.

Original copyright notices, license headers, and applicable third-party attributions are retained in the source code.

Disclaimer

ARCEUS is an independent, community-developed project. It is not affiliated with, endorsed by, or associated with Nintendo, The Pokémon Company, Game Freak, or the Eden Emulator developers.

All trademarks and game-related intellectual property belong to their respective owners.

License

ARCEUS inherits licensing obligations from its upstream codebase. See the repository's "LICENSE" file and individual source-file headers for the applicable license terms.

---

<p align="center">
  <strong>ARCEUS — One game. One focused experience.</strong>
</p>
