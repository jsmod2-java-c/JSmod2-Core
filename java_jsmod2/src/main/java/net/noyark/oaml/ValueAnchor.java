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

import net.noyark.oaml.tree.Anchor;

/**
 * <P>This is an anchor object that represents the anchor
 * defined by &n& in the oaml file.
 * <P>The parser will escape
 * &n& to the anchor object and convert the anchor pointer
 * that calls *n to the corresponding anchor data,ie the 
 * reuse value.
 * @author magiclu550
 * @since JDK1.8
 * @since oaml005
 * @see ValueAnchor
 */
public class ValueAnchor implements Anchor{
	/** anchor name*/
	private String name;
	/** anchor value */
	private String value;
	/** will be build in the parser*/
	public ValueAnchor(String name,String value) {
		this.name = name;
		this.value = value;
	}
	/**
	 * This method can get the anchor name
	 * @return the anchor name
	 */
	@Override
	public String getName() {
		return name;
	}
	/**
	 * This method can get the anchor name
	 * @return the anchor value
	 */
	@Override
	public String getValue() {
		return value;
	}
	/**
	 * Returns a string representation of the object. In general, 
	 * the toString method returns a string that "textually represents" 
	 * this object. The result should be a concise but informative representation 
	 * that is easy for a person to read. It is recommended that all subclasses override this method.
	 * The toString method for class Object returns a string consisting of the name of the class of 
	 * which the object is an instance, the at-sign character `@', and the unsigned hexadecimal 
	 * representation of the hash code of the object. In other words, this method returns a string equal 
	 * to the value of:
	 * getClass().getName() + '@' + Integer.toHexString(hashCode())
	 */
	@Override
	public String toString() {
		return "ValueAnchor [name=" + name + ", value=" + value + "]";
	}

}
