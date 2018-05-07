function filterTable() {
    // Declare variables
    var input, filter, table, tr, td, i, col;
    input = document.getElementById("searchInput");
    filter = input.value.toUpperCase();
    table = document.getElementById("productTable");
    tr = table.getElementsByTagName("tr");

    if($.isNumeric(filter)){
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