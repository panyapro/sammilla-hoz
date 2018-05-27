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
    <h1>Редактирование продукта ${product.name}</h1>
    <nav:valid-message/>
    <form action="/sammilla/product/edit/${product.id}" method="post">
        <label for="name">Название</label>
        <input style="width: 25%" type="text" id="name" class="form-control" name="name" value="${product.name}">

        <label for="code">Штрихкод</label>
        <div>
            <input style="width: 25%" type="text" id="code" class="form-control" name="code" value="${product.code}"
                   disabled>
        </div>

        <label for="arrivalCost">Закупочная цена</label>
        <input style="width: 25%" type="text" id="arrivalCost" class="form-control" name="arrivalCost"
               value="${product.arrivalCost}">

        <label for="sellingPrice">Продажная</label>
        <input style="width: 25%" type="text" id="sellingPrice" class="form-control" name="sellingPrice"
               value="${product.sellingPrice}">

        <label for="categoryId">Категория</label>
        <select id="categoryId" name="categoryId" class="form-control" style="width: 25%">
            <option value="${null}">Выберите категорию</option>
            <c:forEach items="${category}" var="category">
                <option value="${category.id}">${category.name}</option>
            </c:forEach>
        </select>

        <label for="supplierId">Поставщик</label>
        <select id="supplierId" name="supplierId" class="form-control" style="width: 25%">
            <option value="${null}">Выберите поставщика</option>
            <c:forEach items="${suppliers}" var="sup">
                <option value="${sup.id}">${sup.name}</option>
            </c:forEach>
        </select>

        <button type="submit" class="btn btn-default" style="margin-top: 10px"
                onclick="$('#code').prop('disabled', false);">Редактировать
        </button>
    </form>
</div>
<script>
    $("#categoryId").val(${product.category.id})
    $("#supplierId").val(${product.supplier.id})
</script>
</body>

</html>