<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<jsp:useBean id="items" scope="request" type="java.util.List<mateacademy.internetshop.model.Item>"/>
<%--
  Created by IntelliJ IDEA.
  User: andre
  Date: 17-Sep-19
  Time: 18:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bucket</title>

    <style>
        table {
            font-family: "Times New Roman", sans-serif;
            border-collapse: collapse;
            width: 75%;
        }
        th {
            border: 1px solid black;
            text-align: center;
            padding: 8px;
        }
        td {
            border: 1px solid black;
            text-align: left;
            padding: 8px;
        }
        th {
            background-color: #dddddd;
        }
    </style>
</head>
<body>

<h1>Bucket</h1>

<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Price</th>
        <th>Delete</th>
    </tr>
    <c:forEach var="item" items="${items}">
        <tr>
            <td>
                <c:out value="${item.itemId}" />
            </td>
            <td>
                <c:out value="${item.name}" />
            </td>
            <td>
                <c:out value="${item.price}" />
            </td>
            <td>
                <a href="/internet_shop_war_exploded/servlet/deleteItemFromBucket?item_id=${item.itemId}">DELETE</a>
            </td>
        </tr>
    </c:forEach>
</table>

<form action="/internet_shop_war_exploded/servlet/createOrder">
    <div class="container">
        <button type="submit" class="registerbtn">Confirm order</button>
    </div>
</form>
<p>You can continue <a href="/internet_shop_war_exploded/servlet/getAllItems">Shopping</a>.</p>
</body>
</html>
