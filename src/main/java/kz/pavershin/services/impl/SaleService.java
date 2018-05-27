package kz.pavershin.services.impl;

import kz.pavershin.exceptions.InputValidationException;
import kz.pavershin.models.Sale;
import kz.pavershin.models.SaleProduct;
import kz.pavershin.repository.SaleDAO;
import kz.pavershin.services.GlobalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SaleService implements GlobalService<Sale>{

    @Autowired
    private SaleDAO saleDAO;

    @Autowired
    private SaleProductService saleProductService;

    @Autowired
    private AccountService accountService;

    @Override
    public List<Sale> findAll() {
        return saleDAO.findAllByOrderBySaleDateDesc();
    }

    @Override
    public void save(Sale sale) {
        saleDAO.save(sale);
    }

    public void save(List<SaleProduct> saleProducts){
        Double totalAmount = 0.0;
        for(SaleProduct saleProduct : saleProducts){
            totalAmount += (saleProduct.getSellingPrice() * saleProduct.getQuantity());
        }

        Sale sale = new Sale();
        sale.setAmount(totalAmount);
        sale.setSaleDate(new Date());
        save(sale);

        for(SaleProduct saleProduct : saleProducts){
            saleProduct.setSaleId(sale);
            try {
                saleProductService.save(saleProduct);
            } catch(InputValidationException e){
                System.err.println("Failed create saleProduct " + e);
            }
        }

        accountService.save(sale.getAmount(), true);
    }

    @Override
    public Sale getById(Long id) {
        return saleDAO.findById(id);
    }
}
