# Ticket Viewer

## Instructions

### How to run the application

1. Clone this repository. This requires Java JDK to be installed. Check if JAVA_HOME environment variable is set correctly before proceeding.
2. Now add your tickets api along with username and password to ```src/main/resources/credentials.properties```.
   1. The first line must contain the API that points to the tickets api for your account.
   2. Second line should have the username.
   3. Third line will contain the password.
   4. An example is as follows.
```
https://<domain>.zendesk.com/api/v2/tickets
<username>
<password>
```
3. Open a terminal in the cloned folder where the gradle wrapper script (```gradlew```) is located.
4. Run ```./gradlew clean build run --console=plain``` for MacOS and linux and ```.\gradlew.bat clean build run --console=plain``` on Windows.
5. The application should start and just follow on-screen instructions.
6. It is recommended to use a terminal which is atleast 90 characters wide to have a good viewing experience.

### How to run the tests

#### Unit tests

1. ```./gradlew test --tests "com.zccadwait.connection.ConnectionTest"```
2. ```./gradlew test --tests "com.zccadwait.credentials.EndpointReaderTest"```
3. ```./gradlew test --tests "com.zccadwait.model.TicketListTest"```
4. ```./gradlew test --tests "com.zccadwait.model.TicketTest"```

#### Integration tests

Integration tests are not implemented due to the restriction in time.
