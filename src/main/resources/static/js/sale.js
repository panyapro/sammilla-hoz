var newObjects = [];
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
    var totalAmount = newObjects[counter].quantity * newObjects[counter].sellingPrice;
    document.getElementById(globalTableName).insertRow(-1).innerHTML = '' +
        '<td>' + tableRowId + '</td>' +
        '<td>' + name + '</td>' +
        '<td><input onkeyup="changeData(\'' + counter + '\', this,\'sellingPrice\')" class="form-control" value="' + sellingPrice + '"</td>' +
        '<td><input onkeyup="changeData(\'' + counter + '\', this,\'quantity\')" class="form-control" value="' + newObjects[counter].quantity + '"</td>' +
        '<td>' + totalAmount + '</td>';
    counter++;
    calculateTotalAmount();
}

function changeData(rowNumber, input, column) {
    var val = input.value;
    switch (column) {
        case 'sellingPrice':
            newObjects[rowNumber].sellingPrice = Number(val);
            break;
        case 'quantity':
            newObjects[rowNumber].quantity = Number(val);
            break;
    }
    calculateTotalByRow(rowNumber);
    calculateTotalAmount();
}

function calculateTotalByRow(rowNumber) {
    document.getElementById(globalTableName).rows[parseInt(rowNumber) + 1].cells[4].innerHTML =
        newObjects[rowNumber].quantity * newObjects[rowNumber].sellingPrice;
}

function calculateTotalAmount() {
    var totalAmount = 0;
    for (var i = 0; i < newObjects.length; i++) {
        totalAmount += newObjects[i].quantity * newObjects[i].sellingPrice;
    }
    $("#totalAmount").text(totalAmount + " тг.");
}

function clearTable(){
    $("#" + globalTableName).find("tr:gt(0)").remove();
    $("#totalAmount").text(0 + " тг.");
    newObjects = [];
    counter = 0;
}

function create() {
    $.ajax({
        type: "POST",
        url: globalUrl,
        data: JSON.stringify({jsonObjectName: newObjects}),
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
                console.log(data);
                console.log($.isNumeric(request.term));
                    console.log(data.length);
                    if ($.isNumeric(request.term) && data[0] != null && data.length == 1) {
                        var selectedProduct = data[0];
                        selectedProduct.tableId = counter;
                        selectedProduct.quantity = 1;
                        newObjects.push(selectedProduct);
                        insertData(selectedProduct.name, selectedProduct.sellingPrice);
                        $("#searchProduct").val("");

                        allSearchedProducts = [];
                    } else if (data[0] == null) {
                        $("#searchProduct").val("");

                        allSearchedProducts = [];
                    } else {
                        response($.map(data, function (item) {
                            console.log(item);
                            allSearchedProducts.push(item);
                            return {
                                label: item.name,
                                value: item.id
                            }
                        }));
                    }
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
            newObjects.push(selectedProduct);
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