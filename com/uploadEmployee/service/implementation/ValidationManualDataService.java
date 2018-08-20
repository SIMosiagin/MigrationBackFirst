package uploadEmployee.service.implementation;

import uploadEmployee.entity.ValidationManualData;
import uploadEmployee.service.intefaces.ValidationManualDataInterfaceService;
import uploadEmployee.dao.intefaces.ValidationManualDataInterfaceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service("ValidationManualDataInterfaceService")
@Transactional
public class ValidationManualDataService implements ValidationManualDataInterfaceService {
    @Autowired
    private ValidationManualDataInterfaceDao validationManualDataInterfaceDao;

    public void save(ValidationManualData obj) {
        validationManualDataInterfaceDao.save(obj);
    }

    public ArrayList<ValidationManualData> findAll() {
        return validationManualDataInterfaceDao.findAll();
    }

    public ValidationManualData findById(int id) {
        return (ValidationManualData)validationManualDataInterfaceDao.findById(id);
    }

    public void deleteById(int id) {
        validationManualDataInterfaceDao.deleteById(id);
    }

    public ArrayList<ValidationManualData> findByFieldString(String field, String value){
        return (ArrayList<ValidationManualData>)validationManualDataInterfaceDao.findByFieldString(field, value);
    }

    public ArrayList<ValidationManualData> findByFieldInteger(String field, Integer value) {
        return (ArrayList<ValidationManualData>)validationManualDataInterfaceDao.findByFieldInteger(field, value);
    }
}
