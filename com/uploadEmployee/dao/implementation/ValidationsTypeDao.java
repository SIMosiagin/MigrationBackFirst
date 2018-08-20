package uploadEmployee.dao.implementation;

import uploadEmployee.entity.ValidationManualData;
import uploadEmployee.entity.ValidationsType;
import uploadEmployee.dao.intefaces.ValidationsTypeInterfaceDao;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("ValidationsTypeInterfaceDao")
public class ValidationsTypeDao extends AbstractDao implements ValidationsTypeInterfaceDao {
    public void save(ValidationsType object) {
        persist(object);
    }

    public List<ValidationsType> findAll() {
        Criteria criteria = getSession().createCriteria(ValidationsType.class);
        return (List<ValidationsType>)criteria.list();
    }

    public ValidationsType findById(int id) {
        Criteria criteria = getSession().createCriteria(ValidationsType.class);
        criteria.add(Restrictions.eq("id", id));
        return (ValidationsType)criteria.uniqueResult();
    }

    public void deleteById(int id) {
        Query query = getSession().createQuery("delete from  ValidationsType   where id = :id");
        query.setInteger("id",id);
        query.executeUpdate();

    }

    public ArrayList<ValidationsType> findByFieldString(String field, String value) {
        Criteria criteria = getSession().createCriteria(ValidationsType.class);
        criteria.add(Restrictions.eq(field, value));
        return (ArrayList<ValidationsType>)criteria.uniqueResult();
    }

    public ArrayList<ValidationsType> findByFieldInteger(String field, Integer value) {
        Criteria criteria = getSession().createCriteria(ValidationsType.class);
        criteria.add(Restrictions.eq(field, value));
        return (ArrayList<ValidationsType>)criteria.uniqueResult();
    }

    public ValidationsType findByType(String typeName){
        Criteria criteria = getSession().createCriteria(ValidationsType.class);
        criteria.add(Restrictions.eq("typeName", typeName));
        return (ValidationsType)criteria.uniqueResult();
    }

}
