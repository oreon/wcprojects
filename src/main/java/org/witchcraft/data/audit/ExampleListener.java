package org.witchcraft.data.audit;

import org.hibernate.envers.RevisionListener;
import org.jboss.seam.security.Identity;
import org.jboss.seam.Component;

/**
 * 
 * @author jsingh
 *
 */
public class ExampleListener implements RevisionListener {
	
    public void newRevision(Object revisionEntity) {
        CustomRevisionEntity exampleRevEntity = (CustomRevisionEntity) revisionEntity;
        Identity identity =
            (Identity) Component.getInstance("org.jboss.seam.security.identity");

        exampleRevEntity.setUsername(identity.getCredentials().getUsername());
    }
}