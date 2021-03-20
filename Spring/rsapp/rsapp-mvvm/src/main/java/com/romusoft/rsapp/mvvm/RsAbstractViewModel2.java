package com.romusoft.rsapp.mvvm;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.Hashtable;

import org.springframework.ui.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author eromu_000
 *
 */
public abstract class RsAbstractViewModel2<TView extends IRsView> {

	@JsonIgnore
	private final String VIEWMODEL_ATTRIBUTE_NAME = "viewModel";
	@JsonIgnore
	private Model model = null;

	private TView view;
	private String id;
	private String name = VIEWMODEL_ATTRIBUTE_NAME;
	private String description;
	//
	// general purpose metadata
	private final Hashtable<String, Object> metadata = new Hashtable<String, Object>();

	/**
	 * 
	 * @param model
	 */
	public RsAbstractViewModel2() {
		this.generateViewInstance(null);
	}

	/**
	 * using this constructor will automatically set the view model attribute of the
	 * model to 'viewModel' if a new name is required, use the default constructor
	 * 
	 * @param model
	 */
	public RsAbstractViewModel2(Model model) {
		setModel(model); // set model and add the current instance as an attribute
		this.generateViewInstance(null);
	}

	public <NewView extends IRsView> RsAbstractViewModel2(Model model, Class<NewView> clazz) {
		setModel(model); // set model and add the current instance as an attribute
		this.generateViewInstance(clazz);
	}

	/**
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @return
	 */
	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
		if (model != null && this.name != null && this.name.isEmpty() == false) {
			model.addAttribute(this.name, this);
		}
	}

	/**
	 * 
	 * @return
	 */
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 
	 * @return
	 */
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 
	 * @return
	 */
	public Hashtable<String, Object> getMetadata() {
		return metadata;
	}

	/**
	 * 
	 * @param name
	 * @param value
	 */
	public void addAttribute(String name, Object value) {
		if (model != null) {
			model.addAttribute(name, value);
		}
	}

	public TView getView() {
		return view;
	}

	public void setView(TView view) {
		this.view = view;
	}

	public String getViewPath() {

		return view.getHtml();
	}

	/**
	 * 
	 * @param clazz
	 */
	@SuppressWarnings("unchecked")
	public <V extends IRsView> void generateViewInstance(Class<V> clazz) {

		if (clazz == null) {
			//
			// get the generic super type of the view, then create an instance of the view
			clazz = (Class<V>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		}

		if (clazz != null) {
			try {
				view = (TView) clazz.getDeclaredConstructor().newInstance();
				view.setModel(model);
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
}
