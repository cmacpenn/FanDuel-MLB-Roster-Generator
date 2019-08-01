# FanDuel MLB Roster Generator
Fantasy baseball is a contest in which entrants create a team lineup from the list of players who are playing in one of the MLB games on that day.  Each player has a 'salary' - an associated cost - and entrants must choose their team while spending at or below $35,000.  After the MLB games have concluded, each player is assigned a number of fantasy points based on their performance during the game.  The entrant whose fantasy team has the highest fantasy point total wins.  

This program suggests a roster of players likely to score well for a given day.  Users will download the players list for the day from FanDuel and upload it to the program.  The players list contains the list of players for the day, as well as their FanDuel-assigned salaries.  The program will then use historical data to suggest a player lineup that is likely to score well.

## Getting Started

### Downloading FanDuel player files
The GitHub repository has several historical player lists from FanDuel in the input folder, but you can download the newest versions from FanDuel.  The following steps will guide you through downloading the file.
1. Create a free FanDuel account at www.fanduel.com

2. Log in and navigate to the MLB tab
<img src="./images/FanDuel MLB Tab.png" width="1000">

3. Select any of the MLB games.
<img src="./images/FanDuel Game.png" width="1000">

4. Click the 'Enter Lineup' button at the bottom of the screen
<img src="./images/FanDuel Enter Lineup.png" width="1000">

5. Download the player list
<img src="./images/FanDuel Download Player List.png" width="1000">

### Adding the H2 database library
This program depends on the Java H2 database library.  The H2 JAR file is located at library/h2-1.4.199.jar.  You will need to add this to your build path in order for the program to work.

## Authors

* **Colin McLaughlin**
* **Kenton Van**