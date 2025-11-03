<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="css/bookc.css">
    <title>书籍信息</title>
</head>
<body>
<div class="book-card">
    <h2>书籍信息展示</h2>
    <div class="book-info">
        <img src="${book.cover}" class="book-cover">
        <div class="book-details">
            <p><strong>书名:</strong> ${book.name}</p>
            <p><strong>出版社:</strong> ${book.publisher}</p>
            <p><strong>编者:</strong> ${book.editor}</p>
            <p><strong>价格:</strong> ¥${book.price}</p>
            <p><strong>类型:</strong> ${book.type}</p>
            <p><strong>介绍:</strong> ${book.intro}</p>
        </div>
    </div>
    <a href="bookadd.jsp" class="back-btn">录入新书籍</a>
</div>
</body>
</html>