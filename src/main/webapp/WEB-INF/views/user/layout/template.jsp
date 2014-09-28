<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link rel="stylesheet"
	href="<c:url value="/resourcesUtils/css/user/index.css"/>"
	media="screen">
<link rel="stylesheet"
	href="//ajax.googleapis.com/ajax/libs/dojo/1.9.0/dojo/resources/dojo.css"
	media="screen">

<script src="//ajax.googleapis.com/ajax/libs/dojo/1.9.0/dojo/dojo.js"
	data-dojo-config="async: true"></script>
<script src="<c:url value="/resourcesUtils/js/user/search.js"/>"></script>
<title>Insert title here</title>
</head>
<body>
	<!-- Header -->
	<div id="headerwrapper">
		<div id="header">
		
			E-Moty
			<p>The World's Stock Of Facial Expression Image</p>
		</div>
	</div>
	<!-- End of Header -->

	<div id="content">
		<tiles:insertAttribute name="searchField" />

		<tiles:insertAttribute name="body" />



	</div>

	<!-- Footer -->
	<div id="footer">
		<span>E-Moty &copy; 2013 by DualBoot. All Rights Reserved</span>
	</div>
	<!-- End Of Footer -->

</body>
</html>