package net.noyark.scpslserver.jsmod2.utils;

import net.noyark.Code;
import net.noyark.scpslserver.jsmod2.Message;
import net.noyark.scpslserver.jsmod2.FileSystem;
import net.noyark.scpslserver.jsmod2.Server;
import net.noyark.scpslserver.jsmod2.network.DataPacket;

import java.io.*;
import java.util.Arrays;
import java.util.Base64;

public class Utils {

    private static Message messageSender;

    static {
        messageSender = new Message();
    }


    public static BufferedReader getReader(InputStream in) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(in,"utf-8"));
        FileSystem.getFileSystem().getReaders().add(reader);
        return reader;
    }

    public static BufferedReader getReader(File file) throws IOException {
        BufferedReader reader = getReader(new FileInputStream(file));
        FileSystem.getFileSystem().getReaders().add(reader);
        return reader;
    }
    public static InputStream getClassStream(String file){
        InputStream stream = Server.class.getClassLoader().getResourceAsStream(file);
        FileSystem.getFileSystem().getInputStreams().add(stream);
        return stream;
    }
    public static PrintWriter getWriter(OutputStream stream){
        PrintWriter writer = new PrintWriter(stream,true);
        FileSystem.getFileSystem().getWriters().add(writer);
        return writer;
    }
    public static PrintWriter getWriter(File file) throws IOException{
        return getWriter(new FileOutputStream(file));
    }
    public static String getClassFileName(String file){
        return Server.class.getClassLoader().getResource("/").getPath()+file;
    }

    /**
     * 得到格式化json数据  退格用\t 换行用\r
     */
    public static String format(String jsonStr) {
        int level = 0;
        StringBuffer jsonForMatStr = new StringBuffer();
        for(int i=0;i<jsonStr.length();i++){
            char c = jsonStr.charAt(i);
            if(level>0&&'\n'==jsonForMatStr.charAt(jsonForMatStr.length()-1)){
                jsonForMatStr.append(getLevelStr(level));
            }
            switch (c) {
                case '{':
                case '[':
                    jsonForMatStr.append(c+"\n");
                    level++;
                    break;
                case ',':
                    char d = jsonStr.charAt(i-1);
                    if(d == '"' || d == ']'){
                        jsonForMatStr.append(c+"\n");
                    } else {
                        jsonForMatStr.append(c);
                    }
                    break;
                case '}':
                case ']':
                    jsonForMatStr.append("\n");
                    level--;
                    jsonForMatStr.append(getLevelStr(level));
                    jsonForMatStr.append(c);
                    break;
                default:
                    jsonForMatStr.append(c);
                    break;
            }
        }
        return jsonForMatStr.toString();
    }

    private static String getLevelStr(int level){
        StringBuilder levelStr = new StringBuilder();
        for(int levelI = 0;levelI<level ; levelI++){
            levelStr.append("\t");
        }
        return levelStr.toString();
    }

    public static String[] getParentArray(String[] keys){
        return Arrays.copyOf(keys,keys.length-1);
    }

    public static Message getMessageSender(){
        return messageSender;
    }

    public static void TryCatch(Code code){
        try{
            code.code();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 获取数据包id，进行匹配数据包
     * @param bytes
     * @return
     */
    public static Integer getResponsePacketId(byte[] bytes){
        byte[] decodes = Base64.getDecoder().decode(bytes);
        String str = new String(decodes);
        return Integer.parseInt(str.substring(0,str.indexOf("-")));
    }
}
