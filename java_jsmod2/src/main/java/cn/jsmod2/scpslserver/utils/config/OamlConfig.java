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
 */
package cn.jsmod2.scpslserver.utils.config;

import cn.jsmod2.oaml.DocumentFactory;
import cn.jsmod2.oaml.OamlReader;
import cn.jsmod2.oaml.OamlWriter;
import cn.jsmod2.oaml.tree.Document;
import cn.jsmod2.oaml.tree.Node;
import cn.jsmod2.oaml.utils.CollectionFactory;

import java.io.*;
import java.text.ParseException;
import java.util.*;
import java.util.Map.Entry;

/**
 * This configuration file class simplifies the
 * parsing and writing operations of oaml,concealing
 * node objects,merging parsing and writing,but the
 * underlying layer is still a node tree,but external 
 * users can learn the data without knowing the node 
 * tree structure,but here Nodes,paths are unique,that
 * is ,elements with the same name are not allowed at
 * the same level.
 * @author magiclu550
 * @since oaml 003
 * @since JDK1.8
 *
 */

public class OamlConfig extends Config {
	/** the oaml reader stream*/
	private OamlReader reader;
	/** the oaml writer stream*/
	private OamlWriter writer;
	/** the document object*/
	private Document doc;

	private Map<String, Node> haveExistPath;
	public OamlConfig(String fileName, boolean getClassPath) throws FileNotFoundException {
		this(fileName, DocumentFactory.getDocument(), getClassPath);
	}
	public OamlConfig(String fileName, Document doc, boolean getClassPath) throws FileNotFoundException {
		super(fileName,getClassPath);
		this.doc = doc;
		this.haveExistPath = CollectionFactory.getLinkedMap(String.class,Node.class);
	}
	
	/**
	 * This method can put all basic types and string
	 * types.If you put a special data object,you need 
	 * to use the object related method.The path here
	 * uses point splitting,such as a.b.c
	 * @param key the entry key
	 * @param value the entry value
	 * @throws FileNotFoundException #{@link FileNotFoundException}
	 */
	
	public void put(String key,Object value) throws FileNotFoundException {
		Node node = createNode(key);
		if(value.getClass().isArray()) {
			node.setArray(value);
			return;
		}
		node.setValue((value+"").replaceAll(" ",""));
	}
	
	/**
	 * Add a linked hashmap structure,the key-value pair of
	 * path-value in the linked list,and finally add it by
	 * put one by one.
	 * @param map the linkedhashmap (path-value entrys);
	 * @throws FileNotFoundException #{@link FileNotFoundException}
	 */
	
	public void putAll(LinkedHashMap<String, Object> map) throws FileNotFoundException {
		Set<Entry<String, Object>> set = map.entrySet();
		for(Entry<String, Object> entry:set) {
			String key = entry.getKey();
			Object value = entry.getValue();
			put(key, value);
		}
	}
	
	/**
	 * This method can get all the key-value pairs in
	 * the path and store them in the form of an ordered
	 * map.
	 * @param parentPath the parent key path
	 * @return the child key-value pair
	 * @throws IOException #{@link IOException}
	 */
	
	public Map<String,Object> getAll(String parentPath) throws IOException {
		this.in = new FileInputStream(fileName);
		this.reader = new OamlReader(); 
		this.doc = reader.read(in);
		if(parentPath.equals("")) {
			Map<String, Object> map = CollectionFactory.getLinkedMap(String.class,Object.class);
			Collection<Node> nodes = doc.getEntrys();
			for(Node node:nodes) {
				map.put(node.getName(), node.getValue());
			}
			return map;
		}
		Map<String, Object> map = CollectionFactory.getLinkedMap(String.class,Object.class);
		List<Node> parentNode = getNode(parentPath).getSons();
		for(Node node:parentNode) {
			if(node.getString().startsWith("[(")){
				map.put(node.getName(),node.getString());
			}else if(node.isArray()) {
				map.put(node.getName(), node.getArray());
			}else {
				map.put(node.getName(),node.getValue());
			}
		}
		return map;
	}
	
	/**
	 * It can get some basic data,such as strings,etc.,can
	 * also get an array through the array pointer,the array
	 * pointer principle here is the same as <code>getValue</code>
	 * @param key the node path
	 * @return return a object(String)
	 * @throws IOException #{@link IOException}
	 */
	
	public Object get(String key) throws IOException {
		return getNode(key).getValue();
	}

	public Object get(String key,InputStream in) throws IOException{
		return getNode(key,in).getValue();
	}
	
