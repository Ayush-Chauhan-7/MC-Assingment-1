Journey Tracker App README

Introduction

The Journey Tracker App is designed to provide users with a dynamic and interactive way to track their journey through various stops. The app showcases the route of the journey, including all the stops, the distance between the stops, and the progress of the journey. A scrollable list ensures that an unlimited number of stops can be displayed, and a progress bar updates as the user moves to the next stop.

Key Features
1. Dynamic Route Display: Showcases a list of stops along the journey, including the start and end points.
2. Distance Tracking: Displays the distance between each stop in either kilometers or miles, which can be toggled by the user.
3. Progress Tracking: A progress bar indicates how much of the journey has been completed, updating as the journey progresses.
4. Interactive UI: Allows users to move to the next stop and switch between distance units (kilometers and miles).

How It Works:

The app's main functionality is centered around displaying a list of predefined stops in a journey, the distances between these stops, and the overall progress of the journey. This is achieved through a series of composable functions, data classes for modeling the stops and routes, and utility functions for managing the journey's progress.

Main Components

Stop: A data class representing a single stop in the journey, including its name and distance from the previous stop.
Route: A data class defining the entire route, consisting of a list of stops, and identifying the start and end points of the journey.
Progress: A data class tracking the current stop, total distance, and distance left in the journey, along with a flag for internal logic.
moveToNextStop(): A function that updates the current stop to the next one and recalculates the remaining distance.
main(): The main composable function that sets up the UI, including the scrollable list of stops and the progress bar.

UI Components

LazyColumn: Used for efficiently displaying a scrollable list of stops when the number exceeds a certain threshold.
LinearProgressIndicator: Displays the journey's progress as a bar, indicating how much of the total distance has been covered.
Buttons: Two buttons allow users to toggle the distance units and move to the next stop, dynamically updating the UI.

Implementation Details

State Management: The app uses remember and mutableStateOf for managing and updating the UI state in response to user interactions, such as moving to the next stop or toggling the distance units.
Dynamic UI Updates: Conditional rendering and dynamic modifiers are used to adjust the UI based on the current state, such as highlighting the current stop and updating the progress bar.
Accessibility and Design: Care has been taken to ensure the UI is accessible (with content descriptions for images) and visually appealing (with custom colors, padding, and fonts).

Conclusion
The Journey Tracker App is a practical example of building a dynamic and interactive UI with Jetpack Compose, showcasing the power of Kotlin and Compose for modern Android development. Through its intuitive interface, users can easily track their journey's progress, making it an excellent tool for travel and navigation apps.

