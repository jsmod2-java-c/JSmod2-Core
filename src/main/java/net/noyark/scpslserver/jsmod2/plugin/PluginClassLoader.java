package net.noyark.scpslserver.jsmod2.plugin;

import net.noyark.scpslserver.jsmod2.Plugin;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandlerFactory;

public class PluginClassLoader extends URLClassLoader {


    public PluginClassLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }

    public PluginClassLoader(URL[] urls) {
        super(urls);
    }

    public PluginClassLoader(URL[] urls, ClassLoader parent, URLStreamHandlerFactory factory) {
        super(urls, parent, factory);
    }

    public Plugin loadPlugin(File pluginDir){
        
        return null;
    }
}
