package cn.jsmod2.script.kotlin


import cn.jsmod2.ex.TypeErrorException
import cn.jsmod2.script.Memory
import cn.jsmod2.script.Var
import cn.jsmod2.script.function.*
import org.apache.commons.io.FileUtils

import java.io.*
import java.util.*
import java.util.regex.Pattern
import cn.jsmod2.script.function.Function

/**
 * emerald代码再EmeraldVM中是最底层的机器代码，如果要使用EmeraldVM
 * 运行代码，需要将语言编译成ermerald语言执行
 * Jsmod2服务器脚本的解析器
 * script进入脚本解析页面
 * if语法
 * if(bool b){
 * right(){
 *
 * }else{
 *
 * }elif(bool b){
 *
 * };
 * }
 * while语法
 * while(boolean b){
 *
 * }
 * do{
 *
 * }while(boolean b)
 *
 * for语法
 * for(a in range(10)){
 *
 * }
 * 数组
 * [1,2,3]
 * 映射
 * [1=>2,2=>3,3=>4]
 *
 */
class EmeraldScriptVMKotlinNative {

    private val functions = HashMap<String, Function>()

    //全局变量
    private val vars = HashMap<String, Var>()

    //指针support
    private val memory_address_mapping = HashMap<Int, Memory>()

    fun getFunctions(): Map<String, Function> {
        return functions
    }


