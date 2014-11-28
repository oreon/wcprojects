package org.witchcraft.base.entity;

import java.util.Date;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.seam.Component;
import org.witchcraft.seam.action.UserUtilAction;
import org.witchcraft.users.AppUser;

public class EntityListener {
	
	Log log = LogFactory.getLog(EntityListener.class);
	
	@PrePersist
	public void setDatesAndUser(BaseEntity modelBase) {
		Date now = new Date();
		if (modelBase.getDateCreated() == null) {
			modelBase.setDateCreated(now);
		}

		modelBase.setDateUpdated(now);

		try {

			UserUtilAction userUtilAction = (UserUtilAction)Component.getInstance("userUtilAction");
			AppUser currentUser = userUtilAction.getCurrentUser();

			if (currentUser != null) {
				if (modelBase.getCreatedByUser() == null) {
					modelBase.setCreatedByUser(currentUser);
				}
			}else{
//				log.warn("No creator for " + modelBase.getClass().getSimpleName() + "-> " + modelBase.getDisplayName());
			}

		} catch (IllegalStateException e) {
			if (log.isInfoEnabled())
				log.info("couldn't get component instance");
		}

	}
	
	@PreUpdate
	public void setUpdateDateAndUser(BaseEntity modelBase){
		Date now = new Date();
		modelBase.setDateUpdated(now);
	}

}
