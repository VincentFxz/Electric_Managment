require([
	"dojox/data/JsonRestStore",
	"dojo/store/Memory",
	"dojo/store/Cache",
	"dojox/grid/DataGrid",
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
	"dojo/parser",
	"dijit/layout/TabContainer", 
	"dijit/layout/ContentPane",
	"dijit/form/DropDownButton",
	"dijit/TooltipDialog",
	"dijit/form/TextBox",
	"dojo/domReady!"
], function(JsonRestStore, Memory, Cache, DataGrid, ObjectStore, 
	query, ready, TextBox, Button, Menu, MenuItem, ComboButton,
	dom,xhr){	
	
	ready(function(){
		//types for search
		var search_types_value=[
			{name:"name",display_name:"按电表名称搜索"},
			{name:"pumpName",display_name:"按泵名称搜索"},
			{name:"companyName",display_name:"按公司名称搜索"},
			{name:"projectName",display_name:"按项目名称搜索"}
		]
		//construt a search type memu
		var search_type_menu=new Menu({
			id: "search_type_menu"
		});
		//construct menu items for the search type menu
		for (key in search_types_value){
			if(search_types_value[key]){
				var menu_item=new MenuItem({
					label:search_types_value[key].display_name,
					onClick:function(){
					}
				});
				search_type_menu.addChild(menu_item);
			}	
		}
		//construct the search type button
		var search_type_button=new ComboButton({
			id:"search_type_button",
			label:"高级搜索",
			dropDown: search_type_menu
		},"search_type_button");
		search_type_button.startup();
		search_type_menu.startup();
		
		//construt a textbox for search value input
		var search_textbox=new TextBox({
			name:"search_textbox",
			value:"",
			placeholder:"在此输入搜索名称"														
		},"search_textbox");
		search_textbox.startup();
		
		//construt a button for committing a search 
		var search_button=new Button({
			label:"搜索",
			onClick: function(){
//					console.log("search click")
//					var search_value=dijit.byId("search_textbox").value;
//					console.log(search_value);
//					var jsonRestStore=JsonRest({target:"/ammeter/list/"+search_value});		
//					var myStore = Cache(jsonRestStore, Memory());
//					var objectStore=new ObjectStore({objectStore: myStore});
//					console.log(objectStore);
//					grid.store=objectStore;
//					grid.startup();
				}
		},"search_button");
		search_button.startup();	
		//construt add button
		var add_ammeter_btn=new Button({
			label:"新建",
			onClick:function(){
//				console.log(dom.byId("name").value);
//				console.log(dom.byId("add_ammeter_form"));
//				var form_content={
//					name:dom.byId("name").value,
//					companyName:dom.byId("companyName").value,
//					projectName:dom.byId("projectName").value,
//					pumpName:dom.byId("pumpName").value,
//				};
//				jsonRestStore.put(form_content);
				//console.log(jsonRestStore);
				//xhr.post({
				//	form: "add_ammeter_form", // read the url: from the action="" of the <form>
				//	timeout: 3000, // give up after 3 seconds
				//	content:form_content,
				//	handleAs:"json",
				//	load:function(new_ammeter){
							//objectStore.newItem(new_ammeter);
				//			console.log(new_ammeter)
							
				//		}
				//});
				
			}
		},"add_ammeter_btn");
		add_ammeter_btn.startup();
	});		
	
	//construt grid	
	jsonRestStore=JsonRestStore({target:"/ammeter/list/"});		
	var myStore = Cache(jsonRestStore, Memory());
	var objectStore=new ObjectStore({objectStore: myStore});
	console.log(objectStore);
	var grid = new DataGrid({
		store: dataStore = objectStore,
		rowSelector: '20px',
		structure: [
					 { name: "电表编号", field: "id", width: "25%",canSort:true},
					 { name: "电表名称", field: "name", width: "25%",editable:true},
					 { name: "泵名称", field: "pumpName", width: "25%",editable:true},
					 { name:"项目名称", field: "projectName",width: "25%",editable:true},
					 { name:" 公司名称", field: "companyName",width: "25%",editable:true}
				]
	}, "grid"); // make sure you have a target HTML element with this id
	grid.startup();
	
	ready(function(){
		//
		var save_button=new Button({
			label:"保存",
			onClick: function(){
//				var ammeters_selected=grid.selection.getSelected();
//				if(ammeters_selected.length){
//					ammeter_selected=ammeters_selected[0];
//					console.log(ammeter_selected);
//					var url="/ammeter/list/update?id="+ammeter_selected.id+",name="+ammeter_selected.name;
//					console.log(url);
//					xhr.post({
//						form: "deleget_form", // read the url: from the action="" of the <form>
//						timeout: 3000, // give up after 3 seconds
//						content: ammeter_selected // creates ?part=one&another=part with GET, Sent as POST data when using xhrPost
//					});
//				}
				console.log(jsonRestStore);
				jsonRestStore.save();
				
			}
		},"save_button");
		save_button.startup();
		
		var delete_button=new Button({
			label:"删除",
			onClick: function(){
//				var ammeters_selected=grid.selection.getSelected();
//				if(ammeters_selected.length){
//					for(key in ammeters_selected){
//						objectStore.deleteItem(ammeters_selected[key]);
//						jsonRestStore.remove(ammeters_selected[key].id);
//					}
//				}
			}
		},"delete_button");
		delete_button.startup();
		
		var add_button=new Button({
			label:"添加",
			onClick: function(){
				
			}
		},"add_button");
		add_button.startup();
	});
});
