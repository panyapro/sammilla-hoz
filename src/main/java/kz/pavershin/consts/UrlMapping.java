package kz.pavershin.consts;

public interface UrlMapping {

    String SAMMILLA_PATH = "/sammilla";

    interface Product {
        String PRODUCT = "/product";
        String OVERVIEW_PRODUCT = SAMMILLA_PATH + PRODUCT;
        String CREATE_PRODUCT = OVERVIEW_PRODUCT + "/create";
        String CREATE_CATEGORY = OVERVIEW_PRODUCT + "/category/create";
        String GENERATE_BARCODE = OVERVIEW_PRODUCT + "/generateBarCode";
        String EDIT_PRODUCT = OVERVIEW_PRODUCT + "/edit/{productId}";
        String AUTO_COMPLETE = PRODUCT + "/autocomplete";
    }

    interface Sale {
        String SALE = "/sale";
        String OVERVIEW_SALE = SAMMILLA_PATH + SALE;
        String CREATE_SALE = OVERVIEW_SALE + "/create";
        String OVERVIEW_SALE_PRODUCT = OVERVIEW_SALE + "/{saleId}";
    }
}
