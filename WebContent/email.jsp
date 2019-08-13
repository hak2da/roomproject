<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html><head>
<meta charset="UTF-8">
<title>JSP</title>
</head>
<script type="text/javascript">
	function check() {
		var email = $('#email').val();
		var id = $('#id').val();
		console.log(email);
		console.log(id); 
		/* alert(email + " , " + id) */
		
		$.ajax({
			type: 'post',
			url: 'EmailAction.to',
			data: { "email" : email, "id" : id},
			success: function(result) {
				if(result == 1) {
					
				}
			}
		})
	}
</script>

<body>
		<br /><br />
		<h5>인증 번호 7자리를 입력하세요</h5>
		
		<div>
		이쪽 타는지?<br>
			<form method="post" name="" onSubmit="return check();">
				<input type="button" value="이메일 발송"><br /><br />
				<input type="text" name="input">
				<input type="submit" value="인증확인">
			</form>
		</div>
</body>
</html>