<%--
  Created by IntelliJ IDEA.
  User: andre
  Date: 17-Sep-19
  Time: 20:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add item</title>
</head>
<body>
<form action="/internet_shop_war_exploded/servlet/addItem">
    <div class="container">
        <h1>Add item</h1><%--@declare id="item_name"--%><%--@declare id="item_price"--%>

        <label for="item_name"><b>Name</b></label>
        <input type="text" placeholder="Item name" name="item_name" required>

        <label for="item_price"><b>Price</b></label>
        <input type="number" placeholder="Item price" name="item_price" required>
        <hr>

        <button type="submit" class="registerbtn">Add</button>
    </div>
</form>

<p>Check result of adding items by pressing "Items list" button.</p>
<form action="/internet_shop_war_exploded//servlet/adminItems">
    <div class="container">
        <button type="submit" class="registerbtn">Items list</button>
    </div>
</form>

<p>Or you can go to Internet Shop <a href="/internet_shop_war_exploded/index">start page</a>.</p>

</body>
</html>
