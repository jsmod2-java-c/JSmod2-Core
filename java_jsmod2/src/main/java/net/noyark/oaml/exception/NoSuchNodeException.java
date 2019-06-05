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
package net.noyark.oaml.exception;

/**
 * When trying to get a child node,but it has not
 * created a child node of this name,it will throw
 * this exception.If this exception occurs,please 
 * check if the create method is written as a get 
 * method.
 * @author magiclu550
 * @since JDK1.8
 * @since oaml005
 *
 */

public class NoSuchNodeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/** Constructs a new runtime exception with {@code null} as its
     * detail message.  The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     */
	
	public NoSuchNodeException() {
		super();
	}
	
	  /**
     * Constructs a new runtime exception with the specified detail
     * message, cause, suppression enabled or disabled, and writable
     * stack trace enabled or disabled.
     *
     * @param  message the detail message.
     * @param cause the cause.  (A {@code null} value is permitted,
     * and indicates that the cause is nonexistent or unknown.)
     * @param enableSuppression whether or not suppression is enabled
     *                          or disabled
     * @param writableStackTrace whether or not the stack trace should
     *                           be writable
     *
     * @since 1.7
     */
	
	public NoSuchNodeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	
	   /**
  * Constructs a new runtime exception with the specified detail message and
  * cause.  <p>Note that the detail message associated with
  * {@code cause} is <i>not</i> automatically incorporated in
  * this runtime exception's detail message.
  *
  * @param  message the detail message (which is saved for later retrieval
  *         by the {@link #getMessage()} method).
  * @param  cause the cause (which is saved for later retrieval by the
  *         {@link #getCause()} method).  (A <tt>null</tt> value is
  *         permitted, and indicates that the cause is nonexistent or
  *         unknown.)
  * @since  1.4
  */
	
	public NoSuchNodeException(String message, Throwable cause) {
		super(message, cause);
	}

	 /** Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param   message   the detail message. The detail message is saved for
     *          later retrieval by the {@link #getMessage()} method.
     */
	
	public NoSuchNodeException(String message) {
		super(message);
	}

	   /** Constructs a new runtime exception with the specified cause and a
  * detail message of <tt>(cause==null ? null : cause.toString())</tt>
  * (which typically contains the class and detail message of
  * <tt>cause</tt>).  This constructor is useful for runtime exceptions
  * that are little more than wrappers for other throwables.
  *
  * @param  cause the cause (which is saved for later retrieval by the
  *         {@link #getCause()} method).  (A <tt>null</tt> value is
  *         permitted, and indicates that the cause is nonexistent or
  *         unknown.)
  * @since  1.4
  */
	
	public NoSuchNodeException(Throwable cause) {
		super(cause);
	}

}
