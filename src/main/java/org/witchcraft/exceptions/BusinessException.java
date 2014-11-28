package org.witchcraft.exceptions;

/** This class represents non critical business exceptions e.g
 *  A user trying to register with an email when another user with that 
 *  email already exists and email is flagged as unique in model. 
 * @author jsingh
 *
 */
public class BusinessException extends RuntimeException{

	public BusinessException(String message){
		super(message);
	}
	
	public BusinessException(String message, Throwable t){
		super(message, t);
	}
}