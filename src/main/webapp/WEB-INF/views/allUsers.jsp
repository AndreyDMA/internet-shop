<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<jsp:useBean id="users" scope="request" type="java.util.List<mateacademy.internetshop.model.User>"/>
<%--
  Created by IntelliJ IDEA.
  User: andre
  Date: 16-Sep-19
  Time: 22:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AllUsers</title>

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

<h1>All Users</h1>

<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Surname</th>
        <th>Delete</th>
    </tr>
    <c:forEach var="user" items="${users}">
        <tr>
            <td>
                <c:out value="${user.userId}" />
            </td>
            <td>
                <c:out value="${user.name}" />
            </td>
            <td>
                <c:out value="${user.surname}" />
            </td>
            <td>
                <a href="/internet_shop_war_exploded/servlet/deleteUser?user_id=${user.userId}">DELETE</a>
            </td>
        </tr>
    </c:forEach>
</table>

<form action="/internet_shop_war_exploded/registration">
    <div class="container">
        <button type="submit" class="registerbtn">Add new user</button>
    </div>
</form>
<p>Start <a href="/internet_shop_war_exploded/servlet/getAllItems">shopping</a>.</p>

</body>
</html>
