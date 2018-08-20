package uploadEmployee.service.implementation;


import uploadEmployee.entity.TabMapXLSX;


import uploadEmployee.service.intefaces.TabMapXLSXInterfaceService;
import uploadEmployee.dao.intefaces.TabMapXLSXInterfaceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service("TabMapXLSXInterfaceService")
@Transactional
public class TabMapXLSXService implements TabMapXLSXInterfaceService {

    @Autowired
    private TabMapXLSXInterfaceDao tabMapXLSXInterfaceDao;

    public void save(TabMapXLSX obj) {
        tabMapXLSXInterfaceDao.save(obj);
    }

    public List<TabMapXLSX> findAll() {
        return tabMapXLSXInterfaceDao.findAll();
    }

    public TabMapXLSX findById(int id) {
        return (TabMapXLSX)tabMapXLSXInterfaceDao.findById(id);
    }

    public void deleteById(int id) {
        tabMapXLSXInterfaceDao.deleteById(id);
    }

    public ArrayList<TabMapXLSX> findByFieldString(String field, String value){
        return ( ArrayList<TabMapXLSX>)tabMapXLSXInterfaceDao.findByFieldString(field, value);
    }

    public TabMapXLSX findByFieldTwoString(String field, String value, String field2, String value2) {
        return (TabMapXLSX)tabMapXLSXInterfaceDao.findByFieldTwoString(field, value, field2, value2);
    }

    public  ArrayList<TabMapXLSX> findByFieldInteger(String field, Integer value) {
        return ( ArrayList<TabMapXLSX>)tabMapXLSXInterfaceDao.findByFieldInteger(field, value);
    }
}
