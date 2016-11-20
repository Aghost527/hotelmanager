<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/meta.jsp"%>
<html style="height: 100%">
   <head>
       <meta charset="utf-8">
   </head>
   <body style="height: 100%; margin: 0">
       <div id="container" style="height: 100%"></div>
       
       <script type="text/javascript">
var dom = document.getElementById("container");
var myChart = echarts.init(dom);
var app = {};
option = null;
option = {
    title: {
        text: '基站ID: 9875896125224068'
    },
    tooltip: {},
    legend: {
        data:['基站覆盖强度 时间：2015-01-02']
    },
    xAxis: {
        data: ["00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23"]
    },
    yAxis: {},
    series: [{
        name: '基站覆盖强度 时间：2015-01-02',
        type: 'bar',
        data: [-53.524324324324326, -1.0, -1.0,-1.0, -1.0, -57.6,-57.473684210526315,-62.8,-95.0, -86.75,-85,-64.16666666666667,-54.46153846153846,-60.0,-60.0, -65.5945945945946,-70.71428571428571,-58.5
,-70.5,-70,-1.0,-61.7,-58.5]
    }]
};;
if (option && typeof option === "object") {
    var startTime = +new Date();
    myChart.setOption(option, true);
    var endTime = +new Date();
    var updateTime = endTime - startTime;
    console.log("Time used:", updateTime);
}
       </script>
   </body>
</html>