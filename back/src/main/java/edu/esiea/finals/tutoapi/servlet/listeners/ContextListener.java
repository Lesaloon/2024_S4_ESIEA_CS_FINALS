package edu.esiea.finals.tutoapi.servlet.listeners;

import javax.persistence.EntityManager;

import edu.esiea.finals.tutoapi.dao.DAOBDDHelper;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.persistence.*;

@WebListener
public class ContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(final ServletContextEvent sce) {
		EntityManager em = null;
		try {
            em = DAOBDDHelper.getInstance().getEm();
		} catch (final Exception e) {
			e.printStackTrace();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}
}

