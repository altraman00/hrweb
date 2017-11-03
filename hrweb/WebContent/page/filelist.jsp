<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>消息提示</title>
<script src="../js/jquery-3.2.1.min.js" type="text/javascript"></script>
<script type="text/javascript">
	function a() {
		alert(88);
		$.ajax({
			url : "FileListServlet",//servlet文件的名称  
			type : "GET",
			success : function(e) {
				alert("servlet调用成功！");
			}
		});
	}
</script>
</head>
<body onload="a()">
	<form action="<%=request.getContextPath() %>/servlet/FileListServlet" method="post" enctype="multipart/form-data" >
       	上传人名:<input type="text" name="name" /><br/>      
    </form>
</body>
</html>


