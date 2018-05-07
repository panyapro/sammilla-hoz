package kz.pavershin.repository;

import kz.pavershin.models.Sale;
import kz.pavershin.models.SaleProduct;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleProductDAO extends CrudRepository<SaleProduct, Integer> {

    List<SaleProduct> findAll();

    List<SaleProduct> findBySaleId(Sale sale);
}
