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
package net.noyark.oaml.tree;

import net.noyark.oaml.ValueAnchor;

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
public interface Anchor {
	/**
	 * This method can get the anchor name
	 * @return the anchor name
	 */
	String getName();
	/**
	 * This method can get the anchor value
	 * @return the anchor value
	 */
	String getValue();
}
