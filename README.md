# JSMod2    
![avatar](github_info/jsmod2-banner.png)
![LICENSE](https://img.shields.io/badge/license-GPL-blue.svg)

> Copyright: JSmod2 LICENSE 

```java
/*
JSmod2 is a java-based SCP: Secret Laboratory server initiated by jsmod2.cn.
It needs to rely on Smod2 and the ProxyHandler. JSmod2 is an open source
free software that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright JSmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
```



## About Us

Hi there! 

We are JSmod2 developing team. This project is pure, public....and in an sense, it's welfare. ;)
Most of us are high school students, we don't have enough time to find and fix all of the bugs,
please forget it and create an issue :P Feel free to point out any omissions, thank you!
If you are willing to help this project, there are three ways that you can do:

1. Send an email to: 843983728@qq.com (Advice, questions, anyway.)
2. Create an issue!
3. Join our Discord Server and......: [Hey, tap here! :P](https://discord.gg/Qjzvb2a)

Thanks for your supporting!



## What is JSmod2?

JSmod2 is a java extension server.
It's based on Smod2 and [ProxyHandler](https://github.com/jsmod2-java-c/ProxyHandler)

The aim of JSmod2 is to make Java developers develop in Java easily.

Also, it provides APIs that most of them are the same as Smod2.

JSmod2 needs Smod2, and you need to install ProxyHandler correctly 
(Grab it into \sm_plugins). In the future, JSmod2 will support 
connecting multiple Smod2 servers.

## How's the project now?

Currently JSmod2 is still developing,
issues, prs, criticsim and suggestions are welcomed!

If you want to participate in the development of JSmod2,
you can refer to [JSmod2-design](https://github.com/jsmod2-java-c/jsmod2-design) for further information.



## How to get start it?

### Before you do it, please note that:

* We recommend you to set up on Windows 2008 - the latest / Ubuntu Server 18.04.2 LTS

* If you want to install automatically, Python and Pip are required




> Auto-install JSmod2:

* Download: [release](https://github.com/jsmod2-java-c/JSmod2-Core/releases)

If there's no problem, please enter the following text in the terminal:

`cd /path/to/jsmod2 & python3 -m pip install requirement.txt`

And launch:

`cd /path/to/jsmod2 & python3 -m jsmod2manager.py`




> Manual install JSmod2:

* Download: [The latest release](https://github.com/jsmod2-java-c/JSmod2-Core/releases)

* [Java](https://www.oracle.com/technetwork/java/javase/downloads/index.html)

* [ProxyHandler](https://github.com/jsmod2-java-c/JSMod2-ProxyHandler)

After finished installing JRE, you need to put ProxyHandler.dll into 
(Where your server is)\sm_plugins, then start LocalAdmin.exe / MultiAdmin.exe.
If there's no problem, then launch JSmod2.jar:
`java -jar jsmod2.jar`




> Import the DevelopmentKit

Connect Repository

```xml
<repositories>
        <repository>
            <id>nexus</id>
            <name>Team Neux Repository</name><url>http://repo.noyark.net/nexus/content/groups/public/</url>
        </repository>
    </repositories>
```
Import Dependency
```xml
        <dependency>
            <groupId>cn.jsmod2</groupId>
            <artifactId>jsmod2-dk</artifactId>
            <version>1.0.3</version>
        </dependency>
```


> How to develop a plugin?

1. New API can register the Listeners and Commands of JSmod2's；

```java
package com.magiclu.plugin;

import cn.jsmod2.core.annotations.EnableRegister;
import cn.jsmod2.core.annotations.Main;
import cn.jsmod2.core.plugin.PluginBase;

@Main(name="exmaple")

@EnableRegister
public class Main extends PluginBase{
    public void onLoad(){
    }
    public void onEnable(){
    }
    public void onDisable(){
    }
}
```

2. Create a Command:


```java
package com.magiclu.plugin.command;

import cn.jsmod2.api.player.IPlayer;
import cn.jsmod2.core.CommandSender;
import cn.jsmod2.core.command.Command;
import cn.jsmod2.core.plugin.Plugin;

public class TestCommand extends Command{
    
   
    public TestCommand(Plugin plugin){
        super("test","CONSOLE", "hello,jsmod2",plugin);
    }
    //override the method
    public boolean execute(CommandSender commandSender, String[] args){
        //Processing when executing instructions
    }
}

```

3. Create a Listener:


```java
package com.magiclu.plugin.listener;

import cn.jsmod2.api.event.player.IPlayerJoinEvent;
import cn.jsmod2.core.annotations.EventManager;
import cn.jsmod2.core.event.Listener;


public class TestListener implements Listener{
    //EventManager has a listener priority, see the note
    @EventManager
    public void joinEvent(PlayerJoinEvent e){
        //Trigger corresponding event will run
    }
    
}
```
