<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script language="javascript" type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
<link href="My97DatePicker/skin/WdatePicker.css" rel="stylesheet" type="text/css">
</head>
<body>

欢迎 ${loginuser.uname}
	<a href="javascript:;">退出</a>
	<a href="javascript:;">个人考勤首页</a>
	<a href="javascript:;">查看部门考勤</a>
	<a href="javascript:;">审批考勤申请</a>
	<hr>
	审批考勤申请<br>${msg}<br>
	<div style="background-color: #848484;position:absolute;"> 
		<div>
		<form action="login.action">
			<table border="1">
				<tr><td>申请人</td><td>日期</td><td>打卡时间</td><td>申请原因</td><td>操作</td> </tr>
				<c:forEach items="">
				<tr><td><input type="text"></td><td><input type="text"></td><td><input type="text"></td><td><input type="text"></td><td><input type="text"></td> </tr>
				</c:forEach>
			</table>
		</form>
		</div>
	</div>
</body>
</html>