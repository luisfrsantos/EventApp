EventsApp
================

Prerequisites
- Android studio  0.8.1 preview or later
- ANDROID_HOME variable is set.

### getting started
- Fork or clone this repo
- Run the following to pull in all external dependencies:

```bash
./graldew clean build
```

### Build and run all tests
- Runs both instrumented tests within 'app' and all unit tests in the unit-test project
```bash
./gradlew clean build -i
```

## Unit Testing
The 'unit-test' module facilitates for unit testing without an emulator (non-connected). 'Connected`
unit tests should live under <root>/app/src/androidTest.
see: [Here](http://tools.android.com/tech-docs/new-build-system/user-guide)
"This can contain unit tests, instrumentation tests, and later uiautomator tests"

### Unit Tests
```bash
./gradlew :u./grt

```

### KTLINT
- After unit tests have been executed
```bash
./gradlew ktlint

```