    /**
     * 导入文件(实际调用函数)
     * @param file
     * @throws IOException
     */
    @Throws(IOException::class)
    fun importFile(file: String) {
        //utf-8
        //文件头部指定字符集
        var comment = 0
        val reader = BufferedReader(InputStreamReader(FileInputStream(file)))
        val coding = reader.readLine()
        val codes = FileUtils.readLines(File(file), coding)
        var i = 1
        while (i < codes.size) {
            if (codes[i].startsWith("\"\"\"")) {
                comment++
                i++
                continue
            }
            if (comment % 2 == 1 && comment != 0) {
                i++
                continue
            }
            if (codes[i].matches("#[\\s\\S]+".toRegex())) {
                i++
                continue
            }
            val getFunc = StringBuilder(codes[i])
            if (codes[i].matches(Memory.matches["startfunc"]!!.toRegex())) {
                while (!getFunc.toString().endsWith(":end")) {
                    getFunc.append(codes[i].replace(" ".toRegex(), "").replace("\\t".toRegex(), "").replace("#[\\s\\S]+".toRegex(), ""))
                    i++
                }
                i--
            }
            if (codes[i].matches(Memory.matches["start"]!!.toRegex())) {
                while (!getFunc.toString().endsWith("}")) {
                    getFunc.append(codes[i])
                    i++
                }
                i--
            }
            parse(getFunc.toString())
            i++
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
    private fun performCalculations(expression: String): String {
        return if (!expression.matches(Memory.matches["pc"]!!.toRegex())) {
            expression
        } else ""

    }

    private fun performBoolean(expression: String): String {
        return ""
    }


    fun getMemory_address_mapping(): Map<Int, Memory> {
        return memory_address_mapping
    }

    /**
     * 解析全局变量
     * @param cmd
     * @param vars
     * @return
     */
    //a=0
    //强制修改全局变量global::name
    private fun parseVar(cmd: String, vars: MutableMap<String, Var>): Any {
        var cmd = cmd
        if (cmd.matches(Memory.matches["pc"]!!.toRegex())) {
            return cmd
        }
        if (cmd.matches(Memory.matches["func"]!!.toRegex())) {
            return cmd
        }
        if (!cmd.matches(Memory.matches["var"]!!.toRegex())) {
            return cmd
        }

        if (cmd.startsWith("global::")) {
            cmd = cmd.substring("global::".length)
            val key_value = cmd.split("=|:\\*".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val `var` = parseVar(key_value[0].trim { it <= ' ' }, setThat(vars, key_value[1])[0]!!.trim { it <= ' ' }, this.vars, setThat(vars, cmd)[0]!!.trim { it <= ' ' })
            return "global::" + `var`.name + "-TYPE:" + `var`.type
        }
        val key_value = cmd.split("=|:\\*".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val `var` = parseVar(key_value[0], setThat(vars, key_value[1])[0], vars, setThat(vars, cmd)[0])
        return `var`.toString() + "TYPE:" + `var`.type
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
    private fun parseVar(key: String, value: String?, vars: MutableMap<String, Var>, cmd: String?): Var {
        var key = key
        if (key.startsWith("const ")) {
            key = key.substring("const ".length)
        }
        val name = getPtrName(key)
        if (vars[name] != null) {
            val `var`: Var?
            if (key.matches("\\*+[\\s\\S]+".toRegex())) {

                `var` = findVar(key, vars)
            } else {
                `var` = vars[name]
            }
            try {
                `var`!!.value = value
            } catch (e: NullPointerException) {
                throw TypeErrorException("the type is error")
            }

            return `var`
        } else {
            val `var` = Var.compile(cmd)
            vars[key] = `var`
            memory_address_mapping[`var`.hashCode()] = `var`
            return `var`
        }
    }

    /**
     * 列出内存的变量
     * @param cmd
     * @return
     */
    //list
    private fun listVar(cmd: String): String {
        if (cmd.matches(Memory.matches["list"]!!.toRegex())) {
            val builder = StringBuilder()
            for ((key, value) in vars) {
                builder.append(key)
                builder.append("-")
                builder.append(value)
                builder.append("\n")
            }
            return builder.toString()
        }
        return cmd
    }
    //unset a=0

    /**
     * 执行重置指令
     * @param command
     * @param vars
     * @return
     */
    private fun unset(command: String, vars: Map<String, Var>): String {
        if (command.matches(Memory.matches["unset"]!!.toRegex())) {
            val unsets = command.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val name: String
            if (unsets[1].contains("=")) {
                name = unsets[1].substring(0, unsets[1].indexOf("="))
            } else {
                name = unsets[1]
            }
            val `var` = vars[name] ?: return "no such var"
            `var`.unset()
            return unsets[1]
        }
        return command
    }

    /**
     * 执行一个函数
     * @param func
     * @return
     */
    fun executeFunction(func: String, vars: Map<String, Var>): Any? {
        if (!func.matches(Memory.matches["func"]!!.toRegex())) {
            return func
        }
        val strs = func.split("=|:\\*".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        var funcName = func
        if (strs.size == 2) {
            funcName = strs[1]
        }
        val last = ")"
        var args: Array<String?>
        if (funcName.contains("){") && funcName.contains("}")) {

            val before = funcName.substring(0, funcName.indexOf("){") + 1)
            args = before.substring(before.indexOf("(") + 1, before.lastIndexOf(last)).split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        } else {
            args = funcName.substring(funcName.indexOf("(") + 1, funcName.lastIndexOf(last)).split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        }

        args = setThat(vars, *args)
        for (i in args.indices) {
            if (!args[i]!!.isEmpty()) {
                args[i] = executeFunction(args[i]!!.trim { it <= ' ' }, vars).toString()
            }
        }
        val before = funcName
        funcName = funcName.replace("\\(([\\s\\S]+|[\\s\\S]*)\\)".toRegex(), "")
        var funcCode = ""
        //native方法提供了funcCode
        if (before.matches(Memory.matches["ffunc"]!!.toRegex())) {
            funcCode = before.substring(before.indexOf("{") + 1, before.lastIndexOf("}"))
            funcName = before.substring(0, before.indexOf("{")).replace("\\(([\\s\\S]+|[\\s\\S]*)\\)".toRegex(), "")
            args = Arrays.copyOf(args, args.size + 1)
            args[args.size - 1] = funcCode
        }
        val function = functions[funcName]
                ?: return "error:no such function!" + funcName + "on " + func.indexOf(funcName) + " error"

        //普通函数还没开始处理
        //形式参数作用域在方法，方法执行完则销毁
        val vars_func = HashMap<String, Var>()
        vars_func.putAll(vars)

        if (function is NativeFunction<*>) {
            val `object` = (function as NativeFunction<*>).execute(args, vars_func)
            return `object` ?: "NULL"
        }

        //形式参数
        val alls = function.getArgs()
        //形式参数
        for (i in args.indices) {
            if (!alls[i].isEmpty()) {
                var name = alls[i]
                var get = "="
                if (name.startsWith("*")) {
                    get = ":*"
                    name = getPtrName(name)
                    val memory = memory_address_mapping[Integer.parseInt(args[i])]
                            ?: return "type is error: the " + args[i] + " is not pointer type"
                }
                vars_func[name] = Var.compile(name + get + "d:" + args[i])
            }
        }

        return executeCommonFunc(function, vars_func)
    }

    fun executeCommonFunc(function: Function, vars_func: MutableMap<String, Var>): Any? {
        val code = function.getCode()

        val codes = code.split(";".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()

        for (i in codes.indices) {
            val result = parse(codes[i], vars_func, vars)
            if (result.toString().startsWith("returned")) {
                return setThat(vars_func, result.toString().substring("return:".length))[0]
            }
        }
        return "NULL"
    }

    /**
     * 解析函数的返回变量
     * @param func
     * @return
     */
    private fun getFunctionVarName(func: String): String? {
        if (func.matches(Memory.matches["func"]!!.toRegex())) {
            val strs = func.split("=|:\\*".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            if (strs.size == 2) {
                return strs[0]
            }
        }
        return null
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
    private fun defineFunction(func: String): String {
        if (!func.matches(Memory.matches["dfunc"]!!.toRegex())) {
            return func
        }
        val function = Function.compile(func)
        functions[function.getFunctionName()] = function
        return "create successfully"
    }


    /**
     * ***a
     * ***和a
     * 0   1           2              3
     * a->对应hash值->Memory的名字->hash值
     * @param name
     * @return
     */
    fun getPtrValue(name: String, vars: Map<String, Var>): String {
        return if (!name.matches("[\\*]+[a-zA-Z_$]+".toRegex())) {
            name
        } else findVar(name, vars)!!.value
    }

    fun findVar(name: String, vars: Map<String, Var>): Var? {
        var name = name
        val len = getPtrLen(name)
        name = getPtrName(name)
        var nowValue: Var? = null
        for (i in 0 until len) {
            if (i == 0) {
                val address = Integer.parseInt(vars[name]?.getValue())
                val memory = memory_address_mapping[address]
                nowValue = memory as Var
            } else if (i % 2 == 0) {
                nowValue = vars[nowValue!!.value]
            } else {
                val memory = memory_address_mapping[Integer.parseInt(nowValue!!.value)]
                if (memory is Var) {
                    nowValue = memory
                }
            }
        }
        return nowValue
    }

    fun getPtrLen(name: String): Int {
        var len = 0
        val chars = name.toCharArray()
        for (chara in chars) {
            if (chara == '*') {
                len++
            } else {
                return len
            }
        }
        return len
    }

    fun getPtrName(name: String): String {
        return name.substring(getPtrLen(name))
    }

    fun getVars(): Map<String, Var> {
        return vars
    }

    companion object {

        var script: EmeraldScriptVMKotlinNative? = EmeraldScriptVMKotlinNative()
            private set
        init {
            script!!.functions["echo"] = EchoFunction()
            script!!.functions["typeof"] = TypeOfFunction()
            script!!.functions["import"] = ImportFunction()
            script!!.functions["register"] = RegisterNativeFunction()
            script!!.functions["if"] = IfFunction()
            script!!.functions["return"] = ReturnFunction()
            script!!.functions["+"] = StringAddFunction()
        }



        /**
         * 检查是否符合语法
         * @param command
         * @return
         */
        fun matchPattern(command: String): Boolean {
            val patterns = Memory.matches
            for (pattern in patterns.values) {
                if (command.matches(pattern.toRegex())) {
                    return true
                }
            }
            return false
        }


        /**
         * 解析局部变量的参数
         * @param command
         * @param vars
         * @return
         */
        fun parse(command: String, vars: MutableMap<String, Var>, parent: Map<String, Var>): Any? {
            var result: Any? = null
            //执行函数可以返回值
            //a=echo()
            /* 定义函数 */
            result = script!!.defineFunction(command)
            if (result != command) {
                return result
            }
            /* 将变量设置 */
            result = script!!.parseVar(script!!.unset(command, vars), vars)
            if (result != command) {
                return result
            }

            result = script!!.listVar(command)
            if (result != command) {
                return result
            }
            /* 执行函数，并设置返回值 */
            result = script!!.executeFunction(command, vars)

            if (result.toString().startsWith("error:")) {
                return result
            }

            /* 获取返回值 */
            val varName = script!!.getFunctionVarName(command)
            if (varName != null) {
                val cmd: String
                if (command.matches(Memory.matches["ptr"]!!.toRegex())) {
                    cmd = "$varName:*$result"
                } else {
                    cmd = "$varName=$result"
                }
                result = script!!.parseVar(varName, result.toString(), vars, cmd).value
            }

            return result
        }

        fun parse(command: String): String {
            return parse(command, script!!.vars, script!!.vars).toString()
        }

        /**
         * 给命令参数设置变量
         * @param args
         * @return
         */
        fun setThat(vars: Map<String, Var>, vararg args: String?): Array<String?> {
            var args = args
            val lists = LinkedList<String>()
            try {
                var i = 0
                while (i < args.size) {
                    val builder = StringBuilder()
                    if (args[i] != "''") {
                        if (args[i]!!.toString().startsWith("'")) {
                            while (!builder.toString().endsWith("'")) {
                                builder.append(args[i])
                                if (!args[i]!!.toString().endsWith("'"))
                                    builder.append(",")
                                i++
                            }
                            i--
                        }
                    }
                    if (builder.toString().isEmpty()) {
                        builder.append(args[i])
                    }

                    lists.add(builder.toString())
                    i++
                }
            } catch (e: ArrayIndexOutOfBoundsException) {
                throw TypeErrorException("the type must be STRING")
            }

            args = lists.toTypedArray()
            val dArgs = arrayOfNulls<String>(args.size)
            var i = 0
            //关于变量
            for (arg in args) {
                var lo = arg
                val pattern = Pattern.compile("\\$\\{[\\*]+[a-zA-Z_$]+\\}")
                val matcher = pattern.matcher(arg)
                while (matcher.find()) {
                    val group = matcher.group()
                    val value = getStringVal(script!!.getPtrValue(group.substring(group.indexOf("{") + 1, group.lastIndexOf("}")), vars))
                    lo = lo.replace(group, value)
                }
                for ((key, value) in vars) {
                    lo = lo.replace("\${global::$key}", getStringVal(script!!.getVars()[key]!!.getValue()))
                    lo = lo.replace("\${$key}", getStringVal(value.value))
                }
                //字符串'${}ssadads'+''
                val f = script!!.functions["+"] as NativeFunction<*>
                lo = f.execute(arrayOf(lo)).toString()
                dArgs[i] = lo

                i++
            }
            return dArgs
        }

        private fun getStringVal(globalVar: String): String {
            var globalVar = globalVar
            if (globalVar.startsWith("'") && globalVar.endsWith("'")) {
                globalVar = globalVar.substring(globalVar.indexOf("'") + 1, globalVar.lastIndexOf("'"))
            }
            return globalVar
        }
    }
}
