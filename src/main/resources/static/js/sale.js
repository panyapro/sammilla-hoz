var saleProducts = [];
var lastSelectedProduct = {};

var counter = 0;

function split(val) {
    return val.split(/,\s*/);
}

function extractLast(term) {
    return split(term).pop();
}

function insertData(name, sellingPrice) {
    var c = counter + 1;
    var totalAmount = saleProducts[counter].quantity * saleProducts[counter].sellingPrice;
    document.getElementById("saleProductTable").insertRow(-1).innerHTML = '' +
        '<td>' + c + '</td>' +
        '<td>' + name + '</td>' +
        '<td><input onkeyup="changeData(\'' + counter + '\', this,\'sellingPrice\')" class="form-control" value="' + sellingPrice + '"</td>' +
        '<td><input onkeyup="changeData(\'' + counter + '\', this,\'quantity\')" class="form-control" value="' + saleProducts[counter].quantity + '"</td>' +
        '<td>' + totalAmount + '</td>';
    counter++;
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
    calculateTotal(rowNumber);
}

function calculateTotal(rowNumber) {
    document.getElementById("saleProductTable").rows[parseInt(rowNumber) + 1].cells[4].innerHTML =
        saleProducts[rowNumber].quantity * saleProducts[rowNumber].sellingPrice;
}

function clearTable(){
    $("#saleProductTable").find("tr:gt(0)").remove();
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
                        lastSelectedProduct = item;
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
            if (term.length < 1) {
                return false;
            }
        },
        focus: function () {
            // prevent value inserted on focus
            return false;
        },
        select: function (event, ui) {
            lastSelectedProduct.tableId = counter;
            lastSelectedProduct.quantity = 1;
            saleProducts.push(lastSelectedProduct);
            insertData(lastSelectedProduct.name, lastSelectedProduct.sellingPrice);
            $("#searchProduct").val("");

            return false;
        }
    });

});