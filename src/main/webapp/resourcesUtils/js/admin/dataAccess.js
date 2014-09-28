function dropDownData(uri, id, name, placeholder, searchAttr) {
	require([ "dojo", "dojo/store/Memory", "dijit/form/ComboBox",
			"dojo/domReady!" ], function(dojo, MemoryStore, ComboBox) {

		var store = null;

		var xhrGets = dojo.xhrGet({
			// url : "mainCategory/getAll",
			url : uri,
			handleAs : "json",
			preventCache : true,

			load : function(data) {
				// alert(data);
			},
			error : function(error) {
				console.error("Something goes wrong: " + error.message);
			}
		});
		xhrGets.then(function(data) {
			store = new MemoryStore({
				data : data.getAll,
				idProperty : "ID"
			});

			var comboBox = {
				// id : "mainCategoryField",
				id : id,
				// name : "mainCategory",
				name : name,
				value : "",
				store : store,
				// searchAttr : "name",
				searchAttr : searchAttr,
				// placeholder : "Category Type",
				placeholder : placeholder,
				required : true
			};
			// console.log(store); // print on console the store object
			dijit.form.ComboBox(comboBox, id);

		});

	});
}
function postForm(idButton, idForm, uri) {
	require(
			[ "dojo/dom", "dojo/on", "dojo/parser", "dojo/ready",
					"dijit/registry", "dijit/form/Button", "dijit/form/Form",
					"dijit/form/ValidationTextBox", "dojo/request",
					"dojo/dom-form" ],
			function(dom, on, parser, ready, registry, request, domForm) {

				var submitForm = function(evt) {
					// var data = dijit.byId("categoryImageForm").get("value");
					var data = dijit.byId(idForm).get("value");
					var xhrPosts = {
						// url : "categoryOfImage/save",
						url : uri,
						postData : dojo.toJson(data),
						/*
						 * postData : dojo.toJson({ mainCategory : "value1",
						 * category : "value2" }),
						 */
						handleAs : "json",
						headers : {
							"Content-Type" : "application/json",
							"Accept" : "application/json"
						},
						load : function(data) {

							dom.byId("svrMessage").innerHTML = "Form posted.";
						},
						error : function(error) {
							// We'll 404 in the demo, but that's okay. We don't
							// have a
							// 'postIt' service on the
							// docs server.
							dom.byId("svrMessage").innerHTML = "Form posted.";
						}
					};
					if (categoryImageForm.validate()) {
						dojo.stopEvent(event);
						dom.byId("svrMessage").innerHTML = "Message being sent...";
						console.log(data);
						// console.log(categoryImageForm.getValues());
						dojo.xhrPost(xhrPosts);

					} else {
						// alert("muah2");
					}
				};

				ready(function() {
					parser.parse();

					// var myForm = dom.byId("categoryImageForm");
					var button = dom.byId(idButton);
					on(button, "submit", submitForm);

				});

			});
}
