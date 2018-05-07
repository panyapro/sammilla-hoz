<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags" %>

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
    <h1>Поиск продукта</h1>
    <label for="searchInput">
        Название или штрихкод
    </label>
    <input style="width: 25%" type="text" id="searchInput" class="form-control" placeholder="Название/штрихкод"
           autofocus
           onkeyup="filterTable()">

    <h2 class="sub-header">Продукты</h2>
    <div class="table-responsive">
        <table id="productTable" class="table table-striped">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Название</th>
                <th scope="col">Штрихкод</th>
                <th scope="col">Категория</th>
                <th scope="col">Закупочная</th>
                <th scope="col">Продажная</th>
                <th scope="col">Редактировать</th>
            </tr>
            </thead>
            <tbody>
            <c:set var="tableIndex" value="1"/>
            <c:forEach items="${products}" var="product">

                <tr>
                    <td>${tableIndex}</td>
                    <td>${product.name}</td>
                    <td>${product.code}</td>
                    <td>${product.category.name}</td>
                    <td>${product.arrivalCost}</td>
                    <td>${product.sellingPrice}</td>
                    <td>
                        <a style="font-size: 30px;" href="/sammilla/product/edit/${product.id}">
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