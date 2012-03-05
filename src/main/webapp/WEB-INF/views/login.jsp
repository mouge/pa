<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="common/common.jsp"%>
<html>
<head>
<%@ include file="common/head.jsp"%>
<style type="text/css">
.center{
margin-left:auto;
margin-right:auto;
text-align:center;
} 
</style>
<script type="text/javascript">
	$(document).ready(function() {
		setTimeout(function(){$("#msg").slideToggle(1000);},3000);
	 });
</script>  

</head>
<body>

<div class="center"><h1>方远房产卓越绩效管理平台</h1></div>
<div class="center">
<form action="login" method="post">
<img src="/pa/resources/img/logo.jpg">
<br/>
用户：<input type="text" name="username"  value="${loginPage.username}"/> 
<br/>

密码：<input type="password" name="password" value="" />
 <br/>
 <c:if test="${msg!=null}">
 <div id="msg" style="background-color:red;width:200px;margin-left:auto;margin-right:auto;">${msg}</div>
 </c:if>
 <input type="submit" value="登录"/>
 <input type="button" value="忘记密码" " onclick="javascript:window.open('/pa/fetchcsr','_self')"/>
</form>
</div> 
<div>${task.name}<br/>
${task.task}</div>
</body>
</html>
