package kz.pavershin.repository;

import kz.pavershin.models.Revision;
import kz.pavershin.models.RevisionProduct;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RevisionProductDAO extends CrudRepository<RevisionProduct, Long> {

    List<RevisionProduct> findAll();

    List<RevisionProduct> findByRevisionId(Revision revision);
}
