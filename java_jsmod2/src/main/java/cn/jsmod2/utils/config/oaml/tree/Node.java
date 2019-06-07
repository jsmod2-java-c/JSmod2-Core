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
package cn.jsmod2.utils.config.oaml.tree;

import cn.jsmod2.utils.config.oaml.utils.OrderMap;
import cn.jsmod2.utils.config.oaml.XEntry;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * <P>The object of the class that implements
 * the interface represents a key-value pair
 * object in the oml file.The key-value pair
 * object is written into the configuration
 * file in a tree structure.
 * <P>It is called the object set target tree,and 
 * each key-value pair is in the same <code>Document</code>.
 * The objects in the container are unique and corresponding,
 * so each object is only allowed to be read and
 * written once,also for the uniqueness of the object
 * 
 * @author magiclu550
 * @since JDK1.8
 * @since oaml 001
 * @see XEntry
 * @see Document
 *
 */
public interface Node extends Serializable{
	/**
	 * This are the type dictionary,use in reflect
	 */
	public static final Class<Integer> INT = int.class;
	public static final Class<Character> CHAR = char.class;
	public static final Class<Long> LONG = long.class;
	public static final Class<Short> SHORT = short.class;
	public static final Class<Byte> BYTE = byte.class;
	public static final Class<Float> FLOAT = float.class;
	public static final Class<Double> DOUBLE = double.class;
	public static final Class<Boolean> BOOLEAN = boolean.class;
	public static final Class<Integer> CINT = Integer.class;
	public static final Class<Character> CCHAR = Character.class;
	public static final Class<Long> CLONG = Long.class;
	public static final Class<Short> CSHORT = Short.class;
	public static final Class<Byte> CBYTE = Byte.class;
	public static final Class<Float> CFLOAT = Float.class;
	public static final Class<Double> CDOUBLE = Double.class;
	public static final Class<Boolean> CBOOLEAN = Boolean.class;
	public static final Class<int[]> $INT = int[].class;
	public static final Class<char[]> $CHAR = char[].class;
	public static final Class<long[]> $LONG = long[].class;
	public static final Class<short[]> $SHORT = short[].class;
	public static final Class<byte[]> $BYTE = byte[].class;
	public static final Class<float[]> $FLOAT = float[].class;
	public static final Class<double[]> $DOUBLE = double[].class;
	public static final Class<boolean[]> $BOOLEAN = boolean[].class;
	public static final Class<String> STRING = String.class;
	public static final Class<Node> NODE = Node.class;
	public static final Class<NoCompile> NOCOMPILE = NoCompile.class;
	
	/**
	 * <P>It is used to set ordinary strings,and also supports
	 * setting arrays.The string format is "[1,2,3]", etc.
	 * <P>Other setting types are also available,but it is not
	 * recommended to use this method .Other objects have
	 * specific methods.To set.
	 * @param value THE NODE ‘S VALUE
	 */
	
	void setValue(String value);
	
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
	
	String getValue();
	
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
	
	Node createNode(String name);
	
	/**
	 * <P>Gets the parent of the node,and returns a null
	 * value if the node itself does not have a parent.
	 * <P>Therebefore,after obtaining the parent node,it is
	 * recommended to determine whether there is a parent
	 * node and the operate
	 * @return the parent node object
	 */
	
	Node getParent();
	
	/**
	 * <P>You can get the path information of the node in the
	 * document,but you can't display the coordinate points.
	 * <P>If you want to take the coorinate points,you can use the
	 * <code>getSpath</code> method,which is in the form of a 
	 * point path,such as 'a.b.c.d'.
	 * @return the path string
	 */
	
	String getPath();
	
	/**
	 * You can get the name information of the node.If the node name
	 * is "node",then the nam of node is obtained,and the related path
	 * will not be obtained.
	 * @return this node's name
	 */
	
	String getName();
	
	/**
	 * This method can return whether the value of the node is an array
	 * type,that is,the value in the format [value,value,value...]
	 * @return if the value is array,will return true,elif is false
	 */
	
	boolean isArray();
	
	/**
	 * <P>The method is used to obtain all the child nodes under the node,
	 * and returns a list,where all the child nodes are stored in the list,
	 * the set does not include the node,and the child nodes of the child
	 * node are not included
	 * @return all the subnodes
	 */
	
	List<Node> getSons();
	
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
	
	Node getSonsByName(String name);
	
	/**
	 * You can split the value of the [value,value]
	 * format type in oaml into an array,and get the
	 * string by array subscript.If the content is a
	 * string or other path type,data error may occur
	 * @param index the array index
	 * @return the string element of the array
	 */
	
	String getArray(int index);
	
	/**
	 *<P>The set can be written to the value of the node,
	 *and is also added to the value in the form of 
	 *"[value,value,value]".
	 *<P>The specific principle is the same as the array.
	 *It can be obtained either as a collection or as an 
	 *array.In oaml,arrays and collections are one thing.
	 * @param list the list object
	 */
	
	void setList(@SuppressWarnings("rawtypes") List list);
	
	/**
	 * You can convert the value of the node with the format
	 * "[value,value]" into a collection for and return a collection
	 * object.When you get the value,you can get it directly from
	 * collection.
	 * @return the list object
	 */
	
	List<?> getList();
	
