package edu.esiea.finals.tutoapi.interfaces;

import java.util.List;
import java.util.function.Predicate;

public interface IDAO<T> {
	/**
	 * Create a new object
	 *
	 * @param obj
	 * @return
	 * @throws DaoException
	 */
	T create(T obj) throws Exception;

	/**
	 * Bulk create
	 *
	 * @param objs
	 * @return List<T>
	 * @throws DaoException
	 */
	List<T> bulkCreate(List<T> objs) throws Exception;

	/**
	 * Read an object by its id
	 *
	 * @param id
	 * @return T
	 * @throws DaoException
	 */
	T read(int id) throws Exception;

	/**
	 * Read all objects
	 *
	 * @return List<T>
	 * @throws DaoException
	 */
	List<T> readAll() throws Exception;

	/**
	 * Search an object by lambda using the stream API
	 *
	 * @param lambda
	 * @return List<T>
	 * @throws DaoException
	 */
	List<T> search(Predicate<T> lambda) throws Exception;

	// Update an object
	void update(T obj);

	/**
	 * Bulk update
	 *
	 * @param objs
	 * @throws DaoException
	 */
	void bulkUpdate(List<T> objs) throws Exception;

	/**
	 * Delete an object
	 *
	 * @param obj
	 * @throws DaoException
	 */
	void delete(T obj) throws Exception;

	/**
	 * Delete an object
	 *
	 * @param id
	 * @throws DaoException
	 */
	void delete(int id) throws Exception;

	/**
	 * Bulk delete
	 *
	 * @param objs
	 * @throws DaoException
	 */
	void bulkDelete(List<T> objs) throws Exception;

	/**
	 * Bulk delete
	 *
	 * @param ids
	 * @throws DaoException
	 */
	void bulkDelete(int[] ids) throws Exception;
}

