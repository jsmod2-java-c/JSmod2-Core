package cn.jsmod2.core.script;

import cn.jsmod2.core.ex.TypeErrorException;
import cn.jsmod2.core.script.function.*;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.lang.reflect.Method;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Jsmod2服务器脚本的解析器
 * script进入脚本解析页面
 * 目前逻辑控制语句和表达式解析没有做
 */
public class EmeraldScriptVM {

    private Map<String,String> javaMethodMapping = new HashMap<>();

    static {
        script = new EmeraldScriptVM();
        getVM().functions.put("echo",new EchoFunction());
        getVM().functions.put("typeof",new TypeOfFunction());
        getVM().functions.put("import",new ImportFunction());
        getVM().functions.put("register",new RegisterNativeFunction());
        getVM().functions.put("if",new IfFunction());
        getVM().functions.put("return",new ReturnFunction());
        getVM().functions.put("+",new StringAddFunction());
        getVM().functions.put("sline",new SLineEnableFunction());
        getVM().functions.put("jimport",getVM().new JImportFunction());
    }

    //导入java的方法
    class JImportFunction extends NativeFunction{
        public JImportFunction() {
            super("jimport","native jimport(classpath)");
        }

        @Override
        public Object execute(String[] args, Object... objs) {
            return javaMethodMapping.put(args[0].substring(args[0].lastIndexOf(".")+1),args[0]);
        }
    }


    private static EmeraldScriptVM script;

    private Map<String, Function> functions = new HashMap<>();

    //全局变量
    private Map<String,Var> vars = new HashMap<>();

    //指针support
    private Map<Integer,Memory> memory_address_mapping = new HashMap<>();

    public Map<String, Function> getFunctions() {
        return functions;
    }


    /**
     * 导入文件(实际调用函数)
     * @param file
     * @throws IOException
     */
    public void importFile(String file) throws IOException {
        //utf-8
        //文件头部指定字符集
        int comment = 0;
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        String coding = reader.readLine();
        List<String> codes = FileUtils.readLines(new File(file),coding);
        for(int i = 1;i<codes.size();i++){
            if(codes.get(i).startsWith("\"\"\"")){
                comment++;
                continue;
            }
            if(comment%2==1&&comment!=0){
                continue;
            }
            if(codes.get(i).matches("#[\\s\\S]+")){
                continue;
            }
            StringBuilder getFunc = new StringBuilder(codes.get(i));
            if(codes.get(i).matches(Memory.matches.get("startfunc"))){
                while (!getFunc.toString().endsWith(":end")){
                    getFunc.append(codes.get(i).replaceAll(" ","").replaceAll("\\t","").replaceAll("#[\\s\\S]+",""));
                    i++;
                }
                i--;
            }
            if(codes.get(i).matches(Memory.matches.get("start"))){
                while (!getFunc.toString().endsWith("}")){
                    getFunc.append(codes.get(i));
                    i++;
                }
                i--;
            }
            parse(getFunc.toString());
        }
    }

    /**
     * 调用java方法必须以J::开头
     * J::a=A()
     * J::a=A().b()
     * J::b=a.c()
     * J::b=a.c().a
     * J::a=A.a();//静态方法
     * J::a=A().B();//内部类
     *
     * @param code
     * @return
     */
    @SuppressWarnings("unchecked")
    private Object executeJavaFunction(String code,Map<String,Var> vars) throws Exception{
        String entry = code.substring("J::".length());
        String[] key_val = entry.split("=");
        String[] methods = key_val[1].split("\\.");
        Object nowReturn = null;
        for(int i = 0;i<methods.length;i++){
            String method = methods[i];
            if(method.matches("[\\s\\S]+\\([\\s\\S]+\\)")){
                //调用方法或实例化
                String funcName = method.trim().substring(0,method.indexOf("("));
                Object[] args = method.substring(method.indexOf("(")+1,method.lastIndexOf(")")).split(",");
                args = getResultArgs(args,vars);//以后使用
                String packageClass = javaMethodMapping.get(funcName);
                if(packageClass!=null){
                    if(nowReturn!=null) {//nowReturn 不是 null，那就是内部类的实例化
                        Class inner = Class.forName(nowReturn.getClass().getName()+"$"+funcName);
                        nowReturn = inner.getConstructor(inner).newInstance(nowReturn);
                    }else{
                        nowReturn = Class.forName(packageClass).getConstructor().newInstance();
                    }
                    //TODO 参加有参数的支持
                }
            }else{
                //直接调用字段或者静态方法
                //静态方法直接将指针推移1格
                String packageClass = javaMethodMapping.get(method);
                if(packageClass==null){
                    if(nowReturn == null){ //如果现在值为null，那就是现有的数值
                        Var var = vars.get(method);
                        nowReturn = var.getObject();
                    }else{
                        //如果不是null，那就是属性
                        nowReturn = nowReturn.getClass().getField(method).get(nowReturn);
                    }
                }else{
                    i++;
                    method = methods[i];
                    String funcName = method.trim().substring(0,method.indexOf("("));
                    Object[] args = method.substring(method.indexOf("(")+1,method.lastIndexOf(")")).split(",");
                    args = getResultArgs(args,vars);
                    Class<?> clz = Class.forName(packageClass);

                    Method _method = clz.getMethod(funcName);
                    //目前支持无参数的
                    nowReturn = _method.invoke(null);
                    //TODO 添加对有参数的支持
                }
            }
        }
        return nowReturn;//最值的值

    }


