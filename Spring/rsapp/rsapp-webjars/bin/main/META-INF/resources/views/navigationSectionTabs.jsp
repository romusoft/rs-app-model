<!-- This partial view is to be included in every page. the jstl tags will be result on the page
when the following files are include in the header of the page: -->

<div class="container-fluid tab-navigation">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<!-- tabs or menu categories -->
	<div class="outer-tab">
		<ul class="nav nav-tabs">
			<c:forEach var="tab" items="${navigationModelView.authorizedTabList}">

				<li id="${tab.name}" class="dropdown  ${ tab.cssActive}">
					<div class="btn-group">
						<a class="btn btn-link" href="${tab.defaultUri}?tab=${tab.name}"
							title="${tab.description}">
							${tab.title} </a>
						<button class="btn btn-link dropdown-toggle"
							data-toggle="dropdown">
							<span class="caret"> </span>
						</button>
						<!-- tab items or sub menu -->

						<ul class="dropdown-menu">
							<c:forEach var="tabitem" items="${tab.itemList}">
								<li class="${tabitem.cssActive } ${tabitem.cssSeparator }"><a class="wp-loading-splash"
									href="${tabitem.uri}?tab=${tab.name}"
									title="${tabitem.description}">${tabitem.title}</a></li>
							</c:forEach>
						</ul>
					</div>
				</li>
			</c:forEach>
		</ul>
	</div>
	
	
	<div class="wp-help wp-panel">
<!-- 		<span class="glyphicon glyphicon-question-sign"></span>-->
		<div class="${navigationModelView.cssEnvironment}">${assemblyInfo.applicationEnvironment}</div>
		<ul id="help-menu" style="background-color: #f8f9f9;">
			<li>Help
				<ul>
					<li class="wp-help-item"><a href="#"
									onclick="window.open('release-notes', '_blank',
									'top=200,left=300,location=no,status=no,menubar=no,toolbar=no,scrollbars=yes,resizable=yes, width=640,height=auto')">Release Notes </a></li>
					<li>
						<dl class="wp-help-divider">
							<dt style="display: inline;">Environment</dt>
							<dd style="display: inline;">${assemblyInfo.applicationEnvironment}</dd>
							<dd>${assemblyInfo.applicationVersion}</dd>
						</dl> 
					</li>
				</ul>
			</li>
		</ul>
	</div>

	<!-- load inner tabs -->
	<div class="inner-tab">
		<ul class="nav nav-tabs">
			<c:forEach var="tabitem"
				items="${navigationModelView.activeAuthorizedTabItemList}">
				<li class="${tabitem.cssActive }  ${tabitem.cssSeparator }" title="${tabitem.description}"><a class="wp-loading-splash"
					href="${tabitem.uri}?tab=${navigationModelView.activeTabName}">${tabitem.title}</a></li>
			</c:forEach>
		</ul>
	</div>


</div>