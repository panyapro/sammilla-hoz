package kz.pavershin.services.impl;

import kz.pavershin.exceptions.InputValidationException;
import kz.pavershin.models.Category;
import kz.pavershin.repository.CategoryDAO;
import kz.pavershin.services.GlobalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements GlobalService<Category> {

    @Autowired
    private CategoryDAO categoryDAO;

    @Override
    public List<Category> findAll() {
        return categoryDAO.findAllBy();
    }

    @Override
    public void save(Category category) throws InputValidationException {
        Category oldCategory = getByName(category.getName());
        if(oldCategory != null){
            throw new InputValidationException("Категория с таким названием уже есть");
        }
        categoryDAO.save(category);
    }

    @Override
    public Category getById(Long id) {
        return categoryDAO.getById(id);
    }

    public Category getByName(String name){
        return categoryDAO.getByName(name);
    }
}
