<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3">
<head>
    <meta charset="UTF-8"/>
    <title>Insert title here</title>
</head>
<body>
<p>${user.getUserId()}</p>
<p>${user.getUsername()}</p>
<p>${user.password}</p>
<p>${user.email}</p>
<h1 th:inlines="text">文件上传</h1>
<form action="fileUpload" method="post" enctype="multipart/form-data">
    <p>选择文件: <input type="file" name="fileName"/></p>
    <p><input type="submit" value="提交"/></p>
</form>
</body>
</html>