	/**
	 * It can int type data,the premise data is in line
	 * with the int type standard,otherwise throw an 
	 * exception of type mismatch.
	 * @param key the node path
	 * @return the int type data
	 * @throws IOException #{@link IOException}
	 */
	
	public int getInt(String key) throws IOException {
		return getNode(key).getInt();
	}
	
	/**
	 * It can byte type data,the premise data is in line
	 * with the byte type standard,otherwise throw an 
	 * exception of type mismatch.
	 * 
	 * @param key the node path
	 * @return the byte type data
	 * @throws IOException #{@link IOException}
	 */
	
	public byte getByte(String key) throws IOException {
		return getNode(key).getByte();
	}
	
	/**
	 * <P>You can convert a normal type array to a string
	 * type,but it does not support conversion to an object.
	 * <P>of course,the object array will return the object's 
	 * string type.
	 * 
	 * @param key the node path
	 * @return the string array
	 * @throws IOException #{@link IOException}
	 */
	
	public String[] getArray(String key) throws IOException {
		return getNode(key).getArray();
	}
	
	/**
	 * You can convert the value of the node with the format
	 * "[value,value]" into a collection for and return a collection
	 * object.When you get the value,you can get it directly from
	 * collection.
	 * @param key the node path
	 * @return the list object
	 * @throws IOException #{@link IOException}
	 */
	
	public List<?> getList(String key) throws IOException {
		return getNode(key).getList();
	}
	
	/**
	 * It can short type data,the premise data is in line
	 * with the short type standard,otherwise throw an 
	 * exception of type mismatch.
	 * 
	 * @param key the node path
	 * @return the short type data
	 * @throws IOException #{@link IOException}
	 */
	
	public short getShort(String key) throws IOException {
		return getNode(key).getShort();
	}
	
	/**
	 * It can boolean type data,the premise data is in line
	 * with the boolean type standard,otherwise throw an 
	 * exception of type mismatch.
	 * @param key the node path
	 * @return the boolean type data
	 * @throws IOException #{@link IOException}
	 */
	
	public boolean getBoolean(String key) throws IOException {
		return getNode(key).getBoolean();
	}
	
	/**
	 * It can long type data,the premise data is in line
	 * with the long type standard,otherwise throw an 
	 * exception of type mismatch.
	 * @param key the node path
	 * @return the long type data
	 * @throws IOException #{@link IOException}
	 */
	
	public long getLong(String key) throws IOException{
		return getNode(key).getLong();
	}
	
	/**
	 * It can double type data,the premise data is in line
	 * with the double type standard,otherwise throw an 
	 * exception of type mismatch.
	 * @param key the node path
	 * @return the double type data
	 * @throws IOException #{@link IOException}
	 */
	
	public double getDouble(String key) throws IOException {
		return getNode(key).getDouble();
	}
	
	/**
	 * It can float type data,the premise data is in line
	 * with the float type standard,otherwise throw an 
	 * exception of type mismatch.
	 * @param key the node path
	 * @return the float type data
	 * @throws IOException #{@link IOException}
	 */
	
	public float getFloat(String key) throws IOException {
		return getNode(key).getFloat();
	}
	
	/**
	 * It can char type data,the premise data is in line
	 * with the char type standard,otherwise throw an 
	 * exception of type mismatch.
	 * @param key the node path
	 * @return the char type data
	 * @throws IOException #{@link IOException}
	 */
	
	public char getChar(String key) throws IOException {
		return getNode(key).getChar();
	}
	
	/**
	 * You can split the value of the [value,value]
	 * format type in oaml into an array,and get the
	 * string by array subscript.If the content is a
	 * string or other path type,data error may occur
	 * @param key the node path
	 * @param index the array index
	 * @return the string element of the array
	 * @throws IOException #{@link IOException}
	 */
	
	public String getArrayValue(String key,int index) throws IOException {
		return getNode(key).getArray(index);
	}
	
	/**
	 * <P>You can get the time type,prescribing a
	 * good time format ,so that the parser looks
	 * for aqualified value
	 * @param key the node path
	 * @param dateformat the date format
	 * @return the date object(java.util.Date)
	 * @throws ParseException Signals that an error has 
	 * 					been reached unexpectedly while parsing.
	 * @throws IOException #{@link IOException}
	 */
	
	public Date getDate(String key,String dateformat) throws ParseException, IOException {
		return getNode(key).getDate(dateformat);
	}
	
	/**
	 * This method can remove the target entry
	 * @param key the node path
	 * @throws IOException #{@link IOException}
	 */
	
