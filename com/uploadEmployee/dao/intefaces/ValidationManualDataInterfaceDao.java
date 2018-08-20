package uploadEmployee.dao.intefaces;

import uploadEmployee.entity.ValidationManualData;

import java.util.ArrayList;
import java.util.List;

public interface ValidationManualDataInterfaceDao extends InterfaceDao <ValidationManualData> {
    @Override
    void save(ValidationManualData object);

    @Override
    public ArrayList<ValidationManualData> findAll();

    @Override
    public ValidationManualData findById(int id);

    @Override
    void deleteById(int id);

    @Override
    public ArrayList<ValidationManualData> findByFieldString(String field, String value);

    @Override
    public ArrayList<ValidationManualData>  findByFieldInteger(String field, Integer value);
}
