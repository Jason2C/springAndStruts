<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="bootstrap/sampleV3/jquery/jquery-1.8.3.min.js"></script>
<link href="bootstrap/sampleV3/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" media="screen">
<link href="bootstrap/css/bootstrap-datetimepicker.min.css"
	rel="stylesheet" media="screen">
</head>
<body>
	欢迎 ${user.uname}
	<a href="javascript:;">退出</a>
	<a href="javascript:;">个人考勤首页</a>
	<a href="javascript:;">查看部门考勤</a>
	<a href="javascript:;">审批考勤申请</a>
	<hr>
	个人考勤中心<br><br>
	<div style="background-color: #848484;width: 550px;"> 
		<div style="float: left;">
			<form id="form1">
				<table>
					<tr>
						<td>请选择时间:</td>
						<td><input type="text" id="datetimepicker" readonly data-date-format="yyyy-mm-dd"></td>
					</tr>
					<tr>
						<td><input type="button" value="补考勤"></td>
						<td></td>
					</tr>
				</table>
			</form>
		</div>
		<div >
			<form id="form2">
				<table>
					<tr>
						<td>查询指定月份:</td>
						<td><input type="text" id="datetimepicker1" readonly data-date-format="yyyy-mm"></td>
					</tr>
					<tr>
						<td><input type="button" value="查询"></td>
						<td></td>
					</tr>
				</table>
			</form>
		</div>
		<br>
		<div >
			<table border="1">
				<tr>
					<td style="width: 200px;">日期</td>
					<td style="width: 200px;">打卡时间</td>
					<td style="width: 150px;">说明原由</td>
				</tr>
				<c:forEach items="" >
				<tr>
					<td>日期</td>
					<td>打卡时间</td>
					<td>说明原由</td>
				</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	<br><br>
	月份考勤统计:
	<div style="background-color: #848484;width: 550px;">
		<table border="1">
			<tr><td colspan="4" style="width: 550px;"><span>该月总打卡数:0(次)</span></td> </tr>
			<tr><td><span>准点</span></td><td colspan="3"><span>迟到:0(次)</span></td></tr>	
			<tr><td rowspan="2"><span style="color: #006400;">0(次)</span></td><td><span>无故</span></td><td><span>因故</span></td><td><span>未被确认</span></td></tr>
			<tr><td><span style="color: red;">0</span></td><td><span style="color: #006400;">0</span><td><span style="color: #87CEFA;">0</span></td></tr>		
		</table>
	</div>
	
	
</body>
<script type="text/javascript"
	src="bootstrap/sampleV3/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="bootstrap/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript"
	src="bootstrap/js/locales/bootstrap-datetimepicker.fr.js"
	charset="UTF-8"></script>
<script type="text/javascript">
	$(function() {
		$('#datetimepicker').datetimepicker({
			weekStart : 1,
			autoclose : 1,
			//todayHighlight: 1,
			startView : 2,
			minView : 2
		});
		$('#datetimepicker1').datetimepicker({
			weekStart : 1,
			autoclose : 1,
			startView : 3,
			minView : 3
		});
	});
</script>
</html>