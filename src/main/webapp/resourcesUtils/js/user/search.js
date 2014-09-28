require([ "dojo/dom", "dojo/on", "dojo/parser", "dojo/ready", "dijit/registry",
		"dijit/form/Button", "dijit/form/Form", "dijit/form/ValidationTextBox",
		"dojo/request", "dojo/dom-form", "dojox/layout/ContentPane" ], function(dom, on, parser, ready,
		registry, request, domForm, ContentPane) {
	var myContentPane = new ContentPane({

		style : "height:600px"
	}, dom.byId("body"));
	var submitForm = function (evt){
		var data = dijit.byId("searchForm").get("value");
		var xhrPosts = {
				url : "search",
				postData : dojo.toJson(data),

				handleAs : "json",
				headers : {
					"Content-Type" : "application/json",
					"Accept" : "application/json"
				},
				load : function(data) {

					alert(data);
					//dom.byId("svrMessage").innerHTML = "Form posted.";
				},
				error : function(error) {

					//dom.byId("svrMessage").innerHTML = "Form posted.";
				}
			};
		if (searchForm.validate()) {
			dojo.stopEvent(event);
			//dom.byId("svrMessage").innerHTML = "Message being sent...";
			console.log(data);
			// console.log(categoryImageForm.getValues());
			dojo.xhrPost(xhrPosts);

		} else {
			// alert("muah2");
		}
	};
	ready(function() {
		//parser.parse();

		//var myForm = dom.byId("searchForm");
		//on(myForm, "submit", submitForm);


	});
});