package org.witchcraft.exceptions;

/** This class represents non critical business exceptions e.g
 *  A user trying to register with an email when another user with that 
 *  email already exists and email is flagged as unique in model. 
 * @author jsingh
 *
 */
public class UniqueConstraintViolationBusinessException extends BusinessException{
	
	private String entityName;
	
	private String columnName;
	
	public UniqueConstraintViolationBusinessException(String entityName, String columnName){
		super(entityName + " " + columnName + " is not unique ");
		entityName = entityName;
		columnName = columnName;
	}

	public UniqueConstraintViolationBusinessException(String message){
		super(message);
	}
	
	public UniqueConstraintViolationBusinessException(String message, Throwable t){
		super(message, t);
	}
}