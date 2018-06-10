package kz.pavershin.controllers;

import kz.pavershin.consts.JspFilePath;
import kz.pavershin.consts.UrlMapping;
import kz.pavershin.models.Revision;
import kz.pavershin.models.RevisionProduct;
import kz.pavershin.models.Sale;
import kz.pavershin.models.SaleProduct;
import kz.pavershin.services.impl.ProductService;
import kz.pavershin.services.impl.RevisionProductService;
import kz.pavershin.services.impl.RevisionService;
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
public class RevisionControllers {

    private RevisionService revisionService;
    private RevisionProductService revisionProductService;
    private ProductService productService;

    @RequestMapping(path = UrlMapping.Revision.OVERVIEW_REVISION)
    public String overviewRevision(Map<String, Object> model) {
        model.put("revisions", revisionService.findAll());
        return JspFilePath.Revision.OVERVIEW;
    }

    @RequestMapping(path = UrlMapping.Revision.CREATE_REVISION)
    public String revisionProductView(Map<String, Object> map) {
        map.put("disabled", true);
        return JspFilePath.Revision.CREATE_REVISION;
    }

    @RequestMapping(path = UrlMapping.Revision.OVERVIEW_REVISION_PRODUCT)
    public String overviewRevisionProduct(Map<String, Object> model,
                                      @PathVariable(value = "revisionId") String revisionId) {
        Revision revision = revisionService.getById(Long.valueOf(revisionId));
        Double totalAmount = 0.0;
        Double totalSellingAmount = 0.0;
        totalAmount += revision.getAmount();
        List<RevisionProduct> revisionProducts = revisionProductService.findByRevision(revision);
        for (RevisionProduct saleProduct : revisionProducts) {
            totalSellingAmount += saleProduct.getProduct().getArrivalCost();
        }
        model.put("revisionProducts", revisionProducts);
        model.put("totalAmount", totalAmount);
        model.put("totalSellingAmount", totalSellingAmount);
        return JspFilePath.Revision.OVERIVEW_REVISION_PRODUCT;
    }

    @RequestMapping(path = UrlMapping.Revision.CREATE_REVISION,
            method = RequestMethod.POST)
    public ResponseEntity createRevisionProduct(@RequestBody String stringJson) {
        List<RevisionProduct> revisionProductList = new ArrayList<>();
        JSONObject json = new JSONObject(stringJson);
        JSONArray revisionProducts = json.getJSONArray("jsonObjectName");
        for (int i = 0; i < revisionProducts.length(); i++) {
            JSONObject revisionProductObj = revisionProducts.getJSONObject(i);
            Integer arrivalCost = (Integer) (revisionProductObj.get("arrivalCost") == null ? 0 :revisionProductObj.get("arrivalCost"));
            Integer margin = (Integer) revisionProductObj.get("sellingPrice") - arrivalCost;
            Integer quantity = (Integer) revisionProductObj.get("quantity");

            RevisionProduct revisionProduct = new RevisionProduct();
            revisionProduct.setMargin(margin);
            revisionProduct.setQuantity(quantity);
            revisionProduct.setTotalMargin(quantity * margin);
            revisionProduct.setProduct(productService.getByCode((String) revisionProductObj.get("code")));
            revisionProduct.setSellingPrice((Integer)revisionProductObj.get("sellingPrice"));
            revisionProductList.add(revisionProduct);
        }

        revisionService.save(revisionProductList);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Autowired
    public void setRevisionService(RevisionService revisionService) {
        this.revisionService = revisionService;
    }
    @Autowired
    public void setRevisionProductService(RevisionProductService revisionProductService) {
        this.revisionProductService = revisionProductService;
    }
    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
}
