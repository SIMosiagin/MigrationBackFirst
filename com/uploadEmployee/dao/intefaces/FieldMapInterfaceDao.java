package uploadEmployee.dao.intefaces;

import uploadEmployee.entity.FieldMap;
import uploadEmployee.entity.TabMapXLSX;


import java.util.ArrayList;
import java.util.List;

public interface FieldMapInterfaceDao<L> extends InterfaceDao <FieldMap> {
    @Override
    void save(FieldMap object);

    @Override
    public List<FieldMap> findAll();

    @Override
    public FieldMap findById(int id);

    @Override
    void deleteById(int id);

    @Override
    public ArrayList<FieldMap> findByFieldString(String field, String value);

    @Override
    public ArrayList<FieldMap> findByFieldInteger(String field, Integer value);

    public ArrayList<FieldMap> findByLink(L l);

    public FieldMap findByLinkAndFieldString(TabMapXLSX tabMapXLSX, String tableField);
}
