/*
 * $Id: IgnoreException.java 263 2006-05-04 20:58:25Z lhoriman $
 * $Source: /cvsroot/Similarity4/src/java/com/similarity/ejb/NameAlreadyTakenException.java,v $
 */

package org.subethamail.core.plugin.i;

/**
 * Exception indicates that a message should be silently dropped.
 */
public class IgnoreException extends Exception
{
	private static final long serialVersionUID = 1L;

	/**
	 */
	public IgnoreException(String msg)
	{
		super(msg);
	}
	
	/**
	 */
	public IgnoreException(Throwable cause)
	{
		super(cause);
	}
}

