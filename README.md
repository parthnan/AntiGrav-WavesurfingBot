# Anti Gravity + Wavesurfing Bot (used in Robocode tournaments)
Project to create a competitive Robot team of 3 Robots using Java in the software "Robocode"(https://robocode.sourceforge.io/) to fight in a 3 v 3 v 3 tournament. Each match was a 100 rounds with 3 bots from the above code, 3 bots from the opposing programmers, and 3 standard built-in bots known as "Walls.bot" . The team of bots coded above won the tournament of 14 teams decisively, with 13 victories and 0 losses. Tournament resuts Discusses below.

# Summary of the bots
The bot uses two main strategies called AntiGravity(反重力)[link 1] for unpredictable (complex pattern, and yet efficient) movement and Wavesurfing for finding targets from opposing teams regardless of angular orientationlike a 360 degree Radar). 
A third and easy-to implement strategy is also used under certian conditions, known as Ramming, or physically colliding with opponents. Ramming works best the more robots there are on the field.

The details for these three strategies and their implementation is explained thoroughly in the two files : PRESENTATION and DETAILREPORT (in Japanese, tournament held in Japan.).

# How to Use
1. Download the folder "group08" and the Robocode software from the site. Install Robocode.
2. Place the folder "group08" inside the folder "robocode/robots".  
3. Upon running Robocode, on the top left choose "Battle->New". Inside the list of packages choose "group08", and select "Team_group08_2018" to get a team of three OR select G08_Leader to get a single robot under "Robots".
4. Choose the opposing combatant robots similarly as in (3). 
5. Move to the Rules tab and editthe number of rounds, size of battlefield etc , and then start the battle.

# Tournament Results
The tournament, held in Osaka University, had 14 registered teams, and fought Round Robin with every other team. Each match consisted of a 100 battles(quick runs), woth victory based on a complex points system(not just survival, but number of misses, net damage output etc. all count). 

The results are as below. My team, number 8, won every single matchup with a good margin. During the tournament runs that were played for showcasing, the complex pattern of movement as well as the aggressive aiming(Wavesurfing) of our bots was evident.

![alt text](https://raw.githubusercontent.com/parthnan/AntiGrav-WavesurfingBot/master/images/winall.png)


# Basic Reading on the strategies
[1] Secrets from the Robocode masters, Anti-gravity movement: URL: https://www.ibm.com/developerworks/library/j-antigrav/index.html

[2] Secrets from the Robocode masters, Circular targeting: URL:https://www.ibm.com/developerworks/library/j-circular/index.html
