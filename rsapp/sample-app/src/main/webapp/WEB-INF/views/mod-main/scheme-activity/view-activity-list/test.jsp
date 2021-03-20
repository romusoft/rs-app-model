<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<!-- common JSTL -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c_rt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="rs" uri="http://romusoft.net/rsapp/tags"%>

<link rel="stylesheet" href="${viewModel.view.css }">
<script src="${viewModel.view.js }"></script>
<title>${viewModel.view.htmlTitle }</title>

<link rel="stylesheet"
	href="webjars/rs-ui-core/1.0/styles/rs-all.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script src="webjars/rs-ui-core/1.0/js/rs-navigation.js"></script>

</head>
<body>
	<h1>${viewModel.view.pageTitle }</h1>
</body>
</html>