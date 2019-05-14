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

    public static final String PLUGIN_DIR = "/plugins";

    public static final String SERVER_PROPERTIES = "/server.properties";




    private List<OutputStream> outputStreams = new ArrayList<>();

    private List<InputStream> inputStreams = new ArrayList<>();

    private List<BufferedReader> readers = new ArrayList<>();

    private List<PrintWriter> writers = new ArrayList<>();


    private static FileSystem system;

    static {
        system = new FileSystem();
    }

    static {
        Register.getInstance().registerLang();
    }

    private FileSystem(){

    }

    public static final String PROPERTIES = ".properties";

    /**
     * the server.properties
     */

    public Properties serverProperties(Server server){
        try{
            Properties properties = new Properties();
            File serverProp = new File(server.serverfolder+SERVER_PROPERTIES);
            if(!serverProp.exists()){
                FileOutputStream stream = new FileOutputStream(serverProp);
                outputStreams.add(stream);
                properties.setProperty("port","19938");
                properties.setProperty("decode","utf-8");
                properties.setProperty("encode","utf-8");
                properties.store(stream,"this is the server's properties");
                stream.flush();
                properties.load(new FileInputStream(serverProp));
                return properties;
            }else{
                properties.load(new FileInputStream(serverProp));
                return properties;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * plugin dir
     */
    public File pluginDir(Server server){
        File file = new File(server.serverfolder+PLUGIN_DIR);
        if(!file.exists()) {
            file.mkdirs();
        }
        return file;
    }


    public Properties langProperties(ILogger log) throws IOException {
        File file = new File("../init.lang");

        Properties properties = new Properties();

        if(!file.exists()){
            PrintWriter writer = new PrintWriter(file);

            properties.load(Utils.getClassStream("lang.properties"));
            for(String lang:Register.getInstance().getRegisterLang()){
                log.info(properties.getProperty(lang));
            }
            String langType = Server.getScanner().nextLine();
            try{
                properties.load(Utils.getClassStream(langType+PROPERTIES));
                writer.println(langType);
            }catch (Exception e1){
                log.error("sorry,no such language,default: chinese");
                log.error("不好意思，没有这样的语言，默认为:中文");
                try{
                    properties.load(Utils.getClassStream("zh"+PROPERTIES));
                }catch (Exception e2){
                    e2.printStackTrace();
                }
                writer.println("zh");
            }
            writer.close();
        }else{
            BufferedReader reader = Utils.getReader(file);
            String lang = reader.readLine();
            try{
                properties.load(Utils.getClassStream(lang+PROPERTIES));
            }catch (IOException e){
                properties.load(Utils.getClassStream("zh"+PROPERTIES));
            }
        }
        return properties;
    }


    public List<OutputStream> getOutputStreams() {
        return outputStreams;
    }

    public List<InputStream> getInputStreams() {
        return inputStreams;
    }

    public static FileSystem getFileSystem(){
        return system;
    }

    public List<BufferedReader> getReaders(){
        return readers;
    }

    public List<PrintWriter> getWriters(){
        return writers;
    }
}
