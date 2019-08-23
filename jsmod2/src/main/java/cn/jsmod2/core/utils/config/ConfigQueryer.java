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
package cn.jsmod2.core.utils.config;


import cn.jsmod2.core.ex.TypeErrorException;

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
	/** the cn.jsmod2.config object pool */
	private static Map<String, Config> configPool;
	static {
		configPool = new HashMap<>();
	}
	/**
	 * Used to get the OamlConfig object,this is a
	 * very recommended way to get the object,which
	 * can guarantee the object unique and save memory
	 * @param filename the cn.jsmod2.config filename
	 * @param getClass Whether to get the classpath
	 * @param type the cn.jsmod2.config type
	 * @return the OamlConfig object
	 * @throws FileNotFoundException
	 */
	public static Config getInstance(String filename, boolean getClass, ConfigType... type) throws FileNotFoundException {
		Config oc = configPool.get(filename);
		if(oc == null) {
			if(type.length<1){
				throw new TypeErrorException("if the cn.jsmod2.config instance does not exist,the type can not be null");
			}
			switch (type[0]){
				case JSON:
					oc = new JsonConfig(filename,getClass);
					break;
				case OAML:
					oc = new OamlConfig(filename,getClass);
					break;
				case YAML:
					oc = new YamlConfig(filename,getClass);
					break;
				case PROPERTIES:
					oc = new PropertiesConfig(filename,getClass);
			}
			configPool.put(filename,oc);
			return oc;
		}else {
			return oc;
		}
	}
}
