jQuery(document).ready(function($){

    $('#chart').highcharts({
        chart: {
            type: 'line'
        },
        title: {
            text: line.title
        },
        subtitle: {
            text: line.subTitle
        },
        xAxis: {
            categories: line.xAxisCategories
        },
        yAxis: {
            title: {
                text: line.yAxisTitle
            }
        },
        plotOptions: {
            line: {
                dataLabels: {
                    enabled: true
                },
                enableMouseTracking: true
            }
        },
        series: line.series
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
});