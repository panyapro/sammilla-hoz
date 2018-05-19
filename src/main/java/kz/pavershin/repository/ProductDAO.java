package kz.pavershin.repository;

import kz.pavershin.models.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ProductDAO extends CrudRepository<Product, Long> {

    List<Product> findAll();

    Product findById(Long id);

    List<Product> findByNameContains(String name);

    Product findByCode(String code);

}
