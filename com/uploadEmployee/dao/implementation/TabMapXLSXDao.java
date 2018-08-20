package uploadEmployee.dao.implementation;

import uploadEmployee.entity.TabMapXLSX;
import uploadEmployee.dao.intefaces.TabMapXLSXInterfaceDao;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("TabMapXLSXInterfaceDao")
public class TabMapXLSXDao extends AbstractDao implements TabMapXLSXInterfaceDao {

    public void save(TabMapXLSX object) {
        persist(object);
    }

    public List<TabMapXLSX> findAll() {
        Criteria criteria = getSession().createCriteria(TabMapXLSX.class);
        return (List<TabMapXLSX>)criteria.list();
    }


    public TabMapXLSX findById(int id) {
        Criteria criteria = getSession().createCriteria(TabMapXLSX.class);
        criteria.add(Restrictions.eq("id", id));
        return (TabMapXLSX)criteria.uniqueResult();
    }

    public void deleteById(int id) {
        Query query = getSession().createQuery("delete from  TabMapXLSX   where id = :id");
        query.setInteger("id",id);
        query.executeUpdate();

    }

    public ArrayList<TabMapXLSX> findByFieldString(String field, String value) {
        Criteria criteria = getSession().createCriteria(TabMapXLSX.class);
        criteria.add(Restrictions.eq(field, value));
        return (ArrayList<TabMapXLSX>)criteria.list();
    }

    public ArrayList<TabMapXLSX> findByFieldInteger(String field, Integer value) {
        Criteria criteria = getSession().createCriteria(TabMapXLSX.class);
        criteria.add(Restrictions.eq(field, value));
        return (ArrayList<TabMapXLSX>)criteria.uniqueResult();
    }



    public TabMapXLSX findByFieldTwoString(String field, String value, String field2, String value2) {
        Criteria criteria = getSession().createCriteria(TabMapXLSX.class);
        criteria.add(Restrictions.eq(field, value));
        criteria.add(Restrictions.eq(field2, value2));
        return (TabMapXLSX)criteria.uniqueResult();
    }
}
