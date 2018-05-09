package kz.pavershin.services.impl;

import kz.pavershin.exceptions.InputValidationException;
import kz.pavershin.models.Supplier;
import kz.pavershin.repository.SupplierDAO;
import kz.pavershin.services.GlobalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService implements GlobalService<Supplier> {

    @Autowired
    private SupplierDAO supplierDAO;

    @Override
    public List<Supplier> findAll() {
        return  supplierDAO.findAll();
    }

    @Override
    public void save(Supplier supplier) throws InputValidationException {
        supplierDAO.save(supplier);
    }

    @Override
    public Supplier getById(Long id) {
        return supplierDAO.findById(id);
    }
}
