<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "../view/color.jsp" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html>

<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="Content-Type" content="text/html" charset="utf-8">
<link href="../css/star.css" rel="stylesheet" type="text/css">
<script src="../js/jquery-1.11.3.min.js"></script>
<script type = "text/javascript" src = "../view/script.js"></script>
<link href = "../view/mystyle.css" rel = "stylesheet" type = text/css">



<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>방명록 글쓰기</title>
</head>
<body bgcolor = "${ bodyback_c }">
<center><b>글쓰기</b></center><br>
<form action = "writePro.do" method="post" name = "writeform" onsubmit="return writeSave()">

<table width='400' border='1' cellspacing='0' cellpadding='0' bgcolor="<%=bodyback_c %>"
align="center">

<tr>
	<td align = "right" colspan="2"  bgcolor="${value_c}">
	<a href = "list.do"> 글목록 보기</a>
	</td>
</tr>
<tr>
	<td width="70" bgcolor = ""${ value_c}"" align = "center"> ID </td>
	<td width="330"><input type="text" size= "10" maxlenth="10" name ="id"></td>
</tr>


<tr>
	<td width="70" bgcolor = ""${ value_c}"" align = "center"> 비밀번호</td>
	<td width="330"><input type="password" size= "10" maxlenth="10" name ="pwd"></td>
	
</tr>
<tr>
<td width="50" bgcolor = "${ value_c}" align = "center">별점</td>
<td name = "average">
<span class="star-input">
	<span class="input">
    	<input type="radio" name="star-input" value="1" id="p1">
    	<label for="p1">1</label>
    	<input type="radio" name="star-input" value="2" id="p2">
    	<label for="p2">2</label>
    	<input type="radio" name="star-input" value="3" id="p3">
    	<label for="p3">3</label>
    	<input type="radio" name="star-input" value="4" id="p4">
    	<label for="p4">4</label>
    	<input type="radio" name="star-input" value="5" id="p5">
    	<label for="p5">5</label>
  	</span>
  	<output for="star-input"><b>0</b>점</output>						
	<script src="../js/star.js"></script>
	

</span>
</td>
</tr>
<tr>
	<td width="70" bgcolor = ""${ value_c}"" align = "center"> 제 목</td>
	<td width="330"><input type="text" size= "10" maxlenth="10" name ="title"></td>
</tr>
<tr>
	<td width="50" bgcolor = ""${ value_c}"" align = "center"> 한줄평</td>
	<td width="100"><textarea  style= "resize: none;" rows="3" cols="40" name = "content"></textarea></td>
</tr>



<tr>
	<td align="center" colspan="2" bgcolor="${ value_c}">
	<input type = "submit" value = "글쓰기">
	<input type= "reset" value = "다시작성">
	<input type = "button" value = "목록보기" onclick="document.location.href='list.do'">
</tr>
</table>


</form>

</body>
</html>