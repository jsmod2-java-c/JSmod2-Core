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

/**
 * An object that maps keys to values.  A cn.jsmod2.map cannot contain duplicate keys;
 * each key can cn.jsmod2.map to at most one value.
 *
 * <p>This interface takes the place of the <tt>Dictionary</tt> class, which
 * was a totally abstract class rather than an interface.
 *
 * <p>The <tt>Map</tt> interface provides three <i>collection views</i>, which
 * allow a cn.jsmod2.map's contents to be viewed as a set of keys, collection of values,
 * or set of key-value mappings.  The <i>order</i> of a cn.jsmod2.map is defined as
 * the order in which the iterators on the cn.jsmod2.map's collection views return their
 * elements.  Some cn.jsmod2.map implementations, like the <tt>TreeMap</tt> class, make
 * specific guarantees as to their order; others, like the <tt>HashMap</tt>
 * class, do not.
 *
 * <p>Note: great care must be exercised if mutable objects are used as cn.jsmod2.map
 * keys.  The behavior of a cn.jsmod2.map is not specified if the value of an object is
 * changed in a manner that affects <tt>equals</tt> comparisons while the
 * object is a key in the cn.jsmod2.map.  A special case of this prohibition is that it
 * is not permissible for a cn.jsmod2.map to contain itself as a key.  While it is
 * permissible for a cn.jsmod2.map to contain itself as a value, extreme caution is
 * advised: the <tt>equals</tt> and <tt>hashCode</tt> methods are no longer
 * well defined on such a cn.jsmod2.map.
 *
 * <p>All general-purpose cn.jsmod2.map implementation classes should provide two
 * "standard" constructors: a void (no arguments) constructor which creates an
 * empty cn.jsmod2.map, and a constructor with a single argument of type <tt>Map</tt>,
 * which creates a new cn.jsmod2.map with the same key-value mappings as its argument.
 * In effect, the latter constructor allows the cn.jsmod2.user to copy any cn.jsmod2.map,
 * producing an equivalent cn.jsmod2.map of the desired class.  There is no way to
 * enforce this recommendation (as interfaces cannot contain constructors) but
 * all of the general-purpose cn.jsmod2.map implementations in the JDK comply.
 *
 * <p>The "destructive" methods contained in this interface, that is, the
 * methods that modify the cn.jsmod2.map on which they operate, are specified to throw
 * <tt>UnsupportedOperationException</tt> if this cn.jsmod2.map does not support the
 * operation.  If this is the case, these methods may, but are not required
 * to, throw an <tt>UnsupportedOperationException</tt> if the invocation would
 * have no effect on the cn.jsmod2.map.  For example, invoking the {@link #putAll(Map)}
 * method on an unmodifiable cn.jsmod2.map may, but is not required to, throw the
 * exception if the cn.jsmod2.map whose mappings are to be "superimposed" is empty.
 *
 * <p>Some cn.jsmod2.map implementations have restrictions on the keys and values they
 * may contain.  For example, some implementations prohibit null keys and
 * values, and some have restrictions on the types of their keys.  Attempting
 * to insert an ineligible key or value throws an unchecked exception,
 * typically <tt>NullPointerException</tt> or <tt>ClassCastException</tt>.
 * Attempting to query the presence of an ineligible key or value may throw an
 * exception, or it may simply return false; some implementations will exhibit
 * the former behavior and some will exhibit the latter.  More generally,
 * attempting an operation on an ineligible key or value whose completion
 * would not result in the insertion of an ineligible element into the cn.jsmod2.map may
 * throw an exception or it may succeed, at the option of the implementation.
 * Such exceptions are marked as "optional" in the specification for this
 * interface.
 *
 * <p>Many methods in Collections Framework interfaces are defined
 * in terms of the {@link Object#equals(Object) equals} method.  For
 * example, the specification for the {@link #containsKey(Object)
 * containsKey(Object key)} method says: "returns <tt>true</tt> if and
 * only if this cn.jsmod2.map contains a mapping for a key <tt>k</tt> such that
 * <tt>(key==null ? k==null : key.equals(k))</tt>." This specification should
 * <i>not</i> be construed to imply that invoking <tt>Map.containsKey</tt>
 * with a non-null argument <tt>key</tt> will cause <tt>key.equals(k)</tt> to
 * be invoked for any key <tt>k</tt>.  Implementations are free to
 * implement optimizations whereby the <tt>equals</tt> invocation is avoided,
 * for example, by first comparing the hash codes of the two keys.  (The
 * {@link Object#hashCode()} specification guarantees that two objects with
 * unequal hash codes cannot be equal.)  More generally, implementations of
 * the various Collections Framework interfaces are free to take advantage of
 * the specified behavior of underlying {@link Object} methods wherever the
 * implementor deems it appropriate.
 *
 * <p>Some cn.jsmod2.map operations which perform recursive traversal of the cn.jsmod2.map may fail
 * with an exception for self-referential instances where the cn.jsmod2.map directly or
 * indirectly contains itself. This includes the {@code clone()},
 * {@code equals()}, {@code hashCode()} and {@code toString()} methods.
 * Implementations may optionally handle the self-referential scenario, however
 * most current implementations do not do so.
 *
 * <p>This interface is a member of the
 * <a href="{@docRoot}/../technotes/guides/collections/index.html">
 * Java Collections Framework</a>.
 *
 *	This cn.jsmod2.map have order.
 * @param <K> the type of keys maintained by this cn.jsmod2.map
 * @param <V> the type of mapped values
 *
 * @useForReferenceAuthor  Josh Bloch
 * @author  magiclu550
 * @see HashMap
 * @see TreeMap
 * @see Hashtable
 * @see SortedMap
 * @see Collection
 * @see Set
 * @since 1.2
 * @since oaml004
 */

