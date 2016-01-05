StatsInterface
---
A Service Provider Interface to gain access to a wide array of Stat plugins.

This is a library for the creation & manipulation of custom stats.

The goal of this project is to make it easy for developers to track 
all kinds of stats: server stats, game stats, player stats, team stats, etc.
Anything that you could possible dream of.

On the flip side of the coin, this library also allows server admins 
to install any stat plugin they choose (or none).

Additionally, StatsInterface can be used as a guide for stat plugin developers 
to ensure their plugin provides a powerful & flexible API.

What would using StatsInteface look & feel like ? 
Full examples can be found in ```mc.euro.stats.StatsExecutor.java```.
The API is under construction, but here's a preview:

```java
Stats engine; // obtain an instance

// Register your stats:
Stat stat = StatFactory.defineStat( // create/define a Stat
                StatFactory.category(category),
                StatFactory.name(statName),
                StatFactory.type(type),
                StatFactory.context(context));
engine.registerStat(stat);

// Set the data:
Stat stat = StatFactory.get(statId);
Data data = new Data(value);
engine.setData(player, stat, data);

// Or increment the data:
Stat stat = StatFactory.get(statId);
engine.increment(player, stat, amount);

// Get the data back:
engine.getData(player, stat);
```

Right now, the Player parameter limits tracking to only ```Player Stats```. 
This needs to be refactored to some kind of StatHolder/StatOwner object...
in order allow the tracking of other types of stats: Like ```ServerStats```, 
```GameStats```, and ```TeamStats``` (etc).

Maven Repository:
---

[http://rainbowcraft.sytes.net/maven/repository/] (http://rainbowcraft.sytes.net/maven/repository/ "Maven Repository")

If you use maven, put these declarations in your pom.xml:

~~**repositories section:**~~

Check to make sure this repository is still active. If not, you will have to install the project to your local ```~/.m2/repository```

```xml
<repository>
    <id>rainbowcraft-repo</id>
    <url>http://rainbowcraft.sytes.net/maven/repository/</url>
</repository>
```

**Installation to your local ~/.m2/repository**

***git latest version:***

* ```git clone https://github.com/Europia79/StatsInterface.git```
* ```mvn clean install```

***git previous versions:***
* ```git clone https://github.com/Europia79/StatsInterface.git```
* ```git log --format=oneline```
* ```git checkout <hash>```
* ```mvn clean install```
* ```git checkout master```

***file download & mvn install:***

* Or, you can download a jar and run the ```mvn install:install-file``` command.
* This is also helpful to install any dependencies that maven can't automatically download.
  * Arguments: 
    * ```-Dfile=``` : The name & location of the jar
    * ```-DgroupId=``` : Mine is ```mc.euro```
    * ```-DartifactId=``` : If you decompile or unzip the jar, then you can find this & other information inside the folder ```META-INF/maven/{groupId}.{artifactId}/pom.properties``` & ```pom.xml```
    * ```-Dversion``` : Also found in ```pom.properties``` & ```pom.xml```
    * ```Dpackaging=jar```
    * ```DcreateChecksum=true```
  * Example: ```mvn install:install-file -Dfile="C:\Users\Nikolai\Documents\lib\BukkitInterface\2.0.1\BukkitInterface.jar" -DgroupId=mc.euro -DartifactId=BukkitInterface -Dversion=2.0.1 -Dpackaging=jar -DcreateChecksum=true```

**dependencies section:**

```xml
<dependency>
    <groupId>mc.euro</groupId>
    <artifactId>StatsInterface</artifactId>
    <version>0.1-SNAPSHOT</version>
    <scope>provided</scope>
</dependency>
```


Dependencies:
---
- **Stats2** by Lolmewn
  * http://dev.bukkit.org/bukkit-plugins/lolmewnstats/
  * https://bitbucket.org/Lolmewn/stats/src
  * https://bitbucket.org/Lolmewn/stats/wiki/Home
  * https://bitbucket.org/Lolmewn/stats/wiki/Reading%20stat%20information
  * https://bitbucket.org/Lolmewn/stats/wiki/Adding%20your%20own%20stat
- **Stats3** by Lolmewn
  * http://dev.bukkit.org/bukkit-plugins/lolmewnstats/
  * https://bitbucket.org/Lolmewn/stats/src/3be293135784f3908f820a74aea6586d810cd641/?at=3.0
  * http://ci.lolmewn.nl/job/StatsAPI/
  * http://ci.lolmewn.nl/job/StatsAPI/javadoc/
  * http://ci.lolmewn.nl/job/Stats/
  * http://ci.lolmewn.nl/job/Stats/javadoc/
- **ScoreboardStats** v0.9 by games647
  * http://dev.bukkit.org/bukkit-plugins/scoreboardstats/
  * https://github.com/games647/ScoreboardStats
  * https://github.com/games647/ScoreboardStats/wiki
- **EnjinMinecraftPlugin** v2.6.0 by Tux2
  * http://dev.bukkit.org/bukkit-plugins/emp/
- **BattleTracker** v2.5.8.10 by Alkarinv
  * http://dev.bukkit.org/bukkit-plugins/battletracker/
  * http://rainbowcraft.sytes.net/maven/repository/mc/alk/BattleTracker/
  * http://wiki.battleplugins.org/BattleTracker
  * https://github.com/BattlePlugins/BattleTracker

Contact:
---

```Nick``` at Nikolai.Kalashnikov@hotmail.com

```Nicodemis79``` on **Skype**

```Europia79``` on **IRC**

- **EsperNet**
  * ```#battledev```
  * ```#battleplugins```
  * ```#lolmewn```
- **SpigotMC**
  * ```#spigot```
