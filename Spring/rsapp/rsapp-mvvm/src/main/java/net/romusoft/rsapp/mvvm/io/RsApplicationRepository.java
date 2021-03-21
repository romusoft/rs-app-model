package net.romusoft.rsapp.mvvm.io;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author Romulus
 *
 */
@Repository
@Transactional
public class RsApplicationRepository {

	@Autowired
	private EntityManager entityManager;

	/**
	 * 
	 * @param <T>
	 * @param nativeSqlString
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public <T> List<T> findAll(String nativeSqlString, Class<T> clazz) throws Exception {

		return findAll(nativeSqlString, null, clazz);
	}

	/**
	 * 
	 * @param <T>
	 * @param nativeSqlString
	 * @param parameterMap
	 * @param clazz
	 * @return
	 * @throws Exception
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
	 * 
	 * @param <T>
	 * @param nativeSqlString
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public <T> T findOne(String nativeSqlString, Class<T> clazz) throws Exception {
		return findOne(nativeSqlString, null, clazz);
	}

	/**
	 * 
	 * @param <T>
	 * @param nativeSqlString
	 * @param parameterMap
	 * @param clazz
	 * @return
	 * @throws Exception
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

	public <T> T findScalarValue(String nativeSqlString, Class<T> clazz) throws Exception {
		return findScalarValue(nativeSqlString, null, clazz);
	}

	/**
	 * 
	 * @param <T>
	 * @param nativeSqlString
	 * @param parameterMap
	 * @param clazz
	 * @return
	 * @throws Exception
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
	 * 
	 * @param <T>
	 * @param entity
	 * @return
	 * @throws Exception
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
	 * 
	 * @param <T>
	 * @param entity
	 * @throws Exception
	 */
	public <T> void delete(T entity) throws Exception {

		try {
			entityManager.remove(entity);

		} catch (Exception e) {
			throw new Exception("while retrieving the shift log." + e);
		}
	}
}
