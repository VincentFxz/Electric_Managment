var userPane;
var construtUserPane;

var ammeterPane;
var construtAmmeterPane;

require(["dojox/data/JsonRestStore", "dojo/store/Memory", "dojo/store/Cache", "dojox/grid/EnhancedGrid", "dojo/data/ObjectStore", "dojo/query", "dojo/ready", "dijit/form/TextBox", "dijit/form/Button", "dijit/Menu", "dijit/MenuItem", "dijit/form/ComboButton", "dojo/dom", "dojo/_base/xhr", "dojo/i18n!/dojo-release-1.7.2/dojox/grid/enhanced/nls/zh/Filter.js", "dojox/grid/enhanced/plugins/Search", "dojox/grid/enhanced/plugins/Filter", "dojo/parser", "dijit/layout/TabContainer", "dijit/layout/ContentPane", "dijit/form/DropDownButton", "dijit/TooltipDialog", "dijit/form/TextBox", "dojox/grid/enhanced/plugins/IndirectSelection", "dojox/grid/enhanced/plugins/Printer", "dojo/domReady!"], function(JsonRestStore, Memory, Cache, EnhancedGrid, ObjectStore, query, ready, TextBox, Button, Menu, MenuItem, ComboButton, dom, xhr, i18n) {

    ready(function() {
        //hidden the user ContentPane
        userPane = dijit.byId("userPane");
        ammeterPane = dijit.byId("ammeterPane");
        dijit.byId("tab_container").removeChild(dijit.byId("userPane"));
        dijit.byId("tab_container").removeChild(dijit.byId("ammeterPane"));

        console.log(dojo.style("main_container", "width"));

        var ammeter_cell_width = dojo.style("main_container", "width") * 0.18;
        var ammeter_record_width = dojo.style("main_container", "width") * 0.24;

        ammeterRecordStore = new JsonRestStore({
            target: "/ammeter_record/list "
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



        construtAmmeterPane = function construtAmmeterPane() {
            console.log("lalala");

            if(typeof ammeterPane != "undefined"){
                dijit.byId("tab_container").addChild(ammeterPane);
            }
            ammeterStore = new JsonRestStore({
                target: "/ammeter/list/"
            });
            ammeterGrid = new EnhancedGrid({
                store: dataStore = ammeterStore,
                autoWidth: true,
                structure: [{
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
                }, {
                    name: "项目名称",
                    field: "projectName",
                    width: ammeter_cell_width + "px",
                    editable: true
                }, {
                    name: " 公司名称",
                    field: "companyName",
                    width: ammeter_cell_width + "px",
                    editable: true
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
            }, "ammeter_grid");
            ammeterGrid.startup();

            //construt add button
            var add_ammeter_btn = new Button({
                label: "新建",
                onClick: function() {
                    var form_content = {
                        name: dom.byId("name").value,
                        companyName: dom.byId("companyName").value,
                        projectName: dom.byId("projectName").value,
                        pumpName: dom.byId("pumpName").value,
                    };
                    xhr.post({
                        form: "add_ammeter_form",
                        // read the url: from the action="" of the <form>
                        timeout: 3000,
                        // give up after 3 seconds
                        content: form_content,
                        handleAs: "json",
                        load: function(new_ammeter) {
                            dataStore.newItem(new_ammeter);
                            dataStore.save();
                        }
                    });
                }
            }, "add_ammeter_btn");
            add_ammeter_btn.startup();

            //construt save button
            var save_button = new Button({
                label: "保存",
                onClick: function() {
                    console.log("sava clicked");
                    dataStore.save();

                }
            }, "save_button");
            save_button.startup();

            //construt delete button
            var delete_button = new Button({
                label: "删除",
                onClick: function() {
                    console.log("delete");
                    var ammeters_selected = ammeterGrid.selection.getSelected();
                    if (ammeters_selected.length) {
                        for (key in ammeters_selected) {
                            console.log(ammeters_selected[key]);
                            dataStore.deleteItem(ammeters_selected[key]);
                            dataStore.save();
                        }
                    }
                }
            }, "delete_button");
            delete_button.startup();
        }

        construtUserPane = function construtUserPane() {
            //show the user Pane
            if (typeof userPane != "undefined") {
                dijit.byId("tab_container").addChild(userPane, 0);
            }
            userStore = new JsonRestStore({
                target: "/user/list/"
            });
            console.log(userStore);
            userGrid = new EnhancedGrid({
                store: userDataStore = userStore,
                structure: [{
                    name: "用户编号",
                    field: "id",
                    height: "24px",
                    width: "25%",
                    canSort: true
                }, {
                    name: "用户名称",
                    field: "username",
                    width: "25%",
                    editable: true
                }, {
                    name: "用户邮箱",
                    field: "email",
                    width: "25%",
                    editable: true
                }, {
                    name: "用户角色",
                    field: "roles",
                    width: "25%",
                    editable: true
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
                            console.log(new_user);
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
                    var ammeters_selected = ammeterGrid.selection.getSelected();
                    if (ammeters_selected.length) {
                        for (key in ammeters_selected) {
                            console.log(ammeters_selected[key]);
                            userDataStore.deleteItem(ammeters_selected[key]);
                            userDataStore.save();
                        }
                    }
                }
            }, "user_delete_button");
            delete_button.startup();

        }


        //construtUserPane();   
    });
});

function showAmmeterMan(){
    construtAmmeterPane();
}

function showUserMan() {
    construtUserPane();
}