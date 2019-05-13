package net.noyark.scpslserver.jsmod2.utils;

import java.lang.reflect.Method;

public class MethodInvokeMapper {

    private EventPriority priority;

    private Method method;

    public MethodInvokeMapper(EventPriority priority,Method method){
        this.priority = priority;
        this.method = method;
    }

    public EventPriority getPriority() {
        return priority;
    }

    public Method getMethod() {
        return method;
    }
}
