<#include "/common/baseHtml.ftl" />
<@baseHtml localJsFiles=["page/commonQuery.js"] remoteJsFiles=[] localCssFiles=[]>
    <script type="text/javascript">
        line = ${line};
        selectNode = parseInt(${selectNode!"0"});
    </script>

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