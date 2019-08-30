/*
Jsmod2 is a java-based scpsl cn.jsmod2.server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright JavaMultiModStarterAdmin China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2;

import cn.jsmod2.core.Server;
import cn.jsmod2.core.log.ServerLogger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.io.*;
import java.util.Properties;

/**
 * 这是web服务器的启动对象,通过Springboot框架实现的
 * @author magiclu550
 */
@SpringBootApplication
@Configuration
public class Starter {

    public static ApplicationContext run(String[] args){
        return SpringApplication.run(Starter.class,args);
    }


    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer(){
        int port = 19936;
        try {
            String file = Server.class.getProtectionDomain().getCodeSource().getLocation().getFile();
            file = file.substring(0,file.lastIndexOf("/")+1);
            File appFile = new File(file+"app.properties");
            Properties properties = new Properties();
            if (!appFile.exists()) {
                properties.setProperty("http.port", "19936");
                PrintWriter writer = new PrintWriter(appFile);
                properties.store(writer,"springboot port");
                writer.close();
            }else{
                InputStream inputStream = new FileInputStream(appFile);
                properties.load(inputStream);
                port = Integer.parseInt(properties.getProperty("http.port"));
            }
        }catch (IOException e){
            ServerLogger.getLogger().error(e.getMessage());
        }
        int nowPort = port;
        return (factory)-> factory.setPort(nowPort);
    }
}
