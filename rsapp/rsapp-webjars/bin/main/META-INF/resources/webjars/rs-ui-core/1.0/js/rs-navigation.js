/**
 * capture all clicks on the window
 */
/*
 * This must be at the top level this is for ajax calls show spinner when ajax
 * calls start, remove spinner when ajax calls stop
 */
$(document).on({
	ajaxStart : function() {
		$(".wp-content").addClass("loading");
	},
	ajaxStop : function() {
		$(".wp-content").removeClass("loading");
	}
});

$(document)
		.ready(
				function() {
					/*
					 * capture the event and set a cookie to indicate that the
					 * user interacted with tabs it was not a refresh capture
					 * click for links and buttons more may need to be added
					 */
					$(".wp-content").removeClass("loading");
					$(
							".wp-sidenavigation ul li a, .outer-tab .nav-tabs li a, .inner-tab .nav-tabs li a")
							.click(
									function() {
										$(".wp-sidenavigation ul li")
												.removeClass("active");
										$(this).parent().addClass("active");

									});
					/*
					 * do loading splash for any element that wants to
					 * participate
					 */
					$(".wp-loading-splash").click(function() {
						$(".wp-content").toggleClass("loading");

					});

					/*
					 * two column navigation initialization for side strip
					 * toggle
					 */
					$(".wp-sidestrip").on(
							"click",
							function() {
								$(".arrow").toggleClass("left");
								$(".arrow").toggleClass("right");
								$(".wp-sidenavigation").toggleClass(
										"wp-toggle-display");
							});

				});
