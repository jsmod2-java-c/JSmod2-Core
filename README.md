![LICENSE](https://img.shields.io/badge/license-GPL-blue.svg)
![BANNER](6DC1237F3087F7B13213246693E6B81E.jpg)
# JSMOD2
###### smod2 java edition [based on smod2]
> about

`Java server mod2` is a java extension 
server developed based on `smod2` and proxy 
handler (`ProxyHandler`). 

The role of this server is to extend the 
language of SCPSL so that Java can also 
develop SCP plugins.

At the same time, `jsmod2` is a java server 
development framework, so it provides a lot of `APIs`,
so that everyone can use `jsmod2` to develop plugins.

`Jsmod2` must be combined with `smod2`, and ensure that `smod2`
installs the agent. In the future, `jsmod2` will support a 
`jsmod2` to connect multiple `smod2` to form a central cluster.

Welcome everyone to join the `jsmod2` team, 
currently `jsmod2` is in the development stage, 
I hope everyone can contribute code

If you want to participate in the development of jsmod2,
you can refer to README_design.md for architectural 
information.

![avatar](github_info/jsmod2-banner.png)
> How to start JDMOD2
* windows and linux

`java -jar jsmod2.jar`

* JRE

use jre 8:
https://www.oracle.com/technetwork/java/javase/downloads/index.html

* use `jsmod2`

First configure SCPDataNetwork to Smod2 (as a plugin), then start smod2 first, 
then start jsmod2. If it is the default ip and port, make sure they run on a server.


> Develop plugin
1. First you need to create a plugin.yml
```yaml

name: pluginName
main: Package.MainClass
description: description
version: plugin-version

```
2 . Create a jsmod2 main class, and the main class of plugin.yml...
```java
package Package;
/**
* This is the main class
*/
public class MainClass extends PluginBase{
    
        public void onLoad(){
            //The server is officially started before it can be registered.
        }
    
        public void onEnable(){
            //The contents of the server startup, can be registered, initialized, etc.
    
        }
    
        public void onDisable(){
            //Running content when the server is shut down
        }
} 

```
3  . Create a listener
```java
package Package;

public class TestListener implements Listener{
    //EventManager has a listener priority, see the note
    @EventManager
    public void JoinEvent(PlayerJoinEvent e){
        //Trigger corresponding event will run
    }
    
}
```
4 . Create a Command
```java
package Package;

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
5 . Register Command and Listener
```java
package Package;
/**
* This is the main class
*/
public class MainClass extends PluginBase{
    
        public void onLoad(){
            //The server is officially started before it can be registered.
        }
    
        public void onEnable(){
            //The contents of the server startup, can be registered, initialized, etc.
            this.getServer().getPluginManager().registerEvents(new TestListener(),this);
            this.getServer().getPluginManager().registerCommand(new TestCommand(this));
        }
    
        public void onDisable(){
            //Running content when the server is shut down
        }
} 

```
6. New Api can register the listener and command of jsmod2's own accord；

```java
package Package;


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
