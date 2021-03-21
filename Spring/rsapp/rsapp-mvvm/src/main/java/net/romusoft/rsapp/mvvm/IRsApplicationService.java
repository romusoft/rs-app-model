package net.romusoft.rsapp.mvvm;

import java.util.List;

/**
 * This is interface allows basic crud operations for a given model
 * 
 * @author eromu_000
 *
 * @param <Model>
 */
public interface IRsApplicationService<Model> {

	/**
	 * find all records
	 * 
	 * @return
	 */
	public List<Model> findAll();

	/**
	 * update a given model
	 * 
	 * @param model
	 * @return
	 */
	public RsAbstractAuditableModel update(Model model);

	/**
	 * add a new model
	 * 
	 * @param model
	 * @return
	 */
	public RsAbstractAuditableModel add(Model model);

	/**
	 * delete a give model
	 * 
	 * @param model
	 */
	public void delete(Model model);

	/**
	 * delete a model by id
	 * 
	 * @param id
	 */
	public void delete(long id);
}
