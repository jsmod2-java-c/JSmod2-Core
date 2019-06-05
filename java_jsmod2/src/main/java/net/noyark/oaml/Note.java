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

import net.noyark.oaml.tree.NoCompile;

/**
 * Used to build annotation objects.When witing,
 * the annotations that come with the node are 
 * written to the file,but the annotations are
 * ignored when parsing ,so it only works when 
 * writting.The annotations start with #
 * @author magiclu550
 * @since oaml003
 * @since JDK1.8
 */
public class Note implements NoCompile{
	private String note;
	/**
	 * this constructor can build a note object
	 * with the note string start with '#'
	 * @param note the comment without '#'
	 */
	public Note(String note) {
		this.note = "#"+note;
	}
	/**
	 * Get the expression information in oaml
	 * in the object,the writer will writer in.
	 * 
	 * @return the note message
	 */
	
	public String getNote() {
		return note;
	}
}
