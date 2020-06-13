## Clock widget plugin for Intellij IDEA [![Build Status](https://travis-ci.org/xamgore/intellij-clock.svg?branch=master)](https://travis-ci.org/xamgore/intellij-clock)

Plugin displays the current time in the right bottom of the IDE window. That is especially useful when writing code in the full screen mode.

<img src="https://github.com/xamgore/intellij-clock/raw/master/src/main/resources/META-INF/screenshot.png" alt="screenshot with plugin" width="230">
<br>

Plugin is published on [plugins.jetbrains.com](https://plugins.jetbrains.com/plugin/11252-clock-widget) and can be easily installed via **Settings | Plugins** window.


<img src="https://github.com/xamgore/intellij-clock/raw/master/src/main/resources/META-INF/plugins.png" alt="plugins window in intellij idea" width="700">

## Building

```bash
git clone https://github.com/xamgore/intellij-clock.git
cd intellij-clock
```

We use gradle to build the plugin. It comes with a wrapper script (`gradlew` or `gradlew.bat` in the root of the repository) which downloads appropriate version of gradle automatically as long as you have JDK installed. Common tasks are:

* `./gradlew buildPlugin` — fully build plugin and create an archive at `build/distributions` which can be installed into IntelliJ IDEA via `Install plugin from disk` action found in **File | Settings | Plugins**.

* `./gradlew runIde` — run a development IDE with the plugin installed.

* `./gradlew test` — run all tests.

## Developing

[Here](https://www.jetbrains.com/idea/download/) you can get the latest Intellij IDEA Community Edition. When hacking on the plugin, you will need **DevKit** plugin.

To import this project in IntelliJ, use **File | New | Project from Existing Sources** and select `build.gradle` from the root directory of the plugin.

## Deploy

Fresh project versions are set to be auto published in https://plugins.jetbrains.com via Travis CI.
