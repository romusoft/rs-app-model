package net.romusoft.rsapp.mvvm.io;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * This class supports native sql persistence with the current entitymanager
 * 
 * @author Emmanuel Romulus
 *
 */
@Repository
@Transactional
public class RsApplicationRepository {

	@Autowired
	private EntityManager entityManager;

	/**
	 * Find all records
	 * 
	 * @param <T>             the target type to return
	 * @param nativeSqlString the native query to execute to retrieve the data
	 * @param clazz           the target data type to return the list in
	 * @return a list of T data
	 * @throws Exception exception to be thrown if any
	 */
	public <T> List<T> findAll(String nativeSqlString, Class<T> clazz) throws Exception {

		return findAll(nativeSqlString, null, clazz);
	}

	/**
	 * find all
	 * 
	 * @param <T>             the target type to return
	 * @param nativeSqlString the native query to execute to retrieve the data
	 * @param parameterMap    parameter list
	 * @param clazz           the target data type to return the list in
	 * @return a list of T data
	 * @throws Exception Exception exception to be thrown if any
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> findAll(String nativeSqlString, Map<String, Object> parameterMap, Class<T> clazz)
			throws Exception {

		Query query = entityManager.createNativeQuery(nativeSqlString, clazz);
		// Set parameters
		if (parameterMap != null) {
			parameterMap.forEach((k, v) -> query.setParameter(k, v));
		}
		List<T> data = null;
		try {

			/*
			 * execute the query and return the data set
			 */
			data = query.getResultList();

		} catch (Exception e) {

			throw new Exception("Error during query execution. \n" + e.getMessage());
		}

		return data;
	}

	/**
	 * find one record
	 * 
	 * @param <T>             the target type to return
	 * @param nativeSqlString the native query to execute to retrieve the data
	 * @param clazz           the target data type to return the list in
	 * @return a list of T data
	 * @throws Exception Exception exception to be thrown if any
	 */
	public <T> T findOne(String nativeSqlString, Class<T> clazz) throws Exception {
		return findOne(nativeSqlString, null, clazz);
	}

	/**
	 * find one record using parameters
	 * 
	 * @param <T>             the target type to return
	 * @param nativeSqlString the native query to execute to retrieve the data
	 * @param parameterMap    parameter list
	 * @param clazz           the target data type to return the list in
	 * @return a list of T data
	 * @throws Exception Exception exception to be thrown if any
	 */
	@SuppressWarnings("unchecked")
	public <T> T findOne(String nativeSqlString, Map<String, Object> parameterMap, Class<T> clazz) throws Exception {

		Query query = entityManager.createNativeQuery(nativeSqlString, clazz);
		// Set parameters
		if (parameterMap != null) {
			parameterMap.forEach((k, v) -> query.setParameter(k, v));
		}
		T data = null;
		try {

			/*
			 * execute the query and return the data set
			 */
			data = (T) query.getSingleResult();

		} catch (Exception e) {

			throw new Exception("Error during query execution. \n" + e.getMessage());
		}

		return data;
	}

	/**
	 * find a scalar value
	 * 
	 * @param <T>             the target type to return
	 * @param nativeSqlString the native query to execute to retrieve the data
	 * @param clazz           the target data type to return the list in
	 * @return a list of T data
	 * @throws Exception Exception exception to be thrown if any
	 */
	public <T> T findScalarValue(String nativeSqlString, Class<T> clazz) throws Exception {
		return findScalarValue(nativeSqlString, null, clazz);
	}

	/**
	 * find a scalar value using parameters
	 * 
	 * @param <T>             the target type to return
	 * @param nativeSqlString the native query to execute to retrieve the data
	 * @param parameterMap    parameter list
	 * @param clazz           the target data type to return the list in
	 * @return a list of T data
	 * @throws Exception Exception exception to be thrown if any
	 */
	@SuppressWarnings("unchecked")
	public <T> T findScalarValue(String nativeSqlString, Map<String, Object> parameterMap, Class<T> clazz)
			throws Exception {

		Query query = entityManager.createNativeQuery(nativeSqlString);
		// Set parameters
		if (parameterMap != null) {
			parameterMap.forEach((k, v) -> query.setParameter(k, v));
		}
		T data = null;
		try {

			/*
			 * execute the query and return the data set
			 */
			data = (T) query.getSingleResult();

		} catch (Exception e) {

			throw new Exception("Error during query execution. \n" + e.getMessage());
		}

		return data;
	}

	/**
	 * save a given entity
	 * 
	 * @param <T>    the target type to return
	 * @param entity the entity to save
	 * @return a list of T data
	 * @throws Exception Exception exception to be thrown if any
	 */
	public <T> T save(T entity) throws Exception {

		try {
			entityManager.persist(entity);

		} catch (Exception e) {

			throw new Exception("while retrieving the shift log." + e);
		}
		return entity;
	}

	/**
	 * delete a give entity
	 * 
	 * @param <T>    the target type to return
	 * @param entity the entity to delete
	 * @throws Exception Exception exception to be thrown if any
	 */
	public <T> void delete(T entity) throws Exception {

		try {
			entityManager.remove(entity);

		} catch (Exception e) {
			throw new Exception("while retrieving the shift log." + e);
		}
	}
}
