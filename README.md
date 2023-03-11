# KMM Shared MVI Architecture

[![Master build](https://github.com/Maruchin1/kmm-shared-mvi/actions/workflows/master-build.yml/badge.svg)](https://github.com/Maruchin1/kmm-shared-mvi/actions/workflows/master-build.yml)

<img align="right" width="400" src="https://user-images.githubusercontent.com/46427781/224491207-3978aad4-6306-407c-af00-fa25a2960ad7.png">

### androidApp (Native)

- `Views` implemented either with XML or Jetpack Compose.

### iosApp (Native)

- `Views` implemented either with the UIKit or SwiftUI.

### shared (KMM)

- `ViewModel` handles presentation logic and emits `UiState` to the native UI.

- `ViewModel` uses `Repositories` and `UseCases` to get data and execute logic.

- `UseCase` encapsulates some reusable logic which can be used across different `ViewModels`.

- `Repository` access different `DataSources` in order to fetch or persist data locally or remotely.

### Code distribution

| Module | Lines of code |
| ------ | ------------- |
| shared | 509 |
| androidApp | 265 |
| iosApp | 229 |

# Architecture concept

This architectural approach follows Google's [Guide to app architecture](https://developer.android.com/topic/architecture). It uses simple custom `KmmViewModel` and `KmmStateFlow` abstractions to implement which allow you to move presentation logic to the shared code.

With shared View Models you can keep the native UI implementation maximaly simple while all the logic is handled by the KMM code.

Although there are multiple 3rd party solutions which allow you to implement shared presentation logic the goal of this repository is to show that we can achieve a good KMM architecture without using any big 3rd party tools.

# Technical details

KMM module has a single entry point called `DemoSdk`. This calss allows native apps to get instances of `ViewModels` in a simple way. Under the hood KMM module uses [Koin](https://insert-koin.io/) DI framework to connect all the code together.

The `DemoSdk` class accepts an instance of `DemoConfig` as a constructor parameter. This `DemoConfig` uses an `expect/actual` mechanism to provide platform specific configuration to the KMM module. For example on the Android side we need to pass down the `Context` to create local storage, while on the iOS side `Context` is not present.

Thanks to the single `DemoSdk` entry point the native apps gain great flexibility in terms of choosing the tools and solutions according to the preference. 

Demo Android is not forced to use Koin which is present in the KMM module. It is possible to connect shared View Models to the Android UI without using any extra tools. An instance of the `DemoSdk` is created in the `Application` class and accessible from the UI by using the `Context`. Then in the UI the View Model is connected using `val viewModel = viewModel { sdk.loginViewModel }`.

The same applies to the iOS  app. We create a `DemoSdkProvider` which is a Singleton. In this class we create an instance of the `DemoSdk` and allow other parts of the app to access it in order to get given `ViewModels`.

# Demo application

This repository contains a simple app implemented for Android and iOS usign the Shared Services architecture for the KMM module.

### Login screen

We display welcome text with user's email. Email is hardcoded for the demo purpose. 
User can click `Login` button to verify if user with this email can access the app. After successfull verification user is moved to the `Home` screen.

### Home screen

We display a list of posts. Each post contains a title, author name and body. 
User can scroll through the list and click `Logout` button to move back to the `Login` screen

![Projekt bez tytułu (1)](https://user-images.githubusercontent.com/46427781/224264955-f82c7422-fc6d-4a04-a962-b4c514d89d98.png)
