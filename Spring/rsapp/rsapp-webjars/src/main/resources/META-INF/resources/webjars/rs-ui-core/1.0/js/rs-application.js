var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");
$(document).ajaxSend(function(e, xhr, options) {
	xhr.setRequestHeader(header, token);
});
/*
 * get the context path of the application
 */

var contextPath;
var applicationBaseURL;
var serviceBaseURL;

var uriCreate;
var uriRead;
var uriUpdate;
var uriDelete;
var uriDetail;

var startDate;
var endDate;

var app;

$(document).ready(function() {
	initializeComponents();
});

/**
 * initialize the components
 * 
 * @returns
 */
function initializeComponents() {
	contextPath = window.location.pathname.substring(0,
			window.location.pathname.indexOf("/", 2));
	applicationBaseURL = window.location.origin + contextPath;

	serviceBaseURL = applicationBaseURL
			+ document.getElementById("apiBaseUrl").value;

	uriCreate = serviceBaseURL + "/"
			+ document.getElementById("uriCreate").value;

	uriRead = serviceBaseURL + "/" + document.getElementById("uriRead").value;

	uriUpdate = serviceBaseURL + "/"
			+ document.getElementById("uriUpdate").value;

	uriDelete = serviceBaseURL + "/"
			+ document.getElementById("uriDelete").value;

	uriDetail = serviceBaseURL + "/"
			+ document.getElementById("uriDetail").value;

	startDate = serviceBaseURL + "/"
			+ document.getElementById("startDate").value;

	endDate = serviceBaseURL + "/" + document.getElementById("endDate").value;

	/*
	 * build a app context for the global variables
	 */

	/**
	 * externally exposed object
	 */
	app = {

		/**
		 * api end
		 */
		api : {

			serviceBaseURL : serviceBaseURL,
			uriCreate : uriCreate,
			uriRead : uriRead,
			uriUpdate : uriUpdate,
			uriDelete : uriDelete,
			uriDelete : uriDetail

		},

		/**
		 * kendo utilities
		 */
		kendo : {
			tools : {

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
									"class" : "rs-column-menu"
								},
								attributes : {
									"class" : "rs-column-menu"
								}
							});
				},

				/**
				 * kendo alert.
				 */
				alert : function(title, message, action) {
					var kendoDialog = $("#alertDialog").data("kendoDialog");
					if (kendoDialog == undefined) {

						$("#alertDialog").kendoDialog({
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
						return;
					}
					kendoDialog.open();
				}
			}
		// end of tools
		}
	// end of kendo

	}; // end of app
}
