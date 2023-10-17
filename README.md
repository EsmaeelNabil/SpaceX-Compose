# Android Thermondo SpaceX-Compose

- The purpose of this readme is to share the thought process that i had while developing the app and following the guidelines.
- Feel free to view the [Pull requests](https://github.com/EsmaeelNabil/SpaceX-Compose/pulls?q=is%3Apr+is%3Aclosed) that states the steps and what is done in each one with Pictures.

## The task stated the following requirements:

- Users can see space x detailed launches information
- Users can see description of the launches.
- Users can bookmark any launches and view them later.(Don't worry about sending the information to the cloud, saving it locally is good)

## with this Advice in mind while working on the challenge
`What if I don't finish?
Try to produce something that is at least minimally functional. Part of the exercise is to see what you prioritize first when you have a limited amount of time. For any unfinished tasks, please add comments to your code with a short explanation. You will be given an opportunity later to go into more detail and explain how you would go about finishing those tasks.
Have fun! that is the most important thing! Looking forward to your submission`

- I enjoyed it so much! and i Learned a lot in the process.

  
## Decisions made while working on the challenge

- I decided to use the MVVM + clean architecture approach for structuring the codebase in a way that is easy to understand and navigate through even though the project didn't need that much of a structure and could be done with simpler approaches, but since this is a challenge and an opportunity to have fun i wanted to enjoy the time working on it.
- Used `Mockk` for mocks and `Turbin` for testing Kotlin Flows as it makes the process quick and easy.
- Decided to implement the app as an `offline first` app, as the launches won't change over time, but new ones will be added.
- Used `build-logic` convention approach for easier integration and better dependency managmend and better modularazation, [more information here](https://github.com/android/nowinandroid/blob/main/build-logic/README.md).
- Used [ `Coroutines`, `StateFlow` , `Flow`, `Ktor`, `Kotlinx.serialization` ] as I think these are a good fit for this project.
- Used `Dagger-Hilt` for dependency injection, as it makes it easy to inject dependencies and to apply the inversion of control and dependency injection/inversion principles, also in ui testing later by replacing modules.
- Added Github `workflows` as a safety net for running the unit tests with each pull request that is going to be merged with `main`.
- Added `designsystem` module to have a scallable `Theme`, `Typo` ,`Paddings`,`Shapes` and base `Components`.
- Added `common` module to hold the common utils, classes and extensions for less duplications.
- Added `data` module to hold the Repositories and communicate with `network` and `databse` modules for more separation of concerns.
- Added each feature in a separate module for separation of concerns and, decoupling and for better organizing, with the help of `build-logic` gradle `convention plugins` to create new modules with ease


| Features   |      Build.gradle.kts(feature)      |
|----------|:-------------:|
| <img width="273" alt="Screenshot 2023-10-17 at 9 06 10 pm" src="https://github.com/EsmaeelNabil/SpaceX-Compose/assets/28542963/b6e72457-2d7e-4121-87d7-2c4aa820ad29"> | <img width="520" alt="Screenshot 2023-10-17 at 9 07 48 pm" src="https://github.com/EsmaeelNabil/SpaceX-Compose/assets/28542963/f13891fc-ebde-48f1-83de-45c407a87a95">|


## Future Improvements and considerations for scalability 
- Enhancing the design, and making it more performante.
- Adding UI tests and maybe use a library like [accompanist-testharness](https://google.github.io/accompanist/testharness) to make the process easier for testing with different configurations. and Using [MockEngine](https://ktor.io/docs/http-client-testing.html) to mock the network calls for ui tests not only unit tests.
- Using Jetpack Compose navigation bars for better handling the navigation and adding a bottom bar instead of icons.
- Adding a local DataSource instead of relying on `Room's Dao` for better refactoring in case we wanted to use something else like [SqlDelight](https://github.com/cashapp/sqldelight).
- Adding `Unit-tests` and `integration-tests` to the `use cases` in the app.
- Adding `Unit-tests` for the `Mapper` layers.
- Adding and updating more Unit-tests for the viewmodels, datasources, repositories if missing.
- Using MVI approach for better controlling of side effects, events and intents.
- Implementing a generic State Management plugin that can be plugged in the `ViewModels` or Using a `State Management library` like [MvRx](https://github.com/airbnb/mavericks) to make the process of handling the state easier and more maintainable and easier to test, debug and mock.

  
| Launches   |      Error Handling & empty cache      | Boomarks | 
|----------|:-------------:|:----------:|
|![Screenshot 2](https://github.com/EsmaeelNabil/SpaceX-Compose/assets/28542963/aa4372df-6cd8-4dd2-8fb1-bcf247ce8434)|  ![Screenshot 1](https://github.com/EsmaeelNabil/SpaceX-Compose/assets/28542963/24f8c9b4-622e-421f-807f-92ccca1c9946) | ![ss](https://user-images.githubusercontent.com/28542963/275959964-bd14b20e-4e47-495c-8ba7-17ef25214ff1.png) |

























---------------------------------------------------------------------------

# Android Coding Challenge

We appreciate you taking the time to participate and submit a coding challenge. 
In the next step we would like you to implement a list of image dispaly feature with unit and/or integration tests
that fetches data from [The SpaceX API](https://github.com/r-spacex/SpaceX-API/blob/master/docs/README.md) 

Below you will find a list of tasks and limitations
required for completing the challenge.


### Application:

* Users can see space x detailed launches information
* Users can see description of the launches.
* Users can bookmark any launches and view them later.(Don't worry about sending the information to the cloud, saving it locally is good)

### Important:

* test accordingly
* use Jetpack Compose
* Documentation of technical decisions you make during the challenge. 
* The API implementation and the architecture of the code is completely up to you.


### What if I don't finish?

Try to produce something that is at least minimally functional. Part of the exercise is to see what you prioritize first
when you have a limited amount of time. For any unfinished tasks, please add comments to your code with a
short explanation. You will be given an opportunity later to go into more detail and explain how you would go about
finishing those tasks.


Have fun! that is the most important thing!
Looking forward to your submission!
