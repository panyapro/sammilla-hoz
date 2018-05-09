package kz.pavershin.consts;

public interface JspFilePath {

    interface Product {
        String PRODUCT_PATH  = "product";
        String OVERVIEW = PRODUCT_PATH + "/overview";
        String CREATE_PRODUCT = PRODUCT_PATH + "/create-product";
        String CREATE_CATEGORY = PRODUCT_PATH + "/create-category";
        String EDIT_PRODUCT = PRODUCT_PATH + "/edit-product";
    }

    interface Supplier {
        String SUPPLIER_PATH = "supplier";
        String CREATE_SUPPLIER = SUPPLIER_PATH + "/create-supplier";
    }

    interface Sale {
        String SALE_PATH = "sale";
        String OVERVIEW = SALE_PATH + "/overview";
        String CREATE_SALE = SALE_PATH + "/create-sale";
        String OVERIVEW_SALE_PRODUCT = SALE_PATH + "/sale-product/overview";
    }
}
