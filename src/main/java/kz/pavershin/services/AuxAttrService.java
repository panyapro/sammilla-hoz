package kz.pavershin.services;

import kz.pavershin.models.SpecialAttr;
import kz.pavershin.repository.AuxAttrDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuxAttrService {

    @Autowired
    private AuxAttrDAO auxAttrDAO;

    public SpecialAttr getByKey(String key){
        return auxAttrDAO.findBySpecialKey(key);
    }

    public void save(SpecialAttr specialAttr){
        auxAttrDAO.save(specialAttr);
    }
}
