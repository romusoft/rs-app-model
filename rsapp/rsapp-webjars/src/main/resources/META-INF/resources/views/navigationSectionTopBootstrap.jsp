<!-- This partial view is to be included in every page. the jstl tags will be result on the page
when the following files are include in the header of the page: -->

<div class="container-fluid tab-navigation  wp-main-header">
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<!-- tabs or menu categories -->
	<nav class="navbar navbar-expand-md navbar-dark rs-navbar">
		<a class="navbar-brand " href="#">Logo</a>

		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#collapsibleNavbar">
			<span class="navbar-toggler-icon"></span>
		</button>


		<div class="collapse navbar-collapse" id="collapsibleNavbar">

			<ul class="nav navbar-nav mr-auto"></ul>
			<form class="navbar-form navbar-right" action="/action_page.php">
				<div class="input-group">				
					<div class="input-group-prepend">
						<span class="input-group-text"><i class='fas fa-search'></i></span>
					</div>
					
					<input type="text" class="form-control" placeholder="Search"
						name="search">
				</div>
			</form>
			<ul class="nav navbar-nav navbar-right">

				<li class="nav-item"><a class="nav-link" href="#">Social</a></li>
				<li class="nav-item"><a class="nav-link" href="#">Services</a></li>
				<li class="nav-item"><a class="nav-link" href="#">Give</a></li>
				<li class="nav-item"><a class="nav-link" href="#">Sign in</a></li>
			</ul>



		</div>
	</nav>



</div>



