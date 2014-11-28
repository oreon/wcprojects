package org.witchcraft.action.test;

import java.io.File;

import javax.persistence.Query;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.OverProtocol;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.seam.security.Identity;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.importer.ZipImporter;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.witchcraft.seam.action.BaseAction;
import org.witchcraft.users.AppRole;
import org.witchcraft.users.AppUser;

@RunWith(Arquillian.class)
public class AuthenticatorTest extends BaseTest<AppUser> {

	
	@Before
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<AppUser> getAction() {
		
		return null;
	}
	
	
	@Deployment
	@OverProtocol("Servlet 3.0")
	public static Archive<?> createDeployment()
	{
		WebArchive web = ShrinkWrap.create(ZipImporter.class)
				.importFrom(new File("../ma/target/ma.war"))
				.as(WebArchive.class);
		web.addClasses(AuthenticatorTest.class);
		
		return web;
	}

	@Test
	public void validateAuthenticationBadUser() throws Exception {
		new ComponentTest() {

			protected void testComponents() throws Exception {
				Identity.instance().getCredentials().setUsername("admin");
				Identity.instance().getCredentials().setPassword("admin");
				
			
				  assert invokeMethod( "#{authenticator.authenticate}")
				  .equals(true);
				 
			}

		}.run();
	}

	@Test
	public void testRegisterAction() throws Exception {
		em.getTransaction().begin();

		Query query = em
				.createQuery("Select u From AppUser u where u.userName = ?1 ");
		query.setParameter(1, "admin");
		if (!query.getResultList().isEmpty())
			return;

		new ComponentTest() {

			protected void testComponents() throws Exception {
				Identity.instance().getCredentials().setUsername("admin");
				Identity.instance().getCredentials().setPassword("admin");

				createUserAndRole( "admin", "admin", "admin");
				createUserAndRole( "jim", "jim", "support");
				createUserAndRole( "roger", "roger", "support");
				createUserAndRole( "erica", "erica", "manager");
			}
			
		}.run();

		// em.getTransaction().commit();
		em.close();
	}
	
	private AppUser createUserAndRole(String username, String password, String role) {
		em.getTransaction().begin();
		
		AppUser admin = new AppUser();
		admin.setUserName(username);
		admin.setPassword(password);
		
		AppRole adminRole = new AppRole();
		adminRole.setName(role);
		admin.getAppRoles().add(adminRole);
		admin.setEnabled(true);
		
		//setValue("#{userAction.instance}", admin);
		//invokeMethod("#{userAction.save}");
		em.persist(admin);
		em.getTransaction().commit();
		return admin;
	}
	


    

}