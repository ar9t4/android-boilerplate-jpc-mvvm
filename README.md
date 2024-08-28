# 🚀 Android Jetpack Compose Boilerplate
[![CompileSDKVersion](https://img.shields.io/badge/CompileSDKVersion-v34-green.svg)]()
[![TargetSDKVersion](https://img.shields.io/badge/TargetSDKVersion-v34-blue.svg)]()
[![MinSDKVersion](https://img.shields.io/badge/MinSDKVersion-v21-green.svg)]()
[![Boilerplate Version](https://img.shields.io/badge/Boilerplate%20Version-v1.0.0-white.svg)]()

Start developing your new Android application with this boilerplate. It is completely built on Kotlin and Jetpack Compose. Inspired from [Now in Android](https://github.com/android/nowinandroid), follows the official architecture guidance e.g. [Modern App Architecture](https://developer.android.com/topic/architecture#modern-app-architecture) in conjunction with MVVM, UseCases and Repository pattern.

### Modern App Architecture
1. A reactive and layered architecture
2. Unidirectional Data Flow (UDF) in all layers of the app
3. A UI layer with state holders to manage the complexity of the UI
4. Coroutines and Flows
5. Dependency injection best practices.
   <br>
   <br>

## Pre-Requisites
1. Install [Android Studio](https://www.googleadservices.com/pagead/aclk?sa=L&ai=DChcSEwjX4qTswYyCAxU4i2gJHWlEDJAYABAAGgJ3Zg&gclid=CjwKCAjws9ipBhB1EiwAccEi1JqlocRMW5b2pehSD8CllReyGFsH49uO9BCoP7wG2qbF5mh6LHL6NhoClTwQAvD_BwE&ei=KZU2Ze_PGtW0i-gPqtS08Aw&ohost=www.google.com&cid=CAESVeD2V7PgtaoP_sCsQYE4i7iFM_TOKQ50-6YqAragWeAOkLM4vCWgxuaZxJ1vcvkQtq1ihgNmZAuMfiz_aesJQKBXAal8P_WgcpHTDD0Jbf9kqXN5s0o&sig=AOD64_0olBMk3LTAB8pNBZ8f4v0sKMbVqA&q&sqi=2&adurl&ved=2ahUKEwiviJ7swYyCAxVV2gIHHSoqDc4Q0Qx6BAgJEAE)
2. Setup environment variables for **Android**.
   <br>


## Getting Started
1. Clone this repo `git clone https://github.com/ar9t4/android-boilerplate-jpc-mvvm.git`
2. Open the project with Android Studio and let it download gradle dependencies
3. Connect an **Android** device or **Android Emulator**
4. Click on Run project
5. Woah, You are Done!
   <br>

## Features
1. Modern App Architecture in conjunction with MVVM, UseCases and Repository pattern
2. Kotlin Coroutines with Flows
3. Jetpack Compose
4. Jetpack Navigation
5. Hilt Dependency Injection
6. Retrofit: Fetching list of random users from [Random User Generator](https://randomuser.me/)
7. Room Database: Stores list of random users
8. Preferences
9. Work Manager: A pre-defined recurring scheduled work manager job
10. Multi-Lingual support: In-App Localization with 16 languages support
11. Dark Theme support: System Default, Light Mode and Dark Modes
12. Pre-defined Settings, Themes, Languages, Feedback and More screens
    <br>

## Libraries
1. [Kotlin](https://kotlinlang.org) - The official language for Android development
2. [Coroutines](https://developer.android.com/kotlin/coroutines) - For executing background/async jobs
3. [Flows](https://developer.android.com/kotlin/flow) - For consuming async data streams
4. [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - For holding and exposing state to the UI in lifecycle aware manner
5. [Jetpack Compose](https://developer.android.com/compose) - For building native UI
6. [Compose Navigation](https://developer.android.com/develop/ui/compose/navigation) - For navigation between screens
7. [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) - For dependency injection
8. [Room](https://developer.android.com/training/data-storage/room) - For local database support
9. [Work Manager](https://developer.android.com/topic/libraries/architecture/workmanager) - For executing a one time or recurring scheduled task(s)
10. [Retrofit](https://square.github.io/retrofit) - For network communication support
    <br>

## How it looks
<table>
  <tr>
   <td>Light Mode</td>
   <td>Dark Mode</td>
  </tr>
  <tr>
    <td><img width="250" height="500" src="https://github.com/ar9t4/android-boilerplate-jpc-mvvm/blob/main/screenshots/light/lm.gif" alt="Unable to load"></td>
    <td><img width="250" height="500" src="https://github.com/ar9t4/android-boilerplate-jpc-mvvm/blob/main/screenshots/dark/dm.gif" alt="Unable to load"></td>
  </tr>
</table>

## Contribution Guidelines
**PR's** created as per [Official Guidelines](https://docs.github.com/en/pull-requests/collaborating-with-pull-requests/proposing-changes-to-your-work-with-pull-requests/creating-a-pull-request) are always welcome.