	@Override
	public void remove(String key) throws IOException {
		haveExistPath.get(key).getParent().removeChild(getNode(key));;
	}
	
	/**
	 * <P>According to the type of the passed Class,
	 * the object type data of the oaml configuration
	 * file can be assembled into a java object,and an 
	 * object of the type can be returned.
	 * <P>If the object does not exist or is not an object
	 * type,an exception is thrown.
	 * @param key the node path
	 * @param type the object's type,if the object is Test 
	 * 				type,you can use 'Test.class' object
	 * @return the java object compiled from oaml
	 * @throws InstantiationException it will throw exception
	 * 																if it is not object type
	 */
	
	@Override
	public Object getObject(String key,Class<?> type) throws InstantiationException, IllegalAccessException, IOException {
		return getNode(key).getObject(type);
	}
	
	/**
	 * This method is used to get an array of objects,that is,the value
	 * of [(),()] format ,where the type of the object must be specified,
	 * the method will automatically inject the object,you can use multiple 
	 * types and ont type,etc.
	 * @param key the node path
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
	
	public Object[] getObjectArray(String key,Class<?> defaultType,Class<?>... type) throws IllegalArgumentException, IllegalAccessException, InstantiationException, IOException {
		return getNode(key).getObjectArray(defaultType, type);
	}
	
	/**
	 * This method is used to get an list of objects,that is,the value
	 * of [(),()] format ,where the type of the object must be specified,
	 * the method will automatically inject the object,you can use multiple 
	 * types and ont type,etc.
	 * @param key the node path
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
	
	public List<Object> getObjectList(String key,Class<?> defaultType,Class<?>... type) throws IllegalArgumentException, IllegalAccessException, InstantiationException, IOException{
		return getNode(key).getObjectList(defaultType, type);
	}
	
	/**
	 * <P>Objects can be set to values,stored as object types,
	 * with () as the object format,and return the value of 
	 * the successfully converted oaml object type;
	 * @param key the node key
	 * @param obj the object that will be set
	 * @return the oaml object type value
	 */
	
	@Override
	public void setObject(String key,Object obj) throws IOException {
		createNode(key);
		if(haveExistPath.get(key)!=null) {
			haveExistPath.get(key).setObject(obj);
		}else {
			getNode(key).setObject(obj);
		}
	}
	
	/**
	 * it is used to save the config ,like the write,write into
	 * the target file.
	 * @throws UnsupportedEncodingException The Character Encoding 
	 * 																			is not supported.
	 * @throws FileNotFoundException Signals that an attempt to open 
	 * the file denoted by a specified pathname has failed.This exception
	 *  will be thrown by the FileInputStream, FileOutputStream, and 
	 *  RandomAccessFile constructors when a file with the specified 
	 *  pathname does not exist. It will also be thrown by these 
	 *  constructors if the file does exist but for some reason 
	 *  is inaccessible, for example when an attempt is made to open a 
	 *  read-only file for writing.
	 */
	
	public void save() throws UnsupportedEncodingException, FileNotFoundException {
		if(out==null) {
			this.out = new FileOutputStream(fileName);
			this.writer = new OamlWriter(out);
		}
		writer.write(doc);
		doc.getPool().clear();
	}
	
	/**
	 * This method can close the config stream
	 */
	
	public void close(){
		writer.close();
	}
	
	/**
	 * <P>
	 * The encoding format used to set the configuration file. 
	 * The default is UTF-8 format.
	 * @param cdn the charset format
	 */
	
	public void setEncoding(String cdn) {
		doc.setEncoding(cdn);
	}
	
	/**
	 * Can get the supported character set of the 
	 * current configuration file.
	 * @return the encoding type
	 */
	
	public String getEncoding() {
		return doc.getEncoding();
	}
	
	/**
	 * This method can get the document object of this config
	 * @return the document object
	 */
	
	public Document getDocument() {
		return doc;
	}
	
	/**
	 * You can add comment information to the node.When writing,
	 * the comment information starting with # will be written at
	 * the corresponding node,but will not be retained during parsing.
	 *
	 * @param key the node path that is added this note
	 * @param note the note message
	 */
	
	@Override
	public void setNote(String key, String note) throws IOException {
		Node node = createNode(key);
		node.addNote(note);
	}
	
