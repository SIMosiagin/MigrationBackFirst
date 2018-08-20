package uploadEmployee.dao.intefaces;

import java.util.ArrayList;
import java.util.List;

public interface InterfaceDao <T> {

    void save(T object);
    public List<T> findAll();
    public T findById(int id);
    void deleteById(int id);
    public ArrayList<T> findByFieldString(String field, String value);
    public ArrayList<T> findByFieldInteger(String field, Integer value);



}
