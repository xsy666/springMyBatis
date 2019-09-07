<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/7/4 0004
  Time: 下午 8:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>
    ${e.message }
    ${exception.message}
        <a href="${pageContext.request.contextPath}/login">返回到登录页</a>
</h1>
</body>
</html>
