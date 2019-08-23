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
package cn.jsmod2.core.utils.core.config.oaml.utils;

import java.util.*;

public class CollectionFactory {
	public static <T> List<T> getList(Class<T> type){
		return new ArrayList<T>();
	}
	public static <K,V> Map<K, V> getMap(Class<K> key,Class<V> value){
		return new HashMap<K,V>();
	}
	public static <K,V> Map<K, V> getLinkedMap(Class<K> key,Class<V> value){
		return new LinkedHashMap<K,V>();
	}
	public static <K,V> OrderMap<K, V> getOrderMap(Class<K> key, Class<V> value){
		return new NodeMap<>();
	}
}
