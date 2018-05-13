package kz.pavershin.services.impl;

import kz.pavershin.exceptions.InputValidationException;
import kz.pavershin.models.Sale;
import kz.pavershin.models.SaleProduct;
import kz.pavershin.repository.SaleProductDAO;
import kz.pavershin.services.GlobalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleProductService implements GlobalService<SaleProduct> {

    private SaleProductDAO saleProductDAO;
    private StockService stockService;

    @Override
    public List<SaleProduct> findAll() {
        return saleProductDAO.findAll();
    }

    @Override
    public SaleProduct getById(Long id) {
        return null;
    }

    @Override
    public void save(SaleProduct saleProduct) throws InputValidationException{
        saleProductDAO.save(saleProduct);
        stockService.save(saleProduct.getProduct(),-1 * saleProduct.getQuantity());

    }

    public List<SaleProduct> findBySale(Sale sale){
        return saleProductDAO.findBySaleId(sale);
    }

    @Autowired
    public void setSaleProductDAO(SaleProductDAO saleProductDAO) { this.saleProductDAO = saleProductDAO; }

    @Autowired
    public void setStockService(StockService stockService) { this.stockService = stockService; }
}
