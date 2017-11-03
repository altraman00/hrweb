<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- <script src="js/jquery/jquery-3.2.1.min.js" type="text/javascript"></script> -->
<style type="text/css">
._box {
	width: 119px;
	height: 37px;
	background-color: #53AD3F;
	background-image: url(images/bg.png);
	background-repeat: no-repeat;
	background-position: 0 0;
	background-attachment: scroll;
	line-height: 37px;
	text-align: center;
	color: white;
	cursor: pointer;
}

.none {
	width: 0px;
	height: 0px;
	display: none;
}
</style>
<title>文件上传 /></title>
</head>
<body>
	<form action="<%=request.getContextPath() %>/servlet/FileUploadServlet" method="post" enctype="multipart/form-data" >
          上传人名:<input type="text" name="name" /><br/>      
          上传文件:<input type="file" name="file" /><br/>
          <input type="submit" name="submit" value="提交" />
      </form>
</body>
</html>

