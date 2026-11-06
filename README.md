## Clock widget plugin for Intellij IDEA [![Build Status](https://github.com/xamgore/intellij-clock/actions/workflows/build.yml/badge.svg?branch=master)](https://github.com/xamgore/intellij-clock/actions/workflows/build.yml)

Plugin displays the current time in the right bottom of the IDE window. That is especially useful when writing code in the full screen mode.

<img src="https://github.com/xamgore/intellij-clock/raw/master/src/main/resources/META-INF/screenshot.png" alt="screenshot with plugin" width="230">
<br>

Click the clock to choose a 24-hour, 12-hour, locale-based, or custom format (set in Registry under `intellij-clock.format.pattern`).

Plugin is published on [plugins.jetbrains.com](https://plugins.jetbrains.com/plugin/11252-clock-widget) and can be easily installed via **Settings | Plugins** window.


<img src="https://github.com/xamgore/intellij-clock/raw/master/src/main/resources/META-INF/plugins.png" alt="plugins window in intellij idea" width="700">

## Clock format

The default format is **24 hours**.

Click the clock and choose **Custom** to open **Registry**. Type `intellij-clock.format` to find both keys.

* `intellij-clock.format.pattern` sets the Java `DateTimeFormatter` pattern for **Custom**. It defaults to `EEE d MMM, HH:mm`. An empty or invalid pattern displays 24-hour time.
* `intellij-clock.format.locale` sets the formatting locale for all modes. It defaults to `system`, which follows the runtime's formatting locale. You can also set a BCP 47 tag such as `ru-RU` or `zh-CN`.

### Format and locale examples

Use the **Locale** and **Pattern** values together in Registry. Each example shows the same date and time, 6 November 2026 at 17:05:09.

| Country | Locale | Pattern | Example |
| --- | --- | --- | --- |
| United States | `en-US` | `MM/dd/uuuu h:mm a` | `11/06/2026 5:05 PM` |
| Germany | `de-DE` | `dd.MM.uuuu HH:mm` | `06.11.2026 17:05` |
| Russia | `ru-RU` | `EEE d MMM, HH:mm` | `пт 6 нояб., 17:05` |
| China | `zh-CN` | `uuuu年M月d日 EEE HH:mm` | `2026年11月6日 周五 17:05` |
| Japan | `ja-JP` | `uuuu/MM/dd (EEE) HH:mm:ss` | `2026/11/06 (金) 17:05:09` |

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

Push a tag such as `v2.1.2` that matches the version in `build.gradle`. GitHub Actions builds and verifies the plugin, then automatically submits the ZIP to [JetBrains Marketplace](https://plugins.jetbrains.com/plugin/11252-clock-widget) for review.
