package cn.jsmod2;

import cn.jsmod2.core.Server;
import cn.jsmod2.core.utils.Utils;

public class JVMRuntime {

    private Runtime runtime = Runtime.getRuntime();

    private Server server;

    public JVMRuntime(Server server){
        this.server = server;
    }

    public void setServerArgs(){
        try {
            String heap = server.serverProp.getProperty(Register.MAX_HEAP, "1024");
            server.getLogger().multiInfo(getClass(),"Set the JVM max_heap to "+heap+" m","","");
            //java -Xmx1024m 最大堆 以及自定义jvm命令
            runtime.exec("java -Xmx" + heap + "m "+server.serverProp.getProperty(Register.JVM_ARGS));
        }catch (Exception e){
            Utils.printException(e);
        }
    }
}
