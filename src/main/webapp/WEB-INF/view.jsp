<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit my task</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<h1><c:out value="${show.title}"></c:out></h1>
<a href="/shows">Back to dashboard</a>
<br>
<p class="font-weight-bold">Posted by : ${show.user.username}</p>
<p>Network: ${show.network}</p>
<p>Description: ${show.description}</p>
<br>
<c:if test="${show.user.equals(user)}">
    <a href="/shows/${show.id}/edit" class="btn btn-info">Edit</a>
</c:if>
<br>
<hr>
<p>Ratings</p>
<br>

<table class="table table-striped">
    <tr>
        <th class="text-primary">Name</th>
        <th class="text-primary">Rating</th>
    </tr>
    <c:forEach items="${ratings}" var="rating">
        <tr>
            <td>${rating.user.username}</td>
            <td>${rating.ratingValue}</td>
        </tr>
    </c:forEach>
</table>
<br>
<%--@elvariable id="rating" type="javax"--%>
<form:form method="post" action="/shows/${show.id}" modelAttribute="rating">
    <div class="form-group">
        <p class="text-warning">Rating must be 0-5</p>
        <form:label path="ratingValue">Rating:</form:label>
        <form:input path="ratingValue" class="form-control" type="number"></form:input>
        <form:errors path="ratingValue" class="text-danger"></form:errors>
    </div>
    <button class="btn btn-info">Rate!</button>
</form:form>

</body>
</html>