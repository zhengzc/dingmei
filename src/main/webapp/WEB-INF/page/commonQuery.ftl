<#include "/common/baseHtml.ftl" />
<@baseHtml localJsFiles=["page/commonQuery.js"] remoteJsFiles=[] localCssFiles=[]>
    <script type="text/javascript">
        line = ${line};
        selectNode = parseInt(${selectNode!"0"});
    </script>

    <form id="queryForm" class="form-horizontal" action="${basepath}/common/page?id=${id}&selectNode=${selectNode}" method="get">
        <input class="hidden" name="id" value="${id}" />
        <input class="hidden" name="selectNode" value="${selectNode}" />
        <div class="panel-group" id="steps">
            <!-- Step 1 -->
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h4 class="panel-title"><a data-toggle="collapse" data-parent="#steps" href="#stepOne">分段查询</a></h4>
                </div>
                <div id="stepOne" class="panel-collapse collapse">
                    <div class="panel-body">

                        <div class="form-group">
                            <label for="startYear" class="col-sm-2 control-label">开始时间</label>
                            <#list timeKeys as tk>
                                <#if tk == "year">
                                    <div class="col-sm-2">
                                        <input type="text" class="form-control" id="startYear" name="startYear" placeholder="年" maxlength="4">
                                    </div>
                                <#elseif tk == "quarter">
                                    <div class="col-sm-2">
                                        <input type="text" class="form-control" id="startQuarter" name="startQuarter" placeholder="季度" maxlength="1">
                                    </div>
                                <#elseif tk == "month">
                                    <div class="col-sm-2">
                                        <input type="text" class="form-control" id="startMonth" name="startMonth" placeholder="月份" maxlength="2">
                                    </div>
                                <#elseif tk == "week">
                                    <div class="col-sm-2">
                                        <input type="text" class="form-control" id="startWeek" name="startWeek" placeholder="星期" maxlength="2">
                                    </div>
                                <#elseif tk == "day">
                                    <div class="col-sm-2">
                                        <input type="text" class="form-control" id="startDay" name="startDay" placeholder="日" maxlength="2">
                                    </div>
                                </#if>
                            </#list>
                        </div>

                        <div class="form-group">
                            <label for="endYear" class="col-sm-2 control-label">结束时间</label>
                            <#list timeKeys as tk>
                                <#if tk == "year">
                                    <div class="col-sm-2">
                                        <input type="text" class="form-control" id="endYear" name="endYear" placeholder="年" maxlength="4">
                                    </div>
                                <#elseif tk == "quarter">
                                    <div class="col-sm-2">
                                        <input type="text" class="form-control" id="endQuarter" name="endQuarter" placeholder="季度" maxlength="1">
                                    </div>
                                <#elseif tk == "month">
                                    <div class="col-sm-2">
                                        <input type="text" class="form-control" id="endMonth" name="endMonth" placeholder="月份" maxlength="2">
                                    </div>
                                <#elseif tk == "week">
                                    <div class="col-sm-2">
                                        <input type="text" class="form-control" id="endWeek" name="endWeek" placeholder="星期" maxlength="2">
                                    </div>
                                <#elseif tk == "day">
                                    <div class="col-sm-2">
                                        <input type="text" class="form-control" id="endDay" name="endDay" placeholder="日" maxlength="2">
                                    </div>
                                </#if>
                            </#list>
                        </div>

                        <div class="form-group">
                            <div class="col-lg-9 col-lg-offset-2">
                                <button type="submit" class="btn btn-primary" value="提交">提交</button>
                                <button type="reset" class="btn btn-default" value="重置">重置</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>

    <div id="chart">
    </div>
    <#list tables as table>
    <div>
        <h3>${table.title}</h3>
        <table class="table .table-condensed">
            <thead>
            <tr>
                <th>时间</th>
                <#list table.columnName as column>
                    <th>${column}</th>
                </#list>
            </tr>
            </thead>
            <tbody>
            <#list table.datas as row>
            <tr>
                <#list row as cell>
                    <#if cell_index == 0>
                        <th scope="row">${cell}</th>
                    <#else>
                        <td>${cell}</td>
                    </#if>
                </#list>
            </tr>
            </#list>
            </tbody>
        </table>
    </div>
    </#list>

    <div class="bg-info">
        <small>
            <em>&nbsp;&nbsp;&nbsp;&nbsp;${description!""}</em>
        </small>
    </div>
    <#--
    <div>
        <h3>M1供应量</h3>
        <table class="table .table-condensed">
            <thead>
            <tr>
                <th>时间</th>
                <th>数量</th>
                <th>环比</th>
                <th>同比</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th scope="row">8月份</th>
                <td>135.69</td>
                <td>0.27%</td>
                <td>13.3%</td>
            </tr>
            <tr>
                <th scope="row">9月份</th>
                <td>135.69</td>
                <td>0.27%</td>
                <td>13.3%</td>
            </tr>
            <tr>
                <th scope="row">10月份</th>
                <td>135.69</td>
                <td>0.27%</td>
                <td>13.3%</td>
            </tr>
            </tbody>
        </table>
    </div>

    <div>
        <table class="table .table-condensed">
            <thead>
            <tr>
                <th>#</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Username</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th scope="row">1</th>
                <td>Mark</td>
                <td>Otto</td>
                <td>@mdo</td>
            </tr>
            <tr>
                <th scope="row">2</th>
                <td>Jacob</td>
                <td>Thornton</td>
                <td>@fat</td>
            </tr>
            <tr>
                <th scope="row">3</th>
                <td colspan="2">Larry the Bird</td>
                <td>@twitter</td>
            </tr>
            </tbody>
        </table>
    </div>


    <div class="col-md-3">
        <table class="table .table-condensed">
            <thead>
            <tr>
                <th>#</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Username</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th scope="row">1</th>
                <td>Mark</td>
                <td>Otto</td>
                <td>@mdo</td>
            </tr>
            <tr>
                <th scope="row">2</th>
                <td>Jacob</td>
                <td>Thornton</td>
                <td>@fat</td>
            </tr>
            <tr>
                <th scope="row">3</th>
                <td colspan="2">Larry the Bird</td>
                <td>@twitter</td>
            </tr>
            </tbody>
        </table>
    </div>
    <div class="col-md-3">
        <table class="table .table-condensed">
            <thead>
            <tr>
                <th>#</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Username</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th scope="row">1</th>
                <td>Mark</td>
                <td>Otto</td>
                <td>@mdo</td>
            </tr>
            <tr>
                <th scope="row">2</th>
                <td>Jacob</td>
                <td>Thornton</td>
                <td>@fat</td>
            </tr>
            <tr>
                <th scope="row">3</th>
                <td colspan="2">Larry the Bird</td>
                <td>@twitter</td>
            </tr>
            </tbody>
        </table>
    </div>

    -->
</@baseHtml>