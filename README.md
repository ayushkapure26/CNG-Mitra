# CNG मित्र
### Your CNG refill diary, mileage tracker, and expense companion

[![Android build](https://github.com/ayushkapure26/CNG-Mitra/actions/workflows/android.yml/badge.svg)](https://github.com/ayushkapure26/CNG-Mitra/actions/workflows/android.yml)

**Kotlin · Jetpack Compose · Room · Android**

[Get a debug APK](https://github.com/ayushkapure26/CNG-Mitra/actions/workflows/android.yml) · [Build locally](#build) · [Service setup](#features-and-service-setup)

Keep vehicle records, log CNG refills, and understand fuel expenses in one place. Guest Mode keeps the personal diary available offline; cloud backup and online services are optional.

## At a glance

| Feature | What it helps you do |
| --- | --- |
| Vehicle manager | Organize vehicles and odometer records |
| Refill diary | Record fuel quantity, price, and spending |
| Mileage & reports | Review fuel economy and expenses |
| Import & export | Move records through CSV |
| Station discovery | Browse sample stations and unverified driver reports |
| Offline Guest Mode | Use local features without creating an account |

**Project status:** Debug APK builds, unit tests, and lint have passed in GitHub Actions. This is a development app; station information is not a verified live feed.

## Build

Use JDK 21 and Android Studio with SDK platform **Android 36.1** and Build Tools **36.0.0**. Android 36 Robolectric tests require Java 21. The repository includes the Gradle 9.3.1 wrapper, matching Android Gradle Plugin 9.1.1.

```sh
git clone https://github.com/ayushkapure26/CNG-Mitra.git
cd CNG-Mitra
./gradlew :app:assembleDebug :app:testDebugUnitTest
```

On Windows use `gradlew.bat`. Android Studio can create your local SDK path in `local.properties`. Debug signing uses Android's automatically generated debug key.

APK output: `app/build/outputs/apk/debug/app-debug.apk`.
GitHub Actions builds and tests on pushes and pull requests; successful runs provide a **CNG-Mitra-debug-apk** artifact. This is a development APK, not a Play Store release.

## Features and service setup

- **Guest Mode:** local vehicle and refill records, reports, and offline access; no login is required. New installations start with an empty personal diary; no fabricated vehicles or fuel expenses are inserted.
- **Pump data:** bundled stations are sample data, not verified live prices, availability, or pressure. Confirm at the pump before travel.
- **Google Maps:** copy `.env.example` to `.env` and configure `MAPS_API_KEY` with Android package/certificate restrictions.
- **Firebase sign-in and backup:** register Android package `com.aistudio.cngtracker.cngpxz` in your Firebase project. Place its configuration in `app/google-services.json`, enable email/password and Google providers, and register your signing certificate fingerprints. Cloud actions require an authenticated Firebase account. Review `firestore.rules` and deploy them to your own project with `firebase deploy --only firestore:rules --project YOUR_PROJECT_ID`; this repository does not deploy backend services automatically.
- **Gemini:** optional `GEMINI_API_KEY` in `.env`; without it the app uses offline tips. A key bundled in an APK can be extracted. Use a backend before distributing a production app with a shared paid key.

No API keys, signing passwords, or Firebase credentials are provided. The default build works without private service configuration; those services remain unavailable until configured. Never commit `.env`, keystores, or signing passwords.

## Project structure

- `app/src/main/java/com/example/data/`: Room models, DAOs, and repositories
- `app/src/main/java/com/example/ui/`: Compose screens, navigation, and view models
- `app/src/main/java/com/example/util/`: import/export, location, authentication, and reports
- `app/src/test/`: local and Robolectric tests
- `gradle/`: version catalog and wrapper

## Verification

```sh
./gradlew :app:testDebugUnitTest :app:lintDebug
```

For a device smoke test, open Guest Mode, add a vehicle and refill, check reports, export/import CSV, restart the app, and confirm persistence. Test location permission denial and offline operation. Test real sign-in and cloud backup only with your own Firebase configuration.

## Release signing

Set `KEYSTORE_PATH`, `STORE_PASSWORD`, and `KEY_PASSWORD` in your private build environment. The upload key alias is `upload`. Then run `./gradlew :app:bundleRelease`. Keep the signing key backed up privately. Production readiness also requires live data integration, backend access rules, API restrictions, and device testing.

## Provenance

Imported from the owner's CNG मित्र Android project. No new open-source license is granted by this repository cleanup.
