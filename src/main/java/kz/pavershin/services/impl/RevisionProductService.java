package kz.pavershin.services.impl;

import kz.pavershin.exceptions.InputValidationException;
import kz.pavershin.models.Revision;
import kz.pavershin.models.RevisionProduct;
import kz.pavershin.repository.RevisionProductDAO;
import kz.pavershin.services.GlobalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RevisionProductService implements GlobalService<RevisionProduct>{

    private RevisionProductDAO revisionProductDAO;
    private StockService stockService;

    @Override
    public List<RevisionProduct> findAll() {
        return revisionProductDAO.findAll();
    }

    @Override
    public void save(RevisionProduct revisionProduct) throws InputValidationException {
        revisionProductDAO.save(revisionProduct);
        stockService.save(revisionProduct.getProduct(), revisionProduct.getQuantity(), true);
    }

    public List<RevisionProduct> findByRevision(Revision revision){
        return revisionProductDAO.findByRevisionId(revision);
    }

    @Override
    public RevisionProduct getById(Long id) {
        return null;
    }

    @Autowired
    public void setRevisionProductDAO(RevisionProductDAO revisionProductDAO) { this.revisionProductDAO = revisionProductDAO; }

    @Autowired
    public void setStockService(StockService stockService) { this.stockService = stockService; }
}
