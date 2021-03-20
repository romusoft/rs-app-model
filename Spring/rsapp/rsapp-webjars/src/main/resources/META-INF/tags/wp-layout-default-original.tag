<%@tag description="Simple Wrapper Tag" pageEncoding="UTF-8"%>
<%@ taglib prefix="wp" tagdir="/META-INF/tags"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:choose>
	<c:when
		test="${navigationPreferenceViewModel.navigationOption == 'CLASSIC' }">
		<wp:wp-layout-navigation-topbottom>
			<jsp:doBody />
		</wp:wp-layout-navigation-topbottom>
	</c:when>

	<c:when
		test="${navigationPreferenceViewModel.navigationOption == 'COMPACT' }">
		<wp:wp-layout-navigation-top>
			<jsp:doBody />
		</wp:wp-layout-navigation-top>
	</c:when>

	<c:when
		test="${navigationPreferenceViewModel.navigationOption == 'DEFAULT' }">
		<wp:wp-layout-navigation-topleft>
			<jsp:doBody />
		</wp:wp-layout-navigation-topleft>
	</c:when>

	<c:otherwise>
		<wp:wp-layout-navigation-topleft>
			<jsp:doBody />
		</wp:wp-layout-navigation-topleft>
	</c:otherwise>

</c:choose>
