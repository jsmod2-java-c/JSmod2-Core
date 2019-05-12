package net.noyark.scpslserver.jsmod2;

import net.noyark.scpslserver.jsmod2.inferf.log.ILogger;
import net.noyark.scpslserver.jsmod2.utils.Utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * FileSystem create the server file
 *
 * @author magiclu550 #(code) jsmod2
 */

public class FileSystem {

    private static List<String> registerLang = new ArrayList<>();


    static {
        registerLang = new ArrayList<>();
        registerLang();
    }

    public static final String PROPERTIES = ".properties";

    /**
     * the server.properties
     */

    public static void ServerProperties(){

    }

    public static Properties LangProperties(ILogger log) throws IOException {
        File file = new File("../init.lang");

        Properties properties = new Properties();


        if(!file.exists()){
            PrintWriter writer = new PrintWriter(file);

            properties.load(Utils.getClassStream("lang.properties"));
            for(String lang:registerLang){
                log.info(properties.getProperty(lang));
            }
            String langType = Server.getScanner().nextLine();
            try{
                properties.load(Server.class.getClassLoader().getResourceAsStream(langType+PROPERTIES));
                writer.println(langType);
            }catch (Exception e1){
                log.error("sorry,no such language,default: chinese");
                log.error("不好意思，没有这样的语言，默认为:中文");
                try{
                    properties.load(FileSystem.class.getClassLoader().getResourceAsStream("zh"+PROPERTIES));
                }catch (Exception e2){
                    e2.printStackTrace();
                }
                writer.println("zh");
            }
            writer.close();
        }else{
            BufferedReader reader = Utils.getReader(file);
            String lang = reader.readLine();
            properties.load(Server.class.getClassLoader().getResourceAsStream(lang+PROPERTIES));
        }
        return properties;
    }

    public static void registerLang(){
        registerLang.add("zh");
        registerLang.add("en");
    }
}
