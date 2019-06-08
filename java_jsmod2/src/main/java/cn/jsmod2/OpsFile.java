package cn.jsmod2;

import cn.jsmod2.command.PowerPool;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static cn.jsmod2.FileSystem.OPS_FILE;

/**
 *操作admin
 */
public class OpsFile {

    private static OpsFile opsFile;

    private File file;

    public OpsFile() throws IOException{
        this.file = new File(Server.getSender().getServer().serverfolder+ OPS_FILE);
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

    public static OpsFile getOpsFile() {
        try{
            if(opsFile==null)
                opsFile = new OpsFile();
        }catch (IOException e){
            e.printStackTrace();
        }
        return opsFile;
    }
}
