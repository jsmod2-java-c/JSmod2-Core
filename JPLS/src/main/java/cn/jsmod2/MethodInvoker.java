package cn.jsmod2;

import java.lang.reflect.Method;
import java.util.List;

/**
 * MethodInvoker是用来存储一个可执行的方法对象的数据类,主要
 * 用于ServicePanel类中的json解析，目前该项技术正在完善
 * @author MagicLu550
 * @see cn.jsmod2.web.service.PanelService
 */
public class MethodInvoker {
    /**
     * 方法的每个参数对应的值
     */
    private List<Object> objVals;

    /**
     * 方法对象
     */
    private Method method;

    public MethodInvoker(List<Object> objVals, Method method) {
        this.objVals = objVals;
        this.method = method;
    }

    public List<Object> getObjVals() {
        return objVals;
    }

    public void setObjVals(List<Object> objVals) {
        this.objVals = objVals;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }
}
