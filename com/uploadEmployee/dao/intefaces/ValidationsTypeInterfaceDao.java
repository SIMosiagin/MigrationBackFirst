package uploadEmployee.dao.intefaces;

import uploadEmployee.entity.ValidationsType;

import java.util.ArrayList;
import java.util.List;

public interface ValidationsTypeInterfaceDao extends InterfaceDao <ValidationsType> {
    @Override
    void save(ValidationsType object);

    @Override
    public List<ValidationsType> findAll();

    @Override
    public ValidationsType findById(int id);

    @Override
    void deleteById(int id);

    @Override
    public ArrayList<ValidationsType> findByFieldString(String field, String value);

    @Override
    public ArrayList<ValidationsType> findByFieldInteger(String field, Integer value);

    public ValidationsType findByType(String typeName);
}
