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
    <script type="text/javascript" src="/js/sale.js"></script>

</head>

<body>

<nav:navigation/>

<div class="container theme-showcase">
    <h1>Создание продажи</h1>
    <div>
        <label for="searchProduct">
            Название или штрихкод
        </label>
        <input style="width: 25%" type="text" id="searchProduct" class="form-control" placeholder="Название/штрихкод"
               autofocus>

        <button style="float: right" class="btn btn-default" style="margin-top: 10px" onclick="createSale()">Создать</button>
    </div>
    <div style="clear:both"></div>
    <div class="table-responsive">
        <table id="saleProductTable" class="table table-striped">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Продукт</th>
                <%--<th scope="col">Закупочная цена</th>--%>
                <th scope="col">Продажная цена</th>
                <th scope="col">Количество</th>
                <th scope="col">Общая сумма</th>
            </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
    </div>

</div>
</body>
</html>