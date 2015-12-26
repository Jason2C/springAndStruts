<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
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
	<a href="gofillAtt.action">审批考勤申请</a>
	<hr>
	查询部门考勤<br>${msg}<br>
	<div style="background-color: #848484;width: 580px;"> 
		<div style="float: left;">
			<form action="afillAtt.action" method="get">
				<table>
					<tr>
						<td>查询指定月份:</td>
						<td><input class="Wdate" type="text" name="attendance.attendance_time" onClick="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss'})"></td>
						<td>查询指定的组:</td>
						<td><input class="Wdate" type="text" name="attendance.attendance_time" onClick="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss'})"></td>
					</tr>
					<tr>
						<td><input type="submit" value="更改"></td>
						<td></td>
						<td><input type="submit" value="更改"></td>
						<td></td>
					</tr>
					<!-- <tr>
						<td>查询指定月份:</td>
						<td><input class="Wdate" type="text" onClick="WdatePicker({dateFmt: 'yyyy-MM', isShowToday: false, isShowClear: false})"></td>
					</tr>
					<tr>
						<td><input type="button" value="查询"></td>
						<td></td>
					</tr> -->
				</table>
			</form>
		</div>
		<br>
		<div >
			<table border="1">
				<tr>
					<td style="width: 200px;">日期</td>
					<td style="width: 200px;">打卡时间</td>
					<td style="width: 180px;">说明原由</td>
				</tr>
				<c:forEach items="${alist}" var="list">
				<tr>
					<td>${list.attendance_day}</td>
					<td>${list.attendance_every}</td>
					<td>${list.exception_explain}</td>
				</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>