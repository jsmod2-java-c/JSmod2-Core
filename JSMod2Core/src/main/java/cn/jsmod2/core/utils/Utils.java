/*
Jsmod2 is a java-based scpsl server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright Jsmod2 China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.core.utils;

import cn.jsmod2.core.Message;
import cn.jsmod2.core.FileSystem;
import cn.jsmod2.core.Server;
import cn.jsmod2.core.ex.ServerRuntimeException;
import org.apache.commons.codec.binary.Base64;

import java.io.*;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.Arrays;
import java.util.Properties;

/**
 * 一些常用的工具，简化了操作
 *
 * @author magiclu550
 */
public class Utils {

    private static Message messageSender;

    static {
        messageSender = new Message();
    }

    public static Method getServerMethod(String methodName) throws NoSuchMethodException{
        Method method = Server.class.getDeclaredMethod(methodName);
        method.setAccessible(true);
        return method;
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
            try {
                if (!(e instanceof ServerRuntimeException)) {
                    throw new ServerRuntimeException(e);
                }
            }catch (ServerRuntimeException e1){
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    /**
     * 获取数据包id，进行匹配数据包
     * @return
     */
    public static Integer getResponsePacketId(String str){
        str = toGetString(str);
        try {
            return Integer.parseInt(str.substring(0, str.indexOf("-")));
        }catch (NumberFormatException e){
            return Integer.parseInt(str.substring(str.indexOf("x")+1, str.indexOf("-")),16);
        }
    }

    /**
     * 获取尾部请求
     * @param str
     * @return
     */
    public static String getEndRequest(String str){
        str = toGetString(str);
        if(str.contains("~")) return str.substring(str.indexOf("~")+1);
        return str;
    }

    private static String toGetString(String str){
        try{

            byte[] decodes = Base64.decodeBase64(str);
            Properties properties = FileSystem.getFileSystem().serverProperties(Server.getSender().getServer());
            str = new String(decodes,properties.getProperty(FileSystem.SERVER_DECODE));
        }catch (Exception e){
            e.printStackTrace();
        }
        return str;
    }

    public static byte[] getFullBytes(Socket socket, byte[] gets) throws IOException{
        String message = new String(gets,0,getLen(gets));
        StringBuilder builder = new StringBuilder(message);
        if(!message.endsWith(";")){
            int b = 0;
            while (b!=';'){
                b = socket.getInputStream().read();
                if(b == -1){
                    break;
                }
                builder.append((char) b);
            }
        }

        return builder.toString().getBytes();
    }

    public static int getLen(byte[] bytes){
        int len = 0;
        for (byte get : bytes) {
            if (get != 0) {
                len++;
            }
        }
        return len;
    }

    public static String simpleClassName(Class<?> clz){
        String className = clz.getName();
        String[] fields = className.split("\\.");
        StringBuilder builder = new StringBuilder();
        for(int i = 0;i<fields.length-1;i++){
            String after = fields[i].substring(0,1);
            builder.append(after);
            builder.append(".");
        }
        builder.append(fields[fields.length-1]);
        return builder.toString();
    }

    public static String arraysToString(String[] args){
        if(args.length>1) {
            StringBuilder builder = new StringBuilder(args[0]);
            for (int i = 1; i < args.length; i++) {
                builder.append(",");
                builder.append(args[i]);
            }
            return builder.toString();
        }
        return "";
    }

}
