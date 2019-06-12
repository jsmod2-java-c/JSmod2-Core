/*
Jsmod2 is a java-based scpsl server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2;

import cn.jsmod2.log.ILogger;
import cn.jsmod2.utils.Utils;
import scala.sys.Prop;

import java.io.*;
import java.util.*;

/**
 * FileSystem create the server file
 *
 * @author magiclu550 #(code) jsmod2
 */

public class FileSystem {

    public static final String SERVER_ENCODE = "encode";

    public static final String SERVER_DECODE = "decode";

    public static final String THIS_PORT = "this.port";

    public static final String SMOD2_IP = "smod2.ip";

    @Deprecated
    public static final String SERVER_INIT_PORT = "server.init.port";

    public static final String PLUGIN_PORT = "data.network.plugin.port";

    public static final String PLUGIN_DIR = "/plugins";

    public static final String SERVER_PROPERTIES = "/server.properties";

    public static final String OPS_FILE = "/ops.txt";

    private List<OutputStream> outputStreams = new ArrayList<>();

    private List<InputStream> inputStreams = new ArrayList<>();

    private List<BufferedReader> readers = new ArrayList<>();

    private List<PrintWriter> writers = new ArrayList<>();

    private Properties lang;

    private static FileSystem system;

    static {
        system = new FileSystem();
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
                Set<Map.Entry<String,String>> propertiesInfo = Register.getInstance().getServerProperties().entrySet();
                for(Map.Entry<String,String> info:propertiesInfo) {
                    properties.setProperty(info.getKey(), info.getValue());
                }
                properties.store(stream,"this is the server's properties\n data.network.plugin.port is the jsmod2_dataNetwork plugin's port \n the this.port is java server port \n it will support more ports");
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

    public String getLangProperties(String key){
        return lang.getProperty(key);
    }

    void initLang(Properties lang){
        this.lang = lang;
    }

    Properties langProperties(ILogger log) throws IOException {
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
            }catch (IOException|NullPointerException e){
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
