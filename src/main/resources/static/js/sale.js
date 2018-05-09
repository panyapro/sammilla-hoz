var saleProducts = [];
var allSearchedProducts = [];

var counter = 0;

function split(val) {
    return val.split(/,\s*/);
}

function extractLast(term) {
    return split(term).pop();
}

function insertData(name, sellingPrice) {
    var tableRowId = counter + 1;
    var totalAmount = saleProducts[counter].quantity * saleProducts[counter].sellingPrice;
    document.getElementById("saleProductTable").insertRow(-1).innerHTML = '' +
        '<td>' + tableRowId + '</td>' +
        '<td>' + name + '</td>' +
        '<td><input onkeyup="changeData(\'' + counter + '\', this,\'sellingPrice\')" class="form-control" value="' + sellingPrice + '"</td>' +
        '<td><input onkeyup="changeData(\'' + counter + '\', this,\'quantity\')" class="form-control" value="' + saleProducts[counter].quantity + '"</td>' +
        '<td>' + totalAmount + '</td>';
    counter++;
    calculateTotalAmount();
}

function changeData(rowNumber, input, column) {
    var val = input.value;
    switch (column) {
        case 'sellingPrice':
            saleProducts[rowNumber].sellingPrice = Number(val);
            break;
        case 'quantity':
            saleProducts[rowNumber].quantity = Number(val);
            break;
    }
    calculateTotalByRow(rowNumber);
    calculateTotalAmount();
}

function calculateTotalByRow(rowNumber) {
    document.getElementById("saleProductTable").rows[parseInt(rowNumber) + 1].cells[4].innerHTML =
        saleProducts[rowNumber].quantity * saleProducts[rowNumber].sellingPrice;
}

function calculateTotalAmount() {
    var totalAmount = 0;
    for (var i = 0; i < saleProducts.length; i++) {
        totalAmount += saleProducts[i].quantity * saleProducts[i].sellingPrice;
    }
    $("#totalAmount").text(totalAmount + " тг.");
}

function clearTable(){
    $("#saleProductTable").find("tr:gt(0)").remove();
    $("#totalAmount").text(0 + " тг.");
    saleProducts = [];
    counter = 0;
}

function createSale() {
    $.ajax({
        type: "POST",
        url: "/sammilla/sale/create",
        data: JSON.stringify({"saleProducts": saleProducts}),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: clearTable()
    });
}

$(document).ready(function () {

    $("#searchProduct").autocomplete({
        source: function (request, response) {
            $.getJSON('/product/autocomplete', {
                    term: extractLast(request.term)
                }
                , function (data) {
                    response($.map(data, function (item) {
                        allSearchedProducts.push(item);
                        return {
                            label: item.name,
                            value: item.id
                        }
                    }))
                });
        },
        search: function () {
            // custom minLength
            var term = extractLast(this.value);
            if (term.length < 3) {
                return false;
            }
        },
        focus: function () {
            // prevent value inserted on focus
            return false;
        },
        select: function (event, ui) {
            var selectedProduct = findObjectByKey(allSearchedProducts, "id", ui.item.value);
            selectedProduct.tableId = counter;
            selectedProduct.quantity = 1;
            saleProducts.push(selectedProduct);
            insertData(selectedProduct.name, selectedProduct.sellingPrice);
            $("#searchProduct").val("");

            allSearchedProducts = [];

            return false;
        }
    });

});

function findObjectByKey(array, key, value) {
    for (var i = 0; i < array.length; i++) {
        if (array[i][key] === value) {
            return array[i];
        }
    }
    return null;
}