<%--
  Created by IntelliJ IDEA.
  User: paul
  Date: 4/30/19
  Time: 11:13 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Secure XSS</title>
</head>
<body>
<p>This is a page secured against XSS using Apache taglibs</p>

<p>
    Use the request parameter "xss_code" to try and send code, it will be cleansed and not executed.</p>
<p>
    The cleansing is performed by the apache standard taglib code:
</p>
<p>
    <i>c:out value="THIS_IS_THE_VALUE_TO_CLEANSE"</i>
</p>
</body>
</html>