<%@tag description="Simple Wrapper Tag" pageEncoding="UTF-8"%>
<div class="wp-layout-main">
	<!-- the main content divided into 2 rows and 3 columns -->
	<div class="wp-main-content">

		<!-- side strip to toggle side navigation -->
		<div class="wp-sidestrip">
			<div class="arrow left"></div>
			<div class="caption">Navigation links</div>
		</div>
		<!-- side navigation -->
		<div class="wp-sidenavigation">
			<jsp:include page="/META-INF/resources/views/navigationSectionLeft.jsp" />
		</div>

		<!-- the actual content of the page -->
		<div class="wp-content">
			<jsp:doBody />

			<div class="modal-wave-loader">
				<div class="modal-circle"></div>
				<div class="modal-circle"></div>
				<div class="modal-circle"></div>
				<div class="modal-circle"></div>
				<div class="modal-circle"></div>
				<div class="modal-circle"></div>
				<div class="modal-circle"></div>
				<p class="wave">~</p>
				<p class="wave">~</p>
				<p class="wave">Loading</p>
			</div>
		</div>
		<!-- end of page content -->
	</div>
	<!-- end of main page -->
</div>