	/**
	 * Returns a hash code value for the object. This method is supported for the benefit of hash tables such as those provided by java.util.HashMap.
	 * The general contract of hashCode is:
	 * Whenever it is invoked on the same object more than once during an execution of a Java application, the hashCode method must consistently 
	 * return the same integer, provided no information used in equals comparisons on the object is modified. This integer need not remain consistent 
	 * from one execution of an application to another execution of the same application 
	 * If two objects are equal according to the equals(Object) method, then calling the hashCode method on each of the two objects must produce the same integer result.
	 * It is not required that if two objects are unequal according to the java.lang.Object.equals(java.lang.Object) method, then calling the hashCode method on each of 
	 * the two objects must produce distinct integer results. However, the programmer should be aware that producing distinct integer results for unequal objects may improve 
	 * the performance of hash tables.
	 * As much as is reasonably practical, the hashCode method defined by class Object does return distinct integers for distinct objects. (This is typically implemented by 
	 * converting the internal address of the object into an integer, but this implementation technique is not required by the Java™ programming language.)
	 * @return the hashcode
	 */
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((doc == null) ? 0 : doc.hashCode());
		result = prime * result + ((in == null) ? 0 : in.hashCode());
		result = prime * result + ((out == null) ? 0 : out.hashCode());
		result = prime * result + ((reader == null) ? 0 : reader.hashCode());
		result = prime * result + ((writer == null) ? 0 : writer.hashCode());
		return result;
	}
	/**
	 * Indicates whether some other object is "equal to" this one.

<BR>The equals method implements an equivalence relation on non-null object references:

<BR>It is reflexive: for any non-null reference value x, x.equals(x) should return true.
<BR>It is symmetric: for any non-null reference values x and y, x.equals(y) should return true if and only if y.equals(x) returns true.
<BR>It is transitive: for any non-null reference values x, y, and z, if x.equals(y) returns true and y.equals(z) returns true, then x.equals(z) should return true.
<BR>It is consistent: for any non-null reference values x and y, multiple invocations of x.equals(y) consistently return true or consistently return false, provided no information used in equals comparisons on the objects is modified.
<BR>For any non-null reference value x, x.equals(null) should return false.
The equals method for class Object implements the most discriminating possible equivalence relation on objects; that is, for any non-null reference values x and y, this method returns true if and only if x and y refer to the same object (x == y has the value true).

Note that it is generally necessary to override the hashCode method whenever this method is overridden, so as to maintain the general contract for the hashCode method, which states that equal objects must have equal hash codes.

	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OamlConfig other = (OamlConfig) obj;
		if (doc == null) {
			if (other.doc != null)
				return false;
		} else if (!doc.equals(other.doc))
			return false;
		if (in == null) {
			if (other.in != null)
				return false;
		} else if (!in.equals(other.in))
			return false;
		if (out == null) {
			if (other.out != null)
				return false;
		} else if (!out.equals(other.out))
			return false;
		if (reader == null) {
			if (other.reader != null)
				return false;
		} else if (!reader.equals(other.reader))
			return false;
		if (writer == null) {
			if (other.writer != null)
				return false;
		} else if (!writer.equals(other.writer))
			return false;
		return true;
	}
	
	/**
	 * <P>To create a subtree node under the node ,you can 
	 * create multiple nodes with the same name,but the
	 * serial number of the node with the same name is
	 * different.
	 * <P>After obtaining the child node later,you
	 * need to obtain the node set or get the first node 
	 * through the node name
	 * @param key the new node's path
	 * @return this node
	 */
	
	private Node createNode(String key) {
		String[] keys = key.split("\\.");
		StringBuilder builder = new StringBuilder();
		List<String> allPath = CollectionFactory.getList(String.class);
		for(String linkey:keys) {
			builder.append(linkey+".");
			allPath.add(builder.toString());
		}
		Node node = doc.getEntry(keys[0]);
		for(int i = 1;i<keys.length;i++) {
			if(!allPath.get(i).isEmpty()&&haveExistPath.containsKey(allPath.get(i))) {
				node = haveExistPath.get(allPath.get(i));
				continue;
			}
			node = node.createNode(keys[i]);
			haveExistPath.put(allPath.get(i), node);
		}
		return node;
	}
	
	/**
	 * This method can get the node of the first
	 * path of the node.
	 * @param key the node path
	 * @return the first child node of this name
	 */
	
	private Node getNode(String key) throws IOException {
		return getNode(key,new FileInputStream(fileName));
	}

	private Node getNode(String key,InputStream in) throws IOException{
		this.in = in;
		this.reader = new OamlReader();
		this.doc = reader.read(in);
		String[] keys = key.split("\\.");
		Node node = doc.getEntry(keys[0]);
		for(int i = 1;i<keys.length;i++) {
			node = node.getEntry(keys[i]);
		}
		return node;
	}
}
