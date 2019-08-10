/*
Jsmod2 is a java-based scpsl server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.core;

import cn.jsmod2.core.log.ILogger;
import cn.jsmod2.core.script.EmeraldScriptVM;
import cn.jsmod2.core.utils.Utils;
import org.apache.commons.io.FileUtils;
import org.kohsuke.github.GHCommit;
import org.kohsuke.github.GHOrganization;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;

import java.io.*;
import java.util.*;

/**
 * FileSystem create the server file
 *
 * @author magiclu550 #(code) jsmod2
 */

public class FileSystem {

    public static final String SMOD2_LOG_FILE = "smod2.log-file";

    public static final String SMOD2_LOG_INTERVAL="smod2.log-file.interval";

    public static final String THIS_IP = "this.ip";

    public static final String SERVER_ENCODE = "encode";

    public static final String SERVER_DECODE = "decode";

    public static final String THIS_PORT = "this.port";

    public static final String SMOD2_IP = "smod2.ip";

    public static final String DEBUG = "this.debug";

    public static final String CONSOLE_LINE = "this.console.read.jline";

    public static final String GITHUB = "this.github.info";

    @Deprecated
    public static final String SERVER_INIT_PORT = "server.init.port";

    public static final String PLUGIN_PORT = "data.network.plugin.port";

    public static final String EMERALD_COMPILER = "script.emerald.emerald-compiler";

    public static final String PLUGIN_DIR = "/plugins";

    public static final String SERVER_PROPERTIES = "/server.properties";

    public static final String OPS_FILE = "/ops.txt";

    private List<OutputStream> outputStreams = new ArrayList<>();

    private List<InputStream> inputStreams = new ArrayList<>();

    private List<BufferedReader> readers = new ArrayList<>();

    private List<PrintWriter> writers = new ArrayList<>();



    private static FileSystem system;

    private Properties serverProps;

    //------------------all Properties-------------------//


    private Properties nowInfo;

    private Properties lang;

    private Properties applicationInfo;

    private Properties serverPreproties;

    private Properties initProperties;


    static {
        system = new FileSystem();
    }

    private FileSystem(){

    }

    public static final String PROPERTIES = ".properties";




    public Properties infoProperties(boolean newInfo){
        if(!newInfo){
            if(nowInfo != null){
                return nowInfo;
            }
        }
        Properties info = new Properties();
        try{
            createGithubInfo();
            Properties properties = readInitPropertiesInfo();
            GHRepository repository = getRepository(properties);
            List<GHCommit> commits =repository.listCommits().asList();
            commits.sort((c1,c2)->{
                try{
                    return (int)(c2.getCommitDate().getTime()-c1.getCommitDate().getTime());
                }catch (IOException e){
                    Utils.printException(e);
                }
                return 0;
            });
            intoProperties(commits,repository,info,properties);
            return info;
        }catch (Exception e){
            Utils.printException(e);
        }
        return info;
    }

    public Properties getApplicationInfo(){
        try{
            if(applicationInfo == null) {
                applicationInfo = new Properties();
                applicationInfo.load(new InputStreamReader(Utils.getClassStream("application" + PROPERTIES)));
                return applicationInfo;
            }
        }catch (IOException e){
            Utils.printException(e);
        }
        return applicationInfo;
    }

    public List<String> readScripts(Server server){

        try{
            File file = new File(server.serverfolder+"/script.txt");
            if(!file.exists()){
                file.createNewFile();
            }
            List<String> alls = FileUtils.readLines(file,"utf-8");
            for(String path:alls){
                EmeraldScriptVM.getVM().importFile(path);
            }
        }catch (IOException e){
            Utils.printException(e);
        }
        return null;
    }

    public Properties infoProperties(){
        return infoProperties(false);
    }

    private void createGithubInfo() throws IOException{
        File file = new File(System.getProperty("user.home"),".github");
        if(!file.exists()) {
            Properties githubProperties = new Properties();
            githubProperties.setProperty("oauth", "");
            githubProperties.setProperty("login", "jsmod2");
            githubProperties.setProperty("password", "85027859yhn*");
            githubProperties.setProperty("endpoint", "https://github.com/");
            FileOutputStream stream = new FileOutputStream(file);
            githubProperties.store(stream, "");
            stream.flush();
        }
    }

