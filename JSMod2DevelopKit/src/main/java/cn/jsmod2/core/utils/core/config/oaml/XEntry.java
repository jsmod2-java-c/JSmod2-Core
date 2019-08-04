/*Culesberry technolegy Co. Ltd. (c) 2019-2020
 * 
 * Stating that the software,the software belongs Gulesberry
 * noyark open source group,noyark has all the power to interpret
 * and copyright information for the software prohibit organizations
 * and individuals conduct their business practices and illegal practices,
 * projects of: magiclu,Chinese name *Changcun Lu*.The software has nothing
 * to do with current politics,free software is the purpose of noyark
 * 
 * noyark-system info:
 * 	****************************************************
 * 											www.noyark.net
 *		 ****************************************************
 * 
 * 
 */
package cn.jsmod2.core.utils.core.config.oaml;

import cn.jsmod2.core.utils.core.config.oaml.exception.AmbiguousTypeException;
import cn.jsmod2.core.utils.core.config.oaml.exception.IllegalTypeException;
import cn.jsmod2.core.utils.core.config.oaml.exception.NoSuchNodeException;
import cn.jsmod2.core.utils.core.config.oaml.tree.Document;
import cn.jsmod2.core.utils.core.config.oaml.tree.NoCompile;
import cn.jsmod2.core.utils.core.config.oaml.tree.Node;
import cn.jsmod2.core.utils.core.config.oaml.utils.CollectionFactory;
import cn.jsmod2.core.utils.core.config.oaml.utils.OrderMap;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <P>The key-value pair object is written into the 
 * configuration file in a tree structure.
 * <P>It is called the object set target tree,and 
 * each key-value pair is in the same <code>Document</code>.
 * The objects in the container are unique and corresponding,
 * so each object is only allowed to be read and
 * written once,also for the uniqueness of the object
 * 
 * @author magiclu550
 * @since JDK1.8
 * @since oaml 001
 * @see Node
 * @see Document
 *
 */
public class XEntry implements Node {
	/**
	 * the default serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	
	/** The node's name */
	private String name;
	/** The node's value ,default is empty */
	private String value = "";
	/** The node's path,default is empty */
	private String path = "";
	/** the array point number,use <code>getValue</code>,if the
	 * value is array,the point will +1
	 * */
	private int arraySeek;
	/** the parent node,if the node is root,the parent will be null */
	private Node parent;
	/** the node's sons node*/
	private List<Node> sons;
	/** the document(NodeFactory) */
	private Document factory;
	/** the path will the index */
	private String spath;
	/** the son path with index */
	private static Map<String, Integer> index;
	/** the notes of this node*/
	private List<NoCompile> notes;
	static {
		index = CollectionFactory.getMap(STRING,CINT);
	}
	/**
	 * <P>The entry constructor,build the entry and the index.
	 * Each of these nodes is the only object that exists in
	 * the document.When used,the only document is written 
	 * @param name the entry name
	 * @param parent the node's parent
	 * @param factory the factory
	 */
	@SuppressWarnings("static-access")
	public XEntry(String name, Node parent, Document factory) {
		this.name = name;
		this.parent = parent;
		int index = this.index.get(this.path)==null?0:this.index.get(this.path);
		if(parent!=null) {
			this.path = parent.getPath()+"."+name;
			this.spath = parent.getSpath()+"."+name+"["+(this.index.get(this.path)==null?0:this.index.get(this.path))+"]";
		}else {
			this.path = name;
			this.spath = name;
		}
		this.index.put(this.path,index+1);
		this.sons = CollectionFactory.getList(NODE);
		this.factory = factory;
		this.notes = CollectionFactory.getList(NOCOMPILE);
	}
	
	/**
	 * <P>It is used to set ordinary strings,and also supports
	 * setting arrays.The string format is "[1,2,3]", etc.
	 * <P>Other setting types are also available,but it is not
	 * recommended to use this method .Other objects have
	 * specific methods.To set.
	 * @param value THE NODE ‘S VALUE
	 */
	
	@Override
	public void setValue(String value) {
		this.value = value;
	}
	
	/**
	 * <P>
	 * Used TO GET ORDINARY STRING and string array types.
	 * The array uses pointers by default.Each time it gets,
	 * the array is subscripted with +1.Although it can be used
	 * to get other types.
	 * <P>it is definitely not recommended,
	 * especially when getting some types will result in data problem
	 * @return the value of this node
	 */
	
