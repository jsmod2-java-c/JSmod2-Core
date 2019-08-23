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
package cn.jsmod2.core.utils.core.config.oaml;

import cn.jsmod2.core.utils.core.config.oaml.exception.NoSuchAnchorException;
import cn.jsmod2.core.utils.core.config.oaml.exception.ReaderException;
import cn.jsmod2.core.utils.core.config.oaml.io.DocumentReadable;
import cn.jsmod2.core.utils.core.config.oaml.io.DocumentReader;
import cn.jsmod2.core.utils.core.config.oaml.tree.Anchor;
import cn.jsmod2.core.utils.core.config.oaml.tree.Document;
import cn.jsmod2.core.utils.core.config.oaml.tree.Node;

import java.io.*;
import java.util.*;

/**
 * <P>This is the oaml parser class,which can parse
 * the oaml file into a data set according to the
 * oaml file syntax for the program to call.
 * <P>Here,
 * the persistence layer and the program level are
 * connected.When parsing the file,you must ensure
 * that the file is normally available,and declare
 * the character set and version,otherwise it will
 * throw an exception,if the syntax is wrong,the
 * parser will also detect it,throw an error,terminate
 * the parsing,the parser standard algorithm has oaml
 * provisions,standard indentation use \t
 * @author magiclu550
 * @since JDK1.8
 * @since Oaml 001
 * @see BufferedReader
 * @see Reader
 *@see DocumentReadable
 */

public class OamlReader implements DocumentReader {
	/** All anchor objects parsed by the parser */
	private Map<String, Anchor> anchors;
	/** used to read the basic message */
	private BufferedReader reader;
	/** instance the anchors cn.jsmod2.map */
	public OamlReader() {
		anchors = new HashMap<>();
	}
	
	/**
	 * When the file object is passed in,the parser converts
	 * it into a character stream ,and reads and parses the 
	 * information.This method is an overloaded method.When 
	 * reading ,you need to specify the file or its corresponding
	 * stream
	 * @param file the file object
	 * @return the document object
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	
	public Document read(File file) throws FileNotFoundException, IOException {
		return read(new FileInputStream(file));
	}
	
	/**
	 * When the file name is passed in,the parser converts
	 * it into a character stream ,and reads and parses the 
	 * information.This method is an overloaded method.When 
	 * reading ,you need to specify the file or its corresponding
	 * stream
	 * @param file the file name
	 * @return the document object
	 * @throws FileNotFoundException #{@link FileNotFoundException}
	 * @throws IOException #{@link IOException}
	 */
	
	public Document read(String file) throws FileNotFoundException, IOException {
		return read(new InputStreamReader(new FileInputStream(file)));
	}
	
	/**
	 * When the stream is passed in,the parser converts
	 * it into a character stream ,and reads and parses the 
	 * information.This method is an overloaded method.When 
	 * reading ,you need to specify the file or its corresponding
	 * stream
	 * @param inputStream the inputstream
	 * @return the document object
	 * @throws IOException #{@link IOException}
	 */
	
	public Document read(InputStream inputStream) throws IOException {
		return read(new InputStreamReader(inputStream));
	}
	
	/**
	 * When the Reader object is passed in,the parser converts
	 * it into a character stream ,and reads and parses the 
	 * information.This method is an overloaded method.When 
	 * reading ,you need to specify the file or its corresponding
	 * stream
	 * @param read the reader stream
	 * @return the document object
	 * @throws IOException #{@link IOException}
	 */
	
	public Document read(Reader read) throws IOException {
		String cdn = null;
		reader = new BufferedReader(read);
		cdn = reader.readLine();
		if(cdn == null) {
			throw new ReaderException("文件提前结束");
		}else if(!cdn.replace(" ","").matches("\\?encoding:[\\s\\S]+version:[\\s\\S]+\\?")) {
			throw new ReaderException("Nnkown encoding:null");
		}else {
			cdn = cdn.substring(cdn.indexOf("encoding:")+"encoding:".length(),cdn.indexOf("version")).trim();
		}
		Document document = DocumentFactory.getDocument();
		document.setEncoding(cdn);
		String line = null;
		List<String> allText = new ArrayList<String>();
		while((line = reader.readLine())!=null) {
			if(!line.isEmpty()&&!line.startsWith("#")) {
				int startIndex = line.replace("\t", "").replace(" ","").indexOf("[");
				if(startIndex!=-1&&line.charAt(line.indexOf("["))!='\\'&&!line.endsWith("]")) {
					StringBuilder builder = new StringBuilder();
					builder.append(line);
					while(true) {
						String lineString = reader.readLine();
						builder.append(lineString.replace("\t","").replace(" ",""));
						if(lineString.replace(" ","").replace("\t","").replaceAll(":\\[|\\]\\)","").indexOf("]")!=-1){
							break;
						}
					}
					line = builder.toString();
				}
				allText.add(line.replace("\t",""));
			}
		}
		int table = 0;
		List<String> yamlText = new ArrayList<>();
		Set<Integer> tables = new HashSet<>();
		for(String text:allText) {
			if(text.trim().startsWith("{")) {
				table++;
			}else if(text.trim().startsWith("}")){
				table--;
			}
			tables.add(table);
			String temp = text.replaceAll("(\\{|\\})","");
			StringBuilder builder = new StringBuilder();
			if(!temp.isEmpty()) {
				builder.append(table+temp);
				yamlText.add(builder.toString());
			}
		}
		Integer[] integers = tables.toArray(new Integer[tables.size()]);
		Arrays.sort(integers);
		Assert.ObjectTypeError(0, integers, "...");
		int bigest = integers[integers.length-1];
		String text = yamlText.get(0);
		Assert.mustHaveDivide(0, text);
		String firstName = text.substring(1,text.indexOf(":"));
		String firstValue = text.substring(text.indexOf(":")+1);
		Node rootNode = document.getEntry(firstName);
		rootNode.setValue(firstValue);
		Node[] nodes = new Node[bigest+1];
		nodes[0] = rootNode;
		for(int i = 1;i<yamlText.size();i++) {
			text = yamlText.get(i);
			Assert.noMapping(i, text);
			int level = Integer.parseInt(text.substring(0,1));
			Assert.mustHaveDivide(i, text);
			String name = text.substring(1,text.indexOf(":"));
			String value = text.substring(text.indexOf(":")+1);
			if(value.trim().indexOf("*")!=-1) {
				String anchorName = value.substring(value.trim().indexOf("*")+2);
				Anchor anchor = anchors.get(anchorName);
				Assert.notNull(i, anchor, name, new NoSuchAnchorException("no such anchor named "+anchorName));
				value = value.replaceAll("\\*[\\s\\S]+",anchor.getValue());
			}
			//定义锚 &a& 使用锚*a
			if(value.trim().startsWith("&")) {
				String nameString = value.trim().substring(value.trim().indexOf("&")+1,value.trim().indexOf("&",value.trim().indexOf("&")+1));
				String anthorValue = value.trim().substring(value.trim().indexOf("&",value.trim().indexOf("&")+1)+1);
				anchors.put(nameString,new ValueAnchor(nameString, anthorValue));
			}
			if(level==0) {
				rootNode = document.getEntry(name);
				rootNode.setValue(value.trim());
				nodes[0] = rootNode;
			}else {
				nodes[level] = nodes[level-1].createNode(name);
				nodes[level].setValue(value.trim());
			}
		}
		reader.close();
		return document;
	}
}

