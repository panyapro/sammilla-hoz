<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags" %>
<html>

<head>

    <link href="/css/bootstrap.min.css" rel="stylesheet"/>
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
    <h1>Создание категории</h1>
    <nav:valid-message/>
    <form action="/sammilla/product/category/create" method="post">
        <label for="name">Название</label>
        <input style="width: 25%" type="text" id="name" class="form-control" name="name">

        <button type="submit" class="btn btn-default" style="margin-top: 10px">Создать</button>
    </form>
</div>

</body>

</html>