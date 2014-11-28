package org.witchcraft.model.support.audit;

public interface AuditLogConstants {
 	public static final int ENTITY_LOG_TYPE_READ = 0;
 	public static final int ENTITY_LOG_TYPE_CREATE = 1;
 	public static final int ENTITY_LOG_TYPE_UPDATE = 2;
 	public static final int ENTITY_LOG_TYPE_DELETE = 3;
 
 	public static final int SERVICE_LOG_TYPE_READ = 4;
 	public static final int SERVICE_LOG_TYPE_CREATE = 5;
 	public static final int SERVICE_LOG_TYPE_UPDATE = 6;
 	public static final int SERVICE_LOG_TYPE_DELETE = 7;
 
 	public static final int SERVICE_LOG_TYPE_LOGIN = 8;
 	public static final int SERVICE_LOG_TYPE_LOGOUT = 9;
}
