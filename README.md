# TimeReminder

Very simple two screen app that shows how much time is left until you reach average life expectancy. Have fun!

[Download](dev_files/app-debug.apk)

## Technology stack
- Java8
- Kotlin
- Androidx
- MVP
- [Dagger]
- [RxJava]
- [PreferenceFragment]
- [Custom DatePreference]
- [Unit] / [UI tests]
- [Proguard]

## Screenshots

[![Main Screen][screen1th]][screen1]
[![Preference Screen][screen2th]][screen2]

[screen1th]: dev_files/screens/screen1_thumbnail.png
[screen1]: dev_files/screens/screen1.png
[screen2th]: dev_files/screens/screen2_thumbnail.png
[screen2]: dev_files/screens/screen2.png

[Dagger]: https://github.com/Razeeman/Android-TimeReminder/tree/dev/app/src/main/java/com/example/util/timereminder/di
[RxJava]: https://github.com/Razeeman/Android-TimeReminder/blob/dev/app/src/main/java/com/example/util/timereminder/ui/main/MainPresenter.java#L80
[PreferenceFragment]: https://github.com/Razeeman/Android-TimeReminder/tree/dev/app/src/main/java/com/example/util/timereminder/ui/prefs
[Custom DatePreference]: https://github.com/Razeeman/Android-TimeReminder/tree/dev/app/src/main/java/com/example/util/timereminder/ui/prefs/custom
[Unit]: https://github.com/Razeeman/Android-TimeReminder/tree/dev/app/src/test/java/com/example/util/timereminder
[UI tests]: https://github.com/Razeeman/Android-TimeReminder/tree/dev/app/src/androidTest/java/com/example/util/timereminder
[Proguard]: https://github.com/Razeeman/Android-TimeReminder/blob/dev/app/build.gradle#L17
