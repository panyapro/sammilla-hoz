package kz.pavershin.controllers;

import kz.pavershin.consts.JspFilePath;
import kz.pavershin.consts.UrlMapping;
import kz.pavershin.exceptions.InputValidationException;
import kz.pavershin.models.Category;
import kz.pavershin.models.Supplier;
import kz.pavershin.services.impl.SupplierService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class SupplierControllers {

    @Autowired
    private SupplierService supplierService;

    @RequestMapping(value = UrlMapping.Supplier.CREATE_SUPPLIER)
    public String createSupplierView() { return JspFilePath.Supplier.CREATE_SUPPLIER; }



    @RequestMapping(value = UrlMapping.Supplier.CREATE_SUPPLIER,
            method = RequestMethod.POST)
    public String createSupplier(@RequestParam(name = "name", required = false) String name,
                                 @RequestParam(name = "phoneNumber", required = false) String phoneNumber,
                                 @RequestParam(name = "description", required = false) String description,
                                 Map<String, Object> model) {
        if (StringUtils.isEmpty(name)) {
            model.put("message", "Введите название");
            return JspFilePath.Supplier.CREATE_SUPPLIER;
        }

        Supplier supplier = new Supplier();
        supplier.setDescription(description);
        supplier.setName(name);
        supplier.setPhoneNumber(phoneNumber);

        try {
            supplierService.save(supplier);
        } catch (InputValidationException e) {
            model.put("message", e.getMessage());
            return JspFilePath.Supplier.CREATE_SUPPLIER;
        }

        return "redirect:" + UrlMapping.Product.OVERVIEW_PRODUCT;
    }
}
