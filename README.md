###### Note that we still don't know who the pic's author is. If you know or you are, please contact [@MagicLu550](https://github.com/MagicLu550).
![BANNER](6DC1237F3087F7B13213246693E6B81E.jpg)
# JSMod2    ![LICENSE](https://img.shields.io/badge/license-GPL-blue.svg)
###### Smod2 Java Edition [based on Smod2]

> 严正声明:Strict statement

我们维护中华人民共和国主权，台湾，香港，澳门是中华人民共和国的一部分，反对港独暴动和台独分子，坚决打击分裂分子
禁止在issues区,discord区,pr区,发布反国家和平言论,禁止项目贡献过程中,commit信息带有分裂，煽动分裂的字样
如果发现，将以法律或者道德上驱逐

We safeguard the sovereignty of the People’s Republic of China. Taiwan, Hong Kong and Macao are part of the People’s Republic of China. They oppose the riots of the Hong Kong independence and Taiwan independence elements and resolutely crack down on separatists
It is forbidden to issue anti-state peace speech in the issues area, discord area, and pr area, prohibiting the project contribution process, the commit information has the split and incitement to split the words.
If found, will be expelled legally or morally

私たちは中華人民共和国の主権を支持します。台湾、香港、マカオは中華人民共和国の一部です。彼らは香港の独立と台湾の独立の要素の暴動に反対します。 コントリビューションの過程で、コミット情報には分割があり、分割を引き起こす単語は、見つかった場合は法的にまたは道徳的に追放されます。

우리는 중화 인민 공화국의 주권을지지합니다 대만, 홍콩, 마카오는 중화 인민 공화국의 일부이며 홍콩의 독립과 대만 독립 요소의 폭동에 반대하며, 분리 주의자들의 반 국가 평화 발언 금지, 반 국가 평화 발언 금지, 지구 문제에 대한 프로젝트 금지, 지구 문제에 대한 분쟁을 단호히 단속하고 있습니다. 기여 과정에서 커밋 정보는 분리되어 있으며 분리를 촉구하는 단어는 발견되면 합법적 또는 도덕적으로 추방됩니다.

> Copyright: JSmod2 LICENSE 
```java
/*
JSmod2 is a java-based SCP: Secret Laboratory server initiated by jsmod2.cn.
It needs to rely on Smod2 and the ProxyHandler. JSmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright JSmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
```

README edited by YorokobiMaster And MagicLu

> About JSmod2

JSmod2 needs the following dependencies:

* [JSmod2 Protocol](https://github.com/jsmod2-java-c/Jsmod2_protocol.git)

* [Smod2](https://github.com/Grover-c13/Smod2)

`JSmod2` is a java extension server 
developed based on `Smod2` and [ProxyHandler](https://github.com/jsmod2-java-c/ProxyHandler)

The role of JSmod2 is to extend the language of SCPSL
so that Java can also develop SCPSL plugins.

Also, `JSmod2` is a Java server 
development framework, by the time it provides a lot of `APIs`,
everyone can use `JSmod2` to develop plugins.

`JSmod2` needs `Smod2`, and you need to install `ProxyHandler` 
correctly(Grab it into \sm_plugins). In the future, `JSmod2` will support 
connecting multiple `Smod2` servers to form a central cluster.

Welcome everyone to join us, 
currently `JSmod2` is still in the development stage, 
Founder [@MagicLu550](https://github.com/MagicLu550) hopes everyone can contribute
by sending issues ,Prs and suggestions.

If you want to participate in the development of JSmod2,
you can refer to [JSmod2-design](https://github.com/jsmod2-java-c/jsmod2-design) for further information.

![avatar](github_info/jsmod2-banner.png)

> Discord

Welcome to our [discord](https://discord.gg/Qjzvb2a)

> How to start?
* We recommended you to set up in Windows Server 2008-2016 / Ubuntu Server 18.04.2 LTS

* [Environment](https://www.oracle.com/technetwork/java/javase/downloads/index.html)
* [ProxyHandler](https://github.com/jsmod2-java-c/JSMod2-ProxyHandler)

After finished installing JRE, you need to put ProxyHandler.dll into 
(Where your server is)\sm_plugins, then start LocalAdmin.exe / MultiAdmin.exe.
If there's no problem, launch JSmod2.jar:
`java -jar jsmod2.jar`

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
    public void JoinEvent(PlayerJoinEvent e){
        //Trigger corresponding event will run
    }
    
}
```
> Old Method

this method can also create a plugin:

1. First you need to create a plugin.yml 

[@Deprecated]
`now you can not create this file for the plugin,and use @Main instead`
```yaml

name: pluginName
main: Package.MainClass
description: Description of the plugin.
version: The version of the plugin.

```
2. Create JSmod2 main class, and the main class you had been set in plugin.yml:
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


3.Register the Command and Listener:

[@Deprecated]
`now you can not register the listener and command,use @EnableRegister instead`
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

