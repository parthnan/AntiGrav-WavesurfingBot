# Anti Gravity + Wavesurfing Bot (used in Robocode tournaments)
[![HitCount](http://hits.dwyl.com/parthnan/AntiGrav-WavesurfingBot.svg)](http://hits.dwyl.com/parthnan/AntiGrav-WavesurfingBot)

Project to create a competitive Robot team of 3 Robots using Java for Robocode(https://robocode.sourceforge.io/) to fight in a 3 v 3 v 3 tournament. Free For All matches with a 3 bot team, opposing 3 bot team, and a standard "Walls.bot" team. This bot team won the tournament of decisively, with 13 victories and 0 losses, while also being a lightweight bot(<25kB). 

# Summary of the strategy coded in the robot team
The bot uses two main strategies called AntiGravity(反重力)[link 1 below] for unpredictable (complex pattern) movement and Wavesurfing[link 3 below] for finding targets from opposing teams regardless of angular orientation(like a 360 degree Radar). 
A third and easy-to implement Ramming stategy, or physically colliding with opponents also used for attacking. 

Technical details for these three strategies and their implementation is in the two files: PRESENTATION and DETAILREPORT (in Japanese, tournament held in Japan.).

![alt text](https://raw.githubusercontent.com/parthnan/AntiGrav-WavesurfingBot/master/ExampleSurfing.png)

# Basic Reading on the strategies
[1] Secrets from the Robocode masters, Anti-gravity movement: URL: https://www.ibm.com/developerworks/library/j-antigrav/index.html

[2] Secrets from the Robocode masters, Circular targeting: URL:https://www.ibm.com/developerworks/library/j-circular/index.html

[3] Wavesurfing radar algorithm for robocode robots: URL:https://robowiki.net/wiki/Wave_Surfing_Tutorial


# Create your own instance of this robot team code
1. Download the Robocode and Install.
2. Download and place the folder "group08" inside the folder "robocode/robots".  
3. Upon running Robocode, on the top left choose "Battle->New". In the list of packages choose "group08" -> "Team_group08_2018" to get a team of three OR select G08_Leader to get a single robot under "Robots".
4. Choose the opposing combatant robots as in (3). 
5. Move to the Rules tab and editthe number of rounds, size of battlefield etc , and then start the battle.

# Tournament Results
Round Robin Tournament where each match consisted of a 100 battles, victory based on a complex points system(counting survival, number of misses, net damage output etc.). 

My team, won every single matchup with a good margin. 

![alt text](https://raw.githubusercontent.com/parthnan/AntiGrav-WavesurfingBot/master/winall.png)

 

