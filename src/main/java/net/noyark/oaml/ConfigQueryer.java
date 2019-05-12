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

import net.noyark.scpslserver.jsmod2.utils.config.OamlConfig;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

/**
 * Used to get simple file objects,and to
 * ensure that each file corresponds to an
 * object,built-in object pool,store the objects
 * that have been created.
 * @author magiclu550
 * @since JDK1.8
 * @since oaml 003
 *
 */
public class ConfigQueryer {
	/** the config object pool */
	private static Map<String, net.noyark.oaml.simple.OamlConfig> configPool;
	static {
		configPool = new HashMap<>();
	}
	/**
	 * Used to get the OamlConfig object,this is a
	 * very recommended way to get the object,which
	 * can guarantee the object unique and save memory
	 * @param filename the config filename
	 * @param getClass Whether to get the classpath
	 * @return the OamlConfig object
	 * @throws FileNotFoundException
	 */
	public static net.noyark.oaml.simple.OamlConfig getInstance(String filename, boolean getClass) throws FileNotFoundException {
		net.noyark.oaml.simple.OamlConfig oc = configPool.get(filename);
		if(oc == null) {
			oc = new OamlConfig(filename,getClass);
			configPool.put(filename,oc);
			return oc;
		}else {
			return oc;
		}
	}
}
