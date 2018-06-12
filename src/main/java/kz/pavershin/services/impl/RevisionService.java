package kz.pavershin.services.impl;

import kz.pavershin.exceptions.InputValidationException;
import kz.pavershin.models.Revision;
import kz.pavershin.models.RevisionProduct;
import kz.pavershin.repository.RevisionDAO;
import kz.pavershin.services.GlobalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RevisionService implements GlobalService<Revision>{

    @Autowired
    private RevisionDAO revisionDAO;

    @Autowired
    private RevisionProductService revisionProductService;

    @Autowired
    private AccountService accountService;

    @Override
    public List<Revision> findAll() {
        return revisionDAO.findAllByOrderByRevisionDateDesc();
    }

    @Override
    public void save(Revision revision) {
        revisionDAO.save(revision);
    }

    public void save(List<RevisionProduct> revisionProducts){
        Double totalAmount = 0.0;
        for(RevisionProduct revision : revisionProducts){
            totalAmount += (revision.getSellingPrice() * revision.getQuantity());
        }

        Revision revision = new Revision();
        revision.setAmount(totalAmount);
        revision.setRevisionDate(new Date());
        save(revision);

        for(RevisionProduct revisionProduct : revisionProducts){
            revisionProduct.setRevisionId(revision);
        }

        try {
            revisionProductService.save(revisionProducts);
        } catch(InputValidationException e){
            System.err.println("Failed creating revision product" + e);
        }

        accountService.save(revision.getAmount(), false);
    }

    @Override
    public Revision getById(Long id) {
        return revisionDAO.findById(id);
    }
}
