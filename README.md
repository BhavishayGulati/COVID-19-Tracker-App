# Coronavirus Tracker
Coronavirus tracker app for Android with Google News Feed.

![Android](https://img.shields.io/badge/Android%20-green)
![Kotlin](https://img.shields.io/badge/Kotlin%20v1.3.61-orange?logo=Kotlin&logoColor=white)

![image](https://user-images.githubusercontent.com/16730661/77967675-3ed2b280-72ee-11ea-8802-7b4538f59bba.png)

## Features
* __Live data__: Gets the most recent data from [JHU CRC](https://coronavirus.jhu.edu/map.html).
* __PieCharts__:
   * Total number of cases
   * Estimaded mortality rate
* __Country List__: Shows the countries with number of confirmed, deaths and recovered cases.
* __Map__: Shows recent case numbers for countries and US States.
* __News__: Shows the latest local news about COV-19 ([Google News](https://news.google.com/))
* __Localization__: Supports 17 non-English languages.

## How to Use
Build from source code
1. Clone/Download the repo.
2. Open project folder in Android Studio.
3. Add your `Google Maps Android API KEY` value in `res/values/google_maps_api.xml` ([Google Maps Documentation](https://developers.google.com/maps/documentation/android-sdk/start))
4. Choose the target.
4. Build & run!

## Contribute
Please feel free to contribute pull requests or create issues for bugs and feature requests.

## License
The app is available for personal/non-commercial use. It's not allowed to publish, distribute, or use the app in a commercial way.

## Author
Bhavishay Gulati(bhavishyagulati@gmail.com)

## Credits
### Data
 * COV-19 Data is provided by [Johns Hopkins Coronavirus Resource Center](https://coronavirus.jhu.edu).
 * News is provided by [Google News](https://news.google.com/)

### Libraries
* [gson](https://github.com/google/gson): A Java serialization/deserialization library to convert Java Objects into JSON and back
* [MPAndroidChart](https://github.com/PhilJay/MPAndroidChart): A powerful 🚀 Android chart view / graph view library
* [opengraph-java](https://github.com/johndeverall/opengraph-java): A Java object representation of the Open Graph protocol for a web page
* [Picasso](https://square.github.io/picasso/): A powerful image downloading and caching library for Android
* [OkHttp](https://square.github.io/okhttp/): Square’s meticulous HTTP client for Java and Kotlin. 
