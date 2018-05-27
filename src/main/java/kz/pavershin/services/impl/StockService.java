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

    public Stock getByProduct(Product product){
        return stockDAO.findByProduct(product);
    }

    public void save(Product product, Integer quantity, boolean isSetQuantity) throws InputValidationException{
        Stock stock = getByProduct(product);
        if(stock != null){
            if (!isSetQuantity) {
                stock.setQuantity(stock.getQuantity() + quantity);
            } else {
                stock.setQuantity(quantity);
            }
        } else {
            stock = new Stock();
            stock.setProduct(product);
            stock.setQuantity(quantity);
        }
        save(stock);
    }
}
