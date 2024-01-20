# MoodMate Application Proposal 

## Application Proposal and Specification:

My proposed app is a personal utility application that allows users to track their daily mood and well-being. The app will provide a platform for users to log their moods, feelings, and emotions, and help them understand their mental health patterns over time. This will be done by the user logging their mood on a scale of 1-5 and having the option to add notes. 

For each day the user’s mood rating and notes will be stored in a database so that they can be accessed again from the home screen. Firebase will be used to store and retrieve data.

The app will feature a simple and intuitive user interface that will enable users to easily input their daily mood with a few taps on their smartphone screen. The main screen will display a recycler view that allows users to see their recent moods. The user will be able to click on a specific day and look back on their mood and any notes they made for that specific day.

A Wellbeing page will display a range of ways the user could improve their mental well-being, including meditation, deep breathing, and exercise. This will work well with the app as the target audience is people who are looking to keep track of and improve their mental well-being. The user will be able to go through each one by swiping to the right, displayed in a random order.

A reminder notification will be sent out at 8 P.M. every day, reminding the user to log their mood for that day. It will try to keep the user engaged each day and will hopefully embed the use of the app in the user’s daily routine. This operation will be performed by an AlarmManager class.

The app will also allow the user to share their recent mood scores on certain social media such as Twitter. This will be done by a ShareActionProvider which can be found in the details screen which is accessed by clicking on a specific mood from the home page.

With this design, it’s easy to incorporate extra functionality as required.

## Mock Screens

##### Design Overview:

![image](https://github.com/mattfuller5/Mood_Tracker_App/assets/45941691/9b32767d-9d2e-4168-a506-d5128ce5c5be)

##### Home Screen:

![image](https://github.com/mattfuller5/Mood_Tracker_App/assets/45941691/6ed597d4-9f0b-4fb5-9e9e-b1beeef22c11)

•	Displays the recent moods in a recycler view displaying the Mood rating and the date it was submitted.

•	Allows users to tap on a specific day that has been inputted in the past and take them to the Details screen.

•	Buttons in the bottom left and right of the screen allow the user to access the Well-Being screen and Today’s Mood screen respectively.

#### Today's Mood:

![image](https://github.com/mattfuller5/Mood_Tracker_App/assets/45941691/69ee9b50-4781-4eeb-8141-1002f78424b3)

•	User can select a mood level from 1-5 which will highlight that icon.

•	User can add notes in the notes section which when tapped will bring up the system keyboard.

•	Users can tap on the share icon bottom left which will bring the user to the Share screen pop-up.

•	Tapping the bottom right tick will confirm and save the user's mood for that day and return them to the Home screen. These can also be edited after.

•	The Mood will only upload if all inputs are filled and will show a pop-up when the Mood successfully uploads.

#### Details:

![image](https://github.com/mattfuller5/Mood_Tracker_App/assets/45941691/e7f7c1d4-d921-4c22-b484-2b322b1b1a30)

•	Screen is accessed from the main Home screen by clicking on one of the past moods.

•	The screen is similar to the Today’s Mood screen but cannot be edited.

•	Tapping the top right back button will return the user to the Home screen.

•	Pressing the share button will take you to the ShareActionProvider Screen Share.

#### Share:

![image](https://github.com/mattfuller5/Mood_Tracker_App/assets/45941691/8cd1a340-259c-4b4f-8f44-74ee3b436a9b)

•	The screen will be accessed from the Details screen and allow the user to share their mood today or past mood on other apps.

•	Tapping on the greyed-out area of the screen will bring the user back to the Details screen.

•	Tapping on either of the social media will open up the apps with a predetermined post and will allow the user to post to the chosen app.

#### Well-being:

![image](https://github.com/mattfuller5/Mood_Tracker_App/assets/45941691/e79d2c71-7802-4683-b68b-de58b8db746d)

•	The screen can be accessed using the bottom navigation bar from either the Today’s Mood screen or the Home screen.

•	The screen displays information about well-being, to benefit the user.

•	The user can swipe right on the well-being suggestion and it will display a new suggestion in a random order.





