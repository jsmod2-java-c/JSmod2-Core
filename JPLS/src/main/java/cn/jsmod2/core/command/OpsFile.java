/*
Jsmod2 is a java-based scpsl cn.jsmod2.server initiated by jsmod2.cn.
It needs to rely on smod2 and proxy. jsmod2 is an open source
free plugin that is released under the GNU license. Please read
the GNU open source license before using the software. To understand
the appropriateness, if infringement, will be handled in accordance
with the law, @Copyright JavaMultiModStarterAdmin China,more can see <a href="http://jsmod2.cn">that<a>
 */
package cn.jsmod2.core.command;


import cn.jsmod2.core.Server;
import cn.jsmod2.core.interapi.command.IOpsFile;
import cn.jsmod2.core.utils.Utils;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static cn.jsmod2.core.FileSystem.OPS_FILE;

/**
 * 用于标记一个存放管理玩家名称的文件，当一个玩家存在admin属性时，
 * 会注册为管理者，并以一行一行的形式记录管理员名称
 *
 * @author magiclu550
 */
public class OpsFile implements IOpsFile {

    private static OpsFile opsFile;

    private File file;

    public OpsFile(Server server) throws IOException{
        this.file = new File(server.getServer().serverfolder+ OPS_FILE);
        if(!file.exists()) file.createNewFile();
    }

    public void addOp(String name) throws IOException{
        PowerPool.addAdminMemory(name);
        List<String> all = getOps();
        all.add(name);
        FileUtils.writeLines(file,System.getProperty("file.encoding"),all);
    }

    public void removeOp(String name) throws IOException{
        PowerPool.removeAdminMemory(name);
        List<String> all = getOps();
        all.remove(name);
        FileUtils.writeLines(file,System.getProperty("file.encoding"),all);
    }

    public List<String> getOps() throws IOException {
       return FileUtils.readLines(new File(Server.getSender().getServer().serverfolder+OPS_FILE),System.getProperty("file.encoding"));
    }

    public static OpsFile getOpsFile(Server server) {
        try{
            if(opsFile==null)
                opsFile = new OpsFile(server);
        }catch (IOException e){
            Utils.printException(e);
        }
        return opsFile;
    }

    public static OpsFile getInstance(){
        return opsFile;
    }
}
