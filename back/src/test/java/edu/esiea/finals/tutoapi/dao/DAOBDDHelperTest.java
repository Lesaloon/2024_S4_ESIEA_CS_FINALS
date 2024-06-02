package edu.esiea.finals.tutoapi.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

class DAOBDDHelperTest {

	private static DAOBDDHelper daoHelper;
	private EntityManager em;

	@BeforeAll
	public static void setUpBeforeClass() throws Exception {
		DAOBDDHelper.setInstance("TutoPU-test");
		daoHelper = DAOBDDHelper.getInstance();
	}

	@BeforeEach
	public void setUp() throws Exception {
		em = daoHelper.getEm();
	}

	@AfterEach
	public void tearDown() {
		if (em.getTransaction().isActive()) {
			em.getTransaction().rollback();
		}
	}

	@Test
	public void testBeginTransaction() {
		daoHelper.beginTransaction();
		EntityTransaction transaction = em.getTransaction();
		assertTrue(transaction.isActive());
	}

	@Test
	public void testCommitTransaction() {
		daoHelper.beginTransaction();
		daoHelper.commitTransaction();
		EntityTransaction transaction = em.getTransaction();
		assertFalse(transaction.isActive());
	}

	@Test
	public void testRollbackTransaction() {
		daoHelper.beginTransaction();
		daoHelper.rollbackTransaction();
		EntityTransaction transaction = em.getTransaction();
		assertFalse(transaction.isActive());
	}

	@Test
	public void testGetEm() {
		assertNotNull(daoHelper.getEm());
	}

}
