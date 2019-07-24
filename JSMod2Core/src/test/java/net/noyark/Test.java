package net.noyark;

import com.alibaba.fastjson.JSON;

import java.util.Map;

public class Test {


    public static void main(String[] args) {
        JSON.parseObject("{}", Map.class);
    }
}
