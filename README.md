# vocabs_kmm

I'm developing `vocabs_kmm` as a Kotlin Multiplatform Mobile (KMM) project that allows users to input a word in a selected language and generates an example sentence using the OpenAI API to explain the word in a common context. In addition, the OpenAI API is utilized by the app to generate an image with the aim of enhancing comprehension of the provided example sentence. The app also features a study flashcards mode where the user sees the example sentence with the missing word and can test their vocabulary retention.
I integrated several techniques learned from this [KMM course by @philipplackner](https://pl-coding.com/building-industry-level-multiplatform-apps-with-kmm/) into my app. This includes for example sharing the view model, which is very useful.


## Features

I implemented the following features in the `vocabs_kmm` app:

- **OpenAI API using Ktor:** I used the Ktor HTTP client to access the OpenAI API and generate example sentences.
- **Image Generation:** The app is using the OpenAi Api to generate images that complement the example sentences to enhance the user's learning experience.
- **MVI Architecture:** I followed the Model-View-Intent (MVI) architecture to separate concerns and make the codebase more manageable.
- **Database with SQLDelight:** I used SQLDelight in the shared module to store data and provide offline access to the generated flashcards.
- **Study Flashcards:** I added a study flashcards mode where the user can test their vocabulary retention by seeing the example sentence with the missing word.
- **Jet Compose UI for Android:** I utilized Jet Compose, a modern toolkit for building native Android UI, to develop the user interface for the Android version of the app. The app has a dark mode and a light mode theme.
- **Dependency Injection with Hilt for Android:** On the Android side I chose Dagger Hilt for dependency injection to improve modularity and testability of the code. 

### Screen recording of current status
Here you can see two screen recordings videos of some examples of example sentence + image generation and the "random flashcard" study mode.



https://user-images.githubusercontent.com/72472174/229364322-6aacca60-bbfe-477e-ab8d-8e10b54be14a.mp4



https://user-images.githubusercontent.com/72472174/229364328-3b479a8e-f681-43a7-9ad3-276cb1e9dc74.mp4




## Future Features

I plan to implement the following features in future updates of the `vocabs_kmm` app:


- **iOS App** I plan to use SwiftUi to make the UI for the iOS app of the KMM project. As the viewmodel and all the business logic is implemented as a shared kotlin module, this will require only minimal swift code.
- **Improved Study Mode** I think it would be cool to add more features to the existing study mode, such as gamification elements, and a spaced repetition algorithm, to make learning more engaging and effective.



## Installation

To install the `vocabs_kmm` app, follow these steps:
Disclaimer: You will need an OpenAi Api key to run the app. You can generate one here after signing up for an OpenAi account: https://platform.openai.com/account/api-keys 

1. Clone the repository to your local machine.
2. Add your open ai key as a environment variable called OPENAI_API_KEY to your computer.
3. Open the project in Android Studio.
4. Build and run the app on an Android device or emulator.

