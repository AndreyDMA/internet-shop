 <%--
  Created by IntelliJ IDEA.
  User: andre
  Date: 16-Sep-19
  Time: 15:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Index</title>
  </head>
  <body>
  <h1>Internet Shop</h1>

  <p>Press "Registration" button to sign up.</p>
  <form action="/internet_shop_war_exploded/registration">
    <div class="container">
      <button type="submit" class="registerbtn">Registration</button>
    </div>
  </form>

  <p>Press "Login" button to sign in.</p>
  <form action="/internet_shop_war_exploded/login">
    <div class="container">
      <button type="submit" class="registerbtn">Login</button>
    </div>
  </form>

  <p>If you have permission to administrate items - press "Admin" button.</p>
  <form action="/internet_shop_war_exploded/servlet/adminItems">
    <div class="container">
      <button type="submit" class="registerbtn">Admin</button>
    </div>
  </form>

  <p>Press "Load Test Users" button to load test users.</p>
  <form action="/internet_shop_war_exploded/inject">
    <div class="container">
      <button type="submit" class="registerbtn">Load Test Users</button>
    </div>
  </form>

  </body>
</html>
