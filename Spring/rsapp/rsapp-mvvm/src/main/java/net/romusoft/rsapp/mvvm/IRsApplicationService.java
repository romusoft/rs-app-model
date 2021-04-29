package net.romusoft.rsapp.mvvm;

import java.util.List;

/**
 * This is interface allows basic crud operations for a given model
 * 
 * @author Emmanuel Romulus
 *
 * @param <Model> the generic type of model to operate on
 */
public interface IRsApplicationService<Model> {

	/**
	 * find all records
	 * 
	 * @return all records
	 */
	public List<Model> findAll();

	/**
	 * update a given model
	 * 
	 * @param model the model to update
	 * @return the updated record of type RsAbstractAuditableModel
	 */
	public RsAbstractEntityModel update(Model model);

	/**
	 * add a new model
	 * 
	 * @param model the model to add
	 * @return the added model
	 */
	public RsAbstractEntityModel add(Model model);

	/**
	 * delete a give model
	 * 
	 * @param model the model to delete
	 */
	public void delete(Model model);

	/**
	 * delete a model by id
	 * 
	 * @param id the id of the model to delete
	 */
	public void delete(long id);
}
