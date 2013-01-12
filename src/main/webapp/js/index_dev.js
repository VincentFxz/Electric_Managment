var userPane;
var construtUserPane;
var userPaneConstruted = false;

var ammeterPane;
var construtAmmeterPane;
var ammeterPaneConstruted = false;

var ammeterRecordPane;
var construtAmmeterRecordPane;
var ammeterRecordPaneConstruted = false;

var companyPane;
var construtCompanyPane;
var companyPaneConstruted = false;

var projectPane;
var construtProjectPane;
var projectPaneConstruted = false;

var cpPane;
var construtCPPane;
var cpPaneConstruted = false;

var paPane;
var construtPAPane;
var paPaneConstruted = false;

var upPane;
var constructUPPane;
var upPaneConstruted = false;

var constructNewPane;

var saveComputationPane;
var constructSaveComputationPane;
var saveComputationPaneConstructed = false;

var lastAmmeterStatusPane;
var constructLastAmmeterStatusPane;
var lastAmmeterStatusPaneConstructed = false;

var activedMenuItem;


require([
        "dijit/registry",
        "dojo/on",
        "dojo/topic",
        "dojox/data/JsonRestStore", 
        // "dojo/store/JsonRest",
        "dojo/store/Memory", 
        "dojo/store/Cache", 
        "dojox/grid/EnhancedGrid", 
        "dojo/data/ObjectStore", 
        "dojo/query", 
        "dojo/ready", 
        "dijit/form/RadioButton",
        "dijit/form/MultiSelect",
        "dijit/form/TextBox", 
        "dijit/form/Button", 
        "dijit/Menu", 
        "dijit/MenuItem", 
        "dijit/form/ComboButton", 
        "dijit/form/ComboBox", 
        "dijit/form/FilteringSelect",
        "dojo/dom-style",
        "dojo/dom-class",
        "dojo/dom-construct",
        "dojo/dom", 
        "dojo/_base/xhr", 
        "dijit/layout/ContentPane", 
        "dijit/Dialog", 
        "dojo/i18n!/dojo-release-1.7.2/dojox/grid/enhanced/nls/zh/Filter.js",
        "dojox/grid/enhanced/plugins/Search",
        "dojox/grid/enhanced/plugins/Filter", 
        "dojo/parser", 
        "dijit/layout/TabContainer", 
        "dijit/form/DropDownButton", 
        "dijit/TooltipDialog", 
        "dijit/form/TextBox", 
        "dojox/grid/enhanced/plugins/IndirectSelection", 
        "dojox/grid/enhanced/plugins/Printer", 
        "dojo/domReady!"], function(registry, on, topic, JsonRestStore, Memory, Cache, EnhancedGrid, ObjectStore, query, ready, RadioButton, MultiSelect, TextBox, Button, Menu, MenuItem, ComboButton, ComboBox, FilteringSelect, domStyle, domClass, domConstruct, dom, xhr, ContentPane, Dialog, i18n) {

    ready(function() {
    	
        //hidden the user ContentPane
        var tabContainer = dijit.byId("tab_container");

        userPane = dijit.byId("userPane");
        // ammeterPane = dijit.byId("ammeterPane");
        ammeterRecordPane = dijit.byId("ammeterRecordPane");
        companyPane = dijit.byId("companyPane");
        projectPane = dijit.byId("projectPane");
        saveComputationPane = registry.byId("saveComputationPane");
        lastAmmeterStatusPane = registry.byId("lastAmmeterStatusPane");
        cpPane = dijit.byId("cpPane");
        paPane = dijit.byId("paPane");
        upPane = dijit.byId("upPane");

        tabContainer.removeChild(userPane);
        // tabContainer.removeChild(ammeterPane);
        tabContainer.removeChild(ammeterRecordPane);
        tabContainer.removeChild(companyPane);
        tabContainer.removeChild(projectPane);
        tabContainer.removeChild(saveComputationPane);
        tabContainer.removeChild(lastAmmeterStatusPane);
        tabContainer.removeChild(cpPane);
        tabContainer.removeChild(paPane);
        tabContainer.removeChild(upPane);

        var main_container_width = dojo.style("main_container", "width");
        var ammeter_cell_width = main_container_width / 6;
        var ammeter_record_width = main_container_width * 0.24;
        var user_cell_width = main_container_width * 0.17;
        var company_cell_width = main_container_width / 3;
        var project_cell_width = (main_container_width - 100) / 7;
        var cp_cell_width = main_container_width * 0.20;
        var pa_cell_width = main_container_width * 0.20;
        var up_cell_width = main_container_width * 0.20;
        var last_cell_width = "5em";

        var request = {

        };

        var stores = {

        };

        var constructor = {
            projectConstructor : function (name){
                paneNode = constructNewPane("公司" + id + "的项目", "公司" + id + "的项目","width: 100%;",tabContainer);
                //create button node
                var addButtonNodeId = paneNode + "AddButton";
                var newAddButtonNode = document.createElement("div");
                newAddButtonNode.setAttribute("id", addButtonNodeId);
                document.getElementById(paneNode).appendChild(newAddButtonNode);
                //construt add button
                var add_project_btn = new Button({

                    label: "新建",
                    onClick: function() {

                        if(request){
                            request.ammetersForProject = [];
                        }

                        var projectCompanyCombo = registry.byId("projectCompany");
                        
                        if(registry.byId("createNewCompanyRadio").checked){
                            var projectCompanyCombo = registry.byId("projectCompanyCombo");
                            var projectCompanyTextBox = registry.byId("projectCompanyTextBox");
                            
                            if(projectCompanyCombo){
                                registry.remove("projectCompanyCombo");
                                domConstruct.destroy("widget_projectCompanyCombo");
                            }
                            
                            if(projectCompanyTextBox){
                                
                                registry.remove("projectCompanyTextBox");
                                domConstruct.destroy("widget_projectCompanyTextBox");
                            }
                            
                            var projectCompanyTextBox = document.getElementById("projectCompanyTextBox")||document.createElement("input");
                            projectCompanyTextBox.setAttribute("id", "projectCompanyTextBox");
                            document.getElementById("projectCompanyLi").appendChild(projectCompanyTextBox);
                            
                            
                            var companyForProjectTextBox = new TextBox({
                                placeHolder: "输入公司名称"
                            },"projectCompanyTextBox");
                        }
                        
                        if(registry.byId("selectExistingCompanyradio").checked){
                            var projectCompanyCombo = registry.byId("projectCompanyCombo")
                            var projectCompanyTextBox = registry.byId("projectCompanyTextBox");
                            if(projectCompanyTextBox){
                                registry.remove("projectCompanyTextBox");
                                domConstruct.destroy("widget_projectCompanyTextBox");
                            }
                            
                            if(projectCompanyCombo){
                                registry.remove("projectCompanyCombo");
                                domConstruct.destroy("widget_projectCompanyCombo");
                            }
                    
                            var projectCompanyCombo = document.getElementById("projectCompanyCombo")||document.createElement("input");
                            projectCompanyCombo.setAttribute("id", "projectCompanyCombo");
                            document.getElementById("projectCompanyLi").appendChild(projectCompanyCombo);
                            
                            if(stores&&(!stores.companyStore)){
                                stores.companyStore = new JsonRestStore({
                                    target: "/company/list/"
                                });
                            }

                            var companyName = stores.companyStore.fetchItemByIdentity({"identity" : id}).companyName;

                            var projectCompanyCombo = new ComboBox({
                                id: "projectCompanyCombo",
                                name: "company",
                                value: companyName,
                                store: stores.companyStore,
                                searchAttr: "companyName"
                            }, "projectCompanyCombo");
                        }
                        var createProjectForCompanyDialog = registry.byId("createProjectForCompanyDialog");
                        if(createProjectForCompanyDialog){
                            dojo.byId("createProjectDialogHiddenBtn").style.display="inline";                
                            createProjectForCompanyDialog.show();
                        }
                                                                        
                    }
                }, addButtonNodeId);
                add_project_btn.startup();
                
                //create addFromExistButton
                var addFromExistButtonNodeId = paneNode + "AddFromExsitButton";
                var addFromExitAddButtonNode = document.createElement("div");
                addFromExitAddButtonNode.setAttribute("id", addFromExistButtonNodeId);
                document.getElementById(paneNode).appendChild(addFromExitAddButtonNode);
                var addProjectFromExistBtn = new Button({
                    label: "从已存在的项目中添加",
                    onClick: function(){
                        
                    }
                },addFromExistButtonNodeId);
                
                //create close tab button
                constructCloseTabBtn(paneNode);

                //create grid node
                var gridNodeId = paneNode + "Grid";
                var newGridNode = document.createElement("div");
                document.getElementById(paneNode).appendChild(newGridNode);
                newGridNode.setAttribute("id", gridNodeId);
                newGridNode.setAttribute("style", "height:400px;");
                var newGridLayout = layouts.projectGridLayout;
                var newGrid = constructNewGridForPane(gridNodeId, newGridLayout, "/project/list/company/" + id);

                topic.subscribe("updateProject", function(text){
                    newGrid.setQuery({"id" : "*"}); 
                });

            }
        }

        var formatters = {
            dateFormatter : function(inDatum){
                return dojo.date.locale.format(new Date(inDatum), this.constraint);
            },

            ammeterGridOptFormatter : function(id){
                return new Button({
                    label:"查看记录",
                    onClick: function() {
                        construtAmmeterRecordPane(id);
                    }
                });
            },

            projectGridOptFormatter : function(id){
                return new Button({
                    label:"查看电表",
                    onClick: function() {
                        var paneName = "项目" + id + "的电表";
                        var paneNode = dijit.byId(paneName);
                        if(paneNode){
                            paneGrid = dijit.byId(paneName+"Grid");
                            paneGrid.setQuery({"id" : "*"}); 
                            //tabContainer.addChild(paneNode);
                            tabContainer.selectChild(dijit.byId(paneNode));
                            return ;
                        }else{
                            paneNode = constructNewPane(paneName, paneName,"width: 100%;",tabContainer);
                            //create button node
                            var addButtonNodeId = paneNode + "AddButton";
                            var newAddButtonNode = document.createElement("div");
                            newAddButtonNode.setAttribute("id", addButtonNodeId);
                            document.getElementById(paneNode).appendChild(newAddButtonNode);
                            //construt add button
                            var add_ammeter_btn = new Button({

                                label: "新建",
                                onClick: function() {
                                	dojo.byId("ammeterProjectLi").style.display="inline";
                                    var ammeterProjectCombo = registry.byId("ammeterProject");
                                    if(!ammeterProjectCombo){
                                        if(stores&&(!stores.projectStore)){
                                            stores.projectStore = new JsonRestStore({
                                                target: "/project/list/"
                                            });
                                        }

                                        //console.log(stores.projectStore.byId({"identity" : id}));
                                        if(!stores.projectStore.byId({"identity":id})){
                                            stores.projectStore.byId({
                                                "identity": id,
                                                onItem: function(){
                                                    var projectName = stores.projectStore.byId({"identity": id}).projectName;
                                                    var projectForAmmeterCombo = new ComboBox({
                                                        id: "ammeterProject",
                                                        name: "project",
                                                        value: projectName,
                                                        store: stores.projectStore,
                                                        searchAttr: "projectName"
                                                    }, "ammeterProject");

                                                    registry.byId("createAmmeterDialog").show();
                                                },
                                                onError: function(){

                                                }
                                            });
                                        }else{
                                            var projectName = stores.projectStore.byId({"identity": id}).projectName;
                                            var projectForAmmeterCombo = new ComboBox({
                                                id: "ammeterProject",
                                                name: "project",
                                                value: projectName,
                                                store: stores.projectStore,
                                                searchAttr: "projectName"
                                            }, "ammeterProject");

                                            registry.byId("createAmmeterDialog").show();
                                        }

                                    }else{
                                        if(!stores.projectStore.byId({"identity":id})){
                                            stores.projectStore.byId({
                                                "identity": id,
                                                onItem: function(){
                                                    var projectName = stores.projectStore.byId({"identity": id}).projectName;
                                                    ammeterProjectCombo.setValue(projectName);
                                                    registry.byId("createAmmeterDialog").show();
                                                },
                                                onError: function(){

                                                }
                                            });
                                        }else{
                                            var projectName = stores.projectStore.byId({"identity": id}).projectName;
                                            ammeterProjectCombo.setValue(projectName);
                                            console.log(registry.byId("ammeterProject"));
                                            registry.byId("createAmmeterDialog").show();
                                        }
                                        registry.byId("createAmmeterDialog").show();
                                    }
                                }
                            }, addButtonNodeId);
                            add_ammeter_btn.startup();
                            
                            //create addFromExistButton
                            var addFromExistButtonNodeId = paneNode + "AddFromExsitButton";
                            var addFromExitAddButtonNode = document.createElement("div");
                            addFromExitAddButtonNode.setAttribute("id", addFromExistButtonNodeId);
                            document.getElementById(paneNode).appendChild(addFromExitAddButtonNode);
                            var addProjectFromExistBtn = new Button({
                                label: "从已存在的项目中添加",
                                onClick: function(){
                                    
                                }
                            },addFromExistButtonNodeId);
                            
                            //create close tab button
                            constructCloseTabBtn(paneNode);

                            //create grid node
                            var gridNodeId = paneNode + "Grid";
                            var newGridNode = document.createElement("div");
                            document.getElementById(paneNode).appendChild(newGridNode);
                            newGridNode.setAttribute("id", gridNodeId);
                            newGridNode.setAttribute("style", "height:400px;");
                            var newGridLayout = layouts.ammeterGridLayout;
                            var newGrid = constructNewGridForPane(gridNodeId, newGridLayout, "/ammeter/list/project/" + id);
                            topic.subscribe("updateAmmeter", function(text){
                                newGrid.setQuery({"id" : "*"}); 
                            });
                        }   
                    }
                });
            },

            companyGridOptFormatter : function(id, name){
                return new Button({
                    label:"查看项目",
                    onClick: function() {
                        var paneNode = dijit.byId("公司" + id + "的项目");
                        if(paneNode){
                            paneGrid = dijit.byId("公司" + id + "的项目Grid")
                            paneGrid.setQuery({"id" : "*"}); 
                            //tabContainer.addChild(paneNode);
                            tabContainer.selectChild(dijit.byId(paneNode));
                            return ;
                        }else{
                            
                        }                                       
                    }
                });
            }
        };

        var layouts = {
    		
            lastAmmeterStatusGridLayout: [{
                name: "电表名",
                field: "ammeterName",
                width: "15em",
                canSort: true
            }, {
                name: "电表示数",
                field: "ammeterValue",
                width: "15em",
                canSort: true
            }, {
                name: "累时器读数",
                field: "timeSum",
                width: "15em",
                editable: true
            }, {
                name: "单位小时能耗",
                field: "costPerHour",
                width: "15em",
                canSort: true
            }, {
                name: "报警类型",
                field: "warningStatus",
                width: "15em",
                canSort: true
            }],

            
            saveComputationGridLayout: [{
                cells:[[{
                        name: "电表名", field: "ammeterName"
                    },{
                        name: "日期", field: "startDate",  formatter: formatters.dateFormatter,
                    },{
                        name: "累时器值", field: "startTimeSum"
                    },{
                        name: "电表度数", field: "startValue"
                    },{
                        name: "日期", field: "endDate",  formatter: formatters.dateFormatter,
                    },{
                        name: "累时器值", field: "endTimeSum"
                    },{
                        name: "电表示数", field: "endValue"
                    },{
                        name: "互感器倍率", field: "sensorRate"
                    },{
                        name: "技改后能耗", field: "realCost"
                    },{
                        name: "技改前能耗", field: "formerCost"
                    },{
                        name: "节电量", field: "eletricSave"
                    },{
                        name: "标准电费", field: "eletricCharge"
                    },{
                        name: "成分比率", field: "partsRatio"
                    },{
                        name: "节能收益", field: "bonus"
                    }],[{
                        name: "抄表起始示数", colSpan: 4
                    },{
                        name: "抄表结束示数", colSpan: 3
                    },{
                        name: "节能计算", colSpan: 7
                    }
                ]],
                onBeforeRow : function(inDataIndex, inSubRows) {
                     if (inDataIndex >= 0){ 
                        inSubRows[1].invisible = true; 
                    } else { 
                        inSubRows[1].invisible = false; 
                    }  
                }
            }],
    		
            upGridLayout : [{
                name: "编号",
                field: "id",
                width: up_cell_width * 0.2 + "px",
                canSort: true
            }, {
                name: "项目编号",
                field: "projectId",
                width: up_cell_width + "px",
                canSort: true
            }, {
                name: "项目名称",
                field: "projectName",
                width: up_cell_width + "px",
                editable: true
            }, {
                name: "用户编号",
                field: "userId",
                width: up_cell_width + "px",
                canSort: true
            }, {
                name: "用户名称",
                field: "userName",
                width: up_cell_width + "px",
                canSort: true
            }],

            ammeterGridLayout : [{
                name: "电表编号",
                field: "id",
                width: ammeter_cell_width * 0.5 + "px",
                canSort: true
            }, {
                name: "电表名称",
                field: "name",
                width: ammeter_cell_width + "px",
                editable: true
            }, {
                name: "泵名称",
                field: "pumpName",
                width: ammeter_cell_width + "px",
                editable: true
            }, 
            {
                name: "互感器倍率",
                field: "sensorRate",
                width: ammeter_cell_width * 0.5 + "px",
                canSort: true
            },{
                name: "技改前能耗",
                field: "formerCost",
                width: ammeter_cell_width * 0.5 + "px",
                canSort: true
            },{
                name: "报警上限",
                field: "upperLimit",
                width: ammeter_cell_width * 0.5 + "px",
                canSort: true
            },{
                name: "报警下限",
                field: "lowerLimit",
                width: ammeter_cell_width * 0.5 + "px",
                canSort: true
            },{
                name: "操作",
                field: "id",
                type: dojox.grid.cells._Widget,
                editable: false,
                formatter: formatters.ammeterGridOptFormatter
            }],

            projectGridLayout : [{
                name: "项目编号",
                field: "id",
                width: project_cell_width + "px",
                canSort: true
            }, {
                name: "项目名称",
                field: "projectName",
                width: project_cell_width + "px",
                editable: true
            }, {
                name: "项目开始日期",
                field: "startDate",
                width: project_cell_width + "px",
                formatter: formatters.dateFormatter, 
                canSort: true
            }, {
                name: "项目结束日期",
                field: "endDate",
                width: project_cell_width + "px",
                formatter: formatters.dateFormatter, 
                canSort: true
            }, {
                name: "当前电费",
                field: "electricityCharge",
                width: project_cell_width + "px",
                canSort: true
            }, {
                name: "分成比率",
                field: "partsRatio",
                width: project_cell_width + "px",
                canSort: true
            }, {
                name: "操作",
                field: "id",
                 width: project_cell_width / 2 + "px",
                type: dojox.grid.cells._Widget,
                editable: false,
                formatter: formatters.projectGridOptFormatter
            }],

            companyGridLayout : [{
                name: "公司编号",
                field: "id",
                width: company_cell_width + "px",
                canSort: true
            }, {
                name: "公司名称",
                field: "companyName",
                width: company_cell_width + "px",
                editable: true
            },{
                name: "操作",
                field: "id",
                width: company_cell_width / 2 + "px",
                type: dojox.grid.cells._Widget,
                editable: false,
                formatter: formatters.companyGridOptFormatter
            }]
        };
        
        //user project pane
        construtUPPane = function construtUPPane() {
            var construtUPGrid = function construtUPGrid() {
                constructNewGridForPane("up_grid", layouts.upGridLayout, "/up/list/")
            };

            var constructUPBtns = function constructUPBtns(){
                //construt add button
                var add_up_btn = new Button({

                    label: "新建",
                    onClick: function() {
                        var form_content = {
                            userName: dijit.byId("userForUPCombo").get("value"),
                            projectName: dijit.byId("projectForUPCombo").get("value")
                        };
                        xhr.post({
                            form: "add_up_form",
                            // read the url: from the action="" of the <form>
                            timeout: 3000,
                            // give up after 3 seconds
                            content: form_content,
                            handleAs: "json",
                            load: function(new_up) {
                                upDataStore.newItem(new_up);
                                updataStore.save();
                            }
                        });
                    }
                }, "add_up_btn");
                add_up_btn.startup();

                //construt save button
                var up_save_button = new Button({
                    label: "保存",
                    onClick: function() {
                        upDataStore.save();

                    }
                }, "up_save_button");
                up_save_button.startup();

                //construt delete button
                var up_delete_button = new Button({
                    label: "删除",
                    onClick: function() {
                        var up_selected = upGrid.selection.getSelected();
                        if (up_selected.length) {
                            for (key in up_selected) {
                                upDataStore.deleteItem(up_selected[key]);
                                upDataStore.save();
                            }
                        }
                    }
                }, "up_delete_button");
                up_delete_button.startup();
            };
            
            var constructUserProjectForUPCombo = function constructUserProjectForUPCombo() {

                    projectForUPStore = new JsonRestStore({
                        target: "/project/list/"
                    });

                    userForUPStore = new JsonRestStore({
                        target: "/user/list/"
                    });
                    
                    var projectForUPCombo = new ComboBox({
                        id: "projectForUPCombo",
                        name: "project",
                        value: "",
                        store: projectForUPStore,
                        searchAttr: "projectName"
                    }, "projectForUPCombo");

                    var userForUPCombo = new ComboBox({
                        id: "userForUPCombo",
                        name: "user",
                        value: "",
                        store: userForUPStore,
                        searchAttr: "username"
                    }, "userForUPCombo");
                };

            if (!upPaneConstruted) {
                if (typeof upPane != "undefined") {
                    tabContainer.addChild(upPane, 0);
                    tabContainer.selectChild(upPane);
                    construtUPGrid();
                    constructUPBtns();
                    constructUserProjectForUPCombo();
                    upPaneConstruted = true;
                }
            } else {
                tabContainer.selectChild(upPane);
            }
        };
        
        
        //project ammeter pane
        construtPAPane = function construtPAPane() {
            var construtPAGrid = function construtPAGrid() {
                    paStore = new JsonRestStore({
                        target: "/pa/list/"
                    });
                    paGrid = new EnhancedGrid({
                        store: paDataStore = paStore,
                        autoWidth: true,
                        structure: [{
                            name: "编号",
                            field: "id",
                            width: pa_cell_width * 0.2 + "px",
                            canSort: true
                        }, {
                            name: "项目编号",
                            field: "projectId",
                            width: pa_cell_width + "px",
                            canSort: true
                        }, {
                            name: "项目名称",
                            field: "projectName",
                            width: pa_cell_width + "px",
                            editable: true
                        }, {
                            name: "电表编号",
                            field: "ammeterId",
                            width: pa_cell_width + "px",
                            canSort: true
                        }, {
                            name: "电表名称",
                            field: "ammeterName",
                            width: pa_cell_width + "px",
                            canSort: true
                        }],
                        plugins: {
                            search: true,
                            filter: true,
                            printer: true,
                            indirectSelection: {
                                headerSelector: true,
                                width: "40px",
                                styles: "text-align: center;"
                            }
                        }
                    }, "pa_grid");
                    paGrid.startup();

                    //construt add button
                    var add_pa_btn = new Button({

                        label: "新建",
                        onClick: function() {
                            var form_content = {
                                ammeterName: dijit.byId("ammeterForPACombo").get("value"),
                                projectName: dijit.byId("projectForPACombo").get("value")
                            };
                            xhr.post({
                                form: "add_pa_form",
                                // read the url: from the action="" of the <form>
                                timeout: 3000,
                                // give up after 3 seconds
                                content: form_content,
                                handleAs: "json",
                                load: function(new_pa) {
                                    paDataStore.newItem(new_pa);
                                    padataStore.save();
                                }
                            });
                        }
                    }, "add_pa_btn");
                    add_pa_btn.startup();

                    //construt save button
                    var pa_save_button = new Button({
                        label: "保存",
                        onClick: function() {
                            paDataStore.save();

                        }
                    }, "pa_save_button");
                    pa_save_button.startup();

                    //construt delete button
                    var pa_delete_button = new Button({
                        label: "删除",
                        onClick: function() {
                            var pa_selected = paGrid.selection.getSelected();
                            if (pa_selected.length) {
                                for (key in pa_selected) {
                                    paDataStore.deleteItem(pa_selected[key]);
                                    paDataStore.save();
                                }
                            }
                        }
                    }, "pa_delete_button");
                    pa_delete_button.startup();
                }

            var constructProjectAmmeterForPACombo = function constructProjectAmmeterForPACombo() {

                    projectForPAStore = new JsonRestStore({
                        target: "/project/list/"
                    });

                    ammeterForPAStore = new JsonRestStore({
                        target: "/ammeter/list/"
                    });
                    
                    var projectForAmmeterCombo = new ComboBox({
                        id: "projectForPACombo",
                        name: "project",
                        value: "",
                        store: projectForPAStore,
                        searchAttr: "projectName"
                    }, "projectForPACombo");

                    var companyForAmmeterCombo = new ComboBox({
                        id: "ammeterForPACombo",
                        name: "company",
                        value: "",
                        store: ammeterForPAStore,
                        searchAttr: "name"
                    }, "ammeterForPACombo");
                };

            if (!paPaneConstruted) {
                if (typeof paPane != "undefined") {
                    tabContainer.addChild(paPane, 0);
                    tabContainer.selectChild(paPane);
                    construtPAGrid();
                    constructProjectAmmeterForPACombo();
                    paPaneConstruted = true;
                }
            } else {
                tabContainer.selectChild(paPane);
            }
        };

        //company project pane

        construtCPPane = function construtCPPane() {
            var construtCPGrid = function construtCPGrid() {
                    cpStore = new JsonRestStore({
                        target: "/cp/list/"
                    });
                    cpGrid = new EnhancedGrid({
                        store: cpDataStore = cpStore,
                        autoWidth: true,
                        structure: [{
                            name: "编号",
                            field: "id",
                            width: cp_cell_width * 0.2 + "px",
                            canSort: true
                        }, {
                            name: "项目编号",
                            field: "projectId",
                            width: cp_cell_width + "px",
                            canSort: true
                        }, {
                            name: "项目名称",
                            field: "projectName",
                            width: cp_cell_width + "px",
                            editable: true
                        }, {
                            name: "公司编号",
                            field: "companyId",
                            width: cp_cell_width + "px",
                            canSort: true
                        }, {
                            name: "公司名称",
                            field: "companyName",
                            width: cp_cell_width + "px",
                            canSort: true
                        }],
                        plugins: {
                            search: true,
                            filter: true,
                            printer: true,
                            indirectSelection: {
                                headerSelector: true,
                                width: "40px",
                                styles: "text-align: center;"
                            }
                        }
                    }, "cp_grid");
                    cpGrid.startup();

                    //construt add button
                    var add_cp_btn = new Button({

                        label: "新建",
                        onClick: function() {
                            var form_content = {
                                companyName: dijit.byId("companyForCP").get("value"),
                                projectName: dijit.byId("projectForCP").get("value")
                            };
                            xhr.post({
                                form: "add_cp_form",
                                // read the url: from the action="" of the <form>
                                timeout: 3000,
                                // give up after 3 seconds
                                content: form_content,
                                handleAs: "json",
                                load: function(new_cp) {
                                    cpDataStore.newItem(new_cp);
                                    cpdataStore.save();
                                }
                            });
                        }
                    }, "add_cp_btn");
                    add_cp_btn.startup();

                    //construt save button
                    var cp_save_button = new Button({
                        label: "保存",
                        onClick: function() {
                            cpDataStore.save();

                        }
                    }, "cp_save_button");
                    cp_save_button.startup();

                    //construt delete button
                    var cp_delete_button = new Button({
                        label: "删除",
                        onClick: function() {
                            var cp_selected = cpGrid.selection.getSelected();
                            if (cp_selected.length) {
                                for (key in cp_selected) {
                                    cpDataStore.deleteItem(cp_selected[key]);
                                    cpDataStore.save();
                                }
                            }
                        }
                    }, "cp_delete_button");
                    cp_delete_button.startup();
                };

            var constructProjectCompanyForCPCombo = function constructProjectCompanyForCPCombo() {

                    companyForCPStoreForCP = new JsonRestStore({
                        target: "/company/list/"
                    });

                    projectForCPStoreForCP = new JsonRestStore({
                        target: "/project/list/"
                    });
                    
                    var projectForCPCombo = new ComboBox({
                        id: "projectForCP",
                        name: "project",
                        value: "",
                        store: projectForCPStoreForCP,
                        searchAttr: "projectName"
                    }, "projectForCP");

                    var companyForCPCombo = new ComboBox({
                        id: "companyForCP",
                        name: "company",
                        value: "",
                        store: companyForCPStoreForCP,
                        searchAttr: "companyName"
                    }, "companyForCP");
                };

            if (!cpPaneConstruted) {
                if (typeof cpPane != "undefined") {
                    tabContainer.addChild(cpPane, 0);
                    tabContainer.selectChild(cpPane);
                    construtCPGrid();
                    constructProjectCompanyForCPCombo();
                    cpPaneConstruted = true;
                }
            } else {
                tabContainer.selectChild(cpPane);
            }
        };

        //project pane
        construtProjectPane = function construtProjectPane(id) {

            var construtProjectGrid = function construtProjectGrid() {
            		
            		var targetUrl = "/project/list/";
            		
            		if(id){
            			targetUrl = targetUrl + id;
            		}
            		
                    projectStore = new JsonRestStore({
                        target: targetUrl
                    });
                    projectGrid = new EnhancedGrid({
                        store: projectDataStore = projectStore,
                        autoWidth: true,
                        structure: layouts.projectGridLayout,
                        plugins: {
                            search: true,
                            filter: true,
                            printer: true,
                            indirectSelection: {
                                headerSelector: true,
                                width: "40px",
                                styles: "text-align: center;"
                            }
                        }
                    }, "project_grid");
                    projectGrid.startup();

                    //construt add button
                    var add_project_btn = new Button({

                        label: "新建",
                        onClick: function() {
                            var form_content = {
                                projectName: dom.byId("addProjectName").value,
                            };
                            xhr.post({
                                form: "add_project_form",
                                // read the url: from the action="" of the <form>
                                timeout: 3000,
                                // give up after 3 seconds
                                content: form_content,
                                handleAs: "json",
                                load: function(new_project) {
                                    projectDataStore.newItem(new_project);
                                    projectdataStore.save();
                                }
                            });
                        }
                    }, "add_project_btn");
                    add_project_btn.startup();

                    //construt save button
                    var save_button = new Button({
                        label: "保存",
                        onClick: function() {
                            projectDataStore.save();

                        }
                    }, "project_save_button");
                    save_button.startup();

                    //construt delete button
                    var delete_button = new Button({
                        label: "删除",
                        onClick: function() {
                            var project_selected = projectGrid.selection.getSelected();
                            if (project_selected.length) {
                                for (key in project_selected) {
                                    projectDataStore.deleteItem(project_selected[key]);
                                    projectDataStore.save();
                                }
                            }
                        }
                    }, "project_delete_button");
                    delete_button.startup();
                }

            if (!projectPaneConstruted) {
                if (typeof projectPane != "undefined") {
                    tabContainer.addChild(projectPane, 0);
                    tabContainer.selectChild(projectPane);
                    construtProjectGrid();
                    projectPaneConstruted = true;
                }
            } else {
                tabContainer.selectChild(projectPane);
            }
        };
        
        //save computation pane
        constructSaveComputationPane = function constructSaveComputationPane(id) {

            var constructSaveComputationGrid = function constructSaveComputationGrid() {
            		
        		var targetUrl = "/saveComputation/list";
        		
        		if(id){
        			targetUrl = targetUrl + id;
        		}
        		
        		stores.constructSaveComputationStore = new JsonRestStore({
                    target: targetUrl,
                    
                });

                // stores.constructSaveComputationStore.query({
                //     "startDate" : "2013-01-01",
                //     "endDate": "2013-01-10"
                // });

                saveComputationGrid = new EnhancedGrid({
                    store: stores.constructSaveComputationStore,
                    autoWidth: true,
                    structure: layouts.saveComputationGridLayout,
                    // structure: layouts.lalalayout,
                    plugins: {
                        search: true,
                        filter: true,
                        printer: true,
                        indirectSelection: {
                            headerSelector: true,
                            width: "40px",
                            styles: "text-align: center;"
                        }
                    },
                    onStyleRow: function(e) { 
                        dojo.style(e.node.children[0].children[0].rows[1],'display','none'); 
                    } 
                }, "saveComputationGrid");
                saveComputationGrid.startup();
            };

            if (!saveComputationPaneConstructed) {
                if (typeof saveComputationPane != "undefined") {
                    tabContainer.addChild(saveComputationPane, 0);
                    tabContainer.selectChild(saveComputationPane);
                    constructSaveComputationGrid();
                    saveComputationPaneConstructed = true;
                }
            } else {
                tabContainer.selectChild(saveComputationPane);
            }
        };//end save computation pane

        //to add
        constructLastAmmeterStatusPane = function constructLastAmmeterStatusPane(id) {

            var constructLastAmmeterStatusGrid = function constructLastAmmeterStatusGrid() {
                    
                var targetUrl = "/lastAmmeterStatus/list";
                            
                stores.lastAmmeterStatusStore = new JsonRestStore({
                    target: targetUrl
                });
                lastAmmeterStatusGrid = new EnhancedGrid({
                    store: stores.lastAmmeterStatusStore,
                    autoWidth: true,
                    structure: layouts.lastAmmeterStatusGridLayout,
                    // structure: layouts.lalalayout,
                    plugins: {
                        search: true,
                        filter: true,
                        printer: true,
                        indirectSelection: {
                            headerSelector: true,
                            width: "40px",
                            styles: "text-align: center;"
                        }
                    },
                }, "lastAmmeterStatusGrid");
                lastAmmeterStatusGrid.startup();
            };

            if (!lastAmmeterStatusPaneConstructed) {
                if (typeof lastAmmeterStatusPane != "undefined") {
                    tabContainer.addChild(lastAmmeterStatusPane, 0);
                    tabContainer.selectChild(lastAmmeterStatusPane);
                    constructLastAmmeterStatusGrid();
                    lastAmmeterStatusPaneConstructed = true;
                }
            } else {
                tabContainer.selectChild(lastAmmeterStatusPane);
            }
        };

        

        //company pane
        construtCompanyPane = function construtCompanyPane() {
            var construtCompanyGrid = function construtCompanyGrid() {
                    companyStore = new JsonRestStore({
                        target: "/company/list/"
                    });
                    
                    companyGrid = new EnhancedGrid({
                        store: companyDataStore = companyStore,
                        autoWidth: true,
                        structure: layouts.companyGridLayout,
                        plugins: {
                            search: true,
                            filter: true,
                            printer: true,
                            indirectSelection: {
                                headerSelector: true,
                                width: "40px",
                                styles: "text-align: center;"
                            }
                        }
                    }, "company_grid");
                    companyGrid.startup();

                    //construt add button
                    var add_company_btn = new Button({

                        label: "新建",
                        onClick: function() {
                            var form_content = {
                                companyName: dom.byId("addCompanyName").value,
                            };
                            xhr.post({
                                form: "add_company_form",
                                // read the url: from the action="" of the <form>
                                timeout: 3000,
                                // give up after 3 seconds
                                content: form_content,
                                handleAs: "json",
                                load: function(new_company) {
                                    companyDataStore.newItem(new_company);
                                    companydataStore.save();
                                }
                            });
                        }
                    }, "add_company_btn");
                    add_company_btn.startup();

                    //construt save button
                    var save_button = new Button({
                        label: "保存",
                        onClick: function() {
                            companyDataStore.save();

                        }
                    }, "company_save_button");
                    save_button.startup();

                    //construt delete button
                    var delete_button = new Button({
                        label: "删除",
                        onClick: function() {
                            var company_selected = companyGrid.selection.getSelected();
                            if (company_selected.length) {
                                for (key in company_selected) {
                                    companyDataStore.deleteItem(company_selected[key]);
                                    companyDataStore.save();
                                }
                            }
                        }
                    }, "company_delete_button");
                    delete_button.startup();
                }

            if (!companyPaneConstruted) {
                if (typeof companyPane != "undefined") {
                    tabContainer.addChild(companyPane, 0);
                    tabContainer.selectChild(companyPane);
                    construtCompanyGrid();
                    companyPaneConstruted = true;
                }
            } else {
                tabContainer.selectChild(companyPane);
            }
        };
        

        //ammeter record pane
        construtAmmeterRecordPane = function construtAmmeterRecordPane(id) {

            var construtAmmeterRecordGrid = function construtAmmeterRecordGrid() {
            		
            		var restUrl = "/ammeter_record/list";
            		
            		if(id){
            			restUrl = restUrl + "/" + id;
            		}
            		
                    ammeterRecordStore = new JsonRestStore({
                        target: restUrl
                    });
                    ammeterRecordGrid = new EnhancedGrid({
                        autoHeight: 15,
                        store: recordDataStore = ammeterRecordStore,
                        structure: [{
                            name: "电表记录编号",
                            field: "id",
                            width: "25%",
                            canSort: true
                        }, {
                            name: "电表名称",
                            field: "ammeterName",
                            width: "25%",
                            canSort: true
                        }, {
                            name: "电表数值",
                            field: "ammeterValue",
                            width: "25%",
                            canSort: true
                        }, {
                            name: "电表记录日期",
                            field: "recordDate",
                            width: "25%",
                            canSort: true
                        }, ],
                        plugins: {
                            search: true,
                            filter: true,
                            printer: true,
                            indirectSelection: {
                                headerSelector: true,
                                width: "40px",
                                styles: "text-align: center;"
                            }
                        }
                    }, "ammeter_record_grid");
                    ammeterRecordGrid.startup();
                }

            

            if (!ammeterRecordPaneConstruted) {
                if (typeof ammeterRecordPane != "undefined") {
                    tabContainer.addChild(ammeterRecordPane, 0);
                    tabContainer.selectChild(ammeterRecordPane);
                    construtAmmeterRecordGrid();
                    ammeterRecordPaneConstruted = true;
                }
            } else {
                tabContainer.selectChild(ammeterRecordPane);
            }
        };



        //ammeter pane
        construtAmmeterPane = function construtAmmeterPane(id, title) {
            
            var paneNode = registry.byId(title);

            if(paneNode){
                paneGrid = dijit.byId(title + "Grid")
                paneGrid.setQuery({"id" : "*"}); 
                //tabContainer.addChild(paneNode);
                tabContainer.selectChild(dijit.byId(paneNode));
                return ;
            }else{
                paneNode = constructNewPane(id, title, "width: 100%;", tabContainer);
                //create button node
                var addButtonNodeId = paneNode + "AddButton";
                domConstruct.place('<div id="' + addButtonNodeId + '">', paneNode, "first");


                var ammeterAddBtn = new Button({
                    label: "添加",
                    onClick: function(){
                        registry.byId("createAmmeterDialog").show();
                    }
                },addButtonNodeId);
                newProjectDialogAddBtn.startup();


                var ammeterGridId = paneNode + "Grid";
                domConstruct.place('<div id="' + ammeterGridId + '">', paneNode, "last");
                domStyle.set(dojo.byId(ammeterGridId), "height", "400px");
                constructNewGridForPane(ammeterGridId, layouts.ammeterGridLayout, "/ammeter/list/");

            }

            // var constructAmmeterOperBtns = function constructAmmeterOperBtns(){
            //     //construt add button
            //     var add_ammeter_btn = new Button({
            //         label: "新建",
            //         onClick: function() {
            //             var form_content = {
            //                 name: dom.byId("name").value,
            //                 companyName: dijit.byId("companyForAmmeter").get("value"),
            //                 projectName: dijit.byId("projectForAmmeter").get("value"),
            //                 pumpName: dom.byId("pumpName").value,
            //             };
            //             xhr.post({
            //                 form: "add_ammeter_form",
            //                 // read the url: from the action="" of the <form>
            //                 timeout: 3000,
            //                 // give up after 3 seconds
            //                 content: form_content,
            //                 handleAs: "json",
            //                 load: function(new_ammeter) {
            //                     dataStore.newItem(new_ammeter);
            //                     dataStore.save();
            //                 }
            //             });
            //         }
            //     }, "add_ammeter_btn");
            //     add_ammeter_btn.startup();

            //     //construt save button
            //     var save_button = new Button({
            //         label: "保存",
            //         onClick: function() {
            //             dataStore.save();
            //         }
            //     }, "save_button");
            //     save_button.startup();

            //     //construt delete button
            //     var delete_button = new Button({
            //         label: "删除",
            //         onClick: function() {
            //             var user_selected = ammeterGrid.selection.getSelected();
            //             if (user_selected.length) {
            //                 for (key in user_selected) {
            //                     dataStore.deleteItem(user_selected[key]);
            //                     dataStore.save();
            //                 }
            //             }
            //         }
            //     }, "delete_button");
            //     delete_button.startup();
            // };


            // var constructProjectCompanyForAmmeterCombo = function constructProjectCompanyForAmmeterCombo() {

            //     companyForAmmeterStore = new JsonRestStore({
            //         target: "/company/list/"
            //     });

            //     projectForAmmeterStore = new JsonRestStore({
            //         target: "/project/list/"
            //     });
                
            //     var projectForAmmeterCombo = new ComboBox({
            //         id: "projectForAmmeter",
            //         name: "project",
            //         value: "",
            //         store: projectForAmmeterStore,
            //         searchAttr: "projectName"
            //     }, "projectForAmmeter");

            //     var companyForAmmeterCombo = new ComboBox({
            //         id: "companyForAmmeter",
            //         name: "company",
            //         value: "",
            //         store: companyForAmmeterStore,
            //         searchAttr: "companyName"
            //     }, "companyForAmmeter");
            // };    
        };
        
        //user pane
        construtUserPane = function construtUserPane() {

            //show the user Pane
            var construtUserGrid = function construtUserGrid() {
                    userStore = new JsonRestStore({
                        target: "/user/list/"
                    });
                    userGrid = new EnhancedGrid({
                        store: userDataStore = userStore,
                        structure: [{
                            name: "用户编号",
                            field: "id",
                            height: "24px",
                            width: user_cell_width * 0.5 + "px",
                            canSort: true
                        }, {
                            name: "用户名称",
                            field: "username",
                            width: user_cell_width * 0.5 + "px",
                            editable: true
                        }, {
                            name: "用户邮箱",
                            field: "email",
                            width: user_cell_width + "px",
                            editable: true
                        }, {
                            name: "用户角色",
                            field: "roles",
                            width: user_cell_width + "px",
                            editable: true
                        }, {
                            name: "用户公司",
                            field: "company",
                            width: user_cell_width + "px",
                            editable: false
                        }, {
                            name: "用户项目",
                            field: "project",
                            width: user_cell_width + "px",
                            editable: false
                        }

                        ],
                        plugins: {
                            search: true,
                            filter: true,
                            printer: true,
                            indirectSelection: {
                                headerSelector: true,
                                width: "40px",
                                styles: "text-align: center;"
                            }
                        }
                    }, "user_grid");
                    userGrid.startup();

                    //construt add button
                    var add_user_btn = new Button({
                        label: "新建",
                        onClick: function() {
                            var form_content = {
                                username: dom.byId("username").value,
                                email: dom.byId("userEmail").value,
                                password: dom.byId("password").value,
                            };
                            xhr.post({
                                form: "add_user_form",
                                // read the url: from the action="" of the <form>
                                timeout: 3000,
                                // give up after 3 seconds
                                content: form_content,
                                handleAs: "json",
                                load: function(new_user) {
                                    userDataStore.newItem(new_user);
                                    userDataStore.save();
                                }
                            });
                        }
                    }, "add_user_btn");
                    add_user_btn.startup();

                    //construt save button
                    var save_button = new Button({
                        label: "保存",
                        onClick: function() {
                            userDataStore.save();

                        }
                    }, "user_save_button");
                    save_button.startup();

                    //construt delete button
                    var delete_button = new Button({
                        label: "删除",
                        onClick: function() {
                            var user_selected = userGrid.selection.getSelected();
                            if (user_selected.length) {
                                for (key in user_selected) {
                                    userDataStore.deleteItem(user_selected[key]);
                                    userDataStore.save();
                                }
                            }
                        }
                    }, "user_delete_button");
                    delete_button.startup();
                }

            var constructProjectCompanyForUserCombo = function constructProjectCompanyForUserCombo() {

                    companyForUserStore = new JsonRestStore({
                        target: "/company/list/"
                    });

                    projectForUserStore = new JsonRestStore({
                        target: "/project/list/"
                    });
                    
                    var projectForUserCombo = new ComboBox({
                        id: "projectForUser",
                        name: "project",
                        value: "",
                        store: projectForUserStore,
                        searchAttr: "projectName"
                    }, "projectForUser");

                    var companyForAmmeterCombo = new ComboBox({
                        id: "companyForUser",
                        name: "company",
                        value: "",
                        store: companyForUserStore,
                        searchAttr: "companyName"
                    }, "companyForUser");
                };  

            if (!userPaneConstruted) {
                if (typeof userPane != "undefined") {
                    tabContainer.addChild(userPane, 0);
                    tabContainer.selectChild(userPane);
                    construtUserGrid();
                    constructProjectCompanyForUserCombo();
                    userPaneConstruted = true;
                }
            } else {
                tabContainer.selectChild(userPane);
            }
        };


        //construtUserPane(); 

        //constuctNewPane and return the content node id
        constructNewPane = function  constructNewPane(id, title, styles, container){
            console.log(title);
            var newPane = new ContentPane({
            	id: title,
                title:title,
                content:"<div id = "+title+"></div>",
                style: styles
            });
            console.log(newPane);
            container.addChild(newPane, 0);
            container.selectChild(newPane);
            return title;
        };
        
        //create close tab button
        var constructCloseTabBtn = function constructCloseTabBtn(paneNode){
        	 var closeTabButtonNodeId = paneNode + "CloseTabBtn";
             var closeTabButtonNode = document.createElement("div");
             closeTabButtonNode.setAttribute("id", closeTabButtonNodeId);
             document.getElementById(paneNode).appendChild(closeTabButtonNode);
             var addProjectFromExistBtn = new Button({
             	label: "关闭选项卡",
             	onClick: function(){
             		tabContainer.removeChild(dijit.byId(paneNode));
             	}
             },closeTabButtonNodeId);
        };
        
        //constuctNewGrid
        constructNewGridForPane = function constructNewGridForPane(contentNode, layout, targetUrl){
        	
        	 var newStore = new JsonRestStore({
                 target: targetUrl
             });
        	 
             var newGrid = new EnhancedGrid({
                 store: newStore,
                 autoWidth: true,
                 structure: layout,
                 plugins: {
                     search: true,
                     filter: true,
                     printer: true,
                     indirectSelection: {
                         headerSelector: true,
                         width: "40px",
                         styles: "text-align: center;"
                     }
                 }
             }, contentNode);
             newGrid.startup();
             return newGrid;
        };

        //Projects Creation Dialog Events
        var selectExistingCompanyradio = dijit.byId("selectExistingCompanyradio");
        if(selectExistingCompanyradio){
        	on(selectExistingCompanyradio, "click", function(){
        		var projectCompanyCombo = registry.byId("projectCompanyCombo")
        		var projectCompanyTextBox = registry.byId("projectCompanyTextBox");
            	if(projectCompanyTextBox){
            		registry.remove("projectCompanyTextBox");
            		domConstruct.destroy("widget_projectCompanyTextBox");
            	}
            	
            	if(projectCompanyCombo){
            		registry.remove("projectCompanyCombo");
            		domConstruct.destroy("widget_projectCompanyCombo");
            	}
        
            	var projectCompanyCombo = document.getElementById("projectCompanyCombo")||document.createElement("input");
            	projectCompanyCombo.setAttribute("id", "projectCompanyCombo");
            	document.getElementById("projectCompanyLi").appendChild(projectCompanyCombo);
            	
            	if(stores&&(!stores.companyStore)){
                    stores.companyStore = new JsonRestStore({
                        target: "/company/list/"
                    });
                }
                var projectCompanyCombo = new ComboBox({
                    id: "projectCompanyCombo",
                    name: "company",
                    value: "",
                    store: stores.companyStore,
                    searchAttr: "companyName"
                }, "projectCompanyCombo");
        	});
        }
        
        var createNewCompanyRadio = dijit.byId("createNewCompanyRadio");
        if(createNewCompanyRadio){
        	on(createNewCompanyRadio, "click", function(){
        		var projectCompanyCombo = registry.byId("projectCompanyCombo");
        		var projectCompanyTextBox = registry.byId("projectCompanyTextBox");
        		
            	if(projectCompanyCombo){
            		registry.remove("projectCompanyCombo");
            		domConstruct.destroy("widget_projectCompanyCombo");
            	}
            	
            	if(projectCompanyTextBox){
            		registry.remove("projectCompanyTextBox");
            		domConstruct.destroy("widget_projectCompanyTextBox");
            	}
            	
            	var projectCompanyTextBox = document.getElementById("projectCompanyTextBox")||document.createElement("input");
            	projectCompanyTextBox.setAttribute("id", "projectCompanyTextBox");
            	document.getElementById("projectCompanyLi").appendChild(projectCompanyTextBox);
                
//            	domConstruct.create("input", {
//            		innerHTML: "Seven",
//            		className: "seven",
//            		style: {fontWeight: "bold"}
//        		}, list);
            	
            	var companyForProjectTextBox = new TextBox({
        			placeHolder: "输入公司名称"
				},"projectCompanyTextBox");
        		
        	});
        }
        
        var newProjectDialogAddBtn = dijit.byId("newProjectDialogAddBtn");
        if(newProjectDialogAddBtn){
            on(newProjectDialogAddBtn, "click", function(){
            	
            	if(registry.byId("createNewCompanyRadio").checked){
            		var companyName = registry.byId(projectCompanyTextBox).value;
            		var form_content = {
                        companyName: companyName,
                    };
                    xhr.post({
                        form: "add_company_form",
                        // read the url: from the action="" of the <form>
                        timeout: 3000,
                        // give up after 3 seconds
                        content: form_content,
                        handleAs: "json",
                        load: function(new_company) {
//                                companyDataStore.newItem(new_company);
//                                companydataStore.save();
                        }
                    });
            	}
            	
            	if(registry.byId("selectExistingCompanyradio").checked){
            		var companyName = registry.byId("projectCompanyCombo").value;            		
            	}
            	
                var form_content = {
                    projectName: dom.byId("forCompanyProjectName").value,
                    startDate: dom.byId("projectStartDate").value,
                    endDate: dom.byId("projectEndDate").value,
                    companyName: companyName,
                    electricityCharge: dom.byId("electricityCharge").value,
                    partsRatio: dom.byId("partsRatio").value
                };
                xhr.post({
                    form: "newProjectDialogForm",
                    // read the url: from the action="" of the <form>
                    timeout: 3000,
                    // give up after 3 seconds
                    content: form_content,
                    handleAs: "json",
                    load: function(new_project) {
                        console.log(new_project);

                        topic.publish("updateProject", "update");

                        if(new_project){
                            registry.byId("createProjectForCompanyDialog").hide();
                        }
                        if(projectDataStore){
                            projectDataStore.newItem(new_project);
                            projectdataStore.save();
                        }
                    }
                });
                //add ammeter if there is any ammeter to add
                if(request&&request.ammetersForProject){
                    console.log(request.ammetersForProject);
                    for(var i=0; i < request.ammetersForProject.length; i++){
                        xhr.post({
                            url: "/pa/add",
                            // read the url: from the action="" of the <form>
                            timeout: 3000,
                            // give up after 3 seconds
                            content: {
                                "projectName": dom.byId("forCompanyProjectName").value,
                                "ammeterName": request.ammetersForProject[i]
                            },
                            handleAs: "json",
                            load: function(new_project) {
                                console.log("ammeter project link added");
                                console.log(new_project);
                            }
                        });
                        
                    }
                }
                
                request.ammetersForProject = [];
                registry.byId("createProjectForCompanyDialog").hide();
            });
        }

        var addAmmeterForProjectCancelBtn = registry.byId("addAmmeterForProjectCancelBtn");
        if(addAmmeterForProjectCancelBtn){
            on(addAmmeterForProjectCancelBtn, "click", function(){
                registry.byId("AddExistingAmmeterToProjectDialog").hide();
            });
        }

        var createAmmeterDialogCancelBtn = registry.byId("createAmmeterDialogCancelBtn");
        if(createAmmeterDialogCancelBtn){
            on(createAmmeterDialogCancelBtn, "click", function(){
                registry.byId("createAmmeterDialog").hide();
            });
        }

        var newProjectDialogCancelBtn = dijit.byId("newProjectDialogCancelBtn");
        if(newProjectDialogCancelBtn){
            on(newProjectDialogCancelBtn, "click", function(){
                registry.byId("createProjectForCompanyDialog").hide();
            });
        }

        var addAmmeterForProjectBtn = registry.byId("addAmmeterForProjectBtn");
        if(addAmmeterForProjectBtn){
        	on(addAmmeterForProjectBtn, "click", function(){
        		dojo.byId("ammeterProjectLi").style.display="none";
        		registry.byId("createAmmeterDialog").show();
        	})
        }
        
        var addNewAmmeterForNewCreatingProjectBtn = registry.byId("addNewAmmeterForNewCreatingProjectBtn");
        if(addNewAmmeterForNewCreatingProjectBtn){
        	on(addNewAmmeterForNewCreatingProjectBtn, "click", function(){
        		dojo.byId("ammeterProjectLi").style.display="none";
        		registry.byId("createAmmeterDialog").show();
        	})
        }
        
        var addExistingAmmeterForProjectBtn = registry.byId("addExistingAmmeterForProjectBtn");
        if(addExistingAmmeterForProjectBtn){
            on(addExistingAmmeterForProjectBtn, "click", function(){
                xhr.get({
                    url: "/ammeter/list",
                    timeout: 3000,
                    // give up after 3 seconds
                    handleAs: "json",
                    load: function(ammeterList) {
                        domConstruct.empty("ExstingAmmeterMultiSelect");
                        for(var ammeterKey in ammeterList){
                            
                            var option = domConstruct.create("option", {
                                innerHTML: ammeterList[ammeterKey].name,
                                className: "seven",
                                style: {fontWeight: "bold"}
                            });

                            dom.byId("ExstingAmmeterMultiSelect").appendChild(option);
                        }
                        var AddExistingAmmeterToProjectDialog = registry.byId("AddExistingAmmeterToProjectDialog");
                        AddExistingAmmeterToProjectDialog.show();
                    }
                });
                
            });
        }

        var AmmeterMultiSelectRightBtn = registry.byId("AmmeterMultiSelectRightBtn");
        if(AmmeterMultiSelectRightBtn){
            on(AmmeterMultiSelectRightBtn, "click", function(){
                registry.byId("AddedAmmeterMultiSelect").addSelected(registry.byId("ExstingAmmeterMultiSelect"));
            });
        }

        var AmmeterMultiSelectLeftBtn = registry.byId("AmmeterMultiSelectLeftBtn");
        if(AmmeterMultiSelectLeftBtn){
            on(AmmeterMultiSelectLeftBtn, "click", function(){
                registry.byId("ExstingAmmeterMultiSelect").addSelected(registry.byId("AddedAmmeterMultiSelect"));
            });
        }

        var addAmmeterForProjectSubmitBtn = registry.byId("addAmmeterForProjectSubmitBtn");
        if(addAmmeterForProjectSubmitBtn){
            on(addAmmeterForProjectSubmitBtn, "click", function(){
            	request.ammetersForProject = [];
                console.log(dom.byId("AddedAmmeterMultiSelect").childNodes);
                var ammetersToAddOptions = dom.byId("AddedAmmeterMultiSelect").childNodes;
                console.log(ammetersToAddOptions);
                if(ammetersToAddOptions&&(ammetersToAddOptions.length > 0)){
                    for(var i = 0; i < ammetersToAddOptions.length; i++){
                        console.log(ammetersToAddOptions[i])
                        console.log(ammetersToAddOptions[i].innerHTML);
                        request = request || {};
                        request.ammetersForProject = request.ammetersForProject || [];
                        if(ammetersToAddOptions[i].innerHTML){
                            request.ammetersForProject.push(ammetersToAddOptions[i].innerHTML);   
                        }
                    }
                }

                topic.publish("updateProjectAmmeter", "update");
                registry.byId("AddExistingAmmeterToProjectDialog").hide();
            });
        }
        //Ammeter Creation Dialog Events
        var createAmmeterDialogAddBtn = registry.byId("createAmmeterDialogAddBtn");
        if(createAmmeterDialogAddBtn){
            on(createAmmeterDialogAddBtn, "click", function(){
            	var ammeterName = dom.byId("ammeterName").value;
                var form_content = {
                    name: dom.byId("ammeterName").value,
                    pumpName: dom.byId("pumpName").value,
                    projectName: dom.byId("ammeterProject").value,
                    sensorRate: dom.byId("sensorRate").value,
                    formerCost: dom.byId("formerCost").value,
                    upperLimit: dom.byId("upperLimit").value,
                    lowerLimit: dom.byId("lowerLimit").value
                };
                xhr.post({
                    form: "createAmmeterDialogForm",
                    // read the url: from the action="" of the <form>
                    timeout: 3000,
                    // give up after 3 seconds
                    content: form_content,
                    handleAs: "json",
                    load: function(newAmmeter) {

                        topic.publish("updateAmmeter", newAmmeter.name);

                        if(newAmmeter){
                            registry.byId("createAmmeterDialog").hide();
                        }
                        if(projectDataStore){
                            if(stores.ammeterStore){
                                stores.ammeterStore.newItem(newAmmeter);
                                stores.ammeterStore.save();
                            }
                        }
                        registry.byId(createAmmeterDialog).hide();
                    }
                });
            });
        }

        //save computation events
        var saveComputationBtn = registry.byId("saveComputationBtn");
        if(saveComputationBtn){
            on(saveComputationBtn, "click", function(){
                var saveComputationStartDate = dom.byId("saveComputationStartDate").value;
                var saveComputationEndDate = dom.byId("saveComputationEndDate").value;
                var targetUrl = "/saveComputation/list/startDate/saveComputationStartDate/endDate/saveComputationEndDate";
                
                stores.constructSaveComputationStore = new JsonRestStore({
                    target: targetUrl
                });

                saveComputationGrid.setQuery({
                    "startDate" : saveComputationStartDate,
                    "endDate": saveComputationEndDate
                }); 


            });
        }

        //deal with Menu active
        var activeMenuItem = function activeMenuItem(menuItem){
        	if(activedMenuItem){
        		domClass.remove(activedMenuItem, "active");
        	}
        	activedMenuItem = menuItem;
        	domClass.add(activedMenuItem,"active");
        };
        
        var ammeterMenuItem = dojo.byId("ammeterMenuItem");
        if(ammeterMenuItem){
            on(ammeterMenuItem, "click", function(){
                var ammeterMenuItemBtn = dojo.byId("ammeterMenuItemBtn");
                activeMenuItem(ammeterMenuItemBtn);
                construtAmmeterPane("ammeterPane", "电表管理"); 
            });
        }
        
        
        var upMenuItem = dojo.byId("upMenuItem");
        if(upMenuItem){
            on(upMenuItem, "click", function(){
                var upMenuItemBtn = dojo.byId("upMenuItemBtn");
                activeMenuItem(upMenuItemBtn);
                construtProjectPane();
            });
        }
        
        
        var uaMenuItem = dojo.byId("uaMenuItem");
        if(uaMenuItem){
            on(uaMenuItem, "click", function(){
                var uaMenuItemBtn = dojo.byId("uaMenuItemBtn");
                activeMenuItem(uaMenuItemBtn);
                construtAmmeterPane("ammeterPane", "电表管理");
            });
        }
        
        var ammeterRecordMenuItem = dojo.byId("ammeterRecordMenuItem");
        if(ammeterRecordMenuItem){
            on(ammeterRecordMenuItem, "click", function(){
                var ammeterRecordMenuItemBtn = dojo.byId("ammeterRecordMenuItemBtn");
                activeMenuItem(ammeterRecordMenuItemBtn);
                construtAmmeterRecordPane();
            });
        }
        
        var projectMenuItem = dojo.byId("projectMenuItem");
        if(projectMenuItem){
            on(projectMenuItem, "click", function(){
                var projectMenuItemBtn = dojo.byId("projectMenuItemBtn");
                activeMenuItem(projectMenuItemBtn);
                construtProjectPane();
            });

        }

        var createProjectGuideMenuItem = dojo.byId("createProjectGuideMenuItem");
        if(createProjectGuideMenuItem){
            on(createProjectGuideMenuItem, "click", function(){
                if(request){
                    request.ammetersForProject = [];
                }
                var createProjectGuideBtn = dojo.byId("createProjectGuideMenuItem");
                activeMenuItem(createProjectGuideBtn);
                var projectCompanyCombo = registry.byId("projectCompany");
                
                if(registry.byId("createNewCompanyRadio").checked){
                    var projectCompanyCombo = registry.byId("projectCompanyCombo");
                    var projectCompanyTextBox = registry.byId("projectCompanyTextBox");
                    
                    if(projectCompanyCombo){
                        registry.remove("projectCompanyCombo");
                        domConstruct.destroy("widget_projectCompanyCombo");
                    }
                    
                    if(projectCompanyTextBox){
                        
                        registry.remove("projectCompanyTextBox");
                        domConstruct.destroy("widget_projectCompanyTextBox");
                    }
                    
                    var projectCompanyTextBox = document.getElementById("projectCompanyTextBox")||document.createElement("input");
                    projectCompanyTextBox.setAttribute("id", "projectCompanyTextBox");
                    document.getElementById("projectCompanyLi").appendChild(projectCompanyTextBox);
                    
                    
                    var companyForProjectTextBox = new TextBox({
                        placeHolder: "输入公司名称"
                    },"projectCompanyTextBox");
                }
                
                if(registry.byId("selectExistingCompanyradio").checked){
                    var projectCompanyCombo = registry.byId("projectCompanyCombo")
                    var projectCompanyTextBox = registry.byId("projectCompanyTextBox");
                    if(projectCompanyTextBox){
                        registry.remove("projectCompanyTextBox");
                        domConstruct.destroy("widget_projectCompanyTextBox");
                    }
                    
                    if(projectCompanyCombo){
                        registry.remove("projectCompanyCombo");
                        domConstruct.destroy("widget_projectCompanyCombo");
                    }
            
                    var projectCompanyCombo = document.getElementById("projectCompanyCombo")||document.createElement("input");
                    projectCompanyCombo.setAttribute("id", "projectCompanyCombo");
                    document.getElementById("projectCompanyLi").appendChild(projectCompanyCombo);
                    
                    if(stores&&(!stores.companyStore)){
                        stores.companyStore = new JsonRestStore({
                            target: "/company/list/"
                        });
                    }
                    var projectCompanyCombo = new ComboBox({
                        id: "projectCompanyCombo",
                        name: "company",
                        value: "",
                        store: stores.companyStore,
                        searchAttr: "companyName"
                    }, "projectCompanyCombo");
                }
                var createProjectForCompanyDialog = registry.byId("createProjectForCompanyDialog");
                if(createProjectForCompanyDialog){
                    dojo.byId("createProjectDialogHiddenBtn").style.display="inline";                
                    createProjectForCompanyDialog.show();
                }
            });
        }

        var paMenuItem = dojo.byId("paMenuItem")
        if(paMenuItem){
            on(paMenuItem, "click", function(){
                var paMenuItemBtn = dojo.byId("paMenuItemBtn")
                activeMenuItem(paMenuItemBtn);
                construtPAPane();
            });
        }
        
        var puMenuItem = dojo.byId("puMenuItem");
        if(puMenuItem){
            on(puMenuItem, "click", function(){
                var puMenuItemBtn = dojo.byId("puMenuItemBtn");
                activeMenuItem(puMenuItemBtn);
                construtUPPane();
            });
        }
        
        
        var saveComputationMenuItem = dojo.byId("saveComputationMenuItem");
        if(saveComputationMenuItem){
            on(saveComputationMenuItem, "click", function(){
                var saveComputationMenuItemBtn = dojo.byId("saveComputationMenuItemBtn");
                activeMenuItem(saveComputationMenuItemBtn);
                constructSaveComputationPane();
            });
        }
        
        var companyMenuItem = dojo.byId("companyMenuItem");
        if(companyMenuItem){
            on(companyMenuItem, "click", function(){
                var companyMenuItemBtn = dojo.byId("companyMenuItemBtn");
                activeMenuItem(companyMenuItemBtn);
                construtCompanyPane();
            });
        }
        
        var cpMenuItem = dojo.byId("cpMenuItem");
        if(cpMenuItem){
            on(cpMenuItem, "click", function(){
                var cpMenuItemBtn = dojo.byId("cpMenuItemBtn");
                activeMenuItem(cpMenuItemBtn);
                construtCPPane();
            });
        }
        
        var userMenuItem = dojo.byId("userMenuItem");
        if(userMenuItem){
            on(userMenuItem, "click", function(){
                var userMenuItemBtn = dojo.byId("userMenuItemBtn");
                activeMenuItem(userMenuItemBtn);
                construtUserPane();
            });
        }
        
        var LastAmmeterMenuItem = dojo.byId("LastAmmeterMenuItemBtn");
        if(LastAmmeterMenuItem){
            on(LastAmmeterMenuItem ,"click", function(){
                var lastAmmeterMenuItemBtn = dojo.byId("LastAmmeterMenuItemBtn");
                activeMenuItem(lastAmmeterMenuItemBtn);
                constructLastAmmeterStatusPane();
            }); 
        }

        topic.subscribe("updateAmmeter", function(text){
        	console.log(text);
        	var option = domConstruct.create("option", {
                innerHTML: text,
                className: "seven",
                style: {fontWeight: "bold"}
            });

            dom.byId("AddedAmmeterMultiSelect").appendChild(option);
        });
        
        topic.subscribe("updateProjectAmmeter", function(text){
        	console.log("start updateProjectAmmeter");
        	var ammeterAddedMultiSelect = dom.byId("ammeterAddedMultiSelect");
        	domConstruct.empty(ammeterAddedMultiSelect);
        	console.log(request.ammetersForProject);
        	for(var i=0; i<request.ammetersForProject.length; i++){
        		console.log(request.ammetersForProject[i]);
        		var option = domConstruct.create("option", {
                    innerHTML: request.ammetersForProject[i],
                    className: "seven",
                    style: {fontWeight: "bold"}
                });

        		ammeterAddedMultiSelect.appendChild(option);
        	}
        	
        	
        });

        constructLastAmmeterStatusPane();
    });
});

