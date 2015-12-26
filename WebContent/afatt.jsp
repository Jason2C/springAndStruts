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
<div style="margin: 100px 100px;">
		<form action="login.action">
			<table border="1">
				<!-- <tr><td>&nbsp;&nbsp;考&nbsp;勤&nbsp;日&nbsp;&nbsp;:</td><td><input class="Wdate" type="text" onClick="WdatePicker()" name="attendance.attendance_day"></td> </tr>
				<tr><td>&nbsp;考勤时分秒&nbsp;:</td><td><input class="Wdate" type="text" onClick="WdatePicker()" name="attendance.attendance_every"></td> </tr> -->
				<%-- <tr><td>&nbsp;&nbsp;补&nbsp;勤&nbsp;人&nbsp;&nbsp;:</td><td>
					<select name="attendance.empid" id="select_k1" style="width: 171px;" class="xla_k">
						<c:forEach items="${list}" var="list">
						<option value="${list.uid}">${list.uname}</option>
						</c:forEach>
					</select>
				</td> </tr> --%>
				<tr><td>&nbsp;考勤时间点&nbsp;:</td><td><input class="Wdate" type="text" onClick="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss'})" name="attendance.attendance_time"></td> </tr>
				<tr><td>异常解释原因:</td><td><input type="text" name="attendance.exception_explain" > </td> </tr>
				<!-- <tr><td>&nbsp;&nbsp;审批意见&nbsp;&nbsp;:</td><td>同意<input type="radio" name="attendance.approval_opinion" value="同意" checked="checked">不同意<input type="radio" name="attendance.approval_opinion" value="不同意"></td> </tr> -->
				<tr><td colspan="2" style="padding-right: 10px"><input type="submit" value="提交"></td> </tr>
			</table>
		</form>
	</div>

</body>
</html>