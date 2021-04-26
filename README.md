# MetaWeather
Example Android project in Kotlin which uses the https://www.metaweather.com/ api.

# About the project

## Used technologies
- [Android Jetpack](https://developer.android.com/jetpack)
  - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata)
  - [Navigation Component](https://developer.android.com/guide/navigation)
  - [Room](https://developer.android.com/topic/libraries/architecture/room)
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
  - [Data Binding](https://developer.android.com/topic/libraries/data-binding)
  - [ConstraintLayout](https://developer.android.com/training/constraint-layout)
- [MVVM architecture](https://developer.android.com/jetpack/docs/guide)
- [AndroidX](https://developer.android.com/jetpack/androidx)
- [Dagger 2](https://dagger.dev/)
- [Kotlin](https://kotlinlang.org/)
- [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html)
- [Retrofit](https://square.github.io/retrofit/)
- [Glide](https://bumptech.github.io/glide/)
- [Lint](https://developer.android.com/studio/write/lint)
- [Ktlint](https://github.com/pinterest/ktlint)
- [LeakCanary](https://square.github.io/leakcanary/)
- Unit tests (JUnit, Truth, etc.)

## Architecture

MetaWeather is a [single-Activity Android application](https://youtu.be/2k8x8V77CrU) which uses [MVVM architecture](https://developer.android.com/jetpack/docs/guide):

![alt text](https://github.com/ghataa/MetaWeather/blob/master/final-architecture.png "MVVM on Android")

Each layer in the architecture is loosely coupled, mostly with interfaces. The solution is extendable, well-maintainable, and also testable by isolating the different layers/components. The architecture tries to follow the most-used clean code and SOLID principles together with some design patterns like Singleton, Factory, and Dependency Injection.

## Data and cache handling

The application uses two different data sources: local and remote. The application communicates with the remote data source via RESTful API with a Retrofit HTTP client. The local data source is an SQLite database implemented with Room. The application uses the local data source as a source of truth, always. If the local data is outdated, it's going to be updated before showing it. It's also possible to force to update the local data from the remote source by using the pull-to-refresh gesture.

## Testing

The project contains an example for unit testing. It's also possible to test the different components either with unit or instrumented (or even end-to-end Espresso) tests in the future.

## Build project

The project can be compiled by running the `build-project.sh` file.