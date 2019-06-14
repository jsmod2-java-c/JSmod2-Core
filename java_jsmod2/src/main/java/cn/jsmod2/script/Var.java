package cn.jsmod2.script;

import cn.jsmod2.ex.TypeErrorException;

public class Var {

    private String value;

    private String type;

    private boolean isNull;

    public Var(String value){
        this.value = value;
        this.type = parseType(value);
        if(value.matches("NULL")){
            isNull = true;
        }
    }

    private String parseType(String value){
        if(value.matches("[0-9]+")){
            return "INT";
        }else if(value.matches("[0-9]+\\.[0-9]+")){
            return "DOUBLE";
        }else if(value.matches("true|false")){
            return "BOOL";
        }else if(value.matches("NULL")){
            return "NULL";
        }else{
            return "STRING";
        }
    }

    public String getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public boolean isNull() {
        return isNull;
    }

    public void setValue(String value) {
        String type = parseType(value);
        if(getType().equals("NULL")){
            this.value = value;
            this.type = type;
            return;
        }
        if(type.equals(getType())){
            this.value = value;
        }else{
            throw new TypeErrorException("the type is error value is "+getType()+",but the new value type is "+type);
        }
    }

    public void unset(){
        this.type = "NULL";
        this.value = "NULL";
    }

    @Override
    public String toString() {
        return "Var{" +
                "value='" + value + '\'' +
                ", type='" + type + '\'' +
                ", isNull=" + isNull +
                '}';
    }
}
