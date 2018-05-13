package kz.pavershin.repository;

import kz.pavershin.models.Product;
import kz.pavershin.models.Stock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockDAO extends CrudRepository<Stock, Long> {

    Stock findByProduct(Product product);
}
