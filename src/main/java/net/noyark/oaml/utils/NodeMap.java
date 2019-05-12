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
package net.noyark.oaml.utils;

import java.util.*;

/**
 * An object that maps keys to values.  A map cannot contain duplicate keys;
 * each key can map to at most one value.
 *
 * <p>This interface takes the place of the <tt>Dictionary</tt> class, which
 * was a totally abstract class rather than an interface.
 *
 * <p>The <tt>Map</tt> interface provides three <i>collection views</i>, which
 * allow a map's contents to be viewed as a set of keys, collection of values,
 * or set of key-value mappings.  The <i>order</i> of a map is defined as
 * the order in which the iterators on the map's collection views return their
 * elements.  Some map implementations, like the <tt>TreeMap</tt> class, make
 * specific guarantees as to their order; others, like the <tt>HashMap</tt>
 * class, do not.
 *
 * <p>Note: great care must be exercised if mutable objects are used as map
 * keys.  The behavior of a map is not specified if the value of an object is
 * changed in a manner that affects <tt>equals</tt> comparisons while the
 * object is a key in the map.  A special case of this prohibition is that it
 * is not permissible for a map to contain itself as a key.  While it is
 * permissible for a map to contain itself as a value, extreme caution is
 * advised: the <tt>equals</tt> and <tt>hashCode</tt> methods are no longer
 * well defined on such a map.
 *
 * <p>All general-purpose map implementation classes should provide two
 * "standard" constructors: a void (no arguments) constructor which creates an
 * empty map, and a constructor with a single argument of type <tt>Map</tt>,
 * which creates a new map with the same key-value mappings as its argument.
 * In effect, the latter constructor allows the user to copy any map,
 * producing an equivalent map of the desired class.  There is no way to
 * enforce this recommendation (as interfaces cannot contain constructors) but
 * all of the general-purpose map implementations in the JDK comply.
 *
 * <p>The "destructive" methods contained in this interface, that is, the
 * methods that modify the map on which they operate, are specified to throw
 * <tt>UnsupportedOperationException</tt> if this map does not support the
 * operation.  If this is the case, these methods may, but are not required
 * to, throw an <tt>UnsupportedOperationException</tt> if the invocation would
 * have no effect on the map.  For example, invoking the {@link #putAll(Map)}
 * method on an unmodifiable map may, but is not required to, throw the
 * exception if the map whose mappings are to be "superimposed" is empty.
 *
 * <p>Some map implementations have restrictions on the keys and values they
 * may contain.  For example, some implementations prohibit null keys and
 * values, and some have restrictions on the types of their keys.  Attempting
 * to insert an ineligible key or value throws an unchecked exception,
 * typically <tt>NullPointerException</tt> or <tt>ClassCastException</tt>.
 * Attempting to query the presence of an ineligible key or value may throw an
 * exception, or it may simply return false; some implementations will exhibit
 * the former behavior and some will exhibit the latter.  More generally,
 * attempting an operation on an ineligible key or value whose completion
 * would not result in the insertion of an ineligible element into the map may
 * throw an exception or it may succeed, at the option of the implementation.
 * Such exceptions are marked as "optional" in the specification for this
 * interface.
 *
 * <p>Many methods in Collections Framework interfaces are defined
 * in terms of the {@link Object#equals(Object) equals} method.  For
 * example, the specification for the {@link #containsKey(Object)
 * containsKey(Object key)} method says: "returns <tt>true</tt> if and
 * only if this map contains a mapping for a key <tt>k</tt> such that
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
 * <p>Some map operations which perform recursive traversal of the map may fail
 * with an exception for self-referential instances where the map directly or
 * indirectly contains itself. This includes the {@code clone()},
 * {@code equals()}, {@code hashCode()} and {@code toString()} methods.
 * Implementations may optionally handle the self-referential scenario, however
 * most current implementations do not do so.
 *
 * <p>This interface is a member of the
 * <a href="{@docRoot}/../technotes/guides/collections/index.html">
 * Java Collections Framework</a>.
 *
 *	This map have order.
 * @param <K> the type of keys maintained by this map
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
 * @since oaml 004
 */

public class NodeMap<K,V> implements OrderMap<K, V>{
	private final List<OEntry<K, V>> entrys = new ArrayList<>();
	
	/**
     * Returns the number of key-value mappings in this map.  If the
     * map contains more than <tt>Integer.MAX_VALUE</tt> elements, returns
     * <tt>Integer.MAX_VALUE</tt>.
     *
     * @return the number of key-value mappings in this map
     */
	
	@Override
	public int size() {
		return entrys.size();
	}

