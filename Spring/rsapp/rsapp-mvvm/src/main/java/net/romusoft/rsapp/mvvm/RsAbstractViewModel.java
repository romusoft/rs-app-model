package net.romusoft.rsapp.mvvm;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.Hashtable;

import org.springframework.ui.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Describe the viewmodel for mvc/mvvm support
 * 
 * @author Emmanuel Romulus
 *
 * @param <TView> the view data type
 */
public abstract class RsAbstractViewModel<TView extends IRsView> {

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
	 * default constructor
	 */
	public RsAbstractViewModel() {
		this.generateViewInstance(null);
	}

	/**
	 * using this constructor will automatically set the view model attribute of the
	 * model to 'viewModel' if a new name is required, use the default constructor
	 * 
	 * @param model spring mvc model where attributes are stored
	 */
	public RsAbstractViewModel(Model model) {
		setModel(model); // set model and add the current instance as an attribute
		this.generateViewInstance(null);
	}

	/**
	 * Constructor used to override the view in the class view declaration
	 * 
	 * @param <NewView> override the default view in the class definition with a new
	 *                  view
	 * @param model     the spring mvc model
	 * @param clazz     the target type of the view
	 */
	public <NewView extends IRsView> RsAbstractViewModel(Model model, Class<NewView> clazz) {
		setModel(model); // set model and add the current instance as an attribute
		this.generateViewInstance(clazz);
	}

	/**
	 * the name of the view model
	 * 
	 * @return the name of the view model
	 */
	public String getName() {
		return name;
	}

	/**
	 * set the name of the view model object
	 * 
	 * @param name the name of the view model object
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * get the spring mvc model
	 * 
	 * @return the spring mvc model
	 */
	public Model getModel() {
		return model;
	}

	/**
	 * set the spring mvc model
	 * 
	 * @param model the model to be set
	 */
	public void setModel(Model model) {
		this.model = model;
		if (model != null && this.name != null && this.name.isEmpty() == false) {
			model.addAttribute(this.name, this);
		}
	}

	/**
	 * the id of the view model.
	 * 
	 * @return the id of the view model
	 */
	public String getId() {
		return id;
	}

	/**
	 * set the id of the viewmodel
	 * 
	 * @param id the id of the viewmodel
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * a description for the view model
	 * 
	 * @return the description the view model object
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * set a description for the view model
	 * 
	 * @param description the description to be set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * get the metadata for this view model
	 * 
	 * @return the metadata for this view model
	 */
	public Hashtable<String, Object> getMetadata() {
		return metadata;
	}

	/**
	 * add key/value pair attribute to the metadata
	 * 
	 * @param name  the key to be set
	 * @param value the value to be set
	 */
	public void addAttribute(String name, Object value) {
		if (model != null) {
			model.addAttribute(name, value);
		}
	}

	/**
	 * the view for this view model object
	 * 
	 * @return the view for this view model object
	 */
	public TView getView() {
		return view;
	}

	/**
	 * set the view for this view model object
	 * 
	 * @param view the view for this view model object
	 */
	public void setView(TView view) {
		this.view = view;
	}

	/**
	 * get the html path for this view model. It comes from the view that was set
	 * 
	 * @return the html path for the view model from the view
	 */
	public String getViewPath() {

		return view.getHtml();
	}

	/**
	 * Create a view instance base of the class type of the view
	 * @param <V> the view type
	 * @param clazz the target class type of the view
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
