<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>用户登录</title>
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
    <h3>用户登录</h3>
    <div>
        <form enctype="application/x-www-form-urlencoded" method="POST" action="${pageContext.request.contextPath}/Login">
            <label for="username">用户名:</label>
            <input type="text" id="username" name="username" placeholder="用户名..." required>

            <label for="password">密码:</label>
            <input type="password" id="password" name="password" placeholder="密码..." required>

            <label for="usertype">用户类型:</label>
            <select name="usertype" id="usertype">
                <option value="admin">管理员</option>
                <option value="user">用户</option>
            </select>

            <input type="submit" value="登录">
        </form>

    </div>
</body>
</html>
