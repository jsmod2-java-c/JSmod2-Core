package cn.jsmod2.web.utils;

import org.springframework.util.DigestUtils;

public class PasswordUtil {

    /**
     * 密码加密规则
     * @param pwd 密码
     * @return 加密后的密码
     */
    public static String encode(String pwd){
        return DigestUtils.md5DigestAsHex(DigestUtils.md5DigestAsHex(DigestUtils.md5DigestAsHex(pwd.getBytes()).getBytes()).getBytes());
    }

    public static boolean equalPassword(String name,String pwd){
        pwd = encode(pwd);
        if(!DbUtil.containsKey(name)){
            return false;
        }
        if(DbUtil.containsEntry(name,pwd)){
            return true;
        }
        return false;
    }

    public static boolean writePassword(String name,String pwd){
        if(!DbUtil.containsKey(name)){
            pwd = encode(pwd);
            DbUtil.insert(name,pwd);
            return true;
        }
        return false;
    }

    public static boolean modifyPassword(String name,String pwd){
        return DbUtil.modify(name, encode(pwd));
    }

}
