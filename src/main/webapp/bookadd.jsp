<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <link rel="stylesheet" href="css/bookc.css">
    <title>书籍录入</title>
</head>
<body>
<div class="container">
    <h2>书籍信息录入</h2>
    <form action="${pageContext.request.contextPath}/books" method="post" enctype="multipart/form-data">
        <div class="form-group">
            <label>书籍名称:</label>
            <input type="text" name="name" required>
        </div>
        <div class="form-group">
            <label>出版社:</label>
            <input type="text" name="publisher">
        </div>
        <div class="form-group">
            <label>编者:</label>
            <input type="text" name="editor">
        </div>
        <div class="form-group">
            <label>价格:</label>
            <input type="number" step="0.01" name="price">
        </div>
        <div class="form-group">
            <label>书籍类型:</label>
            <select name="type">
                <option value="小说">小说</option>
                <option value="科技">科技</option>
                <option value="教育">教育</option>
                <option value="其他">其他</option>
            </select>
        </div>
        <div class="form-group">
            <label>书籍介绍:</label>
            <textarea name="intro" rows="5"></textarea>
        </div>
        <div class="form-group">
            <label>封面图片:</label>
            <input type="file" name="cover" accept="image/*">
        </div>
        <button type="submit">提交</button>
    </form>
</div>
</body>
</html>