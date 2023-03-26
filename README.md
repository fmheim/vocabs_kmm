# vocabs_kmm

I'm developing `vocabs_kmm` as a Kotlin Multiplatform Mobile (KMM) project that allows users to input a word in a selected language and generates an example sentence using the OpenAI API to explain the word in a common context. The app also features a study flashcards mode where the user sees the example sentence with the missing word and can test their vocabulary retention.
The structure of the app, especially the shared view model and common flow is oriented on the techniques teached in the KMM course by @philipp lackner: https://pl-coding.com/building-industry-level-multiplatform-apps-with-kmm/

## Features

I implemented the following features in the `vocabs_kmm` app:

- **OpenAI API using Ktor:** I used the Ktor HTTP client to access the OpenAI API and generate example sentences.
- **MVI Architecture:** I followed the Model-View-Intent (MVI) architecture to separate concerns and make the codebase more manageable.
- **Database with SQLite:** I used SQLite to store data and provide offline access to the generated example sentences.
- **Study Flashcards:** I added a study flashcards mode where the user can test their vocabulary retention by seeing the example sentence with the missing word.
- **Jet Compose UI for Android:** I utilized Jet Compose, a modern toolkit for building native Android UI, to develop the user interface for the Android version of the app. The app has a dark mode and a light mode theme.


## Future Features

I plan to implement the following features in future updates of the `vocabs_kmm` app:

- **Image Generation:** The app will use the OpenAi Api to generate images that complement the example sentences to enhance the user's learning experience. And the moment only a static place holder image is shown.
- **iOS Aåå** I plan to use SwiftUi to make the UI for the iOS app of the KMM project. As the viewmodel and all the business logic is implemented as a shared kotlin module, this will require only minimal swift code.



## Installation

To install the `vocabs_kmm` app, follow these steps:
Disclaimer: You will need an OpenAi Api key to run the app. You can generate one here after signing up for an OpenAi account: https://platform.openai.com/account/api-keys 

1. Clone the repository to your local machine.
2. Add your open ai key as a environment variable called OPENAI_API_KEY to your computer.
3. Open the project in Android Studio.
4. Build and run the app on an Android device or emulator.

