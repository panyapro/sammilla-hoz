package kz.pavershin.services.impl;

import kz.pavershin.exceptions.InputValidationException;
import kz.pavershin.models.Product;
import kz.pavershin.models.Stock;
import kz.pavershin.repository.StockDAO;
import kz.pavershin.services.GlobalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService implements GlobalService<Stock> {


    @Autowired
    private StockDAO stockDAO;


    @Override
    public List<Stock> findAll() {
        return null;
    }

    @Override
    public void save(Stock stock) throws InputValidationException {
        stockDAO.save(stock);
    }

    @Override
    public Stock getById(Long id) {
        return null;
    }

    public void save(Product product, Integer quantity) throws InputValidationException{
        Stock stock = stockDAO.findByProduct(product);
        if(stock != null){
            stock.setQuantity(stock.getQuantity() + quantity);
        } else {
            stock = new Stock();
            stock.setProduct(product);
            stock.setQuantity(quantity);
        }
        save(stock);
    }
}
