package uploadEmployee.service.intefaces;

import uploadEmployee.entity.TabMapXLSX;

import java.util.ArrayList;
import java.util.List;

public interface TabMapXLSXInterfaceService extends InterfaceService <TabMapXLSX> {

    @Override
    void save(TabMapXLSX obj);

    @Override
    public List<TabMapXLSX> findAll();

    @Override
    public ArrayList<TabMapXLSX> findByFieldString(String field, String value);


    public TabMapXLSX findByFieldTwoString(String field, String value, String field2, String value2);

    @Override
    public TabMapXLSX findById(int id);

    @Override
    void deleteById(int id);

    @Override
    public ArrayList<TabMapXLSX> findByFieldInteger(String field, Integer value);
}
