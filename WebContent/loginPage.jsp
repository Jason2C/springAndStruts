<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<div style="position: relative; display: table-cell; vertical-align: middle;">
		<form action="login.action">
			帐号:<input name="user.uname"> <br>
			 密码:<input type="password" name="user.pwd">  <br>
			 <input type="submit" value="登陆">
		</form>
		<span style="color: red;">${msg}</span>
	</div>

</body>
</html>