<#--定义html文件中head标签里的内容-->
<#include "headHtml.ftl"/>
<#macro baseHtml title="宏观经济" localJsFiles=[] remoteJsFiles=[] localCssFiles=[] curMenu="" >
    <#escape x as x?html>
    <!DOCTYPE html>
    <html lang="zh-CN">
    <head>
        <meta http-equiv="pragma" content="no-cache">
        <meta name="description" content="宏观经济">
        <meta name="keywords" content="宏观经济">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>${title}</title>

        <@headHtml localJsFiles = localJsFiles remoteJsFiles = remoteJsFiles localCssFiles = localCssFiles>

        <#-- 	本地全局css -->
            <link rel="stylesheet" href="${basepath}/resources/css/global.css"/>
        <#--<link rel="stylesheet" type="text/css" href="${basepath}/resources/plugin/datagrid/minified/jquery.bs_grid.min.css">-->
        <#--<script type="text/javascript" src="${basepath}/resources/plugin/datagrid/minified/jquery.bs_grid.min.js"></script>-->
        <#--<script type="text/javascript" src="${basepath}/resources/plugin/datagrid/minified/localization/en.min.js"></script>-->
            <script type="text/javascript"
                    src="${basepath}/resources/plugin/treeview/bootstrap-treeview.min.js"></script>
        <#--<script type="text/javascript" src="http://code.highcharts.com/highcharts.js"></script>-->
            <script type="text/javascript" src="${basepath}/resources/plugin/highcharts/highcharts.js"></script>

            <script type="text/javascript" src="${basepath}/resources/javascripts/leftMenu.js"></script>

            <script type="text/javascript">
                var basepath = '${basepath}';
            </script>
        </@headHtml>
    </head>

    <body>

    <header role="banner" class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
        <#-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse"
                        data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">鼎美</a>
            </div>

        <#-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="#">宏观经济</a></li>
                <#--
                <li><a href="#">最新段子</a></li>
                <li class="dropdown">
                  <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown <b class="caret"></b></a>
                  <ul class="dropdown-menu">
                    <li><a href="#">Action</a></li>
                    <li><a href="#">Another action</a></li>
                    <li><a href="#">Something else here</a></li>
                    <li class="divider"></li>
                    <li><a href="#">Separated link</a></li>
                    <li class="divider"></li>
                    <li><a href="#">One more separated link</a></li>
                  </ul>
                </li>
                -->
                </ul>

            <#--
            <form class="navbar-form navbar-left" role="search">
              <div class="form-group">
                <input type="text" class="form-control" placeholder="Search">
              </div>
              <button type="submit" class="btn btn-default">Submit</button>
            </form> -->

                <ul class="nav navbar-nav navbar-right">
                    <p class="navbar-text">欢迎:${WebUtil.getSessionInfo().realName}</p>
                    <li><a href="/j_spring_security_logout">注销</a></li>
                <#--<li class="dropdown">
                  <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown <b class="caret"></b></a>
                  <ul class="dropdown-menu">
                    <li><a href="#">Action</a></li>
                    <li><a href="#">Another action</a></li>
                    <li><a href="#">Something else here</a></li>
                    <li class="divider"></li>
                    <li><a href="#">Separated link</a></li>
                  </ul>
                </li>-->
                </ul>

            </div><#-- /.navbar-collapse -->
        </div>
    </header>

    <div class="container">
        <div class="row">
            <div class="col-md-3">
                <div id="leftMenu">
                </div>
            </div>
            <div class="col-md-9">
                <#nested/>
            </div>

        </div>
    </div>


    <div id="copyright" class="container">
        <hr/>
        <div class="text-center">
            <small>Copyright©2013 zhengzhichao. All Rights Reserved.</small>
        </div>
    </div>
    </body>
    </html>
    </#escape>
</#macro>
