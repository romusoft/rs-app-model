<!-- common meta tags -->
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<!-- common JSTL -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c_rt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!-- wp tags -->
<%@ taglib prefix="wp" tagdir="/WEB-INF/tags"%>

<c:set var="url">${pageContext.request.requestURL}</c:set>
<c:set var="uri">${pageContext.request.requestURI}</c:set>
<c:set var="username">${pageContext.request.remoteUser}</c:set>
<c:set var="contextpath">${pageContext.request.contextPath}</c:set>
<c:url var="logoutUrl" value="/logout" />
<!-- base allows easy finding of resource uri from the app context path -->
<base
	href="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${contextpath}/" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}">

<meta name="commonData"
	data-baseURI="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${contextpath}/"
	data-mvcBaseUrl="${mvcBaseUrl }"
	data-mvcRedirectUrl="${mvcRedirectUrl }"
	data-apiBaseUrl="${apiBaseUrl }" data-uriCreate="${uriCreate }"
	data-uriRead="${uriRead }" data-uriUpdate="${uriUpdate }"
	data-uriDelete="${uriDelete }" data-uriDetail="${uriDetail }"
	data-username="${username }">


<!-- common script tags -->
<script src="${RsCDNViewModel.jqueryJs }" type="text/javascript"></script>
<script src="${RsCDNViewModel.bootstrapJs }" type="text/javascript"></script>
<script src="${RsCDNViewModel.datatablesJs }" type="text/javascript"></script>
<script src="${RsCDNViewModel.kendouiJs }" type="text/javascript"></script>
<script src="${RsCDNViewModel.wpuicoreJs }" type="text/javascript"></script>

<script src="lib/timezone/jstz.min.js" type="text/javascript"></script>
<script src="commonHeadTags" type="text/javascript"></script>

<!-- support for excel export -->
<script src="${RsCDNViewModel.cdnUrl }/kendo-ui/js/jszip.min.js"
	type="text/javascript"></script>


<!-- common stylesheets -->
<link rel="stylesheet" href="${RsCDNViewModel.bootstrapCss }">
<link rel="stylesheet" href="${RsCDNViewModel.datatablesCss }">
<link rel="stylesheet" href="${RsCDNViewModel.themeCss }">
<link rel="stylesheet" href="${RsCDNViewModel.wpuicoreCss }">

