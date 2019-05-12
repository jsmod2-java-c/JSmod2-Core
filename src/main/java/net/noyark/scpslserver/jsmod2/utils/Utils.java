package net.noyark.scpslserver.jsmod2.utils;

import net.noyark.scpslserver.jsmod2.Server;

import java.io.*;

public class Utils {

    public static BufferedReader getReader(InputStream in) throws IOException{
        return new BufferedReader(new InputStreamReader(in,"utf-8"));
    }

    public static BufferedReader getReader(File file) throws IOException {
        return new BufferedReader(new InputStreamReader(new FileInputStream(file)));
    }
    public static InputStream getClassStream(String file){
        return Server.class.getClassLoader().getResourceAsStream(file);
    }
}
