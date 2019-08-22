package cn.jsmod2;

import cn.jsmod2.core.Server;
import cn.jsmod2.core.log.ServerLogger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.*;
import java.util.Properties;

@SpringBootApplication
@Configuration
@ComponentScan("cn")
public class Starter {

    public static void run(String[] args){
        SpringApplication.run(Starter.class,args);
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
