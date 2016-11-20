<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en"> 
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<!-- <script type="text/javascript" src="${ctxStatic}/app/modules/sys/autoCombo.js?_=${sysInitTime}" charset="utf-8"></script> -->
<link href="${ctxStatic}/js/bootstrap/2.3.2/custom/bootstrap-img.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript">
</script>
<head> 
<title>搜索词自动完成</title> 
<style type="text/css"> 
#search_div{ 
text-align: center;
position:relative;
}
.autocomplete{
border: 1px solid #9ACCFB;
background-color: white;
text-align: left;
}
.autocomplete li{
list-style-type: none;
}
.clickable {
cursor: default;
}
.highlight {
background-color: #9ACCFB;
}
</style>

<script type="text/javascript" >
$(function(){
//取得div层
var $search_div = $('#search_div');
//取得输入框JQuery对象
var $searchInput = $search_div.find('#UserId');
//关闭浏览器提供给输入框的自动完成
$searchInput.attr('autocomplete','off');
//创建自动完成的下拉列表，用于显示服务器返回的数据,插入在搜索按钮的后面，等显示的时候再调整位置
var $autocomplete = $('<div class="autocomplete"></div>')
.hide()
.insertAfter('#UserId');
//清空下拉列表的内容并且隐藏下拉列表区
var clear = function(){
$autocomplete.empty().hide();
};
//注册事件，当输入框失去焦点的时候清空下拉列表并隐藏
$searchInput.blur(function(){
setTimeout(clear,500);
});
//下拉列表中高亮的项目的索引，当显示下拉列表项的时候，移动鼠标或者键盘的上下键就会移动高亮的项目，想百度搜索那样
var selectedItem = null;
//timeout的ID
var timeoutid = null;
//设置下拉项的高亮背景
var setSelectedItem = function(item){
//更新索引变量
selectedItem = item ;
//按上下键是循环显示的，小于0就置成最大的值，大于最大值就置成0
if(selectedItem < 0){
selectedItem = $autocomplete.find('li').length - 1;
}
else if(selectedItem > $autocomplete.find('li').length-1 ) {
selectedItem = 0;
}
//首先移除其他列表项的高亮背景，然后再高亮当前索引的背景
$autocomplete.find('li').removeClass('highlight')
.eq(selectedItem).addClass('highlight');
};
var ajax_request = function(){
//ajax服务端通信
$.ajax({
'url':'/admin/sys/echarts/nameList', //服务器的地址
'data':{'UserId':$searchInput.val()}, //参数
'dataType':'json', //返回数据类型
'type':'POST', //请求类型

'success':function(data){
if(data.length) {
//遍历data，添加到自动完成区
$.each(data, function(index,term) {
//创建li标签,添加到下拉列表中
$('<li></li>').text(term).appendTo($autocomplete)
.addClass('clickable')
.hover(function(){
//下拉列表每一项的事件，鼠标移进去的操作
$(this).siblings().removeClass('highlight');
$(this).addClass('highlight');
selectedItem = index;
},function(){
//下拉列表每一项的事件，鼠标离开的操作
$(this).removeClass('highlight');
//当鼠标离开时索引置-1，当作标记
selectedItem = -1;
})
.click(function(){
//鼠标单击下拉列表的这一项的话，就将这一项的值添加到输入框中
$searchInput.val(term);
//清空并隐藏下拉列表
$autocomplete.empty().hide();
});
});//事件注册完毕
//设置下拉列表的位置，然后显示下拉列表
var ypos = $searchInput.position().top;
var xpos = $searchInput.position().left;
$autocomplete.css('width',$searchInput.css('width'));
$autocomplete.css({'position':'relative','left':xpos  ,'top':ypos });
setSelectedItem(0);
//显示下拉列表
$autocomplete.show();
}
}
});
};
//对输入框进行事件注册
$searchInput
.keyup(function(event) {
//字母数字，退格，空格
if(event.keyCode > 40 || event.keyCode == 8 || event.keyCode ==32) {
//首先删除下拉列表中的信息
$autocomplete.empty().hide();
clearTimeout(timeoutid);
timeoutid = setTimeout(ajax_request,100);
}
else if(event.keyCode == 38){
//上
//selectedItem = -1 代表鼠标离开
if(selectedItem == -1){
setSelectedItem($autocomplete.find('li').length-1);
}
else {
//索引减1
setSelectedItem(selectedItem - 1);
}
event.preventDefault();
}
else if(event.keyCode == 40) {
//下
//selectedItem = -1 代表鼠标离开
if(selectedItem == -1){
setSelectedItem(0);
}
else {
//索引加1
setSelectedItem(selectedItem + 1);
}
event.preventDefault();
}
})
.keypress(function(event){
//enter键
if(event.keyCode == 13) {
//列表为空或者鼠标离开导致当前没有索引值
if($autocomplete.find('li').length == 0 || selectedItem == -1) {
return;
}
$searchInput.val($autocomplete.find('li').eq(selectedItem).text());
$autocomplete.empty().hide();
event.preventDefault();
}
})
.keydown(function(event){
//esc键
if(event.keyCode == 27 ) {
$autocomplete.empty().hide();
event.preventDefault();
}
});
//注册窗口大小改变的事件，重新调整下拉列表的位置
$(window).resize(function() {
var ypos = $searchInput.position().top;
var xpos = $searchInput.position().left;
$autocomplete.css('width',$searchInput.css('width'));
$autocomplete.css({'position':'relative','left':xpos + "px",'top':ypos +"px"});
});
});
</script>
</head>
<body>
<div id = "search_div">
<!-- <label for="UserId">请输入关键词</label><input type="text" id="UserId" name="UserId" /> -->
<input type="button" id="submit" value="搜索"/>
<div class="chartArea">
	
            <div>
                <dl class="itemBox">
                    <dt>二度人脉图</dt>
                    <dd class="clearfix">
                        <div id="2degreeTrans" style="height: 500px">

                       	        
	<form id="search_form" style="padding: 5px;">
	<div id = "search_div">
	<input type="text"  name="UserId" maxLength="25" id="UserId"
                       placeholder="请输入搜索的id..."   
                      onkeydown="if(event.keyCode==13)search()" style="width: 160px" />
		<a class="easyui-linkbutton" href="#" data-options="iconCls:'easyui-icon-search',width:100,height:28,onClick:search">搜索</a>
        </div>
	</form>
	
    </div>
        				<!-- <div class="easyui-layout" fit="true" style="margin: 0px;border: 0px;overflow: hidden;width:100%;height:100%;"> -->
	 		
			<div id="container" style="margin: 0px;border: 0px;overflow: hidden;width:100%;height:100%;"></div>
			
        </div>
    </div>

</div>
</body>

<script type="text/javascript"  charset="utf-8">

	// $('#UserId').combobox({
	// url:'${ctxAdmin}/sys/echarts/nameList?'+$("#search_form").serialize(),
	// valueField:'title',
	// textField:'name',
	// multiple:false,//是否可多选
	// editable:true,
	// onChange: function (n, o) {
	// 	refresh();
	// 	}
	// });
	


	var myChart ;
	


function search(){
	var dom = document.getElementById("container");
		myChart = echarts.init(dom);

     //   var ecConfig = require(['echarts']);
        
    var app = {};
    option = null;
    myChart.showLoading();
    
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
            // legend: [{
               
            //     data: categories.map(function (a) {
            //         return a.name;
            //     })
            // }],
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

