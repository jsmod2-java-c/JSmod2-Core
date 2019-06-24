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
package cn.jsmod2.core.utils.config.oaml.tree;

import cn.jsmod2.core.utils.config.oaml.utils.OrderMap;
import cn.jsmod2.core.utils.config.oaml.NodeFactory;
import cn.jsmod2.core.utils.config.oaml.OamlWriter;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * <P>
 * This class is a Node container, which can accommodate the most advanced nodes
 * , and builds the node tree root and sets the encoding format. 
 * <P>
 * It is also a factory that manufactures the root node. 
 * <P>
 * The factory supports multiple root nodes, but the root node does not directly 
 * allow duplicate names. Name,the previous node will be overwritten by the next 
 * name
 * <P>
 * Will be written to the file via <code>OamlWriter</code>.
 * @author magiclu550
 * @since oaml001
 * @since JDK1.8
 * @see NodeFactory 
 * @see OamlWriter
 */

public interface Document extends Serializable{
	
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
	 * Get all the root nodes
	 * @return all the root nodes
	 */
	
	Collection<Node> getEntrys();
	
	/**
	 * Can get the root node of the specified name.
	 * @param name the node key name
	 * @return the specified node object
	 */
	
	Node getEntry(String name);
	
	/**
	 * Get the object pool, which is the set 
	 * of key-value pairs of the elements corresponding 
	 * to the element name
	 * @return the pool
	 */
	
	OrderMap<String, Node> getPool();
	
	/**
	 * Can delete a specified root node object
	 * @param node the node object
	 */
	
	void remove(Node node);
	
	/**
	 * This method can get all nodes(root nodes and child nodes)
	 * @return key is path,value is node object mapping
	 */
	
	Map<String, Node> getAllNode();
	
	/**
	 * This method can get the notes
	 * @return the note objects
	 */
	
	List<NoCompile> getNotes();
	
	/**
	 * This method can set the note on the first node
	 */
	
	void setNote(String note);
}
