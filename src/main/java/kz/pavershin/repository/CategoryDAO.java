package kz.pavershin.repository;

import kz.pavershin.models.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryDAO extends CrudRepository<Category, Long> {

    List<Category> findAllBy();

    Category getById(Long id);

    Category getByName(String name);
}
