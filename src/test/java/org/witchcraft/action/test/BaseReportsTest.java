package org.witchcraft.action.test;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.query.JRJpaQueryExecuterFactory;

import org.jboss.seam.annotations.In;
import org.jboss.seam.mock.JUnitSeamTest;
import org.junit.Before;



public class BaseReportsTest extends JUnitSeamTest{

	protected JasperReport jasperReport;
	protected JasperPrint jasperPrint;
	
	private static final String NOMBRE_PERSISTENCE_UNIT = "appEntityManager";

	
	@In(create=true)
	protected EntityManager em;
	
	private EntityManagerFactory emf;

	public EntityManagerFactory getEntityManagerFactory() {
		return emf;
	}

	@Before
	public void init() {
		emf = Persistence.createEntityManagerFactory(NOMBRE_PERSISTENCE_UNIT);
		em = getEntityManagerFactory().createEntityManager();
	
	}

	// @Test
	public void runReportTest(String reportName) throws Exception {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put(JRJpaQueryExecuterFactory.PARAMETER_JPA_ENTITY_MANAGER, em);

		InputStream reportStreamXML = this.getClass().getResourceAsStream(
				"/reports/" + reportName + ".jrxml");

		jasperReport = JasperCompileManager.compileReport(reportStreamXML);
		jasperPrint = JasperFillManager.fillReport(jasperReport, parameters);
		JasperExportManager.exportReportToPdfFile(jasperPrint,
				"src/main/resources/reports/" + reportName + ".pdf");

	}

}
