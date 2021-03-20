<div class="selection-container">
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	
	<div class="selection-activeday">
		<span style="font-weight: bold">Active Day:</span> <input
			id="activeday" value="${customerSelection.activeDay}"
			onchange="onActiveDayValueChanged(this.value)" />
	</div>
	<div class="selection-go">
		<button id="gobutton" onclick="onGoClick()">GO</button>
	</div>

	<!-- display data for the selected customer -->

	<script src="app-common/selection-input/date-selection.js">
		
	</script>
</div>




