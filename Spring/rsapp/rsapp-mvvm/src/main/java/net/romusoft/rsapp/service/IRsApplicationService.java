package net.romusoft.rsapp.service;

import java.util.List;

import net.romusoft.rsapp.model.RsEntityModelBase;

/**
 * This is interface defines basic CRUD operations for a given model
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
	public RsEntityModelBase update(Model model);

	/**
	 * add a new model
	 * 
	 * @param model the model to add
	 * @return the added model
	 */
	public RsEntityModelBase add(Model model);

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
