## Clock widget plugin for Intellij IDEA [![Build Status](https://github.com/xamgore/intellij-clock/actions/workflows/build.yml/badge.svg?branch=master)](https://github.com/xamgore/intellij-clock/actions/workflows/build.yml)

Plugin displays the current time in the right bottom of the IDE window. That is especially useful when writing code in the full screen mode.

<img src="https://github.com/xamgore/intellij-clock/raw/master/src/main/resources/META-INF/screenshot.png" alt="screenshot with plugin" width="230">
<br>

Click the clock to change its format.

Plugin is published on [plugins.jetbrains.com](https://plugins.jetbrains.com/plugin/11252-clock-widget) and can be easily installed via **Settings | Plugins** window.


<img src="https://github.com/xamgore/intellij-clock/raw/master/src/main/resources/META-INF/plugins.png" alt="plugins window in intellij idea" width="700">

## Building

```bash
git clone https://github.com/xamgore/intellij-clock.git
cd intellij-clock
```

The minimum IntelliJ IDEA version is 2025.3.2. CI checks 2025.3.2, 2026.1, 2026.2, and 2026.3. Install JDK 21 and use the Gradle wrapper (`gradlew` or `gradlew.bat`).

* `./gradlew buildPlugin` creates the ZIP in `build/distributions`. Install it via **Settings | Plugins | Install Plugin from Disk**.
* `./gradlew runIde` opens a development IDE with the plugin installed.
* `./gradlew check verifyPluginStructure verifyPlugin` runs the project checks and verifies the plugin against the IDE versions listed above.

## Developing

[Download IntelliJ IDEA](https://www.jetbrains.com/idea/download/) and use **File | Open** to open the repository directory as a Gradle project. JetBrains recommends **Plugin DevKit** for IDE inspections and plugin development tooling. The Gradle build runs independently.

## Deploy

Push a tag such as `v2.1.3` that matches the version in `build.gradle`. GitHub Actions builds and verifies the plugin, then automatically submits the ZIP to [JetBrains Marketplace](https://plugins.jetbrains.com/plugin/11252-clock-widget) for review.
