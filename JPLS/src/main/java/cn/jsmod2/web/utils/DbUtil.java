package cn.jsmod2.web.utils;

import cn.jsmod2.core.FileSystem;
import cn.jsmod2.core.utils.Utils;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class DbUtil {

    public static final String MAPPING = "<======>";

    public static String insert(String key,String value){
        try {
            File file = FileSystem.getFileSystem().jsmod2Db();
            FileUtils.write(file,key+MAPPING+value,"UTF-8");
        }catch (IOException e){
            Utils.printException(e);
        }
        return key+MAPPING+value;
    }

    public static boolean modify(String key,String value){
        try {
            File file = FileSystem.getFileSystem().jsmod2Db();
            List<String> lists = FileUtils.readLines(file,"UTF-8");
            boolean found = false;
            for(int i = 0;i<lists.size();i++){
                if(lists.get(i).split(MAPPING)[0].equals(key)){
                    lists.remove(i);
                    found = true;
                    break;
                }
            }
            if(!found){
                return false;
            }
            lists.add(key+MAPPING+value);
            FileUtils.writeLines(file,lists);
            return true;
        }catch (IOException e){
            Utils.printException(e);
        }
        return false;
    }

    public static boolean containsKey(String key){

        return contains(key,0,-1,null);
    }

    public static boolean containsValue(String value){
        return contains(value,1,-1,null);
    }

    public static boolean containsEntry(String key,String value){
        return contains(value,1,0,key);
    }

    private static boolean contains(String key,int index,int aIndex,String value){
        try{
            List<String> strings = FileUtils.readLines(FileSystem.getFileSystem().jsmod2Db(),"UTF-8");
            for(String s:strings){
                String[] key_value = s.split(MAPPING);
                if(key_value[index].equals(key)&&(aIndex==-1||key_value[aIndex].equals(value))){
                    return true;
                }
            }
            return false;
        }catch (IOException e){
            Utils.printException(e);
        }
        return false;
    }


}
