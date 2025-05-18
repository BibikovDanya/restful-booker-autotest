## Restful-booker-autotest
[Restful-booker API](https://restful-booker.herokuapp.com) - is an API playground created by Mark Winteringham for those wanting to learn more about API testing and tools.

This project is designed to familiarize you with java, kotlin and REST Assured. 

## Technology Stack
- [Kotlin](https://kotlinlang.org)
- [Rest-Assured](https://rest-assured.io)
- [JUnit 5](https://junit.org/junit5/)
- [Testcontainers](https://testcontainers.com)

## Requirements
- JDK 21 or higher
- Gradle 7.x or higher
- Docker
## Installation and customization
1. Clone the repo `git clone https://github.com/BibikovDanya/restful-booker-autotest.git`
2. Preparing a local Docker image of restful-booker.
   Since the restful-booker project cannot be published as a Docker image, 
   it is necessary to pre-build a local Docker image of the restful-booker application.
      ```bash 
      git clone https://github.com/mwinteringham/restful-booker.git
      cd restful-booker
      docker-compose build
      ```
3. Navigate into the restful-booker-autotest root folder
4. Running tests
    ```
    ./gradlew clean allTest --info
    ./gradlew clean allStableTest --info
    ./gradlew clean bookTest --info
    ./gradlew clean authTest --info
    ./gradlew clean securityTest --info
    ```