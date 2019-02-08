# PoC cross-platform Android/iOS Ribs Architecture in Kotlin 

iOS:
```
(cd ribs && ./gradlew linkDebugFrameworkIos)
open ios/KotlinNative.xcodeproj 
```

Android:
* Define ANDROID_HOME
*
```
(cd ribs && ./gradlew androidJar)
```
* Run the project from android folder
