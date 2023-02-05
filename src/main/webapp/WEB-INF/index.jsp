<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Project Manager</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<h1 class="text-center text-primary">TV Shows Database</h1>
<br>
<div class="container d-flex">
    <div class="container d-flex">
    <%--@elvariable id="newUser" type="javax"--%>
    <form:form action="/register" method="post" modelAttribute="newUser">
        <h3 class="text-primary">Register</h3>
        <p>
            <form:label path="username">Username:</form:label>
            <form:input path="username" class="form-control" type="text"></form:input>
            <form:errors path="username" class="text-danger"></form:errors>
        </p>
        <p>
            <form:label path="email">Email:</form:label>
            <form:input path="email" class="form-control" type="text"></form:input>
            <form:errors path="email" class="text-danger"></form:errors>
        </p>
        <p>
            <form:label path="password">Password:</form:label>
            <form:input path="password" class="form-control" type="password"></form:input>
            <form:errors path="password" class="text-danger"></form:errors>
        </p>
        <p>
            <form:label path="confirm">Confirm Password:</form:label>
            <form:input path="confirm" class="form-control" type="password"></form:input>
            <form:errors path="confirm" class="text-danger"></form:errors>
        </p>
        <button class="btn btn-primary">Submit</button>
    </form:form>
    </div>
<br>
    <div class="container d-flex">
    <%--@elvariable id="newLogin" type="newLogin"--%>
    <form:form action="/login" method="post" modelAttribute="newLogin">
        <h3 class="text-primary">Log in</h3>
        <p>
            <form:label path="email">Email:</form:label>
            <form:input path="email" class="form-control" type="text"></form:input>
            <form:errors path="email" class="text-danger"></form:errors>
        </p>
        <p>
            <form:label path="password">Password:</form:label>
            <form:input path="password" class="form-control" type="password"></form:input>
            <form:errors path="password" class="text-danger"></form:errors>
        </p>
        <button class="btn btn-primary">Submit</button>
    </form:form>
    </div>
</div>

</body>
</html>