    private Object[] getResultArgs(Object[] args,Map<String,Var> vars) throws Exception{
        args = setThat(vars,args);//字段处理
        for(int index = 0;index<args.length;index++){
            args[index] = executeFunction(args[index].toString(),vars).toString();
            args[index] = executeJavaFunction(args[index].toString(),vars);
        }
        return args;
    }

    private Object executeReturn(String code,Map<String,Var> vars) throws Exception{
        if(code.startsWith("J::")) {
            String entry = code.substring("J::".length());
            String[] key_val = entry.split("=");
            String name = key_val[0].trim();
            Object obj = executeJavaFunction(code,vars);
            if(vars.containsKey(name)){
                vars.get(name).setObject(obj);
                return vars.get(name);
            }else{
                vars.put(name,Var.compile(code));
                vars.get(name).setObject(obj);
                return vars.get(name);
            }
        }else{
            return code;
        }
    }
    
    /**
     * 计算运算表达式
     * let a = (1+1+2+3/4)*2
     * 先算乘除 后算加减
     *
     * @param expression
     * @return
     */
    private String performCalculations(String expression){
        if(!expression.matches(Memory.matches.get("pc"))){
            return expression;
        }

        return "";
    }



    private String performBoolean(String expression){
        return "";
    }


    public Map<Integer, Memory> getMemory_address_mapping() {
        return memory_address_mapping;
    }

    /**
     * 解析全局变量
     * @param cmd
     * @param vars
     * @return
     */
    //a=0
    //强制修改全局变量global::name
    private Object parseVar(String cmd,Map<String,Var> vars){
        if(cmd.matches(Memory.matches.get("pc"))){
            return cmd;
        }
        if(cmd.matches(Memory.matches.get("func"))){
            return cmd;
        }
        if(!cmd.matches(Memory.matches.get("var"))){
            return cmd;
        }

        if(cmd.startsWith("global::")){
            cmd = cmd.substring("global::".length());
            String[] key_value = cmd.split("=|:\\*");
            Var var = parseVar(key_value[0].trim(),setThat(vars,key_value[1])[0].trim(),this.vars,setThat(vars,cmd)[0].trim());
            return "global::"+var.getName()+"-TYPE:"+var.getType();
        }
        String[] key_value = cmd.split("=|:\\*");
        Var var = parseVar(key_value[0],setThat(vars,key_value[1])[0],vars,setThat(vars,cmd)[0]);
        return var+"TYPE:"+var.getType();
    }

    /**
     * 解析局部变量
     * 指针赋值
     *
     * @param key
     * @param value
     * @param vars
     * @return
     */
    private Var parseVar(String key,String value,Map<String,Var> vars,String cmd){
        if(key.startsWith("const ")){
            key = key.substring("const ".length());
        }
        String name = getPtrName(key);
        if(vars.get(name)!=null){
            Var var;
            if(key.matches("\\*+[\\s\\S]+")){

                var = findVar(key,vars);
            }else{
                var = vars.get(name);
            }
            try{
                var.setValue(value);
            }catch (NullPointerException e){
                throw new TypeErrorException("the type is error");
            }
            return var;
        }else{
            Var var = Var.compile(cmd);
            vars.put(key,var);
            memory_address_mapping.put(var.hashCode(),var);
            return var;
        }
    }

    /**
     * 列出内存的变量
     * @param cmd
     * @return
     */
    //list
    private String listVar(String cmd){
        if(cmd.matches(Memory.matches.get("list"))) {
            StringBuilder builder = new StringBuilder();
            for (Map.Entry<String, Var> var : vars.entrySet()) {
                builder.append(var.getKey());
                builder.append("-");
                builder.append(var.getValue());
                builder.append("\n");
            }
            return builder.toString();
        }
        return cmd;
    }

    /**
     * 检查是否符合语法
     * @param command
     * @return
     */
    public static boolean matchPattern(String command){
        Map<String,String> patterns = Memory.matches;
        for(String pattern:patterns.values()){
            if(command.matches(pattern)){
                return true;
            }
        }
        return false;
    }
    //unset a=0

