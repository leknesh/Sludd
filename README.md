# Sludd Weather App

Sludd is a weather application built using Kotlin and Jetpack Compose. It fetches current weather data from the Open-Meteo API and displays it in a user-friendly interface.

## Features

- Fetches current weather data based on the user's location.
- Displays temperature, humidity, wind speed, and weather description.
- Uses a clean and modern UI with Jetpack Compose.

## Technologies Used

- Kotlin
- Jetpack Compose
- Retrofit
- OkHttp
- Gson
- Koin
- Android Studio

## Getting Started

### Prerequisites

- Android Studio
- Android device or emulator with internet access

### Installation

1. Clone the repository:
    ```sh
    git clone https://github.com/leknesh/sludd-weather-app.git
    ```
2. Open the project in Android Studio.
3. Create a `local.properties` file in the root directory of the project and add your API key:
    ```properties
    MAPS_API_KEY=your_api_key_here
    ```
   You can get your Google Maps API key from the [Google Cloud Console](https://console.cloud.google.com/).

4. Build and run the app on an Android device or emulator.

### Troubleshooting
Make sure your API key has the `Maps SDK for Android` enabled.
In case of issues with the API key setup, a version of the app without search functionality is available in the branch `Sludd_103_forecast`.

## Usage

1. Grant location permissions when prompted.
2. The app will automatically fetch and display the current weather data for your location.

## API

This app uses the [Open-Meteo API](https://open-meteo.com/) to fetch weather data.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgements

- [Open-Meteo API](https://open-meteo.com/)
- [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [Retrofit](https://square.github.io/retrofit/)
- [OkHttp](https://square.github.io/okhttp/)
- [Gson](https://github.com/google/gson)
- [Koin](https://insert-koin.io/)