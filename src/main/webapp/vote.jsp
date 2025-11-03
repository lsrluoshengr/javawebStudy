<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>投票 - 选班长系统</title>
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
<h2 class="vote-title">请选择班长候选人</h2>
<form action="VoteServlet" method="post" class="vote-form">
    <div class="candidate-option">
        <input type="radio" name="candidate" value="zhangsan" required id="zhangsan">
        <label for="zhangsan">张三</label>
    </div>
    <div class="candidate-option">
        <input type="radio" name="candidate" value="lisi" id="lisi">
        <label for="lisi">李四</label>
    </div>
    <div class="candidate-option">
        <input type="radio" name="candidate" value="wanger" id="wanger">
        <label for="wanger">王二</label>
    </div>
    <br>
    <input type="submit" value="提交投票" class="vote-submit">
    <a href="ResultServlet" class="result-link">查看当前结果</a>
</form>
</body>
</html>
