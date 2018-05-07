package kz.pavershin.repository;

import kz.pavershin.models.SpecialAttr;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuxAttrDAO extends CrudRepository<SpecialAttr, Long> {

    SpecialAttr findBySpecialKey(String key);
}
