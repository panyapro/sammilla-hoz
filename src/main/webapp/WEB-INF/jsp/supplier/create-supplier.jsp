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
    <script type="text/javascript" src="/js/jquery.mask.js"></script>

</head>

<body>
<nav:navigation/>
<div class="container theme-showcase">
    <h1>Создание категории</h1>
    <nav:valid-message/>
    <form action="/sammilla/supplier/create" method="post">
        <label for="name">Название</label>
        <input style="width: 25%" type="text" id="name" class="form-control" name="name">

        <label for="phoneNumber">Номер телефона</label>
        <input style="width: 25%" type="text" id="phoneNumber" class="form-control" name="phoneNumber">

        <label for="description">Описание</label>
        <textarea style="width: 25%" type="text" id="description" class="form-control" name="description"></textarea>

        <button type="submit" class="btn btn-default" style="margin-top: 10px">Создать</button>
    </form>
    <script>
        $("#phoneNumber").mask("(999) 99-99-999");
    </script>
</div>

</body>

</html>