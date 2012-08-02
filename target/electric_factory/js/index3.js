
require([
	"dojox/data/JsonRestStore",
	"dojo/store/Memory",
	"dojo/store/Cache",
	"dojox/grid/EnhancedGrid",
	"dojo/data/ObjectStore",
	"dojo/query",
	"dojo/ready",
	"dijit/form/TextBox",
	"dijit/form/Button",
	"dijit/Menu", 
	"dijit/MenuItem", 
	"dijit/form/ComboButton",
	"dojo/dom",
	"dojo/_base/xhr",
	"dojo/i18n!/dojo-release-1.7.2/dojox/grid/enhanced/nls/zh/Filter.js",
	"dojox/grid/enhanced/plugins/Search",
	"dojox/grid/enhanced/plugins/Filter",
	"dojo/parser",
	"dijit/layout/TabContainer", 
	"dijit/layout/ContentPane",
	"dijit/form/DropDownButton",
	"dijit/TooltipDialog",
	"dijit/form/TextBox",
	"dojox/grid/enhanced/plugins/IndirectSelection",
	"dojox/grid/enhanced/plugins/Printer",
	"dojo/domReady!"
], function(JsonRestStore, Memory, Cache, EnhancedGrid, ObjectStore, 
	query, ready, TextBox, Button, Menu, MenuItem, ComboButton,
	dom,xhr,i18n){	
	
	ready(function(){
		
		ammeterRecordStore = new JsonRestStore({target: "/ammeter_record/list "});
		console.log(ammeterRecordStore);
		ammeterRecordGrid = new EnhancedGrid({
			store: recordDataStore =ammeterRecordStore,
			structure: [
			            {name: "电表记录编号", field: "id", width: "25%", canSort: true},
			            {name: "电表名称", field: "ammeterName", width: "25%", canSort: true},
			            {name: "电表数值", field: "ammeterValue", width: "25%", canSort: true},
			            {name: "电表记录日期", field: "recordDate", width: "25%", canSort: true},
			            ],
            plugins:{
				search: true,
				filter:true,
				printer: true,
				indirectSelection: {headerSelector:true, width:"40px", styles:"text-align: center;"}
			 }
		},"ammeter_record_grid");
		ammeterRecordGrid.startup();
		
		console.log(recordDataStore);
		
		ammeterStore = new JsonRestStore({target:"/ammeter/list/"});
		ammeterGrid = new EnhancedGrid({
			store: dataStore = ammeterStore,
			structure: [
						 { name: "电表编号", field: "id", width: "25%",canSort:true},
						 { name: "电表名称", field: "name", width: "25%",editable:true},
						 { name: "泵名称", field: "pumpName", width: "25%",editable:true},
						 { name:"项目名称", field: "projectName",width: "25%",editable:true},
						 { name:" 公司名称", field: "companyName",width: "25%",editable:true}
					],
			 plugins:{
				search: true,
				filter:true,
				printer: true,
				indirectSelection: {headerSelector:true, width:"40px", styles:"text-align: center;"}
			 }
		}, "ammeter_grid"); 
		ammeterGrid.startup();	
		
		//construt add button
		var add_ammeter_btn=new Button({
			label:"新建",
			onClick:function(){
				var form_content={
					name:dom.byId("name").value,
					companyName:dom.byId("companyName").value,
					projectName:dom.byId("projectName").value,
					pumpName:dom.byId("pumpName").value,
				};
				xhr.post({
					form: "add_ammeter_form", // read the url: from the action="" of the <form>
					timeout: 3000, // give up after 3 seconds
					content:form_content,
					handleAs:"json",
					load:function(new_ammeter){
							dataStore.newItem(new_ammeter);
						}
				});
			}
		},"add_ammeter_btn");
		add_ammeter_btn.startup();		

		//construt save button
		var save_button=new Button({
			label:"保存",
			onClick: function(){
				console.log("sava clicked");
				dataStore.save();
				
			}
		},"save_button");
		save_button.startup();
		
		//construt delete button
		var delete_button=new Button({
			label:"删除",
			onClick: function(){
				var ammeters_selected=ammeterGrid.selection.getSelected();
				if(ammeters_selected.length){
					for(key in ammeters_selected){
						dataStore.deleteItem(ammeters_selected[key]);
					}
				}
			}
		},"delete_button");
		delete_button.startup();
		
	});
});
