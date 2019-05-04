<%--
  Created by IntelliJ IDEA.
  User: paul
  Date: 4/30/19
  Time: 11:13 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Insecure XSS</title>
</head>
<body>
Hello This is an insecure page!
Use the request parameter "xss_code" to inject HTML/ javascript into this page

<div>
    <p>XSS code execution results:</p>
    <%= request.getParameter("xss_code") %>
</div>
</body>
</html>
