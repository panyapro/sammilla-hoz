package kz.pavershin.repository;

import kz.pavershin.models.Supplier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierDAO extends CrudRepository<Supplier, Long>{

    Supplier findById(Long id);

    List<Supplier> findAll();
}
