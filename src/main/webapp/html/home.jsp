<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>浙大智电</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Le styles -->
    <link href = "/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href = "/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
    <style type = "text/css">
        @import "/dojo-release-1.7.2/dojo/resources/dojo.css";
        @import "/dojo-release-1.7.2/dijit/themes/dijit.css";
        @import "/dojo-release-1.7.2/dijit/themes/claro/claro.css";
        @import "/dojo-release-1.7.2/dojox/grid/resources/claroGrid.css";
        @import "/dojo-release-1.7.2/dojox/grid/enhanced/resources/claro/EnhancedGrid.css";
        @import "/dojo-release-1.7.2/dojox/grid/enhanced/resources/tundra/EnhancedGrid.css";
        @import "/dojo-release-1.7.2/dojox/grid/enhanced/resources/EnhancedGrid_rtl.css";
        @import "/dojo-release-1.7.2/dojox/grid/enhanced/resources/EnhancedGrid_rtl.css";
        body {
            padding-top: 60px;
            padding-bottom: 40px;

        }
        #ammeter_grid  {
            height:400px;
            width: 100%
        }

    </style>
    
    <script src = "/dojo-release-1.7.2/dojo/dojo.js" 
        data-dojo-config="
            async:true,
            parseOnLoad:true,
            locale:'zh-cn'">
    </script>
    
    
    <script language="javascript" src="/js/index3.js">  </script>
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
                <a class="brand" href="#">浙大智电</a>
                <div class="nav-collapse">
                <ul class="nav">
                    <li class="active"><a href="#">主页</a></li>
                    <li><a href="#">关于我们</a></li>
                    <li><a href="#">联系方式</a></li>
                </ul>
                <div class="btn-group pull-right">
                    <a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
                    <i class="icon-user"></i> <shiro:principal />
                    <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="#">Profile</a></li>
                        <li class="divider"></li>
                        <li><a href="#">Sign Out</a></li>
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
                        <li class="nav-header">电表管理</li>
                        <shiro:hasRole name="admin">  
                        <li class="active"><a onclick = "showAmmeterMan()" href="#">电表管理</a></li>
                        </shiro:hasRole> 
                        <li><a onclick = "showAmmeterRecordMan()" href="#">电表记录管理</a></li>
                        <shiro:hasRole name="admin">
                        <li class="nav-header">项目管理</li>
                        <li><a href="#" onclick = "showProjectMan()">项目管理</a></li>
                        <li class="nav-header">公司管理</li>
                        <li><a onclick = "showCompanyMan()" href="#">公司管理</a></li>
                        <li class="nav-header">权限管理</li>
                        <li><a href="#" onclick = "showUserMan()">权限管理</a></li>
                        </shiro:hasRole>
                    </ul>
                </div><!--/.well -->
            </div><!--/.span -->
            <div id ="main_container" class="span9">
                <div class="claro">
                    <div id="tab_container" data-dojo-type="dijit.layout.TabContainer" doLayout="false" style="width:98%;height:85%;">
                        <div id = "ammeterRecordPane" data-dojo-type="dijit.layout.ContentPane" title="电表纪录管理" >
                            <div id="ammeter_record_grid" class="claro" style="height:473px"></div>   
                        </div>
                        
                        <div id = "ammeterPane" data-dojo-type="dijit.layout.ContentPane" title="电表管理">
                            <div id = "ammeter_grid_container" style = "width: 100%;" class = "claro">
                                <div id="search_var">
                                    <div data-dojo-type="dijit.form.DropDownButton">
                                        <span>新建</span>
                                        <div data-dojo-type="dijit.TooltipDialog" id="new_ammeter_dialog">
                                            <form id="add_ammeter_form" method="post" action="/ammeter/list">
                                            <div>
                                                <strong><label class="add_form_label" for="name">电表名称</label></strong>
                                                <div data-dojo-type="dijit.form.TextBox" id="name"></div>
                                            </div>
                                            <div>
                                                <strong><label class="add_form_label" for="pumpName">泵名称</label></strong>
                                                <div data-dojo-type="dijit.form.TextBox" id="pumpName"></div>
                                            </div>
                                            <div>
                                                <strong><label class="add_form_label" for="companyName">公司名称</label></strong>
                                                <div data-dojo-type="dijit.form.TextBox" id="companyName"></div>
                                            </div>
                                            <div>
                                                <strong><label class="add_form_label" for="projectName">项目名称</label></strong>
                                                <div data-dojo-type="dijit.form.TextBox" id="projectName"></div>
                                            </div>
                                            <div>
                                                <button id="add_ammeter_btn"></button>
                                            </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                                <!-- The Data Grid -->
                                <div id="ammeter_grid" class="claro" style="height:400px"></div>
                                <!-- The Action Bar -->
                                <div id="modify_bar" class="claro">
                                    <button id="save_button"></button>
                                    <button id="delete_button"></button>
                                </div>
                            </div>

                            
                        </div>
                        <!-- /ammeter pane -->

                        <div id = "userPane" data-dojo-type="dijit.layout.ContentPane" title = "用户管理">
                            <div id="search_var">
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
                                            <button id="add_user_btn"></button>
                                        </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                            <!-- The Data Grid -->
                            <div id="user_grid" class="claro" style="height:400px"></div>
                            <!-- The Action Bar -->
                            <div id="user_modify_bar" class="claro">
                                <button id="user_save_button"></button>
                                <button id="user_delete_button"></button>
                            </div>
                        </div>
                        <!--/user pane-->

                        <div id = "companyPane" data-dojo-type="dijit.layout.ContentPane" title = "公司管理">
                            <div id="search_var">
                                <div data-dojo-type="dijit.form.DropDownButton">
                                    <span>新建</span>
                                    <div data-dojo-type="dijit.TooltipDialog" id="new_company_dialog">
                                        
                                        <form id="add_company_form" method="post" action="/company/add">
                                            <div>
                                                <strong><label class="add_form_label" for="addCompanyName">公司名称</label></strong>
                                                <div data-dojo-type="dijit.form.TextBox" id="addCompanyName"></div>
                                            </div>
                                            <div>
                                                <button id="add_company_btn"></button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                                <!-- The Data Grid -->
                                <div id="company_grid" class="claro" style="height:400px"></div>
                                <!-- The Action Bar -->
                                <div id="company_modify_bar" class="claro">
                                    <button id="company_save_button"></button>
                                    <button id="company_delete_button"></button>
                                </div>
                            </div>
                        </div>    
                        <!--/company pane-->

                        <div id = "projectPane" data-dojo-type="dijit.layout.ContentPane" title = "项目管理">
                            <div id="search_var">
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
                                <!-- The Data Grid -->
                                <div id="project_grid" class="claro" style="height:400px"></div>
                                <!-- The Action Bar -->
                                <div id="project_modify_bar" class="claro">
                                    <button id="project_save_button"></button>
                                    <button id="project_delete_button"></button>
                                </div>
                            </div>
                        </div> 
                        <!--project Pane-->

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
  </body>
</html>
