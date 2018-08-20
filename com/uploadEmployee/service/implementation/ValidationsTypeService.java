package uploadEmployee.service.implementation;



import uploadEmployee.entity.Validation;
import uploadEmployee.entity.ValidationsType;
import uploadEmployee.service.intefaces.ValidationsTypeInterfaceService;
import uploadEmployee.dao.intefaces.ValidationsTypeInterfaceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service("ValidationsTypeInterfaceService")
@Transactional
public class    ValidationsTypeService implements ValidationsTypeInterfaceService {

    @Autowired
    private ValidationsTypeInterfaceDao validationsTypeInterfaceDao;

    public void save(ValidationsType obj) {
        validationsTypeInterfaceDao.save(obj);
    }

    public List<ValidationsType> findAll() {
        return validationsTypeInterfaceDao.findAll();
    }

    public ValidationsType findById(int id) {
        return (ValidationsType)validationsTypeInterfaceDao.findById(id);
    }

    public void deleteById(int id) {
        validationsTypeInterfaceDao.deleteById(id);
    }

    public ArrayList<ValidationsType> findByFieldString(String field, String value){
        return ( ArrayList<ValidationsType>)validationsTypeInterfaceDao.findByFieldString(field, value);
    }

    public  ArrayList<ValidationsType> findByFieldInteger(String field, Integer value) {
        return ( ArrayList<ValidationsType>)validationsTypeInterfaceDao.findByFieldInteger(field, value);
    }

    public ValidationsType findByType(String typeName){
        return (ValidationsType) validationsTypeInterfaceDao.findByType(typeName);
    }
}
