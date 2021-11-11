package net.romusoft.rsapp.mvvm;

import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.HashMap;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.ui.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import net.romusoft.rsapp.controller.ApiController;
import net.romusoft.rsapp.enums.ViewType;

/**
 * View to use in a view Model
 * 
 * @author Emmanuel Romulus
 *
 */
public abstract class RsViewBase implements IRsView {

	@JsonIgnore
	private Model model = null;
	//
	// file paths
	@JsonIgnore
	private String html = "";
	@JsonIgnore
	private String css = "";
	@JsonIgnore
	private String js = "";

	@JsonIgnore
	private String commondHeadTags;

	private String htmlTitle = "";
	private String pageTitle = "";
	private String pageTitleDescription = "";

	//
	// mvc end points
	private String mvcBaseUrl = "";
	/*
	 * api endpoints
	 */
	private String apiBaseUrl = "";
	private RsViewModelUri uriRead = new RsViewModelUri("", ApiController.URI_READ_ATTRIBUTE_NAME);
	private RsViewModelUri uriCreate = new RsViewModelUri("", ApiController.URI_CREATE_ATTRIBUTE_NAME);
	private RsViewModelUri uriUpdate = new RsViewModelUri("", ApiController.URI_UPDATE_ATTRIBUTE_NAME);
	private RsViewModelUri uriDelete = new RsViewModelUri("", ApiController.URI_DELETE_ATTRIBUTE_NAME);
	private RsViewModelUri uriDetail = new RsViewModelUri("", ApiController.URI_DETAIL_ATTRIBUTE_NAME);

	private HashMap<String, RsViewModelUri> uris = new HashMap<String, RsViewModelUri>(); // and any uri to the view

	/**
	 * default constructor
	 */
	public RsViewBase() {

		this.init();
		updateModelAttributes();

	}

	/**
	 * constructor with the spring mvc model where model attribute can be stored
	 * 
	 * @param model spring mvc model where model attribute can be stored
	 */
	public RsViewBase(Model model) {

		this.init();
		updateModelAttributes();
	}

	/************************************************************/
	/**
	 * 
	 */
	@Override
	public Model getModel() {
		this.init();
		return model;
	}

	/**
	 * 
	 */
	@Override
	public void setModel(Model model) {
		this.model = model;
		this.init();
		updateModelAttributes();

	}

	/**
	 * 
	 */
	public abstract void init();

	/**
	 * 
	 */
	@Override
	public abstract String getHtml();

	/**
	 * 
	 */
	@Override
	public abstract String getCss();

	/**
	 * 
	 */
	@Override
	public abstract String getJs();

	/**
	 * 
	 */
	@Override
	public abstract String getHtmlTitle();

	/**
	 * 
	 */
	@Override
	public abstract String getPageTitle();

	/**
	 * 
	 */
	@Override
	public abstract String getPageTitleDescription();

	/**
	 * 
	 */
	@Override
	public String getMvcBaseUrl() {
		return "";
	}

	/**
	 * 
	 */
	@Override
	public abstract String getApiBaseUrl();

	/**
	 * type of view to determine meta tags
	 * 
	 * @return the view type for the view
	 */
	protected abstract ViewType getViewType();

	/**
	 * 
	 */
	@Override
	public RsViewModelUri getUriRead() {
		return this.uriRead;
	}

	/**
	 * 
	 */
	@Override
	public RsViewModelUri getUriCreate() {
		return this.uriCreate;
	}

	/**
	 * 
	 */
	@Override
	public RsViewModelUri getUriUpdate() {
		return this.uriUpdate;
	}

	/**
	 * 
	 */
	@Override
	public RsViewModelUri getUriDelete() {
		return this.uriDelete;
	}

	/**
	 * 
	 */
	@Override
	public RsViewModelUri getUriDetail() {
		return this.uriDetail;
	}

	@Override
	public HashMap<String, RsViewModelUri> getUris() {
		return uris;
	}