	/**
	 *You can treat any type as a string,including
	 *array types.If you don't want the data in []
	 *format to be converted to an array type,you can
	 *use this parsing mechanism to return a complete 
	 *string.
	 * @return the string (include the array to string)
	 */
	
	String getString();
	
	/**
	 * <P>You can convert a normal type array to a string
	 * type,but it does not support conversion to an object.
	 * <P>of course,the object array will return the object's 
	 * string type.
	 * @return the string array
	 */
	
	String[] getArray();
	
	/**
	 * <P>Objects can be set to values,stored as object types,
	 * with () as the object format,and return the value of 
	 * the successfully converted oaml object type;
	 * @param obj the object that will be set
	 * @return the oaml object type value
	 */
	
	String setObject(Object obj);
	
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
	
	<T> T getObject(Class<T> type) throws InstantiationException;
	
	/**
	 * This method can get the child node of the first
	 * name of the node.
	 * @param name the child node name
	 * @return the first child node of this name
	 */
	
	Node getEntry(String name);
	
	/**
	 * This method can get the position of the
	 * element in the same-named element of the
	 * same level.
	 * @return the position
	 */
	
	int getIndex();
	
	/**
	 * You can get all the child nodes of the
	 * name under the node,and combine them into
	 * an array to return.Note that there are only
	 * child nodes,there are no lower-level nodes,and
	 * the node order is based on the order of addition.
	 * @param name the node name
	 * @return the nodes that are this name
	 */
	
	List<Node> getEntries(String name);
	
	/**
	 * It can int type data,the premise data is in line
	 * with the int type standard,otherwise throw an 
	 * exception of type mismatch.
	 * @return the int type data
	 */
	
	int getInt();
	
	/**
	 * It can boolean type data,the premise data is in line
	 * with the boolean type standard,otherwise throw an 
	 * exception of type mismatch.
	 * @return the boolean type data
	 */
	
	boolean getBoolean();
	
	/**
	 * It can long type data,the premise data is in line
	 * with the long type standard,otherwise throw an 
	 * exception of type mismatch.
	 * @return the long type data
	 */
	
	long getLong();
	
	/**
	 * It can short type data,the premise data is in line
	 * with the short type standard,otherwise throw an 
	 * exception of type mismatch.
	 * @return the short type data
	 */
	
	short getShort();
	
	/**
	 * It can byte type data,the premise data is in line
	 * with the byte type standard,otherwise throw an 
	 * exception of type mismatch.
	 * @return the byte type data
	 */
	
	byte getByte();
	
	/**
	 * It can double type data,the premise data is in line
	 * with the double type standard,otherwise throw an 
	 * exception of type mismatch.
	 * @return the double type data
	 */
	
	double getDouble();
	
	/**
	 * It can float type data,the premise data is in line
	 * with the float type standard,otherwise throw an 
	 * exception of type mismatch.
	 * @return the float type data
	 */
	
	float getFloat();
	
	/**
	 * It can char type data,the premise data is in line
	 * with the char type standard,otherwise throw an 
	 * exception of type mismatch.
	 * @return the char type data
	 */
	
	char getChar();
	
	/**
	 * You can convert a child node to a key-value
	 * pair form,and add it as a map.The value in 
	 * the key-value pair does not support complex objects.
	 * It only supports ordinary strings and ordinary arrays.
	 * If it is an object that is too complicated,it will
	 * be returned as a string.
	 * @return the ordermap include entries
	 */
	
	OrderMap<String, Object> getSonsMap();
	
	/**
	 * The specified child node object can be
	 * deleted ,where the parameter is the object
	 * to be deleted
	 * @param node the node will be deleted
	 */
	
	void removeChild(Node node);
	
	/**
	 * <P>You can get the time type,prescribing a
	 * good time format ,so that the parser looks
	 * for aqualified value
	 * @param format the date format 
	 * @return the date object(java.util.Date)
	 * @throws ParseException Signals that an error has 
	 * 					been reached unexpectedly while parsing.
	 */
	
	Date getDate(String format) throws ParseException;
	
	/**
	 *<P>The array can be written to the value of the node,
	 *and is also added to the value in the form of 
	 *"[value,value,value]".
	 *<P>The specific principle is the same as the array.
	 *It can be obtained either as a collection or as an 
	 *array.In oaml,arrays and collections are one thing.
	 * @param obj the array object
	 */
	
	void setArray(Object obj);
	
	/**
	 * The path and coordinate information of the node can
	 * be otained,and the coordinate include the coordinate 
	 * information of the upper level, and the return format
	 * a.b[0].c[0]
	 * @return the spath
	 */
	
	String getSpath();
	
	/**
	 * You can add comment information to the node.When writing,
	 * the comment information starting with # will be written at
	 * the corresponding node,but will not be retained during parsing.
	 *
	 * @param noteString the note message
	 */
	
	void addNote(String noteString);
	
	/**
	 * This method can get all the annotation information of the node,
	 * and use this object when writing.
	 * @return the NoCompile object
	 */
	
	List<NoCompile> getNotes();
	
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
	
	public Object[] getObjectArray(Class<?> defaultType, Class<?>... type) throws IllegalArgumentException, IllegalAccessException, InstantiationException;

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

	public List<Object> getObjectList(Class<?> defaultType, Class<?>... type) throws IllegalArgumentException, IllegalAccessException, InstantiationException;
}
