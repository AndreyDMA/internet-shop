<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<jsp:useBean id="orders" scope="request" type="java.util.List<mateacademy.internetshop.model.Order>"/>
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
    <title>AllOrders</title>

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

<h1>All Orders</h1>

<table border="1">
    <tr>
        <th>ID</th>
        <th>User ID</th>

        <th>Delete</th>
    </tr>
    <c:forEach var="order" items="${orders}">
        <tr>
            <td>
                <c:out value="${order.orderId}" />
            </td>
            <td>
                <c:out value="${order.userId}" />
            </td>
<%--            <td>--%>
<%--                <c:out value="${order.items}" />--%>
<%--            </td>--%>
            <td>
                <a href="/internet_shop_war_exploded/servlet/deleteOrder?order_id=${order.orderId}">DELETE</a>
            </td>
        </tr>
    </c:forEach>
</table>
<p>You can continue <a href="/internet_shop_war_exploded/servlet/getAllItems">Shopping</a>.</p>

</body>
</html>