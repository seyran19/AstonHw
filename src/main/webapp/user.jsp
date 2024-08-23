<%@ page import="Lesson3.models.User" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User List</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid black;
        }
        th, td {
            padding: 8px;
            text-align: left;
        }
        .button {
            margin-top: 20px;
            padding: 10px 20px;
            font-size: 16px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        .button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
<h1>User List</h1>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Username</th>
    </tr>
    </thead>
    <tbody>
    <%
        // Получаем список пользователей из атрибутов запроса
        List<User> userList = (List<User>) request.getAttribute("userList");

        // Проверяем, что список пользователей не пустой
        if (userList != null && !userList.isEmpty()) {
            for (User user : userList) {
    %>
    <tr>
        <td><%= user.getId() %></td>
        <td><%= user.getName() %></td>
    </tr>
    <%
        }
    } else {
    %>
    <tr>
        <td colspan="2">No users found</td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>
<br><br>
<h2>Add user:</h2>
<!-- Форма для добавления нового пользователя -->
<form action="addUsers" method="post">
    <input type="text" name="name"
           placeholder="write user name"/>
    <br>
    <input type="submit">
</form>
<br><br>
<h2>Update user:</h2>
<!-- Форма для Обновления  пользователя -->
<form action="updateUser" method="post">
    <input type="text" name="id"
           placeholder="write user id"/>
    <br>
    <input type="text" name="name"
           placeholder="write new user name"/>
    <br>
    <input type="submit">
</form>

<h2>Delete user:</h2>
<form action="deleteUser" method="post">
    <input type="text" name="id"
           placeholder="write user id"/>
    <br>
    <input type="submit">
</form>



</body>
</html>