	@Override
	public String getValue() {
		if(!isArray()) {
			return value.trim().replaceAll("\\\\n", (char)13+"");
		}else {
			String value = this.value.replaceAll("(\\[|\\])","");
			String[] values = value.split(",");
			String trueValue;
			if(arraySeek<values.length) {
				trueValue = values[arraySeek];
			}else {
				trueValue = "";
			}
			arraySeek++;
			return trueValue;
		}
	}

	/**
	 * This method can get a Array's value that is this index,just get
	 * the String type
	 * @param index the array index
	 * @return the value
	 */
	
	@Override
	public String getArray(int index) {
		if(!isArray()) {
			return value;
		}else {
			String array = value.replaceAll("[\\[\\]]+","");
			String[] values = array.replace(" ","").split(",");
			return values[index].trim();
		}
	}
	
	/**
	 * <P>To create a subtree node under the node ,you can 
	 * create multiple nodes with the same name,but the
	 * serial number of the node with the same name is
	 * different.
	 * <P>After obtaining the child node later,you
	 * need to obtain the node set or get the first node 
	 * through the node name
	 * @param name the new node's name
	 * @return this node
	 */
	
	@Override
	public Node createNode(String name) {
		Node node = new XEntry(name,this, factory);
		sons.add(node);
		factory.getAllNode().put(node.getPath(), node);
		return node;
	}
	
	/**
	 * This method can get the position of the
	 * element in the same-named element of the
	 * same level.
	 * @return the position
	 */
	
	@Override
	public int getIndex() {
		return index.get(path);
	}
	
	/**
	 * <P>Gets the parent of the node,and returns a null
	 * value if the node itself does not have a parent.
	 * <P>Therebefore,after obtaining the parent node,it is
	 * recommended to determine whether there is a parent
	 * node and the operate
	 * @return the parent node object
	 */
	
	@Override
	public Node getParent() {
		return parent;
	}
	
	/**
	 * <P>You can get the path information of the node in the
	 * document,but you can't display the coordinate points.
	 * <P>If you want to take the coorinate points,you can use the
	 * <code>getSpath</code> method,which is in the form of a 
	 * point path,such as 'a.b.c.d'.
	 * @return the path string
	 */
	
	@Override
	public String getPath() {
		return path;
	}
	
	/**
	 * You can get the name information of the node.If the node name
	 * is "node",then the nam of node is obtained,and the related path
	 * will not be obtained.
	 * @return this node's name
	 */
	
	@Override
	public String getName() {
		return name;
	}
	
	/**
	 * This method can return whether the value of the node is an array
	 * type,that is,the value in the format [value,value,value...]
	 * @return if the value is array,will return true,elif is false
	 */
	
	@Override
	public boolean isArray() {
		return value.matches("\\[[\\s\\S]+\\]");
	}
	
	/**
	 * <P>The method is used to obtain all the child nodes under the node,
	 * and returns a list,where all the child nodes are stored in the list,
	 * the set does not include the node,and the child nodes of the child
	 * node are not included
	 * @return all the subnodes
	 */
	
	@Override
	public List<Node> getSons() {
		return sons;
	}
	
	/**
	 *<P>The array can be written to the value of the node,
	 *and is also added to the value in the form of 
	 *"[value,value,value]".
	 *<P>The specific principle is the same as the array.
	 *It can be obtained either as a collection or as an 
	 *array.In oaml,arrays and collections are one thing.
	 * @param obj the array object
	 */
	
