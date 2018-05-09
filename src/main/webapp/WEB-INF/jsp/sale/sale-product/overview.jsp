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
    <h1>Обзор продажи</h1>

    <h2 class="sub-header">Сумма продажи - ${totalAmount} тг.</h2>
    <h2 class="sub-header">Сумма прибыли - ${totalSellingAmount} тг.</h2>
    <div class="table-responsive">
        <table id="productTable" class="table table-striped">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Название</th>
                <th scope="col">Закупочная цена</th>
                <th scope="col">Продажная цена</th>
                <th scope="col">Количество</th>
                <th scope="col">Итого</th>
                <th scope="col">Маржа</th>
            </tr>
            </thead>
            <tbody>
            <c:set var="tableIndex" value="1"/>
            <c:forEach items="${saleProducts}" var="saleProduct">
                <tr>
                    <td>${tableIndex}</td>
                    <td>${saleProduct.product.name}</td>
                    <td>${saleProduct.product.arrivalCost}</td>
                    <td>${saleProduct.sellingPrice}</td>
                    <td>${saleProduct.quantity}</td>
                    <td>${saleProduct.quantity * saleProduct.sellingPrice}</td>
                    <td>${saleProduct.totalMargin}</td>
                </tr>
                <c:set var="tableIndex" value="${tableIndex + 1}"/>
            </c:forEach>
            </tbody>
        </table>
    </div>

</div>
</body>
</html>