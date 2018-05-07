package kz.pavershin.services.impl;

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

    @Override
    public List<SaleProduct> findAll() {
        return saleProductDAO.findAll();
    }

    @Override
    public SaleProduct getById(Long id) {
        return null;
    }

    @Override
    public void save(SaleProduct saleProduct) {
        saleProductDAO.save(saleProduct);
    }

    public List<SaleProduct> findBySale(Sale sale){
        return saleProductDAO.findBySaleId(sale);
    }

    @Autowired
    public void setSaleProductDAO(SaleProductDAO saleProductDAO) { this.saleProductDAO = saleProductDAO; }
}
