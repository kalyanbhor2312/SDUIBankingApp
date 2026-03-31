# KBSDUIBankApp

KBSDUIBankApp is a modern Android banking application built with a focus on modularity and **Server-Driven UI (SDUI)**. It leverages Jetpack Compose for building a dynamic and responsive user interface that can be updated from the server without requiring a new app release.

## Project Structure

The project follows a multi-module architecture to promote separation of concerns, scalability, and faster build times.

### Core Modules
- **`:app`**: The main entry point of the application. It handles global navigation and module orchestration.
- **`:core`**: Contains base classes, utilities, and common models used across the entire project.
- **`:core-engine`**: The heart of the Server-Driven UI implementation. It handles the rendering logic for dynamic components.
- **`:designsystem`**: A collection of reusable UI components and tokens (colors, typography) based on Jetpack Compose, ensuring a consistent look and feel.

### Feature Modules
Each feature is encapsulated in its own module under the `:features` namespace (with some legacy structure in `:account`):
- **`:features:auth`**: Handles user authentication, login flows, and session management.
- **`:features:home`**: The main dashboard showing account overviews and quick actions.
- **`:features:account`**: Detailed views and management for bank accounts.
- **`:features:payment`**: Facilitates transfers, bill payments, and beneficiary management.
- **`:features:profile`**: Manages user settings, profile details, and preferences.

## Tech Stack
- **Language**: Kotlin
- **UI Framework**: Jetpack Compose
- **Architecture**: Multi-module with Feature-based separation.
- **Dependency Management**: Gradle Version Catalogs (`libs.versions.toml`).
- **Build System**: Gradle Kotlin DSL.
- **SDUI**: Custom engine for rendering UI components from remote JSON/configuration.

## Getting Started
1. Clone the repository.
2. Open the project in **Android Studio (Ladybug or newer)**.
3. Sync the project with Gradle files.
4. Run the `:app` module on an emulator or physical device.

## Modularity Highlights
- **Centralized Configuration**: Common Android configurations (SDK versions, compile options) are managed in the root `build.gradle.kts` to ensure consistency.
- **Isolated Features**: Feature modules depend on `:core` and `:designsystem` but remain independent of each other, communicating through the `:app` router.
