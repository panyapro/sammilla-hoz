package kz.pavershin.controllers;

import kz.pavershin.consts.JspFilePath;
import kz.pavershin.consts.UrlMapping;
import kz.pavershin.exceptions.InputValidationException;
import kz.pavershin.models.Category;
import kz.pavershin.models.Product;
import kz.pavershin.services.impl.CategoryService;
import kz.pavershin.services.impl.ProductService;
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

    @RequestMapping(value = UrlMapping.Product.OVERVIEW_PRODUCT,
            method = RequestMethod.GET)
    public String productView(Map<String, Object> model) {
        model.put("products", productService.findAll());

        return JspFilePath.Product.OVERVIEW;
    }

    @RequestMapping(value = UrlMapping.Product.CREATE_PRODUCT)
    public String createProductView(Map<String, Object> model) {
        model.put("category", categoryService.findAll());

        return JspFilePath.Product.CREATE_PRODUCT;
    }

    @RequestMapping(value = UrlMapping.Product.EDIT_PRODUCT)
    public String editProductView(@PathVariable(value = "productId") String productId,
                                  Map<String , Object> model) {
        model.put("product", productService.findById(Long.valueOf(productId)));
        model.put("category", categoryService.findAll());
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
                                Map<String, Object> model) {
        model.put("category", categoryService.findAll());
        if (StringUtils.isEmpty(name) ||
                StringUtils.isEmpty(code) ||
//                arrivalCost == null ||
                sellingPrice == null ||
                categoryId == null) {
            model.put("message", "Не все данные введены");
            return JspFilePath.Product.CREATE_PRODUCT;
        }
        try {
            Category category = categoryService.getById(categoryId.longValue());
            Product product = new Product(name, code, arrivalCost, sellingPrice, category);
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
                                @PathVariable(value = "productId") String productId,
                                Map<String, Object> model) {
        model.put("product", productService.findById(Long.valueOf(productId)));
        model.put("category", categoryService.findAll());

        if (StringUtils.isEmpty(name) ||
                StringUtils.isEmpty(code) ||
//                arrivalCost == null ||
                sellingPrice == null ||
                categoryId == null) {
            model.put("message", "Не все данные введены");
            return JspFilePath.Product.EDIT_PRODUCT;
        }
        try {
            Category category = categoryService.getById(categoryId.longValue());
            Product product = new Product(name, code, arrivalCost, sellingPrice, category);
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
//
//    @RequestMapping(value = UrlMapping.Product.FIND_BY_ID)
//    public @ResponseBody
//    Product getProductListById(@RequestParam("id") Integer id) {
//        Product product =
//    }


    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
}
