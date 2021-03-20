$(document).ready(function() {
	initializeComponents();
});

/**
 * initialize the components
 * 
 * @returns
 */
var rskendo;
function initializeComponents() {
	rskendo = {
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
}// end of kendo
