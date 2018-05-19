<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>

<head>

    <link href="/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="/css/fontawesome-all.css" rel="stylesheet"/>
    <link href="/css/bootstrap-theme.min.css" rel="stylesheet"/>
    <link href="/css/custom.css" rel="stylesheet"/>
    <script type="text/javascript" src="/js/jquery.min.js"></script>
    <script type="text/javascript" src="/js/jquery-ui.min.js"></script>
    <script type="text/javascript" src="/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/js/product.js"></script>

</head>

<body>

<nav:navigation/>

<div class="container theme-showcase">
    <h1>Обзор ревезии</h1>

    <div class="table-responsive">
        <table id="productTable" class="table table-striped">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Дата ревизии</th>
                <th scope="col">Сумма</th>
                <th scope="col">Просмотр</th>
            </tr>
            </thead>
            <tbody>
            <c:set var="tableIndex" value="1"/>
            <c:forEach items="${revisions}" var="revision">
                <tr>
                    <td>${tableIndex}</td>
                    <td>${revision.revisionDate}</td>
                    <td>${revision.amount}</td>
                    <td>
                        <a style="font-size: 30px;" href="/sammilla/revision/${revision.id}">
                            <i class="fas fa-pen-square"></i>
                        </a>
                    </td>
                </tr>
                <c:set var="tableIndex" value="${tableIndex + 1}"/>
            </c:forEach>
            </tbody>
        </table>
    </div>

</div>

</body>

</html>