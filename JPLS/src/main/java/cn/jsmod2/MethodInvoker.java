package cn.jsmod2;

import java.lang.reflect.Method;
import java.util.List;

public class MethodInvoker {

    private List<Object> objVals;

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
