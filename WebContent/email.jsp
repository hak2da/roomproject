<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html><head>
<meta charset="UTF-8">
<title>JSP</title>
</head>
<body>
	<center>
		<br /><br />
		<h5>인증 번호 7자리를 입력하세요</h5>
		
		<div>
			<form method="post" name="email">
				<input type="text" name="num"><br /><br />
				<input type="submit" value="인증하기">
			</form>
		</div>
</body>
</html>