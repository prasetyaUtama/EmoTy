<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="<c:url value="/resourcesUtils/css/admin/button.css"/>"
	media="screen">
<link rel="stylesheet"
	href="<c:url value="/resourcesUtils/css/admin/adm.css"/>"
	media="screen">
<link rel="stylesheet"
	href="<c:url value="/resourcesUtils/css/admin/dropdown_menu.css"/>"
	media="screen">
<link rel="stylesheet"
	href="<c:url value="/resourcesUtils/css/admin/dynamic_tab.css"/>"
	media="screen">
<link rel="stylesheet"
	href="//ajax.googleapis.com/ajax/libs/dojo/1.9.0/dojo/resources/dojo.css"
	media="screen">
<link rel="stylesheet"
	href="//ajax.googleapis.com/ajax/libs/dojo/1.9.0/dijit/themes/claro/claro.css"
	media="screen">

<link rel="stylesheet"
	href="//ajax.googleapis.com/ajax/libs/dojo/1.9.0/dojox/grid/resources/claroGrid.css"
	media="screen">

<script src="//ajax.googleapis.com/ajax/libs/dojo/1.9.0/dojo/dojo.js"
	data-dojo-config="async: true"></script>

<title>Insert title here</title>
</head>
<body class="claro">
	<div id="headerwrapper">
		<div id="header">
			<div id="logo" style="text-align: center;">
				<span
					style="font-size: 21px; font-weight: bold; font-family: cursive;">E-Moty</span>
			</div>
			<div id='cssmenu'>
				<ul>
					<li class='has-sub '><a href='#'><span>ADMIN</span></a>
						<ul>
							<li class='has-sub '><a href='#'><span>Settings</span></a></li>
							<li class='has-sub '><a href='#'><span>Log Out</span></a></li>
						</ul></li>
				</ul>
			</div>
		</div>
		<div class="clear"></div>
	</div>
	<div id="content">
		<tiles:insertAttribute name="menu" />
		<div id="contentkanan">sd</div>
	</div>



</body>
<script>
	require([ "dojo", "dojo/dom", "dojo/on", "dojo/parser", "dojo/ready",
			"dojox/layout/ContentPane", "dojo/_base/connect",
			"dojo/_base/event" ,"dojo/query", "dojo/NodeList-dom","dojo/NodeList-traverse"], function(dojo, dom, on, parser, ready,
			ContentPane, event,query) {

		var uri = null;
		var div = null;
		var myContentPane = new ContentPane({

			style : "height:600px"
		}, dom.byId("contentkanan"));

		var clickPage = function make_event_handler(uri) {

			return function(evt) {
				//style
				


				var loadUrl = {
					// The URL of the request
					url : uri,
					timeout : 2000,
					load : function(newContent) {
						myContentPane.setContent(newContent);
					},
					// The error handler
					error : function() {
						// Do nothing -- keep old content there
					}
				};

				//"categoryOfImage/create", "body"
				//"image/upload", "body"

				dojo.xhrGet(loadUrl);
				dojo.query("ul#tabs li").removeClass("active");
				//console.log(dojo.query(this.id).parent());
				dojo.query("#"+this.id).parent("li").addClass("active");

			};
		};
		ready(function(evt) {
			parser.parse();

			var categoryOfImage = dom.byId("categoryOfImageLink");
			var image = dom.byId("imageLink");
			on(categoryOfImage, "click", clickPage("categoryOfImage/create"));
			//on(categoryOfImage, "click", clickPage1);

			on(image, "click", clickPage("image/create"));
		});
	});
</script>
</html>