package uploadEmployee.service.intefaces;

import uploadEmployee.entity.ValidationManualData;

import java.util.ArrayList;
import java.util.List;

public interface ValidationManualDataInterfaceService extends InterfaceService <ValidationManualData> {
    @Override
    void save(ValidationManualData obj);

    @Override
    public ArrayList<ValidationManualData> findAll();

    @Override
    public ArrayList<ValidationManualData>  findByFieldString(String field, String value);

    @Override
    public ValidationManualData findById(int id);

    @Override
    void deleteById(int id);

    @Override
    public ArrayList<ValidationManualData> findByFieldInteger(String field, Integer value);
}
