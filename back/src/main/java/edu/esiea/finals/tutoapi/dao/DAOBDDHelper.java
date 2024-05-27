package edu.esiea.finals.tutoapi.dao;


import org.eclipse.persistence.sessions.Session;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class DAOBDDHelper {
	private static DAOBDDHelper instance;
	private final EntityManager em;

	private DAOBDDHelper(final String persistenceUnitName) throws Exception {
		try {
			this.em = Persistence.createEntityManagerFactory(persistenceUnitName).createEntityManager();
			final Session session = this.em.unwrap(Session.class);
			System.out.println("EntityManager créé : " + session.getDatasourcePlatform().toString());
		} catch(final Exception e) {
			throw new Exception("Impossible de créer l'EntityManager.", e);
		}
	}

	public static DAOBDDHelper getInstance() throws Exception {
		if (DAOBDDHelper.instance == null) {
			DAOBDDHelper.instance = new DAOBDDHelper("back");
		}
		return DAOBDDHelper.instance;
	}
	
	public static DAOBDDHelper setTestInstance() throws Exception {
		instance = new DAOBDDHelper("back-test");
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

}
