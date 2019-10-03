<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%--<jsp:useBean id="items" scope="request" type="java.util.List<mateacademy.internetshop.model.Item>"/>--%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AdminItems</title>
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

<h1>You can make operations with items</h1>

<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Price</th>
        <th>Update</th>
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
                <a href="/internet_shop_war_exploded/servlet/updateItem?item_id=${item.itemId}">Update</a>
            </td>
            <td>
                <a href="/internet_shop_war_exploded/servlet/deleteItem?item_id=${item.itemId}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
<form action="/internet_shop_war_exploded/servlet/addItem">
    <div class="container">
        <button type="submit" class="registerbtn">Add new item</button>
    </div>
</form>
</body>
</html>
