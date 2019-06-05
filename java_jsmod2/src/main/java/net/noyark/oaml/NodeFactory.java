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

import net.noyark.oaml.tree.Document;
import net.noyark.oaml.tree.NoCompile;
import net.noyark.oaml.tree.Node;
import net.noyark.oaml.utils.CollectionFactory;
import net.noyark.oaml.utils.OrderMap;

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
 * @author magiclu550
 * @since oaml001
 * @since JDK1.8
 * @see NodeFactory 
 */
public final class NodeFactory implements Document{
	/**
	 * The default servialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * all of the nodes on this document
	 */
	private Map<String, Node> allNode = CollectionFactory.getMap(String.class,Node.class);
	/**
	 * the character set,default "UTF-8"
	 */
	private String cdn = "UTF-8";
	/**
	 * all of the roots
	 */
	private OrderMap<String, Node> nodePool = CollectionFactory.getOrderMap(String.class,Node.class);
	/**
	 * the notes of the first 
	 */
	private List<NoCompile> notes = CollectionFactory.getList(NoCompile.class);
	/**
	 * the anchors
	 */
	
	
	public Node getEntry(String name) {
		Node node = nodePool.get(name);
		if(node==null) {
			node = new XEntry(name,null,this);
			nodePool.put(name,node);
			allNode.put(name, node);
		}
		return node;
	}
	/**
	 * <P>
	 * The encoding format used to set the configuration file. 
	 * The default is UTF-8 format.
	 * @param cdn the charset format
	 */
	public void setEncoding(String cdn) {
		this.cdn = cdn;
	}
	/**
	 * Can get the supported character set of the 
	 * current configuration file.
	 * @return the encoding type
	 */
	public String getEncoding() {
		return cdn;
	}
	/**
	 * Get all the root nodes
	 * @return all the root nodes
	 */
	public Collection<Node> getEntrys(){
		return nodePool.values();
	}
	/**
	 * Get the object pool, which is the set 
	 * of key-value pairs of the elements corresponding 
	 * to the element name
	 * @return the pool
	 */
	public OrderMap<String, Node> getPool(){
		return nodePool;
	}
	
	/**
	 * Can delete a specified root node object
	 * @param node the node object
	 */
	@Override
	public void remove(Node node) {
		nodePool.remove(node.getName());
	}
	/**
	 * @see Object#toString()
	 */
	public String toString() {
		return super.toString()+nodePool;
	}
	/**
	 * This method can get all nodes(root nodes and child nodes)
	 * @return key is path,value is node object mapping
	 */
	@Override
	public Map<String, Node> getAllNode(){
		return allNode;
	}
	/**
	 * This method can get the notes
	 * @return the note objects
	 */
	@Override
	public List<NoCompile> getNotes(){
		return notes;
	}
	/**
	 * This method can set the note on the first node
	 */
	@Override
	public void setNote(String note) {
		notes.add(new Note(note));
	}
	/**
	 * @see Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cdn == null) ? 0 : cdn.hashCode());
		result = prime * result + ((nodePool == null) ? 0 : nodePool.hashCode());
		return result;
	}
	/**
	 * @see Object#equals(Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NodeFactory other = (NodeFactory) obj;
		if (cdn == null) {
			if (other.cdn != null)
				return false;
		} else if (!cdn.equals(other.cdn))
			return false;
		if (nodePool == null) {
			if (other.nodePool != null)
				return false;
		} else if (!nodePool.equals(other.nodePool))
			return false;
		return true;
	}
	
}
