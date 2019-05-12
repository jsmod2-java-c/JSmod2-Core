package net.noyark.scpslserver.jsmod2.utils;

import net.noyark.scpslserver.jsmod2.FileSystem;
import net.noyark.scpslserver.jsmod2.Server;

import java.io.*;

public class Utils {

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
}
