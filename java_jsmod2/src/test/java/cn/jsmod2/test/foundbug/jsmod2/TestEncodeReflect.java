package cn.jsmod2.test.foundbug.jsmod2;

//import com.alibaba.fastjson.JSON;
//import org.junit.Test;
//
//import java.lang.reflect.Field;
//import java.text.NumberFormat;
//import java.util.Arrays;

public class TestEncodeReflect {
//    @Test
//    public void test() throws Exception{
//        User o = reflect(new String[]{"test-object02-a:1"});
//        System.out.println(o.getTest().getObject02().getA());
//    }

//
//    public User reflect(String[] props) throws Exception{
//        User o = new net.noyark.test.foundbug.jsmod2.User();
//        for(int i = 0;i<props.length;i++){
//            String[] key_value = props[i].split(":");
//            String[] fields = key_value[0].split("-");
//            Object field = o;
//            for(int j = 0;j<fields.length-1;j++){
//                field = invokeGetMethod(field,fields[j]);
//            }
//            invokeSetMethod(field,fields[fields.length-1],key_value[1]);
//        }
//        return o;
//    }
//
//    private Object invokeGetMethod(Object o,String field) throws Exception{
//        StringBuilder builder = new StringBuilder((field.charAt(0)+"").toUpperCase());
//        String first = "get"+builder.append(field.substring(1));
//        return o.getClass().getMethod(first).invoke(o);
//    }
//    private void invokeSetMethod(Object o,String field,String value) throws Exception{
//        Field field1 = o.getClass().getDeclaredField(field);
//        field1.setAccessible(true);
//        Class<?> clz = field1.getType();
//        Object object = JSON.parseObject(value,clz);
//        field1.set(o,object);
//        //o.getClass().getDeclaredMethod(first).invoke(o,new Object[]{value});
//    }
}