	@Override
	public void setArray(Object obj) {
		Class<?> type = obj.getClass();
		if(obj.getClass().isArray()) {
			boolean isInteger = type.equals($INT);
			boolean isBoolean = type.equals($BOOLEAN);
			boolean isLong = type.equals($LONG);
			boolean isCharacter = type.equals($CHAR);
			boolean isByte = type.equals($BYTE);
			boolean isShort = type.equals($SHORT);
			boolean isDouble = type.equals($DOUBLE);
			boolean isFloat = type.equals($FLOAT);
			if(isBoolean){
				this.value = Arrays.toString((boolean[])obj).replace(" ","");
			}else if(isByte){
				this.value = Arrays.toString((byte[])obj).replace(" ","");
			}else if(isCharacter){
				this.value = Arrays.toString((char[])obj).replace(" ","");
			}else if(isDouble){
				this.value = Arrays.toString((double[])obj).replace(" ","");
			}else if(isFloat){
				this.value = Arrays.toString((float[])obj).replace(" ","");
			}else if(isLong){
				this.value = Arrays.toString((long[])obj).replace(" ","");
			}else if(isInteger){
				this.value = Arrays.toString((int[])obj).replace(" ","");
			}else if(isShort){
				this.value = Arrays.toString((short[])obj).replace(" ","");
			}else {
				StringBuilder builder = new StringBuilder("["+(char)13);
				int index = path.split("\\.").length;
				for(Object o:(Object[])obj) {
					for(int i =0;i<index+4;i++) {
						builder.append("\t");
					}
					builder.append(setObject(o)+","+(char)13);	
				}
				builder = new StringBuilder(builder.substring(0,builder.toString().length()-2)+(char)13);
				for(int i = 0;i<index+1;i++) {
					builder.append("\t");
				}
				//对于对象数组
				this.value = (builder+"]").replace(" ","");
			}
		}else {
			throw new IllegalTypeException("this type is not array");
		}
	}
	
	/**
	 *<P>The set can be written to the value of the node,
	 *and is also added to the value in the form of 
	 *"[value,value,value]".
	 *<P>The specific principle is the same as the array.
	 *It can be obtained either as a collection or as an 
	 *array.In oaml,arrays and collections are one thing.
	 * @param list the list object
	 */
	
	@Override
	public void setList(@SuppressWarnings("rawtypes") List list) {
		this.value = list.toString().replace(" ","");
	}
	
	/**
	 * <P>This method can obtain all the child nodes of the
	 * name under the node by the node name,(node name is 
	 * the key name),and return it as a collection .
	 * <P>Note that the child elements of the child element are 
	 * not included ,and the existing elements are not created.
	 * <P>return an empty set if the element does not exist
	 * @param name the subnodes' name
	 * @return the node object
	 */
	
	@Override
	public Node getSonsByName(String name){
		for(Node node:sons) {
			if(node.getName().equals(name)) {
				return node;
			}
		}
		throw new NoSuchNodeException("no such node named "+name);
	}
	
	/**
	 * You can convert the value of the node with the format
	 * "[value,value]" into a collection for and return a collection
	 * object.When you get the value,you can get it directly from
	 * collection.
	 * @return the list object
	 */
	
	@Override
	public List<String> getList() {
		String[] lists = value.replace(" ","").split(",");
		return Arrays.asList(lists);
	}
	
	/**
	 * <P>You can convert a normal type array to a string
	 * type,but it does not support conversion to an object.
	 * <P>of course,the object array will return the object's 
	 * string type.
	 * @return the string array
	 */
	
	@Override
	public String[] getArray(){
		String[] liStrings = value.replaceAll("[\\[\\]]+","").replace(" ","").split(",");
		return liStrings;
	}
	
	/**
	 *You can treat any type as a string,including
	 *array types.If you don't want the data in []
	 *format to be converted to an array type,you can
	 *use this parsing mechanism to return a complete 
	 *string.
	 * @return the string (include the array to string)
	 */
	
	@Override
	public String getString() {
		return value;
	}
	
	/**
	 * The path and coordinate information of the node can
	 * be otained,and the coordinate include the coordinate 
	 * information of the upper level, and the return format
	 * a.b[0].c[0]
	 * @return the spath
	 */
	
	public String getSpath() {
		return spath;
	}
	
	/**
	 * <P>According to the type of the passed Class,
	 * the object type data of the oaml configuration
	 * file can be assembled into a java object,and an 
	 * object of the type can be returned.
	 * <P>If the object does not exist or is not an object
	 * type,an exception is thrown.
	 * @param type the object's type,if the object is Test 
	 * 				type,you can use 'Test.class' object
	 * @return the java object compiled from oaml
	 * @throws InstantiationException it will throw exception
	 * 																if it is not object type
	 */
	
	@Override
	public <T> T getObject(Class<T> type) throws InstantiationException{
		String value = this.getValue();
		return getObject(value, type);
	}
	
