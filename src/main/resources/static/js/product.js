function filterTable() {
    // Declare variables
    var input, filter, table, tr, td, i, col;
    input = document.getElementById("searchInput");
    filter = input.value.toUpperCase();
    table = document.getElementById("productTable");
    tr = table.getElementsByTagName("tr");

    if(isCheckBitRight(filter)){
        col = 2;
    } else {
        col = 1;
    }

    for (i = 0; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td")[col];
        if (td) {
            if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
    }
}

function generateBarCode() {
    var input = document.getElementById("code");
    $.ajax({
        url: "/sammilla/product/generateBarCode",
        success: function(result){
        input.value = result;
    }});
}

function isCheckBitRight(code) {
    //validate is digit
    if(!$.isNumeric(code)){
        return false;
    }

    if (code == null || !code.hasOwnProperty("length")) {
        return false;
    }
    var s = 0;
    var k = 0;
    for (var i = code.length - 2; i >= 0; i--) {
        if (k % 2 === 0) {
            s += parseInt(code.charAt(i) + "") * 3;
        } else {
            s += parseInt(code.charAt(i) + "");
        }
        k++;
    }
    if (parseInt(code.charAt(code.length - 1) + "") !== (10 - s % 10) % 10) {
        return false;
    }
    return true;
}