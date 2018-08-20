package uploadEmployee.dao.implementation;

import uploadEmployee.entity.TabMapXLSX;
import uploadEmployee.entity.Validation;
import uploadEmployee.dao.intefaces.ValidationInterfaceDao;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("ValidationInterfaceDao")
public class ValidationDao extends AbstractDao implements ValidationInterfaceDao {

    public void save(Validation object) {
        persist(object);
    }

    public List<Validation> findAll() {
        Criteria criteria = getSession().createCriteria(Validation.class);
        return (List<Validation>)criteria.list();
    }

    public Validation findById(int id) {
        Criteria criteria = getSession().createCriteria(Validation.class);
        criteria.add(Restrictions.eq("id", id));
        return (Validation)criteria.uniqueResult();
    }

    public void deleteById(int id) {
        Query query = getSession().createQuery("delete from  Validation   where id = :id");
        query.setInteger("id",id);
        query.executeUpdate();

    }

    public ArrayList<Validation> findByFieldString(String field, String value) {
        Criteria criteria = getSession().createCriteria(Validation.class);
        criteria.add(Restrictions.eq(field, value));
        return (ArrayList<Validation>)criteria.uniqueResult();
    }

    public ArrayList<Validation> findByFieldInteger(String field, Integer value) {
        Criteria criteria = getSession().createCriteria(Validation.class);
        criteria.add(Restrictions.eq(field, value));
        return (ArrayList<Validation>)criteria.uniqueResult();
    }

    public ArrayList<Validation> findByTabMapXLSX (TabMapXLSX tabMapXLSX){
        Criteria criteria = getSession().createCriteria(Validation.class);
        criteria.add(Restrictions.eq("tabMapXLSX", tabMapXLSX));
        return (ArrayList<Validation>)criteria.list();
    }

    public Validation findByLinkAndFieldString(TabMapXLSX tabMapXLSX, String fieldMap) {
        Criteria criteria = getSession().createCriteria(Validation.class);
        criteria.add(Restrictions.eq("tabMapXLSX", tabMapXLSX));
        criteria.add(Restrictions.eq("fieldMap", fieldMap));
        return (Validation)criteria.uniqueResult();
    }

}
