package uploadEmployee.dao.implementation;


import uploadEmployee.entity.FieldMap;
import uploadEmployee.dao.intefaces.FieldMapInterfaceDao;
import uploadEmployee.entity.TabMapXLSX;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("FieldMapInterfaceDao")
public class FieldMapDao extends AbstractDao implements FieldMapInterfaceDao<TabMapXLSX> {


    public void save(FieldMap object) {
        persist(object);
    }

    public List<FieldMap> findAll() {
        Criteria criteria = getSession().createCriteria(FieldMap.class);
        return (List<FieldMap>)criteria.list();
    }

    public FieldMap findById(int id) {
        Criteria criteria = getSession().createCriteria(FieldMap.class);
        criteria.add(Restrictions.eq("id", id));
        return (FieldMap)criteria.uniqueResult();
    }

    public void deleteById(int id) {
        Query query = getSession().createQuery("delete from  FieldMap   where id = :id");
        query.setInteger("id",id);
        query.executeUpdate();

    }

    public ArrayList<FieldMap> findByFieldString(String field, String value) {
        Criteria criteria = getSession().createCriteria(FieldMap.class);
        criteria.add(Restrictions.eq(field, value));
        return (ArrayList<FieldMap>)criteria.list();
    }


    public ArrayList<FieldMap> findByFieldInteger(String field, Integer value) {
        Criteria criteria = getSession().createCriteria(FieldMap.class);
        criteria.add(Restrictions.eq(field, value));
        return (ArrayList<FieldMap>)criteria.list();
    }


    public ArrayList<FieldMap> findByLink(TabMapXLSX object) {
        Criteria criteria = getSession().createCriteria(FieldMap.class);
        criteria.add(Restrictions.eq("id_tab_map_xlsx", object));
        return (ArrayList<FieldMap>)criteria.list();
    }

    public FieldMap findByLinkAndFieldString(TabMapXLSX tabMapXLSX, String xlsxField) {
        Criteria criteria = getSession().createCriteria(FieldMap.class);
        criteria.add(Restrictions.eq("id_tab_map_xlsx", tabMapXLSX));
        criteria.add(Restrictions.eq("xlsxField", xlsxField));
        return (FieldMap)criteria.uniqueResult();
    }
}
