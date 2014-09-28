function addCategory(idButton, idList, idField, idCategories, idSubmitButton,
		uri, idImage) {
	require([ "dojo/store/Memory", "dojo/dom", "dojo/on", "dojo/parser",
			"dojo/ready", "dijit/form/Button", "dijit/form/FilteringSelect" ],
			function(Memory, dom, on, parser, ready, FilteringSelect) {
				var categories = [];
				var categoriesList = new Memory({
					data : categories,
					idProperty : "category"
				});
				var addCategoryToList = function(evt) {

					var field = dijit.byId(idField);
					// var categories = dijit.byId(idCategories);
					var categoryValue = field.get("value");
					var value = "<div class='node'>" + field.get("value")
							+ "</div>";
					var categoryValues = categoryValue.split(" - ");
					if (categoriesList.query({
						mainCategory : categoryValues[1],
						category : categoryValues[0]
						
					}).length == 0) {
						
						categoriesList.add({
							mainCategory : categoryValues[1],
							category : categoryValues[0]
						});
						dojo.place(value, idList, "after");

						// categories.set("value", category);
					}

				};

				var upload = function(evt) {
					var categories = dijit.byId(idCategories);
					// categories.set("value", categoriesList);
				/*	console.log(dojo.toJson(categoriesList.data));
					console.log(dojo.toJson([{
						mainCategory : "value1",
						category : "value2"
					},{
						mainCategory : "value1",
						category : "value2"
					}]
					));*/
					console.log(dojo.toJson(categoriesList.data+",{'idImage':'"+idImage+"'}"));
					var xhrPosts = {
						// url : "categoryOfImage/save",
						url : uri,
						postData : dojo.toJson(categoriesList.data),

						/*postData : dojo.toJson([{
							mainCategory : "value1",
							category : "value2"
						},{
							mainCategory : "value1",
							category : "value2"
						}]),*/

						handleAs : "json",
						headers : {
							"Content-Type" : "application/json",
							"Accept" : "application/json"
						},
						load : function(data) {

							//dom.byId("svrMessage").innerHTML = "Form posted.";
						},
						error : function(error) {
							// We'll 404 in the demo, but that's okay. We don't
							// have a
							// 'postIt' service on the
							// docs server.
							//dom.byId("svrMessage").innerHTML = "Form posted.";
						}
					};
					

					dojo.xhrPost(xhrPosts);

				};
				ready(function() {
					parser.parse();
					var button = idButton;
					var submitButton = idSubmitButton;

					if (idImage == true) {
						on(button, "click", addCategoryToList);
						on(submitButton, "click", upload);
					}

					// console.log(store.query({name:"Bill"}).length);

				});

			});
}