	 /**
     * Returns <tt>true</tt> if this map contains no key-value mappings.
     *
     * @return <tt>true</tt> if this map contains no key-value mappings
     */
	
	@Override
	public boolean isEmpty() {
		return entrys.isEmpty();
	}

	  /**
     * Returns <tt>true</tt> if this map contains a mapping for the specified
     * key.  More formally, returns <tt>true</tt> if and only if
     * this map contains a mapping for a key <tt>k</tt> such that
     * <tt>(key==null ? k==null : key.equals(k))</tt>.  (There can be
     * at most one such mapping.)
     *
     * @param key key whose presence in this map is to be tested
     * @return <tt>true</tt> if this map contains a mapping for the specified
     *         key
     */
	
	@Override
	public boolean containsKey(Object key) {
		for(OEntry<K, V> entry:entrys) {
			if(entry.getKey().equals(key)) {
				return true;
			}
		}
		return false;
	}

	 /**
     * Returns <tt>true</tt> if this map maps one or more keys to the
     * specified value.  More formally, returns <tt>true</tt> if and only if
     * this map contains at least one mapping to a value <tt>v</tt> such that
     * <tt>(value==null ? v==null : value.equals(v))</tt>.  This operation
     * will probably require time linear in the map size for most
     * implementations of the <tt>Map</tt> interface.
     *
     * @param value value whose presence in this map is to be tested
     * @return <tt>true</tt> if this map maps one or more keys to the
     *         specified value
     */
	
	
	@Override
	public boolean containsValue(Object value) {
		for(OEntry<K, V> entry:entrys) {
			if(entry.getValue().equals(value)) {
				return true;
			}
		}
		return false;
	}

	 /**
     * Returns the value to which the specified key is mapped,
     * or {@code null} if this map contains no mapping for the key.
     *
     * <p>More formally, if this map contains a mapping from a key
     * {@code k} to a value {@code v} such that {@code (key==null ? k==null :
     * key.equals(k))}, then this method returns {@code v}; otherwise
     * it returns {@code null}.  (There can be at most one such mapping.)
     *
     * <p>If this map permits null values, then a return value of
     * {@code null} does not <i>necessarily</i> indicate that the map
     * contains no mapping for the key; it's also possible that the map
     * explicitly maps the key to {@code null}.  The {@link #containsKey
     * containsKey} operation may be used to distinguish these two cases.
     *
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or
     *         {@code null} if this map contains no mapping for the key
     */
	
	@Override
	public V get(Object key) {
		for(OEntry<K, V> entry:entrys) {
			if(entry.getKey().equals(key)) {
				return entry.getValue();
			}
		}
		return null;
	}
	
	/**
     * Associates the specified value with the specified key in this map
     * (optional operation).  If the map previously contained a mapping for
     * the key, the old value is replaced by the specified value.  (A map
     * <tt>m</tt> is said to contain a mapping for a key <tt>k</tt> if and only
     * if {@link #containsKey(Object) m.containsKey(k)} would return
     * <tt>true</tt>.)
     *
     * @param key key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return the previous value associated with <tt>key</tt>, or
     *         <tt>null</tt> if there was no mapping for <tt>key</tt>.
     *         (A <tt>null</tt> return can also indicate that the map
     *         previously associated <tt>null</tt> with <tt>key</tt>,
     *         if the implementation supports <tt>null</tt> values.)
     */
	
	
	
	@Override
	public V put(K key, V value) {
		entrys.add(new NodeEntry(key,value));
		return value;
	}
	
	/**
	 * This method can put the entry with index
	 * @see OrderMap#put(Object, Object)
	 * @param index the index of entry
	 * @param key key with which the specified value is to be associated
   * @param value value to be associated with the specified key
	 * @return the value
	 */
	
	public V put(int index,K key,V value) {
		entrys.add(index, new NodeEntry(key, value));
		return value;
	}

	 /**
     * Removes the mapping for a key from this map if it is present
     * (optional operation).   More formally, if this map contains a mapping
     * from key <tt>k</tt> to value <tt>v</tt> such that
     * <code>(key==null ?  k==null : key.equals(k))</code>, that mapping
     * is removed.  (The map can contain at most one such mapping.)
     *
     * <p>Returns the value to which this map previously associated the key,
     * or <tt>null</tt> if the map contained no mapping for the key.
     *
     * <p>If this map permits null values, then a return value of
     * <tt>null</tt> does not <i>necessarily</i> indicate that the map
     * contained no mapping for the key; it's also possible that the map
     * explicitly mapped the key to <tt>null</tt>.
     *
     * <p>The map will not contain a mapping for the specified key once the
     * call returns.
     *
     * @param key key whose mapping is to be removed from the map
     * @return the previous value associated with <tt>key</tt>, or
     *         <tt>null</tt> if there was no mapping for <tt>key</tt>.
     */
	@Override
	public V remove(Object key) {
		OEntry<K,V> exist = null;
		for(OEntry<K, V> entry:entrys) {
			if(entry.equals(key)) {
				entrys.remove(key);
				exist = entry;
			}
		}
		return exist==null?null:exist.getValue();
	}

