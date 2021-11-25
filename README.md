# Ticket Viewer

## Instructions

1. Clone this repository. This requires Java JDK to be installed.
2. Now add your api along with username and password to ```src/main/resources/credentials.properties```.
   1. The first line must contain the API.
   2. Second line should have the username.
   3. Third line will contain the password.
   4. An example is as follows.
```
https://<domain>.zendesk.com/api/v2/tickets
<username>
<password>
```
3. Open a terminal in the cloned folder where the gradle wrapper script (```gradlew```) is located.
4. Run ```./gradlew clean build run``` for MacOS and linux and ```./gradlew.bat clean build run``` on Windows.
5. The application should start and just follow on-screen instructions.