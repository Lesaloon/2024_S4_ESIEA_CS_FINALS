package edu.esiea.finals.tutoapi.servlet.listeners;

import javax.persistence.EntityManager;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {

	private static EntityManager em;

	//	@Override
	//	public void contextInitialized(ServletContextEvent sce) {
	//		final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CDC-API");
	//		CDCContextListener.em = entityManagerFactory.createEntityManager();
	//	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		if (ContextListener.em != null) {
			ContextListener.em.close();
		}
	}

	public EntityManager getEntityManager() {
		return ContextListener.em;
	}
}
