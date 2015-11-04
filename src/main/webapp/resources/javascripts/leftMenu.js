jQuery(document).ready(function($){

    $.post(basepath+"/index/leftTree",{},function(data){
        $("#leftMenu").treeview({
            data:data,
            showBorder:false,
            onNodeSelected:function(event,data){
                if("c" == data.urlType){
                    window.location.href = basepath+data.url+"&selectNode="+data.nodeId;
                }else if("l" == data.urlType){
                    window.location.href = basepath+data.url+"?selectNode="+data.nodeId;
                }else{

                }
            }
        });

        $('#leftMenu').treeview('expandAll');

        if(typeof(selectNode) != "undefined" && selectNode != -1){
            $("#leftMenu").treeview("selectNode",[ selectNode, { silent: true } ]);
        }else{
            var selectNode = $.getUrlParam("selectNode");
            if(selectNode != null){
                $("#leftMenu").treeview("selectNode",[parseInt(selectNode), { silent: true } ]);
            }
        }

    })
})