<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp"%>
<link rel="stylesheet"
	href="<c:url value="/resourcesUtils/css/admin/imageScale.css"/>"
	media="screen">


<fieldset>
	<legend>Form Post Test</legend>

	<!--  <input name="file" id="file" type="file" /> -->
	<div id="result"></div>
	<div id="uploadFile">
		<input class="browseButton" name="uploadedfile" multiple="false"
			type="file" data-dojo-type="dojox/form/Uploader"
			uploadOnSelect="true" url="image/save"
			data-dojo-props="label: 'Select Some Files'" id="uploaderImage">
		<div id="files" data-dojo-type="dojox/form/uploader/FileList"
			data-dojo-props="uploaderId: 'uploaderImage'"></div>

	</div>

	<input id="categoryField">
	<button id="addCategory" data-dojo-type="dijit/form/Button"
		type="submit">Add</button>
	<button id="categoryImageButton" data-dojo-type="dijit/form/Button"
		type="submit">Submit</button>
	<div id="categoriesList"
		style="width: 100px; height: 3px; background-color: red"></div>
	<div id="output"></div>
</fieldset>
<script>
	/*  require(["dojo/request/iframe", "dojo/dom", "dojo/dom-construct", "dojo/json", "dojo/on", "dojo/domReady!"],
		function(iframe, dom, domConst, JSON, on){
		  on(dom.byId("categoryImageButton"), "click", function(){
		    domConst.place("<p>Requesting...</p>", "output");
		    iframe("save",{
		      form: "imageUpload",
		     // handleAs: "text",
		      mimetype: "text/html",
		    }).then(function(data){
		      domConst.place("<p>data: <code>" + JSON.stringify(data) + "</code></p>", "output");
		    });
		  });
		});   */
</script>
<script src="<c:url value="/resourcesUtils/js/admin/dataAccess.js"/>"></script>
<script
	src="<c:url value="/resourcesUtils/js/admin/addCategoryImage.js"/>"></script>
<script>
	require([ "dojox/form/Manager", "dojo/domReady!", "dojo/request/iframe",
			"dojo/dom", "dojo/dom-construct", "dojo/json", "dojo/on",
			"dojox/form/Uploader", "dojox/form/uploader/FileList",
			"dijit/form/Button", "dojox/form/uploader/plugins/Flash",
			"dojox/form/uploader/plugins/IFrame",
			"dojox/form/uploader/plugins/HTML5"

	], function(parser, iframe, dom, domConst, JSON, on) {

		dropDownData("categoryOfImage/view/getAll", "categoryField",
				"categoryOfImage", "Category Type", "name");

	});

	require(
			["dojo/dom-style", "dojo/ready", "dojo/on", "dojo/dom", "dojo/parser" ],
			function(domStyle,ready, on, dom, parse) {
				ready(function() {
					parse.parse();
					var submitButton = dijit.byId("categoryImageButton");
					var addButton = dijit.byId("addCategory");
					submitButton.setAttribute('disabled', true);
					addButton.setAttribute('disabled', true);
					var idImage = false;
					var uploader = dijit.byId("uploaderImage");
					on(
							uploader,
							"complete",
							function(result) {
								idImage = result.result;
								//console.log(idImage);
								dom.byId("result").innerHTML = '<img src="data:image/png;base64,'+result.image+'" alt="my image"/>';
								domStyle.set("uploadFile", "display", "none"); // == 0.5
								//dojo.place('<img src="data:image/png;base64,'+result.image+'" alt="my image"/>', "uploadFile", "replace");
								submitButton.setAttribute('disabled', false);
								addButton.setAttribute('disabled', false);
								addCategory(addButton, "categoriesList",
										"categoryField", "categories",
										submitButton, "image/addCategories",
										idImage);
								//console.log(addButton.get("disabled"));
							});

				});
			});
</script>



