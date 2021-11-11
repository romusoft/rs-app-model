var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");
$(document).ajaxSend(function(e, xhr, options) {

	//
	// let the check user authentication check for the cors header token
	if (options.url.includes("check-user-authentication") == false) {
		mainApp.checkUserAuthentication();
	}
	xhr.setRequestHeader(header, token);

});
/**
 * when ajax status is 401 (unauthorized) redirect to the login page Session has
 * expired
 * 
 * @param event
 * @param jqxhr
 * @param settings
 * @param exception
 * @returns
 */
// $(document).ajaxError(function(event, jqxhr, settings, exception) {
// if (jqxhr.status === 401 || jqxhr.status == 403) {
// alert('Session has expired. Click ok to be redirected to the Login screen');
//		
// var applicationBaseUrl = mainApp.getApplicationBaseUrl();
// window.top.location.replace(applicationBaseUrl);
// }
// console.log(jqxhr.status);
// });
$(document).ajaxError(
		function(event, jqxhr, settings, exception) {

			if (jqxhr.status == 200) {
				return;
			}

			if (jqxhr.status == 417) {
				// console.log(jqxhr);
				return;
			}

			if (jqxhr.status == 0) { // firefox sometimes sends a code of 0.
				// It is not an error ignore it.
				return;
			}

			//
			// navigate to the custom error page
			var errorUrl = "application-api-error?status=" + jqxhr.status
					+ "&url=" + settings.url + "&data=" + settings.data;
			window.location.replace(errorUrl);

		});

/**
 * externally exposed object
 */
var kendoSupport = {
	tools : {

		/**
		 * remove column menu from hour endings
		 */
		removeColumnMenu : function() {
			$(".wp-no-column-menu").find(".k-header-column-menu").remove();
		},

		/**
		 * add menuColumn so use can select columns to display
		 * 
		 * @param gridColumns
		 */
		addMenuColumn : function(gridColumns) {
			gridColumns
					.push({
						field : "columnMenu",
						headerTemplate : "<span style='margin:0;color:transparent'>Menu<span>",
						menu : false,
						width : 42,
						resizable : false,
						headerAttributes : {
							"class" : "wp-column-menu"
						},
						attributes : {
							"class" : "wp-column-menu"
						}
					});
		},

		/*
		 * kendo alert.
		 */
		alert : function(title, message, action) {
			var element = $("<div></div>").kendoDialog({
				width : "450px",
				title : title,
				closable : true,
				modal : true,
				content : "<p>" + message + "<p>",
				actions : [ {
					text : 'Ok',
					action : action
				} ]
			});
			kendoDialog = element.data("kendoDialog");
			kendoDialog.open();
		},

		/**
		 * filter all columns in a grid based on a text box input
		 */
		initializeGridTextBoxFilter : function(gridId, textboxId) {
			//
			// register the input event on the text box to apply the filter to
			// the
			// grid
			//
			var textBoxElement = $(document.getElementById(textboxId));
			textBoxElement
					.on(
							'input',
							function(e) {
								var grid = $(document.getElementById(gridId))
										.data("kendoGrid");
								var columns = grid.columns;

								var filter = {
									logic : 'or',
									filters : []
								};
								columns
										.forEach(function(x) {
											if (x.field) {
												var type = grid.dataSource.options.schema.model.fields[x.field].type;
												if (type == 'string'
														|| type == undefined) {
													filter.filters.push({
														field : x.field,
														operator : 'contains',
														value : e.target.value
													})
												} else if (type == 'number') {
													if (isNumeric(e.target.value)) {
														filter.filters
																.push({
																	field : x.field,
																	operator : 'eq',
																	value : e.target.value
																});
													}

												} else if (type == 'date') {
													var data = grid.dataSource
															.data();
													for (var i = 0; i < data.length; i++) {
														var dateStr = kendo
																.format(
																		x.format,
																		data[i][x.field]);
														//
														// check if text
														// contains
														// the
														// target value
														if (dateStr
																.includes(e.target.value)) {
															filter.filters
																	.push({
																		field : x.field,
																		operator : 'eq',
																		value : data[i][x.field]
																	})
														}
													}
												} else if (type == 'boolean'
														&& getBoolean(e.target.value) !== null) {
													var bool = getBoolean(e.target.value);
													filter.filters.push({
														field : x.field,
														operator : 'eq',
														value : bool
													});
												}
											}
										});
								grid.dataSource.filter(filter);
							});

		}

	}
};

