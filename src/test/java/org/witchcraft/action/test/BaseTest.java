package  org.witchcraft.action.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.jboss.seam.annotations.In;
import org.jboss.seam.mock.JUnitSeamTest;
import org.jboss.seam.security.Identity;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.witchcraft.base.entity.BaseEntity;
import org.witchcraft.seam.action.BaseAction;

public abstract class BaseTest<T extends BaseEntity> extends JUnitSeamTest {
	
	private static final String NOMBRE_PERSISTENCE_UNIT = "appEntityManager";
	private static EntityManagerFactory emf;
	
	@In(create=true)
	protected static EntityManager em;

	public EntityManagerFactory getEntityManagerFactory() {
		return emf;
	}
	
	public void init(){
		
	}

	@BeforeClass
	public  static void initme() {
		emf = Persistence.createEntityManagerFactory(NOMBRE_PERSISTENCE_UNIT);
		em = emf.createEntityManager();
		//em.getTransaction().begin();
		//getAction().setEntityManager(Search.getFullTextEntityManager(em));
	}
	
	//@Before
	public void beginTx() {
		try {
			em.getTransaction().begin();
		} catch (Exception e) {
			//e.printStackTrace();
		}
		super.begin();
	}

	//@After
	public void endTx() {
		try {
			if(em.getTransaction() != null && em.getTransaction().isActive())
				em.getTransaction().rollback();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	abstract public BaseAction<T> getAction();

	@AfterClass
	public static void destroy() {
		em.close();
		emf.close();
	}
	
	public void login(final String username, final String pwd){
		try {
			new ComponentTest() {
				protected void testComponents() throws Exception {
					Identity.instance().getCredentials().setUsername(username);
					Identity.instance().getCredentials().setPassword(pwd);
					
					  assert invokeMethod( "#{authenticator.authenticate}")
					  .equals(true);
				}

			}.run();
		} catch (Exception e) {
	
		}
	}

}
