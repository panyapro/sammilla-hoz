package kz.pavershin.repository;

import kz.pavershin.models.Sale;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleDAO extends CrudRepository<Sale, Long>{

    List<Sale> findAllByOrderBySaleDateDesc();

    Sale findById(Long id);
}
