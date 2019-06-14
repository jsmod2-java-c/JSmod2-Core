package cn.jsmod2.script.function;

public abstract class Function {

    private String functionName;

    private String code;

    public Function(String functionName,String code) {
        this.functionName = functionName;
        this.code = code;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