/**
 * common application functionalities
 */
var mainApp = {
	intervalId : 0,
	api : {},
	kendo : {},
	regionViewModel : {},
	/**
	 * check whether the user is still authenticated on every request
	 * 
	 * @returns
	 */
	checkUserAuthentication : function() {
		var url = mainApp.getApplicationBaseUrl();
		url = url + "api/home/check-user-authentication";
		$.ajax({
			type : "GET",
			contentType : "application/json; charset=utf-8",
			url : url,
			dataType : "json",
			cache : false,
			success : function(result) {
				// do nothing.
			},
			error : function(result) {
				document.location.reload();
			}
		});
	},

	/**
	 * 
	 * @returns
	 */
	getApplicationBaseUrl : function() {
		var contextPath = window.location.pathname.substring(0,
				window.location.pathname.indexOf("/", 2));
		//
		// ensure the context path does not end with more than one slash
		contextPath = contextPath + "/";
		contextPath = contextPath.replace("//", "/");
		return window.location.origin + contextPath;
	},

	/**
	 * initialize the components
	 * 
	 * @returns
	 */
	initializeComponents : function() {
		var applicationBaseURL = mainApp.getApplicationBaseUrl();
		/*
		 * set time zone
		 */
		var tz = jstz.determine(); // Determines the time zone of the browser
		// client
		var timezonename = tz.name(); // 'Asia/Kolhata' for Indian Time.
		document.querySelector('meta[name="commonData"]').setAttribute(
				"data-timezonename", timezonename);
		var common = document.querySelector("meta[name='commonData']");
		var serviceBaseURL = applicationBaseURL + common.dataset.apibaseurl;
		//
		// CREATE
		var uriCreate = common.dataset.uricreate == "" ? "" : serviceBaseURL
				+ common.dataset.uricreate;
		//
		// READ
		var uriRead = common.dataset.uriread == "" ? "" : serviceBaseURL
				+ common.dataset.uriread;
		//
		// UPDATE
		var uriUpdate = common.dataset.uriupdate == "" ? "" : serviceBaseURL
				+ common.dataset.uriupdate;
		//
		// DELETE
		var uriDelete = common.dataset.uridelete == "" ? "" : serviceBaseURL
				+ common.dataset.uridelete;
		//
		// DETAIL
		var uriDetail = common.dataset.uridetail == "" ? "" : serviceBaseURL
				+ common.dataset.uridetail;
		/*
		 * build a app context for the global variables
		 */
		/**
		 * externally exposed object
		 */
		mainApp.api = {
			serviceBaseURL : serviceBaseURL,
			uriCreate : uriCreate,
			uriRead : uriRead,
			uriUpdate : uriUpdate,
			uriDelete : uriDelete,
			uriDetail : uriDetail,
			timezoneName : timezonename

		}; // end of app

		mainApp.kendo = kendoSupport;

	},

	/**
	 * DATASOURCE FOR CURRENT VIEWMODEL
	 * 
	 * @Param onComplete -
	 *            the function(jqXHR, textStatus) when the request is complete-
	 *            jqXHR contains data and response headers
	 * @Param onError -
	 *            function(e) to call when there is an error. e contains
	 *            e.xhr.responseJSON and e.errorThrown
	 * @returns viewModelDataSource
	 */
	getViewModelDataSource : function(onComplete, onError) {
		var dataSource = new kendo.data.DataSource(
				{
					transport : {
						read : {
							type : "get",
							url : mainApp.api.uriRead,
							complete : function(jqXHR, textStatus) {
								if (onComplete != undefined) {
									onComplete(jqXHR, textStatus);
								}
							}
						},

						parameterMap : function(options, operation) {
							return {
								models : kendo.stringify(options.models)
							};
						}
					},

					schema : {
						model : {
							id : "id",
							fields : {
								id : {
									type : "int"
								}
							}
						}
					},
					error : function(e) {
						if (onError != undefined) {
							onError(e);
							return;
						}
						/*
						 * The e event argument will represent the following
						 * object: { errorThrown: "Unauthorized", sender: {...
						 * the Kendo UI DataSource instance ...} status: "error"
						 * xhr: {... the Ajax request object ...} }
						 * 
						 */

						//
						// display the error from the response
						var EXPECTATION_FAILED = 417;
						var errorText = (e.xhr.responseJSON) ? e.xhr.responseJSON.message
								: e.errorThrown;
						if (e.xhr.status == EXPECTATION_FAILED) {
							kendo.alert(errorText);
						}
					}
				});
		return dataSource;
	},
	/**
	 * LOAD VIEWMODEL DATA USING THE READ URI
	 * 
	 * @Param viewModel -
	 *            the viewModel to add data to. The data will be located in
	 *            viewModel.viewModel. Additionally, the viewModel will contain
	 *            the following for convenience purpose: metadata, actions,
	 *            uris,
	 * 
	 * @Param onDataReady -
	 *            function(viewModel) make the modified viewModel available to
	 *            function parameter
	 * @Param onComplete -
	 *            the function(jqXHR, textStatus) when the request is complete-
	 *            jqXHR contains data and response headers
	 * @Param onError -
	 *            function(e) to call when there is an error. e contains
	 *            e.xhr.responseJSON and e.errorThrown
	 * @returns viewModelDataSource
	 */
	loadViewModelData : function(viewModel, onDataReady, onComplete, onError) {

		var dataSource = mainApp.getViewModelDataSource(onComplete, onError);
		//
		// load data for the viewmodel
		// read fires everytime as opposed to fetch that fires once
		dataSource.read().then(function() {

			var view = dataSource.view();
			var data = view[0];
			if (viewModel != undefined && viewModel.set != null) {
				viewModel.set("viewModel", data);
				viewModel.set("metadata", data.metadata);
				viewModel.set("actions", data.actions);
				viewModel.set("uris", data.view.uris);
			}

			if (onDataReady != undefined) {
				onDataReady(viewModel);
			}
		});
	},
	/**
	 * @param name
	 * @param url
	 * @returns
	 */
	getParameterByName : function(name, url) {
		if (!url)
			url = window.location.href;
		name = name.replace(/[\[\]]/g, '\\$&');
		var regex = new RegExp('[?&]' + name + '(=([^&#]*)|&|#|$)'), results = regex
				.exec(url);
		if (!results)
			return null;
		if (!results[2])
			return '';
		return decodeURIComponent(results[2].replace(/\+/g, ' '));
	},

	/**
	 * Ajax services
	 */
	dataServices : {

		/**
		 * do a post ajax call the target uri
		 */
		postData : function(data, uri, successCallback, errorCallback) {
			/*
			 * prepare the ajax call for add
			 */
			var url = uri;
			$.ajax({
				type : "POST",
				contentType : "application/json; charset=utf-8",
				url : url,
				data : kendo.stringify(data),
				dataType : "json",
				cache : false,
				success : function(data) {
					if (successCallback != undefined)
						successCallback(data);
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {

					if (errorCallback != undefined)
						errorCallback(XMLHttpRequest, textStatus, errorThrown);
				}
			});

		},

	},

};

/**
 * initialize the current page
 */
// mainApp.checkUserAuthentication();// check authentication
mainApp.initializeComponents(); // initialize common data

/**
 * select input on focus
 * 
 * @returns
 */
$(function() {
	commonHeadTags.events.selectInputOnFocus();
});

/**
 * common head tab utilities
 */
var commonHeadTags = {

	events : {

		selectInputOnFocus : function() {
			// alert("focusing");
			// wire focus of all numerictextbox widgets on the page
			$("input").on("focus", function() {
				// alert("focusing");
				var input = $(this);
				clearTimeout(input.data("selectTimeId")); // stop started time
				// out if
				// any

				var selectTimeId = setTimeout(function() {
					input.select();
					// To make this work on iOS, too, replace the above line
					// with the
					// following one. Discussed in
					// https://stackoverflow.com/q/3272089
					// input[0].setSelectionRange(0, 9999);
				});

				input.data("selectTimeId", selectTimeId);
			}).blur(function(e) {
				clearTimeout($(this).data("selectTimeId")); // stop started
				// timeout
			});
		}
	}

};// end of the kendo support object

/**
 * editor to use
 * 
 * @param container
 * @param options
 * @returns
 */
function editNumber(container, options) {
	$('<input data-bind="value:' + options.field + '"/>').appendTo(container)
			.kendoNumericTextBox({
				spinners : false
			});
}

/**
 * template for dirty fields
 * 
 * @param data
 * @param fieldName
 * @returns
 */
function dirtyField(data, fieldName) {
	if (data.dirty) {
		return "<span class='k-dirty'></span>";
	} else {
		return "";
	}
}

/**
 * copy a text to the clipboard
 * 
 * @param text
 * @returns
 */
function copyToClipBoard(text) {

	// create a temporary "dummy" object to hold the value we want to copy
	var dummy = document.createElement("textarea");
	document.body.appendChild(dummy);
	dummy.value = text;
	// select the text to copy
	dummy.select();
	dummy.setSelectionRange(0, 99999); /* For mobile devices */
	// copy the text to the clipboard.
	document.execCommand("copy");
	// remove the dummy element.
	document.body.removeChild(dummy);
}

$(document).ready(
		function() {
			var common = document.querySelector("meta[name='commonData']");
			var availableRegions = [];
			//
			// convert the availableRegions comma separated string to
			// label,value pairs
			var temp = common.dataset.availableregions.replace("[", "")
					.replace("]", "").split(",");
			temp.forEach(function(item, index) {
				availableRegions.push({
					value : index,
					label : item.trim()
				});
			});
			//
			// convert the selected regions comma separated string to a list of
			// strings
			var selectedRegions = [];
			temp = common.dataset.selectedregions.replace("[", "").replace("]",
					"").split(",");
			temp.forEach(function(item, index) {
				selectedRegions.push(item.trim());
			});

			var selectedRegionsText = common.dataset.selectedregionstext;
			//
			// REGIONS
			mainApp.regionViewModel = kendo.observable({
				availableRegions : availableRegions,
				selectedRegions : selectedRegions,
				selectedRegionsText : selectedRegionsText,
				dataBound : function(e) {

					var listBox = $("#mainAppAllRegionsListBox").data(
							"kendoListBox");
					var items = listBox.items();

					//
					// do the current region selection
					var i = 0;
					var j = 0;
					for (i = 0; i < this.selectedRegions.length; i++) {
						for (j = 0; j < items.length; j++) {

							var dataItem = listBox.dataItem(items[j]);
							if (this.selectedRegions[i] == dataItem.label) {
								listBox.select(items[j]);
							}
						}
					}

					//
					// automatically transfer to the selected list
					listBox._executeCommand("transferTo");
				},
				editRegionsFilter : function() {
					var dialog = $("#mainAppRegionsFilterDialog").data(
							"kendoWindow");
					dialog.setOptions({
						height : 385
					});

					dialog.center().open();

				},
				okClicked : function() {
					var listBox = $("#mainAppSelectedRegionsListBox").data(
							"kendoListBox");
					var data = listBox.dataItems();
					var uri = mainApp.getApplicationBaseUrl()
							+ "api/home/update-region-filter";

					mainApp.dataServices.postData(data, uri, function(data) {
						//
						// close the window and reload the current page
						var dialog = $("#mainAppRegionsFilterDialog").data(
								"kendoWindow");
						dialog.close();
						window.location.reload();
					});

				},
				cancelClicked : function() {
					var dialog = $("#mainAppRegionsFilterDialog").data(
							"kendoWindow");
					dialog.close();
				}
			});

			//
			// bind the filter container
			var container = $("#mainAppRegionsFilterContainer");
			kendo.bind(container, mainApp.regionViewModel);
			mainApp.regionViewModel.dataBound();
		});
