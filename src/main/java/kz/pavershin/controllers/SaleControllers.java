package kz.pavershin.controllers;

import kz.pavershin.consts.JspFilePath;
import kz.pavershin.consts.UrlMapping;
import kz.pavershin.models.Sale;
import kz.pavershin.models.SaleProduct;
import kz.pavershin.services.impl.ProductService;
import kz.pavershin.services.impl.SaleProductService;
import kz.pavershin.services.impl.SaleService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class SaleControllers {

    @Autowired
    private SaleService saleService;
    @Autowired
    private SaleProductService saleProductService;
    @Autowired
    private ProductService productService;

    @RequestMapping(path = UrlMapping.Sale.OVERVIEW_SALE)
    public String overviewSale(Map<String, Object> model) {
        List<Sale> sales = saleService.findAll();
        Double totalAmount = 0.0;
        Double totalSellingAmount = 0.0;
        for (Sale sale : sales) {
            totalAmount += sale.getAmount();
            for (SaleProduct saleProduct : saleProductService.findBySale(sale)) {
                totalSellingAmount += saleProduct.getTotalMargin();
            }
        }

        model.put("sales", sales);
        model.put("totalAmount", totalAmount);
        model.put("totalSellingAmount", totalSellingAmount);
        return JspFilePath.Sale.OVERVIEW;
    }

    @RequestMapping(path = UrlMapping.Sale.OVERVIEW_SALE_PRODUCT)
    public String overviewSaleProduct(Map<String, Object> model,
                                      @PathVariable(value = "saleId") String saleId) {
        Sale sale = saleService.getById(Long.valueOf(saleId));
        Double totalAmount = 0.0;
        Double totalSellingAmount = 0.0;
        totalAmount += sale.getAmount();
        List<SaleProduct> saleProductList = saleProductService.findBySale(sale);
        for (SaleProduct saleProduct : saleProductList) {
            totalSellingAmount += saleProduct.getTotalMargin();
        }
        model.put("saleProducts", saleProductList);
        model.put("totalAmount", totalAmount);
        model.put("totalSellingAmount", totalSellingAmount);
        return JspFilePath.Sale.OVERIVEW_SALE_PRODUCT;
    }

    @RequestMapping(path = UrlMapping.Sale.CREATE_SALE)
    public String saleProductView() {
        return JspFilePath.Sale.CREATE_SALE;
    }

    @RequestMapping(path = UrlMapping.Sale.CREATE_SALE,
            method = RequestMethod.POST)
    public ResponseEntity createSaleProduct(@RequestBody String stringJson) {
        List<SaleProduct> saleProductList = new ArrayList<>();
        System.err.println(stringJson);
        JSONObject json = new JSONObject(stringJson);
        JSONArray saleProducts = json.getJSONArray("saleProducts");
        for (int i = 0; i < saleProducts.length(); i++) {
            JSONObject saleProductObj = saleProducts.getJSONObject(i);

            Integer margin = (Integer) saleProductObj.get("sellingPrice") - (Integer) saleProductObj.get("arrivalCost");
            Integer quantity = (Integer) saleProductObj.get("quantity");

            SaleProduct saleProduct = new SaleProduct();
            saleProduct.setMargin(margin);
            saleProduct.setQuantity(quantity);
            saleProduct.setTotalMargin(quantity * margin);
            saleProduct.setProduct(productService.getByCode((String) saleProductObj.get("code")));
            saleProduct.setSellingPrice((Integer)saleProductObj.get("sellingPrice"));
            saleProductList.add(saleProduct);
        }

        saleService.save(saleProductList);
        return new ResponseEntity(HttpStatus.OK);
    }
}