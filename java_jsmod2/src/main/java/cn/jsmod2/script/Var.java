package cn.jsmod2.script;

import cn.jsmod2.ex.TypeErrorException;



import static cn.jsmod2.script.EmeraldScript_JavaParser.*;


public class Var extends Memory{

    private String value;

    private String type;

    private boolean isNull;

    private String name;

    private Var(String name,String value){
        this.name = name;
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

    public void setPointerType(String type){
        if(type.matches("INT|NULL")){
            this.type = "*";
        }else if(type.matches("\\*+")){
            this.type = type+"*";
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

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(String value) {
        if(type.matches("\\*+")){
            if(!value.startsWith("&")){
                Var var = (Var) (getScript().getMemory_address_mapping().get(Integer.parseInt(value)));
                if(!var.getType().equals(type)){
                    this.value = value;
                }else{
                    throw new TypeErrorException("the value type is not "+type);
                }
            }else{
                this.value = getScript().getVars().get(value.substring(value.indexOf("&")+1)).hashCode()+"";
            }
            return;
        }
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

    public String getName() {
        return name;
    }

    public void unset(){
        this.type = "NULL";
        this.value = "NULL";
    }
    // var c=1
    public static Var compile(String command){
        if(command.matches(matches.get("ptr"))){
            String[] right_left = command.split(":\\*");
            String right = right_left[0].trim();
            String left = right_left[1].trim();
            Var var;
            if(left.startsWith("d:")){
                var = (Var) (getScript().getMemory_address_mapping().get(Integer.parseInt(left.substring("d:".length()))));
            }else{
                var = getScript().getVars().get(left);
            }
            Var var_ptr = new Var(right,var.hashCode()+"");
            var_ptr.setPointerType(var.getType());
            return var_ptr;
        }else{
            String[] key_value = command.split("=");
            if(key_value.length<2){
                command = command+" ";
                String key = key_value[0];
                key_value = new String[2];
                key_value[0] = key;
                key_value[1] = " ";
            }
            if(Memory.command.contains(key_value[0])){
                throw new TypeErrorException("the name is define in native");
            }
            String[] values = new String[key_value.length-1];
            System.arraycopy(key_value,1,values,0,values.length);
            StringBuilder builder = new StringBuilder();
            for(int i = 0;i<values.length;i++){
                builder.append(values[i]);
                if(!(i == values.length-1)) {
                    builder.append("=");
                }
            }
            if(command.endsWith("=")){
                builder.append("=");
            }
            return new Var(key_value[0],builder.toString());
        }
    }

    @Override
    public String toString() {
        return name+"="+value;
    }
}
