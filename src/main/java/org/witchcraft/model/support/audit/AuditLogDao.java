package org.witchcraft.model.support.audit;

import java.util.List;

public interface AuditLogDao {
	
	public List<AuditLog> getAuditLogsForEntity(String name);

	public AuditLog persist(AuditLog auditLog);

}