    /**
     *执行重置指令
     * @param command
     * @param vars
     * @return
     */
    private String unset(String command,Map<String,Var> vars){
        if(command.matches(Memory.matches.get("unset"))){
            String[] unsets = command.split(" ");
            String name;
            if(unsets[1].contains("=")){
                name = unsets[1].substring(0,unsets[1].indexOf("="));
            }else{
                name = unsets[1];
            }
            Var var = vars.get(name);
            if(var == null){
                return "no such var";
            }
            var.unset();
            return unsets[1];
        }
        return command;
    }

    /**
     * 执行一个函数
     * @param func
     * @return
     */
    public Object executeFunction(String func,Map<String,Var> vars){
        if(!func.matches(Memory.matches.get("func"))){
            return func;
        }
        String[] strs = func.split("=|:\\*");
        String funcName= func;
        if(strs.length==2){
            funcName = strs[1];
        }
        String last = ")";
        String[] args;
        if(funcName.contains("){")&&funcName.contains("}")){

            String before = funcName.substring(0,funcName.indexOf("){")+1);
            args = before.substring(before.indexOf("(")+1,before.lastIndexOf(last)).split(",");
        }else{
            args = funcName.substring(funcName.indexOf("(")+1,funcName.lastIndexOf(last)).split(",");
        }

        args = setThat(vars,args);
        for(int i = 0;i<args.length;i++){
            if(!args[i].isEmpty()){
                args[i] = executeFunction(args[i].trim(),vars).toString();
            }
        }
        String before = funcName;
        funcName = funcName.replaceAll("\\(([\\s\\S]+|[\\s\\S]*)\\)","");
        String funcCode = "";
        //native方法提供了funcCode
        if(before.matches(Memory.matches.get("ffunc"))){
            funcCode = before.substring(before.indexOf("{")+1,before.lastIndexOf("}"));
            funcName = before.substring(0,before.indexOf("{")).replaceAll("\\(([\\s\\S]+|[\\s\\S]*)\\)","");
            args = Arrays.copyOf(args,args.length+1);
            args[args.length-1] = funcCode;
        }
        Function function = functions.get(funcName);
        if(function==null){
            return "error:no such function!"+funcName+"on "+func.indexOf(funcName)+" error";
        }

        //普通函数还没开始处理
        //形式参数作用域在方法，方法执行完则销毁
        Map<String,Var> vars_func = new HashMap<>();
        vars_func.putAll(vars);

        if(function instanceof NativeFunction){
            Object object = ((NativeFunction) function).execute(args,vars_func);
            return object==null?"NULL":object;
        }

        //形式参数
        String[] alls = function.getArgs();
        //形式参数
        for(int i =0;i<args.length;i++){
            if(!alls[i].isEmpty()){
                String name = alls[i];
                String get = "=";
                if(name.startsWith("*")){
                    get=":*d:";
                    name=getPtrName(name);
                    Memory memory = memory_address_mapping.get(Integer.parseInt(args[i]));
                    if(memory==null){
                        return "type is error: the "+args[i]+" is not pointer type";
                    }
                }
                vars_func.put(name,Var.compile(name+get+args[i]));
            }
        }

        return executeCommonFunc(function,vars_func);
    }

    public Object executeCommonFunc(Function function,Map<String,Var> vars_func){
        String code = function.getCode();

        String[] codes =code.split(";");

        for(int i = 0;i<codes.length;i++) {
            Object result = parse(codes[i],vars_func);
            if(result.toString().startsWith("returned")){
                return setThat(vars_func,result.toString().substring("returned:".length()))[0];
            }
        }
        return "NULL";
    }

    /**
     * 解析函数的返回变量
     * @param func
     * @return
     */
    private String getFunctionVarName(String func){
        if(func.matches(Memory.matches.get("func"))){
            String[] strs = func.split("=|:\\*");
            if(strs.length==2){
                return strs[0];
            }
        }
        return null;
    }
    //定义函数是
    // func name(a,b);start:
    //  语句
    //  语句
    // :end
    //

    /**
     * 解析函数
     * @param func
     * @return
     */
    private String defineFunction(String func){
        if(!func.matches(Memory.matches.get("dfunc"))){
            return func;
        }
        Function function = Function.compile(func);
        functions.put(function.getFunctionName(),function);
        return "create successfully";
    }


