package edu.esiea.finals.tutoapi.dao;

import java.util.List;
import java.util.function.Predicate;

import edu.esiea.finals.tutoapi.interfaces.IDAO;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class GenericDAO<T> implements IDAO<T> {

	protected DAOBDDHelper bdd = null;
	protected EntityManager em = null;
	protected Class<T> persistentClass = null;

	public GenericDAO(final Class<T> persistentClass) {
		try {
			this.bdd = DAOBDDHelper.getInstance();
			this.em = this.bdd.getEm();
		} catch (final Exception e) {
			this.bdd = null;
			System.out.println("Unable to create EntityManager");
		}
		this.persistentClass = persistentClass;
	}

	@Override
	public T create(T obj) throws Exception {
		this.bdd.beginTransaction();
		this.bdd.getEm().persist(obj);
		this.bdd.getEm().getTransaction().commit();
		return obj;
	}

	@Override
	public List<T> bulkCreate(List<T> objs) throws Exception {
		this.bdd.beginTransaction();
		for (final T obj : objs) {
			this.bdd.getEm().persist(obj);
		}
		this.bdd.getEm().getTransaction().commit();
		return objs;
	}

	@Override
	public T read(int id) throws Exception {
		final Query query = this.em
				.createQuery("select e from " + this.persistentClass.getSimpleName() + " e" + " where e.id =:id")
				.setParameter("id", id);
		return query.getResultList().isEmpty() ? null : (T) query.getSingleResult();
	}

	@Override
	public List<T> readAll() throws Exception {
		final Query query = this.em.createQuery("select e from " + this.persistentClass.getSimpleName() + " e");
		// Récupère
		return query.getResultList();
	}

	@Override
	public List<T> search(Predicate<T> lambda) throws Exception {
		final List<T> list = this.readAll();
		return list.stream().filter(lambda).toList();
	}

	@Override
	public void update(T obj) {
		this.bdd.beginTransaction();
		this.bdd.getEm().merge(obj);
		this.bdd.getEm().getTransaction().commit();
	}

	@Override
	public void bulkUpdate(List<T> objs) throws Exception {
		this.bdd.beginTransaction();
		for (final T obj : objs) {
			this.bdd.getEm().merge(obj);
		}
		this.bdd.getEm().getTransaction().commit();
	}

	@Override
	public void delete(T obj) throws Exception {
		this.bdd.beginTransaction();
		this.bdd.getEm().remove(obj);
		this.bdd.getEm().getTransaction().commit();
	}

	@Override
	public void delete(int id) throws Exception {
		this.bdd.beginTransaction();
		final T obj = this.read(id);
		this.bdd.getEm().remove(obj);
		this.bdd.getEm().getTransaction().commit();

	}

	@Override
	public void bulkDelete(List<T> objs) throws Exception {
		this.bdd.beginTransaction();
		for (final T obj : objs) {
			this.bdd.getEm().remove(obj);
		}
		this.bdd.getEm().getTransaction().commit();
	}

	@Override
	public void bulkDelete(int[] ids) throws Exception {
		this.bdd.beginTransaction();
		for (final int id : ids) {
			final T obj = this.read(id);
			this.bdd.getEm().remove(obj);
		}
		this.bdd.getEm().getTransaction().commit();
	}

}

