package edu.esiea.finals.tutoapi.dao;


import org.apache.logging.log4j.Logger;
import org.eclipse.persistence.sessions.Session;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;



public class DAOBDDHelper {
	private static DAOBDDHelper instance;
	private final EntityManager em;
	private static final Logger logger = org.apache.logging.log4j.LogManager.getLogger(DAOBDDHelper.class);

	private DAOBDDHelper(final String persistenceUnitName) throws Exception {
		try {
			this.em = Persistence.createEntityManagerFactory(persistenceUnitName).createEntityManager();
			final Session session = this.em.unwrap(Session.class);
			System.out.println("EntityManager créé : " + session.getDatasourcePlatform().toString());
		} catch(final Exception e) {
			logger.error("Impossible de créer l'EntityManager.", e);
			throw new Exception("Impossibl e de créer l'EntityManager.", e);
		}
	}

	public static DAOBDDHelper getInstance() throws Exception {
		if (DAOBDDHelper.instance == null) {
			DAOBDDHelper.instance = new DAOBDDHelper("TutoPU");
		}
		return DAOBDDHelper.instance;
	}
	
	public static DAOBDDHelper setTestInstance() throws Exception {
		instance = new DAOBDDHelper("TutoPU-test");
		return instance;
	}
	
	public static DAOBDDHelper setInstance(final String persistenceUnitName) throws Exception {
		instance = new DAOBDDHelper(persistenceUnitName);
		return instance;
	}

	public EntityManager getEm() {

		return this.em;
	}

	public void beginTransaction() {
		this.em.getTransaction().begin();
	}

	public void commitTransaction() {
		final EntityTransaction transaction = this.em.getTransaction();
		if (transaction.isActive()) {
			transaction.commit();
		}
	}

	public void rollbackTransaction() {
		final EntityTransaction transaction = this.em.getTransaction();
		if (transaction.isActive()) {
			transaction.rollback();
		}
	}
	
	public void close() {
		this.em.close();
	}
}
