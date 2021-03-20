
<!-- common meta tags -->
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />
<meta name="commonData" data-apiBaseUrl="${apiBaseUrl }"
	data-uriCreate="${uriCreate }" data-uriRead="${uriRead }"
	data-uriUpdate="${uriUpdate }" data-uriDelete="${uriDelete }"
	data-uriDetail="${uriDetail }" data-startDate="${startDate }"
	data-endDate="${endDate }">

<!-- common JSTL -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c_rt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!-- wp tags -->
<%@ taglib prefix="wp" tagdir="/META-INF/tags"%>



<c:set var="url">${pageContext.request.requestURL}</c:set>
<c:set var="uri">${pageContext.request.requestURI}</c:set>
<c:set var="username">${pageContext.request.remoteUser}</c:set>
<c:set var="contextpath">${pageContext.request.contextPath}</c:set>
<c:url var="logoutUrl" value="/logout" />

<base
	href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${contextpath}/" />


<!-- common script tags -->
<script src="${cdnViewModel.jqueryJs }" type="text/javascript"></script>
<script src="${cdnViewModel.bootstrapJs }" type="text/javascript"></script>
<script src="${cdnViewModel.datatablesJs }" type="text/javascript"></script>
<script src="${cdnViewModel.kendouiJs }" type="text/javascript"></script>

<script src='https://kit.fontawesome.com/a076d05399.js'></script>
<!-- ui core needs the latest changes. -->
<script src="${cdnViewModel.wpuicoreJs }"></script>


<script src="app-common/commonHeadTags.js"></script>
<script src="app-common/site.js"></script>


<!-- common stylesheets -->
<link rel="stylesheet" href="${cdnViewModel.bootstrapCss }">
<link rel="stylesheet" href="${cdnViewModel.datatablesCss }">
<link rel="stylesheet" href="${cdnViewModel.themeCss }">


<!-- ui core needs the latest changes. -->
<link rel="stylesheet" href="${cdnViewModel.wpuicoreCss }">


<link rel="stylesheet" href="app-common/commonHeadTags.css">
<link rel="stylesheet" href="app-common/site.css">

<!-- favicons -->