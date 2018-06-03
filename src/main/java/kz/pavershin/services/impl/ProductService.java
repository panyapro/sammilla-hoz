package kz.pavershin.services.impl;

import kz.pavershin.consts.AuxAttrKeys;
import kz.pavershin.exceptions.InputValidationException;
import kz.pavershin.models.SpecialAttr;
import kz.pavershin.models.Product;
import kz.pavershin.repository.ProductDAO;
import kz.pavershin.services.AuxAttrService;
import kz.pavershin.services.GlobalService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ProductService implements GlobalService<Product> {

    private ProductDAO productDAO;
    private AuxAttrService auxAttrService;
    private StockService stockService;

    @Override
    public List<Product> findAll() {
        return productDAO.findAll();
    }

    @Override
    public void save(Product product) throws InputValidationException {
        Product oldProduct = getByCode(product.getCode());
        if(oldProduct != null){
            throw new InputValidationException("Товар с таким штрихкодом существует");
        }
        createOrEditProduct(product);

        stockService.save(product, 0, true);
    }

    public Product edit(Product newProduct) throws InputValidationException{
        Product oldProduct = getByCode(newProduct.getCode());
        if(oldProduct == null){
            throw new InputValidationException("Товара с таким штрихкодом не существует");
        }
        newProduct.setId(oldProduct.getId());
        return createOrEditProduct(newProduct);
    }

    /**
     * PLEASE USE THIS METHOD FOR CREATE / EDIT PRODUCT
     * @param product
     * @return
     */
    private Product createOrEditProduct(Product product){
        product.setName(product.getName().toLowerCase());
        productDAO.save(product);

        return product;
    }

    public String generateBarCode(){
        SpecialAttr specialAttr = auxAttrService.getByKey(AuxAttrKeys.ATTR_NEXT_INNER_PRODUCT_BARCODE);
        String nextBarCode =  specialAttr.getValue();
        if(StringUtils.isEmpty(nextBarCode)){
            nextBarCode = "1";
        }
        Integer nextBarcodeInt = Integer.valueOf(nextBarCode);
        String newCode = generateBarcode(nextBarcodeInt);

        while(getByCode(newCode) != null){
            nextBarcodeInt++;
            newCode = generateBarcode(nextBarcodeInt);
        }
        specialAttr.setValue(String.valueOf(nextBarcodeInt + 1));
        auxAttrService.save(specialAttr);

        return newCode;
    }

    public List<Product> findByCodeOrName(String codeOrName) {
        if (isCheckBitRight(codeOrName)) {
            return new ArrayList(Arrays.asList(productDAO.findByCode(codeOrName)));
        } else {
            return productDAO.findByNameContains(codeOrName);
        }
    }

    /* Generates inner barcode by counter (id = count of inner products + 1) */
    private String generateBarcode(int id) {
        // Please note that barcodes start with 211
        String result = "21100000000" + id;
        if (result.length() > 12) {
            result = result.substring(0, result.length() - 12) + result.substring(2 * (result.length() - 12));
        }
        result += generateCheckBit(result);
        return result;
    }

    /* Generate check bit(last bit) for barcode using specific algorithm */
    public int generateCheckBit(String code) {
        int s = 0;
        int k = 0;
        for (int i = code.length() - 1; i >= 0; i--) {
            if (k % 2 == 0) {
                s += new Integer(code.charAt(i) + "") * 3;
            } else {
                s += new Integer(code.charAt(i) + "");
            }
            k++;
        }
        return (10 - s % 10) % 10;
    }

    public boolean isCheckBitRight(String code) {
        //is only digits
        if(StringUtils.isNumeric(code)){
            return false;
        }

        char[] codeChars = code.toCharArray();
        for (int i = 0; i < codeChars.length; i++) {
            if (Character.isSpaceChar(codeChars[i])) {
                return false;
            }
        }

        int s = 0;
        int k = 0;
        for (int i = code.length() - 2; i >= 0; i--) {
            if (k % 2 == 0) {
                s += new Integer(code.charAt(i) + "") * 3;
            } else {
                s += new Integer(code.charAt(i) + "");
            }
            k++;
        }
        if (new Integer(code.charAt(code.length() - 1) + "") != (10 - s % 10) % 10) {
            return false;
        }
        return true;
    }

    @Override
    public Product getById(Long id) {
        return null;
    }

    public Product getByCode(String code){
        return productDAO.findByCode(code);
    }

    public Product findById(Long id){
        return productDAO.findById(id);
    }

    @Autowired
    public void setProductDAO(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Autowired
    public void setAuxAttrService(AuxAttrService auxAttrService) {
        this.auxAttrService = auxAttrService;
    }

    @Autowired
    public void setStockService(StockService stockService) { this.stockService = stockService; }
}
