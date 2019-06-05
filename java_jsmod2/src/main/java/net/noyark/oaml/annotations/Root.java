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
package net.noyark.oaml.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The value used to mark this field on the field
 * corresponds to which key,the format is
 * \@Root<br>
 * public int hello=0;<br>
 * When parsing ,it will resolve to a: 0,If multi-level
 * paths are used,they are split by points.
 * @author magiclu550
 * @since JDK1.8
 * @since oaml 002
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Root {
	
	/**
	 * The node path
	 * @return the node path
	 */
	
	String value();
}
