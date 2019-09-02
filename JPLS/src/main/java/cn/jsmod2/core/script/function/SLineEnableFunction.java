package cn.jsmod2.core.script.function;

import cn.jsmod2.core.FileSystem;
import cn.jsmod2.core.Server;

/**
 * sline
 */
public class SLineEnableFunction extends NativeFunction {

    public SLineEnableFunction() {
        super("sline", "native func sline()");
    }

    @Override
    public Object execute(String[] args, Object... objs) {
        if(args.length == 1){
            if(args[0].equals("false")){
                return Server.getRuntime().running().serverProp.setProperty(FileSystem.CONSOLE_LINE,"false");
            }
        }
        return Server.getRuntime().running().serverProp.setProperty(FileSystem.CONSOLE_LINE,"true");
    }
}
