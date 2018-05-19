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

    interface Supplier {
        String SUPPLIER = SAMMILLA_PATH + "/supplier";
        String CREATE_SUPPLIER = SUPPLIER + "/create";
    }

    interface Sale {
        String SALE = "/sale";
        String OVERVIEW_SALE = SAMMILLA_PATH + SALE;
        String CREATE_SALE = OVERVIEW_SALE + "/create";
        String OVERVIEW_SALE_PRODUCT = OVERVIEW_SALE + "/{saleId}";
    }

    interface Revision {
        String REVISION = "/revision";
        String OVERVIEW_REVISION = SAMMILLA_PATH + REVISION;
        String CREATE_REVISION = OVERVIEW_REVISION + "/create";
        String OVERVIEW_REVISION_PRODUCT = OVERVIEW_REVISION + "/{revisionId}";
    }
}
