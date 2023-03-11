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

# Architecture concept

--

# Technical details

--

# Demo application

This repository contains a simple app implemented for Android and iOS usign the Shared Services architecture for the KMM module.

### Login screen

We display welcome text with user's email. Email is hardcoded for the demo purpose. 
User can click `Login` button to verify if user with this email can access the app. After successfull verification user is moved to the `Home` screen.

### Home screen

We display a list of posts. Each post contains a title, author name and body. 
User can scroll through the list and click `Logout` button to move back to the `Login` screen

![Projekt bez tytułu (1)](https://user-images.githubusercontent.com/46427781/224264955-f82c7422-fc6d-4a04-a962-b4c514d89d98.png)
