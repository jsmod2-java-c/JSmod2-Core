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

import cn.jsmod2.core.utils.core.config.oaml.tree.Document;
import cn.jsmod2.core.utils.core.config.oaml.utils.CollectionFactory;

import java.util.List;

/**
 * <P>
 * This is the factory class used to generate the Document 
 * container.
 * @author magiclu550
 * @since JDK1.8
 * @since Oaml001
 */

public final class DocumentFactory {
	
	private DocumentFactory() {
	}
	
	/**
	 * This method can get a <code>Document</code> object 
	 * @return the Document object
	 */
	
	public static Document getDocument() {
		return new NodeFactory();
	}
	
	/**
	 * <P>
	 * This method can predetect the shape
	 * of the string information in the oaml
	 * document ,and will eventually return a
	 * string or other object based on the synax
	 * @param document the oaml text
	 * @return the string of oaml or object of oaml
	 */
	
	public static Object load(String document) {
		if(document.matches("\\[[\\s\\S]+\\]")) {
			String[] strings = document.split(",");
			List<String> list = CollectionFactory.getList(String.class);
			for(String str:strings) {
				list.add(str);
			}
			return list;
		}else if(document.startsWith("#")){
			return "";
		}else if(document.matches("\\([\\s\\S]+\\)")){
			return "Object object";
		}else{
			return document.replace("\\n","\\\\n");
		}
	}

	/**
	 * You can modify the variable specified in the value to the value you
	 * need. The variable declaration format: $ variable name, in the process
	 * of processing, the parameter is passed by value mapping,
	 * new ValueMapping ("variable name", "value") variable name is not Need
	 * to be prefixed with $, you can modify multiple variables at the same time,
	 * do not support special objects, you can get string modification with get,
	 * and then convert to object by a method of getObject
	 * @param result The result set at the beginning is generally obtained by the get method.
	 * 				 Such as the cn.jsmod2.config.get method
	 *   			Entry.get method
	 * @param mappings the mapping values
	 * @return the value that is changed
	 */

	public static String setVar(String result, ValueMapping... mappings){

		StringBuilder builder = new StringBuilder(result.toString());
		for(int i = 0;i<mappings.length;i++){
			String before = "$"+mappings[i].getBefore();//before value
			String after = mappings[i].getAfter();//after value
			if(builder.indexOf(before)==-1){
				return builder.toString();
			}
			builder.replace(builder.indexOf(before),before.length(),after);
		}
		return builder.toString();
	}
}
