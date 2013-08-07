<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>劲力节能</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Le styles -->

     <!--
        @import "/dojo-release-1.7.2/dojo/resources/dojo.css";
        @import "/dojo-release-1.7.2/dijit/themes/dijit.css";
        @import "/dojo-release-1.7.2/dijit/themes/claro/claro.css";
        @import "/dojo-release-1.7.2/dojox/grid/resources/claroGrid.css";
        @import "/dojo-release-1.7.2/dojox/grid/enhanced/resources/claro/EnhancedGrid.css";
        @import "/dojo-release-1.7.2/dojox/grid/enhanced/resources/tundra/EnhancedGrid.css";
        @import "/dojo-release-1.7.2/dojox/grid/enhanced/resources/EnhancedGrid_rtl.css";
        @import "/dojo-release-1.7.2/dojox/grid/enhanced/resources/EnhancedGrid_rtl.css";
    -->
    <link href = "/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href = "/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
    <style type = "text/css">
        @import "/dojo1.7src/dojo/resources/dojo.css";
        @import "/dojo1.7src/dijit/themes/dijit.css";
        @import "/dojo1.7src/dijit/themes/claro/claro.css";
        @import "/dojo1.7src/dojox/grid/resources/claroGrid.css";
        @import "/dojo1.7src/dojox/grid/enhanced/resources/claro/EnhancedGrid.css";
        @import "/dojo1.7src/dojox/grid/enhanced/resources/tundra/EnhancedGrid.css";
        @import "/dojo1.7src/dojox/grid/enhanced/resources/EnhancedGrid_rtl.css";
        @import "/dojo1.7src/dojox/grid/enhanced/resources/EnhancedGrid_rtl.css";

        body {
            padding-top: 60px;
            padding-bottom: 40px;

        }

        .dialogLabel{
            float:left;
            width: 8em;
            margin-left: 3em;
            margin-right: 1em;
        }

        body .short {
            width: 5em;
        }
        body .medium {
            width: 10em;
        }
        body .long {
            width: 20em;
        }

        #ammeter_grid  {
            height:400px;
            width: 100%
        }

        div .sel1 {
            float: left; 
        }

        div .sel2 {
            float:left;
        }

        div .leftRightButtons {
            float: left;
            padding: 10em 0.5em 0 0.5em;
        }

        .clear {
          clear: both;
        }



    </style>

    

    <script src = "/dojo1.7src/dojo/dojo.js" 
        data-dojo-config="
            async:true,
            parseOnLoad:true,
            locale:'zh-cn'">
    </script>
        <!--
    <script src ="//ajax.googleapis.com/ajax/libs/dojo/1.8.1/dojo/dojo.js" 
        data-dojo-config="
            async:true,
            parseOnLoad:true,
            locale:'zh-cn'">
    </script>
    -->
    
    <script language="javascript" src="/js/index_dev.js">  </script>
  </head>

  <body class = "claro">
    <div class="navbar navbar-fixed-top">
        <div class="navbar-inner">
            <div class="container">
                <a class="btn btn-navbar" data-toggle = "collapse" data-target = ".nav-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                </a>
                <a class="brand" href="#">劲力节能</a>
                <div class="nav-collapse">
                <ul class="nav">
                    <li class="active"><a href="#">返回主页</a></li>
                    <!--
                    
                    <li><a href="#">关于我们</a></li>
                    <li><a href="#">联系方式</a></li>
                -->
                </ul>
                <div class="btn-group pull-right">
                    <a id="userStatusBtn" class="btn dropdown-toggle" data-toggle="dropdown" href="#" >
                    <i class="icon-user"></i> <shiro:principal />
                    <span class="caret"></span>
                    </a>
                    <ul id="userDropDown" class="dropdown-menu">
                        <li><a href="/logout">切换用户</a></li>
                        <li class="divider"></li>
                        <li><a href="/logout">退出</a></li>
                    </ul>
                </div>
            </div><!-- /.nav-collapse -->
          </div>
        </div><!-- /navbar-inner -->
    </div>
    <div class="container-fluid">
        <div class="row-fluid">
            <div class="span3">
                <div class="well sidebar-nav">
                    <ul class="nav nav-list">
                        <li class="nav-header">个人管理</li>
                        <li id="upMenuItemBtn"><a id="upMenuItem" href="#">我的项目</a></li>
                        <li id="uaMenuItemBtn"><a id="uaMenuItem" href="#">我的电表</a></li>
                        <li id="LastAmmeterMenuItemBtn"><a id="LastAmmeterMenuItem" href="#">电表最新数据</a></li>
                        <li id="saveComputationMenuItemBtn"><a id="saveComputationMenuItem" href="#">节能核算表</a></li>
                        <li id="saveComputationChartMenuItemBtn"><a id="saveComputationChartMenuItem" href="#">节能核算表(趋势图)</a></li>

                        <shiro:hasRole name="admin">
                        <li class="nav-header">项目管理</li>
                        <li id="createProjectGuideBtn"><a id="createProjectGuideMenuItem" href="#" >新项目向导</a></li>
                        <li id="projectMenuItemBtn"><a id="projectMenuItem" href="#" >项目管理</a></li>
                        <li id = "paMenuItemBtn"><a id="paMenuItem" href="#" >项目电表管理</a></li>
                        <li id="puMenuItemBtn" ><a id="puMenuItem" href="#" >项目用户管理</a></li>

                        <li class="nav-header">GPRS管理</li>
                        <li id="gprsMenuItemBtn"><a id="gprsMenuItem" href="#" >GPRS管理</a></li>
                        <li id="ammeterGprsMenuItemBtn"><a id="ammeterGprsMenuItem" href="#" >GPRS电表管理</a></li>

                        <li class="nav-header">电表管理</li>
                        <shiro:hasRole name="admin">  
                        <li id="ammeterMenuItemBtn"><a id="ammeterMenuItem" href="#">电表管理</a></li>
                        </shiro:hasRole> 
                        <li id="ammeterRecordMenuItemBtn" ><a id="ammeterRecordMenuItem" href="#">电表记录管理</a></li>

                        <li class="nav-header">公司管理</li>
                        <li id="companyMenuItemBtn"><a id="companyMenuItem" href="#">公司管理</a></li>
                        <li id="cpMenuItemBtn"><a id="cpMenuItem" href="#">公司项目管理</a></li>
                        <!-- <li><a onclick = "showCompanyMan()" href="#">公司电表管理</a></li>  -->
                        <li class="nav-header">权限管理</li>
                        <li id="userMenuItemBtn"><a id="userMenuItem" href="#" >权限管理</a></li>
                        </shiro:hasRole>
                    </ul>
                </div><!--/.well -->
            </div><!--/.span -->
            <div id ="main_container" class="span9">
                <div class="claro">
                    <div id="tab_container" data-dojo-type="dijit.layout.TabContainer" doLayout="false" style="width:98%;height:85%;">
                        <div id = "ammeterRecordPane" data-dojo-type="dijit.layout.ContentPane" title="电表纪录管理" >
                            <br />
                            <div id="ammeter_record_grid" class="claro" style="height:473px"></div>   
                        </div>
                        
                        

                        <div id = "cpPane" data-dojo-type="dijit.layout.ContentPane" title="公司项目管理">
                            <div id = "cp_grid_container" style = "width: 100%;" class = "claro">
                                <div id="search_var">
                                    <div data-dojo-type="dijit.form.DropDownButton">
                                        <span>新建</span>
                                        <div data-dojo-type="dijit.TooltipDialog" id="new_cp_dialog">
                                            <form id="add_cp_form" method="post" action="/cp/add">
                                            <div>
                                                <strong><label class="add_form_label" for="companyForCP">公司名称</label></strong>
                                                <input id = "companyForCP" />
                                            </div>
                                            <div>
                                                <strong><label class="add_form_label" for="projectForCP">项目名称</label></strong>
                                                <input id = "projectForCP" />
                                            </div>
                                            <div>
                                                <button id="add_cp_btn"></button>
                                            </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                                <!-- The Data Grid -->
                                <br />
                                <div id="cp_grid" class="claro" style="height:400px"></div>
                                <!-- The Action Bar -->
                                <div id="modify_bar" class="claro">
                                    <button id="cp_save_button"></button>
                                    <button id="cp_delete_button"></button>
                                </div>
                            </div>
                        </div>
                        <!-- /cp pane -->

                        <div id = "paPane" data-dojo-type="dijit.layout.ContentPane" title="项目电表管理">
                            <div id = "pa_grid_container" style = "width: 100%;" class = "claro">
                                <shiro:hasRole name="admin">
                                    <div data-dojo-type="dijit.form.DropDownButton">
                                        <span>新建</span>
                                        <div data-dojo-type="dijit.TooltipDialog" id="new_pa_dialog">
                                            <form id="add_pa_form" method="post" action="/pa/add">
                                            <div>
                                                <strong><label class="add_form_label" for="projectForPACombo">项目名称</label></strong>
                                                <input id="projectForPACombo" />
                                            </div>
                                            <div>
                                                <strong><label class="add_form_label" for="ammeterForPACombo">电表名称</label></strong>
                                                <input id="ammeterForPACombo" />
                                            </div>
                                            <div>
                                                <button id="add_pa_btn"></button>
                                            </div>
                                            </form>
                                        </div>
                                    </div>
                                </shiro:hasRole>    
                                <!-- The Data Grid -->
                                <br />
                                <div id="pa_grid" class="claro" style="height:400px"></div>
                                <!-- The Action Bar -->
                                <div id="modify_bar" class="claro">
                                    <button id="pa_save_button"></button>
                                    <button id="pa_delete_button"></button>
                                </div>
                            </div>
                        </div>
                        <!-- /pa pane -->

                        <div id = "upPane" data-dojo-type="dijit.layout.ContentPane" title="项目电表管理">
                            <div id = "up_grid_container" style = "width: 100%;" class = "claro">
                                <shiro:hasRole name="admin">
                                    <div data-dojo-type="dijit.form.DropDownButton">
                                        <span>新建</span>
                                        <div data-dojo-type="dijit.TooltipDialog" id="new_up_dialog">
                                            <form id="add_up_form" method="post" action="/up/add">
                                            <div>
                                                <strong><label class="add_form_label" for="upProjectName">项目名称</label></strong>
                                                <input id="projectForUPCombo" />
                                            </div>
                                            <div>
                                                <strong><label class="add_form_label" for="upAmmeterName">用户名称</label></strong>
                                                <input id="userForUPCombo" />
                                            </div>
                                            <div>
                                                <button id="add_up_btn"></button>
                                            </div>
                                            </form>
                                        </div>
                                    </div>
                                </shiro:hasRole>    
                                <!-- The Data Grid -->
                                <br />
                                <div id="up_grid" class="claro" style="height:400px"></div>
                                <!-- The Action Bar -->
                                <div id="modify_bar" class="claro">
                                    <button id="up_delete_button"></button>
                                </div>
                            </div>
                        </div>
                        <!-- /up upne -->
                        <div id = "agPane" data-dojo-type="dijit.layout.ContentPane" title="电表GPRS管理">
                            <div id = "ag_grid_container" style = "width: 100%;" class = "claro">
                                <shiro:hasRole name="admin">
                                    <div data-dojo-type="dijit.form.DropDownButton">
                                        <span>新建</span>
                                        <div data-dojo-type="dijit.TooltipDialog" id="new_ag_dialog">
                                            <form id="add_ag_form">
                                            <div>
                                                <strong><label class="add_form_label" for="ammeterForAGCombo">电表名</label></strong>
                                                <input id="ammeterForAGCombo" />
                                            </div>
                                            <div>
                                                <strong><label class="add_form_label" for="gprsForAGCombo">gprs名</label></strong>
                                                <input id="gprsForAGCombo" />
                                            </div>
                                            <div>
                                                <button id="add_ag_btn"></button>
                                            </div>
                                            </form>
                                        </div>
                                    </div>
                                </shiro:hasRole>    
                                <!-- The Data Grid -->
                                <br />
                                <div id="ag_grid" class="claro" style="height:400px"></div>
                                <!-- The Action Bar -->
                                <div id="modify_bar" class="claro">
                                    <button id="ag_delete_button"></button>
                                </div>
                            </div>
                        </div>

                        <div id = "userPane" data-dojo-type="dijit.layout.ContentPane" title = "用户管理">
                            <div id="search_var">
                                <!--
                                <div data-dojo-type="dijit.form.DropDownButton">
                                    <span>新建</span>
                                    
                                    <div data-dojo-type="dijit.TooltipDialog" id="new_user_dialog">
                                        <form id="add_user_form" method="post" action="/user/add">
                                        <div>
                                            <strong><label class="add_form_label" for="username">用户名称</label></strong>
                                            <div data-dojo-type="dijit.form.TextBox" id="username"></div>
                                        </div>
                                        <div>
                                            <strong><label class="add_form_label" for="userEmail">用户邮箱</label></strong>
                                            <div data-dojo-type="dijit.form.TextBox" id="userEmail"></div>
                                        </div>
                                        <div>
                                            <strong><label class="add_form_label" for="password">默认密码</label></strong>
                                            <div data-dojo-type="dijit.form.TextBox" id="password"></div>
                                        </div>
                                        <div>
                                            <strong><label class="add_form_label" for="companyForUser">公司</label></strong>
                                            <input id = "companyForUser" />
                                        </div>
                                        <div>
                                            <strong><label class="add_form_label" for="projectForUser">项目</label></strong>
                                            <input id = "projectForUser" />
                                        </div>
                                        <div>
                                            <button id="add_user_btn"></button>
                                        </div>
                                        </form>
                                    </div>
                                    
                                </div>
                                -->
                                <button id="showCreateUserDialogBtn" style="margin-left:1em;" data-dojo-type="dijit.form.Button">
                                            新建用户
                                </button>
                                <div class="dijitHidden">

                                </div>
                            </div>
                            <!-- The Data Grid -->
                            <br />
                            <div id="user_grid" class="claro" style="height:400px"></div>
                            <!-- The Action Bar -->
                            <div id="user_modify_bar" class="claro">
                                <button id="user_save_button"></button>
                                <button id="user_delete_button"></button>
                            </div>
                        </div>
                        <!--/user pane-->

                        <div id = "companyPane" data-dojo-type="dijit.layout.ContentPane" title = "公司管理">
                            <shiro:hasRole name="admin">
                            <button id="showCreateCompanyDialogBtn" style="margin-left:1em;" data-dojo-type="dijit.form.Button">
                                            新建公司
                            </button>
                            </shiro:hasRole>
                            <!-- The Data Grid -->
                            <br />
                            <div id="company_grid" class="claro" style="height:400px"></div>
                            <!-- The Action Bar -->
                            <div id="company_modify_bar" class="claro">
                                <button id="saveCompanyBtn" data-dojo-type="dijit.form.Button">
                                        保存
                                </button>
                                <button id="deleteCompanyBtn" data-dojo-type="dijit.form.Button">
                                        删除
                                </button>
                            </div>
                        </div>    
                        <!--/company pane-->

                        <div id = "projectPane" data-dojo-type="dijit.layout.ContentPane" title = "项目管理">
                            <shiro:hasRole name="admin">
                            <div data-dojo-type="dijit.form.DropDownButton">
                                <span>新建</span>
                                <div data-dojo-type="dijit.TooltipDialog" id="new_project_dialog">
                                    
                                    <form id="add_project_form" method="post" action="/project/add">
                                        <div>
                                            <strong><label class="add_form_label" for="addProjectName">项目名称</label></strong>
                                            <div data-dojo-type="dijit.form.TextBox" id="addProjectName"></div>
                                        </div>
                                        <div>
                                            <button id="add_project_btn"></button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                            </shiro:hasRole>
                            <!-- The Data Grid -->
                            <br />
                            <div id="project_grid" class="claro" style="height:400px"></div>
                            <shiro:hasRole name="admin">
                            <!-- The Action Bar -->
                            <div id="project_modify_bar" class="claro">
                                <button id="project_save_button"></button>
                                <button id="project_delete_button"></button>
                            </div>
                            </shiro:hasRole>
                        </div> 
                        <!--project Pane-->
                        <div id = "saveComputationPane" data-dojo-type="dijit.layout.ContentPane" title = "节能核算表" style="overflow:auto;">
                            <div class="dijitDialogPaneActionBar" style ="text-align: left;">
                                <div style="float: left;">
                                    <label class="dialogLabel"  for="saveComputationStartDate">结算开始时间:</label>
                                    <input id="saveComputationStartDate" dojoType="dijit.form.DateTextBox" required=true/>
                                </div>
                                <div style="float: left;">
                                    <label class="dialogLabel" for="saveComputationEndDate">结算结束时间:</label>
                                    <input id="saveComputationEndDate" dojoType="dijit.form.DateTextBox" required=true/>
                                </div>
                                <div style="float: left;">
                                    <label class="dialogLabel" for="saveComputationAmmeter">电表:</label>
                                    <input id="saveComputationAmmeter" required=true/>
                                </div>
                                <div style="float:left;">
                                    <button id="saveComputationBtn" style="margin-left:1em;" data-dojo-type="dijit.form.Button">
                                            开始结算
                                    </button>
                                </div>

                                <div class="clear"></div>
                            </div>
                            <!-- The Data Grid -->
                            <br />
                            <div id="saveComputationGrid" class="claro" style="height:400px"></div>
                            <!-- The Action Bar -->
                        </div>

                        <div id = "saveComputationChartPane" data-dojo-type="dijit.layout.ContentPane" title = "节能核算表(趋势图)" style="overflow:auto;">
                            
                            
                            <br />
                            <div style = "text-align : left";>
                                <div style="float:left">
                                    <div id="vertical" style="height:400px;float:left"></div>
                                </div>
                                <div style="float:left">
                                    <div id="saveComputationChart" class="claro" style="height:400px;width:800px"></div>
                                    <div style="clear:both;"></div>
                                </div>
                                <div class="clear"></div>


                            </div>
                            
                            <div id="legend"></div>
                            <!-- The Action Bar -->
                        </div>

                        <div id = "lastAmmeterStatusPane" data-dojo-type="dijit.layout.ContentPane" title = "电表最新数据" style="overflow:auto;">
                            <!-- The Data Grid -->
                            <br>
                            <div id="lastAmmeterStatusGrid" class="claro" style="height:400px; margin-top:2em;"></div>
                            <!-- The Action Bar -->
                        </div> 


                        <!-- hiddens -->
                        <div class="dijitHidden">

                            <div data-dojo-type="dijit.Dialog" data-dojo-id="saveComputationDialog" id="saveComputationDialog" title="节能核算信息" style="width:56em;">
                                <form id="createSaveComputationForm" method="post" action="/company/add" >
                                    <ul class="nav">
                                        <li style="margin-bottom: 9px;">
                                            <label class="dialogLabel" for="saveComputationDialogAmmeterName">电表名称:</label>
                                            <input id="saveComputationDialogAmmeterName" data-dojo-type="dijit.form.TextBox" data-dojo-props='name:"readOnlyWidget", readOnly:true, value:"Should be returned"'/>
                                        </li>
                                        <li style="margin-bottom: 9px;">
                                            <label class="dialogLabel" for="saveComputationDialogProjectName">项目名称:</label>
                                            <input id="saveComputationDialogProjectName" data-dojo-type="dijit.form.TextBox" data-dojo-props='name:"readOnlyWidget", readOnly:true, value:"Should be returned"'/>
                                        </li>
                                        <li style="margin-bottom: 9px;">
                                            <label class="dialogLabel" for="saveComputationDialogStartDate">开始时间:</label>
                                            <input id="saveComputationDialogStartDate" data-dojo-type="dijit.form.TextBox" data-dojo-props='name:"readOnlyWidget", readOnly:true, value:"Should be returned"'/>
                                        </li>
                                        <li style="margin-bottom: 9px;">
                                            <label class="dialogLabel" for="saveComputationDialogStartTimeSum">开始累时器值:</label>
                                            <input id="saveComputationDialogStartTimeSum" data-dojo-type="dijit.form.TextBox" data-dojo-props='name:"readOnlyWidget", readOnly:true, value:"Should be returned"'/>
                                        </li>
                                        <li style="margin-bottom: 9px;">
                                            <label class="dialogLabel" for="saveComputationDialogStartValue">开始电表值:</label>
                                            <input id="saveComputationDialogStartValue" data-dojo-type="dijit.form.TextBox" data-dojo-props='name:"readOnlyWidget", readOnly:true, value:"Should be returned"'/>
                                        </li>
                                        <li style="margin-bottom: 9px;">
                                            <label class="dialogLabel" for="saveComputationDialogEndDate">结束时间:</label>
                                            <input id="saveComputationDialogEndDate" data-dojo-type="dijit.form.TextBox" data-dojo-props='name:"readOnlyWidget", readOnly:true, value:"Should be returned"'/>
                                        </li>
                                        <li style="margin-bottom: 9px;">
                                            <label class="dialogLabel" for="saveComputationDialogEndTimeSum">结束累时器值:</label>
                                            <input id="saveComputationDialogEndTimeSum" data-dojo-type="dijit.form.TextBox" data-dojo-props='name:"readOnlyWidget", readOnly:true, value:"Should be returned"'/>
                                        </li>
                                        <li style="margin-bottom: 9px;">
                                            <label class="dialogLabel" for="saveComputationDialogEndValue">结束电表值:</label>
                                            <input id="saveComputationDialogEndValue" data-dojo-type="dijit.form.TextBox" data-dojo-props='name:"readOnlyWidget", readOnly:true, value:"Should be returned"'/>
                                        </li>
                                        <li style="margin-bottom: 9px;">
                                            <label class="dialogLabel" for="saveComputationDialogSensorRate">互感器倍率:</label>
                                            <input id="saveComputationDialogSensorRate" data-dojo-type="dijit.form.TextBox" data-dojo-props='name:"readOnlyWidget", readOnly:true, value:"Should be returned"'/>
                                        </li>
                                        <li style="margin-bottom: 9px;">
                                            <label class="dialogLabel" for="saveComputationDialogRealCost">技改后能耗:</label>
                                            <input id="saveComputationDialogRealCost" data-dojo-type="dijit.form.TextBox" data-dojo-props='name:"readOnlyWidget", readOnly:true, value:"Should be returned"'/>
                                        </li>
                                        <li style="margin-bottom: 9px;">
                                            <label class="dialogLabel" for="saveComputationDialogFormerCost">技改前能耗:</label>
                                            <input id="saveComputationDialogFormerCost" data-dojo-type="dijit.form.TextBox" data-dojo-props='name:"readOnlyWidget", readOnly:true, value:"Should be returned"'/>
                                        </li>
                                        <li style="margin-bottom: 9px;">
                                            <label class="dialogLabel" for="saveComputationDialogEletricSave">节电量:</label>
                                            <input id="saveComputationDialogEletricSave" data-dojo-type="dijit.form.TextBox" data-dojo-props='name:"readOnlyWidget", readOnly:true, value:"Should be returned"'/>
                                        </li>
                                        <li style="margin-bottom: 9px;">
                                            <label class="dialogLabel" for="saveComputationDialogEletricChargeSave">节电费:</label>
                                            <input id="saveComputationDialogEletricChargeSave" data-dojo-type="dijit.form.TextBox" data-dojo-props='name:"readOnlyWidget", readOnly:true, value:"Should be returned"'/>
                                        </li>
                                        <li style="margin-bottom: 9px;">
                                            <label class="dialogLabel" for="saveComputationDialogStandardCoalRatio">折算煤系数:</label>
                                            <input id="saveComputationDialogStandardCoalRatio" data-dojo-type="dijit.form.TextBox" data-dojo-props='name:"readOnlyWidget", readOnly:true, value:"Should be returned"'/>
                                        </li>
                                        <li style="margin-bottom: 9px;">
                                            <label class="dialogLabel" for="saveComputationDialogCoalSave">节约标煤:</label>
                                            <input id="saveComputationDialogCoalSave" data-dojo-type="dijit.form.TextBox" data-dojo-props='name:"readOnlyWidget", readOnly:true, value:"Should be returned"'/>
                                        </li>
                                        <li style="margin-bottom: 9px;">
                                            <label class="dialogLabel" for="saveComputationDialogEletricCharge">标准电费:</label>
                                            <input id="saveComputationDialogEletricCharge" data-dojo-type="dijit.form.TextBox" data-dojo-props='name:"readOnlyWidget", readOnly:true, value:"Should be returned"'/>
                                        </li>
                                        <li style="margin-bottom: 9px;">
                                            <label class="dialogLabel" for="saveComputationDialogPartsRatio">成分比率:</label>
                                            <input id="saveComputationDialogPartsRatio" data-dojo-type="dijit.form.TextBox" data-dojo-props='name:"readOnlyWidget", readOnly:true, value:"Should be returned"'/>
                                        </li>
                                        <li style="margin-bottom: 9px;">
                                            <label class="dialogLabel" for="saveComputationDialogTheOtherPartyBouns">节能公司收益:</label>
                                            <input id="saveComputationDialogTheOtherPartyBouns" data-dojo-type="dijit.form.TextBox" data-dojo-props='name:"readOnlyWidget", readOnly:true, value:"Should be returned"'/>
                                        </li>
                                        <li style="margin-bottom: 9px;">
                                            <label class="dialogLabel" for="saveComputationDialogThePartyBonus">用能公司收益:</label>
                                            <input id="saveComputationDialogThePartyBonus" data-dojo-type="dijit.form.TextBox" data-dojo-props='name:"readOnlyWidget", readOnly:true, value:"Should be returned"'/>
                                        </li>
                                    </ul>
                                </form>
                                
                                <div class="dijitDialogPaneActionBar">
                                    <button id="saveComputationDialogAddBtn" data-dojo-type="dijit.form.Button">
                                        保存核算信息
                                    </button>
                                    <button id="saveComputationDialogCancelBtn" data-dojo-type="dijit.form.Button">
                                        取消
                                    </button>
                                </div>
                            </div>

                            <div data-dojo-type="dijit.Dialog" data-dojo-id="createCompanyDialog" id="createCompanyDialog" title="添加公司" style="width:56em;">
                                <form id="createCompanyDialogForm" method="post" action="/company/add" >
                                    <ul class="nav">
                                        <li style="margin-bottom: 9px;">
                                            <label class="dialogLabel" for="companyName">公司名称:</label>
                                            <input id="companyName" type="text" dojoType="dijit.form.ValidationTextBox" class="long"
                                                required="true" 
                                                ucfirst="true" invalidMessage="">
                                            </input>
                                        </li>
                                    </ul>
                                </form>
                                
                                <div class="dijitDialogPaneActionBar">
                                    <button id="createCompanyDialogAddBtn" data-dojo-type="dijit.form.Button">
                                        添加
                                    </button>
                                    <button id="createCompanyDialogCancelBtn" data-dojo-type="dijit.form.Button">
                                        取消
                                    </button>
                                </div>
                            </div>

							<div data-dojo-type="dijit.Dialog" data-dojo-id="createProjectForCompanyDialog" id="createProjectForCompanyDialog" title="添加新项目到公司" style="width:56em; ">
								<form id="newProjectDialogForm" method="post" action="/project/add" >
                                    <ul class="nav">
                                    	
                                   		<fieldset id="createOrSelectCompany" class="dijitInline">
                                   			<li style="margin-bottom: 9px;">
	                                            <label class="dialogLabel" for="createNewCompanyRadio">新建公司:</label>
	                                            <input type="radio" id="createNewCompanyRadio"  value="full" dojoType="dijit.form.RadioButton" />
                                            </li>
                                            <li style="margin-bottom: 9px;">
	                                            <label class="dialogLabel" for="selectExistingCompanyradio">选择已存在公司:</label>
	                                            <input type="radio" id="selectExistingCompanyradio" checked value="full" dojoType="dijit.form.RadioButton" />
                                            </li>
                                        </fieldset>
                                        <li style="margin-bottom: 9px;" id="projectCompanyLi">
                                            <label class="dialogLabel" id="projectCompanyLabel" for="projectCompany">项目公司:</label>
                                            <input class="medium dijitHidden" id="projectCompanyCombo" />
                                            <input class="medium dijitHidden" id="projectCompanyTextBox" />
                                        </li>
                                        <li style="margin-bottom: 9px;">
                                            <label class="dialogLabel" for="forCompanyProjectName">项目名称:</label>
                                            
                                            <input type="text" id="forCompanyProjectName" name="forCompanyProjectName" class="long"
                                                dojoType="dijit.form.ValidationTextBox"
                                                required="true" 
                                                ucfirst="true" invalidMessage=""/>
                                            </input>
                                        </li>
                                        <li style="margin-bottom: 9px;">
                                            <label class="dialogLabel" for="projectStartDate">项目开始时间:</label>
                                            <input id="projectStartDate" name="projectStartDate" dojoType="dijit.form.DateTextBox" required=true/>
                                        </li>
                                        <li style="margin-bottom: 9px;">
                                            <label class="dialogLabel" for="projectEndDate">项目结束时间:</label>
                                            <input id="projectEndDate" name="projectEndDate" dojoType="dijit.form.DateTextBox" required=true/>
                                        </li>
                                        <li style="margin-bottom: 9px;">
                                            <label class="dialogLabel" for="electricityCharge">电费:</label>
                                            <input type="text" id="electricityCharge" class="short"dojoType="dijit.form.ValidationTextBox" trim="true" required="true" regExp="^[1-9]\d*\.\d*|0\.\d*[1-9]\d*$|^[1-9]\d*$"
                                            invalidMessage="请输入正确的电费格式 (ex: 5.5)"/>
                                        </li>
                                        
                                        <li style="margin-bottom: 9px;">
                                            <label class="dialogLabel" for="partsRatio">分成比率:</label>
                                            <input type="text" id="partsRatio" class="short"dojoType="dijit.form.ValidationTextBox" trim="true" required="true" regExp="^[1-9]\d*\.\d*|0\.\d*[1-9]\d*$|^[1-9]\d*$"
                                            invalidMessage="请输入正确的比率格式 (ex: 0.5)"/>
                                        </li>
                                        <li style="margin-bottom: 9px;">
                                            <label class="dialogLabel" for="ammeterAddedMultiSelect">已经添加的电表:</label><br>
                                    		<select id="ammeterAddedMultiSelect" multiple data-dojo-type="dijit.form.MultiSelect" data-dojo-props='name:"select", style:{height:"15em", width:"22em", border:"5px solid #ededed"}'>
                                    		</select>
                                        </li>
                                        <li style="margin-bottom: 9px;">
                                            <label class="dialogLabel" for="userAddedMultiSelect">已经添加的用户:</label><br>
                                            <select id="userAddedMultiSelect" multiple data-dojo-type="dijit.form.MultiSelect" data-dojo-props='name:"select", style:{height:"15em", width:"22em", border:"5px solid #ededed"}'>
                                            </select>
                                        </li>
                                    </ul>
									
								</form>
								<form id="newProjectDialogCPFrom" method="post" action="/cp/add" >
								</form>
								
						
								<div class="dijitDialogPaneActionBar">
                                    <div style="display:none;" id="createProjectDialogHiddenBtn">
                                        <button id="addExistingAmmeterForProjectBtn" data-dojo-type="dijit.form.Button">
                                            继续添加电表
                                        </button>
                                        <button id="addUsersForProjectBtn" data-dojo-type="dijit.form.Button">
                                            继续添加用户
                                        </button>
                                    </div>
                                    <button id="newProjectDialogAddBtn" data-dojo-type="dijit.form.Button">
                                        添加
                                    </button>
                                    <button id="newProjectDialogCancelBtn" data-dojo-type="dijit.form.Button">
                                        取消
                                    </button>
								</div>
							</div>
                            <div data-dojo-type="dijit.Dialog" data-dojo-id="createAmmeterDialog" id="createAmmeterDialog" title="添加电表" style="width:56em;">
                                <form id="createAmmeterDialogForm" method="post" action="/ammeter/add" >
                                    <ul class="nav">
                                        <li style="margin-bottom: 9px;">
                                            <label class="dialogLabel" for="ammeterName">电表名称:</label>
                                            <input id="ammeterName" type="text" dojoType="dijit.form.ValidationTextBox" class="long"
                                                required="true" 
                                                ucfirst="true" invalidMessage="">
                                            </input>
                                        </li>
                                        <li style="margin-bottom: 9px;">
                                            <label for="pumpName" class="dialogLabel">泵名称:</label>
                                            <input id="pumpName" type="text" dojoType="dijit.form.ValidationTextBox" class="long"
                                                required="false" 
                                                ucfirst="true" invalidMessage="">
                                            </input>
                                        </li>
                                        </li>
                                        <li id="ammeterProjectLi" style="margin-bottom: 9px;">
                                            <label class="dialogLabel" for="ammeterProject">电表项目:</label>
                                            <input class="medium" id="ammeterProject" />
                                        </li>
                                        <li style="margin-bottom: 9px;">
                                            <label class="dialogLabel" for="sensorRate">互感器倍率:</label>
                                            <input type="text" id="sensorRate" class="short" dojoType="dijit.form.ValidationTextBox" trim="true" required="true" regExp="^[1-9]\d*\.\d*|0\.\d*[1-9]\d*$|^[1-9]\d*$"
                                            invalidMessage="请输入正确的互感器倍率格式 (ex: 5.5)"/>
                                        </li>
                                        <li style="margin-bottom: 9px;">
                                            <label class="dialogLabel" for="formerCost">技改前能耗:</label>
                                            <input type="text" id="formerCost" class="short" dojoType="dijit.form.ValidationTextBox" trim="true" required="true" regExp="^[1-9]\d*\.\d*|0\.\d*[1-9]\d*$|^[1-9]\d*$"
                                            invalidMessage="请输入正确的能耗格式 (ex: 5.5)"/>
                                        </li>
                                        <li style="margin-bottom: 9px;">
                                            <label class="dialogLabel" for="upperLimit">警报上限:</label>
                                            <input type="text" id="upperLimit" class="short" dojoType="dijit.form.ValidationTextBox" trim="true" required="true" regExp="^[1-9]\d*\.\d*|0\.\d*[1-9]\d*$|^[1-9]\d*$"
                                            invalidMessage="请输入正确的警报上限格式 (ex: 5.5)"/>
                                        </li>
                                        <li style="margin-bottom: 9px;">
                                            <label class="dialogLabel" for="lowerLimit">警报下限:</label>
                                            <input type="text" id="lowerLimit" class="short" dojoType="dijit.form.ValidationTextBox" trim="true" required="true" regExp="^[1-9]\d*\.\d*|0\.\d*[1-9]\d*$|^[1-9]\d*$"
                                            invalidMessage="请输入正确的警报下限格式 (ex: 5.5)"/>
                                        </li>
                                        <fieldset id="createOrSelectGPRS" class="dijitInline">
                                            <li style="margin-bottom: 9px;">
                                                <label class="dialogLabel" for="createNewGPRSRadio">新建GPRS模块:</label>
                                                <input type="radio" id="createNewGPRSRadio" checked  value="full" dojoType="dijit.form.RadioButton" />
                                            </li>
                                            <li style="margin-bottom: 9px;">
                                                <label class="dialogLabel" for="selectExistingGPRSRadio">选择已有GPRS:</label>
                                                <input type="radio" id="selectExistingGPRSRadio"  value="full" dojoType="dijit.form.RadioButton" />
                                            </li>
                                        </fieldset>
                                        <li style="margin-bottom: 9px;" id="ammeterGPRSLi">
                                            <label class="dialogLabel" id="ammeterGPRSLabel" for="ammeterGPRS"> GPRS:</label>
                                            <input class="medium dijitHidden" id="ammeterGPRSCombo" />
                                            <input class="medium dijitHidden" id="ammeterGPRSNameTextBox" />
                                            <input class="medium dijitHidden" id="ammeterGPRSIdentifierTextBox" />
                                        </li>

                                    </ul>
                                </form>
                                
                                <div class="dijitDialogPaneActionBar">
                                    <button id="createAmmeterDialogAddBtn" data-dojo-type="dijit.form.Button">
                                        添加
                                    </button>
                                    <button id="createAmmeterDialogCancelBtn" data-dojo-type="dijit.form.Button">
                                        取消
                                    </button>
                                </div>
                            </div>

                            <div data-dojo-type="dijit.Dialog" data-dojo-id="createGPRSDialog" id="createGPRSDialog" title="添加电表" style="width:56em;">
                                <form id="createGPRSDialogForm" method="post" action="/gprs/add" >
                                    <ul class="nav">
                                        <li style="margin-bottom: 9px;">
                                            <label class="dialogLabel" for="gprsName">GPRS名称:</label>
                                            <input id="gprsName" type="text" dojoType="dijit.form.ValidationTextBox" class="long"
                                                required="true" 
                                                ucfirst="true" invalidMessage="">
                                            </input>

                                        </li>
                                        <li style="margin-bottom: 9px;">
                                            <label class="dialogLabel" for="gprsIdentifer">GPRS标示:</label>
                                            <input id="gprsIdentifer" type="text" dojoType="dijit.form.ValidationTextBox" class="long"
                                                required="true" 
                                                ucfirst="true" invalidMessage="">
                                            </input>
                                            
                                        </li>
                                    </ul>
                                </form>
                                <div class="dijitDialogPaneActionBar">
                                    <button id="createGPRSDialogAddBtn" data-dojo-type="dijit.form.Button">
                                        添加
                                    </button>
                                    <button id="createGPRSDialogCancelBtn" data-dojo-type="dijit.form.Button">
                                        取消
                                    </button>
                                </div>
                            </div>

                            <!-->Create User Dialog</!-->
                            <div data-dojo-type="dijit.Dialog" data-dojo-id="createUserDialog" id="createUserDialog" title="添加电表" style="width:56em;">
                                <form id="createUserDialogForm" method="post" action="/user/list" >
                                    <ul class="nav">
                                        <li style="margin-bottom: 9px;">
                                            <label class="dialogLabel" for="userName">用户名称:</label>
                                            <input id="userName" type="text" dojoType="dijit.form.ValidationTextBox" class="long"
                                                required="true" 
                                                ucfirst="true" invalidMessage="">
                                            </input>
                                        </li>
                                        <li style="margin-bottom: 9px;">
                                            <label for="userEmail" class="dialogLabel">用户邮箱:</label>
                                            <input id="userEmail" type="text" dojoType="dijit.form.ValidationTextBox" class="long"
                                                required="false" 
                                                ucfirst="true" invalidMessage="">
                                            </input>
                                        </li>
                                        <li style="margin-bottom: 9px;">
                                            <label for="userPassword" class="dialogLabel">用户密码:</label>
                                            <input id="userPassword" type="text" dojoType="dijit.form.ValidationTextBox" class="long"
                                                required="false" 
                                                ucfirst="true" invalidMessage="">
                                            </input>
                                        </li>
                                        <li id="userProjectLi" style="margin-bottom: 9px; display:none;">
                                            <label class="dialogLabel" for="userProject">用户项目:</label>
                                            <input class="medium" id="userProject" />
                                        </li>
                                    </ul>
                                </form>
                                
                                <div class="dijitDialogPaneActionBar">
                                    <button id="createUserDialogAddBtn" data-dojo-type="dijit.form.Button">
                                        添加
                                    </button>
                                    <button id="createUserDialogCancelBtn" data-dojo-type="dijit.form.Button">
                                        取消
                                    </button>
                                </div>
                            </div>

                            <div data-dojo-type="dijit.Dialog" data-dojo-id="ErrorDialog" id="ErrorDialog" title="错误" style="width:600px;">
                                错误
                            </div>

                            <div data-dojo-type="dijit.Dialog" data-dojo-id="addUsersToProjectDialog" id="addUsersToProjectDialog" >
                                <div>
                                	<button id="addNewUserForNewCreatingProjectBtn" data-dojo-type="dijit.form.Button">
                                            添加新的用户到项目
                                    </button>
                                </div>
                                <div class="sel1" role="presentation">
                                    <label for="exstingAUsersMultiSelect">用户:</label><br>
                                    <select id="exstingAUsersMultiSelect" multiple data-dojo-type="dijit.form.MultiSelect" data-dojo-props='name:"select", style:{height:"20em", width:"22em", border:"5px solid #ededed"}'>
                                        <option class="clone" value="dojo._defaultEasing"></option>
                                    </select>
                                </div>
                                <div class="leftRightButtons" role="presentation">
                                    <span>
                                        <button id="usersMultiSelectLeftBtn" data-dojo-type="dijit.form.Button">
                                            &lt;
                                        </button>
                                        <button id="usersMultiSelectRightBtn" data-dojo-type="dijit.form.Button">
                                            &gt;
                                        </button>
                                    </span>
                                </div>
                                <div class="sel2" role="presentation">
                                    <label for="addedUsersMultiSelect">已添加的用户:</label><br>
                                    <select id="addedUsersMultiSelect" multiple data-dojo-type="dijit.form.MultiSelect" data-dojo-props='name:"select", style:{height:"20em", width:"22em", border:"5px solid #ededed"}'>
                                    </select>
                                </div>
                                <div class="clear"></div>
                                <div class="dijitDialogPaneActionBar">
                                    <button id="addUsersForProjectSubmitBtn" data-dojo-type="dijit.form.Button">
                                        完成
                                    </button>
                                    <button id="addUsersForProjectCancelBtn" data-dojo-type="dijit.form.Button">
                                        取消
                                    </button>
                                </div>
                            </div>

                            <div data-dojo-type="dijit.Dialog" data-dojo-id="AddExistingAmmeterToProjectDialog" id="AddExistingAmmeterToProjectDialog" >
                                <div>
                                    <button id="addNewAmmeterForNewCreatingProjectBtn" data-dojo-type="dijit.form.Button">
                                            添加新的电表到项目
                                    </button>
                                </div>
                                <div class="sel1" role="presentation">
                                    <label for="ExstingAmmeterMultiSelect">电表:</label><br>
                                    <select id="ExstingAmmeterMultiSelect" multiple data-dojo-type="dijit.form.MultiSelect" data-dojo-props='name:"select", style:{height:"20em", width:"22em", border:"5px solid #ededed"}'>
                                        <option class="clone" value="dojo._defaultEasing"></option>
                                    </select>
                                </div>
                                <div class="leftRightButtons" role="presentation">
                                    <span>
                                        <button id="AmmeterMultiSelectLeftBtn" data-dojo-type="dijit.form.Button">
                                            &lt;
                                        </button>
                                        <button id="AmmeterMultiSelectRightBtn" data-dojo-type="dijit.form.Button">
                                            &gt;
                                        </button>
                                    </span>
                                </div>
                                <div class="sel2" role="presentation">
                                    <label for="AddedAmmeterMultiSelect">已添加的电表:</label><br>
                                    <select id="AddedAmmeterMultiSelect" multiple data-dojo-type="dijit.form.MultiSelect" data-dojo-props='name:"select", style:{height:"20em", width:"22em", border:"5px solid #ededed"}'>
                                    </select>
                                </div>
                                <div class="clear"></div>
                                <div class="dijitDialogPaneActionBar">
                                    <button id="addAmmeterForProjectSubmitBtn" data-dojo-type="dijit.form.Button">
                                        完成
                                    </button>
                                    <button id="addAmmeterForProjectCancelBtn" data-dojo-type="dijit.form.Button">
                                        取消
                                    </button>
                                </div>
                            </div>

                            <div data-dojo-type="dijit.Dialog" data-dojo-id="modifyPasswordDialog" id="modifyPasswordDialog" title="添加电表" style="width:56em;">
                                <form id="modifyPasswordDialogForm" method="post" action="/user/list" >
                                    <ul class="nav">
                                        <li style="margin-bottom: 9px;">
                                            <label class="dialogLabel" for="newPassword">新密码:</label>
                                            <input id="newPassword" type="text" dojoType="dijit.form.ValidationTextBox" class="long"
                                                required="true" 
                                                ucfirst="true" invalidMessage="">
                                            </input>
                                        </li>
                                    </ul>
                                </form>
                                
                                <div class="dijitDialogPaneActionBar">
                                    <button id="modifyPasswordBtn" data-dojo-type="dijit.form.Button">
                                        修改
                                    </button>
                                    <button id="modifyPasswordCancelBtn" data-dojo-type="dijit.form.Button">
                                        取消
                                    </button>
                                </div>
                            </div>

						</div>


						<!-- /hiddens -->
                    </div>
                </div>
                <!-- /tab_container -->
            </div>
            <!-- /span -->
        </div>
        <!-- /.row-->
        <form id="search_ammeter_form" action="ammeter/search"></form>
        <form id="deleget_form" action="/ammeter/list/update"></form>
        <hr>
        <footer>
            <p>&copy; Vincent Fan 2012</p>
        </footer>
    </div>
    </div> 
    <!-- /container -->
    <!-- hiddens -->
  </body>
</html>