	/**
	 * <P>Objects can be set to values,stored as object types,
	 * with () as the object format,and return the value of 
	 * the successfully converted oaml object type;
	 * @param obj the object that will be set
	 * @return the oaml object type value
	 */
	
	@Override
	public String setObject(Object obj) {
		if(obj == null) {
			return null;
		}
		Field[] fields = obj.getClass().getDeclaredFields();
		StringBuilder builder = new StringBuilder("(");
		//(,,,)
		for(Field f:fields){
			f.setAccessible(true);
			String name = f.getName();
			String value = null;
			try {
				Class<?> type = f.getType();
				boolean isCommon = type.equals(STRING)||type.equals(INT)||type.equals(CINT)||
						type.equals(BOOLEAN)||type.equals(CBOOLEAN)||type.equals(CLONG)||type.equals(LONG)||
						type.equals(CHAR)||type.equals(CCHAR)||type.equals(BYTE)||type.equals(CBYTE)||type.equals(SHORT)||
						type.equals(CSHORT)||type.equals(DOUBLE)||type.equals(CDOUBLE)||type.equals(FLOAT)||type.equals(CFLOAT);
				if(isCommon){
					value = f.get(obj)+"";
				}else if(f.getType().isArray()){
					if(f.getType().equals($INT)){
						value = Arrays.toString((int[])f.get(obj)).replace(" ", "");
					}else if(f.getType().equals($BOOLEAN)){
						value = Arrays.toString((boolean[])f.get(obj)).replace(" ","");
					}else if(f.getType().equals($LONG)){
						value = Arrays.toString((long[])f.get(obj)).replace(" ","");
					}else if(f.getType().equals($DOUBLE)){
						value = Arrays.toString((double[])f.get(obj)).replace(" ","");
					}else if(f.getType().equals($FLOAT)){
						value = Arrays.toString((float[])f.get(obj)).replace(" ","");
					}else if(f.getType().equals($CHAR)){
						value = Arrays.toString((char[])f.get(obj)).replace(" ","");
					}else if(f.getType().equals($SHORT)){
						value = Arrays.toString((short[])f.get(obj)).replace(" ","");
					}else if(f.getType().equals($BYTE)){
						value = Arrays.toString((byte[])f.get(obj)).replace(" ","");
					}else{
						value = Arrays.toString((Object[])f.get(obj)).replace(" ","");
					}
				}else{
					value = f.get(obj)==null?null:f.get(obj).getClass().toString();
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			builder.append(name+":"+value+";");
		}
		this.setValue(builder.substring(0,builder.length()-1).toString()+")");
		return builder.substring(0,builder.length()-1).toString()+")";
	}
	
	/**
	 * This method can get the child node of the first
	 * name of the node.
	 * @param name the child node name
	 * @return the first child node of this name
	 */
	
	@Override
	public Node getEntry(String name) {
		for(Node n:sons) {
			if(n.getPath().equals(getPath()+"."+name)) {
				return n;
			}
		}
		throw new NoSuchNodeException("no such node named "+getPath()+"."+name);
	}
	
	/**
	 * This method is used to get an array of objects,that is,the value
	 * of [(),()] format ,where the type of the object must be specified,
	 * the method will automatically inject the object,you can use multiple 
	 * types and ont type,etc.
	 * @param defaultType the default type,when the second parameter has no
	 * 										value or the value is insufficient, it will default
	 * 										to this substitute.
	 * @param type the types,Corresponding object array subscript
	 * @return the object array(many types)
	 * @throws IllegalArgumentException Thrown to indicate that a method has been 
	 * 												passed an illegal or inappropriate argument.
	 * @throws IllegalAccessException An IllegalAccessException is thrown when an 
	 * 			application tries to reflectively create an instance (other than an array), 
	 * 			set or get a field, or invoke a method, but the currently executing method 
	 * 			does not have access to the definition of the specified class, field, method 
	 * 			or constructor.
	 * @throws InstantiationException Thrown when an application tries to create an instance 
	 * 					of a class using the newInstance method in class Class, but the specified class 
	 * 					object cannot be instantiated. The instantiation can fail for a variety of reasons 
	 * 					including but not limited to:
	 * the class object represents an abstract class, an interface, an array class, a primitive type
	 * , or void the class has no nullary constructor

	 */
	
	public Object[] getObjectArray(Class<?> defaultType,Class<?>... type) throws IllegalArgumentException, IllegalAccessException, InstantiationException {
		String[] strings = getString().replaceAll("(\\[|\\])","").split("\\),");
		Object[] os = new Object[strings.length];
		if(type.length==0) {
			for(int i = 0;i<strings.length;i++) {
				os[i] = getObject(strings[i],defaultType);
			}
		}else {
			for(int i = 0;i<strings.length;i++) {
				if(i<type.length) {
					os[i] = getObject(strings[i],type[i]);
				}else {
					os[i] = getObject(strings[i],defaultType);
				}
			}
		}
		return os;
	}
	
	/**
	 * This method is used to get an list of objects,that is,the value
	 * of [(),()] format ,where the type of the object must be specified,
	 * the method will automatically inject the object,you can use multiple 
	 * types and ont type,etc.
	 * 
	 * @param defaultType the default type,when the second parameter has no
	 * 										value or the value is insufficient, it will default
	 * 										to this substitute.
	 * @param type the types,Corresponding object array subscript
	 * @return the object array(many types)
	 * @throws IllegalArgumentException Thrown to indicate that a method has been 
	 * 												passed an illegal or inappropriate argument.
	 * @throws IllegalAccessException An IllegalAccessException is thrown when an 
	 * 			application tries to reflectively create an instance (other than an array), 
	 * 			set or get a field, or invoke a method, but the currently executing method 
	 * 			does not have access to the definition of the specified class, field, method 
	 * 			or constructor.
	 * @throws InstantiationException Thrown when an application tries to create an instance 
	 * 					of a class using the newInstance method in class Class, but the specified class 
	 * 					object cannot be instantiated. The instantiation can fail for a variety of reasons 
	 * 					including but not limited to:
	 * the class object represents an abstract class, an interface, an array class, a primitive type
	 * , or void the class has no nullary constructor
	 */
	
	public List<Object> getObjectList(Class<?> defaultType,Class<?>... type) throws IllegalArgumentException, IllegalAccessException, InstantiationException{
		Object[] objs = getObjectArray(defaultType, type);
		List<Object> list = CollectionFactory.getList(Object.class);
		for(Object obj:objs) {
			list.add(obj);
		}
		return list;
	}
	
	/**
	 * You can get all the child nodes of the
	 * name under the node,and combine them into
	 * an array to return.Note that there are only
	 * child nodes,there are no lower-level nodes,and
	 * the node order is based on the order of addition.
	 * @param name the node name
	 * @return the nodes that are this name
	 */
	
	@Override
	public List<Node> getEntries(String name){
		List<Node> nodes = new ArrayList<Node>();
		for(Node node:sons) {
			if(name.equals(node.getName())) {
				nodes.add(node);
			}
		}
		return nodes;
	}
	
	/**
	 * It can int type data,the premise data is in line
	 * with the int type standard,otherwise throw an 
	 * exception of type mismatch.
	 * @return the int type data
	 */
	
	
	@Override
	public int getInt() {
		return Integer.parseInt(getValue());
	}
	
	/**
	 * It can boolean type data,the premise data is in line
	 * with the boolean type standard,otherwise throw an 
	 * exception of type mismatch.
	 * @return the boolean type data
	 */
	
	@Override
	public boolean getBoolean() {
		return Boolean.parseBoolean(getValue());
	}
	
	/**
	 * It can long type data,the premise data is in line
	 * with the long type standard,otherwise throw an 
	 * exception of type mismatch.
	 * @return the long type data
	 */
	
	@Override
	public long getLong() {
		return Long.parseLong(getValue());
	}
	
	/**
	 * It can short type data,the premise data is in line
	 * with the short type standard,otherwise throw an 
	 * exception of type mismatch.
	 * @return the short type data
	 */
	
	@Override
	public short getShort() {
		return Short.parseShort(getValue());
	}
	
	/**
	 * It can byte type data,the premise data is in line
	 * with the byte type standard,otherwise throw an 
	 * exception of type mismatch.
	 * @return the byte type data
	 */
	
	@Override
	public byte getByte() {
		return Byte.parseByte(getValue());
	}
	
	/**
	 * It can double type data,the premise data is in line
	 * with the double type standard,otherwise throw an 
	 * exception of type mismatch.
	 * @return the double type data
	 */
	
	@Override
	public double getDouble() {
		
		return Double.parseDouble(getValue());
	}
	
	/**
	 * It can float type data,the premise data is in line
	 * with the float type standard,otherwise throw an 
	 * exception of type mismatch.
	 * @return the float type data
	 */
	
	
	@Override
	public float getFloat() {
		
		return Float.parseFloat(getValue());
	}
	
	/**
	 * It can char type data,the premise data is in line
	 * with the char type standard,otherwise throw an 
	 * exception of type mismatch.
	 * @return the char type data
	 */
	
	@Override
	public char getChar() {
		return getValue().charAt(0);
	}
	
	/**
	 * You can convert a child node to a key-value
	 * pair form,and add it as a map.The value in 
	 * the key-value pair does not support complex objects.
	 * It only supports ordinary strings and ordinary arrays.
	 * If it is an object that is too complicated,it will
	 * be returned as a string.
	 * @return the ordermap include entries
	 */
	
	@Override
	public OrderMap<String,Object> getSonsMap(){
		OrderMap<String, Object> map = CollectionFactory.getOrderMap(STRING, Object.class);
		for(Node node:sons) {
			Object o;
			if(node.isArray()) {
				o = node.getArray();
			}else {
				o = node.getValue();
			}
			String key;
			key = node.getName();
			map.put(key,o);
		}
		return map;
	}
	
	/**
	 * The specified child node object can be
	 * deleted ,where the parameter is the object
	 * to be deleted
	 * @param node the node will be deleted
	 */
	
	@Override
	public void removeChild(Node node) {
		sons.remove(node);
	}
	
	/**
	 * <P>You can get the time type,prescribing a
	 * good time format ,so that the parser looks
	 * for aqualified value
	 * @param format the date format 
	 * @return the date object(java.util.Date)
	 * @throws ParseException Signals that an error has 
	 * 					been reached unexpectedly while parsing.
	 */
	
	@Override
	public Date getDate(String format) throws ParseException {
		return new SimpleDateFormat(format).parse(getValue());
	}
	
	/**
	 * You can add comment information to the node.When writing,
	 * the comment information starting with # will be written at
	 * the corresponding node,but will not be retained during parsing.
	 *
	 * @param noteString the note message
	 */
	
	@Override
	public void addNote(String noteString) {
		NoCompile note = new Note(noteString);
		notes.add(note);
	}
	
	/**
	 * This method can get all the annotation information of the node,
	 * and use this object when writing.
	 * @return the NoCompile object
	 */
	
	@Override
	public List<NoCompile> getNotes(){
		return notes;
	}
	
	/**
	 * <P>According to the type of the passed Class,
	 * the object type data of the oaml configuration
	 * file can be assembled into a java object,and an 
	 * object of the type can be returned.
	 * <P>If the object does not exist or is not an object
	 * type,an exception is thrown.
	 * @param value the oaml object value,such as (a:b;c:f;f:a;o:p)
	 * @param objType the object's type,if the object is Test
	 * 				type,you can use 'Test.class' object
	 * @return the java object compiled from oaml
	 * @throws InstantiationException it will throw exception
	 * 																if it is not object type
	 */
	
	@SuppressWarnings("unchecked")
	public <T> T getObject(String value,Class<T> objType) throws IllegalArgumentException, InstantiationException {
		if(value.equals("null")) {
			return null;
		}
		if(value.indexOf("(")!=0) {
			throw new AmbiguousTypeException("this is not object type");
		}
		Object object;
		try {
			object =  objType.newInstance();
			Map<String, String> map = new LinkedHashMap<String, String>();
			String[] oamlFields = value.replaceAll("\\(|\\)","").split(";");
			for(String of:oamlFields) {
				String[] k_v = of.split(":");
				map.put(k_v[0], k_v[1]);
			}
			Field[] fields = objType.getDeclaredFields();
			
			for(Field field:fields){
				Class<?> type = field.getType();
				String name = field.getName();
				field.setAccessible(true);
				boolean isString =  type.equals(String.class);
				boolean isInteger = type.equals(CINT)||type.equals(INT);
				boolean isBoolean = type.equals(CBOOLEAN)||type.equals(BOOLEAN);
				boolean isLong = type.equals(CLONG)||type.equals(LONG);
				boolean isCharacter = type.equals(CHAR)||type.equals(CHAR);
				boolean isByte = type.equals(CBYTE)||type.equals(BYTE);
				boolean isShort = type.equals(CSHORT)||type.equals(SHORT);
				boolean isDouble = type.equals(CDOUBLE)||type.equals(DOUBLE);
				boolean isFloat = type.equals(CFLOAT)||type.equals(FLOAT);
				if(isString) {
					field.set(object, map.get(name));
				}else if(isBoolean){
					field.set(object, Boolean.parseBoolean(map.get(name)));
				}else if(isByte){
					field.set(object,Byte.parseByte(map.get(name)));
				}else if(isCharacter){
					field.set(object,Byte.parseByte(map.get(name)));
				}else if(isDouble){
					field.set(object,Byte.parseByte(map.get(name)));
				}else if(isFloat){
					field.set(object,Float.parseFloat(map.get(name)));
				}else if(isLong){
					field.set(object, Long.parseLong(map.get(name)));
				}else if(isInteger){
					field.set(object, Integer.parseInt(map.get(name)));
				}else if(isShort){
					field.set(object,Short.parseShort(map.get(name)));
				}else if(field.getType().isArray()) { 
					Object[] objects = map.get(name).replaceAll("\\[|\\]", "").split(",");
					if(field.getType().equals($INT)){
					int[] temp = new int[objects.length];
					for(int i = 0;i<objects.length;i++) {
						temp[i] = Integer.parseInt(objects[i]+"");
					}
					field.set(object, temp);
				}else if(field.getType().equals($BOOLEAN)){
					boolean[] temp = new boolean[objects.length];
					for(int i = 0;i<objects.length;i++) {
						temp[i] = Boolean.parseBoolean(objects[i]+"");
					}
					field.set(object, temp);
				}else if(field.getType().equals($LONG)){
					long[] temp = new long[objects.length];
					for(int i = 0;i<objects.length;i++) {
						temp[i] = Long.parseLong(objects[i]+"");
					}
					field.set(object, temp);
				}else if(field.getType().equals($DOUBLE)){
					double[] temp = new double[objects.length];
					for(int i = 0;i<objects.length;i++) {
						temp[i] = Double.parseDouble(objects[i]+"");
					}
					field.set(object, temp);
				}else if(field.getType().equals($FLOAT)){
					float[] temp = new float[objects.length];
					for(int i = 0;i<objects.length;i++) {
						temp[i] = Float.parseFloat(objects[i]+"");
					}
					field.set(object, temp);
				}else if(field.getType().equals($CHAR)){
					char[] temp = new char[objects.length];
					for(int i = 0;i<objects.length;i++) {
						temp[i] = (objects[i]+"").charAt(0);
					}
					field.set(object, temp);
				}else if(field.getType().equals($SHORT)){
					short[] temp = new short[objects.length];
					for(int i = 0;i<objects.length;i++) {
						temp[i] = Short.parseShort(objects[i]+"");
					}
					field.set(object, temp);
				}else if(field.getType().equals($BYTE)){
					byte[] temp = new byte[objects.length];
					for(int i = 0;i<objects.length;i++) {
						temp[i] = Byte.parseByte(objects[i]+"");
					}
					field.set(object, temp);
				}else {
					value = Arrays.toString((Object[])field.get(object)).replace(" ","");
				}
			}	
			}
		}catch(IllegalAccessException e) {
			return null;
		}
		return (T)object;
	}
	
	/**
	 * <P>Returns a string representation of the object. In general, the toString method returns a string 
	 * that "textually represents" this object. The result should be a concise but informative representation 
	 * that is easy for a pe(this.index.get(this.path)==null?0:this.index.get(this.path))rson to read. 
	 * <P>It is recommended that all subclasses override this method.
	 * The toString method for class Object returns a string consisting of the name of the class of which the 
	 * object is an instance, the at-sign character `@', and the unsigned hexadecimal representation of the hash 
	 * code of the object. In other words, this method returns a string equal to the value of:
	 */
	
	@Override
	public String toString() {
		return this.getClass().getName()+"@"+Integer.toHexString(hashCode())+"[name=" + name + ", value=" + value+"]";
	}
}
