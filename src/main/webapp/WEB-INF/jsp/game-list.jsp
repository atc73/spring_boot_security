<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Games</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body>

<h1>Games</h1>

<table class="table table-striped">
    <thead>
    <tr>
        <th>#</th>
        <th>Name</th>
        <th>Description</th>
        <th colspan="3">Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${games}" var="game">
        <tr>
            <td>${game.id}</td>
            <td>${game.name}</td>
            <td>${game.description}</td>
            <td>
                <a class="btn btn-primary" href="${pageContext.request.contextPath}/games/update?id=${game.id}">Edit</a>
            </td>
            <td>
                <form method="post" action="${pageContext.request.contextPath}/games/delete">
                    <input type="hidden" value="${game.id}" name="idGame">
                    <button class="btn btn-danger">Delete</button>
                </form>
            </td>
            <td>
                <a class="btn btn-success"
                   href="${pageContext.request.contextPath}/games/game-details?id=${game.id}">Détails</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