	/**
     * Removes all of the mappings from this map (optional operation).
     * The map will be empty after this call returns.
     *
     */
	
	@Override
	public void clear() {
		entrys.clear();
	}

    /**
     * Returns a {@link List} view of the mappings contained in this map.
     * The list is backed by the map, so changes to the map are
     * reflected in the list, and vice-versa.  If the map is modified
     * while an iteration over the list is in progress (except through
     * the iterator's own <tt>remove</tt> operation, or through the
     * <tt>setValue</tt> operation on a map entry returned by the
     * iterator) the results of the iteration are undefined.  The list
     * supports element removal, which removes the corresponding
     * mapping from the map, via the <tt>Iterator.remove</tt>,
     * <tt>Set.remove</tt>, <tt>removeAll</tt>, <tt>retainAll</tt> and
     * <tt>clear</tt> operations.  It does not support the
     * <tt>add</tt> or <tt>addAll</tt> operations.
     *
     * @return a list view of the mappings contained in this map
     */
	
	@Override
	public List<OEntry<K, V>> entryList() {
		return entrys;
	}
	
	 /**
     * A map entry (key-value pair).  The <tt>Map.entrySet</tt> method returns
     * a collection-view of the map, whose elements are of this class.  The
     * <i>only</i> way to obtain a reference to a map entry is from the
     * iterator of this collection-view.  These <tt>Map.Entry</tt> objects are
     * valid <i>only</i> for the duration of the iteration; more formally,
     * the behavior of a map entry is undefined if the backing map has been
     * modified after the entry was returned by the iterator, except through
     * the <tt>setValue</tt> operation on the map entry.
     *
     * @see Map#entrySet()
     * @see OrderMap#entryList()
     * @since 1.2
     * @since oaml004
     */
	
	
	class NodeEntry implements OEntry<K, V>{
		private K key;
		private V value;
		public NodeEntry(K key,V value) {
			this.key = key;
			this.value = value;
		}
		
		  /**
         * Returns the key corresponding to this entry.
         *
         * @return the key corresponding to this entry

         */
		
		
		@Override
		public K getKey() {
			return key;
		}
		
		/**
         * Returns the value corresponding to this entry.  If the mapping
         * has been removed from the backing map (by the iterator's
         * <tt>remove</tt> operation), the results of this call are undefined.
         *
         * @return the value corresponding to this entry
         */
		
		
		@Override
		public V getValue() {
			return value;
		}
		
		 /**
         * Replaces the value corresponding to this entry with the specified
         * value (optional operation).  (Writes through to the map.)  The
         * behavior of this call is undefined if the mapping has already been
         * removed from the map (by the iterator's <tt>remove</tt> operation).
         *
         * @param value new value to be stored in this entry
         * @return old value corresponding to the entry
         */
		
		@Override
		public V setValue(V value) {
			this.value = value;
			return value;
		}
		@Override
		public String toString() {
			return "Entry ["+key+"=" + value + "]";
		}
	}
	@Override
	public String toString() {
		return "NodeMap [entrys=" + entrys + "]";
	}
	   /**
  * Returns a {@link List} view of the mappings contained in this map.
  * The list is backed by the map, so changes to the map are
  * reflected in the list, and vice-versa.  If the map is modified
  * while an iteration over the list is in progress (except through
  * the iterator's own <tt>remove</tt> operation, or through the
  * <tt>setValue</tt> operation on a map entry returned by the
  * iterator) the results of the iteration are undefined.  The list
  * supports element removal, which removes the corresponding
  * mapping from the map, via the <tt>Iterator.remove</tt>,
  * <tt>Set.remove</tt>, <tt>removeAll</tt>, <tt>retainAll</tt> and
  * <tt>clear</tt> operations.  It does not support the
  * <tt>add</tt> or <tt>addAll</tt> operations.
  *
  * @return a list view of the mappings contained in this map
  */
	@Override
	public List<V> values() {
		List<V> collection = new ArrayList<>();
		for(OEntry<K, V> entry:entrys) {
			collection.add(entry.getValue());
		}
		return collection;
	}
	
}
