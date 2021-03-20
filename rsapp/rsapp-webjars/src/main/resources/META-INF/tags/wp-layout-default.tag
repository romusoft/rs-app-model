<%@tag description="Simple Wrapper Tag" pageEncoding="UTF-8"%>
<%@ taglib prefix="wp" tagdir="/META-INF/tags"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Default layout -->
<wp:wp-layout-navigation-topleft-bootstrap>
	<jsp:include page="/META-INF/resources/views/commonBodyElements.jsp" />
	<jsp:doBody />
</wp:wp-layout-navigation-topleft-bootstrap>


