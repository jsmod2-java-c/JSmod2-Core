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
package net.noyark.oaml.io;

import net.noyark.oaml.OamlWriter;
import net.noyark.oaml.tree.Document;

import java.io.UnsupportedEncodingException;

/**
 * <P>
 * It is used to write the node object to oaml document.
 * It is written in the oaml standard syntax.However,When
 * the file is illegally manipulated,the syntax is not guaranteed
 * to be correct,Therefore,it should be written reasonably.In addition,
 * when writing and saving,Overwrite the original content,so you need
 * to save the orginal content,please get and then set.
 * @author magiclu550
 * @since JDK1.8
 * @since oaml 001
 * @see OamlWriter
 */

public interface DocumentWritable {
	/**
	 * <P>This method is used to write the node to the document,
	 * and the orginal thing is empty befre writing.After writing,
	 * please close the stream.After closing the stream,
	 * you can not write it again.The syntax is not guaranteed to
	 * be completely correct
	 * @param doc the <code>Document</code> object
	 * @throws UnsupportedEncodingException if the document's encode is wrong
	 * 																			,will throw
	 */
	void write(Document doc) throws UnsupportedEncodingException ;
}
