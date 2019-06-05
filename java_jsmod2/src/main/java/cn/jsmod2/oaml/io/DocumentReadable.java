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
package cn.jsmod2.oaml.io;

import cn.jsmod2.oaml.OamlReader;
import cn.jsmod2.oaml.tree.Document;

import java.io.*;

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
 *@see OamlReader
 */
public interface DocumentReadable {
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
	
	public Document read(File file) throws FileNotFoundException, IOException;
	
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
	
	public Document read(String file) throws FileNotFoundException, IOException;
	
	/**
	 * When the stream is passed in,the parser converts
	 * it into a character stream ,and reads and parses the 
	 * information.This method is an overloaded method.When 
	 * reading ,you need to specify the file or its corresponding
	 * stream
	 * @param inputstream the inputstream
	 * @return the document object
	 * @throws IOException #{@link IOException}
	 */
	
	public Document read(InputStream inputStream) throws IOException;
	
	/**
	 * When the Reader object is passed in,the parser converts
	 * it into a character stream ,and reads and parses the 
	 * information.This method is an overloaded method.When 
	 * reading ,you need to specify the file or its corresponding
	 * stream
	 * @param reader the reader stream
	 * @return the document object
	 * @throws IOException #{@link IOException}
	 */
	
	public Document read(Reader read) throws IOException;
}
