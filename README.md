# Android Architecture Components Sample

A Sample project that uses ViewModel, LiveData, Room, Retrofit, Navigation, Dagger2 with an MVVM architecture.  
The app uses the public github api for example data.  
This application contains a working example of Network, Database, Memory Cache and Repository layer init with Dagger2.
Coroutine examples. Suspended functions in Network, Database and Repository layer.  
On boarding flow example. (Just an Intro screen which is only available after first start).
Splash Screen example.  
Managers like: LoadingManager, NavigationManager, DialogManager.  
The project contains some unused components(broadcast receiver, worker, notification etc.) and resources (anim, vector drawables etc.) for only the sake of example.
Singing config example in gradle, sensitive and build related info stored in local.properties. 

# Build info

If you would like to build the app then you need to specify a few thing in your local.properties.  
baseUrl = https://api.github.com/  
databaseName = AppDatabase1 // Or whatever you want.  
sampleKeyAlias = some_key // Create a jks file and use its key alias and password.  
sampleKeyPassword = some_password  
apiSecret = sample_api_secret // This is not used yet, but if you would like to implement an OAuth2 authentication with some api key, this is a good place to store it.  

You can add your jks file location in gradle in the signing config section at the "storeFile" property. Current state: projectDir/keys/basic, where basic is the name of the jks file.

### Screens
 - SplashScreen
 - Intro (appears only after first start)
 - RepoList (List of github repositories, recycler view example with diff util support)
 - RepoDetail (Detailed info of a selected repo, example for data pass between Fragments)
 - Settings (Example for a simple settings screen, options saved into shared preferences)

### Used libraries and tools
- Dagger2
- LiveData
- ViewModel
- Room
- Retrofit2
- Navigation
- EventBus
- Glide
- Coroutines
- Work
- KTX
