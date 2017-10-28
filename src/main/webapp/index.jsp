<%@ page language="java" contentType="text/html; charset=utf-8"  
    pageEncoding="utf-8"%>  
  <%@ page isELIgnored ="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">  
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">  
<title>文件上传下载</title>  
</head>  
<body>  
	
    <form action="http://localhost:8080/Statistic/FileUpload/gdpUpload" method="post" enctype="multipart/form-data">  
  
           选择文件:<input type="file" name="file" width="120px">  
           城市<input type="text" name="city">
          县    <input type="text" name="county"> 
          年<input type="text" name="year">
        <input type="submit" value="上传">  
    </form>  
    <hr>  
     <form action="http://localhost:8080/Statistic/FileDownload/fileDownLoad" method="get">  
        <input type="submit" value="下载"> enctype="multipart/form-data"  lll
    </form>  
    	
</body>  
</html> 