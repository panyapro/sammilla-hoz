package kz.pavershin.controllers;

import kz.pavershin.consts.JspFilePath;
import kz.pavershin.consts.UrlMapping;
import kz.pavershin.exceptions.InputValidationException;
import kz.pavershin.models.Category;
import kz.pavershin.models.Product;
import kz.pavershin.models.Supplier;
import kz.pavershin.services.impl.CategoryService;
import kz.pavershin.services.impl.ProductService;
import kz.pavershin.services.impl.SupplierService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class ProductControllers {

    private ProductService productService;
    private CategoryService categoryService;
    private SupplierService supplierService;

    @RequestMapping(value = UrlMapping.Product.OVERVIEW_PRODUCT,
            method = RequestMethod.GET)
    public String productView(Map<String, Object> model) {
        model.put("products", productService.findAll());

        return JspFilePath.Product.OVERVIEW;
    }

    @RequestMapping(value = UrlMapping.Product.CREATE_PRODUCT)
    public String createProductView(Map<String, Object> model) {
        model.put("category", categoryService.findAll());
        model.put("suppliers", supplierService.findAll());

        return JspFilePath.Product.CREATE_PRODUCT;
    }

    @RequestMapping(value = UrlMapping.Product.EDIT_PRODUCT)
    public String editProductView(@PathVariable(value = "productId") String productId,
                                  Map<String , Object> model) {
        model.put("product", productService.findById(Long.valueOf(productId)));
        model.put("category", categoryService.findAll());
        model.put("suppliers", supplierService.findAll());

        return JspFilePath.Product.EDIT_PRODUCT;
    }

    @RequestMapping(value = UrlMapping.Product.GENERATE_BARCODE)
    public @ResponseBody
    String generateBarCode() {
        return productService.generateBarCode();
    }

    @RequestMapping(value = UrlMapping.Product.CREATE_CATEGORY)
    public String createCategoryView() { return JspFilePath.Product.CREATE_CATEGORY; }



    @RequestMapping(value = UrlMapping.Product.CREATE_CATEGORY,
            method = RequestMethod.POST)
    public String createCategory(@RequestParam(name = "name", required = false) String name,
                                 Map<String, Object> model) {
        if (StringUtils.isEmpty(name)) {
            model.put("message", "Введите название");
            return JspFilePath.Product.CREATE_CATEGORY;
        }
        Category category = new Category();
        category.setName(name);
        try {
            categoryService.save(category);
        } catch (InputValidationException e) {
            model.put("message", e.getMessage());
            return JspFilePath.Product.CREATE_CATEGORY;
        }

        return "redirect:" + UrlMapping.Product.OVERVIEW_PRODUCT;
    }

    @RequestMapping(value = UrlMapping.Product.CREATE_PRODUCT,
            method = RequestMethod.POST)
    public String createProduct(@RequestParam(name = "name", required = false) String name,
                                @RequestParam(name = "code", required = false) String code,
                                @RequestParam(name = "arrivalCost", required = false) Integer arrivalCost,
                                @RequestParam(name = "sellingPrice", required = false) Integer sellingPrice,
                                @RequestParam(name = "categoryId", required = false) Integer categoryId,
                                @RequestParam(name = "supplierId", required = false) Integer supplierId,
                                Map<String, Object> model) {
        model.put("category", categoryService.findAll());
        model.put("suppliers", supplierService.findAll());

        if (StringUtils.isEmpty(name) ||
                StringUtils.isEmpty(code) ||
//                arrivalCost == null || // TODO delete comment after adding all products
                sellingPrice == null ||
                supplierId == null ||
                categoryId == null) {
            model.put("message", "Не все данные введены");
            return JspFilePath.Product.CREATE_PRODUCT;
        }
        try {
            Category category = categoryService.getById(categoryId.longValue());
            Supplier supplier = supplierService.getById(supplierId.longValue());
            Product product = new Product(name, code, arrivalCost, sellingPrice, category, supplier);
            productService.save(product);
        } catch (InputValidationException e) {
            model.put("message", e.getMessage());
            return JspFilePath.Product.CREATE_PRODUCT;
        }
        return "redirect:" + UrlMapping.Product.OVERVIEW_PRODUCT;
    }

    @RequestMapping(value = UrlMapping.Product.EDIT_PRODUCT,
            method = RequestMethod.POST)
    public String editProduct(@RequestParam(name = "name", required = false) String name,
                                @RequestParam(name = "code", required = false) String code,
                                @RequestParam(name = "arrivalCost", required = false) Integer arrivalCost,
                                @RequestParam(name = "sellingPrice", required = false) Integer sellingPrice,
                                @RequestParam(name = "categoryId", required = false) Integer categoryId,
                                @RequestParam(name = "supplierId", required = false) Integer supplierId,
                                @PathVariable(value = "productId") String productId,
                                Map<String, Object> model) {
        model.put("product", productService.findById(Long.valueOf(productId)));
        model.put("category", categoryService.findAll());
        model.put("suppliers", supplierService.findAll());

        if (StringUtils.isEmpty(name) ||
                StringUtils.isEmpty(code) ||
//                arrivalCost == null ||
                sellingPrice == null ||
                supplierId == null ||
                categoryId == null) {
            model.put("message", "Не все данные введены");
            return JspFilePath.Product.EDIT_PRODUCT;
        }
        try {
            Category category = categoryService.getById(categoryId.longValue());
            Supplier supplier = supplierService.getById(supplierId.longValue());
            Product product = new Product(name, code, arrivalCost, sellingPrice, category, supplier);
            productService.edit(product);
        } catch (InputValidationException e) {
            model.put("message", e.getMessage());
            return JspFilePath.Product.EDIT_PRODUCT;
        }
        return "redirect:" + UrlMapping.Product.OVERVIEW_PRODUCT;
    }

    @RequestMapping(value = UrlMapping.Product.AUTO_COMPLETE)
    public @ResponseBody
    List<Product> autoCompleteByQuery(@RequestParam("term") String query) {
        List<Product> productList = new ArrayList<>();
        if (StringUtils.isNotEmpty(query)) {
            productList = productService.findByCodeOrName(query);
        }
        return productList;
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Autowired
    public void setSupplierService(SupplierService supplierService) { this.supplierService = supplierService; }
}
