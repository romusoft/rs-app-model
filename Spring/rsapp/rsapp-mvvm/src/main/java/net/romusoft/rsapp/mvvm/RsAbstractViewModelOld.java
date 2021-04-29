////package net.romusoft.rsapp.mvvm;
////
////import java.util.ArrayList;
////import java.util.Hashtable;
////import java.util.List;
////
////import org.springframework.ui.Model;
////
////import com.fasterxml.jackson.annotation.JsonIgnore;
////
////import net.romusoft.rsapp.mvvm.model.RsBasicApiContextPaths;
////import net.romusoft.rsapp.mvvm.model.RsViewModelItem;
////
/////**
//// * 
//// * @author Emmanuel Romulus
//// *
//// */
////public abstract class RsAbstractViewModelOld {
//
////	@JsonIgnore
////	private final String VIEWMODEL_ATTRIBUTE_NAME = "viewModel";
////	@JsonIgnore
////	private final String HTML_TITLE_ATTRIBUTE_NAME = "htmlTitle";
////	@JsonIgnore
////	private final String PAGE_TITLE_ATTRIBUTE_NAME = "pageTitle";
////	@JsonIgnore
////	private final String PAGE_TITLE_DESCRIPTION_ATTRIBUTE_NAME = "pageTitleDescription";
////	@JsonIgnore
////	private final String SCHEDULE_DATE_ATTRIBUTE_NAME = "scheduleDay";
////	@JsonIgnore
////	private Model model = null;
////
////	/*
////	 * the main header for the viewmodel similar to page title with the option of
////	 * setting more info such as cssclass, etc..
////	 */
////	private final RsViewModelItem headerInfo = new RsViewModelItem();
////
////	private String name = "";
////	private String htmlTitle;
////	private String pageTitle;
////	private String pageTitleDescription;
////	/*
////	 * api endpoints
////	 */
////	private String apiBaseUrl;
////	private String uriRead;
////	private String uriCreate;
////	private String uriUpdate;
////	private String uriDelete;
////	private String uriDetail;
////
////	//
////	//
////	private String id;
////	private String description;
////	//
////	// general purpose metadata
////	private final Hashtable<String, Object> metadata = new Hashtable<String, Object>();
////	//
////	// list of model items
////	private final List<RsViewModelItem> modelItems = new ArrayList<RsViewModelItem>();
////
////	/**
////	 * constructors
////	 */
////	public RsAbstractViewModelOld() {
////	}
////
////	/**
////	 * 
////	 * @param name
////	 */
////	public RsAbstractViewModelOld(String name) {
////		this.name = name;
////	}
////
////	/**
////	 * 
////	 * @param name
////	 * @param model
////	 */
////	public RsAbstractViewModelOld(String name, Model model) {
////		this.name = name;
////		this.model = model;
////		if (model != null) {
////			model.addAttribute(name, this);
////		}
////	}
////
////	/**
////	 * using this constructor will automatically set the view model attribute of the
////	 * model to 'viewModel' if a new name is required, use the default constructor
////	 * 
////	 * @param model
////	 */
////	public RsAbstractViewModelOld(Model model) {
////		this.model = model;
////		if (model != null) {
////			model.addAttribute(VIEWMODEL_ATTRIBUTE_NAME, this);
////		}
////	}
////
////	/**
////	 * 
////	 * @return
////	 */
////	public String getName() {
////		return name;
////	}
////
////	/**
////	 * 
////	 * @param name
////	 */
////	public void setName(String name) {
////		this.name = name;
////	}
////
////	/**
////	 * 
////	 * @return
////	 */
////	public Model getModel() {
////		return model;
////	}
////
////	/**
////	 * 
////	 * @param model
////	 */
////	public void setModel(Model model) {
////		this.model = model;
////		if (model != null && this.name != null && this.name.isEmpty() == false) {
////			model.addAttribute(this.name, this);
////		}
////
////	}
////	//
////	// end of constructors
////	//
////
////	/**
////	 * 
////	 * @return
////	 */
////	public String getHtmlTitle() {
////		return htmlTitle;
////	}
////
////	/**
////	 * 
////	 * @param htmlTitle
////	 */
////	public void setHtmlTitle(String htmlTitle) {
////		this.htmlTitle = htmlTitle;
////		this.addAttribute(HTML_TITLE_ATTRIBUTE_NAME, htmlTitle);
////	}
////
////	/**
////	 * 
////	 * @return
////	 */
////	public String getPageTitle() {
////		return pageTitle;
////	}
////
////	/**
////	 * 
////	 * @param pageTitle
////	 */
////	public void setPageTitle(String pageTitle) {
////		this.pageTitle = pageTitle;
////		this.addAttribute(PAGE_TITLE_ATTRIBUTE_NAME, pageTitle);
////	}
////
////	/**
////	 * 
////	 * @return
////	 */
////	public String getPageTitleDescription() {
////		return pageTitleDescription;
////	}
////
////	/**
////	 * 
////	 * @param pageTitleDescription
////	 */
////	public void setPageTitleDescription(String pageTitleDescription) {
////		this.pageTitleDescription = pageTitleDescription;
////		this.addAttribute(PAGE_TITLE_DESCRIPTION_ATTRIBUTE_NAME, pageTitleDescription);
////	}
////
////	/**
////	 * 
////	 * @return
////	 */
////	public String getApiBaseUrl() {
////		return apiBaseUrl;
////	}
////
////	/**
////	 * 
////	 * @param apiBaseUrl
////	 */
////	public void setApiBaseUrl(String apiBaseUrl) {
////		this.apiBaseUrl = apiBaseUrl;
////		this.addAttribute(RsBasicApiContextPaths.API_BASE_URL_ATTRIBUTE_NAME, apiBaseUrl);
////	}
////
////	/**
////	 * 
////	 * @return
////	 */
////	public String getUriRead() {
////		return uriRead;
////	}
////
////	/**
////	 * 
////	 * @param uriRead
////	 */
////	public void setUriRead(String uriRead) {
////		this.uriRead = uriRead;
////		this.addAttribute(RsBasicApiContextPaths.URI_READ_ATTRIBUTE_NAME, uriRead);
////
////	}
////
////	/**
////	 * 
////	 * @return
////	 */
////	public String getUriCreate() {
////		return uriCreate;
////	}
////
////	/**
////	 * 
////	 * @param uriCreate
////	 */
////	public void setUriCreate(String uriCreate) {
////		this.uriCreate = uriCreate;
////		this.addAttribute(RsBasicApiContextPaths.URI_CREATE_ATTRIBUTE_NAME, uriCreate);
////	}
////
////	/**
////	 * 
////	 * @return
////	 */
////	public String getUriUpdate() {
////		return uriUpdate;
////	}
////
////	/**
////	 * 
////	 * @param uriUpdate
////	 */
////	public void setUriUpdate(String uriUpdate) {
////		this.uriUpdate = uriUpdate;
////		this.addAttribute(RsBasicApiContextPaths.URI_UPDATE_ATTRIBUTE_NAME, uriUpdate);
////	}
////
////	/**
////	 * 
////	 * @return
////	 */
////	public String getUriDelete() {
////		return uriDelete;
////	}
////
////	/**
////	 * 
////	 * @param uriDelete
////	 */
////	public void setUriDelete(String uriDelete) {
////		this.uriDelete = uriDelete;
////		this.addAttribute(RsBasicApiContextPaths.URI_DELETE_ATTRIBUTE_NAME, uriDelete);
////
////	}
////
////	/**
////	 * 
////	 * @return
////	 */
////	public String getUriDetail() {
////		return uriDetail;
////	}
////
////	/**
////	 * 
////	 * @param uriDetail
////	 */
////	public void setUriDetail(String uriDetail) {
////		this.uriDetail = uriDetail;
////		this.addAttribute(RsBasicApiContextPaths.URI_DETAIL_ATTRIBUTE_NAME, uriDetail);
////
////	}
////
////	/**
////	 * 
////	 * @return
////	 */
////	public String getId() {
////		return id;
////	}
////
////	/**
////	 * 
////	 * @param id
////	 */
////	public void setId(String id) {
////		this.id = id;
////	}
////
////	/**
////	 * 
////	 * @return
////	 */
////	public String getDescription() {
////		return description;
////	}
////
////	/**
////	 * 
////	 * @param description
////	 */
////	public void setDescription(String description) {
////		this.description = description;
////	}
////
////	/**
////	 * 
////	 * @return
////	 */
////	public Hashtable<String, Object> getMetadata() {
////		return metadata;
////	}
////
////	/**
////	 * 
////	 * @return
////	 */
////	public List<RsViewModelItem> getModelItems() {
////		return modelItems;
////	}
////
////	/**
////	 * 
////	 * @return
////	 */
////	public RsViewModelItem getHeaderInfo() {
////		return headerInfo;
////	}
////
////	/**
////	 * 
////	 * @param name
////	 * @param value
////	 */
////	public void addAttribute(String name, Object value) {
////		if (model != null) {
////			model.addAttribute(name, value);
////		}
////	}
////
////	/**
////	 * 
////	 * @return
////	 */
////	public RsViewModelItem addModelItem() {
////
////		RsViewModelItem item = new RsViewModelItem();
////		this.modelItems.add(item);
////		return item;
////	}
////
////	/**
////	 * 
////	 * @param id
////	 * @return
////	 */
////	public RsViewModelItem addModelItem(String id) {
////
////		RsViewModelItem item = new RsViewModelItem(id);
////		this.modelItems.add(item);
////		return item;
////	}
////
////	/**
////	 * 
////	 * @param id
////	 * @param description
////	 * @return
////	 */
////	public RsViewModelItem addModelItem(String id, String description) {
////
////		RsViewModelItem item = new RsViewModelItem(id, description);
////		this.modelItems.add(item);
////		return item;
////	}
////
////	/**
////	 * 
////	 * @param id
////	 * @param description
////	 * @param modelItems
////	 * @return
////	 */
////	public RsViewModelItem addModelItem(String id, String description, List<RsViewModelItem> targetModelItems) {
////
////		RsViewModelItem item = new RsViewModelItem(id, description);
////		targetModelItems.add(item);
////		return item;
////	}
////
////	/**
////	 * 
////	 * @param id
////	 * @param description
////	 * @param modelItems
////	 * @return
////	 */
////	public RsViewModelItem addModelItem(String description, List<RsViewModelItem> targetModelItems) {
////
////		RsViewModelItem item = new RsViewModelItem(description);
////		targetModelItems.add(item);
////		return item;
////	}
////
////}
