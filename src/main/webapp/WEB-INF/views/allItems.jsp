<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%--<jsp:useBean id="items" scope="request" type="java.util.List<mateacademy.internetshop.model.Item>"/>--%>

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
    <title>AllItems</title>
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

<h1>All Items</h1>

<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Price</th>
        <th>Buy</th>
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
                <a href="/internet_shop_war_exploded/servlet/addItemToBucket?item_id=${item.itemId}">BUY</a>
            </td>
        </tr>
    </c:forEach>
</table>
<p>You can check items in your <a href="/internet_shop_war_exploded/servlet/bucket">Bucket</a>.</p>
</body>
</html>