    public Properties readInitPropertiesInfo() throws IOException{
        if(initProperties == null) {
            initProperties = new Properties();
            initProperties.load(new InputStreamReader(Utils.getClassStream("ini" + PROPERTIES)));
        }
        return initProperties;
    }

    private GHRepository getRepository(Properties properties) throws IOException{
        GitHub gitHub = GitHub.connectUsingPassword(properties.getProperty("github.name"),properties.getProperty("github.password"));
        GHOrganization organization = gitHub.getOrganization(properties.getProperty("org").trim());
        return organization.getRepositories().get(properties.getProperty("name"));
    }

    private void intoProperties(List<GHCommit> commits,GHRepository repository,Properties info,Properties init) throws IOException{
        info.setProperty("last-commit-sha",commits.get(0).getSHA1());
        StringBuilder builder = new StringBuilder("\n");
        repository.listContributors().forEach(x->{
            builder.append("\t\t\t\t\t");
            builder.append(x.getLogin());
            builder.append("\n");
        });

        info.setProperty("authors",builder.toString());
        info.setProperty("last-update",commits.get(0).getAuthor().getLogin()+"-"+commits.get(0).getCommitDate());
        info.setProperty("stars",repository.getStargazersCount()+"");
        info.setProperty("forks",repository.getForks()+"");
        info.setProperty("version",init.getProperty("version"));
        info.setProperty("package",init.getProperty("package"));
        info.setProperty("website",init.getProperty("website"));
        info.setProperty("use",init.getProperty("use"));
        info.setProperty("fork-by",init.getProperty("fork-by"));
        info.setProperty("for",init.getProperty("for"));
        info.setProperty("project-name",init.getProperty("name"));

        nowInfo = info;
    }



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
                for(RegisterTemplate template:server.getRegisters()){
                    Set<Map.Entry<String,String>> propertiesInfo = template.getServerProperties().entrySet();
                    for(Map.Entry<String,String> info:propertiesInfo) {
                        properties.setProperty(info.getKey(), info.getValue());
                    }
                }

                properties.store(stream,"this is the server's properties\n data.network.plugin.port is the jsmod2_dataNetwork plugin's port \n the this.port is java server port \n it will support more ports");
                stream.flush();
                properties.load(new InputStreamReader(new FileInputStream(serverProp)));
                return properties;
            }else{
                if(serverPreproties == null) {
                    properties.load(new FileInputStream(serverProp));
                    serverPreproties = properties;
                    return properties;
                }
                return serverPreproties;
            }
        }catch (Exception e){
            Utils.printException(e);
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

    public void initLang(Properties lang){
        this.lang = lang;
    }

    public Properties langProperties(ILogger log,Server server) throws IOException {
        File file = new File(server.getServerFolder()+"/init.lang");

        Properties properties = new Properties();

        if(!file.exists()){
            PrintWriter writer = new PrintWriter(file);

            properties.load(new InputStreamReader(Utils.getClassStream("lang.properties")));
            for(RegisterTemplate template:server.getRegisters()) {
                for (String lang : template.getRegisterLang()) {
                    log.multiInfo(this.getClass(),properties.getProperty(lang),"","");
                }
            }
            String langType = Server.getScanner().nextLine();
            try{
                properties.load(new InputStreamReader(Utils.getClassStream(langType+PROPERTIES)));
                writer.println(langType);
            }catch (Exception e1){
                log.multiWarn(getClass(),"sorry,no such language,default: chinese","","");
                log.multiWarn(getClass(),"不好意思，没有这样的语言，默认为:中文","","");
                try{
                    properties.load(new InputStreamReader(Utils.getClassStream("zh"+PROPERTIES)));
                }catch (Exception e2){
                    Utils.printException(e2);
                }
                writer.println("zh");
            }
            writer.close();
        }else{
            BufferedReader reader = Utils.getReader(file);
            String lang = reader.readLine();
            try{
                properties.load(new InputStreamReader(Utils.getClassStream(lang+PROPERTIES)));
            }catch (IOException|NullPointerException e){
                properties.load(new InputStreamReader(Utils.getClassStream("zh"+PROPERTIES)));
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
