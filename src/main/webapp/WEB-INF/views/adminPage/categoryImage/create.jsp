<%@ page pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/include.jsp"%>
<fieldset>
	<div data-dojo-type="dijit/form/Form" id="categoryImageForm"
		data-dojo-id="categoryImageForm" action="" method="post">

		<legend>Create new category of image</legend>
		<input id="mainCategoryField"> <input type="text"
			id="categoryImageField" name="category" required="true"
			data-dojo-type="dijit/form/ValidationTextBox"
			placeholder="Type in category of image" />

		<button id="categoryImageButton" data-dojo-type="dijit/form/Button"
			type="submit">Create</button>

		<div id="svrMessage"></div>

	</div>
</fieldset>
<div id="gridContainer" style="width: 100%; height: 200px;"></div>
<script src="<c:url value="/resourcesUtils/js/admin/dataAccess.js"/>"></script>
<script>
require(['dojox/grid/DataGrid'], function(DataGrid){
var layout = [
              {name: 'No', field: 'no'},
              {name: 'Main Category', field: 'mainCategory', width: 10,
                  editable: true,        /*Editable cell*/
                  type: dojox.grid.cells.Cell, //Use DateTextBox in editing mode

              },
              {name: 'Category', field: 'category', width: 10,
                  editable: true,        /*Editable cell*/
                  type: dojox.grid.cells.Cell, //Use DateTextBox in editing mode

              }
          ];
          
dropDownData("mainCategory/getAll","mainCategoryField","mainCategory", "Main Category Type","name" );
dataGrid("categoryOfImage/view/gridView", layout);
postForm("categoryImageForm","categoryImageForm","categoryOfImage/save");
});

</script>
<script src="<c:url value="/resourcesUtils/js/admin/dataGrid.js"/>"></script>

