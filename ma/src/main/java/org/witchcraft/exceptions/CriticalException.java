package org.witchcraft.exceptions;

/** This class represents  critical excpetions such as resource unavailable
 *  or undhandled application exceptions.  
 * @author jsingh
 */
public class CriticalException extends RuntimeException{

	public CriticalException(String message){
		super(message);
	}
	
	public CriticalException(String message, Throwable t){
		super(message, t);
	}
}
