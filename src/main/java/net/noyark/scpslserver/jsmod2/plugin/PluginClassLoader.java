package net.noyark.scpslserver.jsmod2.plugin;

import net.noyark.scpslserver.jsmod2.Plugin;

import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandlerFactory;

public class PluginClassLoader extends URLClassLoader {

    public static final String PLUGIN_FILE = "plugins";

    public PluginClassLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }

    public PluginClassLoader(URL[] urls) {
        super(urls);
    }

    public PluginClassLoader(URL[] urls, ClassLoader parent, URLStreamHandlerFactory factory) {
        super(urls, parent, factory);
    }

    public Plugin loadPlugin(){


        return null;
    }
}
