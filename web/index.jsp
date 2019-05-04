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
    <title>Insecure and secure XSS examples</title>
</head>
<body>
<div>
    <p>Examples of insecure and secure XSS pages:</p>
    <div>
        <p>JSP Examples:</p>
        <ul>
            <li><a href="insecure_xss.jsp?xss_code=<script>alert('HACKED: this page is easily hacked')</script>">Insecure
                Xss
                example</a></li>
            <li><a href="secure_xss.jsp?xss_code=<script>alert('HACKED: this page is easily hacked')</script>">Secure
                Xss example</a></li>
        </ul>
    </div>
    <div>
        <p>Servlet Examples:</p>
        <ul>
            <li>
                <a href="default?xss_mode=insecure&xss_code=<script>alert('HACKED: this page is easily hacked')</script>">Insecure
                    Xss
                    example</a></li>
            <li><a href="default?xss_mode=secure&xss_code=<script>alert('HACKED: this page is easily hacked')</script>">Secure
                Xss example</a></li>
        </ul>
    </div>
</div>
<p/>
<div>
    <p>Examples of insecure and secure CSRF pages:</p>
    <div>
        <p>Servlet Examples:</p>
        <ul>
            <li>
                <a href="csrf?csrf_mode=insecure">Insecure CSRF example</a>
            </li>
            <li>
                <a href="csrf?csrf_mode=secure">Secure CSRF example</a>
            </li>
        </ul>
    </div>
</div>
</body>
</html>
