<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>JSP - Hello World</title>
  <style>
    body{
      text-align: center;
      font-size: 30px;
    }
  </style>
</head>
<body>
<h1><%= "Hello World!" %></h1>
<br/>
<a href="hello-servlet">Hello Servlet</a><br/>
<a href="html/login2.html">登录</a><br/>
<a href="html/register.html">多文件上传</a><br/>
<a href="html/downloader.html">下载</a><br/>

<a href="RandomImage">随机图片</a><br/>
<a href="html/schedule.html">cookies</a><br/>


<a href="Headers">头文件</a><br/>
<a href="Lazy">lazy</a><br/>
<a href="Worker">worker</a><br/>
<a href="Image">Image</a><br/>
<table><row1><th>姓名</th><th>年龄</th></row1></table>
<div class="login-box">
  <h2>书籍管理系统登录</h2>
  <form action="${pageContext.request.contextPath}/loginbook" method="post">
    <div class="input-group">
      <label>用户名</label>
      <input type="text" name="username" required>
    </div>
    <div class="input-group">
      <label>密码</label>
      <input type="password" name="password" required>
    </div>
    <div class="msg"><%= request.getAttribute("msg") == null ? "" : request.getAttribute("msg") %></div>
    <button type="submit">登录</button>
  </form>
</div>
</body>
</html>