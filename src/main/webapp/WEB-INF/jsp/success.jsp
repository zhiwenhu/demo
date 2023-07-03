<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%
    String contextPath = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + contextPath + "/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>Title</title>
</head>
<body>
<h2>你好，${username}</h2>
</body>
</html>