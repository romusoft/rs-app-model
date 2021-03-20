-----------------------------------------------------
ORIGIN OF CONTENT:
-----------------------------------------------------

https://stackoverflow.com/a/13103364
	
To simplify pages or views development, we have decided to adopt jsp tags as a way to make using a common layout easier.
We have create a default layout tag that uses two rows and three columns. 
We plan on adding more layout tags later.

To benefit from that layout component, just use the tag as regular html element in the body of your html document:

<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="../includes/commonHeadTags.jsp"%>
<title>tab stud display</title>
</head>
<body>
	<!-- the actual content of the page in the layout-->
	<wp:wp-layout-default>
		
		<div> Hello world of jsp tags</div>
	</wp:wp-layout-default>
</body>
</html>

