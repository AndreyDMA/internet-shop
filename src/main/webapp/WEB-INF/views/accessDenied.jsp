<%--
  Created by IntelliJ IDEA.
  User: andre
  Date: 23-Sep-19
  Time: 12:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Access denied</title>
</head>
<body>
<h1>Sorry, requested page is denied for you...</h1>

<p>Press "Continue" button to continue shopping.</p>
<form action="/internet_shop_war_exploded/servlet/getAllItems">
    <div class="container">
        <button type="submit" class="registerbtn">Continue</button>
    </div>
</form>

<p>Press "Logout" button to change account.</p>
<form action="/internet_shop_war_exploded/logout">
    <div class="container">
        <button type="submit" class="registerbtn">Logout</button>
    </div>
</form>
</body>
</html>
