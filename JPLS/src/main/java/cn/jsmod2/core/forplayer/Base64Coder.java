package cn.jsmod2.core.forplayer;

import java.util.Base64;

public class Base64Coder implements Coder {

    @Override
    public String decode(String message) {
        return new String(Base64.getDecoder().decode(message.getBytes()));
    }
}
