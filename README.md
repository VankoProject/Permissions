# Permissions
This is a simple training application for Android that demonstrates how to handle permissions for camera, location, and audio recording. 
The app utilizes various approaches to handle these permissions, including granting, denying, and permanently denying them. 
It also demonstrates two different methods of working with permissions: the standard approach using request codes and the newer Activity Result API.

Features
- Grant, deny, and permanently deny permissions for camera, location, and audio recording.
- Handle permission requests using both the traditional request code method and the Activity Result API.
- Display informative messages and user prompts related to permissions.
- 
Prerequisites
- Android device running Android 6.0 (Marshmallow) or higher.
- Android Studio IDE (latest version recommended).
- Basic understanding of Android development and permissions.
  
Getting Started
- Clone or download the project from the repository: [https://github.com/VankoProject/Permissions].
- Open the project in Android Studio.
- Build and run the app on your Android device or emulator.

Usage
- Launch the app on your device.
- The app's main screen will display buttons for camera, location, and audio permissions.
- Press the buttons to simulate different permission scenarios:
Grant permission: The app will request the corresponding permission and display a success message upon granting.
Deny permission: The app will request the corresponding permission and display a failure message upon denial.
Permanently deny permission: The app will simulate a permanent denial, showing a prompt to guide the user to manually enable the permission in the device settings.
The app demonstrates two approaches to handling permissions:
Traditional approach using request codes: The app uses requestPermissions() to request permissions and handles the results in onRequestPermissionsResult().
Activity Result API approach: The app uses registerForActivityResult() and ActivityResultCallback to request permissions and handle the results.
Observe the different behaviors and messages displayed by the app based on the permission handling approach and user responses.

Additional Notes
- The app's code includes comments and explanations to help understand the implementation of permission handling.
- The app targets Android API level 23 (Marshmallow) as the minimum supported version.
- Make sure to review and understand the permissions requested in the app's manifest file (AndroidManifest.xml).
