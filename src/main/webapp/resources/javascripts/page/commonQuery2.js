jQuery(document).ready(function($){
    $('#chart').highcharts({
        data: {
            table: document.getElementById('dataTable')
        },
        chart: {
            type: 'column'
        },
        title: {
            text: 'Data extracted from a HTML table in the page'
        },
        yAxis: {
            allowDecimals: false,
            title: {
                text: 'Units'
            }
        },
        tooltip: {
            formatter: function() {
                return '<b>'+ this.series.name +'</b><br/>'+
                    this.y +' '+ this.x;
            }
        }
    });
    /*var tableData = {
        "total_rows": "2",
        "page_data": [{
                "id": "111",
                "lastname": "Diaz",
                "firstname": "Kai",
                "email": "odio.Aliquam@Phasellus.org",
                "gender": "female"
            },{
                "id": "112",
                "lastname": "Snider",
                "firstname": "Nelle",
                "email": "vulputate@nonlobortis.org",
                "gender": "female"
            }
        ]
    };

    $("#grid").bs_grid(tableData);*/


    $("#saveAnalysis").bind("click",function(){
        var analysisStr = $("#analysisContext").val();
        var param = {};
        param.groupId = groupId;
        param.analysis = analysisStr;
        $.post(basepath+"/common/updateGroupAnalysis",param,function(data){
            if(data.code == 200){
                window.location.reload(true);
            }else{
                $("#errMsg").text(data.msg);
                $("#errMsg").removeClass("hidden");
            }
        });
    });
});