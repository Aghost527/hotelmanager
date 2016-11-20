<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>


<link href="${ctxStatic}/js/bootstrap/2.3.2/custom/bootstrap-img.css" rel="stylesheet" type="text/css"/>

<script type="text/javascript" src="${ctxStatic}/app/layout/west.js?_=${sysInitTime}" charset="utf-8"></script>


<%-- easyui-layout布局 --%>
<div class="easyui-layout" fit="true" style="margin: 0px;border: 0px;overflow: hidden;width:100%;height:100%;">
    <div data-options="region:'north',title:'过滤条件',collapsed:false,split:false,border:false"
         style="padding: 0px; height: 70px;width:100%; overflow-y: hidden;">
		 
	<form id="search_form" style="padding: 5px;">
	<input type="text"  name="id" maxLength="25"
                      class="easyui-validatebox textbox eu-input" placeholder="请输入查询的id..." value='3477533311'
                      onkeydown="if(event.keyCode==13)search()" style="width: 160px" />
		<a class="easyui-linkbutton" href="#" data-options="iconCls:'easyui-icon-search',width:100,height:28,onClick:search">查询</a>
        
	</form>
	
    </div>
    <!-- 中间部分 列表 -->
    <div data-options="region:'center',split:true" style="overflow: hidden;">
        <div class="easyui-layout" fit="true" style="margin: 0px;border: 0px;overflow: hidden;width:100%;height:100%;">
	 		
			<div id="container" style="margin: 0px;border: 0px;overflow: hidden;width:100%;height:100%;"></div>
			
        </div>
    </div>
</div>

<script type="text/javascript"  charset="utf-8">




	var $search_form;
	$search_form = $('#search_form').form();
	var myChart ;
	


function search(){
	var dom = document.getElementById("container");
	myChart = echarts.init(dom);

     
        
    var app = {};
    option = null;
    myChart.showLoading();
    
    // $.get('/admin/sys/echarts/graph_data', $.serializeObject($search_form),function (graph) {
		 $.get('/admin/sys/echarts/graph_data', $("#search_form").serialize(),function (graph) {
        
        myChart.hideLoading();
		//myChart.on("click",function (param){ alert( );});
  
        var categories = [{name: '强烈推荐',symbol:'circle'}, {name: '可选推荐',symbol:'rect'}];

		
		
		var i=0;
        graph.nodes.forEach(function (node) {
            node.itemStyle = 'normal';
            node.value = node.symbolSize;
			node.draggable = true;
            node.categorie= (node.symbolSize%2);
			node.label = {
                normal: {
                    show: node.symbolSize > 50
                }
            };
            node.onclick=function(){alert();}
            node.onClick=function(){alert();}
           // node.symbol='image://http://tp4.sinaimg.cn/'+ node.id+ '/180/1/1';
            node.itemStyle.borderColor="#abc";
            node.itemStyle.borderWidth=5;
            node.opacity=0; 
        });
		
		
	
		
        option = {
            title: {
                text: '好友推荐',
                subtext: '基于二度人脉算法',
                top: 'bottom',
                left: 'right'
            },
			   visualMap: {
        min: 20,
        max: 70,
        left: 'right',
        top: 'top',
        text: ['高','低'],           // 文本，默认为数值文本
		orient:'horizontal',
		handlePosition: 'right',
		
        calculable: true
			},
            tooltip: {},
            legend: [{
               
                data: categories.map(function (a) {
                    return a.name;
                })
            }],
            animationDuration: 1500,
            animationEasingUpdate: 'quinticInOut',
            series: [
                {
                    name: '好友推荐',
                    type: 'graph',
                    layout: 'force',
					force: {

							//initLayout: 'circular',
							repulsion: 3000,
							gravity: 0.8,
							edgeLength: 150,
							layoutAnimation: true,
							},
                    data: graph.nodes,
                    links: graph.links,
                    categories: categories,
                    nodeClick:' zoomToNode',
                    roam: true,
                    label: {
                        normal: {
                            position: 'right',
                            formatter: '{b}'
                        }
                    },
                    lineStyle: {
                        normal: {
                            curveness: 0
                        }
                    }
                }
            ]
        };

        myChart.setOption(option);
    }, 'json');
	;
    if (option && typeof option === "object") {
        var startTime = +new Date();
        myChart.setOption(option, true);
        var endTime = +new Date();
        var updateTime = endTime - startTime;
        console.log("Time used:", updateTime);
    }

}


</script>

