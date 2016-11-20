<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<META content="fason,阿信" name=Author>
<title>动态提示的下拉框</title>
<style>
a{color:red;text-decoration:none;font-size:12px}
</style>
</head>
<body onload="Init()">
<center>
<h2>动态提示的下拉框</h2>
<hr>
<form name=frm>
<table>
  <tr>
    <td>请输入1或2或3或4或5进行测试：<br><input name="txt" style="width:100px" onkeyup="SelectTip(0)"> <input type="button" value="reset" onclick="SelectTip(1)"></td>
  </tr>
  <tr>
    <td>
    <span id="demo"><select name="demo" style="width:100px" size=10 onchange="txt.value=options[selectedIndex].text;">
 <option value="1">1</option>
 <option value="12">12</option>
 <option value="123">123</option>
 <option value="1234">1234</option>
 <option value="2">2</option>
 <option value="23">23</option>
 <option value="234">234</option>
 <option value="2345">2345</option>
 <option value="3">3</option>
 <option value="34">34</option>
 <option value="345">345</option>
 <option value="3456">3456</option>
 <option value="5">5</option>
 <option value="51">51</option>
 <option value="51w">51w</option>
 <option value="51wi">51wi</option>
 <option value="51win">51win</option>
 <option value="51windows">51windows</option>
 </select></span>
    </td>
  </tr>
 </form>
</table>
<hr>
<script language="JavaScript">
var TempArr=[];//存贮option

function Init(){
var SelectObj=document.frm.elements["demo"]
/*先将数据存入数组*/
with(SelectObj)
 for(i=0;i<length;i++)TempArr[i]=[options[i].text,options[i].value]
}

function SelectTip(flag){
var TxtObj=document.frm.elements["txt"]
var SelectObj=document.getElementById("demo")
var Arr=[]
with(SelectObj){
 var SelectHTML=innerHTML.match(/<[^>]*>/)[0]
 for(i=0;i<TempArr.length;i++)
 if(TempArr[i][0].indexOf(TxtObj.value)==0||flag)//若找到以txt的内容开头的，添option。若flag为true,对下拉框初始化
 Arr[Arr.length]="<option value='"+TempArr[i][1]+"'>"+TempArr[i][0]+"</option>"
 innerHTML=SelectHTML+Arr.join()+"</SELECT>"
}
}
</script>
</body>