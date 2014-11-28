package org.witchcraft.exceptions;

/** This class represents  contract violation exception - the ones that should never have happened and are very likely programmers 
 *  fault e.g if a function recieves a null / invalid arguement they can throw this exception.
 * @author jsingh
 */
public class ContractViolationException extends RuntimeException{

	public ContractViolationException(String message){
		super(message);
	}
	
	public ContractViolationException(String message, Throwable t){
		super(message, t);
	}
}
