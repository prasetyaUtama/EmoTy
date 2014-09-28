function dataGrid(uri,layout){

require(['dojox/grid/DataGrid', 'dojo/data/ItemFileWriteStore', 'dojox/grid/cells/dijit', 'dojo/date/stamp', 'dojo/date/locale', 'dojo/domReady!'],
  function(DataGrid, ItemFileWriteStore, cells, stamp, locale){
    function formatDate(datum){
        /*Format the value in store, so as to be displayed.*/
        var d = stamp.fromISOString(datum);
        return locale.format(d, {selector: 'date', formatLength: 'long'});
    }

    function getDateValue(){
        /*Override the default getValue function for dojox.grid.cells.DateTextBox*/
        return stamp.toISOString(this.widget.get('value'));
    }

  /*  var layout = [
        {name: 'No', field: 'no'},
        {name: 'Main Category', field: 'mainCategory', width: 10,
            editable: true,        Editable cell
            type: dojox.grid.cells.Cell, Use DateTextBox in editing mode

        },
        {name: 'Category', field: 'category', width: 10,
            editable: true,        Editable cell
            type: dojox.grid.cells.Cell, Use DateTextBox in editing mode

        }
    ];*/
    var store = null;
    
    //get data
    var xhrGets = dojo.xhrGet({
		//url : "categoryOfImage/view/gridView",
    	url : uri,
		handleAs : "json",
		preventCache : true,

		load : function(data) {
		
		},
		error : function(error) {
			console.error("Something goes wrong: " + error.message);
		}
	});
    xhrGets.then(function(datas) {
		store = new ItemFileWriteStore({
	        data: {
	            identifier: "no",
	            items:datas.categories,
	        }
	    });
		  var grid = new dojox.grid.DataGrid({
		        id: 'grid',
		        store: store,
		        structure: layout
		    });
		    grid.placeAt('gridContainer');
		    grid.startup();

	});
    
  
});
}