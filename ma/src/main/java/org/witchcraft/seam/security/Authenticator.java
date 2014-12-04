package org.witchcraft.seam.security;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.jboss.seam.Component;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.bpm.Actor;
import org.jboss.seam.international.StatusMessage.Severity;
import org.jboss.seam.international.StatusMessages;
import org.jboss.seam.log.Log;
import org.jboss.seam.security.Credentials;
import org.jboss.seam.security.Identity;
import org.jboss.seam.security.permission.RuleBasedPermissionResolver;
import org.witchcraft.seam.action.UserUtilAction;
import org.witchcraft.users.AppRole;
import org.witchcraft.users.AppUser;

@Name("authenticator")
public class Authenticator {

	@Logger
	Log log;

	@In
	EntityManager entityManager;

	@In
	Credentials credentials;

	@In(required = false)
	Actor actor;

	@In
	Identity identity;

	@In
	protected StatusMessages statusMessages;

	public boolean authenticate() {

		try {

			// for multitenanted uncomment
			// Session session = (Session) entityManager.getDelegate();
			// session.disableFilter("tenantFilterDef");

			AppUser user = (AppUser) entityManager
					.createQuery(
							"from AppUser where username = :username and password = :password")
					.setParameter("username", credentials.getUsername())
					.setParameter("password", credentials.getPassword())
					.getSingleResult();

			if (!user.getEnabled()) {
				addErrorMessage("Your account has been disabled - please contact support ");

				// add message not enalbed
				return false;
			}

			if (user.getAppRoles() != null) {
				Set<AppRole> roles = user.getAppRoles();
				for (AppRole role : roles) {
					identity.addRole(role.getName());
				}
			} else {
				log.warn("no role found for user " + user.getUserName());
			}
			updateActor(user);
			UserUtilAction userUtilAction = (UserUtilAction) Component
					.getInstance("userUtilAction");

			RuleBasedPermissionResolver resolver = RuleBasedPermissionResolver
					.instance();
			
			if (resolver != null) {
				resolver.getSecurityContext().insert(user);
			}

			userUtilAction.setCurrentUser(user);
			// userUtilAction.setCurrentFacility(currentFacility);
			/*
			 * user.setLastLogin(new Date()); UserAction userAction =
			 * (UserAction) Component.getInstance("userAction");
			 * userAction.setInstance(user); userAction.save();
			 */

			// for multitenanted uncomment
			// session.enableFilter("tenantFilterDef").setParameter("tenantId",user.getTenant());

			return true;
		}

		catch (NoResultException ex) {

			return false;

		} finally {

		}

	}

	private void updateActor(AppUser user) {
		if (actor == null)
			return;
		actor.setId(user.getUserName());
		Set<AppRole> roles = user.getAppRoles();
		for (AppRole role : roles) {
			actor.getGroupActorIds().add(role.getName());
		}
	}

	protected void addErrorMessage(String message, Object... params) {
		statusMessages.add(Severity.ERROR, message, params);
	}

}