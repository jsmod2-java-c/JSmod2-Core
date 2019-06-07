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
package cn.jsmod2.utils.config.oaml;

import cn.jsmod2.utils.config.oaml.io.DocumentWriter;
import cn.jsmod2.utils.config.oaml.tree.Document;
import cn.jsmod2.utils.config.oaml.tree.NoCompile;
import cn.jsmod2.utils.config.oaml.tree.Node;

import java.io.*;
import java.util.*;

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
 *
 */

public class OamlWriter implements DocumentWriter {
	
	/** Used to save a stream that has already written the opening text*/
	private static Set<PrintWriter> streamPool;
	/** In order to ensure a direct one-to-one correspondence between objects
	 * and files,the same object will not be written a second time*/
	private List<Node> haveWrite;
	static {
		streamPool = new HashSet<PrintWriter>();
	}
	/** the out stream*/
	private OutputStream out;
	/** the print stream*/
	private PrintWriter ps;
	/** Used to control indentation */
	private int start;
	private int end;
	/** The constructor is used to get the outputstream */
	public OamlWriter(String file) throws IOException {
		this();
		this.out = new FileOutputStream(file);
	}
	/** The constructor is used to get the outputstream */
	public OamlWriter(OutputStream out) throws FileNotFoundException {
		this();
		this.out = out;
	}
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
	public void write(Document doc) throws UnsupportedEncodingException {
		String encoding = doc.getEncoding();
		if(ps==null) {
			ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(out,encoding)),true);
			if(!streamPool.contains(ps)) {
				ps.println("?encoding: "+encoding+" version: 1.0.0?");
			}else {
				streamPool.add(ps);
			}
		}
		Collection<Node> entrys = doc.getEntrys();
		entrys.toArray(new Node[entrys.size()]);
		for(NoCompile note:doc.getNotes()) {
			ps.println(note.getNote());
		}
		for(Node node:entrys) {
			if(!haveWrite.contains(node)) {
				List<Node> subs = node.getSons();
				printNote(node,0);
				if(!subs.isEmpty()) {
					print(node,0,"");
					printStart(0);
					writeInto(subs);
					printEnd(0);;
				}else {
					print(node,0,"");
				}
				haveWrite.add(node);
			}
		}
	}
	
	/**
	 * <P>Closes this stream and releases any system resources 
	 * associated with it. If the stream is already closed 
	 * then invoking this method has no effect.
	 * <P>As noted in AutoCloseable.close(), cases where the close 
	 * may fail require careful attention. It is strongly advised t
	 * o relinquish the underlying resources and to internally mark 
	 * the Closeable as closed, prior to throwing the IOException.
	 * 
	 * @see Closeable#close()
	 */
	public void close() {
		if(ps!=null)
			ps.close();
	}
	
	/**
	 * Flushes this stream by writing any buffered output 
	 * to the underlying stream.
	 * @see Flushable#flush()
	 */
	
	public void flush() {
		if(ps!=null)
			ps.flush();
	}
	/**
	 * Used to write comments
	 * @param node the node that have notes
	 * @param empty the \t number
	 */
	private void printNote(Node node,int empty) {
		for(int i = 0;i<empty-1;i++) {
			ps.print("\t");
		}
		List<NoCompile> notes = node.getNotes();
		for(NoCompile note:notes) {
			ps.println(note.getNote());
		}
	}
	/**
	 * used to print the node object
	 * @param node the node object
	 * @param empty the \t number
	 * @param prefix start with prefix
	 */
	private void print(Node node,int empty,String prefix) {
		for(int i = 0;i<empty;i++) {
			ps.print("\t");
		}
		ps.println(prefix+node.getName()+": "+node.getString().replace("\n","\\n"));
	}
	/**
	 * it is used to print the {
	 * @param empty the \t number
	 */
	private void printStart(int empty){
		for(int i = 0;i<empty;i++) {
			ps.print("\t");
		}
		ps.println("{");
	}
	/**
	 * it is used to print the }
	 * @param empty the \t number
	 */
	private void printEnd(int empty){
		for(int i = 0;i<empty;i++) {
			ps.print("\t");
		}
		ps.println("}");
	}
	/**
	 * Write all elements recursively in the form
	 * of a tree
	 * @param subs the root node's childs
	 */
	private void writeInto(List<Node> subs) {
		this.start++;
		end = start;
		for(int i = 0;i<subs.size();i++) {
			if(!haveWrite.contains(subs.get(i))) {
				printNote(subs.get(i),this.start);
				if(!subs.get(i).getSons().isEmpty()) {
					print(subs.get(i),this.start,"");
					printStart(this.start);
					List<Node> nodes = subs.get(i).getSons();
					writeInto(nodes);
					nodes.clear();
					printEnd(this.end);
				}else {
					print(subs.get(i),this.end,"");
				}
				haveWrite.add(subs.get(i));
			}
		}
		end--;
		start--;
	}
	/** this constructor is used to get the list*/
	private OamlWriter() {
		haveWrite = new ArrayList<Node>();
	}
}
