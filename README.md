# Anti Gravity + Wavesurfing Bot (used in Robocode tournaments)
Project to create a competitive Robot team of 3 Robots using Java in the software "Robocode"(https://robocode.sourceforge.io/) to fight in a 3 v 3 v 3 tournament. Each match was a 100 rounds with 3 bots from the above code, 3 bots from the opposing programmers, and 3 standard built-in bots known as "Walls.bot" . This bot team won the tournament of decisively, with 13 victories and 0 losses, while also being a lightweight bot(<25kB). 

# Summary of the bots
The bot uses two main strategies called AntiGravity(反重力)[link 1] for unpredictable (complex pattern) movement and Wavesurfing for finding targets from opposing teams regardless of angular orientation(like a 360 degree Radar). 
A third and easy-to implement Ramming stategy, or physically colliding with opponents also used for attacking. 

The details for these three strategies and their implementation is in the two files : PRESENTATION and DETAILREPORT (in Japanese, tournament held in Japan.).

# How to Use
1. Download the Robocode and Install.
2. Download and place the folder "group08" inside the folder "robocode/robots".  
3. Upon running Robocode, on the top left choose "Battle->New". In the list of packages choose "group08" -> "Team_group08_2018" to get a team of three OR select G08_Leader to get a single robot under "Robots".
4. Choose the opposing combatant robots as in (3). 
5. Move to the Rules tab and editthe number of rounds, size of battlefield etc , and then start the battle.

# Tournament Results
Round Robin Tournament where each match consisted of a 100 battles, victory based on a complex points system(counting survival, number of misses, net damage output etc.). 

My team, won every single matchup with a good margin. 

![alt text](https://raw.githubusercontent.com/parthnan/AntiGrav-WavesurfingBot/master/winall.png)


# Basic Reading on the strategies
[1] Secrets from the Robocode masters, Anti-gravity movement: URL: https://www.ibm.com/developerworks/library/j-antigrav/index.html

[2] Secrets from the Robocode masters, Circular targeting: URL:https://www.ibm.com/developerworks/library/j-circular/index.html
