package uploadEmployee.service.implementation;


import uploadEmployee.entity.FieldMap;

import uploadEmployee.entity.TabMapXLSX;
import uploadEmployee.service.intefaces.FieldMapInterfaceService;
import uploadEmployee.dao.intefaces.FieldMapInterfaceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service("FieldMapInterfaceService")
@Transactional
public class FieldMapService implements FieldMapInterfaceService<TabMapXLSX> {

    @Autowired
    private FieldMapInterfaceDao fieldMapInterfaceDao;

    public void save(FieldMap obj) {
        fieldMapInterfaceDao.save(obj);
    }

    public List<FieldMap> findAll() {
        return fieldMapInterfaceDao.findAll();
    }

    public FieldMap findById(int id) {
        return (FieldMap)fieldMapInterfaceDao.findById(id);
    }

    public void deleteById(int id) {
        fieldMapInterfaceDao.deleteById(id);
    }

    public ArrayList<FieldMap> findByFieldString(String field, String value){
        return (ArrayList<FieldMap>)fieldMapInterfaceDao.findByFieldString(field, value);
    }

    public ArrayList<FieldMap> findByFieldInteger(String field, Integer value) {
       return (ArrayList<FieldMap>)fieldMapInterfaceDao.findByFieldInteger(field, value);
    }

    public ArrayList<FieldMap> findByLink(TabMapXLSX object) {
        return (ArrayList<FieldMap>)fieldMapInterfaceDao.findByLink(object);
    }

    public FieldMap findByLinkAndFieldString(TabMapXLSX tabMapXLSX, String tableField){
        return (FieldMap) fieldMapInterfaceDao.findByLinkAndFieldString(tabMapXLSX, tableField);
    }
}
