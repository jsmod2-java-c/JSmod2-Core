package cn.jsmod2.core.interapi.command;

import java.io.IOException;
import java.util.List;

public interface IOpsFile {


    void addOp(String name) throws IOException;

    void removeOp(String name) throws IOException;

    List<String> getOps() throws IOException;



}
