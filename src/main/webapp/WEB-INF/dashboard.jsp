<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Project Manager Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<h1>Welcome,  ${user.username}!</h1>
<a href="/logout">Log out</a>
<br>
<h5>TV Shows</h5>

<table class="table table-striped">
    <tr>
        <th class="text-primary">Show</th>
        <th class="text-primary">Network</th>
        <th class="text-primary">Average</th>
    </tr>
    <c:forEach items="${shows}" var="show">
        <tr>
            <td><a href="/shows/${show.id}">${show.title}</a></td>
            <td>${show.network}</td>
            <td>${show.average}</td>
        </tr>
    </c:forEach>
</table>
<br>
<a href="/shows/new" class="btn btn-info">Add a show</a>
</body>
</html>