    /**
     * 解析局部变量的参数
     * @param command
     * @param vars
     * @return
     */
    public Object parse(String command, Map<String,Var> vars){
        Object result = null;
        //执行函数可以返回值
        //a=echo()
        try {
            result = executeReturn(command, vars);
            if(!result.equals(command)){
                return result;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        /* 定义函数 */

        result = this.defineFunction(command);
        if(!result.equals(command)){
            return result;
        }
        /* 将变量设置 */
        result = this.parseVar(this.unset(command,vars),vars);
        if(!result.equals(command)){
            return result;
        }



        result = this.listVar(command);
        if(!result.equals(command)){
            return result;
        }
        /* 执行函数，并设置返回值 */
        result = this.executeFunction(command,vars);

        if(result.toString().startsWith("error:")){
            return result;
        }

        /* 获取返回值 */
        String varName = this.getFunctionVarName(command);
        if(varName!=null){
            String cmd;
            if(command.matches(Memory.matches.get("ptr"))){
                cmd=varName+":*d:"+result;
            }else{
                cmd=varName+"="+result;
            }
            System.out.println(cmd);
            result = this.parseVar(varName,result.toString(),vars,cmd).getValue();
        }

        return result;
    }

    public String parse(String command){
        return parse(command,this.vars).toString();
    }

    /**
     * 给命令参数设置变量
     * @param args
     * @return
     */
    public String[] setThat(Map<String,Var> vars,Object... args){
        List<String> lists = new LinkedList<>();
        try{
            for(int i=0;i<args.length;i++){
                StringBuilder builder = new StringBuilder();
                if(!args[i].equals("''")) {
                    if (args[i].toString().startsWith("'")) {
                        while (!builder.toString().endsWith("'")) {
                            builder.append(args[i]);
                            if (!args[i].toString().endsWith("'"))
                                builder.append(",");
                            i++;
                        }
                        i--;
                    }
                }
                if(builder.toString().isEmpty()){
                    builder.append(args[i]);
                }

                lists.add(builder.toString());
            }
        }catch (ArrayIndexOutOfBoundsException e){
            throw new TypeErrorException("the type must be STRING");
        }

        args = lists.toArray(new String[lists.size()]);
        String[] dArgs = new String[args.length];
        int i = 0;
        //关于变量
        for(Object arg:args){
            String lo = arg.toString();
            Pattern pattern = Pattern.compile("\\$\\{[\\*]+[a-zA-Z_$]+\\}");
            Matcher matcher = pattern.matcher(arg.toString());
            while (matcher.find()){
                String group = matcher.group();
                String value = getStringVal(this.getPtrValue(group.substring(group.indexOf("{")+1,group.lastIndexOf("}")),vars));
                lo = lo.replace(group,value);
            }
            for(Map.Entry<String,Var> var:vars.entrySet()){
                if(this.getVars().get(var.getKey())!=null)lo = lo.replace("${global::"+var.getKey()+"}",getStringVal(this.getVars().get(var.getKey()).getValue()));
                lo = lo.replace("${"+var.getKey()+"}",getStringVal(var.getValue().getValue()));
            }
            //字符串'${}ssadads'+''
            NativeFunction f = (NativeFunction)(this.functions.get("+"));
            lo = f.execute(new String[]{lo}).toString();
            dArgs[i] = lo;

            i++;
        }
        return dArgs;
    }

    private static String getStringVal(String globalVar){
        if(globalVar.startsWith("'")&&globalVar.endsWith("'")){
            globalVar = globalVar.substring(globalVar.indexOf("'")+1,globalVar.lastIndexOf("'"));
        }
        return globalVar;
    }


    /**
     * ***a
     * ***和a
     * 0   1           2              3
     * a->对应hash值->Memory的名字->hash值
     * @param name
     * @return
     */
    public String getPtrValue(String name,Map<String,Var> vars){
        if(!name.matches("[\\*]+[a-zA-Z_$]+")){
            return name;
        }
        return findVar(name,vars).getValue();
    }

    public Var findVar(String name,Map<String,Var> vars){
        int len = getPtrLen(name);
        name = getPtrName(name);
        Var nowValue = null;
        for(int i =0;i<len;i++){
            if(i ==0){
                int address = Integer.parseInt(vars.get(name).getValue());
                Memory memory = memory_address_mapping.get(address);
                nowValue = ((Var) memory);
            }else if(i%2==0){
                nowValue = vars.get(nowValue.getValue());
            }else{
                Memory memory = memory_address_mapping.get(Integer.parseInt(nowValue.getValue()));
                if(memory instanceof Var){
                    nowValue = ((Var) memory);
                }
            }
        }
        return nowValue;
    }
    public int getPtrLen(String name){
        int len = 0;
        char[] chars = name.toCharArray();
        for(char chara:chars){
            if (chara == '*'){
                len++;
            }else{
                return len;
            }
        }
        return len;
    }
    public String getPtrName(String name){
        return name.substring(getPtrLen(name));
    }

    public static EmeraldScriptVM getVM() {
        return script;
    }

    public Map<String, Var> getVars() {
        return vars;
    }
}
