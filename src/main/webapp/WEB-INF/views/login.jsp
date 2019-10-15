<%--
  Created by IntelliJ IDEA.
  User: andre
  Date: 19-Sep-19
  Time: 20:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<div>${errorMessage}</div>
<form action="/internet_shop_war_exploded/login" method="post">
    <div class="container">
        <%--@declare id="user_name"--%>
        <%--@declare id="user_surname"--%>
        <%--@declare id="login"--%>
        <%--@declare id="psw"--%>
        <%--@declare id="psw-repeat"--%>
        <h1>Register</h1>
        <p>Please fill in this form to sign in.</p>
        <hr>

        <label for="login"><b>Login</b></label>
        <input type="text" placeholder="Enter Email" name="login" required>

        <label for="psw"><b>Password</b></label>
        <input type="password" placeholder="Enter Password" name="psw" required>

        <button type="submit" class="registerbtn">Sign in</button>
    </div>

    <div class="container signin">
        <p>Don't have an account? <a href="/internet_shop_war_exploded/registration">Sign up</a>.</p>
    </div>
</form>
</body>
</html>
