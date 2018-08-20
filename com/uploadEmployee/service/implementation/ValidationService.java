package uploadEmployee.service.implementation;


import uploadEmployee.entity.TabMapXLSX;
import uploadEmployee.entity.Validation;
import uploadEmployee.service.intefaces.ValidationInterfaceService;
import uploadEmployee.dao.intefaces.ValidationInterfaceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service("ValidationInterfaceService")
@Transactional
public class ValidationService implements ValidationInterfaceService {
    @Autowired
    private ValidationInterfaceDao validationInterfaceDao;

    public void save(Validation obj) {
        validationInterfaceDao.save(obj);
    }

    public List<Validation> findAll() {
        return validationInterfaceDao.findAll();
    }

    public Validation findById(int id) {
        return (Validation)validationInterfaceDao.findById(id);
    }

    public void deleteById(int id) {
        validationInterfaceDao.deleteById(id);
    }

    public ArrayList<Validation> findByFieldString(String field, String value){
        return (ArrayList<Validation>)validationInterfaceDao.findByFieldString(field, value);
    }

    public ArrayList<Validation> findByFieldInteger(String field, Integer value) {
        return (ArrayList<Validation>)validationInterfaceDao.findByFieldInteger(field, value);
    }

    public ArrayList<Validation> findByTabMapXLSX (TabMapXLSX tabMapXLSX){
        return (ArrayList<Validation>)validationInterfaceDao.findByTabMapXLSX(tabMapXLSX);
    }

    public Validation findByLinkAndFieldString(TabMapXLSX tabMapXLSX, String fieldMap){
        return (Validation) validationInterfaceDao.findByLinkAndFieldString(tabMapXLSX, fieldMap);
    }
}
