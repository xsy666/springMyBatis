<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/8/30
  Time: 11:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<span> user id:</span><span>${uId}</span><br>
<span> username:</span><span>${userName}</span><br>
<span> id:</span><span>${user.id}</span><br>
<span> username:</span><span>${user.userName}</span><br>
<span> creationDate:</span><span><fmt:formatDate value="${user.creationDate}" pattern="yyyy-MM-dd HH:mm:ss"/></span><br>
<span> gender:</span><span>${user.gender}</span><br>
<span> code:</span><span>${user.userCode}</span><br>
<span> phone:</span><span>${user.phone}</span><br>
</body>
</html>
