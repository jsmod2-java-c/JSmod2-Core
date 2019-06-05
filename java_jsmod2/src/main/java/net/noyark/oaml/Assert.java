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
package net.noyark.oaml;

import net.noyark.oaml.exception.ReaderException;

/**
 * <P>
 * This is an assertion class that is responsible for 
 * judging some syntax errors in oaml and throwing 
 * related exceptions.
 * <P>
 * @author magiclu550
 * @since oaml java002
 * @since JDK1.8
 */

public class Assert {
	
	/**
	 * <P>
	 * Used to determine if a key-value pair is split by ‘:‘, if not, 
	 * throw a syntax error
	 * @param count the line number
	 * @param text the entry 
	 */
	
	public static void mustHaveDivide(int count,String text) {
		try {
			text.substring(1,text.indexOf(":"));
		}catch(StringIndexOutOfBoundsException e) {
			throw new ReaderException("on line "+count+" near "+text.substring(1)+",no ':' to divide the key and value");
		}
	}
	
	/**
	 * <P>
	 * Used to determine if there are braces that correspond to each other. 
	 * If the braces are not paired, a syntax error will be thrown.
	 * @param count the line number
	 * @param text the entry
	 */
	
	public static void noMapping(int count,String text) {
		try {
			Integer.parseInt(text.substring(0,1));
		}catch (NumberFormatException e) {
			throw new ReaderException("on line "+count+" near "+text.substring(1)+" no '{' mapping with '}'") ;
		}
	}
	
	/**
	 * This method can detect whether the object type is
	 * legal or not.When there is no object ,the object 
	 * level will parse the error
	 * @param count the number of line
	 * @param integers the levels
	 * @param text the entry 
	 */
	
	public static void ObjectTypeError(int count,Integer[] integers,String text) {
		try {
			@SuppressWarnings("unused")
			int i = integers[integers.length-1];
		}catch (ArrayIndexOutOfBoundsException e) {
			throw new ReaderException("on line "+count+" near "+text+" the object type is error");
		}
	}
	/**
	 * This method can assert the object is null,if it is,
	 * the method will throw a exception
	 * @param count the line number
	 * @param o the object
	 * @param name the entry name
	 * @param e the exception will be throwed
	 */
	public static void notNull(int count,Object o,String name,Exception e) {
		if(o == null) {
			throw new ReaderException("on line "+count+" near "+name+" the anthor is null",e);
		}
	}
}
