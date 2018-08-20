package uploadEmployee.dao.implementation;

import uploadEmployee.entity.ValidationManualData;
import uploadEmployee.dao.intefaces.ValidationManualDataInterfaceDao;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("ValidationManualDataInterfaceDao")
public class ValidationManualDataDao extends AbstractDao implements ValidationManualDataInterfaceDao {
    public void save(ValidationManualData object) {
        persist(object);
    }

    public ArrayList<ValidationManualData> findAll() {
        Criteria criteria = getSession().createCriteria(ValidationManualData.class);
        return (ArrayList<ValidationManualData>)criteria.list();
    }

    public ValidationManualData findById(int id) {
        Criteria criteria = getSession().createCriteria(ValidationManualData.class);
        criteria.add(Restrictions.eq("id", id));
        return (ValidationManualData)criteria.uniqueResult();
    }

    public void deleteById(int id) {
        Query query = getSession().createQuery("delete from  ValidationManualData   where id = :id");
        query.setInteger("id",id);
        query.executeUpdate();

    }

    public ArrayList<ValidationManualData> findByFieldString(String field, String value) {
        Criteria criteria = getSession().createCriteria(ValidationManualData.class);
        criteria.add(Restrictions.eq(field, value));
        return (ArrayList<ValidationManualData>)criteria.uniqueResult();
    }

    public ArrayList<ValidationManualData> findByFieldInteger(String field, Integer value) {
        Criteria criteria = getSession().createCriteria(ValidationManualData.class);
        criteria.add(Restrictions.eq(field, value));
        return (ArrayList<ValidationManualData>)criteria.uniqueResult();
    }
}