public interface OrderMap<K,V>{
	
	  /**
     * Returns the number of key-value mappings in this cn.jsmod2.map.  If the
     * cn.jsmod2.map contains more than <tt>Integer.MAX_VALUE</tt> elements, returns
     * <tt>Integer.MAX_VALUE</tt>.
     *
     * @return the number of key-value mappings in this cn.jsmod2.map
     */
	
	int size();
	
	   /**
     * Returns <tt>true</tt> if this cn.jsmod2.map contains no key-value mappings.
     *
     * @return <tt>true</tt> if this cn.jsmod2.map contains no key-value mappings
     */
	
	boolean isEmpty();
	
	  /**
     * Returns <tt>true</tt> if this cn.jsmod2.map contains a mapping for the specified
     * key.  More formally, returns <tt>true</tt> if and only if
     * this cn.jsmod2.map contains a mapping for a key <tt>k</tt> such that
     * <tt>(key==null ? k==null : key.equals(k))</tt>.  (There can be
     * at most one such mapping.)
     *
     * @param key key whose presence in this cn.jsmod2.map is to be tested
     * @return <tt>true</tt> if this cn.jsmod2.map contains a mapping for the specified
     *         key
     */
	
	boolean containsKey(Object key);
	
	 /**
     * Returns <tt>true</tt> if this cn.jsmod2.map maps one or more keys to the
     * specified value.  More formally, returns <tt>true</tt> if and only if
     * this cn.jsmod2.map contains at least one mapping to a value <tt>v</tt> such that
     * <tt>(value==null ? v==null : value.equals(v))</tt>.  This operation
     * will probably require time linear in the cn.jsmod2.map size for most
     * implementations of the <tt>Map</tt> interface.
     *
     * @param value value whose presence in this cn.jsmod2.map is to be tested
     * @return <tt>true</tt> if this cn.jsmod2.map maps one or more keys to the
     *         specified value
     */
	
	boolean containsValue(Object value);
	
	   /**
     * Returns the value to which the specified key is mapped,
     * or {@code null} if this cn.jsmod2.map contains no mapping for the key.
     *
     * <p>More formally, if this cn.jsmod2.map contains a mapping from a key
     * {@code k} to a value {@code v} such that {@code (key==null ? k==null :
     * key.equals(k))}, then this method returns {@code v}; otherwise
     * it returns {@code null}.  (There can be at most one such mapping.)
     *
     * <p>If this cn.jsmod2.map permits null values, then a return value of
     * {@code null} does not <i>necessarily</i> indicate that the cn.jsmod2.map
     * contains no mapping for the key; it's also possible that the cn.jsmod2.map
     * explicitly maps the key to {@code null}.  The {@link #containsKey
     * containsKey} operation may be used to distinguish these two cases.
     *
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or
     *         {@code null} if this cn.jsmod2.map contains no mapping for the key
     */
	
	V get(Object key);
	
    /**
     * Associates the specified value with the specified key in this cn.jsmod2.map
     * (optional operation).  If the cn.jsmod2.map previously contained a mapping for
     * the key, the old value is replaced by the specified value.  (A cn.jsmod2.map
     * <tt>m</tt> is said to contain a mapping for a key <tt>k</tt> if and only
     * if {@link #containsKey(Object) m.containsKey(k)} would return
     * <tt>true</tt>.)
     *
     * @param key key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return the previous value associated with <tt>key</tt>, or
     *         <tt>null</tt> if there was no mapping for <tt>key</tt>.
     *         (A <tt>null</tt> return can also indicate that the cn.jsmod2.map
     *         previously associated <tt>null</tt> with <tt>key</tt>,
     *         if the implementation supports <tt>null</tt> values.)
     */
	
	V put(K key, V value);
	
