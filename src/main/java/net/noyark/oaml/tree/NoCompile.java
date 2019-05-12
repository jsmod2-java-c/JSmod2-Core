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

import net.noyark.oaml.Note;

/**
 * <P>
 * The class that implements the interface
 * will ignore the object during parsing,
 * and the parser will not parse the object
 * <P>
 * The parser will detect unresolved objects
 * ,so they will not parse them.
 * @author Magiclu550
 * @since JDK1.8
 * @since Oaml 005
 * @see Note
 */

public interface NoCompile {
	
	/**
	 * Get the expression information in oaml
	 * in the object,the writer will writer in.
	 * 
	 * @return the note message
	 */
	
	String getNote();
}
