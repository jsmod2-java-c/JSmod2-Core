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
package cn.jsmod2.core.utils.config.oaml.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * when you create a configuration file using reflection,
 * you can use the Comfig annotation annotation on the class
 * name to mark the file name of the configuration file,which
 * will be registered in the file pool.that is,the OamlConfig 
 * object.
 * @author magiclu550
 * @since JDk1.8
 * @since Oaml 002
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Config {
	/**
	 * The oaml file name
	 * @return the filename
	 */
	String value();
	/**
	 * Whether to supplement the classpath before
	 * the path
	 * @return whether get the classpath
	 */
	boolean classpath() default false;
}