    /**
     * Removes the mapping for a key from this cn.jsmod2.map if it is present
     * (optional operation).   More formally, if this cn.jsmod2.map contains a mapping
     * from key <tt>k</tt> to value <tt>v</tt> such that
     * <code>(key==null ?  k==null : key.equals(k))</code>, that mapping
     * is removed.  (The cn.jsmod2.map can contain at most one such mapping.)
     *
     * <p>Returns the value to which this cn.jsmod2.map previously associated the key,
     * or <tt>null</tt> if the cn.jsmod2.map contained no mapping for the key.
     *
     * <p>If this cn.jsmod2.map permits null values, then a return value of
     * <tt>null</tt> does not <i>necessarily</i> indicate that the cn.jsmod2.map
     * contained no mapping for the key; it's also possible that the cn.jsmod2.map
     * explicitly mapped the key to <tt>null</tt>.
     *
     * <p>The cn.jsmod2.map will not contain a mapping for the specified key once the
     * call returns.
     *
     * @param key key whose mapping is to be removed from the cn.jsmod2.map
     * @return the previous value associated with <tt>key</tt>, or
     *         <tt>null</tt> if there was no mapping for <tt>key</tt>.
     */
	
	V remove(Object key);
	
	 /**
     * Removes all of the mappings from this cn.jsmod2.map (optional operation).
     * The cn.jsmod2.map will be empty after this call returns.
     *
     */
	
	void clear();
	
    /**
     * Returns a {@link List} view of the mappings contained in this cn.jsmod2.map.
     * The list is backed by the cn.jsmod2.map, so changes to the cn.jsmod2.map are
     * reflected in the list, and vice-versa.  If the cn.jsmod2.map is modified
     * while an iteration over the list is in progress (except through
     * the iterator's own <tt>remove</tt> operation, or through the
     * <tt>setValue</tt> operation on a cn.jsmod2.map entry returned by the
     * iterator) the results of the iteration are undefined.  The list
     * supports element removal, which removes the corresponding
     * mapping from the cn.jsmod2.map, via the <tt>Iterator.remove</tt>,
     * <tt>Set.remove</tt>, <tt>removeAll</tt>, <tt>retainAll</tt> and
     * <tt>clear</tt> operations.  It does not support the
     * <tt>add</tt> or <tt>addAll</tt> operations.
     *
     * @return a list view of the mappings contained in this cn.jsmod2.map
     */
	
	List<OEntry<K, V>> entryList();
	
	/**
	 * This method can put the entry with index
	 * @see OrderMap#put(Object, Object)
	 * @param index the index of entry
	 * @param key key with which the specified value is to be associated
   * @param value value to be associated with the specified key
	 * @return the value
	 */
	
	V put(int index, K key, V value);
	
	   /**
     * Returns a {@link List} view of the mappings contained in this cn.jsmod2.map.
     * The list is backed by the cn.jsmod2.map, so changes to the cn.jsmod2.map are
     * reflected in the list, and vice-versa.  If the cn.jsmod2.map is modified
     * while an iteration over the list is in progress (except through
     * the iterator's own <tt>remove</tt> operation, or through the
     * <tt>setValue</tt> operation on a cn.jsmod2.map entry returned by the
     * iterator) the results of the iteration are undefined.  The list
     * supports element removal, which removes the corresponding
     * mapping from the cn.jsmod2.map, via the <tt>Iterator.remove</tt>,
     * <tt>Set.remove</tt>, <tt>removeAll</tt>, <tt>retainAll</tt> and
     * <tt>clear</tt> operations.  It does not support the
     * <tt>add</tt> or <tt>addAll</tt> operations.
     *
     * @return a list view of the mappings contained in this cn.jsmod2.map
     */
	
	List<V> values();
	
    /**
     * A cn.jsmod2.map entry (key-value pair).  The <tt>Map.entrySet</tt> method returns
     * a collection-view of the cn.jsmod2.map, whose elements are of this class.  The
     * <i>only</i> way to obtain a reference to a cn.jsmod2.map entry is from the
     * iterator of this collection-view.  These <tt>Map.Entry</tt> objects are
     * valid <i>only</i> for the duration of the iteration; more formally,
     * the behavior of a cn.jsmod2.map entry is undefined if the backing cn.jsmod2.map has been
     * modified after the entry was returned by the iterator, except through
     * the <tt>setValue</tt> operation on the cn.jsmod2.map entry.
     *
     * @see Map#entrySet()
     * @see OrderMap#entryList()
     * @since 1.2
     * @since oaml004
     */
	
	interface OEntry<K,V> {

		  /**
         * Returns the key corresponding to this entry.
         *
         * @return the key corresponding to this entry

         */
		
		K getKey();

		/**
         * Returns the value corresponding to this entry.  If the mapping
         * has been removed from the backing cn.jsmod2.map (by the iterator's
         * <tt>remove</tt> operation), the results of this call are undefined.
         *
         * @return the value corresponding to this entry
         */
		
		V getValue();

	     /**
         * Replaces the value corresponding to this entry with the specified
         * value (optional operation).  (Writes through to the cn.jsmod2.map.)  The
         * behavior of this call is undefined if the mapping has already been
         * removed from the cn.jsmod2.map (by the iterator's <tt>remove</tt> operation).
         *
         * @param value new value to be stored in this entry
         * @return old value corresponding to the entry
         */
		
		V setValue(V value);
		
	}
}