	/*************************************/
	/**
	 * 
	 */
	@Override
	public void setHtml(String html) {
		this.html = html;
		if (model == null)
			return;
		model.addAttribute(HTML_PATH_ATTRIBUTE_NAME, this.html);

	}

	/**
	 * 
	 */
	@Override
	public void setCss(String css) {
		this.css = css;
		if (model == null)
			return;

		model.addAttribute(CSS_PATH_ATTRIBUTE_NAME, this.css);
	}

	/**
	 * 
	 */
	@Override
	public void setJs(String js) {
		this.js = js;
		if (model == null)
			return;

		model.addAttribute(JS_PATH_ATTRIBUTE_NAME, this.js);
	}

	/**
	 * 
	 */
	@Override
	public void setHtmlTitle(String htmlTitle) {
		this.htmlTitle = htmlTitle;
		if (model == null)
			return;
		model.addAttribute(HTML_TITLE_ATTRIBUTE_NAME, this.htmlTitle);

	}

	/**
	 * 
	 */
	@Override
	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
		if (model == null)
			return;
		model.addAttribute(PAGE_TITLE_ATTRIBUTE_NAME, this.pageTitle);

	}

	/**
	 * 
	 */
	@Override
	public void setPageTitleDescription(String pageTitleDescription) {
		this.pageTitleDescription = pageTitleDescription;
		if (model == null)
			return;
		model.addAttribute(PAGE_TITLE_DESCRIPTION_ATTRIBUTE_NAME, this.pageTitleDescription);

	}

	/**
	 * 
	 */
	@Override
	public void setMvcBaseUrl(String mvcBaseUrl) {
		this.mvcBaseUrl = mvcBaseUrl;
		if (model == null)
			return;

		model.addAttribute(MVC_BASE_URL_ATTRIBUTE_NAME, this.mvcBaseUrl);
	}

	/**
	 * 
	 */
	@Override
	public void setApiBaseUrl(String apiBaseUrl) {
		this.apiBaseUrl = apiBaseUrl;
		if (model == null)
			return;

		model.addAttribute(API_BASE_URL_ATTRIBUTE_NAME, this.apiBaseUrl);

	}

	@Override
	public void setUriRead(RsViewModelUri uriRead) {
		this.uriRead = uriRead;
	}

	@Override
	public void setUriCreate(RsViewModelUri uriCreate) {
		this.uriCreate = uriCreate;
	}

	@Override
	public void setUriUpdate(RsViewModelUri uriUpdate) {
		this.uriUpdate = uriUpdate;
	}

	@Override
	public void setUriDelete(RsViewModelUri uriDelete) {
		this.uriDelete = uriDelete;
	}

	@Override
	public void setUriDetail(RsViewModelUri uriDetail) {
		this.uriDetail = uriDetail;
	}

	/**
	 * set the uri hashmap
	 * 
	 * @param uris an instance of hashmap for the uris
	 */
	public void setUris(HashMap<String, RsViewModelUri> uris) {
		this.uris = uris;
	}

	/**
	 * add a uri to the view
	 * 
	 * @param name the name of the uri
	 * @param uri  the uri object
	 * @return return the uri for chaining
	 */
	@Override
	public RsViewModelUri addUri(String name, RsViewModelUri uri) {
		return this.uris.put(name, uri);
	}

	/**
	 * retrieve an existing uri from the view
	 * 
	 * @param name the name of the uri
	 * @return the existing instance of null
	 */
	@Override
	public RsViewModelUri findUri(String name) {
		if (this.uris.containsKey(name) == false)
			return null;
		return this.uris.get(name);
	}

	/**
	 * initialize the view with its basic components such as html, css, javaScript
	 * path
	 * 
	 */
	@Override
	public void updateModelAttributes() {

		this.htmlTitle = getHtmlTitle() == null ? "" : getHtmlTitle();
		this.pageTitle = getPageTitle() == null ? "" : getPageTitle();
		this.pageTitleDescription = getPageTitleDescription() == null ? "" : getPageTitleDescription();
		/*
		 * api endpoints
		 */
		this.apiBaseUrl = getApiBaseUrl() == null ? "" : getApiBaseUrl();
		this.mvcBaseUrl = getMvcBaseUrl() == null ? "" : getMvcBaseUrl();

		this.uriRead.setModel(model);
		this.uriCreate.setModel(model);
		this.uriUpdate.setModel(model);
		this.uriDelete.setModel(model);
		this.uriDetail.setModel(model);

		//
		// file paths
		this.html = getHtml() == null ? "" : getHtml();
		this.css = getCss() == null ? "" : getCss();
		this.js = getJs() == null ? "" : getJs();

		this.configureViewType(this.getViewType());
		if (model == null)
			return;
		model.addAttribute(HTML_TITLE_ATTRIBUTE_NAME, this.htmlTitle);
		model.addAttribute(PAGE_TITLE_ATTRIBUTE_NAME, this.pageTitle);
		model.addAttribute(PAGE_TITLE_DESCRIPTION_ATTRIBUTE_NAME, this.pageTitleDescription);

		model.addAttribute(API_BASE_URL_ATTRIBUTE_NAME, this.apiBaseUrl);

		model.addAttribute(ApiController.URI_CREATE_ATTRIBUTE_NAME, this.uriCreate.getUrl());
		model.addAttribute(ApiController.URI_READ_ATTRIBUTE_NAME, this.uriRead.getUrl());
		model.addAttribute(ApiController.URI_UPDATE_ATTRIBUTE_NAME, this.uriUpdate.getUrl());
		model.addAttribute(ApiController.URI_DELETE_ATTRIBUTE_NAME, this.uriDelete.getUrl());
		model.addAttribute(ApiController.URI_DETAIL_ATTRIBUTE_NAME, this.uriDetail.getUrl());
		//
		// where to navigation after an action is complete: update or cancel
		model.addAttribute(MVC_BASE_URL_ATTRIBUTE_NAME, this.mvcBaseUrl);

		model.addAttribute(HTML_PATH_ATTRIBUTE_NAME, this.html);
		model.addAttribute(CSS_PATH_ATTRIBUTE_NAME, this.css);
		model.addAttribute(JS_PATH_ATTRIBUTE_NAME, this.js);

	}

	/**
	 * 
	 * @param viewType
	 */
	private void configureViewType(ViewType viewType) {
		switch (viewType) {
		case JSP:
			configureJsp();
			break;
		case THYMELEAF:
			configureThymeleaf();
			break;
		case DEFAULT:
			configureHtml();
			break;
		}

	}

	/**
	 * configure the view for jsp
	 */
	private void configureJsp() {

		byte[] data;
		try {
			String filepath = "commonHeadTags.jsp";
			Resource resource = new ClassPathResource(filepath);
			if (resource.exists() == false)
				return;

			InputStream resourceInputStream = resource.getInputStream();
			data = IOUtils.toByteArray(resourceInputStream);
			StringBuilder builder = new StringBuilder(new String(data));
			//
			// add the view js tag
			if (this.getJs() != null && this.getJs().isBlank() == false) {
				builder.append(MessageFormat.format("<script src='{0}'>", this.getJs()));
				builder.append("</script>");
			}
			//
			// add css
			if (this.getCss() != null && this.getCss().isBlank() == false) {
				builder.append(MessageFormat.format("<link rel=='{0}' href='{1}'>", "stylesheet", this.getCss()));
			}

			this.commondHeadTags = builder.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * configure the view for html
	 */
	private void configureHtml() {

		// TODO Auto-generated method stub

	}

	/**
	 * configure the view for thymeleaf
	 */
	private void configureThymeleaf() {
		// TODO Auto-generated method stub

	}

}
