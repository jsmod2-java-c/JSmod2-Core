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
package net.noyark.oaml.simple;

import net.noyark.oaml.tree.Document;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
public interface OamlConfig {

   /**
    * This method can put all basic types and string
    * types.If you put a special data object,you need
    * to use the object related method.The path here
    * uses point splitting,such as a.b.c
    * @param key the entry key
    * @param value the entry value
    * @throws FileNotFoundException #{@link FileNotFoundException}
    */

   void put(String key, Object value) throws FileNotFoundException;

   /**
    * It can get some basic data,such as strings,etc.,can
    * also get an array through the array pointer,the array
    * pointer principle here is the same as <code>getValue</code>
    * @param key the node path
    * @return return a object(String)
    * @throws IOException #{@link IOException}
    */

   Object get(String key) throws IOException;

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

   void save() throws UnsupportedEncodingException, FileNotFoundException;

   /**
    * Add a linked hashmap structure,the key-value pair of
    * path-value in the linked list,and finally add it by
    * put one by one.
    * @param map the linkedhashmap (path-value entrys);
    * @throws FileNotFoundException #{@link FileNotFoundException}
    */

   void putAll(LinkedHashMap<String, Object> map) throws FileNotFoundException;

   /**
    * It can int type data,the premise data is in line
    * with the int type standard,otherwise throw an
    * exception of type mismatch.
    * @param key the node path
    * @return the int type data
    * @throws IOException #{@link IOException}
    */

   int getInt(String key) throws IOException;

   /**
    * It can byte type data,the premise data is in line
    * with the byte type standard,otherwise throw an
    * exception of type mismatch.
    *
    * @param key the node path
    * @return the byte type data
    * @throws IOException #{@link IOException}
    */

   byte getByte(String key) throws IOException;

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

   String[] getArray(String key) throws IOException;

   /**
    * You can convert the value of the node with the format
    * "[value,value]" into a collection for and return a collection
    * object.When you get the value,you can get it directly from
    * collection.
    * @param key the node path
    * @return the list object
    * @throws IOException #{@link IOException}
    */

   List<?> getList(String key) throws IOException;

   /**
    * It can short type data,the premise data is in line
    * with the short type standard,otherwise throw an
    * exception of type mismatch.
    *
    * @param key the node path
    * @return the short type data
    * @throws IOException #{@link IOException}
    */

   short getShort(String key) throws IOException;

   /**
    * It can boolean type data,the premise data is in line
    * with the boolean type standard,otherwise throw an
    * exception of type mismatch.
    * @param key the node path
    * @return the boolean type data
    * @throws IOException #{@link IOException}
    */


   boolean getBoolean(String key) throws IOException;

   /**
    * It can long type data,the premise data is in line
    * with the long type standard,otherwise throw an
    * exception of type mismatch.
    * @param key the node path
    * @return the long type data
    * @throws IOException #{@link IOException}
    */

   long getLong(String key) throws IOException;

   /**
    * It can double type data,the premise data is in line
    * with the double type standard,otherwise throw an
    * exception of type mismatch.
    * @param key the node path
    * @return the double type data
    * @throws IOException #{@link IOException}
    */


   double getDouble(String key) throws IOException;

   /**
    * It can float type data,the premise data is in line
    * with the float type standard,otherwise throw an
    * exception of type mismatch.
    * @param key the node path
    * @return the float type data
    * @throws IOException #{@link IOException}
    */


   float getFloat(String key) throws IOException;

   /**
    * It can char type data,the premise data is in line
    * with the char type standard,otherwise throw an
    * exception of type mismatch.
    * @param key the node path
    * @return the char type data
    * @throws IOException #{@link IOException}
    */


   char getChar(String key) throws IOException;

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

   String getArrayValue(String key, int index) throws IOException;

   /**
    * <P>You can get the time type,prescribing a
    * good time format ,so that the parser looks
    * for aqualified value
    * @param key the node path
    * @param format the date format
    * @return the date object(java.util.Date)
    * @throws ParseException Signals that an error has
    * 					been reached unexpectedly while parsing.
    * @throws IOException #{@link IOException}
    */

   Date getDate(String key, String format) throws ParseException, IOException;

   /**
    * This method can remove the target entry
    * @param key the node path
    * @throws IOException #{@link IOException}
    */

   void remove(String key) throws IOException;

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

   Object getObject(String key, Class<?> type) throws InstantiationException, IllegalAccessException, IOException;

   /**
    * <P>Objects can be set to values,stored as object types,
    * with () as the object format,and return the value of
    * the successfully converted oaml object type;
    * @param key the node key
    * @param obj the object that will be set
    * @return the oaml object type value
    */

   void setObject(String key, Object obj) throws IOException;

   /**
    * This method can close the config stream
    */

   void close();

   /**
    * <P>
    * The encoding format used to set the configuration file.
    * The default is UTF-8 format.
    * @param cdn the charset format
    */

   void setEncoding(String cdn);

   /**
    * Can get the supported character set of the
    * current configuration file.
    * @return the encoding type
    */

   String getEncoding();

   /**
    * This method can get the document object of this config
    * @return the document object
    */

   Document getDocument();

   /**
    * This method can get all the key-value pairs in
    * the path and store them in the form of an ordered
    * map.
    * @param parentPath the parent key path
    * @return the child key-value pair
    * @throws IOException #{@link IOException}
    */

   Map<String,Object> getAll(String parentPath) throws IOException;

   /**
    * You can add comment information to the node.When writing,
    * the comment information starting with # will be written at
    * the corresponding node,but will not be retained during parsing.
    *
    * @param key the node path that is added this note
    * @param note the note message
    */

   void setNote(String key, String note) throws IOException;

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


   Object[] getObjectArray(String key, Class<?> defaultType, Class<?>... type) throws IllegalArgumentException, IllegalAccessException, InstantiationException, IOException;

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

   List<Object> getObjectList(String key, Class<?> defaultType, Class<?>... type) throws IllegalArgumentException, IllegalAccessException, InstantiationException, IOException;
}

