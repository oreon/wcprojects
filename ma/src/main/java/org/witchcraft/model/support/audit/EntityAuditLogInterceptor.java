package org.witchcraft.model.support.audit;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.Transient;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Observer;
import org.jboss.seam.security.Credentials;
import org.jboss.seam.security.Identity;
import org.witchcraft.base.entity.BaseEntity;
import org.witchcraft.seam.action.BaseAction;
import org.witchcraft.seam.action.EventTypes;

/**
 * @author jsingh
 * 
 */
@Name("entityAuditLogInterceptor")
public class EntityAuditLogInterceptor extends BaseAction<AuditLog>{

	protected Log logger = LogFactory.getLog(getClass());

	//private AuditLogDao auditLogDao;

	//@Override
	@Observer(value = {"ARCHIVE", "CREATE" , "UPDATE", "DELETE"})
	public boolean onEvent(EventTypes event, Object entity ) {
		saveAuditLog(event, entity );
		return true;
	}

	@SuppressWarnings("unchecked")
	private void saveAuditLog(EventTypes action, Object entity) {

		//log.info("SAVE AUDIT LOG CALLED FOR " + action);
		
		if (entity instanceof Auditable) {
			Credentials credentials = Identity.instance().getCredentials();
			String userName = credentials == null ? "UNKNOWN": credentials.getUsername();
			BaseEntity businessEntity = (BaseEntity) entity	;
			AuditLog auditLog = new AuditLog(action, businessEntity,  entity.getClass().getCanonicalName(), businessEntity.getId(),  userName);
			persist(auditLog);
		}
	}

	

	private byte[] objectToByteArray(Object object) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream oos;
		byte barr[] = null;

		try {
			oos = new ObjectOutputStream(bos);
			oos.writeObject(bos);
			barr = bos.toByteArray();

			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return barr;
	}
	
	@Transient
	public List<AuditLog> getAuditLogsForEntity(String name){
		String qryString = "Select e from AuditLog e where  e.entityName = ?1";
		Query query = getEntityManager().createQuery(qryString).setParameter(1, name);
		return query.getResultList();
	}
	
	
	@Transient
	public List<AuditLog> getAuditLogsForEntityAndId(String name, Long id){
		String qryString = "Select e from AuditLog e where  e.entityName = ?1 and e.entityId = ?2";
		Query query = getEntityManager().createQuery(qryString).setParameter(1, name).setParameter(2, id);
		return query.getResultList();
	}

	

}
