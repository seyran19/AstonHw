<%@ page import="Lesson4.Entity.Car" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cars List</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
        }
        th {
            background-color: #f2f2f2;
            text-align: left;
        }
    </style>
</head>
<body>
<h1>List of Cars</h1>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Model</th>
        <th>Type</th>
    </tr>
    </thead>
    <tbody>
    <%
        List<Car> cars = (List<Car>) request.getAttribute("car");
        if (cars != null && !cars.isEmpty()) {
            for (Car car : cars) {
    %>
    <tr>
        <td><%= car.getId() %></td>
        <td><%= car.getModel() %></td>
        <td><%= car.getType() %></td>
    </tr>
    <%
        }
    } else {
    %>
    <tr>
        <td colspan="3">No cars available</td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>
<br><br>

<h2>Add Car:</h2>
<!-- Форма для добавления нового пользователя -->
<form action="addCar" method="post">
    <input type="text" name="model"
           placeholder="write car's model"/>
    <input type="text" name="type"
           placeholder="write car's type"/>
    <br>
    <input type="submit">
</form>
<br><br>
<h1>Update Car</h1>
<form action="updateCar" method="post">
    <label for="id">ID:</label>
    <input type="text" id="id" name="id"><br><br>

    <label for="model">Model:</label>
    <input type="text" id="model" name="model" required><br><br>


    <label for="type">Type:</label>
    <input type="text" id="type" name="type" required><br><br>

    <input type="submit" value="Update Car">
</form>
<br><br>
<h2>Delete Car:</h2>
<!-- Форма для добавления нового пользователя -->
<form action="deleteCar" method="post">
    <input type="text" name="id"  required
           placeholder="write car's id"/>
    <br>
    <input type="submit">
</form>
</body>
</html>
