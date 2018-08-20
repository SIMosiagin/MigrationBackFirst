package uploadEmployee.service.intefaces;

import java.util.ArrayList;
import java.util.List;

public interface InterfaceService <T> {
    void save(T obj);
    public List<T> findAll();
    public ArrayList<T> findByFieldString(String field, String value);
    public ArrayList<T>  findByFieldInteger(String field, Integer value);
    public T findById(int id);
    void deleteById(int id);



}
