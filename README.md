# Gallery (Demo)
### Summary

Android app that retrieves a list of images from an end point and displays then on a grid allowing 
the user to click to enlarge the image and zoom.

#### Architecture

The application uses MVVM, dependency injection, repository pattern and other principles of clean 
architecture like use cases to control the flow of information between components and keep 
dependencies between modules to a minimum.

Its composed of only one activity containing the main navigation host and 2 fragments for the views

### Libraries/Dependencies
* [Koin](https://insert-koin.io/) for dependency injection to achieve 
a decoupled more testable logic
* [Timber](https://github.com/JakeWharton/timber) for improved logging capabilities
* [Navigation Component](https://developer.android.com/guide/navigation) for navigation between
 fragments and static type safe arguments
* [Retrofit2](http://square.github.io/retrofit/) for retrieving remote data
* [Moshi](https://github.com/square/moshi) for parsing server response json objects into java classes
* [okHttp Logging Interceptor](https://github.com/square/okhttp/tree/master/okhttp-logging-interceptor)
  for intercepting and logging all request and response made using retrofit
* [Glide](https://github.com/bumptech/glide) for loading images efficiently into the views
* [ScrollGalleryView](https://github.com/VEINHORN/ScrollGalleryView) for showing image details and
enabling zooming capabilities and thumbnail previews
* [Mockito](https://github.com/mockito/mockito) for mocking classes in unit testing

### Limitations/Known issues
* As scroll view gallery library is unmaintained I had to downgrade glide library version for 
compatibility. To solve this is possible to write a custom image loader which also could include a 
custom url for thumbnails improving loading speeds. For the future local images represented now as 
strings should contain both the regular big url (_27.jpg) and the thumbnail url (_2.jpg) to optimize use in 
different parts of the app.   
 
 ### Next steps
 * Build my own image loader to be able to use latest glide library
 * Support thumbnails in the image list and image details to improve loading speeds
  
