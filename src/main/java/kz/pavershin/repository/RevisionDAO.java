package kz.pavershin.repository;

import kz.pavershin.models.Revision;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RevisionDAO extends CrudRepository<Revision, Long> {

    List<Revision> findAllByOrderByRevisionDateDesc();

    Revision findById(Long id);
}
