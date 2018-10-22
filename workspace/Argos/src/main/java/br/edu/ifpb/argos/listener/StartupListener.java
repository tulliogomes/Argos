package br.edu.ifpb.argos.listener;

import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.apache.log4j.Logger;
import br.edu.ifpb.argos.dao.PersistenceUtil;

@WebListener
public class StartupListener implements ServletContextListener {
	private static Logger logger = Logger.getLogger(StartupListener.class);

	public void contextDestroyed(ServletContextEvent arg0) {
		EntityManagerFactory emf = PersistenceUtil.getEntityManagerFactory();
		if (emf != null) {
			emf.close();
			logger.info("F�brica de EntityManagers fechada.");
		}
	}

	public void contextInitialized(ServletContextEvent event) {
		PersistenceUtil.createEntityManagerFactory("argos");
		logger.info("Fábrica de EntityManagers instanciada.");
	}

}
